/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kasir;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import kasir.koneksi;
public class tambah_pelanggan extends javax.swing.JFrame {
private Connection conn = koneksi.getConnection();
private DefaultTableModel tabmodel;
 
private void autoNumber(){
    String noBrg = "PLGN000";
    int i = 0;
    try {
        Statement st = conn.createStatement();
        String sql = "SELECT PelangganID FROM `pelanggan`";
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            noBrg = rs.getString("PelangganID");
           }
            noBrg = noBrg.substring(4);
            i = Integer.parseInt(noBrg)+1;
            noBrg = "00" +i;
            noBrg = "PLGN"+ noBrg.substring(noBrg.length()-3);
            pelangganid.setText(noBrg);
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}

    public tambah_pelanggan() {
        initComponents();
        kosong();
        aktif();
        datatable();
        autoNumber();
    }
protected void aktif (){
        pelangganid.requestFocus();
    }
    protected void kosong(){
        pelangganid.setText("");
        namapelanggan.setText("");
        alamat.setText("");
        nomortelpone.setText("");
    }
     protected void datatable(){
    Object[] Baris ={"PelangganID","NamaPelanggan","Alamat","Nomor Telepon"};
        tabmodel = new DefaultTableModel (null, Baris);
        String cariitem=txtcari.getText();
        
        try {
            String sql = "SELECT * FROM pelanggan where PelangganID like '%"+cariitem+"%' or NamaPelanggan like '%"+cariitem+"%' order by PelangganID asc";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                tabmodel.addRow(new Object[]{
                hasil.getString(1),
                hasil.getString(2),
                hasil.getString(3),
                hasil.getString(4)

            });
            }
            tblplg.setModel(tabmodel);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "data gagal dipanggil"+e);
            
        }
        
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pelangganid = new javax.swing.JTextField();
        namapelanggan = new javax.swing.JTextField();
        alamat = new javax.swing.JTextField();
        nomortelpone = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblplg = new javax.swing.JTable();
        txtcari = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("PelangganID");

        jLabel2.setText("Nama Pelanggan");

        jLabel3.setText("Alamat");

        jLabel4.setText("Nomor Telpone");

        tblplg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "PelangganID", "Nama Pelanggan", "Alamat", "Nomor Telpone"
            }
        ));
        tblplg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblplgMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblplg);

        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcariKeyPressed(evt);
            }
        });

        jButton1.setText("cari");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("hapus");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("edit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("batal");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("simpan");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nomortelpone, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(alamat)
                            .addComponent(namapelanggan)
                            .addComponent(pelangganid)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton5)
                        .addGap(28, 28, 28)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(pelangganid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(namapelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(alamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(nomortelpone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton3)
                        .addComponent(jButton4))
                    .addComponent(jButton5))
                .addContainerGap(126, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
String sql = "insert into pelanggan values (?,?,?,?)";
      try{
          PreparedStatement stat = conn.prepareStatement(sql);
          stat.setString(1, pelangganid.getText());
          stat.setString(2, namapelanggan.getText());
          stat.setString(3, alamat.getText());
          stat.setString(4, nomortelpone.getText());

          
          stat.executeUpdate();
          JOptionPane.showMessageDialog(null, "data berhasil disimpan");
          kosong();
          pelangganid.requestFocus();
      }
      catch (SQLException e){
          JOptionPane.showMessageDialog(null, "data gagal tersimpan"+e);
      }
      datatable(); 
      autoNumber();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
  int ok = JOptionPane.showConfirmDialog(null, "hapus","Konfirmasi Dialog",JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql = "delete from pelanggan where PelangganID ='"+pelangganid.getText()+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "data berhasil dihapus");
                kosong();
                pelangganid.requestFocus();
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog(null, "data gagal dihapus"+e);
            }
            datatable();
        }         // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tblplgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblplgMouseClicked
 int bar = tblplg.getSelectedRow();
       String a = tblplg.getValueAt(bar, 0).toString();
       String b = tblplg.getValueAt(bar, 1).toString();
       String c = tblplg.getValueAt(bar, 2).toString();
       String d = tblplg.getValueAt(bar, 3).toString();

       
       pelangganid.setText(a);
       namapelanggan.setText(b);
       alamat.setText(c);
       nomortelpone.setText(d);        // TODO add your handling code here:
    }//GEN-LAST:event_tblplgMouseClicked

    private void txtcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyPressed
if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           datatable();
}// TODO add your handling code here:
    }//GEN-LAST:event_txtcariKeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
 kosong();
        datatable();         // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 try{
            String sql = "update pelanggan set NamaPelanggan=?, Alamat=?, Nomor_Telepon=? where PelangganID='"+pelangganid.getText()+"'";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, namapelanggan.getText());
            stat.setString(2, alamat.getText());
            stat.setString(3, nomortelpone.getText());


            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "data berhasil diubah");
            kosong();
            pelangganid.requestFocus();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "data gagal diubah"+e);
        }          // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 datatable();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(tambah_pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tambah_pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tambah_pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tambah_pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tambah_pelanggan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alamat;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField namapelanggan;
    private javax.swing.JTextField nomortelpone;
    private javax.swing.JTextField pelangganid;
    private javax.swing.JTable tblplg;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}