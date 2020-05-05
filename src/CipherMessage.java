
public class CipherMessage{
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static String encrypt(String plainText, int shiftKey) {
        plainText = plainText.toLowerCase();
        String cipherText = "";
        for (int i = 0; i < plainText.length(); i++) {
       // introduce a local variable, you don't want to perform charAt twice
          char c = plainText.charAt(i);
          int charPosition1 = ALPHABET.indexOf(c);
          // if charPositions is -1 then it is not found 
          if (charPosition1 == -1) { // or define a constant NOT_FOUND = -1
              cipherText += c;
              // continue with the for loop
              continue;
          }
            int charPosition = ALPHABET.indexOf(plainText.charAt(i));
            int keyVal = (shiftKey + charPosition) % 26;
            char replaceVal = ALPHABET.charAt(keyVal);
            cipherText += replaceVal;
        }
        return cipherText;
    }
    public static void main(String[] args) {
      System.out.println(encrypt("olssv hkht", 19));
    }
    
    

  
}


