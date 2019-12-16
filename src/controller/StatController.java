/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Models.Connect;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import view.Stat;


/**
 *
 * @author tranm
 */
public class StatController {
    public void ThongKe(String DateCheckFrom, String DateCheckTo, DefaultTableModel tbnBillStat, JTable tbBillStat){
        ResultSet rs = null;
        try {
            Vector column;
            column = new Vector();
            Statement statement = Connect.getConnection().createStatement();
            String sql = "select * from Bill" + " where DataCheck between '" + DateCheckFrom + "' and '" + DateCheckTo +"'" ;
            rs = statement.executeQuery(sql); 
            column.add("Ngày ");
            column.add("Tổng tiền ");
            tbnBillStat.setColumnIdentifiers(column);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Vector data = null;
            tbnBillStat.setRowCount(0);
            if (rs != null) {
                while (rs.next()) {
                    data = new Vector();
                    data.addElement(rs.getString("DataCheck"));
                    data.addElement(rs.getString("totalPrice"));                   
                    // Thêm một dòng vào table model
                    tbnBillStat.addRow(data);
                }
            } 
            else {
                ;
            }
            tbBillStat.setModel(tbnBillStat);
        } catch (Exception ex) {
            Logger.getLogger(Stat.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Connect.close();
        }
    }
}
