import java.util.ArrayList;

public class Debug {

  public static void main(String[] args) {
    ArrayList<AESMessage> sList = new ArrayList<AESMessage>();
    AESMessage message = new AESMessage();
    AESMessage message2 = new AESMessage();
    AESMessage message3 = new AESMessage();
    message.setMessageString("hello");
    message.setKey("my key");
    message2.setMessageString("message 2");
    message2.setKey("key");
    message3.setMessageString("message 3");
    message3.setKey("3 key");
    sList.add(message);
    sList.add(message2);
    sList.add(message3);
    System.out.println(sList.toString());
    

  }

}
