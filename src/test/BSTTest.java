package test;

import java.util.Random;

import implementations.BinarySearchTree;

public class BSTTest {
	
	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Random rand = new Random();
		int num = 0;
		for (int i = 0; i < 20; i++) {
			num = rand.nextInt(100) + 1;
			boolean result = bst.add(num);  // iterative test
			System.out.println("Adding " + num + ", " + result);
		}
		System.out.println("\nPrinting inorder: ");
		bst.recursiveInOrder();
		System.out.println();
		System.out.println("Contains: " + num + " --> " + bst.contains(num));
		System.out.println("Contains: 200" + " --> " + bst.contains(200));
		bst.delete(num);
		System.out.println("Contains: " + num + " --> " + bst.contains(num));
		System.out.println();
		bst.findMax();
		bst.findMin();
	}

}
