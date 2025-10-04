package steele;

/**
 * Node class for linked list
 * @author KevinSteele
 * @version 1.0
 * @since 10-2-2025
 * 
 */
public class Node {
	public int data; // data of the node
	public Node next; // pointer to the next node
	
	
	/**
	 * Constructor for Node
	 * @param data data of the node
	 */
	public Node(int data) {
		this.data = data;
		this.next = null;
	}
}
