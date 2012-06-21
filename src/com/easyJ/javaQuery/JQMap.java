/**
 * 
 */
package com.easyJ.javaQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.easyJ.ognl.OgnlExpression;
import com.easyJ.parser.ExpressionTree;
import com.easyJ.util.JException;

/**
 * @author khan.m
 *
 */
public class JQMap<T, V> implements JableMap<T, V> {
	
	private Map<T, V> map;
	
	public JQMap(Map<T, V> map)
	{
		this.map = map;
	}

	/**
	 * @see com.easyJ.javaQuery.JableMap#each(com.easyJ.javaQuery.MapExecutor)
	 */
	@Override
	public JableMap<T, V> each(MapExecutor<T, V> executor) throws JException {
		try
		{
			Map<T, V> result = J.getMapImpl();
			for( Map.Entry<T, V> entry : this.map.entrySet() )
			{				
				if( executor.execute(entry) )
					result.put(entry.getKey(), entry.getValue());
			}
			return new JQMap<T, V>(result);
		}catch(Exception e)
		{
			throw new JException("Error Eaching.", e);
		}
	}

	/**
	 * @see com.easyJ.javaQuery.JableMap#equals(java.util.Map)
	 */
	@Override
	public boolean isEqual2(Map<T, V> map2) throws JException {
		boolean result = true;		
		Iterator<T> itr;
		try{
			for(itr = this.map.keySet().iterator(); itr.hasNext() ;)
			{
				T key1 = itr.next();
				result &= ( map2.keySet().contains(key1) && this.map.get(key1).equals( map2.get(key1) ) );	
				if( map2.keySet().contains(key1) )
					map2.remove(key1);
			}
			if (itr.hasNext() || map2.size() > 0) result = false;
			return result;
		}catch(Exception ex2){
			throw new JException("Cannot test equality:", ex2);
		}
	}	

	/**
	 * @see com.easyJ.javaQuery.JableMap#find(java.lang.String)
	 */
	@Override
	public JableMap<T, V> find(String query) throws JException {
		ExpressionTree<T, V> etree = null;
		try{			
			etree = (new ExpressionTree<T, V>(query)).compile();		
		}catch(Exception ex){
			throw new JException("Invalid Expression: " + query, ex);
		}
		Map<T, V> result = null;		
		try{
			result = J.getMapImpl();
			
			Iterator<Map.Entry<T, V>> itr = this.map.entrySet().iterator();			
			while( itr.hasNext() ){
				Map.Entry<T, V> t = itr.next();
				if( etree.evaluate(t) )
					result.put(t.getKey(), t.getValue());
			}				
		}catch(Exception ex2){
			throw new JException("Cannot find:", ex2);
		}
		return new JQMap<T,V>(result);
	}

	/**
	 * @see com.easyJ.javaQuery.JableMap#get(java.lang.String)
	 */
	@Override
	public JableMap<T, V> get(String expression) throws JException {
		ExpressionTree<T, V> etree = null;
		try{			
			etree = (new ExpressionTree<T, V>(expression)).compile();	
		}catch(Exception ex){
			throw new JException("Invalid Expression: " + expression, ex);
		}
		Map<T, V> result = null;
		
		try{			
			int[] indices = etree.evaluate(this.map.size());
			result = J.getMapImpl();
			List<Integer> indicesList = J.newlist(indices).list();
			int i = 0;			
			for(Iterator<Map.Entry<T, V>> itr = this.map.entrySet().iterator(); itr.hasNext();i++)
			{
				Map.Entry<T, V> t = itr.next();
				if( indicesList.contains(i) )				
					result.put(t.getKey(), t.getValue());						
			}
		}catch(Exception ex2){
			throw new JException("Cannot get:", ex2);
		}
		return new JQMap<T,V>(result);
	}

	/* (non-Javadoc)
	 * @see com.krazylib.javaQuery.JableMap#grep(java.lang.String)
	 */
	@Override
	public JableMap<T, V> grep(String regex) throws JException {
		Map<T, V> result = null;
		
		try{
			result = J.getMapImpl();
			
			Pattern pattern = Pattern.compile(regex);
			for( Iterator<Map.Entry<T, V>> itr = this.map.entrySet().iterator(); itr.hasNext();)
			{
				Map.Entry<T, V> obj = itr.next();
				Matcher keym = pattern.matcher( J.$(obj.getKey()).serializeProps() );
				Matcher valuem = pattern.matcher( J.$(obj.getValue()).serializeProps() );
				if( keym.find() || valuem.find() )
					result.put(obj.getKey(), obj.getValue());
			}			
		}catch(Exception ex2){
			throw new JException("Cannot grep:", ex2);
		}
		return new JQMap<T,V>(result);
	}

	/* (non-Javadoc)
	 * @see com.krazylib.javaQuery.JableMap#map()
	 */
	@Override
	public Map<T, V> map() {		
		return this.map;
	}

	/* (non-Javadoc)
	 * @see com.krazylib.javaQuery.JableMap#not(java.lang.String)
	 */
	@Override
	public JableMap<T, V> not(String expression) throws JException {
		ExpressionTree<T, V> etree = null;
		try{			
			etree = (new ExpressionTree<T, V>(expression)).compile();		
		}catch(Exception ex){
			throw new JException("Invalid Expression: " + expression, ex);
		}
		Map<T, V> result = null;
		
		try{
			result = J.getMapImpl();
			
			Iterator<Map.Entry<T, V>> itr = this.map.entrySet().iterator();			
			while( itr.hasNext() ){
				Map.Entry<T, V> t = itr.next();
				if( !etree.evaluate(t) )
					result.put(t.getKey(), t.getValue());
			}				
		}catch(Exception ex2){
			throw new JException("Cannot negate:", ex2);
		}
		return new JQMap<T,V>(result);
	}

	/* (non-Javadoc)
	 * @see com.krazylib.javaQuery.JableMap#project(java.lang.String)
	 */
	@Override
	public JableList<Object> project(String expression) throws JException {
		List< Object> result = new ArrayList<Object>();
		ExpressionTree<T, Object> etree = null;
		try{			
			etree = (new ExpressionTree<T, Object>(expression)).compile();		
		}catch(Exception ex){
			throw new JException("Invalid Expression: " + expression, ex);
		}
		
		try
		{			
			for( Iterator<Map.Entry<T, V>> itr = this.map.entrySet().iterator(); itr.hasNext();)
			{
				Map.Entry<T, V> t = itr.next();
				result.add( etree.getValue(t) );
			}			
		}catch(Exception e)
		{
			throw new JException("Cannot project.", e);
		}
		return new JQList<Object>(result);
	}

	/* (non-Javadoc)
	 * @see com.krazylib.javaQuery.JableMap#remove(java.lang.String)
	 */
	@Override
	public JableMap<T, V> remove(String expression) throws JException {
		ExpressionTree<T, V> etree = null;
		try{			
			etree = (new ExpressionTree<T, V>(expression)).compile();	
		}catch(Exception ex){
			throw new JException("Invalid Expression: " + expression, ex);
		}
		Map<T, V> result = null;
		
		try{			
			int[] indices = etree.evaluate(this.map.size());
			result = J.getMapImpl();
			List<Integer> indicesList = J.newlist(indices).list();
			int i = -1;			
			for(Iterator<Map.Entry<T, V>> itr = this.map.entrySet().iterator(); itr.hasNext();i++)
			{
				Map.Entry<T, V> t = itr.next();
				if( (indicesList.size() != 0 && indicesList.contains(i++)) || 
						(indicesList.size() == 0 && etree.evaluate(t)) )
						itr.remove();
					else
						result.put(t.getKey(), t.getValue());						
			}
		}catch(Exception ex2){
			throw new JException("Cannot remove:", ex2);
		}
		return new JQMap<T,V>(result);
	}

	/* (non-Javadoc)
	 * @see com.krazylib.javaQuery.JableMap#set(java.lang.String, java.lang.Object)
	 */
	@Override
	public JableMap<T, V> set(String property, Object value) throws JException {
		try
		{
			for( Iterator<Map.Entry<T, V>> itr = this.map.entrySet().iterator(); itr.hasNext();)
			{
				Map.Entry<T, V> t = itr.next();
				OgnlExpression.setValue(property, t, value);
			}
		}catch(Exception ex2){
			throw new JException("Cannot set values:", ex2);
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see com.krazylib.javaQuery.JableMap#toList(java.lang.String)
	 */
	@Override
	public JableList<Object> toList(String expression) throws JException {
		List<Object> list = new ArrayList<Object>();
		ExpressionTree<T, V> etree = null;
		try{			
			etree = (new ExpressionTree<T, V>(expression)).compile();	
		}catch(Exception ex){
			throw new JException("Invalid Expression: " + expression, ex);
		}
		
		try{
			for( Iterator<Map.Entry<T, V>> itr = this.map.entrySet().iterator(); itr.hasNext();)
			{
				Map.Entry<T, V> t = itr.next();
				list.add( etree.getValue(t) );
			}
		}catch(Exception ex2){
			throw new JException("Cannot convert to list:", ex2);
		}
		return new JQList<Object>(list);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.map.size();
	}
}
