/**
 * 
 */
package com.easyJ.util;

import java.util.HashSet;
import java.util.Set;

/**
 * General utility class.
 * @author khan.m
 *
 */
public class Util {
	
	/**
	 * "#this" can be used in OGNL expression referring to self objects.
	 * @param expr
	 * @return
	 */
	public static boolean isThis(String expr)
	{
		return expr.trim().equals("#this");
	}

	/**
	 * Union operation on two arrays.
	 * @param A
	 * @param B
	 * @return
	 */
	public static int[] union(int[] A, int[] B)
	{
		Set<Integer> result = new HashSet<Integer>();
		for(int i : A) result.add(i);
		for(int i : B) result.add(i);
		int[] arr = new int[result.size()];
		int index = -1;
		for(Integer i : result) arr[index++] = i.intValue();			
		return arr;
	}
	
	/**
	 * Intersection operation on two arrays.
	 * @param A
	 * @param B
	 * @return
	 */
	public static int[] intersect(int[] A, int[] B)
	{
		Set<Integer> temp = new HashSet<Integer>();
		Set<Integer> result = new HashSet<Integer>();
		if( A.length >= B.length)
		{
			for(int i : A) temp.add(i);
			for(int i : B)			
				if( temp.contains(i) )
					result.add(i);			
		}
		else
		{
			for(int i : B) temp.add(i);
			for(int i : A)			
				if( temp.contains(i) )
					result.add(i);
		}
		
		int[] arr = new int[result.size()];
		int index = -1;
		for(Integer i : result) arr[index++] = i.intValue();			
		return arr;
	}
	
	/**
	 * Method to reverse  a string.
	 * @param str
	 * @return
	 */
	public static String reverseStr(String str)
	{
		StringBuffer result = new StringBuffer();
		for( int i = str.length() - 1; i >= 0; i--)
			result.append(str.charAt(i));
		return result.toString();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
