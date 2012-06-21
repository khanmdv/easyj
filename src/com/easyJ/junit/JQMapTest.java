/**
 * 
 */
package com.easyJ.junit;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.easyJ.javaQuery.J;
import com.easyJ.util.JException;

/**
 * @author khan.m
 *
 */
public class JQMapTest extends TestCase {
	
	private Map<Integer, Employee> map;
	private Map<Integer, Employee> map2;

	/**
	 * @param name
	 */
	public JQMapTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		map = new HashMap<Integer, Employee>();
		map.put(100, new Employee(100, "Vipin","M", 5000L, "Permanent"));
		map.put(101, new Employee(101, "Amit","M", 3000L, "Permanent"));
		map.put(102, new Employee(102, "Mohtashim","M", 10000L, "Permanent"));
		map.put(103, new Employee(103, "Yash","M", 2000L, "Temp"));
		map.put(104, new Employee(104, "Sheryl","F", 4000L, "Permanent"));
		map.put(105, new Employee(105, "Bhavya","F", 4000L, "Permanent"));
		map.put(106, new Employee(106, "Anjana","F", 6000L, "Permanent"));
		map.put(107, new Employee(107, "Swati","F", 2000L, "Temp"));
		map.put(108, new Employee(108, "Nishant","M", 4000L, "Permanent"));
		map.put(109, new Employee(109, "Kislay","M", 5000L, "Permanent"));
		map.put(110, new Employee(110, "Jagadeesh","M", 10000L, "Permanent"));
		map.put(111, new Employee(111, "Sangeetha","F", 4000L, "Temp"));
		map.put(112, new Employee(112, "Ajoy","M", 7000L, "Temp"));
		map.put(113, new Employee(113, "Neeta","F", 8000L, "Permanent"));
		map.put(114, new Employee(114, "Shahenaz","F", 8000L, "Permanent"));
		map.put(115, new Employee(115, "Rohit","M", 3000L, "Permanent"));
		map.put(116, new Employee(116, "Ankur","M", 6000L, "Permanent"));
		map.put(117, new Employee(117, "Ashley","M", 5000L, "Temp"));
		map.put(118, new Employee(118, "Debanjan","M", 7000L, "Permanent"));
		map.put(119, new Employee(119, "Rifat","F", 6000L, "Permanent"));
		
		map2 = new HashMap<Integer, Employee>();
		map2.put(100, new Employee(100, "Vipin","M", 5000L, "Permanent"));
		map2.put(101, new Employee(101, "Amit","M", 3000L, "Permanent"));
		map2.put(102, new Employee(102, "Mohtashim","M", 10000L, "Permanent"));
		map2.put(103, new Employee(103, "Yash","M", 2000L, "Temp"));
		map2.put(104, new Employee(104, "Sheryl","F", 4000L, "Permanent"));
		map2.put(105, new Employee(105, "Bhavya","F", 4000L, "Permanent"));
		map2.put(106, new Employee(106, "Anjana","F", 6000L, "Permanent"));
		map2.put(107, new Employee(107, "Swati","F", 2000L, "Temp"));
		map2.put(108, new Employee(108, "Nishant","M", 4000L, "Permanent"));
		map2.put(109, new Employee(109, "Kislay","M", 5000L, "Permanent"));
		map2.put(110, new Employee(110, "Jagadeesh","M", 10000L, "Permanent"));
		map2.put(111, new Employee(111, "Sangeetha","F", 4000L, "Temp"));
		map2.put(112, new Employee(112, "Ajoy","M", 7000L, "Temp"));
		map2.put(113, new Employee(113, "Neeta","F", 8000L, "Permanent"));
		map2.put(114, new Employee(114, "Shahenaz","F", 8000L, "Permanent"));
		map2.put(115, new Employee(115, "Rohit","M", 3000L, "Permanent"));
		map2.put(116, new Employee(116, "Ankur","M", 6000L, "Permanent"));
		map2.put(117, new Employee(117, "Ashley","M", 5000L, "Temp"));
		map2.put(118, new Employee(118, "Debanjan","M", 7000L, "Permanent"));
		map2.put(119, new Employee(119, "Rifat","F", 6000L, "Permanent"));
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		map.clear();
		map2.clear();
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQMap#isEqual2(java.util.Map)}.
	 */
	public void testIsEqual2() throws JException{
		assertTrue( J.$(map).isEqual2(map2) );
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQMap#find(java.lang.String)}.
	 */
	public void testFind()  throws JException {
		assertEquals( J.$(map).find("{key > 110}").size(), 9 );
		assertEquals( J.$(map).find("{value.sex.equals(\"F\")}").size(), 8 );		
		assertEquals( J.$(map).find("{value.sex.equals(\"F\") and value.salary > 4000L}").size(), 4 );
		assertEquals( J.$(map).find("[key.class=java.lang.Integer]").size(), 20 );
		assertEquals( J.$(map).find("[value.superclass=java.lang.Object]").size(), 20 );
		try{ 
			J.$(map).find("{value.sex.equals(\"F\")").size(); fail("Find: Syntax check failed.");
		}catch(JException je){ /* Let it pass */ }
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQMap#get(java.lang.String)}.
	 */
	public void testGet()  throws JException {
		assertEquals( J.$(map).get("[:0-7]").size(), 8 );
		assertEquals( J.$(map).get("[:1,2,3,4,5]").size(), 5 );
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQMap#grep(java.lang.String)}.
	 */
	public void testGrep()  throws JException {
		assertEquals( J.$(map).grep("Mohtashim").size(), 1 );
		assertEquals( J.$(map).grep("8000").size(), 2 );
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQMap#not(java.lang.String)}.
	 */
	public void testNot()  throws JException {
		assertEquals( J.$(map).not("{key > 110}").size(), 11 );
		assertEquals( J.$(map).not("{value.sex.equals(\"F\")}").size(), 12 );		
		assertEquals( J.$(map).not("{value.sex.equals(\"F\") and value.salary > 4000L}").size(), 16 );
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQMap#project(java.lang.String)}.
	 */
	public void testProject()  throws JException {
		assertEquals( J.$(map).project("{key * 2}").size(), 20 );
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQMap#remove(java.lang.String)}.
	 */
	public void testRemove()  throws JException {
		assertEquals( J.$(map).remove("[:1-6]").map().size(), 14 );
		assertEquals( J.$(map).remove("[:1,2,3,4,5,6]").map().size(), 14 );
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQMap#set(java.lang.String, java.lang.Object)}.
	 */
	public void testSet()  throws JException {
		assertEquals( J.$(map).set("value.salary", 100).map().get(100).getSalary(), 100 );
		assertEquals( J.$(map).set("value.name", "Test").map().get(100).getName(), "Test" );
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQMap#toList(java.lang.String)}.
	 */
	public void testToList()  throws JException {
		assertEquals( J.$(map).toList("{key}").list().size(), 20 );
		assertEquals( J.$(map).toList("{value.sex}").unique().size(), 2 );
	}

}
