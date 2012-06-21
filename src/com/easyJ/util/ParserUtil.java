/**
 * 
 */
package com.easyJ.util;

/**
 * Utility class to help with parsing.
 * @author khanm
 *
 */
public class ParserUtil {
	
	/**
	 * Returns if a char is a space.
	 * @param c
	 * @return
	 */
	public static boolean isSpace(char c){
		return c == ' ';
	}
	
	/**
	 * Returns if a char is a digit.
	 * @param c
	 * @return
	 */
	public static boolean isDigit(char c){
		return (c + "").matches("\\d");
	}
	
	/**
	 * Returns if a char is an alphabet.
	 * @param c
	 * @return
	 */
	public static boolean isAlphabet(char c){
		return (c + "").toLowerCase().matches("[a-z]");
	}
	
	/**
	 * Returns if a char is a delimiter.
	 * @param c
	 * @return
	 */
	public static boolean isDelim(char c){
		return "&|()".indexOf(c) >= 0;
	}
	
	/**
	 * Returns if a char is a escape char("\")
	 * @param c
	 * @return
	 */
	public static boolean isEscapeChar(char c){
		return c == '\\';
	}
	
	/**
	 * Returns if a char is a JExpression delimiter. [ and ];
	 * @param c
	 * @return
	 */
	public static boolean isJDelim(char c){
		return "[]".indexOf(c) >= 0;
	}
	
	/**
	 * Returns if a char is OGNL delimiter. { and };
	 * @param c
	 * @return
	 */
	public static boolean isOgnlDelim(char c){
		return "{}".indexOf(c) >= 0;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}