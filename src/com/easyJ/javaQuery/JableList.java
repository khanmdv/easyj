/**
 * 
 */
package com.easyJ.javaQuery;

import java.util.List;

import com.easyJ.util.JException;

/**
 * Interface to expose methods to work on List interface.
 * @author khan.m
 *
 */
public interface JableList<T> extends Jable{
	
	/**
	 * Filters the List by applying the query on all
	 * the objects of the List. The query results in either
	 * TRUE or FALSE. Only objects with TRUE are selected.
	 * @param query
	 * @return JableList<T>
	 * @throws JException
	 */
	public JableList<T> find(String query) throws JException;
	
	/**
	 * Works the opposite of "find" method.Only objects with FALSE are selected.
	 * @param expression
	 * @return JableList<T>
	 * @throws JException
	 */
	public JableList<T> not(String expression) throws JException;
	
	/**
	 * Reduce the set of matched elements to those that match the expression.
	 * Example of expression - :first, :last, :even, :odd
	 * @param expression
	 * @return JableList<T>
	 * @throws JException
	 */
	public JableList<T> filter(String expression) throws JException;
	
	/**
	 * Filter List by specifying list indices.
	 * Example - "1-5", "1,4,5"
	 * @param expression
	 * @return JableList<T>
	 * @throws JException
	 */
	public JableList<T> get(String expression) throws JException;
	
	/**
	 * Remove the set of matched elements from the List.
	 * @param expression
	 * @return JableList<T>
	 * @throws JException
	 */
	public JableList<T> remove(String expression) throws JException;
	
	/**
	 * Group the List elements into a Map. The expressions is 
	 * a list of columns/fields by which the grouping should be done.
	 * Based on the number of columns the Map will be deepened.
	 * @param expression
	 * @return JableMap<Object, Object>
	 * @throws JException
	 */
	public JableMap<Object, Object> groupBy(String expression) throws JException;
	
	/**
	 * Convert the List elements into a Map. The expression will
	 * be executed on each object of the list and the result will be
	 * used as a key of the Map.
	 * @param expression
	 * @return JableMap<Object, T>
	 * @throws JException
	 */
	public JableMap<Object, T> toMap(String expression) throws JException;
	
	/**
	 * Returns a unique list of objects of the List satisfying the expression.
	 * @param expression
	 * @return JableList<T>
	 * @throws JException
	 */
	public JableList<T> unique(String expression) throws JException;
	
	/**
	 * 
	 * Returns a unique list of objects of the List.
	 * @return JableList<T>
	 * @throws JException
	 */
	public JableList<T> unique() throws JException;
	
	/**
	 * Executes the expression on every object of the List and returns
	 * a List of the output.
	 * @param expression
	 * @return JableList<Object> 
	 * @throws JException
	 */
	public JableList<Object> project(String expression) throws JException;
	
	/**
	 * Compare two Lists.
	 * @param expression
	 * @return boolean
	 * @throws JException
	 */
	public boolean isEqual2(List<T> collection2) throws JException;
	
	/**
	 * Returns the actual List implementation.
	 * @return List<T>
	 */
	public List<T> list();
	
	/**
	 * Executes the function "execute" for each object. 
	 * @param executor
	 * @return JableList<T>
	 * @throws JException
	 */
	public JableList<T> each(ListExecutor<T> executor) throws JException;
	
	/**
	 * Converts the objects of the List into a Delimiter Separated String.
	 * @param delimiter
	 * @return String
	 */
	public String toDSV(String delimiter) throws JException;
	
	/**
	 * Converts the objects of the List into a Delimiter Separated String 
	 * using a type converter which works as toString method.
	 * @param delimiter
	 * @param converter
	 * @return String
	 */
	public String toDSV(String delimiter, StringConverter<T> converter) throws JException;
	
	/**
	 * Converts a Delimiter Separated String to a List using a Object Converter.
	 * @param dsv
	 * @param delimiter
	 * @return JableList<T>
	 */
	public JableList<T> toListFromDSV(String dsv, String delimiter, ObjectConverter<T> converter);	
	
	/**
	 * Searches for a given pattern inside every object of the List.
	 * @param expression
	 * @return JableList<T>
	 * @throws JException
	 */
	public JableList<T> grep(String regex) throws JException;
	
	/**
	 * Sets the property of each object to the value.
	 * @param expression
	 * @return JableList<T>
	 * @throws JException
	 */
	public JableList<T> set(String property, Object value) throws JException;
}
