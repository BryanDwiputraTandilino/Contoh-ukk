/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kasir;

import java.awt.print.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class transaksi extends javax.swing.JFrame {
    private Connection con = koneksi.getConnection();
    private Statement stat;
    private ResultSet rs;
    private String sql;

    String Tanggal;
    private DefaultTableModel model;
    
    public void totalBiaya(){
        int jumlahBaris = jTable1.getRowCount();
        int totalBiaya = 0;
        int jumlahBarang, hargaBarang;
        for (int i = 0; i < jumlahBaris; i++){
            jumlahBarang = Integer.parseInt(jTable1.getValueAt(i, 4).toString());
            hargaBarang = Integer.parseInt(jTable1.getValueAt(i, 5).toString());
            totalBiaya = totalBiaya + (jumlahBarang * hargaBarang);
        }
        txtotalbayar.setText(String.valueOf(totalBiaya));
        txtampil.setText("Rp "+ totalBiaya +",00");
    }
    
    public void autonumber(){
        try {
            Connection c = koneksi.getConnection();
            Statement stat = c.createStatement();
            String sql = "SELECT * FROM detailpenjualan ORDER BY PenjualanID DESC";
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()){
                String NoFaktur = rs.getString("PenjualanID").substring(2);
                String TR = ""+(Integer.parseInt(NoFaktur)+1);
                String Nol = "";
                
                if(TR.length()==1)
                {Nol = "000";}
                else if(TR.length()==2)
                {Nol = "00";}
                else if(TR.length()==3)
                {Nol = "0";}
                else if(TR.length()==4)
                {Nol = "";}
                txnotransaksi.setText("TR" + Nol + TR);
                }else {
                txnotransaksi.setText("TR0001");
            }
            rs.close();
            stat.close();
        } catch (Exception e) {
            System.out.println("autonumber error");
           
        }
    }
    //AUTO NUMBER ID DETAIL
    public void autonumber_iddetail(){
        try {
            Connection c = koneksi.getConnection();
            Statement stat = c.createStatement();
            String sql = "SELECT * FROM detailpenjualan ORDER BY DetailID DESC";
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()){
                String NoFaktur = rs.getString("DetailID").substring(2);
                String TR = ""+(Integer.parseInt(NoFaktur)+1);
                String Nol = "";
                
                if(TR.length()==1)
                {Nol = "000";}
                else if(TR.length()==2)
                {Nol = "00";}
                else if(TR.length()==3)
                {Nol = "0";}
                else if(TR.length()==4)
                {Nol = "";}
                txdetailid.setText("DT" + Nol + TR);
                }else {
                txdetailid.setText("DT0001");
            }
            rs.close();
            stat.close();
        } catch (Exception e) {
            System.out.println("autonumber error");
           
        }
    }
    
//klik langsung produkid,namaproduk,harga
    private void tampil_data(){
        try {
            sql= "SELECT * FROM produk WHERE ProdukID='"+txidbarang.getText()+"'";
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            if(rs.next()){
            }
            txnamabarang.setText(rs.getString("NamaProduk"));
            txharga.setText(rs.getString("Harga"));
            
        } catch (Exception e) {
        }
    }
    
    private void kosongkan_text(){
   // txprodukid.setText("");
    txnamabarang.setText("");
    txharga.setText("0");
    txidbarang.requestFocus();
    }
    
    //klik langsung pelangganid dan namapelanggan
    private void tampil_data_pelanggan(){
        try {
            sql= "SELECT * FROM pelanggan WHERE PelangganID='"+txidcustomer.getText()+"'";
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            if(rs.next()){
            }
            txnamacustomer.setText(rs.getString("NamaPelanggan"));
            
            
        } catch (Exception e) {
        }
    }
    
    private void kosongkan_text_pelanggan(){
   // txprodukid.setText("");
    txnamacustomer.setText("");
    txidcustomer.requestFocus();
    }
    

   
    public void loadData(){
        DefaultTableModel tabmodel = (DefaultTableModel) jTable1.getModel();
        tabmodel.addRow(new Object[]{
            txdetailid.getText(),
            txnotransaksi.getText(),
            txidbarang.getText(),
            txnamabarang.getText(),
            txjumlah.getText(),
            txharga.getText(),
            txtotalbayar.getText()
        });
    }
    
    public void kosong(){
        DefaultTableModel tabmodel = (DefaultTableModel)jTable1.getModel();
        
        while (tabmodel.getRowCount()>0) {
            tabmodel.removeRow(0);
            
        }
    }
    public void bill()
    {
        String total = txtotalbayar.getText();
         String pay = txbayar.getText();
         String bal = txkembalian.getText();
         
          DefaultTableModel model = new DefaultTableModel();
        
        model = (DefaultTableModel)jTable1.getModel();
         
         area.setText(area.getText() + "******************************************************\n");
         area.setText(area.getText() + "                                Kios Rey                        \n");
         area.setText(area.getText() + "*******************************************************\n");
         
         //Heading
          area.setText(area.getText() + "Barang" + "\t" + "Jumlah" + "\t" + "Harga" + "\t" + "Total" + "\n"  );
          
          
          for(int i = 0; i < model.getRowCount(); i++)
          {
              
              String pname = (String)model.getValueAt(i, 3);
              String pjmlh = (String)model.getValueAt(i, 4);
              String price = (String)model.getValueAt(i, 5);
              String amount = (String)model.getValueAt(i, 6); 
              
           area.setText(area.getText() + pname  + "\t" + pjmlh  + "\t" + price + "\t" + amount  + "\n"  );
    
          }
          
          area.setText(area.getText() + "\n");     
          
          area.setText(area.getText() + "\t" + "\t" + "          Subtotal :" + total + "\n");
          area.setText(area.getText() + "\t" + "\t" + "          Tunai :" + pay + "\n");
          area.setText(area.getText() + "\t" + "\t" + "          Kembalian :" + bal + "\n");
          area.setText(area.getText() + "\n");
          area.setText(area.getText() + "*******************************************************\n");
          area.setText(area.getText() + "                      THANK YOU BUDA                   \n");

        
    }
    
    
    
    public void utama(){
            txdetailid.setText("");
            txnotransaksi.setText("");
            txidbarang.setText("");
            txnamabarang.setText("");
            txharga.setText("");
            txjumlah.setText("");
            autonumber();
            autonumber_iddetail();
    }
    
    public void clear(){
            txidcustomer.setText("");
            txnamacustomer.setText("0");
            txtotalbayar.setText("0");
            txbayar.setText("0");
            txkembalian.setText("0");
            txtampil.setText("0");
   }
   public void clear2(){
       txdetailid.setText("");
        txidbarang.setText("");
        txnamabarang.setText("");
        txharga.setText("");
        txjumlah.setText("");
        
    } 
    public void tambahTransaksi(){
        int harga = Integer.parseInt(txharga.getText());
        int jumlah = Integer.parseInt(txjumlah.getText());
        txtotalbayar.setText(String.valueOf(jumlah * harga));
        
        loadData();
        clear2();
        txidbarang.requestFocus();
    }
    
    
    public transaksi() {
        initComponents();
        
      model = new DefaultTableModel();
        
        jTable1.setModel(model);
        model.addColumn("ID Detail");
        model.addColumn("ID Penjualan");
        model.addColumn("ProdukID");
        model.addColumn("Nama Produk");
        model.addColumn("Jumlah");
        model.addColumn("Harga");
        model.addColumn("Total");
           
           utama();
           Date date = new Date();
           SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
           
           txtanggal.setText(s.format(date));
           txtotalbayar.setText("0");
           txbayar.setText("0");
           txkembalian.setText("0");
           txidcustomer.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txnotransaksi = new javax.swing.JTextField();
        txnamacustomer = new javax.swing.JTextField();
        txidcustomer = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txdetailid = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtanggal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txnamabarang = new javax.swing.JTextField();
        txidbarang = new javax.swing.JTextField();
        txharga = new javax.swing.JTextField();
        txjumlah = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        txtampil = new javax.swing.JTextField();
        btntambah = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        area = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtotalbayar = new javax.swing.JTextField();
        txkembalian = new javax.swing.JTextField();
        txbayar = new javax.swing.JTextField();
        btn_cetak_struk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 5, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 82, Short.MAX_VALUE)
        );

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Cambria Math", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Detail ID:");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Cambria Math", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID Pelanggan:");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Cambria Math", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ProdukID:");

        txidcustomer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txidcustomerKeyPressed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Cambria Math", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ID Penjualan:");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Cambria Math", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tanggal:");

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Cambria Math", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nama Pelanggan:");

        txidbarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txidbarangKeyPressed(evt);
            }
        });

        txjumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txjumlahActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Cambria Math", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Jumlah:");

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Cambria Math", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nama Produk:");

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Cambria Math", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Harga:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtampil.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtampil.setText("Rp.0");

        btntambah.setText("Tambah");
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });

        btnhapus.setText("Hapus");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

        area.setColumns(20);
        area.setRows(5);
        jScrollPane2.setViewportView(area);

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Cambria Math", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Total Bayar:");

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Cambria Math", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Bayar:");

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Cambria Math", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Kembalian:");

        txbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txbayarActionPerformed(evt);
            }
        });

        btn_cetak_struk.setText("Cetak Struk");
        btn_cetak_struk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cetak_strukActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(32, 32, 32)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txnamacustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txidcustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txnotransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txdetailid, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButton1)
                        .addGap(12, 12, 12)
                        .addComponent(txtampil, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtotalbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txkembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txidbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txharga, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btntambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnhapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(124, 124, 124))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_cetak_struk)
                        .addGap(193, 193, 193))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txnotransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txdetailid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(txtanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txidcustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txnamacustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txidbarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btntambah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnhapus)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtotalbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2))
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 16, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtampil, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txkembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(43, 43, 43))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cetak_struk)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txjumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txjumlahActionPerformed
tambahTransaksi();        // TODO add your handling code here:
    }//GEN-LAST:event_txjumlahActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
  DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
  int row = jTable1.getSelectedRow();
  model.removeRow(row);
  totalBiaya();
  txbayar.setText("0");
  txkembalian.setText("0");         // TODO add your handling code here:
    }//GEN-LAST:event_btnhapusActionPerformed

    private void txbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txbayarActionPerformed
   int total,bayar,kembalian;
        
        total = Integer.valueOf(txtotalbayar.getText());
        bayar = Integer.valueOf(txbayar.getText());
        
        if (total > bayar){
            JOptionPane.showMessageDialog(null, "Uang tidak cukup untuk melakukan pembayaran");
        } else {
            kembalian = bayar - total;
            txkembalian.setText(String.valueOf(kembalian));
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_txbayarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
       
        String idpenjualan = txnotransaksi.getText();
        String tanggal = txtanggal.getText();
        String idpelanggan = txidcustomer.getText();
        String total = txtotalbayar.getText();
        
        try {
            Connection c = koneksi.getConnection();
            String sql = "INSERT INTO penjualan VALUES (?, ?, ?, ?)";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, idpenjualan);
            p.setString(2, tanggal);
            p.setString(3, idpelanggan);
            p.setString(4, total);
            p.executeUpdate();
            p.close();
            System.out.println("Simpan Penjualan Berhasil");
        } catch (Exception e) {
            System.out.println("simpan penjualan error");
        }
            try {
                Connection c = koneksi.getConnection();
                int baris = jTable1.getRowCount();
                
                for (int i = 0; i < baris; i++) {
                    String sql = "INSERT INTO detailpenjualan(DetailID,PenjualanID, ProdukID,NamaProduk,Jumlah,Harga,Total) VALUES('" + 
                            jTable1.getValueAt(i, 0) +"','"+ jTable1.getValueAt(i, 1) +"','"+ jTable1.getValueAt(i, 2)
                            +"','"+ jTable1.getValueAt(i, 3) +"','"+ jTable1.getValueAt(i, 4)+"','"+ jTable1.getValueAt(i, 5)+"','"+ jTable1.getValueAt(i, 6)+"')";
                    PreparedStatement p = c.prepareStatement(sql);
                    p.executeUpdate();
                    p.close();
                }
                System.out.println("Simpan detailPenjualan Berhasil");
        } catch (Exception e) {
                System.out.println("simpan detailpenjualan error");
        }
            bill();
            clear();
            utama();
            kosong();
            autonumber();
            autonumber_iddetail();
            txtampil.setText("Rp. 0");
                                          
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txidbarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txidbarangKeyPressed
if(evt.getKeyCode()==10 && txidbarang.getText().equalsIgnoreCase("")==false){
            tampil_data();
          
        }else{
            kosongkan_text();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txidbarangKeyPressed

    private void txidcustomerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txidcustomerKeyPressed
 if(evt.getKeyCode()==10 && txidcustomer.getText().equalsIgnoreCase("")==false){
            tampil_data_pelanggan();
          
        }else{
            kosongkan_text_pelanggan();
        }
                // TODO add your handling code here:
    }//GEN-LAST:event_txidcustomerKeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
int bar = jTable1.getSelectedRow();
        String a = jTable1.getValueAt(bar, 0).toString();
        String b = jTable1.getValueAt(bar, 1).toString();
        String c = jTable1.getValueAt(bar, 2).toString();
        String d = jTable1.getValueAt(bar, 3).toString();
        String e = jTable1.getValueAt(bar, 4).toString();
        String f = jTable1.getValueAt(bar, 5).toString();
        String g = jTable1.getValueAt(bar, 6).toString();
        
        txidbarang.setText(c);
        txnamabarang.setText(d);
        txjumlah.setText(e);
        txharga.setText(f);        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void btn_cetak_strukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cetak_strukActionPerformed
 try {
                area.print();
            } catch (PrinterException ex) {
                Logger.getLogger(transaksi.class.getName()).log(Level.SEVERE, null, ex);
            }        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cetak_strukActionPerformed

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
tambahTransaksi();
totalBiaya();        // TODO add your handling code here:
    }//GEN-LAST:event_btntambahActionPerformed

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
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area;
    private javax.swing.JButton btn_cetak_struk;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btntambah;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txbayar;
    private javax.swing.JTextField txdetailid;
    private javax.swing.JTextField txharga;
    private javax.swing.JTextField txidbarang;
    private javax.swing.JTextField txidcustomer;
    private javax.swing.JTextField txjumlah;
    private javax.swing.JTextField txkembalian;
    private javax.swing.JTextField txnamabarang;
    private javax.swing.JTextField txnamacustomer;
    private javax.swing.JTextField txnotransaksi;
    private javax.swing.JTextField txtampil;
    private javax.swing.JTextField txtanggal;
    private javax.swing.JTextField txtotalbayar;
    // End of variables declaration//GEN-END:variables
}
