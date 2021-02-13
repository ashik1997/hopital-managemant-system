/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalERP;

import static hospitalERP.HomePage.date;
import static hospitalERP.HomePage.time;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Aashiq
 */
public class ChackInOut extends javax.swing.JInternalFrame {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public ChackInOut() {
        initComponents();
        conn= DatabaseConnection.db_connect();
    }
///---image search and set for CheckIn
    void ImagesearchCheckIn(){
        try{
        String sql = "select * from EmployeeTable where Employee_ID=?";
        pst = conn.prepareStatement(sql);
        pst.setString(1, txt_employeeid_checkin.getText());
        rs = pst.executeQuery();
        if (rs.next()) {
            byte[] person_image=rs.getBytes("Image");
            ImageIcon imageicon = new ImageIcon(new ImageIcon(person_image).getImage().getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(), Image.SCALE_DEFAULT));
            lbl_image.setIcon(imageicon);                
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        finally{
            try{
            rs.close();
            pst.close();
            }catch(SQLException e){}
        }
    }
 //----end image search and set for CheckIn 
 
///---image search and set for CheckOut    
    void ImagesearchCheckOut(){
        try{
        String sql = "select * from EmployeeTable where Employee_ID=?";
        pst = conn.prepareStatement(sql);
        pst.setString(1, txt_employeeid_checkout.getText());
        rs = pst.executeQuery();
        if (rs.next()) {
            byte[] person_image=rs.getBytes("Image");
            ImageIcon imageicon = new ImageIcon(new ImageIcon(person_image).getImage().getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(), Image.SCALE_DEFAULT));
            lbl_image.setIcon(imageicon);                
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        finally{
            try{
            rs.close();
            pst.close();
            }catch(SQLException e){}
        }
    }
//-------end image search and set for CheckOut 

// ------data insert into attandence table   
    void CheckIn(){
         //--already submited or not
         
        try{
            String sql1 = "select * from EmployeeAttandence where Employee_ID=? and Date=?";
            pst=conn.prepareStatement(sql1);
            pst.setString(1, txt_employeeid_checkin.getText());
            pst.setString(2, date);            
            rs=pst.executeQuery();
            if(rs.next()){
            JOptionPane.showMessageDialog(null, "Already Submited");//end check submited
            }else{
            
        if("".equals(txt_employeeid_checkin.getText())){
        txt_employeeid_checkin.requestFocus();
        }else{    
        try {
            String sql = "insert into EmployeeAttandence (Employee_ID,Date,In_Time) values (?,?,?)";            
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_employeeid_checkin.getText());
            pst.setString(2, date);
            pst.setString(3, time);
            pst.execute();
            txt_employeeid_checkin.setText("");
            lbl_image.setIcon(null);
          
    //            JOptionPane.showMessageDialog(null, "Data Saved");
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
            finally{
                try{
                    rs.close();
                    pst.close();
                }catch(SQLException e){}
            }                                       
            }
        }
        }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(SQLException e){}
        }
    }
//------end data insert into attandence table

//------update attandence table    
    void CheckOut(){
        if("".equals(txt_employeeid_checkout.getText())){
        txt_employeeid_checkout.requestFocus();
        }else{        
        try{
        String sql = "update EmployeeAttandence set Out_Time='"+time+"'"           
            + "where Employee_ID='"+txt_employeeid_checkout.getText()+"' and Date='"+date+"' ";
        pst = conn.prepareStatement(sql);
        pst.execute();
        txt_employeeid_checkout.setText("");
        lbl_image.setIcon(null);
        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
            try {
                rs.close();
                pst.close();
            } catch (SQLException e) {}
            }        
        }        
    }
//-----end update attandence table
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btn_submiti_checkout = new javax.swing.JButton();
        txt_employeeid_checkout = new javax.swing.JTextField();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        lbl_imagein1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_submiti_checkin = new javax.swing.JButton();
        txt_employeeid_checkin = new javax.swing.JTextField();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        lbl_imagein = new javax.swing.JLabel();
        jDesktopPane3 = new javax.swing.JDesktopPane();
        lbl_image = new javax.swing.JLabel();

        setClosable(true);

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Check out", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Academy Engraved LET", 2, 18))); // NOI18N
        jPanel2.setMinimumSize(new java.awt.Dimension(280, 140));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Eployee ID :");

        btn_submiti_checkout.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_submiti_checkout.setText("Submit");
        btn_submiti_checkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_submiti_checkoutActionPerformed(evt);
            }
        });

        txt_employeeid_checkout.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_employeeid_checkout.setToolTipText("Enter id");
        txt_employeeid_checkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_employeeid_checkoutActionPerformed(evt);
            }
        });
        txt_employeeid_checkout.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_employeeid_checkoutKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_employeeid_checkoutKeyReleased(evt);
            }
        });

        jDesktopPane2.setLayer(lbl_imagein1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_imagein1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_imagein1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(320, Short.MAX_VALUE)
                        .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel2)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btn_submiti_checkout, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txt_employeeid_checkout))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_employeeid_checkout, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addComponent(btn_submiti_checkout, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Check in", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Academy Engraved LET", 2, 18))); // NOI18N
        jPanel1.setMinimumSize(new java.awt.Dimension(280, 140));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Eployee ID :");

        btn_submiti_checkin.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_submiti_checkin.setText("Submit");
        btn_submiti_checkin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_submiti_checkinActionPerformed(evt);
            }
        });

        txt_employeeid_checkin.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_employeeid_checkin.setToolTipText("Enter id");
        txt_employeeid_checkin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_employeeid_checkinActionPerformed(evt);
            }
        });
        txt_employeeid_checkin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_employeeid_checkinKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_employeeid_checkinKeyReleased(evt);
            }
        });

        jDesktopPane1.setLayer(lbl_imagein, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_imagein, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_imagein, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_submiti_checkin, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 121, Short.MAX_VALUE))
                            .addComponent(txt_employeeid_checkin))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_employeeid_checkin, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addComponent(btn_submiti_checkin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jDesktopPane3.setLayer(lbl_image, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane3Layout = new javax.swing.GroupLayout(jDesktopPane3);
        jDesktopPane3.setLayout(jDesktopPane3Layout);
        jDesktopPane3Layout.setHorizontalGroup(
            jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_image, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
        );
        jDesktopPane3Layout.setVerticalGroup(
            jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_image, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jDesktopPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jDesktopPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_submiti_checkinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_submiti_checkinActionPerformed
         CheckIn();
    }//GEN-LAST:event_btn_submiti_checkinActionPerformed

    private void txt_employeeid_checkinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_employeeid_checkinActionPerformed

    }//GEN-LAST:event_txt_employeeid_checkinActionPerformed

    private void txt_employeeid_checkinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_employeeid_checkinKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){ //enter press code
            CheckIn();
        }
    }//GEN-LAST:event_txt_employeeid_checkinKeyPressed

    private void txt_employeeid_checkinKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_employeeid_checkinKeyReleased
        ImagesearchCheckIn();
    }//GEN-LAST:event_txt_employeeid_checkinKeyReleased

    private void btn_submiti_checkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_submiti_checkoutActionPerformed
        CheckOut();
    }//GEN-LAST:event_btn_submiti_checkoutActionPerformed

    private void txt_employeeid_checkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_employeeid_checkoutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_employeeid_checkoutActionPerformed

    private void txt_employeeid_checkoutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_employeeid_checkoutKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){  //enter press code
            CheckOut();
        }
    }//GEN-LAST:event_txt_employeeid_checkoutKeyPressed

    private void txt_employeeid_checkoutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_employeeid_checkoutKeyReleased
        ImagesearchCheckOut();
    }//GEN-LAST:event_txt_employeeid_checkoutKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_submiti_checkin;
    private javax.swing.JButton btn_submiti_checkout;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JDesktopPane jDesktopPane3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbl_image;
    private javax.swing.JLabel lbl_imagein;
    private javax.swing.JLabel lbl_imagein1;
    private javax.swing.JTextField txt_employeeid_checkin;
    private javax.swing.JTextField txt_employeeid_checkout;
    // End of variables declaration//GEN-END:variables
}
