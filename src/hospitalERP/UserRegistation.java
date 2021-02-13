/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalERP;

import static hospitalERP.Login.loginby;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Aashiq
 */
public class UserRegistation extends javax.swing.JInternalFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public UserRegistation() {
        initComponents();
        conn= DatabaseConnection.db_connect();
        Update_BloodDonor_table();
    }

    public void Update_BloodDonor_table(){
        try{
        String sl="select Employee_ID,User_Name,Division,Registation_By from UserRegistation";
        pst=conn.prepareStatement(sl);
        rs=pst.executeQuery();
        tbl_user.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(SQLException e){
//        JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
            rs.close();
            pst.close();
            }catch(SQLException e){}
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_User_name = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_employee_id = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btn_save = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        cmb_division = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txt_passwrd = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_user = new javax.swing.JTable();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        lbl_image = new javax.swing.JLabel();
        btn_attach = new javax.swing.JButton();

        setClosable(true);
        setTitle("User Registation");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 13))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("User Name");

        txt_User_name.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Password");

        txt_employee_id.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_employee_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_employee_idActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Employee ID");

        btn_save.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_save.setText("Save");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        btn_delete.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_update.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        cmb_division.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmb_division.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Admin", "Doctor", "Nurse", "Staff" }));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Division");

        txt_passwrd.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(3, 3, 3))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel2))
                            .addGap(3, 3, 3))
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txt_passwrd)
                        .addComponent(txt_User_name)
                        .addComponent(cmb_division, javax.swing.GroupLayout.Alignment.TRAILING, 0, 238, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(btn_delete)
                                .addGap(23, 23, 23)
                                .addComponent(btn_update)
                                .addGap(18, 18, 18)
                                .addComponent(btn_save))
                            .addComponent(txt_employee_id, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_employee_id, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_User_name, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_passwrd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_division, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_save)
                    .addComponent(btn_delete)
                    .addComponent(btn_update))
                .addContainerGap())
        );

        tbl_user.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_userMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_user);

        jDesktopPane1.setLayer(lbl_image, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_image, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_image, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
        );

        btn_attach.setText("Attach");
        btn_attach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_attachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDesktopPane1)
                            .addComponent(btn_attach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_attach)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_employee_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_employee_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_employee_idActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        int p=JOptionPane.showConfirmDialog(null,"Do You Want To Save","Saved",JOptionPane.YES_NO_OPTION);
        if(p==0){    
        try {
            String sql = "insert into UserRegistation (Employee_ID,User_Name,Password,Division,Registation_By,Image) values (?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_employee_id.getText());
            pst.setString(2, txt_User_name.getText());
            pst.setString(3, txt_passwrd.getText());
            pst.setString(4, cmb_division.getSelectedItem().toString());
            pst.setString(5, loginby);
            pst.setBytes(6, person_image);
            pst.execute();
            Update_BloodDonor_table();
            lbl_image.setIcon(null);
//            JOptionPane.showMessageDialog(null, "Data Saved");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        finally{
        try{
        rs.close();
        pst.close();
        }catch(Exception e){}
        }
        }
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        int p = JOptionPane.showConfirmDialog(null, "Do You Want To Delete", "Delete", JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            try {
                String sql = "delete from UserRegistation where Employee_ID=? and User_Name=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, txt_employee_id.getText());
                pst.setString(2, txt_User_name.getText());
                pst.execute();
                Update_BloodDonor_table();
                lbl_image.setIcon(null);
//                JOptionPane.showMessageDialog(null, "Data Deleted");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {}
            }
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        int p=JOptionPane.showConfirmDialog(null,"Do You Want To Update","Update",JOptionPane.YES_NO_OPTION);
        if(p==0){
            try{
                String sql = "update UserRegistation set Employee_ID='"+txt_employee_id.getText()+"',"
                + "User_Name='"+txt_User_name.getText()+"',"                    
                + "Password='"+txt_passwrd.getText()+"',"
                + "Division='"+cmb_division.getSelectedItem().toString()+"'," 
                + "Registation_By='"+loginby+"'"
//                + "Image='"+(person_image)+"'"        
                + "where Employee_ID='"+txt_employee_id.getText()+"'";
                pst = conn.prepareStatement(sql);                   
                pst.execute();
                Update_BloodDonor_table();
                lbl_image.setIcon(null);
                //        JOptionPane.showMessageDialog(null,"Upadate");
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
            }
            finally {
            try {
                rs.close();
                pst.close();
            }catch (SQLException e) {}
            }                
        }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void tbl_userMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_userMouseClicked
        try{
            int row=tbl_user.getSelectedRow();
            String tableClick=(tbl_user.getModel().getValueAt(row, 0).toString());
            String sql="select * from UserRegistation where Employee_ID='"+tableClick+"' ";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                txt_employee_id.setText(rs.getString("Employee_ID"));
                txt_User_name.setText(rs.getString("User_Name"));
                txt_passwrd.setText(rs.getString("Password"));
                cmb_division.setSelectedItem(rs.getString("Division"));
                byte[] person_imag=rs.getBytes("Image");
                ImageIcon imageicon = new ImageIcon(new ImageIcon(person_imag).getImage().getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(), Image.SCALE_DEFAULT));
                lbl_image.setIcon(imageicon);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);

        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){}
        }
    }//GEN-LAST:event_tbl_userMouseClicked

    private void btn_attachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_attachActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename= f.getAbsolutePath();
        ImageIcon imageicon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(), Image.SCALE_DEFAULT));
        lbl_image.setIcon(imageicon);
//        lbl_image.setText(filename);
        try {
            File image = new File(filename);
            FileInputStream fix = new FileInputStream(image);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int number; (number = fix.read(buf)) != -1;) {
                bos.write(buf, 0, number);
            }
            person_image = bos.toByteArray();
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
        }
        finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {
                //JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_btn_attachActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_attach;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cmb_division;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_image;
    private javax.swing.JTable tbl_user;
    private javax.swing.JTextField txt_User_name;
    private javax.swing.JTextField txt_employee_id;
    private javax.swing.JPasswordField txt_passwrd;
    // End of variables declaration//GEN-END:variables
    private ImageIcon format = null;
    byte[] person_image = null;
    String filename = null;
}
