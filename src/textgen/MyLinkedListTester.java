/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
	    shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		try {
			shortList.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		try {
			shortList.remove(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		try {
			longerList.remove(longerList.size());
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}

		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		int cnt = 0;
		assertEquals("Adding to ShortList ", true, shortList.add("pean"));
		assertEquals("Adding to ShortList ", true, shortList.add("1"));
		while(cnt++ < 10000) {
			assertEquals("Adding to longerList ", true, longerList.add(cnt));
		}
		assertEquals("Getting Size ", 10010, longerList.size());
		
		try {
			shortList.add(null);
			fail("Null Pointer Check");
		}
		catch (NullPointerException e) {
			
		}
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		int cnt = 0;
		while(cnt++ < 10) {
			assertEquals("Adding to longerList ", true, longerList.add(cnt));
		}
		assertEquals("Getting Size ", 20, longerList.size());
	}

	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		try {
			shortList.add(-1, "str1");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		try {
			shortList.add(shortList.size() + 1, "str1");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			shortList.add(1, null);
			fail("Null Pointer Check");
		}
		catch (NullPointerException e) {
			
		}
		
		/*Add to an empty list*/
		emptyList.add(0,9);
		assertEquals("AddAtIndex: check get is in-correct ", (Integer)9, emptyList.get(0));
		
		/* Add to the end of the list */
		int oldSize = longerList.size();
		longerList.add(oldSize, 100);
		assertEquals("AddAtIndex: check size is in-correct ", oldSize + 1, longerList.size());
		assertEquals("AddAtIndex: check get is in-correct ", (Integer)100, longerList.get(oldSize));
		
		/* Add to the start of the list */
		oldSize = longerList.size();
		longerList.add(0, 1);
		assertEquals("AddAtIndex: check size is in-correct ", oldSize + 1, longerList.size());
		assertEquals("AddAtIndex: check get is in-correct ", (Integer)1, longerList.get(0));
		
		/* Add more than 1 element to the list; also tests adding at any valid index */
		int i = 0;
		while(i < 10 ) {
			longerList.add(i, i);
			assertEquals("AddAtIndex: check get is in-correct ", (Integer)i, longerList.get(i));
			i++;
		}		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    try {
	    	shortList.set(-1, "A");
	    	fail("Check out of bounds");
	    }
	    catch (IndexOutOfBoundsException e){
	    	
	    }

	    try {
	    	longerList.set(1, null);
	    	fail("Null pointer");
	    }
	    catch (NullPointerException e) {
	    	
	    }
	    
	    try {
	    	longerList.set(longerList.size(), 1);
	    	fail("Check out of bounds");
	    }
	    catch (IndexOutOfBoundsException e){
	    	
	    }
	    
	    /* Setting with only one element in the list */
	    emptyList.add(3);
	    assertEquals("SetIndex: check set is in-correct ", (Integer)3, emptyList.set(0, 1));
	    assertEquals("SetIndex: check get is in-correct ", (Integer)1, emptyList.get(0));
	    
	    /* Setting first element in the list */
	    assertEquals("SetIndex: check set is in-correct ", "A", shortList.set(0, "X"));
	    assertEquals("SetIndex: check get is in-correct ", "X", shortList.get(0));

	    /* Setting last element in the list */
	    assertEquals("SetIndex: check set is in-correct ", "B", shortList.set(shortList.size() - 1, "Y"));
	    assertEquals("SetIndex: check get is in-correct ", "Y", shortList.get(shortList.size() - 1));
	    
	    /* Setting a random element */
	    assertEquals("SetIndex: check set is in-correct ", (Integer)5, longerList.set(5, 100));
	    assertEquals("SetIndex: check get is in-correct ", (Integer)100, longerList.get(5));
	}
	
}
