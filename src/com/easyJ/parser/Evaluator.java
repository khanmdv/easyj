/**
 * 
 */
package com.easyJ.parser;

/**
 * Interface to evaluate objects.
 * @author khan.m
 *
 */
public interface Evaluator<T, V> {
	
	/**
	 * Evaluate an object
	 * @param object
	 * @return
	 */
	public boolean evaluate(Token token, T object) throws JParseException;
	
	/**
	 * Evaluate a collection
	 * @param object
	 * @return
	 */
	public int[] evaluate(Token token, int collectionSize) throws JParseException;
	
	/**
	 * Evaluate a collection
	 * @param object
	 * @return
	 */
	public Object getValue(Token token, T object) throws JParseException;
}
