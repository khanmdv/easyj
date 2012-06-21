/**
 * 
 */
package com.easyJ.ognl;

import ognl.OgnlContext;
import ognl.OgnlException;

import com.easyJ.parser.Evaluator;
import com.easyJ.parser.JParseException;
import com.easyJ.parser.Token;


/**
 * Class to compile and evaluate OGNL expressions.
 * @author khan.m
 *
 */
public class OgnlEvaluator<T,V> implements Evaluator<T,V> {

	/**
	 * 	@Override
	 */
	@Override
	public boolean evaluate(Token token, T object) {
		try
		{		
			OgnlExpression ognlex = new OgnlExpression(token.getToken());
			Object booleanObj = ognlex.getValue(new OgnlContext(), object);
			if( booleanObj instanceof Boolean )
				return (Boolean)booleanObj;
			else
				return false;
			
		}catch(OgnlException ognlexc)
		{
			System.out.println("Error while parsing");
			// throw your custom error here
		}
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] evaluate(Token token, int collectionSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValue(Token token, T object) throws JParseException {
		try
		{		
			OgnlExpression ognlex = new OgnlExpression(token.getToken());
			Object obj = ognlex.getValue(new OgnlContext(), object);
			return obj;			
		}catch(OgnlException ognlexc)
		{
			System.out.println("Error while parsing");
			// throw your custom error here
		}
		return null;
	}
}
