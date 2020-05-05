/********************************************
 * File: AESMessage.java 
 * Author: Adam Shedivy 
 * Date: 2/1/2020 
 * Course: CS 400 
 * Compiler: javac.exe
 * Platform: Windows 10
 *******************************************/
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * AESMessage object represents an String message that has a secret key associated with it. The
 * AESMessage can either represent an encrypted or decrypted message.
 * 
 * This class uses the AES encryption algorithm framework. Because AES is a 'Symmetric' block
 * algorithm, the key allows for both encryption and decryption
 * 
 * @author ajshe
 *
 */
public class AESMessage {

  private static SecretKeySpec secretKey;
  private static byte[] key;
  private String messageString; // message to encode / decode
  private String keyString; // key to encode / decode message
  private boolean isEncrypted;

  /**
   * @return - secret key used for encryption
   */
  public String getKey() {
    return keyString;
  }
  
  public SecretKeySpec getKeySpec() {
    return secretKey;
  }

  /**
   * @return - true is message is encrypted, else false
   */
  public boolean isEncryped() {
    return this.isEncrypted;
  }

  /**
   * set isEncrypted
   * 
   * @param answer - true or false
   * @return = true or false
   */
  public boolean setEncryted(boolean answer) {
    return this.isEncrypted = answer;

  }

  @Override
  public String toString() {
    return this.keyString + ":" + this.messageString;

  }

  /**
   * set secret key used for encryption / decryption
   * 
   * @param myKey - string key
   */
  public void setKey(String myKey) {
    MessageDigest sha = null;
    try {
      keyString = myKey;
      key = myKey.getBytes("UTF-8"); // convert key to bytes

      // use Secure Hash Algorithm to generate hash of data
      sha = MessageDigest.getInstance("SHA-1");
      key = sha.digest(key);
      key = Arrays.copyOf(key, 16);

      // construct key from given byte array
      secretKey = new SecretKeySpec(key, "AES");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  /**
   * encryption algorithm using AES
   * 
   * @param strToEncrypt - message to encrypt
   * @param secret       - secret code used in encryption
   * @return
   */
  public String encrypt(String strToEncrypt, String secret) {
    try {
      // set secret key to input String
      setKey(secret);

      // create Cipher object for encryption
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

      // set encrypt mode
      cipher.init(Cipher.ENCRYPT_MODE, secretKey);
      messageString =
          Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
      setMessageString(messageString); // set message string to encrypted message
      setEncryted(true); // set isEncrypted to true
      return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));

    } catch (Exception e) {
      System.out.println("Error while encrypting: " + e.toString());
    }

    return messageString;
  } // end encrypt

  /**
   * decryption algorithm
   * 
   * @param strToDecrypt - encrypted message to decrypt
   * @param secret       - secret key used to encrypt messege
   * @return
   */
  public String decrypt(String strToDecrypt, String secret) {
    try {
      // create secret key
      setKey(secret);
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");

      // use decrypt mode now
      cipher.init(Cipher.DECRYPT_MODE, secretKey);
      messageString = new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
      setEncryted(false); // message is now decrypted
      setMessageString(messageString); // set message to decrypted message
      return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    } catch (Exception e) {
      System.out.println("Error while decrypting: " + e.toString());
    }
    setEncryted(false);
    return messageString;
  } // end decrypt

  /**
   * getter method for message
   * 
   * @return - message (no matter in encrypted or not)
   */
  public String getMessageString() {
    return messageString;
  }

  /**
   * set message
   * 
   * @param message - secret message
   */
  public void setMessageString(String message) {
    this.messageString = message;
    this.isEncrypted = false;
  }
  
//  @Override
//  public int compareTo( message) {
//
//  }

} // end AESMessage
