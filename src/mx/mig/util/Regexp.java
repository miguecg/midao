/*
 * Autor: Miguel Angel Cedeño Garcidueñas
 * email: miguelcedega@correo.fie.umich.mx
 * 
 * 
 * Expresiones regulares para de alguna forma validar 
 * los parametros dados.
 * 
 * 
 */
package mx.mig.util;

/**
 *
 * @author miguel
 */
public class Regexp {
    
    /**
     * Revisa si es numero
     * 
     * @param cadena
     * @return 
     */
    public static boolean esNumero(String cadena) {                
        return (cadena.matches("\\d+") ? true : false);        
    }
    
    /**
     * Revisa si son letras
     * @param cadena
     * @return 
     */
    public static boolean esLetra(String cadena) {
        return (cadena.matches("\\w+") && cadena.matches("\\D+") ? true : false);
    }
    /**
     * Revisa la estructura del correo electronico
     * @param cadena
     * @return 
     */
    public static boolean esEmail(String cadena) {
        return (cadena.matches("(\\w+[.\\-_]?\\d*[.\\-_]?\\w+)*@\\w+([.\\-_]?\\w+)*([.]\\w{2,3})+$") ? true : false);
    }
    
    /**
     * Revisa si son alfanumericos
     * @param cadena
     * @return 
     */
    public static boolean esAlfaNumerico(String cadena) {
        return (cadena.matches("\\w*|\\d*") ? true : false);
    }
    /**
     * Revisa si es una matricula (UMSNH solamente)
     * @param v_matricula
     * @return 
     */
    public static boolean esMatricula (String v_matricula) {
       boolean es_mat = false;
       
        if (v_matricula.matches("\\d{7}+\\w{1}+") || v_matricula.matches("\\d{8}+") || v_matricula.matches("INC\\d{5}+") || v_matricula.matches("EN\\d{4}+") || v_matricula.matches("\\d{6}+")) {
            es_mat = true;
        }
      return es_mat; 
    }
    
    /**
     * Revisa si se parece a la estructura de una CURP
     * @param v_curp
     * @return 
     */
    public static boolean esCurp (String v_curp) {
       boolean c_valido = false;
       String c_curp = v_curp.toUpperCase ().trim ();
       
       if (c_curp.length() == 18) {
           
           if (c_curp.matches ("\\w{4}\\d{6}\\w{5}\\w{1}\\d{2}")) {               
               c_valido = true;
           } else {
               c_valido = false;
           }
           
       } else {
           c_valido = false;
       }
       
     return c_valido;  
   }
    
}
