/**
 * 
 */
package com.easyJ.parser;

import java.util.Map.Entry;

/**
 * This node is either a JExpression or an OGNL expression. The node has 
 * a token which can be evaluated and the value of the token becomes 
 * the value of the node.
 * @author khan.m
 *
 */
class ConstNode<T, V> extends ExpNode<T, V> {
	// An expression node that holds a number.
	Token token;

	public ConstNode(Token token) {
		// Construct a ConstNode containing the specified number.
		this.token = token;
		this.leaf = true;
	}

	/**
	 * Get the value of the node.
	 */
	@Override
	public boolean value(T value) throws JParseException{
		// The value of the node is the number that it contains.
		return this.token.evaluate(value);
	}
	
	/**
	 * Get the associated token.
	 */
	public Token token() {
		// The value of the node is the number that it contains.
		return this.token;
	}
	
	/**
	 * Get the value of the node.
	 */
	@Override
	public int[] value(int collectionSize) throws JParseException{
		return this.token.evaluate(collectionSize);
	}

	/**
	 * Get the value of the node.
	 */
	@Override
	public boolean value(Entry<T, V> value) throws JParseException{
		// The value of the node is the number that it contains.
		return this.token.evaluate(value);
	}

	/**
	 * Get the value of the node.
	 */
	@Override
	public Object getValue(Object object) throws JParseException {
		return this.token.getValue(object);
	}
}