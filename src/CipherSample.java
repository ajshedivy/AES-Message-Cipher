
import java.security.Key;
 
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
 
public class CipherSample {
    public static void main(String[] args){
        try{
            byte[] plainBytes = "HELLO Adam How are you".getBytes();
             
            // Generate the key first
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);  // Key size
            Key key = keyGen.generateKey();
             
            // Create Cipher instance and initialize it to encrytion mode
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  // Transformation of the algorithm
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] cipherBytes = cipher.doFinal(plainBytes);
            System.out.println( new String(cipherBytes));
             
            // Reinitialize the Cipher to decryption mode
            cipher.init(Cipher.DECRYPT_MODE,key, cipher.getParameters());
            byte[] plainBytesDecrypted = cipher.doFinal(cipherBytes);
             
            System.out.println("DECRUPTED DATA : "+new String(plainBytesDecrypted));    
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
