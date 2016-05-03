package pp.app;

public class ProjectLeaderQueue {
	Node head;
	Node tail;
	
	public void enqueue(User u) {
		Node oldTail = tail;
		tail = new Node(u);
		tail.next = null;
		
		if (isEmpty()) {
			head = tail;
		} else {
			oldTail.next = tail;
		}
	}

	public User dequeue() {
		if (!isEmpty()) {
			User userToReturn = head.u;
			head = head.next;
			return userToReturn;
		}
		return null;
	}
	
	private boolean isEmpty() {
		return head == null;
	}

	private class Node {
		User u;
		Node next;
		
		public Node(User u) {
			this.u = u;
		}
	}
}