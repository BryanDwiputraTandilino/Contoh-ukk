/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kasir;

/**
 *
 * @author PC
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class koneksi {
    private static Connection koneksi;
    
    public static Connection getConnection(){
    if(koneksi == null);
        try {
            String url="jdbc:mysql://localhost:3306/kasir";
            String user="root";
            String pass="";
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            koneksi = DriverManager.getConnection(url,user,pass);
            System.out.println("Berhasil coy");
        } catch (Exception e) {
            System.out.println("Gagal");
        }
    return koneksi;
    }
    public static void main(String args[]){
    getConnection();
}

}
