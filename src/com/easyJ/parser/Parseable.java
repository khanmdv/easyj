/**
 * 
 */
package com.easyJ.parser;

/**
 * Interface for an expression parser.
 * @author khan.m
 *
 */
public interface Parseable<T> {
	
	/**
	 * Method returns true if the parser has anymore tokens available.
	 * @return
	 */
	public boolean hasMoreTokens();
	
	/**
	 * Returns the next token.
	 * @return
	 */
	public T getToken();
}
