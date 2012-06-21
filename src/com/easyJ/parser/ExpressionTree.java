package com.easyJ.parser;

import java.util.Map;

/*
 This program reads standard expressions typed in by the user.
 The program constructs an expression tree to represent the
 exprssion.  It then prints the value of the tree.  It also uses
 the tree to print out a list of commands that could be used
 on a stack machine to evaluate the expresion.
 The expressions can use positive real numbers and
 the binary operators +, -, *, and /.  The unary minus operation
 is supported.  The expressions are defined by the BNF rules:

 <expression>  ::=  [ "-" ] <term> [ [ "+" | "-" ] <term> ]...
 <term>  ::=  <factor> [ [ "*" | "/" ] <factor> ]...
 <factor>  ::=  <number>  |  "(" <expression> ")"
 A number must begin with a digit (i.e., not a decimal point).
 A line of input must contain exactly one such expression.  If extra
 data is found on a line after an expression has been read, it is
 considered an error.
 In addtion to the main program class, SimpleParser3, this program
 defines a set of four classes for implementing expression trees.
 (I know that it's not very good style to have them here...)
 */

public class ExpressionTree<T, V> {

	Parser parser;
	Tokenizer tokens;
	ExpNode<T,V> exp;
	
	static class ParseError extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		// Represents a syntax error found in the user's input.
		ParseError(String message) {
			super(message);
		}
	} // end nested class ParseError
	
	public ExpressionTree(String expression) throws Exception
	{	
		parser = new Parser(expression);
		tokens = new Tokenizer(parser);			
	}		
	
	/**
	 * Build the expression tree.
	 * @return
	 * @throws Exception
	 */
	public ExpressionTree<T, V> compile() throws Exception
	{
		exp = expressionTree();		
		return this;
	}
	
	/**
	 * Evaluate a Map object's Entry.
	 * @param object
	 * @return
	 * @throws JParseException
	 */
	public boolean evaluate(Map.Entry<T, V> object) throws JParseException
	{						
		return exp.value(object);
	}
	
	/**
	 * Evaluate an object.
	 * @param object
	 * @return
	 * @throws JParseException
	 */
	public boolean evaluate(T object) throws JParseException
	{						
		return exp.value(object);
	}
	
	/**
	 * Evaluate a collection.
	 * @param collectionSize
	 * @return
	 * @throws JParseException
	 */
	public int[] evaluate(int collectionSize) throws JParseException
	{
		return this.exp.value(collectionSize);
	}
	
	/**
	 * Get the value of the propery of the object.
	 * @param object
	 * @return
	 * @throws JParseException
	 */
	public Object getValue(Object object) throws JParseException{
		return this.exp.getValue(object);
	}
	
	/**
	 * Method to build the expression tree using recursive descent parser technique.
	 * Handles the OR condition.
	 * @return
	 * @throws Exception
	 */
	private ExpNode<T,V> expressionTree() throws Exception {	
		
		ExpNode<T,V> exp; // The expression tree for the expression.
		exp = termTree(); // Start with the first term.
		
		if( !tokens.hasNext() ) return exp;
		
		Token token = tokens.next();
		//System.out.println("expression tree token => " + token.getToken());
		if( token != null && token.getToken().equals("|") ) {
			// Read the next term and combine it with the
			// previous terms into a bigger expression tree.
			while( token != null && token.getToken().equals("|") )
			{
				ExpNode<T,V> nextTerm = termTree();
				exp = new BinOpNode<T,V>(token, exp, nextTerm);
				token = tokens.hasNext() ? tokens.next() : null;
			}
		}
		
		if( token != null )
			tokens.goBack(1);
		return exp;
	} // end expressionTree()

	/**
	 * Method to build the expression tree using recursive descent parser technique.
	 * Handles the AND condition.
	 * @return
	 * @throws Exception
	 */
	private ExpNode<T,V> termTree() throws Exception {

		ExpNode<T,V> term; // The expression tree representing the term.		
		term = factorTree();
		
		if( !tokens.hasNext() ) return term;
		Token token = tokens.next();
		//System.out.println("term tree token => " + token.getToken());
		if( token != null && token.getToken().equals("&") ) {			
			// Read the next factor, and combine it with the
			// previous factors into a bigger expression tree.
			while( token != null && token.getToken().equals("&") )
			{
				ExpNode<T,V> nextFactor = factorTree();
				term = new BinOpNode<T,V>(token, term, nextFactor);		
				token = tokens.hasNext() ? tokens.next() : null;
			}
		}
		
		if( token != null )
			tokens.goBack(1);
		
		return term;
	} // end termValue()

	/**
	 * Constructs the appropriate node object based on the type
	 * of the expression.
	 * @return
	 * @throws Exception
	 */
	private ExpNode<T,V> factorTree() throws Exception {
		
		if( !tokens.hasNext() ) return null;
		Token token = tokens.next();
		//System.out.println("factor tree token => " + token.getToken());
		if( token == null ) return null;
		
		if ( token.getTokentype() == Types.J_EXPRESSION || 
				token.getTokentype() == Types.OGNL_EXPRESSION ) {
			// The factor is a number. Return a ConstNode.			
			return new ConstNode<T,V>(token);
		}
		else if ( token.getTokentype() == Types.DELIMITER && 
					token.getToken().equals("(") ) {
			// The factor is an expression in parentheses.			
			ExpNode<T,V> exp = expressionTree();
			
			if ( !(token = tokens.next()).getToken().equals(")") )
				throw new ParseError("Missing right parenthesis.");			
			return exp;
		}
		else if ( token == null )
			throw new ParseError(
					"End-of-line encountered in the middle of an expression.");
		else if ( token.getToken().equals(")") )
			throw new ParseError("Extra right parenthesis.");
		else if ( token.getToken().equals("&") || token.getToken().equals("|") )
			throw new ParseError("Misplaced operator.");
		else
			throw new ParseError("Unexpected character \"" + token.getToken()
					+ "\" encountered.");

	} // end factorTree()
} // end class SimpleParser3