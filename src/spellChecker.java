import java.io.*;
import java.util.*;

class TrieNode {
	final int ALPHABETS_SIZE = 26;
	char value;
	boolean isWord;
	TrieNode[] children = new TrieNode[ALPHABETS_SIZE];
	
	TrieNode() {
		value = 0;
		isWord = false;
		for(int i = 0; i < ALPHABETS_SIZE; i++) {
			children[i] = null;
		}
	}	
}

class Trie {
	TrieNode root;
	
	Trie() {
		root = new TrieNode();
	}
	
	void insert(String word) {
		TrieNode path = root;
		
		for(int i = 0; i < word.length(); i++) {			
			int index = word.charAt(i) - 'a';
			if(path.children[index] == null) {
				path.children[index] = new TrieNode();
				path.children[index].value = word.charAt(i);
			}
			path = path.children[index];
		}
		path.isWord = true;
	}
	
	boolean search(String word) {
		TrieNode path = root;
		
		for(int i = 0; i < word.length(); i++) {
			int index = word.charAt(i) - 'a';
			if(path.children[index] == null) {
				return false;
			}
			path = path.children[index];
		}
		return(path != null && path.isWord);
	}
}

public class spellChecker {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		Trie trie = new Trie();
		
		File file = new File("dictionary.txt"); 
		  
		BufferedReader br = new BufferedReader(new FileReader(file)); 
	  
		HashSet<String> hs = new HashSet<String>();
		
		String str; 
		while ((str = br.readLine()) != null) { 
			//System.out.println(st);
			if(hs.add(str)) {
				trie.insert(str);
			}			
	  	}
		System.out.println("Dictionary Trie created.");
		br.close();
		System.out.print("Enter a string for spell check: ");		
		String[] words = scan.nextLine().toLowerCase().replaceAll("\\p{Punct}", "").split(" ");	
		scan.close();
		ArrayList<String> misspelled_words = new ArrayList<String>();
		int flag = 0;
		
		for (int i=0; i<words.length; i++) {			
			boolean check = trie.search(words[i]);
			if(!check) {
				misspelled_words.add(words[i]);
				flag = 1;
			}			
		}
		if(flag == 1) {
			System.out.println("Invalid String!");
			System.out.print("Misspelled Words: ");
			String joined = String.join(", ", misspelled_words);
			System.out.println(joined);
		}
		else {
			System.out.println("Valid String!");
		}		
	}
}
