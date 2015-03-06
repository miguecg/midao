/*
 * Autor: Miguel Angel Cedeno Garciduenas
 * miguecg@gmail.com
 * 
 */

package mx.mig.dbc;

/**
 *
 * @author miguel
 */
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;



public class Dao {

    private Properties props = new Properties();
    private static final String RUTA = "conn.properties";
    private Connection conn;
    private Statement stm;
    private ResultSet rst;
    
    public Dao() throws IOException,SQLException {
        this.cargarPropiedades();
        this.conectar();        
    }
    
    
    public Dao(String ruta) throws IOException,SQLException {
        this.cargarPropiedades(ruta);
        this.conectar();
    }
    
    private void conectar() throws SQLException {
        
       DriverManager.registerDriver(new com.mysql.jdbc.Driver());
       conn = DriverManager.getConnection("jdbc:mysql://"+props.getProperty("host")+":"+props.getProperty("puerto")+"/"+props.getProperty("dbname")+"?user="+props.getProperty("usuario")+"&password="+props.getProperty("password"));
        
    }
    
    private void cargarPropiedades() throws IOException {
        FileInputStream in = new FileInputStream(new File(RUTA));
        props.load(in);       
    }
    
    private  void cargarPropiedades(String ruta) throws IOException {
        FileInputStream in = new FileInputStream(new File(ruta));
        props.load(in);
    }
    

    public ResultSet consultar(String sql) throws SQLException {
        stm = conn.createStatement();
        rst = stm.executeQuery(sql);
     return rst;   
    }
    
    public int insertar(String sql) throws SQLException {
        stm = conn.createStatement();
     return stm.executeUpdate(sql);     
    }
    
    public int actualizar(String sql) throws SQLException {
        return this.insertar(sql);
    }
    
    public int borrar(String sql) throws SQLException {
        return this.insertar(sql);
    }
    
    
    public void desconectar(){
        try {
            if (rst != null) rst.close();
            stm.close();
            conn.close();
        } catch (SQLException e) {
            
        }
    }
    
}
