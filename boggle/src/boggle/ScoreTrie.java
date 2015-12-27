package boggle;

import java.util.ArrayList;

public class ScoreTrie {
	
	Node root;
	
	public ScoreTrie() {
		root = new Node();
	}
	
	public boolean find(String s) {
		return true;
	}
	
	public void insert(String s, int score) {
		String lowercase = s.toLowerCase();
		insertHelper(root, lowercase, lowercase, score);
	}
	
	private void insertHelper(Node n, String left, String value, int score) {
		// Get proper child node
		Node chosenChild = n.hasChild(left.charAt(0));
		if (chosenChild == null) {
			// Need new child node
			chosenChild = n.addChild(left.charAt(0));
		}
		
		// Add to child
		if (left.length() == 1) {
			chosenChild.value = value;
			chosenChild.score = score;
			// return
		}
		else {
			insertHelper(chosenChild, left.substring(1), value, score);
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
			int score = child.score;
			if (!(value.isEmpty())) {
				System.out.println("Value: " + value + " " + score);
			}
			printHelper(child);
		}
	}
	
    // Trie Node Class
	private class Node { 
		char c;
		ArrayList<Node> children;
		String value;
		int score;
		
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
		
		ScoreTrie tr = new ScoreTrie();
		tr.insert("Hello", 10);
		tr.insert("He", 5);
		tr.insert("Help", 7);
		tr.insert("Cow", 9);
		tr.insert("Cones", 11);
		tr.insert("Cone", 6);
		tr.insert("Constant", 7);
		
		tr.printAll();
	}

}
