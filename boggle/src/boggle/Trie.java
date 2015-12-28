package boggle;

import java.util.ArrayList;

public class Trie {
	
	Node root;
	
	public Trie() {
		root = new Node();
	}
	
	/**
	 * -1 word doesn't exist and this "prefix" isn't in the trie
	 * 0 word doesn't exist
	 * 1 word exists
	 * @param n
	 * @param s
	 * @return
	 */
	public int contains(String s) {
		return containsHelper(root, s);
	}
	
	
	private int containsHelper(Node n, String s) {
		if (s == null)
			return -1;
		
		Node chosenChild = n.hasChild(s.charAt(0));
		if (chosenChild == null) {
			// word doesn't exist and nothing with this prefix exists
			return -1;
		}
		else {
			if (s.length() <= 1) {
				if (chosenChild.value.isEmpty()) {
					// word doesn't exist
					return 0;
				}
				else {
					// word exists
					return 1;
				}
			}
			else {
				return containsHelper(chosenChild, s.substring(1));
			}
		}
	}
	
	public void insert(String s) {
		String lowercase = s.toLowerCase();
		insertHelper(root, lowercase, lowercase);
	}
	
	private void insertHelper(Node n, String left, String value) {
		// Get proper child node
		Node chosenChild = n.hasChild(left.charAt(0));
		if (chosenChild == null) {
			// Need new child node
			chosenChild = n.addChild(left.charAt(0));
		}
		
		// Add to child
		if (left.length() == 1) {
			chosenChild.value = value;
			// return
		}
		else {
			insertHelper(chosenChild, left.substring(1), value);
		}
	}
	
	public void printAll() {
		printHelper(root);
	}
	
	private void printHelper(Node n) {
		ArrayList<Node> children = n.children;
		for (int i = 0; i < children.size(); i++) {
			Node child = children.get(i);
			
			char c = child.c;
			//System.out.println("Node: " + c);
			
			String value = child.value;
			if (!(value.isEmpty())) {
				System.out.println("Value: " + value);
			}
			printHelper(child);
		}
	}
	
    // Trie Node Class
	private class Node { 
		char c;
		ArrayList<Node> children;
		String value;
		
		public Node() {
			// Root Node constructor only
			this.c = 0; // empty
			this.children = new ArrayList<Node>();
			this.value = "";
		}
		
		public Node(char c) {
			this.c = c;
			this.children = new ArrayList<Node>();
			this.value = "";
		}
		
		public Node(char c, String value) {
			this.c = c;
			this.children = new ArrayList<Node>();
			this.value = value;
		}
		
		public boolean equals(char c) {
			return this.c == c;
		}
		
		public Node hasChild(char c) {
			for (int i = 0; i < children.size(); i++) {
				if (children.get(i).equals(c)) {
					return children.get(i);
				}
			}
			return null;
		}
		
		public Node addChild(char c) {
			children.add(new Node(c));
			return children.get(children.size() - 1);
		}
		
		public Node addChild(char c, String value) {
			children.add(new Node(c, value));
			return children.get(children.size() - 1);
		}
	}
	
	public static void main(String[] args) {
		
		Trie tr = new Trie();
		tr.insert("Hello");
		tr.insert("He");
		tr.insert("Help");
		tr.insert("Cow");
		tr.insert("Cones");
		tr.insert("Cone");
		tr.insert("Constant");
		
		tr.printAll();
	}

}
