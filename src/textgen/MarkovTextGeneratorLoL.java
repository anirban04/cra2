package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		
		if(sourceText.equals("")) {
			return;
		}

		String[] tokens;
		tokens = sourceText.split("[\\s]+");
		starter = tokens[0];
		boolean done = false;
		
		
		for(int i=0; i<tokens.length; i++) {
			if(tokens.equals("")) {
				continue;
			}
			for (ListNode ln: wordList) {
				if(ln.getWord().equals(tokens[i])) {
					if((i + 1) < tokens.length)
						ln.addNextWord(tokens[i+1]);
					else
						ln.addNextWord(starter);
					done = true;
					break;
				}
			}
			if(!done) {
				ListNode newLn = new ListNode(tokens[i]);
				if((i + 1) < tokens.length)
					newLn.addNextWord(tokens[i+1]);
				else
					newLn.addNextWord(starter);
				wordList.add(newLn);
			}
			done = false;
		}
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		StringBuilder sb = new StringBuilder();
		String appStr = null;
		while(numWords-- > 0) {
	    	if(sb.length() == 0) {
	    		appStr = starter;
	    		sb.append(appStr);
	    	}
	    	else {
	    		for(ListNode ln : wordList) {
	    			if(ln.getWord().equals(appStr)) {
	    				appStr = ln.getRandomNextWord(rnGenerator);

	    				if (appStr.equals(null))
	    					appStr = starter;

	    				sb.append(" ");
	    				sb.append(appStr);
	    				break;
	    			}
	    		}
	    	}
	    }
		return sb.toString();
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		wordList.clear();
		starter = "";
		if(sourceText.equals("")) {
			return;
		}
		train(sourceText);
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(85868));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		String str = null;
		/* Check for nextWord to have no elements */
		if(nextWords.size() == 0)
			return null;

		int temp = generator.nextInt();
		if(temp <  0)
			temp = temp * -1;

		int index = temp % (nextWords.size());

		str = nextWords.get(index);
		return str;
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


