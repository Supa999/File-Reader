package tester;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import steele.Node;

class NodeTester {

	@Test
	void testNodeCreation() {
		Node node = new Node(5);
		assertNotNull(node);
		assertEquals(5, node.data);
		assertNull(node.next);
	}
	
	@Test
	void testNodeLinking() {
		Node first = new Node(10);
		Node second = new Node(20);
		first.next = second;
		
		assertNotNull(first.next);
		assertEquals(20, first.next.data);
		assertNull(second.next);
	}
	
	@Test
	void testMultipleNodeLinking() {
		Node head = new Node(1);
		Node second = new Node(2);
		Node third = new Node(3);
		
		head.next = second;
		second.next = third;
		
		assertEquals(1, head.data);
		assertEquals(2, head.next.data);
		assertEquals(3, head.next.next.data);
		assertNull(head.next.next.next);
	}

}
