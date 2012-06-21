/**
 * 
 */
package com.easyJ.parser;

import com.easyJ.j.JEvaluator;
import com.easyJ.ognl.OgnlEvaluator;

/**
 * Factory to create either OGNL or JExpression evaluator.
 * @author khan.m
 *
 */
public class EvaluatorFactory {
	
	/**
	 * Factory method.
	 * @param tokType
	 * @return
	 */
	public static Evaluator getEvaluator(Types tokType)
	{
		if( tokType == Types.J_EXPRESSION )
		{
			return new JEvaluator();
		}
		else if( tokType == Types.OGNL_EXPRESSION )
		{
			return new OgnlEvaluator();
		}
		else
			return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
