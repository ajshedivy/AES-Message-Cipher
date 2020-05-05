
public class Cipher {

  // Encrypts text using a shift od s
  public static String encrypt(String text, int s) {
    StringBuffer result = new StringBuffer();

    for (int i = 0; i < text.length(); i++) {
      if (Character.isUpperCase(text.charAt(i))) {
        char ch = (char) (((int) text.charAt(i) + s - 65) % 26 + 65);
        result.append(ch);
      } 
      else if (text.charAt(i) == -1) {
        result.append(i);
      }
        char ch = (char) (((int) text.charAt(i) + s - 97) % 26 + 97);
        result.append(ch);
      }
    return result;
    }
  

  public static void main(String[] args) {
    String textString = "abcdefghijklmnopqrstuvwxyz";
    int shift = 3;
    System.out.println(encrypt(textString, shift));
    System.out.println(textString + "->" + encrypt(textString, shift));

  }

}
