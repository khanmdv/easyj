/**
 * 
 */
package com.easyJ.javaQuery;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.easyJ.ognl.OgnlExpression;
import com.easyJ.util.JException;

/**
 * @author khan.m
 *
 */
public class JQObject<T> extends Object implements JableObject<T> {
	
	private T t;
	
	public JQObject(T t)
	{
		this.t = t;
	}

	@Override
	public JableObject<T> call(String method, Object[] args) throws JException {
		try{
			this.t.getClass().getMethod(method, args != null ? args.getClass() : null).invoke(this, args);
		}catch(Exception e){
			throw new JException("Cannot call method:" + method, e);
		}
		return this;
	}
	
	@Override
	public JableObject<T> copy(T obj, String properties) throws JException{
		if( properties == null || properties.trim().equals("") )
			return this;
		try{
			for( String prop : properties.split(",") )
			{
				Object value = OgnlExpression.getValue(prop, obj);
				OgnlExpression.setValue(prop, this.t, value);
			}
		}catch(Exception e){
			throw new JException("Cannot copy properties:", e);
		}
		return this;
	}

	@Override
	public boolean doesImplementAll(String interfazs) throws JException {
		if( interfazs == null )
			return false;
		try{
			Class[] theInterfaces = this.t.getClass().getInterfaces();
			
			String[] interfazes = interfazs.split(","); 
			List<String> interfazesList = (List<String>)J.newlist( interfazs.split(",") ).list();
			
			boolean implement = true;
			for( String intfc : interfazes )
				if ( !interfazesList.contains(intfc) )
					implement &= false;
			
			return implement;
		}catch(Exception e){
			throw new JException("Cannot find if the class implements interfaces:", e);
		}
	}

	@Override
	public boolean doesImplementAny(String interfazs) throws JException {
		try{
			Class[] theInterfaces = this.t.getClass().getInterfaces();
			
			String[] interfazes = interfazs.split(","); 
			List<String> interfazesList = (List<String>)J.newlist( interfazs.split(",") ).list();
			for( String intfc : interfazes )
				if ( interfazesList.contains(intfc) )
					return true;
			return false;
		}catch(Exception e){
			throw new JException("Cannot find if the class implements interface:", e);
		}
	}

	@Override
	public String grep(String regex) throws JException {
		try{
			String props = this.serializeProps();
			Pattern pattern = Pattern.compile(regex);
			Matcher m = pattern.matcher(props);
			if( m.find() )
				return m.group();
		}catch(Exception e){
			throw new JException("Cannot grep:", e);
		}
		return null;
	}

	@Override
	public boolean hasMethod(String method) throws JException {
		return J.newlist( this.t.getClass().getMethods() ).find("{name.equals(\"" + method  + "\")}").list().size() > 0;	
	}

	@Override
	public boolean hasProperty(String property) throws JException {
		return J.newlist( this.t.getClass().getFields() ).find("{name.equals(\"" + property  + "\")}").list().size() > 0;
	}

	@Override
	public String serializeProps() throws JException {
		StringBuffer buf = new StringBuffer();
		try{
			Field[] fields = this.t.getClass().getDeclaredFields();
			if( fields != null )	
			{
				for(  Field field : fields )
				{
					Method meth = null;
					try{
						meth = t.getClass().getMethod( this.getter(field.getName()), null );
					}catch(NoSuchMethodException nsme){
						// Ignore
					}
					if( meth != null )
					{
						if( buf.length() == 0 )
							buf.append(field.getName()).append("=").append( meth.invoke(t, null) );
						else
							buf.append(",").append(field.getName()).append("=").append( meth.invoke(t, null) );
					}					
				}				
			}
			// If the object is of primitive types(Like String, Integer etc., use toString() to get the value.
			if( buf.length() == 0)
				buf.append(t.toString());
			
		}catch(Exception e){
			throw new JException("Cannot serialize.", e);
		}
		return buf.toString();
	}

	@Override
	public JableObject<T> set(String property, Object value) throws JException {
		try{
			OgnlExpression.setValue(property, this.t, value);
			return this;
		}catch(Exception e){
			throw new JException("Cannot set value:", e);
		}
	}

	@Override
	public T me() {
		// TODO Auto-generated method stub
		return this.t;
	}
	
	private String getter(String meth)
	{
		return "get" + (meth.charAt(0) + "").toUpperCase() + meth.substring(1);
	}
	
	private String setter(String meth)
	{
		return "set" + (meth.charAt(0) + "").toUpperCase() + meth.substring(1);
	}
}
