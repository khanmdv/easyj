/**
 * 
 */
package com.easyJ.parser;

/**
 * Class represents a token of the query. A token can be of only two types.
 * OGNL Expression Type: 
 * 		This type of token is enclosed within "{" and "}" brackets. This 
 * 		can be any OGNL expression. Please find the OGNL 
 * 		documentation at http://www.opensymphony.com/ognl/index.html
 * 
 * JExpression Type:
 * 		This type of token is enclosed within "[" and "]" brackets. 
 * 		JExpressions are used to filter objects on the basis of the 
 * 		class extended, interfaces implemented and other files related
 * 		queries.
 * @author khanm
 *
 */
public class Token{

	private String token;
	private Types tokentype;
	private Evaluator evaluator;	
	
	/**
	 * 
	 * @param token
	 * @param tokentype
	 */
	public Token(String token, Types tokentype) {
		super();
		this.token = token;
		this.tokentype = tokentype;
		this.evaluator = EvaluatorFactory.getEvaluator(this.tokentype);
	}	

	/**
	 * Evaluate the token on an object.
	 * @param object
	 * @return
	 * @throws JParseException
	 */
	public boolean evaluate(Object object) throws JParseException
	{		
		return this.evaluator.evaluate(this, object);
	}
	
	/**
	 * Evaluate the token on a collection.
	 * @param collectionSize
	 * @return
	 * @throws JParseException
	 */
	public int[] evaluate(int collectionSize) throws JParseException
	{
		return this.evaluator.evaluate(this, collectionSize);
	}
	
	/**
	 * Get the output by evaluating the query on the object.
	 * @param object
	 * @return
	 * @throws JParseException
	 */
	public Object getValue(Object object) throws JParseException
	{		
		return this.evaluator.getValue(this, object);
	}

	/**
	 * 
	 * @return
	 */
	public String getToken() {
		return token;
	}

	/**
	 * 
	 * @param token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 
	 * @return
	 */
	public Types getTokentype() {
		return tokentype;
	}

	/**
	 * 
	 * @param tokentype
	 */
	public void setTokentype(Types tokentype) {
		this.tokentype = tokentype;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
