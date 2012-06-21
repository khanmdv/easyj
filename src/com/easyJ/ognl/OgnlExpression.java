package com.easyJ.ognl;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 * Class to evaluate OGNL expressions.
 * @author khan.m
 *
 */
public class OgnlExpression
{
    private Object expression;

    public OgnlExpression(String expressionString) throws OgnlException
    {
        super();
        expression = Ognl.parseExpression(expressionString);
    }
    
    /**
     * Static Method to get the value of any object's getter.
     * @param getter
     * @param object
     * @return
     * @throws OgnlException
     */
    public static Object getValue(String getter, Object object) 
    {
    	try
    	{
    		OgnlExpression ognl = new OgnlExpression(getter);
    		return ognl.getValue(new OgnlContext(), object);
    	}catch(OgnlException exp)
    	{
    		return null;
    	}
    }
    
    /**
     * Static Method to set the value of any object's setter.
     * @param getter
     * @param object
     * @return
     * @throws OgnlException
     */
    public static void setValue(String getter, Object object, Object value) 
    {
    	try
    	{
    		OgnlExpression ognl = new OgnlExpression(getter);
    		ognl.setValue(new OgnlContext(), object, value);
    	}catch(OgnlException exp)
    	{
    		return;
    	}
    }   
    
    /**
     * Method to get the value of any object's getter.
     * @param context
     * @param rootObject
     * @return
     * @throws OgnlException
     */
    public Object getValue(OgnlContext context, Object rootObject) throws OgnlException
    {
        return Ognl.getValue(getExpression(), context, rootObject);
    }

    /**
     * Method to set the value of any object's setter.
     * @param context
     * @param rootObject
     * @param value
     * @throws OgnlException
     */
    public void setValue(OgnlContext context, Object rootObject, Object value) throws OgnlException
    {
        Ognl.setValue(getExpression(), context, rootObject, value);
    }

    public Object getExpression()
    {
        return expression;
    }

    
}