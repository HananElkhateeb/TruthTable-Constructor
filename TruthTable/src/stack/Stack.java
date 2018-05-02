package stack;

import stack.IStack;

/**
 * @author HANAN SAMIR
 *
 */
public class Stack implements IStack {
	/**
	 * hanan.
	 */
	private int size = 0;
	/**
	 * hanan.
	 */
	class LinkedList {
		/**
		 * hanan.
		 */
		private Node head = null;
		/**
		 * hanan.
		 */
		public class Node {
			/**
			 * hanan.
			 */
			public Object data;
			/**
			 * hanan.
			 */
		    public Node pnext;
		    /**
			* @param element this is id of table.
			* @param next this is id of table.
			*/
		public Node(Object element, Node next) {
				this.data = element;
				this.pnext = next;
			}
		}
		/**
		 * hanan.
		 */
		public void print() {
			Node i = head;
			while (i != null) {
				System.out.println(i.data);
				i = i.pnext;
			}
		}
		/**
		 * @param element this is id of table.
		 * @param index this is id of table.
		 */
		public void add(int index, Object element) {
			Node newnode = new Node(element, null);
			if (index == 0) {
				newnode.pnext = head;
				head = newnode;
			} else {
				Node curr = head;
				for (int i = 1; i < index; i++) {
					curr = curr.pnext;
				}
				newnode.pnext = curr.pnext;
				curr.pnext = newnode;
			}
		}
		/**
		 * @param element this is id of table.
		 */
		public void addHead(Object element) {
			Node newnode = new Node(element, null);
			newnode.pnext = head;
			head = newnode;
		}
		/**
		 * hanan.
		 */
		public void remove() {
			head = head.pnext;
		}
		/**
		 *  @return top of stack element, or through exception if empty
		 */
		public Object get() {
			return head.data;
		}
		/**
		 * hanan.
		 */
		public void reverse() {
			Node before = null;
			Node tmp = head;
			while (tmp != null) {
				Node next = tmp.pnext;
				tmp.pnext = before;
				before = tmp;
				tmp = next;
			}
			head = before;
		}
	}
	/**
	 * hanan.
	 */
	LinkedList stelement = new LinkedList();
	/**
	* Inserts a specified element at the specified position in the list.
	* @param index zero-based index
	* @param element object to insert
	*/
	public void add(int index, Object element) {
		stelement.reverse();
		if (index < 0 || index > size) {
			throw new RuntimeException();
		}
		stelement.add(index, element);
		size++;
		stelement.reverse();
	}
	/**
	 * hanan.
	 */
	public void display() {
		System.out.println("stack:");
		stelement.print();
	}
	/**
	* Removes the element at the top of stack and returns that element.
	* @return top of stack element, or through exception if empty
	*/
	public Object pop() {
		if (size == 0) {
			throw new RuntimeException();
		}
		Object element = stelement.get();
		stelement.remove();
		size--;
		return element;
	}
	/**
	* Get the element at the top of stack without removing it from stack.
	* @return top of stack element, or through exception if empty
	*/
	public Object peek() {
		if (size == 0) {
			throw new RuntimeException();
		}
		Object element = stelement.get();
		return element;
	}
	/**
	* Pushes an item onto the top of this stack.
	* @param element to insert
	*/
	public void push(Object element) {
		stelement.addHead(element);
		size++;
	}
	/**
	* Tests if this stack is empty.
	* @return true if stack empty
	*/
	public boolean isEmpty() {
		return size == 0;
	}
	/**
	* Returns the number of elements in the stack.
	* @return number of elements in the stack
	*/
	public int size() {
		return size;
	}

}
