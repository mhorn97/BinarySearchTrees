package implementations;

// Binary Search Tree that has an invariant that no duplicate data is allowed
public class BinarySearchTree<T extends Comparable<? super T>> {
	
	// root of tree
	private Node root;
	private int size;
	
	// internal class that defines a node's structure
	// we will be accessing the internal members of this
	// class directly
	protected class Node {
		private T element;
		
		private Node left;
		private Node right;
		
		// constructor for Node
		public Node(T element) {
			this.element = element;
			this.left = null;
			this.right = null;
		}
		
		// toString for Node class
		public String toString() {
			String data = (this.element == null) ? "null" : this.element.toString();
			String leftChild = (this.left == null) ? "null" : this.left.element.toString();
			String rightChild = (this.right == null) ? "null" : this.right.element.toString();
			return leftChild + " <-- " + data + " --> " + rightChild;
		}
	}
	
	// constructor for the tree itself
	public BinarySearchTree() {
		root = null;
	}
	
	// constructor for the tree that takes a starting
	// root node
	public BinarySearchTree(T element) {
		root = new Node(element);
		size++;
	}
	
	// add method - iterative version
	/*public boolean add(T element) {
		// edge case -- is the tree empty?
		if (root == null) {
			root = new Node(element);
			size++;
			return true;
		}
		
		// walk down the tree to the insertion point
		Node current = root;
		Node prev = root;
		int comp = 0;
		while (current != null) {
			// compare the keys
			comp = element.compareTo(current.element);
			
			// if comp < 0, go left
			if (comp < 0) {
				prev = current;
				current = current.left;
			} else if (comp > 0) {  // if comp > 0, go right
				prev = current;
				current = current.right;
			} else { // duplicate
				return false;
			}
		}
		
		// at a leaf's null child
		if (comp < 0) {
			prev.left = new Node(element);
			size++;
		} else {
			prev.right = new Node(element);
			size++;
		}
		
		return true;
	}
	*/
	
	//Add method recursive version
	public boolean add(T element)
	{
		if(root == null)
		{
			root = new Node(element);
			size++;
			return true;
		}
		return add(element,root);
	}
	
	private boolean add(T element, Node current)
	{
		// find the element in the BST
		int comp = element.compareTo(current.element);
		
		if(comp != 0)
		{
			// test results of comparison
			if (comp < 0) 
			{  // search left
				if(current.left != null)
				{
					return add(element,current.left);
				}
				else 
				{
					current.left = new Node(element);
					return true;
				}
			}
			else
			{  // search right
				if(current.right != null)
				{
					return add(element, current.right);
				} else 
				{  
					current.right = new Node(element);
					return true;
				}
			}
		}
		else 
		{
			return false;
		}
	}
	
	// get the size of the BST
	public int size() {
		return size;
	}
	
	// return a boolean to indicate is BST is empty
	public boolean isEmpty() {
		return size == 0;
	}
	
	// clear the BST
	public void clear() {
		root = null;
		size = 0;
	}
	
	//Contains recursive method
	public boolean contains(T element)
	{
		if(root == null)
		{
			return false;
		}
		
		Node current = root;
		int comp;
		while(current!=null) 
		{

			comp = element.compareTo(current.element);
			if(comp < 0)
			{
				current = current.left;
			}
			else if(comp > 0)
			{
				current = current.right;
			}
			else if(comp == 0)
			{
				return true;
			}
		}
		return false;
	}
	
	/*public boolean contains(T element) {
		return contains(element, root);
	}
	
	// test whether our BST contains an element
	private boolean contains(T element, Node current) {
		if (current == null) {
			return false;
		}
		
		int compare = element.compareTo(current.element);
		
		if (compare < 0) {  // search element is smaller than current node's element
			return contains(element, current.left); 
		} else if (compare > 0) { // search element is larger than current node's element
			return contains(element, current.right);
		} else { // they are the same -- found it!
			return true;
		}
	}
	*/
	// remove an element from the tree if it exists -- recursively
	public void delete(T element) {
		root = delete(element, root);
	}
	
	private Node delete(T element, Node current) {
		if (current == null) {
			return current;
		}
		
		// find the element in the BST
		int comp = element.compareTo(current.element);
		
		// test results of comparison
		if (comp < 0) {  // search left
			current.left = delete(element, current.left);
		} else if (comp > 0) {  // search right
			current.right = delete(element, current.right);
		} else {  // found the element we want to delete
			
			if (current.right == null) { // one or no children to the left
				current = current.left;
				size--;
			} else if (current.left == null) {  // one child on the right
				current = current.right;
				size--;
			} else { // two children
				Node minRight = findMin(current.right);
				current.element = minRight.element;
				
				// now delete the min value's original node
				current.right = delete(minRight.element, current.right);
			}
		}
		return current;
	}
	
	private Node findMin(Node node) {
		if (node == null) return null;
		else if (node.left == null) return node;
		return findMin(node.left);
	}
	
	
	/******* TRAVERSALS **********************/
	// recursive version of inorder traversal
	public void recursiveInOrder() {
		recursiveInOrder(root);
	}
	
	// overloaded method
	public void recursiveInOrder(Node n) {
		if (n == null) return;
		recursiveInOrder(n.left);
		System.out.println(n.element);
		recursiveInOrder(n.right);
	}

}
