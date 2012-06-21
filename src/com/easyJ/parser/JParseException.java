/**
 * 
 */
package com.easyJ.parser;

/**
 * Exception for query parser.
 * @author khan.m
 *
 */
public class JParseException extends Exception {

	private String expression;

	/**
	 * 
	 * @param message
	 */
	public JParseException(String message){
		super(message);
	}
	
	/**
	 * 
	 * @param message
	 * @param expression
	 */
	public JParseException(String message, String expression){
		super(message);
		this.expression = expression;
	}
	
	/**
	 * 
	 * @param e
	 * @param expression
	 */
	public JParseException(Exception e, String expression)
	{
		super(e);
		this.expression = expression;
	}
	
	public JParseException(Exception e)
	{
		super(e);
	}
	
	public JParseException(String message, Exception e)
	{
		super(message, e);
	}
	/**
	 * @return the expression
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * @param expression the expression to set
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}	
}
