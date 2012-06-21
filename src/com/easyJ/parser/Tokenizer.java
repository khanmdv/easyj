/**
 * 
 */
package com.easyJ.parser;

import java.util.ArrayList;
import java.util.List;

import com.easyJ.util.JException;

/**
 * Class to move forward/backword with a list of tokens.
 * @author khan.m
 *
 */
public class Tokenizer {

	List<Token> tokens;
	int index;
	
	
	/**
	 * 
	 */
	public Tokenizer() {
		super();
		index = 0;
		this.tokens = new ArrayList<Token>(); 
	}

	/**
	 * 
	 */
	public Tokenizer(JParseable parser) throws Exception{		
		this();
		Token tok = null;
		while((tok = parser.nextToken()) != null )
			this.tokens.add(tok);
	}

	/**
	 * @param tokens
	 */
	public Tokenizer(List<Token> tokens) {
		this();
		this.tokens = tokens;
	}

	/**
	 * @param tokens the tokens to set
	 */
	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}
	
	/**
	 * Method to addd token
	 * @param token
	 */
	public void addToken(Token token){
		this.tokens.add(token);
	}
	
	/**
	 * Go back one token.
	 * @param num
	 * @throws Exception
	 */
	public void goBack(int num) throws Exception{
		if( (this.index - num) >= 0 )
			this.index = this.index - num;
		else
			throw new Exception("Cannot go back.");
	}
	
	/**
	 * Get the next token.
	 * @return
	 * @throws Exception
	 */
	public Token next() throws Exception
	{
		if( this.index >= 0 )
			return this.tokens.get(this.index++);
		else
			throw new JException("Cannot get token. Invalid index");
	}
	
	/**
	 * Returns true if there are tokens left to be iterated on.
	 * @return
	 */
	public boolean hasNext()
	{
		return (this.index >= 0) && (this.index < this.tokens.size());
	}	
	
	/**
	 * Reset the tokenizer.
	 */
	public void reset()
	{
		this.index = 0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}