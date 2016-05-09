package pp.app;

public class ProjectLeaderQueue {
	Node head;
	Node tail;
	int size;
	
	/*
	 * Singly linked list for the queue of project leaders
	 */
	
	public void enqueue(User u) {
		Node oldTail = tail;
		tail = new Node(u);
		tail.next = null;
		
		if (isEmpty()) {
			head = tail;
		} else {
			oldTail.next = tail;
		}
		size++;
	}

	public User dequeue() {
		if (!isEmpty()) {
			User userToReturn = head.u;
			head = head.next;
			size--;
			return userToReturn;
		}
		return null;
	}
	
	public void delete(User u) {
		if (head.u.equals(u)) {
			Node nodeToDelete = head;
			head = head.next;
			size--;
//			return nodeToDelete.u;
		}
		
		
		Node n = head;
		for (int i = 0; i < size-1; i++) {
			if (n.next.u.equals(u)) {
				Node nodeToDelete = n.next;
				if (!nodeToDelete.equals(tail)) {
					n.next = nodeToDelete.next;
				} else {
					tail = n;
				}
				size--;
//				return nodeToDelete.u;
			}
			n = n.next;
		}
//		return null;
	}
	
	private boolean isEmpty() {
		return head == null;
	}
	
	public String toString() {
		Node n = head;
		String s = "";
		for (int i = 0; i < size; i++) {
			s += n.u.getUserId() + ", ";
			n = n.next;
		}
		return s;
	}

	private class Node {
		User u;
		Node next;
		
		public Node(User u) {
			this.u = u;
		}
	}
}