/**
 * 
 */
package com.easyJ.parser;

import java.util.ArrayList;

import com.easyJ.util.JException;
import com.easyJ.util.ParserUtil;

/**
 * Class to parse the query in to List of Token objects.
 * Collects the expressions enclosed within "{expr}" and 
 * between "[expr]" separated by logical operators "&" and "|".
 * 
 * Those expressions found enclosed within "{" and "}" are called
 * OGNL expressions and the ones within "[" and "]" are called JExpressions.
 * 
 * 
 * @author khanm
 *
 */
public class Parser implements JParseable{
	
	private String expression;
	private StringBuffer token;
	private Types tokType;
	private char[] chararr;
	private int index;
	
	
	public Parser(String expression) {
		super();
		this.expression = expression;
		this.chararr = this.expression.toCharArray();
	}
	
	/**
	 * Get the previous token's index.
	 * @return
	 */
	@Override
	public int getPrevIndex()
	{
		return (this.index <= 0 ? this.index : this.index - 1);
	}

	/**
	 * Returns the next token element.
	 * @return
	 * @throws JException
	 */
	@Override
	public Token nextToken() throws JException
	{
		char c;		
		token = new StringBuffer();		
		// Return if end of token
		if( this.index == this.chararr.length ) return null;
		
		// Ignore white spaces if any
		while( ParserUtil.isSpace(this.chararr[this.index]) ) this.index++;
		
		c = this.chararr[this.index];	
		                 
		if( ParserUtil.isDelim((c)) )
		{
			token.append(c);
			tokType = Types.DELIMITER;
			this.index++;
		}
		else if( ParserUtil.isOgnlDelim((c)) )
		{
			this.index++;			
			while( !ParserUtil.isOgnlDelim((c = this.chararr[this.index])) ||
					( ParserUtil.isOgnlDelim((c)) && 
					  ParserUtil.isEscapeChar(this.chararr[this.getPrevIndex()])) )
			{
				token.append(c);
				
				if ( (ParserUtil.isOgnlDelim((c)) && 
						  ParserUtil.isEscapeChar(this.chararr[this.getPrevIndex()])) )
					token.deleteCharAt(token.length() - 2);
				
				this.index++;
			}
			this.index++;
			tokType = Types.OGNL_EXPRESSION;
		}
		else if( ParserUtil.isJDelim((c)) )
		{
			this.index++;
			while( !ParserUtil.isJDelim((c = this.chararr[this.index])) ||
					( ParserUtil.isJDelim((c)) && 
					  ParserUtil.isEscapeChar(this.chararr[this.getPrevIndex()])) )
			{
				token.append(c);
				
				if ( (ParserUtil.isJDelim((c)) && 
						  ParserUtil.isEscapeChar(this.chararr[this.getPrevIndex()])) )
					token.deleteCharAt(token.length() - 2);
				
				this.index++;
			}
			this.index++;
			tokType = Types.J_EXPRESSION;
		}
		else
			throw new JException("Unrecognized operator/delimiter '" + c + "'");
		
		return new Token(token.toString(), tokType);
	}
	
	/**
	 * Method returns true if the parser has anymore tokens available.
	 * @return
	 */
	@Override
	public boolean hasMoreTokens() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * 
	 */
	public String getToken()
	{
		return token.toString();
	}
	
	/**
	 * 
	 */
	public String getTokenType()
	{
		return this.tokType.toString();
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Parser parser = new Parser(args[0]);
		ArrayList<Token> tokens = new ArrayList<Token>();
		
		try
		{
			Token token = null;
			while( (token = parser.nextToken()) != null )
			{			
				tokens.add(token);
			}
		}catch(Exception ee)
		{
			System.out.println("Error -> " + ee.getMessage());
		}
		
	}	

	
}
