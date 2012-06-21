/**
 * 
 */
package com.easyJ.parser;

import com.easyJ.util.JException;

/**
 * Custom parser interface for JExpressions.
 * @author khan.m
 *
 */
public interface JParseable extends Parseable<String> {
	
	/**
	 * Get the previous token's index.
	 * @return
	 */
	public int getPrevIndex();
	
	/**
	 * Returns the token type(OGNL/JExpression)
	 * @return
	 */
	public String getTokenType();
	
	/**
	 * Returns the next token element.
	 * @return
	 * @throws JException
	 */
	public Token nextToken() throws JException;
}
