package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
		TrieNode node = root;
		boolean present = true;

		if (word == null)
			return false;

	    String lWord = word.toLowerCase();
	    char[] cArr = lWord.toCharArray();
	    
	    for(char c : cArr) {
	    	TrieNode temp = node.getChild(c);
	    	if(temp == null) {
	    		present = false;
	    		temp = node.insert(c);	
	    	}
	    	node = temp;
	    }
	    
	    if((present) && (node.endsWord()))
	    	return false;
	    else {
	    	node.setEndsWord(true);
	    	size++;
	    	return true;
	    }
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    return this.size;
	}
	
	/* Returns null if stem not found */
	private TrieNode getStemNode(String s) {
		TrieNode node = root;

		if (s == null)
			return null;

	    String lWord = s.toLowerCase();
	    char[] cArr = lWord.toCharArray();
	    
	    for(char c : cArr) {
	    	node = node.getChild(c);
	    	if(node == null) {
	    		return null;
	    	}
	    }
	    return node;
	}
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
		TrieNode node = getStemNode(s);
		
	    if(node == null)
	    	return false;
	    
	    if(node.endsWord())
	    	return true;
	    else 
	    	return false;
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
 
    	 List<String> lst = new LinkedList<String>();
    	 TrieNode node = getStemNode(prefix);
    	 
    	 if(node == null)
    		 return lst;

 
    	 Queue<TrieNode> q = new LinkedList<TrieNode>();
    	 q.add(node);
    	 
    	 while ((!q.isEmpty()) && (numCompletions > 0)) {
    		 node = q.remove();
    		 if(node.endsWord()) {
    			 lst.add(node.getText());
    			 numCompletions--;
    		 }
    		 Set<Character> cList = node.getValidNextCharacters();
    		 for (Character c : cList) {
    			 q.add(node.getChild(c));
    		 }
    	 }
    	 return lst;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}	
}