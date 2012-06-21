/**
 * 
 */
package com.easyJ.javaQuery;

import java.util.Map;

/**
 * Method used by the "each" function.
 * @author khan.m
 *
 */
public interface MapExecutor<T, V> {
	/**
	 * Method used by the "each" function.
	 * @param entry
	 * @return
	 */
	public boolean execute(Map.Entry<T, V> entry); 
}
