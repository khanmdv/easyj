/**
 * 
 */
package com.easyJ.util;

/**
 * Exception class to differentiate easyJ exceptions.
 * @author khan.m
 *
 */
public class JException extends Exception {
	
	public JException(String message){
		super(message);
	}
	
	public JException(Exception e)
	{
		super(e);
	}
	
	public JException(String message, Exception e)
	{
		super(message, e);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
