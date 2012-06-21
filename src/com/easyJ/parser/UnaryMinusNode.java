/**
 * 
 */
package com.easyJ.parser;

import java.util.Map.Entry;

/**
 * @author khan.m
 *
 */
class UnaryMinusNode<T, V> extends ExpNode<T, V> {
	// An expression node to represent a unary minus operator.
	ExpNode<T, V> operand; // The operand to which the unary minus applies.
	
	UnaryMinusNode(ExpNode<T,V> operand) {
		// Construct a UnaryMinusNode with the specified operand.
		this.operand = operand;
	}
	
	@Override
	Token token() {
		// The value is the negative of the value of the operand.
		return null;
	}
	
	@Override
	public boolean value(T value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int[] value(int collectionSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean value(Entry<T, V> value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getValue(Object object) throws JParseException {
		// TODO Auto-generated method stub
		return null;
	}
}
