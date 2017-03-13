import java.io.*;
import javax.swing.DefaultListModel;
public class AutoSuggest {
	int count = 0;
	
	static TrieNode root;
	static String string;
	static DefaultListModel model;
	
	public AutoSuggest()
	{
		root = new TrieNode();
		try
		{
			 BufferedReader br = new BufferedReader(new FileReader("bin/dictionary.txt"));
			 String line = br.readLine();
			 while(line!=null)
			 {
				 insertWordIntoTrie(root, line);
				 line = br.readLine();
			 }
		}
		catch(IOException exception)
		{
			exception.printStackTrace();
		}
	}
	boolean insertWordIntoTrie(TrieNode root,String word)
	{
		for(int i=0;i<word.length();i++)
		{
			int index = word.charAt(i)-'a';
			if(root.next[index]==null)
			{
				root.next[index] = new TrieNode();
			}
			root = root.next[index];
		}
		if(root.isEOW==true)
		return false;
		root.isEOW = true;
		return true;
	}
	void printAllWordsHelper(TrieNode root,StringBuilder word,DefaultListModel model)
	{
		if(count>15) return;
		if(root.isEOW==true)
		{
			string = word.toString();
			model.addElement(string);
			count++;
		}
		for(int i=0;i<26;i++)
		{
			if(root.next[i]!=null)
			{
					char c = (char)(i+'a');
					StringBuilder temp = new StringBuilder(word.toString());
					temp.append(String.valueOf(c));
					printAllWordsHelper(root.next[i],temp,model);
			}
		}
	}
	void printAllWordsWithPrefix(TrieNode root,String prefix)
	{
		if(root==null) return;
		for(int i=0;i<prefix.length();i++)
		{
			int ind = prefix.charAt(i)-'a';
			if(root.next[ind]!=null)
				root = root.next[ind];
			else
				{
					model.clear();
					return;
				}
		}
		StringBuilder s = new StringBuilder(prefix);
		model = new DefaultListModel();  //Createing new Model on every call from GUI.java
		count = 0; // to count number of words on every call from GUI.java
		printAllWordsHelper(root,s,model);
	}
	public static void main(String args[])
	{
		new AutoSuggest();
	}

}
