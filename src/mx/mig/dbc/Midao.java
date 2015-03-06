/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.mig.dbc;

/**
 *
 * @author miguel
 */
import java.io.IOException;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Midao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        Dao dao = new Dao();
        ResultSet rst = dao.consultar("Select marc_marca,marc_descrip From Marca");
        
        while (rst != null && rst.next()) {
            System.out.println(rst.getInt("marc_marca")+" "+rst.getString("marc_descrip"));
        }
        
        dao.desconectar();
    }
}
