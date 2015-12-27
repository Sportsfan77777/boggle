package boggle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Dictionary {
	
	Trie dictionary;
	
	public Dictionary() {
		dictionary = new Trie();
		loadDictionary();
	}
	
	public void loadDictionary() {
		File f = new File("words/words.txt");
		FileInputStream fs = null;
		BufferedReader br = null;
		try {
			fs = new FileInputStream(f);
			br = new BufferedReader(new InputStreamReader(fs));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				dictionary.insert(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	 
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public int contains(String s) {
		return dictionary.contains(s);
	}
	
	public void print() {
		dictionary.printAll();
	}
	
	public static void main(String[] args) {
		Dictionary d = new Dictionary();
		d.print();
	}
}
