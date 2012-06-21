/**
 * 
 */
package com.easyJ.parser;

import java.util.Map;

/**
 *	An abstract class representing any node in an expression tree.
 *	The following three concrete node classes are subclasses. 
 *	Every node is associated with a token which can be evaluated
 *	and the value of the toen is the value of the node. 
 * @author khan.m
 *
 */
abstract class ExpNode<T, V> {
	
	/**
	 * This it true in case of the nodes with no children.
	 */
	protected boolean leaf;
	
	/**
	 * Get the value of the node by evaluating it.
	 * @param value
	 * @return
	 * @throws JParseException
	 */
	public abstract boolean value(T value) throws JParseException;
	
	/**
	 * Get the value of the node by evaluating it.
	 * @param value
	 * @return
	 * @throws JParseException
	 */
	public abstract boolean value(Map.Entry<T, V> value) throws JParseException;
	
	/**
	 * Get the value of the node by evaluating it.
	 * @param collectionSize
	 * @return
	 * @throws JParseException
	 */
	public abstract int[] value(int collectionSize) throws JParseException;
	
	/**
	 * Get the value of the node by evaluating it.
	 * @param object
	 * @return
	 * @throws JParseException
	 */
	public abstract Object getValue(Object object) throws JParseException;
	
	/**
	 * Get the token associated with the node.
	 * @return
	 */
	abstract Token token();

	/**
	 * @return the leaf
	 */
	public boolean isLeaf() {
		return leaf;
	}

	/**
	 * @param leaf the leaf to set
	 */
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}	
}