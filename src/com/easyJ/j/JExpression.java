/**
 * 
 */
package com.easyJ.j;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.easyJ.javaQuery.J;
import com.easyJ.parser.JParseException;
import com.easyJ.util.Constants;
import com.easyJ.util.JException;
import com.easyJ.util.Util;

/**
 * Class to evaluate a JExpression. JExpressions are queries enclosed
 * within "[" and "]" brackets. The class can compile and evaluate 
 * the following expressions...
 * <br/>
 * [superclass=java.lang.String] - This query will return true if the
 * 									  object extends the class "String".
 * <br/>
 * [interface=java.lang.Runnable]- This query will return true if the
 * 									   object implements the "Runnable" Interface. 
 * <br/>
 * [class=java.lang.String]- This query will return true if the
 * 								 object is of the class "String".
 * <br/>
 * [key.superclass=java.lang.String] - This query will return true if the key
 * 									      of the Map entry extends the class "String".
 * <br/>
 * [key.interface=java.lang.Runnable] - This query will return true if the key
 * 									       of the Map entry implements the "Runnable" Interface. 
 * <br/>
 * [key.class=java.lang.String] - This query will return true if the key
 * 									 of the Map entry is of the class "String".	 
 * <br/>
 * [value.superclass=java.lang.String] - This query will return true if the value
 * 									      of the Map entry extends the class "String".
 * <br/>
 * [value.interface=java.lang.Runnable] - This query will return true if the value
 * 									       of the Map entry implements the "Runnable" Interface. 
 * <br/>
 * [value.class=java.lang.String] - This query will return true if the value
 * 									 of the Map entry is of the class "String".
 * <br/>
 * [1,5,7,8] - This will return the 1st, 5th, 7th and 8th objects in the collection.
 * <br/>
 * [:first] - This will return the first object of the collection.
 *  <br/>
 * [file.ext=pdf,wav] - This will return true if the file has either of the extensions.
 * <br/>
 * [file.name=(^a-z0-9$)\.doc] - This will return true if the file name matches the regular expression.
 * <br/>
 * [file.content=(^a-z0-9$)\.doc] - This will return true if the file content matches the regular expression. 
 * 
 * @author khan.m
 *
 */
public class JExpression<T,V> implements Expression<T,V>, Constants{

	private static Map<JExpressionType, String> regexes;
	private JExpressionType type;
	private String value;
	
	/**
	 * Load all the regexes to parse the JExpression
	 */
	static{
		regexes = new HashMap<JExpressionType, String>();
		regexes.put(JExpressionType.SUPERCLASS, "^superclass=([a-zA-Z0-9\\.\\$_]+)");
		regexes.put(JExpressionType.CLASS, "^class=([a-zA-Z0-9\\.\\$_]+)");
		regexes.put(JExpressionType.INTERFACE, "^interface=([a-zA-Z0-9\\.\\$_]+)");
		// For Hashmap
		regexes.put(JExpressionType.KEY_SUPERCLASS, "^key\\.superclass=([a-zA-Z0-9\\.\\$_]+)");
		regexes.put(JExpressionType.KEY_CLASS, "^key\\.class=([a-zA-Z0-9\\.\\$_]+)");
		regexes.put(JExpressionType.KEY_INTERFACE, "^key\\.interface=([a-zA-Z0-9\\.\\$_]+)");
		
		regexes.put(JExpressionType.VALUE_SUPERCLASS, "^value\\.superclass=([a-zA-Z0-9\\.\\$_]+)");
		regexes.put(JExpressionType.VALUE_CLASS, "^value\\.class=([a-zA-Z0-9\\.\\$_]+)");
		regexes.put(JExpressionType.VALUE_INTERFACE, "^value\\.interface=([a-zA-Z0-9\\.\\$_]+)");
		
		regexes.put(JExpressionType.GET, "^:([0-9,\\-]+)");
		regexes.put(JExpressionType.FILTER, "^:([a-zA-Z0-9\\.\\$_]+)");		
		regexes.put(JExpressionType.FILE_EXT, "^file\\.ext=([a-zA-Z0-9,]+)");
		regexes.put(JExpressionType.FILE_NAME, "^file\\.name=(.+)");
		regexes.put(JExpressionType.FILE_CONTENT, "^file\\.content=(.+)");
	}
	
	/**
	 * 
	 */
	public JExpression()
	{
		// No Argument Constructor
	}
	
	/**
	 * Method to compile the query.
	 * @param expression
	 * @throws JParseException
	 */
	@Override
	public void compile(String exp) throws JParseException
	{		
		for(Map.Entry<JExpressionType, String> e : regexes.entrySet() )
		{
			Pattern pattern = Pattern.compile(e.getValue(), Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(exp);
			if(matcher.matches())
			{				
				type = e.getKey();
				value = matcher.group(1);
				break;
			}
		}		
		
		if( this.type == null || this.value == null )
			throw new JParseException("Unsupported Expression: " + exp, exp);
	}
	
	/**
	 * Method to evaluate the object against the query.
	 * @param object
	 * @return
	 */
	@Override
	public boolean matches(T object)
	{		
		Map.Entry<T, V> e = null;
		switch(this.type)
		{
			case SUPERCLASS:
				return this.isSuperClass(object);
			case CLASS:
				return this.isClass(object);
			case INTERFACE:
				return this.doesImplement(object);
				
			case KEY_SUPERCLASS:
				e = (Map.Entry<T, V>)object;
				return this.isSuperClass(e.getKey());
			case KEY_CLASS:
				e = (Map.Entry<T, V>)object;
				return this.isClass(e.getKey());
			case KEY_INTERFACE:
				e = (Map.Entry<T, V>)object;
				return this.doesImplement(e.getKey());
				
			case VALUE_SUPERCLASS:
				e = (Map.Entry<T, V>)object;
				return this.isSuperClass(e.getValue());
			case VALUE_CLASS:
				e = (Map.Entry<T, V>)object;
				return this.isClass(e.getValue());
			case VALUE_INTERFACE:
				e = (Map.Entry<T, V>)object;
				return this.doesImplement(e.getValue());				
			case FILE_EXT:
				return this.isExtension(object);
			case FILE_NAME:
				return this.matchesFileName(object);	
			case FILE_CONTENT:
				return this.matchesFileContent(object);	
			case NONE:
				return false;				
			default:
				return false;			
		}		
	}
	
	/**
	 * Method to evaluate a collection against the query.
	 * @param collectionSize
	 * @return
	 */
	@Override
	public int[] matches(int collectionSize)
	{	
		switch(this.type)
		{			
			case FILTER:
				return this.filter(collectionSize);	
			case GET:
				return this.get(collectionSize);
			case NONE:
				return null;
			default:
				return null;
		}
	}
	
	/**
	 * This method evaluates the following kind of expressions...
	 * "[:first]", "[:last]", "[:even]", "[:odd]"
	 * @param collectionSize
	 * @return
	 */
	public int[] filter(int collectionSize)
	{		
		if( this.value.equals( FIRST ) )
			return new int[]{0};
		else if( this.value.equals( LAST ) )
			return new int[]{collectionSize - 1};
		else if( this.value.equals( EVEN ) )
		{
			int[] result = new int[ collectionSize/2 ];
			for(int i = 1; i <= result.length; i++)
				result[i - 1] = (i * 2) - 1;			
			return result;
		}
		else if( this.value.equals( ODD ) )
		{
			int[] result = new int[ (collectionSize % 2 == 0) ? (collectionSize/2) : (collectionSize/2) + 1];
			for(int i = 0; i < result.length; i++)
				result[i] = 2 * i;			
			return result;
		}
		else return null;
	}
	
	/**
	 * This method evaluates the following kind of expressions...
	 * "[1,2,3]", "[1-8]"
	 * @param collectionSize
	 * @return
	 */
	public int[] get(int collectionSize)
	{		
		if( this.value.indexOf("-") > 0 )
		{
			// range
			String startStr = this.value.substring(0, this.value.indexOf("-"));
			String endStr = this.value.substring(this.value.indexOf("-") + 1);			
			int start = Integer.parseInt(startStr.trim());
			int end = Integer.parseInt(endStr.trim());
			int[] result = new int[ (end - start) + 1 ];
			for( int i = start, j = 0; i <= end; i++, j++ )
				result[j] = i;	
			
			return result;			
		}
		else if ( this.value.indexOf(",") > 0 )
		{
			String[] indicesStr = this.value.split(",");
			int[] result = new int[ indicesStr.length ];
			for ( int i = 0 ; i < indicesStr.length; i++ )			
				result[i] = i;	
			
			return result;
		}
		else
			return null;
	}
	
	/**
	 * Method to check if the object extends the class specified in the 
	 * JExpression.
	 * @param object
	 * @return
	 */
	private boolean isSuperClass(Object object)
	{
		return object.getClass().getSuperclass().getName().equals(this.value);		
	}
	
	/**
	 * Method to check if the object is of the class specified in the 
	 * JExpression.
	 * @param object
	 * @return
	 */
	private boolean isClass(Object object)
	{
		return object.getClass().getName().equals(this.value);
	}
	
	/**
	 * Method to check if the object implements the interface specified in the 
	 * JExpression.
	 * @param object
	 * @return
	 */
	private boolean doesImplement(Object object)
	{		
		Class[] interfaces = object.getClass().getInterfaces();
		
		for(Class cl : interfaces) 
		{
			if( this.value.equals(cl.getName()) )
				return true;
		}
		return false;
	}
	
	/**
	 * Method to check if the file has either of the extensions specified in the 
	 * JExpression.
	 * @param file
	 * @return
	 */
	private boolean isExtension(T file){
		File f = (File)file;		
		String[] extensions = Util.reverseStr( f.getPath() ).split("\\.");		
		boolean all = this.value.equals("*") || this.value.equals("*.*");
		if( extensions != null && extensions.length > 0)
		{
			String ext = Util.reverseStr( extensions[0] );			
			if( all || J.newlist(this.value.split(",")).list().contains(ext) )
				return true;	
		}		
		return false;			
	}
	
	/**
	 * Method to check if the file matches the regular expression 
	 * specified in the JExpression.
	 * @param file
	 * @return
	 */
	private boolean matchesFileName(T file){
		boolean all = this.value.equals("*") || this.value.equals("*.*");
		Pattern pattern = Pattern.compile(this.value);
		Matcher matcher = pattern.matcher(((File)file).getName());
		return all || matcher.find();
	}
	
	/**
	 * Method to check if the file's content matches the regular 
	 * expression specified in the JExpression.
	 * @param file
	 * @return
	 */
	private boolean matchesFileContent(T file){
		try{
			File f = (File)file;
			Pattern pattern = Pattern.compile(this.value);
			Matcher matcher = pattern.matcher( J.$(f).content() );
			return matcher.find();
		}catch(JException je)
		{
			return false;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws JParseException{
		JExpression j = new JExpression();
		j.compile("class=test.JParser");
		
		if(j.matches(j))
			System.out.println("Matched");
		else
			System.out.println("Not matched");	
	}
}
