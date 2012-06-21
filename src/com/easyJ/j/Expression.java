/**
 * 
 */
package com.easyJ.j;

import com.easyJ.parser.JParseException;

/**
 * Interface to parse a query expression and execute the query on 
 * object/collection.
 * @author khan.m
 *
 */
public interface Expression<T, V> {

	/**
	 * Method to compile the query.
	 * @param expression
	 * @throws JParseException
	 */
	public void compile(String expression) throws JParseException;
	
	/**
	 * Method to evaluate the object against the query.
	 * @param object
	 * @return
	 */
	public boolean matches(T object);
	
	/**
	 * Method to evaluate a collection against the query.
	 * @param collectionSize
	 * @return
	 */
	public int[] matches(int collectionSize);
}
