public class TrieNode {
	boolean isEOW;
	TrieNode next[] = new TrieNode[26];
	public TrieNode()
	{
		isEOW = false;
		for(int i=0;i<26;i++)
		{
			next[i] = null;
		}
	}
}
