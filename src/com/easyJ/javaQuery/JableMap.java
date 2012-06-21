/**
 * 
 */
package com.easyJ.javaQuery;

import java.util.Map;

import com.easyJ.util.JException;

/**
 * Interface to expose methods to work on Map interface.
 * @author khan.m
 *
 */
public interface JableMap<T, V> extends Jable{
	/**
	 * Filters the Map by applying the query on all
	 * the entries(Map.Entry) of the Map. The query results in either
	 * TRUE or FALSE. Only objects with TRUE are selected.
	 * @param query
	 * @return
	 * @throws JException
	 */
	public JableMap<T, V> find(String query) throws JException;
	
	/**
	 * Works the opposite of "find" method.Only objects with FALSE are selected.
	 * @param expression
	 * @return
	 * @throws JException
	 */
	public JableMap<T, V> not(String expression) throws JException;
	
	/**
	 * Filter Map by specifying map indices.
	 * Example - "1-5", "1,4,5"
	 * @param expression
	 * @return
	 * @throws JException
	 */
	public JableMap<T, V> get(String expression) throws JException;
	
	/**
	 * Remove the set of matched elements from the List.
	 * @param expression
	 * @return
	 * @throws JException
	 */
	public JableMap<T, V> remove(String expression) throws JException;
	
	/**
	 * Convert the Map entries into a List after evaluating the expression
	 * on each entry  of the map.
	 * @param expression
	 * @return
	 * @throws JException
	 */
	public JableList<Object> toList(String expression) throws JException;
	
	/**
	 * Executes the expression on every object of the Map and returns
	 * a List of the output.
	 * @param expression
	 * @return
	 * @throws JException
	 */
	public JableList<Object> project(String expression) throws JException;
	
	/**
	 * Compare two maps.
	 * @param expression
	 * @return
	 * @throws JException
	 */
	public boolean isEqual2(Map<T, V> map2) throws JException;
	
	/**
	 * Return the underlying Map implementation.
	 * @return
	 */
	public Map<T, V> map();	
	
	/**
	 * Executes the function "execute" for each object. 
	 * @param executor
	 * @return
	 * @throws JException
	 */
	public JableMap<T, V> each(MapExecutor<T, V> executor)  throws JException;
		
	/**
	 * Searches for a given pattern inside every entry of the Map.
	 * @param expression
	 * @return
	 * @throws JException
	 */
	public JableMap<T, V> grep(String expression) throws JException;
	
	/**
	 * Sets the property of each entry to the value.
	 * @param expression
	 * @return
	 * @throws JException
	 */
	public JableMap<T, V> set(String property, Object value) throws JException;
}
