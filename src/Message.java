
public class Message extends AESMessage {
  
  private String messageString;
  private String encryptID;
  private boolean isEncrypted;
  
  public Message(String messageString, String encryptID, boolean isEncrypted) {
    this.messageString = messageString;
    this.encryptID = encryptID;
    this.isEncrypted = isEncrypted;
  }
  public Message() {
    // TODO Auto-generated constructor stub
  }
  
  public void setMessage(String inputMessageString) {
    this.messageString = inputMessageString;
  }
  public void setEncryptID(String ID) {
    this.encryptID = ID;
  }
  public String getMessage() {
    return messageString;
  }
  public String getID() {
    return encryptID;
  }
  public boolean isEncrytped() {
    return isEncrypted;
  }
  public void setEncryption(boolean set) {
    isEncrypted = set;
  }
  
  public String encodeMessage(String message, String ID) {
    this.messageString = AESMessage.encrypt(message, ID);
    isEncrypted = true;
    return this.messageString;
  }
  public void decodeMessage() {
    int deCipher = 26 - Integer.parseInt(encryptID);
    if (isEncrypted == true) {
      this.messageString = CipherMessage.encrypt(messageString.toString(), deCipher);
    }
    else {
      System.out.println("message not encoded");
    }
  }@Override
  public String toString() {
    return encryptID + ":" + messageString;
    
  }
  
  public static void main(String[] args) {
    Message first = new Message();
    first.setEncryptID("3");
    //String myMessage = new StringBuffer("hello there");
    first.setMessage(myMessage);
    first.encodeMessage(first.messageString.toString(), first.getID());
    System.out.println(first.messageString);
    first.encodeMessage(first.messageString.toString(), "23");
    System.out.println(first.messageString.toString());

    
    
  }
 
 
} // end Message Class
