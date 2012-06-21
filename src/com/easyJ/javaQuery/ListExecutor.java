/**
 * 
 */
package com.easyJ.javaQuery;

/**
 * Interface used by the "each" function.
 * @author khan.m
 *
 */
public interface ListExecutor<T> {
	/**
	 * Method used by the "each" function.
	 * @param t
	 * @return
	 */
	public boolean execute(T t);
}
