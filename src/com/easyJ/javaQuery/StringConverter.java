/**
 * 
 */
package com.easyJ.javaQuery;

/**
 * Interface used by the toDSV method.
 * @author khan.m
 *
 */
public interface StringConverter<T> {
	/**
	 * Convert an object to string.
	 * @param t
	 * @return
	 */
	public String convertToStr(T t);
}
