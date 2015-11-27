package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		this.head = new LLNode<E>(null);
		this.tail = new LLNode<E>(null);
		this.size = 0; 
		this.tail.prev = head;
		this.head.next = tail;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		if (element == null)
			throw new NullPointerException("Null Element Cannot be added!");

		add(this.size, element);
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index)
	{
		LLNode<E> node = head;

		if ((index >=size) || (index < 0))
			throw new IndexOutOfBoundsException("Index out of bounds!");
		
		while(index-- >= 0)
			node = node.next;

		return node.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		/* Holds the node after which the element is to be inserted */
		LLNode<E> node = head;

		if ((index < 0) || (index > size))
			throw new IndexOutOfBoundsException("Cannot add at that index!");
		
		if (element == null)
			throw new NullPointerException("Null Element Cannot be added!");
		
		if(index < size) {
			while(index-- > 0)
				node = node.next;
		}
		else
			node = tail.prev;
		
		LLNode<E> newNode = new LLNode<E>(element);
		newNode.next = node.next;
		newNode.prev = node;
		newNode.next.prev = newNode;
		newNode.prev.next = newNode;
		this.size++;
	}

	/** Return the size of the list */
	public int size() 
	{
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		LLNode<E> node = head;

		if ((index >=size) || (index < 0))
			throw new IndexOutOfBoundsException("Index out of bounds!");
		
		while(index-- >= 0)
			node = node.next;
		
		node.prev.next = node.next;
		node.next.prev = node.prev;
		
		this.size--;

		return node.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		LLNode<E> node = head;
		E data;

		if ((index >=size) || (index < 0))
			throw new IndexOutOfBoundsException("Index out of bounds!");
		
		if(element == null)
			throw new NullPointerException("Cannot set to null!");
		
		while(index-- >= 0)
			node = node.next;

		data = node.data;
		node.data = element;

		return data;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
}
