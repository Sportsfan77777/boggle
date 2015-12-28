package boggle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Dictionary {
	
	Trie dictionary;
	
	Trie mostCommon;
	Trie common;
	Trie rare;
	
	public Dictionary() {
		dictionary = new Trie();
		loadDictionary();
		
		mostCommon = new Trie();
		loadMostCommonWords();
		
		common = new Trie();
		loadCommonWords();
		
		rare = new Trie();
		loadRareWords();
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
	
	public boolean isMostCommon(String s) {
		if (mostCommon.contains(s) == 1)
			return true;
		else
			return false;
	}
	
	public boolean isCommon(String s) {
		if (common.contains(s) == 1)
			return true;
		else
			return false;
	}
	
	public boolean isRare(String s) {
		if (rare.contains(s) == 1)
			return true;
		else
			return false;
	}
	
	public void print() {
		dictionary.printAll();
	}
	
	public void loadMostCommonWords() {
		File f = new File("words/thousand.txt");
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
				mostCommon.insert(line);
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
	
	public void loadCommonWords() {
		File f = new File("words/leveltwo.txt");
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
				common.insert(line);
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
	
	public void loadRareWords() {
		File f = new File("words/levelthree.txt");
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
				rare.insert(line);
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
	
	public static void main(String[] args) {
		Dictionary d = new Dictionary();
		d.print();
	}
}
