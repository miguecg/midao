/*
 * CifraBean.java
 *
 * Created on 4 de enero de 2005, 10:32 AM
 *
 *
 *
 *
 *
 *
 *---------------------------------------------------
 * Clase tipo bean para cifrar contrasenias y texto
 * Solo se implementa aqui el MD5 y MacMD5 para
 * crear hash de password y hash para verificar
 * autenticidad
 *---------------------------------------------------
 *
 *
 * Autor: Miguel Angel Cedeño Garcidueñas
 * email: miguelcedega@correo.fie.umich.mx
 *
 *
 *
 *
 *
 *
 *M16U3
 */

package mx.mig.util;

/**
 * CifraBean. Bean para cifrar contrase�as en formato MD5, adem�s proporciona un hash aleatorio en este
 * algoritmo.
 */

/**
 * @author miguel
 */
import java.io.Serializable;
import javax.crypto.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CifraBean implements Serializable {
  
  private static String c_palabra;
  private static String Hash_MD5;
  private static String Clave_MD5;
  private static final String Semillita = "&%sdsa$(feewk))dsdsadw345=(&2s/$wsmrm654ff655sdasqw2493%";
  private static byte[] Semilla = Semillita.getBytes ();
   
    public static String hashMD5 (String palabra) {
        c_palabra = palabra;           
        try {
                      
            char[] chararray = c_palabra.toCharArray();
            Hash_MD5 = hashPassword(chararray);
            
        } catch (Exception ex) {
            ex.printStackTrace ();
        }
     return Hash_MD5;   
    }
    
    
    /**
     * 
     * Crear hash aleatorio.
     */
    
    public static String hashMD5 () {
        try {
            
          KeyGenerator kg = KeyGenerator.getInstance ("HmacMD5");
          SecretKey sk = kg.generateKey ();
          Mac mac = Mac.getInstance ("HmacMD5");
          mac.init (sk);
          byte[] result = mac.doFinal (Semilla);
          
          Clave_MD5 = convert(result);
          
        } catch (Exception ex) {
            ex.printStackTrace ();
        }
      return Clave_MD5;  
    }
    
    
     private static String hashPassword(char[] pwd) throws NoSuchAlgorithmException {

        String algo = "MD5";
        MessageDigest md = MessageDigest.getInstance(algo);
        md.reset();

        byte[] pwdb = new byte[pwd.length];
        byte[] crypt = null;
        for (int b = 0; b < pwd.length; b++) {
            pwdb[b] = (byte) pwd[b];
        }
        crypt = md.digest(pwdb);
        smudge(pwdb);
      //  return new String(Base64.encode(crypt));
        return (convert(crypt));
    }

  /*------------------------------------------------------------------------*/  
    /**
     * El asunto que aqui meto este metodo
     * para convertir de bytes a string.
     *
     */
    
    private static String convert(byte bytes[]) {

        StringBuffer sb = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            sb.append(convertDigit((int) (bytes[i] >> 4)));
            sb.append(convertDigit((int) (bytes[i] & 0x0f)));
        }
        return (sb.toString());

    }
     
    /**
     * Y este metodo que convierte a decimales los
     * hexadecimales.
     */
     
     private static char convertDigit(int value) {

        value &= 0x0f;
        if (value >= 10) {
            return ((char) (value - 10 + 'a'));
        } else {
            return ((char) (value + '0'));
        }    

     }
     
     private static void smudge(char[] pwd) {
        if (pwd != null) {
            for (int b = 0; b < pwd.length; b++) {
                pwd[b] = 0;
            }
        }
    }

    /**
     * Zero the contents of the specified array.
     * @param pwd the array to zero
     */
    private static void smudge(byte[] pwd) {
        if (pwd != null) {
            for (int b = 0; b < pwd.length; b++) {
                pwd[b] = 0;
            }
        }
    }
    
   
    
}
