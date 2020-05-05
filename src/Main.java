//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Java IO Practice
// Files:           Main.java, AESMessage.java
// Course:          CS 400, Spring, 2020
//
// Author:          Adam Shedivy
// Email:           ajshedivy@wisc.edu
// Lecturer's Name: Debra Deppeler
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    N/A
// Partner Email:   N/A
// Partner Lecturer's Name: N/A
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   X   Write-up states that pair programming is allowed for this assignment.
//   X   We have both read and understand the course Pair Programming Policy.
//   X   We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         N/A
// Online Sources:  https://www.comparitech.com/blog/information-security/what-is-aes-encryption/
//                  https://www.tutorialspoint.com/cryptography/advanced_encryption_standard.htm
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/********************************************
 * File:     Main.java
 * Author:   Adam Shedivy
 * Date:     2/1/2020
 * Compiler: javac.exe
 * Platform: Windows 10
 *******************************************/
import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Driver class for encryption program
 * 
 * @author ajshe
 *
 */

public class Main {

  private static final Scanner sc = new Scanner(System.in);
  private final String NAME = "Adam shedivy, ajshedivy@wisc.edu, 001";
  private static String PROMPT_MENU =
      "" + "======== MENU ========\n" + "[1] Create a new Message\n"
          + "[2] Display last Encrypted Message\n" + "[3] Display Encypted/Decrypted Messages\n"
          + "[4] Input file to Encrypt\n" + "[5] Input file to Decrypt\n" + "[6] Export Messages\n"
          + "[7] exit\n" + "> ";
  private static String PROMPT_PROGRAM_DISCRIPTION =
      "###### Welcome to the Message Encrypter/ Decrypter ######\n\n"
          + "This message encoder is based on the 'AES encryption algorithm', or 'Advanced Encryption Standard'.\n"
          + "AES is a fast and secure form of encryption that can keep your data safe. AES is a subset of the\n"
          + "Rijindael block cipher that was developed by two belgian cryptographers. The US government uses this\n"
          + "algorithm for securing sensitve, but unclassified information.\n\n"
          + "The following program demonstrates its simple and efficient use.\n"
          + "To start, select command [1] to encrypt a message\n\n";
  private static String PROMPT_INPUT_MESSAGE = "Enter message to encrypt\n" + "> ";
  private static String PROMPT_SET_KEY =
      "Enter a String of words, characters, or numbers to represent the secret key.\n"
          + " -An example key: secretkeyformessage1\n" + " -Another example: This is my secret key.\n\n"
          + "Enter secret key\n" + "> ";
  private static String PROMPT_SET_MESSAGE_RESPONSE =
      "Success! Message ready to encrypt.\n";
  private static String PROMPT_ENCRYPTED_MESSAGE = "Secret key set! Message encrypted!\n";
  private static String PROMPT_MESSAGES_ADDED_LIST =
      "Message added to Encrypted message collection:\n"
          + " Select command [1] to encrypt another message\n"
          + " Select command [2] to view the encrypted message\n"
          + " Select command [3] to view your collection of encrypted messages\n"
          + " Select command [6] to export encrypted messages to text file\n";
  private static String PROMPT_PRINT_ERROR = "There are no encrypted messages.\n" + "Press:\n"
      + " [1] to enter message from console\n" + " [5] enter file to encrypt\n";
  private static String PROMPT_PRINT_ERROR_DE = "There are no decrypted messages.\n" + "Press:\n"
      + " [5] to enter a file to decrypt\n";
  private static String PROMPT_MESSAGE_COL_HEADER_EN =
      "Here is your collection of encrypted messages\n" + "Format: [Secret Key:Message]\n" + ">> ";
  private static String PROMPT_MESSAGE_COL_HEADER_DE =
      "Here is your collection of decrypted messages\n" + "Format: [Secret Key:Message]\n" + ">> ";
  private static String secretKey;
  private static String inputMessageString;
  private static AESMessage message;
  private static ArrayList<AESMessage> messageList = new ArrayList<AESMessage>();
  private static ArrayList<AESMessage> decodeMessageList = new ArrayList<AESMessage>();

  /**
   * Painter driver method
   * 
   * @param scn Scanner with System.in
   */
  private static void driver(Scanner scn) {
    System.out.println(PROMPT_PROGRAM_DISCRIPTION);
    boolean isTerminated = false;
    while (!isTerminated) {
      System.out.print(PROMPT_MENU);
      isTerminated = processCommand(scn);
    }

    System.out.print("Bye!\n");
  }

  private static boolean processCommand(Scanner scn) {
    String cmd = scn.nextLine().trim(); // read user command line
    switch (cmd) {
      case "1": // create message and encrypt message
        try {
          System.out.print(PROMPT_INPUT_MESSAGE);
          inputMessageString = scn.nextLine();
          System.out.println();
          message = new AESMessage();
          message.setMessageString(inputMessageString); // set message field to user input
          System.out.println(PROMPT_SET_MESSAGE_RESPONSE);
          
          System.out.print(PROMPT_SET_KEY);
          secretKey = scn.nextLine().trim();
          System.out.println();
          String messageToBeEncoded = message.getMessageString();
          message.setKey(secretKey);
          message.encrypt(messageToBeEncoded, secretKey);
          System.out.println(PROMPT_ENCRYPTED_MESSAGE);
          messageList.add(message);
          System.out.println(PROMPT_MESSAGES_ADDED_LIST);
        } catch (Exception e) {
          e.printStackTrace();
        }
        break;
      case "2": // Display last encrypted message
        try {
          if (message.isEncryped() == false) {
            System.out.println("Message not set");
            System.out.println();
          } else {
            System.out.println("Last encrypted message: " + message.getMessageString());
            System.out.println();
          }

        } catch (Exception e) {
          e.printStackTrace();
        }
        break;
      case "3": // print stored messages
        try {
          System.out.print("Would you like to view your Encrypted Messages or Decrypted Messages?\n"
              + " Enter 'E' for Encryped Messages\n" + " Enter 'D' for Decrypted Messages\n"
              + "> ");
          String encryptKey = "E";
          String decryptKey = "D";
          String action = scn.nextLine();
          System.out.println();
          if (action.equals(encryptKey)) {
            if (messageList.isEmpty()) {
              System.out.println(PROMPT_PRINT_ERROR);
              System.out.println();
            } else {
              System.out.print(PROMPT_MESSAGE_COL_HEADER_EN);
              System.out.println(messageList.toString());
              System.out.println();
            }
          }
          if (action.equals(decryptKey)) {
            if(decodeMessageList.isEmpty()) {
              System.out.println(PROMPT_PRINT_ERROR_DE);
              System.out.println();
            }
            else {
              System.out.print(PROMPT_MESSAGE_COL_HEADER_DE);
              System.out.println(decodeMessageList.toString());
              System.out.println();
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
        break;
      case "5": // decrypt message/s from file
        try {
          System.out.print("Enter File name\n" + "> ");
          String fileName = scn.nextLine();
          java.io.File file = new File(fileName);
          System.out.println("Decryped Messages:");

          if (file.exists()) {
            Scanner inFile = new Scanner(file);

            String line = null;
            while (inFile.hasNextLine()) {
              AESMessage decodeMessage = new AESMessage();
              line = inFile.nextLine();

              String[] token = line.split(":");
              String secretKeyString = token[0];
              String messageToDecode = token[1];
              decodeMessage.decrypt(messageToDecode, secretKeyString);
              decodeMessageList.add(decodeMessage);
              System.out.println("> Secret Key: " + secretKeyString + " | " + " Decrypted Message: "
                  + decodeMessage.getMessageString());
            }
          }
          System.out.println();
          System.out.print("Collection of Decrypted messages\n" + ">> ");
          System.out.println(decodeMessageList.toString());
          System.out.println();
        } catch (Exception e) {
          e.printStackTrace();
        }
        break;
      case "4": // encrypt message/s from file
        try {
          System.out.print("Enter File name\n" + "> ");
          String fileName = scn.nextLine();
          java.io.File file = new File(fileName);
          System.out.println("Encryped Messages:");

          if (file.exists()) {
            Scanner inFile = new Scanner(file);

            String line = null;
            while (inFile.hasNextLine()) {
              AESMessage encodeMessage = new AESMessage();
              line = inFile.nextLine();

              String[] token = line.split(":");
              String secretKey = token[0];
              String messageToEncrypt = token[1];
              encodeMessage.encrypt(messageToEncrypt, secretKey);
              messageList.add(encodeMessage);
              // System.out.println(messageList.toString());

              System.out.println("> Secret Key: " + secretKey + " | " + "Encrypted Message: "
                  + encodeMessage.getMessageString());
            }
          }
          System.out.println();
          System.out.print("Collection of Encrypted Messages\n" + ">> ");
          System.out.println(messageList.toString());
          System.out.println();
        } catch (Exception e) {
          e.printStackTrace();
        }
        break;
      case "6": // write message collection to file
        try {
          System.out.print("Enter output File name\n" + "> ");
          String fileName = scn.nextLine();
          System.out
              .println("Would you like to export your encrypted messages or decrypted message?");
          System.out.print(
              " Enter 'E' for encryped message\n" + " Enter 'D' for decrypted messages\n" + "> ");
          String exportAction = scn.nextLine();
          PrintWriter writer = new PrintWriter(fileName);
          if (exportAction.equals("E")) {
            if (messageList.isEmpty()) {
              System.out.println(
                  "You have no encoded messages. Select command 1 from menu to encode message");
            } else {
              for (AESMessage messageLine : messageList) {
                writer.println(messageLine);
              }
              System.out.println("Success! Refresh project to see your exported encoded messages");
              messageList.clear();
            }
          } else if (exportAction.contentEquals("D")) {
            if (decodeMessageList.isEmpty()) {
              System.out.println("no messages have been deocoded");
            } else {
              for (AESMessage decodeMessageLine : decodeMessageList) {
                writer.println(decodeMessageLine);
              }
              decodeMessageList.clear();
              System.out.println("Success! Refresh project to see your exported decoded messages");

            }
          } else {
            System.out.println("Somethign went wrong");
          }
          writer.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
        System.out.println();
        break;
      case "7": // Quit 
        try {
         return true;
        } catch (Exception e) {
          e.printStackTrace();
        }
        break;
    }
    return false;
  }

  public static void main(String[] args) {
    // print student information
    Main main = new Main();
    System.out.println(main.NAME);
    driver(sc);
    
  }
  
} // end Main class
