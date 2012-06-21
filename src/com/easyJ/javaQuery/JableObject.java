/**
 * 
 */
package com.easyJ.javaQuery;

import java.lang.reflect.InvocationTargetException;

import com.easyJ.util.JException;

/**
 * Interface to expose methods to work on an Object.
 * @author khan.m
 *
 */
public interface JableObject<T> {
	
	/**
	 * Checks if the class of the object implements any of the specified 
	 * interfaces. 
	 * @param interfaze
	 * @return
	 * @throws JException
	 */
	public boolean doesImplementAny(String interfazes) throws JException;
	
	/**
	 * Checks if the class of the object implements all of the specified 
	 * interfaces. 
	 * @param interfaze
	 * @return
	 * @throws JException
	 */
	public boolean doesImplementAll(String interfazes) throws JException;
	
	/**
	 * Checks if the class of the object has the specified 
	 * method. 
	 * @param method
	 * @return
	 * @throws JException
	 */
	public boolean hasMethod(String method) throws JException;
	
	/**
	 * Checks if the class of the object has the specified 
	 * property. It requires the getter of the property to be available.
	 * @param property
	 * @return
	 * @throws JException
	 */
	public boolean hasProperty(String property) throws JException;
	
	/**
	 * Invokes the specified method of the object.
	 * @param method
	 * @param args
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public JableObject<T> call(String method, Object[] args) 
	throws JException;
	
	/**
	 * Copies the properties from the obj to the Object.
	 * @param obj
	 * @param properties
	 * @return
	 */
	public JableObject<T> copy(T obj, String properties) throws JException;
	
	/**
	 * Sets the Object's property to the value.
	 * @param property
	 * @param value
	 * @return
	 * @throws JException
	 */
	public JableObject<T> set(String property, Object value) throws JException;
	
	/**
	 * Searches the pattern in the properties of the Object.
	 * @param regex
	 * @param value
	 * @return
	 * @throws JException
	 */
	public String grep(String regex) throws JException;
	
	/**
	 * Converts the properties to name1=value1,name2=value2... string.
	 * @return
	 * @throws IllegalAccessException
	 */
	public String serializeProps() throws JException;
	
	/**
	 * Returns the Object itself.
	 * @return
	 */
	public T me();
}
