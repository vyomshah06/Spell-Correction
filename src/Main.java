import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int flag=0;
		String str; 
		
		Scanner scan = new Scanner(System.in);
		
		Trie trie = new Trie();
		spellCorrection sc = new spellCorrection();
		
		File file = new File("dictionary.txt"); 
		  
		BufferedReader br = new BufferedReader(new FileReader(file)); 
	  
		HashSet<String> hs = new HashSet<String>();
		
		while ((str = br.readLine()) != null) { 
			if(hs.add(str)) {
				trie.insert(str);
			}			
	  	}
		br.close();
		str = "";
		System.out.print("Enter Text: ");		
		String[] words = scan.nextLine().toLowerCase().replaceAll("\\p{Punct}", "").split(" ");	
		scan.close();
		
		for (int i=0; i<words.length; i++) {			
			boolean check = trie.search(words[i]);
			if(!check) {				
				flag = 1;
				str = "";
				int edits, max=Integer.MAX_VALUE;
				for(String s : hs) {
					edits = sc.editDistance(words[i], s);
					if(edits < max) {
						max = edits;
						str = s;
					}
				}
				words[i] = str;
			}
		}
		if(flag == 1) {
			System.out.println("Invalid String!");
			if(str != "") {
				System.out.println("Did you mean '" + String.join(" ", words) + "' ?");
			}
			else {
				System.out.println("No suggestion found.");
			}
		}
		else {
			System.out.println("Valid String!");
		}		
	}
}
