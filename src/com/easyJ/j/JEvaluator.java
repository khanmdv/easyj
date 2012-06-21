/**
 * 
 */
package com.easyJ.j;

import com.easyJ.parser.Evaluator;
import com.easyJ.parser.JParseException;
import com.easyJ.parser.Token;

/**
 * @author khan.m
 *
 */
public class JEvaluator<T, V> implements Evaluator<T,V> {
	/**
	 * @throws Exception 
	 * 
	 */
	@Override
	public boolean evaluate(Token token, T object) throws JParseException{
		Expression<T, Object> jp = new JExpression<T, Object>();
		jp.compile(token.getToken());
		return jp.matches(object);
	}

	@Override
	public int[] evaluate(Token token, int collectionSize) throws JParseException{
		Expression<T, Object> jp = new JExpression<T, Object>();
		jp.compile(token.getToken());
		return jp.matches(collectionSize);
	}

	@Override
	public Object getValue(Token token, T object) throws JParseException {
		// TODO Auto-generated method stub
		return null;
	}
}
