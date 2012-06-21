/**
 * 
 */
package com.easyJ.javaQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.easyJ.ognl.OgnlExpression;
import com.easyJ.parser.ExpressionTree;
import com.easyJ.util.JException;

/**
 * Implementation of the JableList interface. Default implementation of List is 
 * java.util.ArrayList
 * @author khan.m
 *
 */
public class JQList<T> implements JableList<T> {
	
	private List<T> list;
	
	public JQList(List<T> list)
	{
		this.list = list;
	}
	
	@Override
	public JableList<T> each(ListExecutor<T> executor) throws JException {
		try
		{
			List<T> result = new ArrayList<T>();
			for( Iterator<T> itr = this.list.iterator(); itr.hasNext();)
			{
				T object = itr.next();
				if( executor.execute(object) )
					result.add(object);
			}
			return new JQList<T>(result);
		}catch(Exception e)
		{
			throw new JException("Error Eaching.", e);
		}
	}

	@Override
	public boolean isEqual2(List<T> list2) throws JException {
		boolean result = true;		
		Iterator<T> itr, itr2;
		try{
			for(itr = this.list.iterator(), itr2 = list2.iterator(); itr.hasNext() && itr2.hasNext();)
			{
				T object1 = itr.next();
				T object2 = itr2.next();
				result &= object1.equals(object2);					
			}
			
			if( itr.hasNext() || itr2.hasNext() ) result = false;
			
			return result;
		}catch(Exception ex2){
			throw new JException("Cannot test equality:", ex2);
		}
	}

	@Override
	public JableList<T> filter(String expression) throws JException {
		ExpressionTree<T, Object> etree = null;
		try{			
			etree = (new ExpressionTree<T, Object>(expression)).compile();		
		}catch(Exception ex){
			throw new JException("Invalid Expression: " + expression, ex);
		}
		List<T> result = null;
		
		try{			
			int[] indices = etree.evaluate(this.list.size());
			result = new ArrayList<T>();			
			for(int i : indices)			
				result.add( this.list.get(i) );			
		}catch(Exception ex2){
			throw new JException("Cannot filter:", ex2);
		}
		return new JQList<T>(result);		
	}

	/**
	 * 
	 */
	@Override
	public JableList<T> find(String query) throws JException {
		ExpressionTree<T, Object> etree = null;
		try{			
			etree = (new ExpressionTree<T, Object>(query)).compile();		
		}catch(Exception ex){
			throw new JException("Invalid Expression: " + query, ex);
		}
		List<T> result = new ArrayList<T>();
		
		try{
			Iterator<T> itr = this.list.iterator();			
			while( itr.hasNext() ){
				T t = itr.next();
				if( etree.evaluate(t) )
					result.add(t);
			}				
		}catch(Exception ex2){
			throw new JException("Cannot find:", ex2);
		}
		return new JQList<T>(result);
	}

	@Override
	public JableList<T> get(String expression) throws JException {
		ExpressionTree<T, Object> etree = null;
		try{			
			etree = (new ExpressionTree<T, Object>(expression)).compile();		
		}catch(Exception ex){
			throw new JException("Invalid Expression: " + expression, ex);
		}
		List<T> result = null;
		
		try{			
			int[] indices = etree.evaluate(this.list.size());
			result = new ArrayList<T>();
			List<Integer> indicesList = J.newlist(indices).list();
			int i = 0;			
			for(Iterator<T> itr = this.list.iterator(); itr.hasNext();i++)
			{
				T t = itr.next();
				if( indicesList.contains(i) )				
					result.add( t );								
			}
		}catch(Exception ex2){
			throw new JException("Cannot get items:", ex2);
		}
		return new JQList<T>(result);
	}

	@Override
	public JableList<T> grep(String regex) throws JException {
		List<T> list = new ArrayList<T>();
		
		try{
			Pattern pattern = Pattern.compile(regex);
			for( Iterator<T> itr = this.list.iterator(); itr.hasNext();)
			{
				T obj = itr.next();
				Matcher m = pattern.matcher( J.$(obj).serializeProps() );
				if( m.find() )
					list.add(obj);
			}			
		}catch(Exception ex2){
			throw new JException("Cannot grep:", ex2);
		}
		return new JQList<T>(list);
	}

	@Override
	public JableMap<Object, Object> groupBy(String expressions)
			throws JException {
		try
		{
			String[] columns = expressions.split(",");
			return new JQMap<Object, Object>(this.groupByInternal(columns, 0, this.list));
		}catch(Exception ex2){
			throw new JException("Cannot group by:", ex2);
		}
	}

	@Override
	public List<T> list() {
		return this.list;
	}

	@Override
	public JableList<T> not(String expression) throws JException {
		ExpressionTree<T, Object> etree = null;
		try{			
			etree = (new ExpressionTree<T, Object>(expression)).compile();		
		}catch(Exception ex){
			throw new JException("Invalid Expression: " + expression, ex);
		}
		List<T> result = new ArrayList<T>();
		
		try{
			Iterator<T> itr = this.list.iterator();
			
			while( itr.hasNext() ){
				T t = itr.next();
				if( !etree.evaluate(t) )
					result.add(t);
			}				
		}catch(Exception ex2){
			throw new JException("Cannot negate:", ex2);
		}
		return new JQList<T>(result);
	}

	@Override
	public JableList<Object> project(String expression) throws JException {
		ExpressionTree<T, Object> etree = null;
		try{			
			etree = (new ExpressionTree<T, Object>(expression)).compile();		
		}catch(Exception ex){
			throw new JException("Invalid Expression: " + expression, ex);
		}
		List<Object> result = new ArrayList<Object>();
		
		try{
			Iterator<T> itr = this.list.iterator();
			
			while( itr.hasNext() ){
				T t = itr.next();
				result.add(etree.getValue(t));
			}				
		}catch(Exception e)
		{
			throw new JException("Cannot project.", e);
		}
		return new JQList<Object>(result);	
	}

	@Override
	public JableList<T> remove(String expression) throws JException {
		ExpressionTree<T, Object> etree = null;
		try{			
			etree = (new ExpressionTree<T, Object>(expression)).compile();		
		}catch(Exception ex){
			throw new JException("Invalid Expression: " + expression, ex);
		}
		List<T> result = null;
		
		try{			
			int[] indices = etree.evaluate(this.list.size());			
			List<Integer> indicesList = J.newlist(indices).list();			
			result = new ArrayList<T>();
			int i = -1;			
			for(Iterator<T> itr = this.list.iterator(); itr.hasNext();)
			{
				T t = itr.next();
				if( (indicesList.size() != 0 && indicesList.contains(i++)) || 
						(indicesList.size() == 0 && etree.evaluate(t)) )	
					itr.remove();
				else
					result.add( t );
			}
		}catch(Exception ex2){
			throw new JException("Cannot remove:", ex2);
		}
		return new JQList<T>(result);
	}

	@Override
	public JableList<T> set(String property, Object value) throws JException {
		try
		{
			for( Iterator<T> itr = this.list.iterator(); itr.hasNext();)
			{
				T t = itr.next();
				OgnlExpression.setValue(property, t, value);
			}
		}catch(Exception ex2){
			throw new JException("Cannot set values:", ex2);
		}
		return this;
	}

	@Override
	public String toDSV(String delimiter) throws JException {
		StringBuffer buf = new StringBuffer();
		try{
			for( Iterator<T> itr = this.list.iterator(); itr.hasNext();)
			{
				T obj = itr.next();
				if( buf.length() == 0 )
					buf.append(obj.toString());
				else
					buf.append(delimiter).append(obj.toString());
			}
		}catch(Exception ex2){
			throw new JException("Cannot convert to delimiter separated string:", ex2);
		}
		return buf.toString();
	}

	@Override
	public String toDSV(String delimiter, StringConverter<T> converter)
			throws JException {
		StringBuffer buf = new StringBuffer();
		try{
			for( Iterator<T> itr = this.list.iterator(); itr.hasNext();)
			{
				T obj = itr.next();
				if( buf.length() == 0 )
					buf.append(obj.toString());
				else
					buf.append(delimiter).append( converter.convertToStr(obj) );
			}
		}catch(Exception ex2){
			throw new JException("Cannot convert to delimiter separated string:", ex2);
		}
		return buf.toString();
	}

	@Override
	public JableList<T> toListFromDSV(String dsv, String delimiter,
			ObjectConverter<T> converter) {		
		String[] parts = dsv.split(delimiter);
		for( String part : parts )
			this.list.add(converter.convertFromStr(part));
		
		return this;
	}

	@Override
	public JableMap<Object, T> toMap(String expression) throws JException {
		Map<Object, T> map = new HashMap<Object, T>();
		ExpressionTree<T, Object> etree = null;
		try{			
			etree = (new ExpressionTree<T, Object>(expression)).compile();		
		}catch(Exception ex){
			throw new JException("Invalid Expression: " + expression, ex);
		}	
		
		try{
			Iterator<T> itr = this.list.iterator();			
			while( itr.hasNext() ){
				T t = itr.next();
				map.put( etree.getValue(t), t );
			}				
		}catch(Exception ex2){
			throw new JException("Cannot create map:", ex2);
		}
		return new JQMap<Object, T>(map);
	}

	@Override
	public JableList<T> unique(String expression) throws JException {
		Map<Object, T> map = new HashMap<Object, T>();
		ExpressionTree<T, Object> etree = null;
		try{			
			etree = (new ExpressionTree<T, Object>(expression)).compile();		
		}catch(Exception ex){
			throw new JException("Invalid Expression: " + expression, ex);
		}
		
		try{
			Iterator<T> itr = this.list.iterator();			
			while( itr.hasNext() ){
				T t = itr.next();
				map.put( etree.getValue(t), t );
			}				
		}catch(Exception ex2){
			throw new JException("Cannot find unique set:", ex2);
		}
		return new JQList<T>(new ArrayList<T>(map.values()));
	}

	@Override
	public JableList<T> unique() throws JException {
		Set<T> set = new HashSet<T>();
		try{
			for( Iterator<T> itr = this.list.iterator(); itr.hasNext();)
			{
				T t = itr.next();
				set.add(t);
			}
		}catch(Exception ex2){
			throw new JException("Cannot find unique set:", ex2);
		}
		return new JQList<T>(new ArrayList<T>(set));
	}

	/**
	 * Returns the size of the sub list.
	 */
	@Override
	public int size() {
		return this.list.size();
	}
	
	/**
	 * Method to group by recursively by a number of columns;
	 * @param columns
	 * @param index
	 * @param resultSet
	 * @return
	 */
	private Map<Object, Object> groupByInternal(String[] columns, int index, List resultSet)
	{
		if( index == columns.length ) return null;
		
		String column = columns[index].trim();	
		Map<Object, Object> map = new HashMap<Object, Object>();
		Iterator itr = resultSet.iterator();
		for( int i = 0 ; i < resultSet.size(); i++ )
		{
			Object obj = itr.next();
			Object key = OgnlExpression.getValue(column, obj);
			List<Object> list = (List<Object>)map.get( key );
			
			if( list == null )
			{
				list = new ArrayList<Object>();
				map.put(key, list);
			}
			list.add(obj);
		}
		
		for( Object key : map.keySet() )
		{
			List<Object> nList = (List<Object>)map.get(key);
			Map<Object, Object> nMap = groupByInternal(columns, index + 1, nList);
			
			if( nMap == null ) continue;
			
			map.put(key, nMap);
		}		
		return map;
	}
}
