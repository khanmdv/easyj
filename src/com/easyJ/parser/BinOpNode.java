/**
 * 
 */
package com.easyJ.parser;

import java.util.Map.Entry;

import com.easyJ.util.Util;

/**
 * Node for the operators "&" and "|".
 * @author khan.m
 *
 */
class BinOpNode<T, V> extends ExpNode<T, V> {

	// An expression node representing a binary operator,
	Token token; // The operator.
	ExpNode<T,V> left; // The expression for its left operand.
	ExpNode<T,V> right; // The expression for its right operand.
	
	public BinOpNode(Token tok, ExpNode<T, V> left, ExpNode<T, V> right) {
		// Construct a BinOpNode containing the specified data.
		this.token = tok;
		this.left = left;
		this.right = right;
		this.leaf = false;
	}

	/**
	 * Get the associated token.
	 */
	Token token() {
		// The value of the node is the number that it contains.
		return this.token;
	}
	
	/**
	 * Evaluate the left and right children and perform 
	 * the operation as specified by the associated token.
	 */
	@Override
	public boolean value(T object) throws JParseException{
		// The value is obtained by evaluating the left and right
		// operands and combining the values with the operator.
		
		Token x = left.token();
		Token y = right.token();
		/**
		 * TODO - Code to do something when a binary operator is found.
		 */
		char c = this.token.getToken().charAt(0);
		//System.out.println(x.getToken() + " & " + y.getToken());
		switch(c)
		{
			case '&': return (this.left.isLeaf() ? x.evaluate(object) : this.left.value(object)) && 
					(this.right.isLeaf() ? y.evaluate(object) : this.right.value(object));
			case '|': return (this.left.isLeaf() ? x.evaluate(object) : this.left.value(object)) || 
					(this.right.isLeaf() ? y.evaluate(object) : this.right.value(object));
			default: return false;
		}			
	}

	/**
	 * Evaluate the left and right children and perform 
	 * the operation as specified by the associated token.
	 */
	@Override
	public int[] value(int collectionSize) throws JParseException{
		int[] result = null;
		// The value is obtained by evaluating the left and right
		// operands and combining the values with the operator.
		
		Token x = left.token();
		Token y = right.token();
		/**
		 * TODO - Code to do something when a binary operator is found.
		 */
		char c = this.token.getToken().charAt(0);
		//System.out.println(x.getToken() + " & " + y.getToken());
		switch(c)
		{
			case '&': return (this.left.isLeaf() ? x.evaluate(collectionSize) : Util.intersect( this.left.value(collectionSize),  
					this.right.isLeaf() ? y.evaluate(collectionSize) : this.right.value(collectionSize) ) );
			case '|': return (this.left.isLeaf() ? x.evaluate(collectionSize) : Util.union( this.left.value(collectionSize), 
					this.right.isLeaf() ? y.evaluate(collectionSize) : this.right.value(collectionSize) ) );
			default: return null;
		}
	}

	/**
	 * Evaluate the left and right children and perform 
	 * the operation as specified by the associated token.
	 */
	@Override
	public boolean value(Entry<T, V> value) throws JParseException{
		// The value is obtained by evaluating the left and right
		// operands and combining the values with the operator.
		
		Token x = left.token();
		Token y = right.token();
		/**
		 * TODO - Code to do something when a binary operator is found.
		 */
		char c = this.token.getToken().charAt(0);
		//System.out.println(x.getToken() + " & " + y.getToken());
		switch(c)
		{
			case '&': return (this.left.isLeaf() ? x.evaluate(value) : this.left.value(value)) && 
					(this.right.isLeaf() ? y.evaluate(value) : this.right.value(value));
			case '|': return (this.left.isLeaf() ? x.evaluate(value) : this.left.value(value)) || 
					(this.right.isLeaf() ? y.evaluate(value) : this.right.value(value));
			default: return false;
		}			
	}
	
	/**
	 * Evaluate the left and right children and perform 
	 * the operation as specified by the associated token.
	 */
	@Override
	public Object getValue(Object object) throws JParseException {		
		// The value is obtained by evaluating the left and right
		// operands and combining the values with the operator.
		Token x = left.token();
		Token y = right.token();
		/**
		 * TODO - Code to do something when a binary operator is found.
		 */
		char c = this.token.getToken().charAt(0);
		//System.out.println(x.getToken() + " & " + y.getToken());
		switch(c)
		{
			case '&': return (this.left.isLeaf() ? x.evaluate(object) : this.left.value((T)object)) && 
					(this.right.isLeaf() ? y.evaluate(object) : this.right.value((T)object));
			case '|': return (this.left.isLeaf() ? x.evaluate(object) : this.left.value((T)object)) || 
					(this.right.isLeaf() ? y.evaluate(object) : this.right.value((T)object));
			default: return false;
		}	
	}
}

