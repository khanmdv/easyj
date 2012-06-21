/**
 * J.java
 */
package com.easyJ.javaQuery;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.easyJ.util.Dir;
import com.easyJ.util.JException;

/**
 * Wrapper class to return the easy query implementation of the
 * collection/object. 
 * This is similar to jQuery's $ function. In this case, 
 * the $ function belongs to the class J. The $ function  
 * is overloaded to accept various collections/objects.
 * The library supports the following classes.
 * <ul>
 * 	<li>java.util.List<T></li>
 * 	<li>java.util.Map<T, V></li>
 * 	<li>java.lang.Object<T></li>
 * 	<li>java.io.File</li>
 * </ul>
 * @author khan.m
 *
 */
public class J {

	/**
	 * By default the map implementation is chosen as HashMap. This
	 * can be overridden by the user to corresponding implementation.
	 */
	public static String mapImpl = "java.util.HashMap";
	
	/**
	 * Method to return an object of the chosen map implementation.
	 * Returns java.util.HashMap by default.
	 * @param <T>
	 * @param <V>
	 * @return Implementation of the Map interface.
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@SuppressWarnings("unchecked")
	public static <T, V> Map<T, V> getMapImpl()
	throws ClassNotFoundException, IllegalAccessException, InstantiationException{
		synchronized(J.mapImpl)	{
			return (Map<T, V>)Class.forName(J.mapImpl).newInstance();
		}
	}
	
	/**
	 * Access method to return the easy query object for List Interface.
	 * @param <T>
	 * @param list
	 * @return JableList<T>
	 */
	public static <T> JableList<T> $(List<T> list)
	{
		return new JQList<T>(list);
	}
	
	/**
	 * Access method to return the easy query object for Map Interface.
	 * @param <T>
	 * @param <V>
	 * @param map
	 * @return JableMap<T, V>
	 */
	public static <T, V> JableMap<T, V> $(Map<T, V> map)
	{
		return new JQMap<T, V>(map);
	}
	
	/**
	 * Access method to return the easy query object for Object.
	 * @param <T>
	 * @param t
	 * @return JableObject<T>
	 */
	public static <T> JableObject<T> $(T t)
	{
		return new JQObject<T>(t);
	}
	
	/**
	 * Access method to return the easy query object for File objects.
	 * @param file
	 * @return JableFile
	 */
	public static JableFile $(File file){
		return new JQFile(file);
	}
	
	/**
	 * Access method to return the easy query object for Dir objects. 
	 * <b>Note:</b>
	 * There is no Dir class provided by the java library. Dir class wraps 
	 * java.io.File object internally. 
	 * @param file
	 * @return JableDir
	 */
	public static JableDir $(Dir file){		
		return new JQDir(file.getDir());
	}
	
	/**
	 * Access method to return the easy query object for Arrays.
	 * @param <T>
	 * @param array
	 * @return JableList<T>
	 */
	public static <T> JableList<T> newlist(T[] array)
	{
		if( array == null )
			return new JQList<T>(new ArrayList<T>());
		
		List<T> list = new ArrayList<T>(array.length);
		
		for( T obj : array )
			list.add(obj);
		
		return new JQList<T>(list);
	}
	
	/**
	 * Access method to return the easy query object for primitive int type.
	 * @param array
	 * @return JableList<Integer>
	 */
	public static JableList<Integer> newlist(int[] array)
	{
		if( array == null )
			return new JQList<Integer>(new ArrayList<Integer>());
		
		List<Integer> list = new ArrayList<Integer>(array.length);
		
		for( Integer obj : array )
			list.add(obj);
		
		return new JQList<Integer>(list);
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws JException{
		try
		{
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("a", 1);
		map.put("b", 2);
		map.put("c", 3);
		
		System.out.println( J.$(map).toList("value * 3").list() );
		}catch(Exception e)
		{
			System.out.println("Error:");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}		
	}
}
