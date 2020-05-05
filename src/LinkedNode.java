/**
 * LinkedNode class represents a generic class of a node in a linked list
 * 
 * @author Mouna AYARI BEN HADJ KACEM
 */
public class LinkedNode<T> {
  // Fields
  private T data; // item data field of any type T
  private LinkedNode<T> next; // reference to the next node in the list


  // Constructors

  /**
   * Creates an instanceof LinkedNode with a given item The created LinkedNode does not refer to any
   * next one
   * 
   * @param data
   */
  public LinkedNode(T data) {
    this.data = data;
    next = null; // optional
  }



  /**
   * Creates an instance of LinkedNode with given item and reference to next node
   * 
   * @param data value
   * @param next reference to the next node of the same type
   */
  public LinkedNode(T data, LinkedNode<T> next) {
    this.data = data;
    this.next = next;
  }


  // Getters and Setters


  /**
   * Accessor for item field
   * @return the item
   */
  public T getData() {
    return data;
  }



  /**
   * Setter of the item
   * 
   * @param data the item to set
   */
  public void setData(T data) {
    this.data = data;
  }



  /**
   * Accessor for next field
   * 
   * @return the next
   */
  public LinkedNode<T> getNext() {
    return next;
  }



  /**
   * Setter for next field
   * 
   * @param next the next to set
   */
  public void setNext(LinkedNode<T> next) {
    this.next = next;
  }



  /**
   * Test the implementation of the LinkedNode
   * 
   * @param args
   */
  public static void main(String[] args) {
    LinkedNode<String> node0 = new LinkedNode<String>("Hello");
    
    LinkedNode<String> node1 = new LinkedNode<>("CS300 Students", node0);

    System.out.println("Data value of strNode: " + node0.getData());
    System.out.println("Data value of strNode1: " + node1.getData());

    LinkedNode<String> node2 = new LinkedNode<>("CS200",
        new LinkedNode<String>("CS300", new LinkedNode<String>("CS400")));
    System.out.println(node2.getNext().getNext().getData());

  }



} // end generic class LinkedNode
