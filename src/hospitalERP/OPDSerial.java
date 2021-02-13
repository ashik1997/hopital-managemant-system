package hospitalERP;


import static hospitalERP.Login.loginby;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
//import net.proteanit.sql.DbUtils;

public class OPDSerial extends javax.swing.JInternalFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public OPDSerial() {
        initComponents();
        conn= DatabaseConnection.db_connect();
        CurrentDateTime();
        Update_OPDSerial_table();
        AutoSerialNo();
        AutoOPDID();
        discount();
        AllDoctor();
        lbl_entered_by.setText(loginby);
    }

    public final void CurrentDateTime(){
         Calendar cal=new GregorianCalendar();
        int month=cal.get(Calendar.MONTH);
        int year=cal.get(Calendar.YEAR);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        txt_registation_date.setText((year+"-"+(month+1)+"-"+day));
        
//        int second=cal.get(Calendar.SECOND);
//        int minute=cal.get(Calendar.MINUTE);
//        int hour=cal.get(Calendar.HOUR);
//        lbl_time.setText("Time::"+(hour+":"+minute+":"+second));    
        }
    //-----This Method for OPD Serial table update
    public final void Update_OPDSerial_table(){
    try{
    String sl="select * from OPDSerial";
    pst=conn.prepareStatement(sl);
    rs=pst.executeQuery();
    tbl_opd_list.setModel(DbUtils.resultSetToTableModel(rs));
    }
    catch(SQLException e){
        JOptionPane.showMessageDialog(null, e);
    }
    finally{
        try{
            rs.close();
            pst.close();
        }catch(SQLException e){}
        }
    }
    
    
    
//-----This Method for autometic serial number creat     
    public final void AutoSerialNo(){
        try{
            String sl="select max(Serial_No) from OPDSerial where Doctor_Name=?";
            pst=conn.prepareStatement(sl);
            pst.setString(1, cmb_doctor_name.getSelectedItem().toString());
            rs=pst.executeQuery();
            String s=rs.getString("max(Serial_No)");
            if(s==null){
            txt_serial_no.setText("1"); 
            }else{
            int a = Integer.parseInt(s);
            int b=a+1;
            txt_serial_no.setText(String.valueOf(b));
            }
            
        }catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        finally{
        try{
            rs.close();
            pst.close();
        }catch(SQLException e){}
        }
        }
    ////-----all field clear
    public void ClearTextField(){
        txt_patient_name.setText("");
        txt_age.setText("");
        txt_contact_no.setText("");
        txt_address.setText("");
        txt_search.setText("Enter Phone No");
        cmb_blood_group.setSelectedIndex(0);
        cmb_gender.setSelectedIndex(0);
        cmb_doctor_name.setSelectedIndex(0);
        txt_total_amount.setText("0.0");
        txt_visit_amount.setText("0.0");
        lbl_doctor_name.setText("");
        txt_doctor_details.setText("");
        search.setText("Search enter name or phone or date ..........");
      
    }
    ///--automatic patient id create
     public final void AutoOPDID(){
        try{
            String sl="select max(OPD_ID) from MainOPDTable";
            pst=conn.prepareStatement(sl);
            rs=pst.executeQuery();
            String s=rs.getString("max(OPD_ID)");
            if(s==null){
            txt_opdid.setText("1"); 
            }else{
            int a = Integer.parseInt(s);
            int b=a+1;
            txt_opdid.setText(String.valueOf(b));
            }
            
        }catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        finally{
        try{
            rs.close();
            pst.close();
        }catch(SQLException e){}
        }
     } 
     ///---discount
       final void discount() {                                         
        double a=Double.parseDouble(txt_visit_amount.getText());
        double b=Double.parseDouble(txt_discount.getText());
        double c=((b*a)/100);
        txt_total_amount.setText(String.valueOf(a-c));
    }
      ///----insert main opd table
      void MainOPDTable(){
      try {
            String sql = "insert into MainOPDTable (OPD_ID,Patient_Name,Age,Gender,Blood_Group,Contact_No,Address,Doctor_Name,Visit_Amount,Entered_By) values (?,?,?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_opdid.getText());            
            pst.setString(2, txt_patient_name.getText());
            pst.setString(3, txt_age.getText());
            pst.setString(4, cmb_gender.getSelectedItem().toString());
            pst.setString(5, cmb_blood_group.getSelectedItem().toString());
            pst.setString(6, txt_contact_no.getText());
            pst.setString(7, txt_address.getText());          
            pst.setString(8, cmb_doctor_name.getSelectedItem().toString());
            pst.setString(9, txt_total_amount.getText());  
            pst.setString(10, loginby);
                         
            pst.execute();
      
      }catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }finally {
            try {
                rs.close();
                pst.close();
            }catch (SQLException e) {}
        }
      }
      ///----combo box set doctor name
    final void AllDoctor(){
        try {
        String sql="Select * from EmployeeTable where Depatment='doctor' or Depatment='Doctor' or Titel='Doctor' or Titel='doctor'";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        while (rs.next())
             {      
                        
                cmb_doctor_name.addItem(rs.getString("Employee_Name"));
             }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }finally {
            try {
                rs.close();
                pst.close();
            }catch (SQLException e) {}
        }
    }
//-----click doctor name then set all doctor info    
    void DoctorDetailsSearch(){
        try {
        String sql = "select * from EmployeeTable where Employee_Name=? ";
        pst = conn.prepareStatement(sql);
        pst.setString(1, cmb_doctor_name.getSelectedItem().toString());
        rs = pst.executeQuery();
        if (rs.next()) {
        lbl_doctor_name.setText(rs.getString("Employee_Name"));
        txt_visit_amount.setText(rs.getString("Visite_Amount"));
        txt_doctor_details.setText(rs.getString("Doctor_Detalies"));
        }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }finally {
            try {
                rs.close();
                pst.close();
            }catch (SQLException e) {}
        }
    }
///search and fillup all text field     
void MainOPDSearchSetField(){
        try {
        String sql = "select * from MainOPDTable where Contact_No=? ";
        pst = conn.prepareStatement(sql);
        pst.setString(1, txt_search.getText());
        rs = pst.executeQuery();
        
        if (rs.next()) {
        txt_opdid.setText(rs.getString("OPD_ID"));
        txt_patient_name.setText(rs.getString("Patient_Name"));
        txt_age.setText(rs.getString("Age"));
        cmb_gender.setSelectedItem(rs.getString("Gender"));        
        cmb_blood_group.setSelectedItem(rs.getString("Blood_Group"));
        txt_contact_no.setText(rs.getString("Contact_No"));
        txt_address.setText(rs.getString("Address"));
//        tbl_opd_list.setModel(DbUtils.resultSetToTableModel(rs));
        }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }finally {
            try {
                rs.close();
                pst.close();
            }catch (SQLException e) {}
        }
    }    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btn_clear = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_refresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_opd_list = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        lbl_doctor_name = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txt_doctor_details = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_contact_no = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        txt_address = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txt_registation_date = new javax.swing.JTextField();
        cmb_blood_group = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmb_gender = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txt_patient_name = new javax.swing.JTextField();
        txt_age = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        cmb_doctor_name = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txt_discount = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_total_amount = new javax.swing.JTextField();
        txt_visit_amount = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lbl_entered_by = new javax.swing.JLabel();
        txt_serial_no = new javax.swing.JTextField();
        txt_opdid = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("OPD Serial");

        btn_clear.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btn_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Clear-icon.png"))); // NOI18N
        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        btn_save.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Actions-document-save-icon.png"))); // NOI18N
        btn_save.setText("Save");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(77, 77, 77)
                .addComponent(btn_clear, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addGap(61, 61, 61)
                .addComponent(btn_save, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_clear)
                    .addComponent(btn_save)
                    .addComponent(btn_delete))
                .addGap(0, 0, 0))
        );

        btn_refresh.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Button-Refresh-icon.png"))); // NOI18N
        btn_refresh.setText("Refresh");
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });

        tbl_opd_list.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tbl_opd_list.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_opd_list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_opd_listMouseClicked(evt);
            }
        });
        tbl_opd_list.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbl_opd_listKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_opd_list);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doctor Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 11))); // NOI18N

        lbl_doctor_name.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_doctor_name.setText("Doctor name");

        txt_doctor_details.setBackground(new java.awt.Color(240, 240, 240));
        txt_doctor_details.setColumns(20);
        txt_doctor_details.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_doctor_details.setRows(5);
        jScrollPane4.setViewportView(txt_doctor_details);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_doctor_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_doctor_name, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contact Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 11))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Address");

        txt_contact_no.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_contact_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_contact_noActionPerformed(evt);
            }
        });
        txt_contact_no.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_contact_noKeyPressed(evt);
            }
        });

        txt_address.setColumns(20);
        txt_address.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_address.setRows(5);
        jScrollPane6.setViewportView(txt_address);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setText("Contact No");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                    .addComponent(txt_contact_no))
                .addGap(13, 13, 13))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_contact_no, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txt_search.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_search.setText("Enter Phone No");
        txt_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_searchMouseClicked(evt);
            }
        });
        txt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_searchActionPerformed(evt);
            }
        });
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_searchKeyPressed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel17.setText("Serial No");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Patient Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 11))); // NOI18N

        txt_registation_date.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_registation_date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_registation_dateActionPerformed(evt);
            }
        });
        txt_registation_date.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_registation_dateKeyPressed(evt);
            }
        });

        cmb_blood_group.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmb_blood_group.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "O +", "O -", "A +", "A -", "B +", "B -", "AB +", "AB -" }));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Age");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Registation Date");

        cmb_gender.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmb_gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Unknown" }));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel11.setText("Gender");

        txt_patient_name.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_patient_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_patient_nameActionPerformed(evt);
            }
        });
        txt_patient_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_patient_nameKeyPressed(evt);
            }
        });

        txt_age.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_age.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ageActionPerformed(evt);
            }
        });
        txt_age.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_ageKeyPressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel13.setText("Blood Group");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Patient Name");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_registation_date))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_patient_name))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_age))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_gender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_blood_group, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(13, 13, 13))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_registation_date, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_patient_name, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_age, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_gender, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_blood_group, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("Search");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doctor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 11))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel16.setText("Visit Amount");

        cmb_doctor_name.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmb_doctor_name.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Doctor" }));
        cmb_doctor_name.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_doctor_nameItemStateChanged(evt);
            }
        });
        cmb_doctor_name.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                cmb_doctor_nameMouseMoved(evt);
            }
        });
        cmb_doctor_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmb_doctor_nameMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmb_doctor_nameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmb_doctor_nameMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cmb_doctor_nameMousePressed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel14.setText("Doctor Name");

        txt_discount.setBackground(new java.awt.Color(240, 240, 240));
        txt_discount.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_discount.setText("0");
        txt_discount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_discountKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_discountKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel5.setText("Discount%");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel6.setText("Total");

        txt_total_amount.setBackground(new java.awt.Color(240, 240, 240));
        txt_total_amount.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txt_visit_amount.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_visit_amount.setText("0");
        txt_visit_amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_visit_amountKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_visit_amountKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Entered By-");

        lbl_entered_by.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_entered_by.setText("Name");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_doctor_name, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txt_visit_amount)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_discount, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_total_amount)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_entered_by, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_doctor_name, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_visit_amount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_discount, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(lbl_entered_by))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_total_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
        );

        txt_serial_no.setBackground(new java.awt.Color(240, 240, 240));
        txt_serial_no.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        txt_opdid.setBackground(new java.awt.Color(240, 240, 240));
        txt_opdid.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("OPD ID");

        search.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        search.setText("Search enter name or phone or date ..........");
        search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchMouseClicked(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(21, 21, 21))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_serial_no, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_opdid, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(search)
                                .addGap(18, 18, 18)
                                .addComponent(btn_refresh))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_search))))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(txt_opdid, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_serial_no))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_refresh)
                    .addComponent(search))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jScrollPane3.setViewportView(jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 912, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        setBounds(0, 0, 928, 649);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_registation_dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_registation_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_registation_dateActionPerformed

    private void txt_registation_dateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_registation_dateKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_registation_dateKeyPressed

    private void txt_patient_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_patient_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_patient_nameActionPerformed

    private void txt_patient_nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_patient_nameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_patient_nameKeyPressed

    private void txt_ageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ageActionPerformed

    private void txt_ageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ageKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ageKeyPressed

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchActionPerformed

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){  //enter press code
         MainOPDSearchSetField();   
        }
    }//GEN-LAST:event_txt_searchKeyPressed

    private void txt_contact_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_contact_noActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_contact_noActionPerformed

    private void txt_contact_noKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_contact_noKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_contact_noKeyPressed

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        Update_OPDSerial_table();
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        try{
            String sql1 = "select * from OPDSerial where Serial_No=? and Registation_Date=? and Doctor_Name=? and Patient_Name=?";
            pst=conn.prepareStatement(sql1);
            pst.setString(1, txt_serial_no.getText());
            pst.setString(2, txt_registation_date.getText());
            pst.setString(3, cmb_doctor_name.getSelectedItem().toString());
            pst.setString(4, txt_patient_name.getText());
            rs=pst.executeQuery();
            if(rs.next()){
            JOptionPane.showMessageDialog(null, "Already Exist");
            }else{
            
        if("".equals(txt_patient_name.getText())){
        txt_patient_name.requestFocus();
        }else if("".equals(cmb_doctor_name.getSelectedItem().toString())){
        cmb_doctor_name.requestFocus();
        }else{
        int p=JOptionPane.showConfirmDialog(null,"Do You Want To Save","Saved",JOptionPane.YES_NO_OPTION);
            if(p==0){    
        try {
            String sql = "insert into OPDSerial (Serial_No,Registation_Date,Patient_Name,Age,Gender,Blood_Group,Contact_No,Address,Doctor_Name,Amount,Entered_By,OPD_ID) values (?,?,?,?,?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_serial_no.getText());
            pst.setString(2, txt_registation_date.getText());
            pst.setString(3, txt_patient_name.getText());
            pst.setString(4, txt_age.getText());
            pst.setString(5, cmb_gender.getSelectedItem().toString());
            pst.setString(6, cmb_blood_group.getSelectedItem().toString());
            pst.setString(7, txt_contact_no.getText());
            pst.setString(8, txt_address.getText());          
            pst.setString(9, cmb_doctor_name.getSelectedItem().toString());
            pst.setString(10, txt_total_amount.getText());  
            pst.setString(11, loginby);
            pst.setString(12, txt_opdid.getText());             
            pst.execute();
            MainOPDTable();
            Update_OPDSerial_table();
            AutoSerialNo();
            AutoOPDID();
            ClearTextField();
            discount();
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
            }
        }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(SQLException e){}
        }
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        //--delete blood donor info from database
        if("".equals(txt_opdid.getText())||"".equals(txt_patient_name.getText())){
        JOptionPane.showMessageDialog(null, "Please Enter OPD_ID And Patient_Name");
        }else{
        int p = JOptionPane.showConfirmDialog(null, "Do You Want To Delete", "Delete", JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            try {
                String sql = "delete from OPDSerial where OPD_ID=? and Patient_Name=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, txt_opdid.getText());
                pst.setString(2, txt_patient_name.getText());
                pst.execute();
                ClearTextField();
                AutoSerialNo();
                AutoOPDID();                
                Update_OPDSerial_table();
//                JOptionPane.showMessageDialog(null, "Data Deleted");

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
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        ClearTextField();
        AutoSerialNo();
        AutoOPDID();
        discount();
        Update_OPDSerial_table();
        
    }//GEN-LAST:event_btn_clearActionPerformed

    private void tbl_opd_listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_opd_listMouseClicked
        try {
        int row=tbl_opd_list.getSelectedRow();
        String tableClick=(tbl_opd_list.getModel().getValueAt(row, 0).toString());
        String sql="select * from OPDSerial where OPD_ID ='"+tableClick+"'";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        if(rs.next()){
        txt_serial_no.setText(rs.getString("Serial_No"));
        txt_registation_date.setText(rs.getString("Registation_Date"));
        txt_patient_name.setText(rs.getString("Patient_Name"));
        txt_age.setText(rs.getString("Age"));
        cmb_gender.setSelectedItem(rs.getString("Gender"));
        cmb_blood_group.setSelectedItem(rs.getString("Blood_Group"));        
        txt_contact_no.setText(rs.getString("Contact_No"));
        txt_address.setText(rs.getString("Address"));
        cmb_doctor_name.setSelectedItem(rs.getString("Doctor_Name"));
        txt_total_amount.setText(rs.getString("Amount"));
        lbl_entered_by.setText(rs.getString("Entered_By"));        
        
        }
        } catch (SQLException e) {
//                JOptionPane.showMessageDialog(null, e);
            } finally {
            try {
                rs.close();
                pst.close();
            } catch (SQLException e) {}
            }
    }//GEN-LAST:event_tbl_opd_listMouseClicked

    private void cmb_doctor_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmb_doctor_nameMouseClicked

    }//GEN-LAST:event_cmb_doctor_nameMouseClicked

    private void cmb_doctor_nameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmb_doctor_nameMousePressed

    }//GEN-LAST:event_cmb_doctor_nameMousePressed

    private void cmb_doctor_nameMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmb_doctor_nameMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_doctor_nameMouseMoved

    private void cmb_doctor_nameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmb_doctor_nameMouseExited

    }//GEN-LAST:event_cmb_doctor_nameMouseExited

    private void cmb_doctor_nameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmb_doctor_nameMouseEntered

    }//GEN-LAST:event_cmb_doctor_nameMouseEntered

    private void cmb_doctor_nameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_doctor_nameItemStateChanged
        AutoSerialNo();
        DoctorDetailsSearch();
        discount();
    }//GEN-LAST:event_cmb_doctor_nameItemStateChanged

    private void tbl_opd_listKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_opd_listKeyReleased
         try{
        int row=tbl_opd_list.getSelectedRow();
        String tableClick=(tbl_opd_list.getModel().getValueAt(row, 0).toString());
        String sql="select * from OPDSerial where OPD_ID ='"+tableClick+"'";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        if(rs.next()){
        txt_serial_no.setText(rs.getString("Serial_No"));
        txt_registation_date.setText(rs.getString("Registation_Date"));
        txt_patient_name.setText(rs.getString("Patient_Name"));
        txt_age.setText(rs.getString("Age"));
        cmb_gender.setSelectedItem(rs.getString("Gender"));
        cmb_blood_group.setSelectedItem(rs.getString("Blood_Group"));
        cmb_doctor_name.setSelectedItem(rs.getString("Doctor_Name"));
        txt_visit_amount.setText(rs.getString("Amount"));        
        lbl_entered_by.setText(rs.getString("Entered_By"));
        txt_contact_no.setText(rs.getString("Contact_No"));
        txt_address.setText(rs.getString("Address"));
        txt_opdid.setText(rs.getString("OPD_ID"));
        }
        }
        catch(SQLException e){
//        JOptionPane.showMessageDialog(null,e);
        }finally{
            try{
            rs.close();
            pst.close();
            }catch(SQLException e){}
        }
    }//GEN-LAST:event_tbl_opd_listKeyReleased

    private void txt_discountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_discountKeyReleased
        discount();
    }//GEN-LAST:event_txt_discountKeyReleased

    private void txt_visit_amountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_visit_amountKeyReleased
        discount();
    }//GEN-LAST:event_txt_visit_amountKeyReleased

    private void txt_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_searchMouseClicked
        txt_search.setText("");
    }//GEN-LAST:event_txt_searchMouseClicked

    private void txt_visit_amountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_visit_amountKeyPressed
        
    }//GEN-LAST:event_txt_visit_amountKeyPressed

    private void txt_discountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_discountKeyPressed
        
    }//GEN-LAST:event_txt_discountKeyPressed

    private void searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseClicked
        search.setText("");
    }//GEN-LAST:event_searchMouseClicked

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        try {
        String sql = "select * from OPDSerial where Contact_No like '%" +search.getText() + "%' order by Registation_Date asc";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        tbl_opd_list.setModel(DbUtils.resultSetToTableModel(rs));                                              
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }finally {
            try {
                rs.close();
                pst.close();
            }catch (SQLException e) {}
        }
    }//GEN-LAST:event_searchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JButton btn_save;
    private javax.swing.JComboBox<String> cmb_blood_group;
    private javax.swing.JComboBox<String> cmb_doctor_name;
    private javax.swing.JComboBox<String> cmb_gender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lbl_doctor_name;
    private javax.swing.JLabel lbl_entered_by;
    private javax.swing.JTextField search;
    private javax.swing.JTable tbl_opd_list;
    private javax.swing.JTextArea txt_address;
    private javax.swing.JTextField txt_age;
    private javax.swing.JTextField txt_contact_no;
    private javax.swing.JTextField txt_discount;
    private javax.swing.JTextArea txt_doctor_details;
    private javax.swing.JTextField txt_opdid;
    private javax.swing.JTextField txt_patient_name;
    private javax.swing.JTextField txt_registation_date;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_serial_no;
    private javax.swing.JTextField txt_total_amount;
    private javax.swing.JTextField txt_visit_amount;
    // End of variables declaration//GEN-END:variables
}
