package hospitalERP;


import static hospitalERP.LocalVariable.logintime;
import static hospitalERP.Login.division;
import static hospitalERP.Login.loginby;
import java.awt.Toolkit;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aashiq
 */
public class HomePage extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    static String date;
    static String time;
    public HomePage() {
        initComponents();
        conn= DatabaseConnection.db_connect();
        CurrentDateTime();
        DisplaySizeFrame();
        
    }
    
    
    ///----display size 
    final void DisplaySizeFrame(){
       Toolkit tk= Toolkit.getDefaultToolkit();
            int xsize=(int) tk.getScreenSize().getWidth();
            int ysize=(int) tk.getScreenSize().getHeight();
            int y= ysize-35;
            int x= xsize+12;
            this.setSize(x,y); 
    }

    
///---Date and time show-----
    public final void CurrentDateTime(){
     
        Thread clock=new Thread(){
        public void run(){
        for(;;){
         Calendar cal=new GregorianCalendar();
        int month=cal.get(Calendar.MONTH);
        int year=cal.get(Calendar.YEAR);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        lbl_date.setText((year+"-"+(month+1)+"-"+day));
        
        date=(year+"-"+(month+1)+"-"+day);//use for check in and out 
        
        int second=cal.get(Calendar.SECOND);
        int minute=cal.get(Calendar.MINUTE);
        int hour=cal.get(Calendar.HOUR);
        lbl_time.setText((hour+":"+minute+":"+second));
        
        time=(hour+":"+minute+":"+second);//use for check in and out
        
        try {
            sleep(1000);
        }catch (InterruptedException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
        }
        };
        clock.start();       
    }


    ///-----log out 
      public void Logout(){
     try{
        //--audite table update.... 
        String sql = "update Audite set Logout_Date='"+lbl_date.getText()+"',"         
        + "Logout_Time='"+lbl_time.getText()+"'"
        + "where User_Name='"+loginby+"'"
        + " and Login_Time='"+logintime+"'" ;
        pst = conn.prepareStatement(sql);                   
        pst.execute();
        this.dispose();
        new Login().setVisible(true);
            //        JOptionPane.showMessageDialog(null,"Upadate");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
        finally {
        try {
            rs.close();
            pst.close();
        }catch (SQLException e) {}
        }
     }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        user_reg = new javax.swing.JMenuItem();
        mnu_room_add = new javax.swing.JMenuItem();
        mnu_logout = new javax.swing.JMenuItem();
        exit = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        login_list = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        mnu_emp_attandence_list = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        mnu_ipd_reg = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        mnu_blood_donor_reg = new javax.swing.JMenuItem();
        lbl_date = new javax.swing.JMenu();
        lbl_time = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jDesktopPane1.setMinimumSize(new java.awt.Dimension(1000, 500));

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1271, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 572, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jScrollPane1.setViewportView(jPanel1);

        jMenu1.setText("File");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem7.setText("CheckInOut");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        user_reg.setText("User Registation");
        user_reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_regActionPerformed(evt);
            }
        });
        jMenu1.add(user_reg);

        mnu_room_add.setText("Add New Room ");
        mnu_room_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_room_addActionPerformed(evt);
            }
        });
        jMenu1.add(mnu_room_add);

        mnu_logout.setText("Log Out");
        mnu_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_logoutActionPerformed(evt);
            }
        });
        jMenu1.add(mnu_logout);

        exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        jMenu1.add(exit);

        jMenuBar1.add(jMenu1);

        jMenu5.setText("View");

        login_list.setText("Login List");
        login_list.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login_listActionPerformed(evt);
            }
        });
        jMenu5.add(login_list);

        jMenuBar1.add(jMenu5);

        jMenu4.setText("Employee");

        jMenuItem6.setText("Employee Registation");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        mnu_emp_attandence_list.setText("Employee Attandence List");
        mnu_emp_attandence_list.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_emp_attandence_listActionPerformed(evt);
            }
        });
        jMenu4.add(mnu_emp_attandence_list);

        jMenuBar1.add(jMenu4);

        jMenu3.setText("Doctor");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        jMenuItem5.setText("Patient Visite");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuBar1.add(jMenu3);

        jMenu6.setText("OPD");

        jMenuItem1.setText("OPD Registation ");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem1);

        jMenuItem9.setText("OPD List");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem9);

        jMenuBar1.add(jMenu6);

        jMenu7.setText("IPD");

        mnu_ipd_reg.setText("IPD Registation & Addmitted");
        mnu_ipd_reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_ipd_regActionPerformed(evt);
            }
        });
        jMenu7.add(mnu_ipd_reg);

        jMenuBar1.add(jMenu7);

        jMenu8.setText("Blood Donor");

        mnu_blood_donor_reg.setText("Blood Donor Registation");
        mnu_blood_donor_reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_blood_donor_regActionPerformed(evt);
            }
        });
        jMenu8.add(mnu_blood_donor_reg);

        jMenuBar1.add(jMenu8);

        lbl_date.setText("date");
        jMenuBar1.add(lbl_date);

        lbl_time.setText("time");
        jMenuBar1.add(lbl_time);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        OPDSerial opd=new OPDSerial();
        jDesktopPane1.add(opd);
        opd.show();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed

    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked

    }//GEN-LAST:event_jMenu1MouseClicked

    private void user_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_regActionPerformed
        if("Admin".equals(division)){
        UserRegistation lg=new UserRegistation();
        jDesktopPane1.add(lg);
        lg.show();    
        }
        else{
            JOptionPane.showMessageDialog(null, "Only access for Admin");
        }
    }//GEN-LAST:event_user_regActionPerformed

    private void mnu_blood_donor_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_blood_donor_regActionPerformed
        BloodDonorRegistation lg=new BloodDonorRegistation();
        jDesktopPane1.add(lg);
        lg.show();
    }//GEN-LAST:event_mnu_blood_donor_regActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        this.dispose();
    }//GEN-LAST:event_exitActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
       if("Doctor".equals(division) || "Admin".equals(division)){
        OPDList ooo=new OPDList();
        jDesktopPane1.add(ooo);
        ooo.show();   
       }
       else{
           JOptionPane.showMessageDialog(null, "Only access for Doctor and Admin");
       }
        
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        EmployeeRegistation er=new EmployeeRegistation();
        jDesktopPane1.add(er);
        er.show();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        ChackInOut io=new ChackInOut();
        jDesktopPane1.add(io);
        io.show();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void mnu_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_logoutActionPerformed
        Logout();        
    }//GEN-LAST:event_mnu_logoutActionPerformed

    private void login_listActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login_listActionPerformed
        Audite a=new Audite();
        jDesktopPane1.add(a);
        a.show();
    }//GEN-LAST:event_login_listActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        AlllOPD_List p=new AlllOPD_List();
        jDesktopPane1.add(p);
        p.show();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void mnu_emp_attandence_listActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_emp_attandence_listActionPerformed
       EmployeeAttandenceList a=new EmployeeAttandenceList();
        jDesktopPane1.add(a);
        a.show();   
    }//GEN-LAST:event_mnu_emp_attandence_listActionPerformed

    private void mnu_ipd_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_ipd_regActionPerformed
      IPDForm a=new IPDForm();
        jDesktopPane1.add(a);
        a.show();  
    }//GEN-LAST:event_mnu_ipd_regActionPerformed

    private void mnu_room_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_room_addActionPerformed
        RoomAdd a=new RoomAdd();
        jDesktopPane1.add(a);
        a.show();
    }//GEN-LAST:event_mnu_room_addActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem exit;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu lbl_date;
    private javax.swing.JMenu lbl_time;
    private javax.swing.JMenuItem login_list;
    private javax.swing.JMenuItem mnu_blood_donor_reg;
    private javax.swing.JMenuItem mnu_emp_attandence_list;
    private javax.swing.JMenuItem mnu_ipd_reg;
    private javax.swing.JMenuItem mnu_logout;
    private javax.swing.JMenuItem mnu_room_add;
    private javax.swing.JMenuItem user_reg;
    // End of variables declaration//GEN-END:variables
}
