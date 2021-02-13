package hospitalERP;

import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
//import net.proteanit.sql.DbUtils;

public class BloodDonorRegistation extends javax.swing.JInternalFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public BloodDonorRegistation() {
        initComponents();
        conn= DatabaseConnection.db_connect();
        Update_BloodDonor_table();
        AutoDonorNo();
        
    } 
    
    public void Update_BloodDonor_table(){
        try{
        String sl="select * from BloodDonor";
        pst=conn.prepareStatement(sl);
        rs=pst.executeQuery();
        tbl_blood_donor_list.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
            rs.close();
            pst.close();
            }catch(Exception e){}
        }
    }
    
    public void AutoDonorNo(){
        try{
            String sl="select max(Donor_No) from BloodDonor";
            pst=conn.prepareStatement(sl);
            rs=pst.executeQuery();
            String s=rs.getString("max(Donor_No)");
            if(s==null){
            txt_donor_no.setText("1"); 
            }else{
            int a = Integer.parseInt(s);
            int b=a+1;
            txt_donor_no.setText(String.valueOf(b));
            }  
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        finally{
        try{
            rs.close();
            pst.close();
        }catch(Exception e){}
        }
    }
    public void ClearField(){
        txt_name.setText(null);
        txt_age.setText(null);
        txt_phone.setText(null);
        txt_email.setText(null);
        txt_address.setText(null);
        txt_search.setText("Enter Blood Group , Phone , Name , Address");
        cmb_blood_group.setSelectedIndex(0);
        cmb_sex.setSelectedIndex(0);
        Update_BloodDonor_table();
        AutoDonorNo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txt_email = new javax.swing.JTextField();
        txt_donor_no = new javax.swing.JTextField();
        txt_name = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_phone = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmb_sex = new javax.swing.JComboBox<>();
        txt_age = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cmb_blood_group = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_address = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_save = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_blood_donor_list = new javax.swing.JTable();
        txt_search = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(204, 0, 0));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/blood-icon.png"))); // NOI18N

        txt_email.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txt_donor_no.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_donor_no.setPreferredSize(new java.awt.Dimension(10, 23));
        txt_donor_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_donor_noActionPerformed(evt);
            }
        });

        txt_name.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Phone");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Name");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Address");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Age");

        txt_phone.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_phoneActionPerformed(evt);
            }
        });
        txt_phone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_phoneKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Sex");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Blood Group");

        cmb_sex.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmb_sex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Male", "Female" }));

        txt_age.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Email");

        cmb_blood_group.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmb_blood_group.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-" }));
        cmb_blood_group.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_blood_groupActionPerformed(evt);
            }
        });

        txt_address.setColumns(20);
        txt_address.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_address.setRows(5);
        jScrollPane1.setViewportView(txt_address);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Donor No");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_blood_group, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_donor_no, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmb_sex, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_age))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_email))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_name))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_phone)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_donor_no, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_blood_group, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_age, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_sex, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        btn_save.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Actions-document-save-icon.png"))); // NOI18N
        btn_save.setText("Save");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        btn_update.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Actions-edit-redo-icon.png"))); // NOI18N
        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Actions-window-close-icon.png"))); // NOI18N
        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_clear.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btn_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Clear-icon.png"))); // NOI18N
        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(btn_delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(btn_update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_update)
                    .addComponent(btn_save)
                    .addComponent(btn_delete)
                    .addComponent(btn_clear))
                .addContainerGap())
        );

        tbl_blood_donor_list.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_blood_donor_list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_blood_donor_listMouseClicked(evt);
            }
        });
        tbl_blood_donor_list.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbl_blood_donor_listKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_blood_donor_list);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1311, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        jScrollPane4.setViewportView(jPanel5);

        txt_search.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_search.setText("Enter Blood Group , Phone , Name , Address");
        txt_search.setToolTipText("Enter Blood Group");
        txt_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_searchMouseClicked(evt);
            }
        });
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_searchKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setText("Search");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/blood.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addComponent(jLabel10)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel11)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        jScrollPane2.setViewportView(jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 929, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        if("".equals(txt_name.getText())||"".equals(txt_donor_no.getText())){
        JOptionPane.showMessageDialog(null, "Please Enter Donor No And Name");
        }else{
        int p = JOptionPane.showConfirmDialog(null, "Do You Want To Delete", "Delete", JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            try {
                String sql = "delete from BloodDonor where Donor_No=? and Name=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, txt_donor_no.getText());
                pst.setString(2, txt_name.getText());
                pst.execute();
//                JOptionPane.showMessageDialog(null, "Data Deleted");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
        AutoDonorNo();
        ClearField();
        Update_BloodDonor_table();
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        
        try{
            String sql1 = "select * from BloodDonor where Donor_No=?";
            pst=conn.prepareStatement(sql1);
            pst.setString(1, txt_donor_no.getText());
            rs=pst.executeQuery();
            if(rs.next()){
            JOptionPane.showMessageDialog(null, "Already Exist");
            AutoDonorNo();
            }else{
            
        if("".equals(cmb_blood_group.getSelectedItem().toString())){
        cmb_blood_group.requestFocus();
        }else if("".equals(txt_name.getText())){
        txt_name.requestFocus();
        }else if("".equals(txt_age.getText())){
        txt_age.requestFocus();
        }else if("".equals(txt_phone.getText())){
        txt_phone.requestFocus();
        }else{
            int p=JOptionPane.showConfirmDialog(null,"Do You Want To Save","Saved",JOptionPane.YES_NO_OPTION);
            if(p==0){
                
                try {
                    String sql = "insert into BloodDonor (Donor_No,Blood_Group,Name,Age,Sex,Phone,Email,Address) values (?,?,?,?,?,?,?,?)";
                    pst = conn.prepareStatement(sql);
                    
                    pst.setString(1, txt_donor_no.getText());
                    pst.setString(2, (cmb_blood_group.getSelectedItem().toString()));
                    pst.setString(3, txt_name.getText());
                    pst.setString(4, txt_age.getText());
                    pst.setString(5, (cmb_sex.getSelectedItem().toString()));
                    pst.setString(6, txt_phone.getText());
                    pst.setString(7, txt_email.getText());
                    pst.setString(8, txt_address.getText());                    
                    pst.execute();
//            JOptionPane.showMessageDialog(null, "Data Saved");
                }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                }
                finally{
                    try{
                    rs.close();
                    pst.close();
                    }catch(Exception e){}
                }
            }
            Update_BloodDonor_table();
            ClearField();
            AutoDonorNo();
        }
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
            rs.close();
            pst.close();
            }catch(Exception e){}
        }    
    }//GEN-LAST:event_btn_saveActionPerformed

    private void txt_donor_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_donor_noActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_donor_noActionPerformed

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
        try {
        String sql1 = "select * from BloodDonor  where Blood_Group like  '" + txt_search.getText() + "%' order by Blood_Group asc";
        pst=conn.prepareStatement(sql1);
        rs=pst.executeQuery();
        tbl_blood_donor_list.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (Exception e) {
        JOptionPane.showMessageDialog(null,e);
        }finally {
            try {
            rs.close();
            pst.close();
            }catch (Exception e) {}
        }
    }//GEN-LAST:event_txt_searchKeyPressed

    private void cmb_blood_groupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_blood_groupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_blood_groupActionPerformed

    private void tbl_blood_donor_listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_blood_donor_listMouseClicked
        try{
        int row=tbl_blood_donor_list.getSelectedRow();
        String tableClick=(tbl_blood_donor_list.getModel().getValueAt(row, 0).toString());
        String sql="select * from BloodDonor where Donor_No='"+tableClick+"' ";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        if(rs.next()){
        txt_donor_no.setText(rs.getString("Donor_No"));
        txt_name.setText(rs.getString("Name"));
        txt_age.setText(rs.getString("Age"));
        txt_phone.setText(rs.getString("Phone"));
        txt_email.setText(rs.getString("Email"));
        txt_address.setText(rs.getString("Address"));
        cmb_blood_group.setSelectedItem(rs.getString("Blood_Group"));
        cmb_sex.setSelectedItem(rs.getString("Sex"));
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
    }//GEN-LAST:event_tbl_blood_donor_listMouseClicked

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        if("".equals(cmb_blood_group.getSelectedItem().toString())){
        cmb_blood_group.requestFocus();
        }else if("".equals(txt_name.getText())){
        txt_name.requestFocus();
        }else if("".equals(txt_age.getText())){
        txt_age.requestFocus();
        }else if("".equals(txt_phone.getText())){
        txt_phone.requestFocus();
        }else{
        int p=JOptionPane.showConfirmDialog(null,"Do You Want To Update","Update",JOptionPane.YES_NO_OPTION);
        if(p==0){
        try{
        String sql = "update BloodDonor set Name='"+txt_name.getText()+"',"
            + "Age='"+txt_age.getText()+"',"
            + "Phone='"+txt_phone.getText()+"',"
            + "Email='"+txt_email.getText()+"',"
            + "Address='"+txt_address.getText()+"',"
            + "Blood_Group='"+cmb_blood_group.getSelectedItem().toString()+"',"
            + "Sex='"+cmb_sex.getSelectedItem().toString()+"'"
            + "where Donor_No='"+txt_donor_no.getText()+"' ";
        pst = conn.prepareStatement(sql);
        pst.execute();
//        JOptionPane.showMessageDialog(null,"Upadate");       
        }
        catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
        }
        finally {
            try {
            rs.close();
            pst.close();
            }catch (Exception e) {
            }
        }
        Update_BloodDonor_table();
        ClearField();
        AutoDonorNo();
        }
        }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void tbl_blood_donor_listKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_blood_donor_listKeyReleased
        try{
        int row=tbl_blood_donor_list.getSelectedRow();
        String tableClick=(tbl_blood_donor_list.getModel().getValueAt(row, 0).toString());
        String sql="select * from BloodDonor where Donor_No='"+tableClick+"' ";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        if(rs.next()){
        txt_donor_no.setText(rs.getString("Donor_No"));
        txt_name.setText(rs.getString("Name"));
        txt_age.setText(rs.getString("Age"));
        txt_phone.setText(rs.getString("Phone"));
        txt_email.setText(rs.getString("Email"));
        txt_address.setText(rs.getString("Address"));
        cmb_blood_group.setSelectedItem(rs.getString("Blood_Group"));
        cmb_sex.setSelectedItem(rs.getString("Sex"));
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
    }//GEN-LAST:event_tbl_blood_donor_listKeyReleased

    private void txt_phoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_phoneKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c)|| (c==KeyEvent.VK_BACK_SLASH)|| c==KeyEvent.VK_DELETE)){
        evt.consume();
        }
    }//GEN-LAST:event_txt_phoneKeyTyped

    private void txt_phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_phoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_phoneActionPerformed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased

    }//GEN-LAST:event_formMouseReleased

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        ClearField();
    }//GEN-LAST:event_btn_clearActionPerformed

    private void txt_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_searchMouseClicked
        txt_search.setText("");
    }//GEN-LAST:event_txt_searchMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cmb_blood_group;
    private javax.swing.JComboBox<String> cmb_sex;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tbl_blood_donor_list;
    private javax.swing.JTextArea txt_address;
    private javax.swing.JTextField txt_age;
    private javax.swing.JTextField txt_donor_no;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_phone;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
