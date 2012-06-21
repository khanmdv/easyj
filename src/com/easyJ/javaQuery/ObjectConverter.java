/**
 * 
 */
package com.easyJ.javaQuery;

/**
 * Interface used by the toListFromDSV method.
 * @author khan.m
 *
 */
public interface ObjectConverter<T> {
	/**
	 * Converts a String back to an object.
	 * @param str
	 * @return
	 */
	public T convertFromStr(String str);
}
