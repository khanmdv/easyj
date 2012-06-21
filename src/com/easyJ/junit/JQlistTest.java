/**
 * 
 */
package com.easyJ.junit;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.easyJ.javaQuery.J;
import com.easyJ.javaQuery.ObjectConverter;
import com.easyJ.javaQuery.StringConverter;
import com.easyJ.util.JException;

/**
 * @author khan.m
 *
 */
public class JQlistTest extends TestCase {

	List<Employee> list;
	List<Employee> list2;
	/**
	 * @param name
	 */
	public JQlistTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		list = new ArrayList<Employee>();
		list.add(new Employee(100, "Vipin","M", 5000L, "Permanent"));
		list.add(new Employee(101, "Amit","M", 3000L, "Permanent"));
		list.add(new Employee(102, "Mohtashim","M", 10000L, "Permanent"));
		list.add(new Employee(103, "Yash","M", 2000L, "Temp"));
		list.add(new Employee(104, "Sheryl","F", 4000L, "Permanent"));
		list.add(new Employee(105, "Bhavya","F", 4000L, "Permanent"));
		list.add(new Employee(106, "Anjana","F", 6000L, "Permanent"));
		list.add(new Employee(107, "Swati","F", 2000L, "Temp"));
		list.add(new Employee(108, "Nishant","M", 4000L, "Permanent"));
		list.add(new Employee(109, "Kislay","M", 5000L, "Permanent"));
		list.add(new Employee(110, "Jagadeesh","M", 10000L, "Permanent"));
		list.add(new Employee(111, "Sangeetha","F", 4000L, "Temp"));
		list.add(new Employee(112, "Ajoy","M", 7000L, "Temp"));
		list.add(new Employee(113, "Neeta","F", 8000L, "Permanent"));
		list.add(new Employee(114, "Shahenaz","F", 8000L, "Permanent"));
		list.add(new Employee(115, "Rohit","M", 3000L, "Permanent"));
		list.add(new Employee(116, "Ankur","M", 6000L, "Permanent"));
		list.add(new Employee(117, "Ashley","M", 5000L, "Temp"));
		list.add(new Employee(118, "Debanjan","M", 7000L, "Permanent"));
		list.add(new Employee(119, "Rifat","F", 6000L, "Permanent"));
		
		// duplicate
		list2 = new ArrayList<Employee>();
		list2.add(new Employee(100, "Vipin","M", 5000L, "Permanent"));
		list2.add(new Employee(101, "Amit","M", 3000L, "Permanent"));
		list2.add(new Employee(102, "Mohtashim","M", 10000L, "Permanent"));
		list2.add(new Employee(103, "Yash","M", 2000L, "Temp"));
		list2.add(new Employee(104, "Sheryl","F", 4000L, "Permanent"));
		list2.add(new Employee(105, "Bhavya","F", 4000L, "Permanent"));
		list2.add(new Employee(106, "Anjana","F", 6000L, "Permanent"));
		list2.add(new Employee(107, "Swati","F", 2000L, "Temp"));
		list2.add(new Employee(108, "Nishant","M", 4000L, "Permanent"));
		list2.add(new Employee(109, "Kislay","M", 5000L, "Permanent"));
		list2.add(new Employee(110, "Jagadeesh","M", 10000L, "Permanent"));
		list2.add(new Employee(111, "Sangeetha","F", 4000L, "Temp"));
		list2.add(new Employee(112, "Ajoy","M", 7000L, "Temp"));
		list2.add(new Employee(113, "Neeta","F", 8000L, "Permanent"));
		list2.add(new Employee(114, "Shahenaz","F", 8000L, "Permanent"));
		list2.add(new Employee(115, "Rohit","M", 3000L, "Permanent"));
		list2.add(new Employee(116, "Ankur","M", 6000L, "Permanent"));
		list2.add(new Employee(117, "Ashley","M", 5000L, "Temp"));
		list2.add(new Employee(118, "Debanjan","M", 7000L, "Permanent"));
		list2.add(new Employee(119, "Rifat","F", 6000L, "Permanent"));
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		list.clear();
		list2.clear();
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQList#equals(java.util.List)}.
	 */
	public void testIsEqual2ListOfT() throws JException{		
		assertTrue( J.$(list).isEqual2(list2) );	
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQList#filter(java.lang.String)}.
	 */
	public void testFilter()  throws JException{
		assertEquals( J.$(list).filter("[:0-7]").size(), 8 );		
		assertEquals( J.$(list).filter("[:1,2,3,4,5]").size(), 5 );		
		assertEquals( J.$(list).filter("[:first]").size(), 1 );
		assertEquals( J.$(list).filter("[:last]").size(), 1 );
		assertEquals( J.$(list).filter("[:even]").size(), 10);
		assertEquals( J.$(list).filter("[:odd]").size(), 10 );
		try{
			J.$(list).filter("[odd]").size(); fail("Filter: Expression Syntax check failed.");
		}catch(JException je){ /* Let it pass */ }
		
		try{ 
			J.$(list).filter("[:odd").size(); fail("Filter: Syntax check failed.");
		}catch(JException je){ /* Let it pass */ }
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQList#find(java.lang.String)}.
	 */
	public void testFind()  throws JException{
		assertEquals( J.$(list).find("{salary > 5000L}").size(), 9 );
		assertEquals( J.$(list).find("{sex.equals(\"F\")}").size(), 8 );		
		assertEquals( J.$(list).find("{sex.equals(\"F\") and salary > 4000L}").size(), 4 );
		assertEquals( J.$(list).find("{sex.equals(\"F\") and salary > 4000L} & [interface=java.lang.Cloneable]").size(), 4 );
		assertEquals( J.$(list).find("{sex.equals(\"F\") and salary > 4000L} & [superclass=java.lang.Object]").size(), 4 );
		assertEquals( J.$(list).find("{sex.equals(\"F\") and salary > 4000L} & [class=com.krazylib.junit.Employee]").size(), 4 );
		assertEquals( J.$(list).find("({sex.equals(\"F\") and salary > 4000L} & [class=com.krazylib.junit.Employee]) | {employeeId in \\{119,114,113,106\\}}").size(), 4 );
		
		try{ 
			J.$(list).find("{sex.equals(\"F\")").size(); fail("Find: Syntax check failed.");
		}catch(JException je){ /* Let it pass */ }
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQList#get(java.lang.String)}.
	 */
	public void testGet()  throws JException{
		assertEquals( J.$(list).get("[:0-7]").size(), 8 );
		assertEquals( J.$(list).get("[:1,2,3,4,5]").size(), 5 );
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQList#grep(java.lang.String)}.
	 */
	public void testGrep()  throws JException{
		assertEquals( J.$(list).grep("Mohtashim").size(), 1 );
		assertEquals( J.$(list).grep("8000").size(), 2 );
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQList#groupBy(java.lang.String)}.
	 */
	public void testGroupBy()  throws JException{
		assertEquals( J.$(list).groupBy("salary").map().size(), 8 );
		assertEquals( J.$(list).groupBy("sex").map().size(), 2 );
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQList#not(java.lang.String)}.
	 */
	public void testNot()  throws JException{
		assertEquals( J.$(list).not("{salary > 5000L}").size(), 11 );
		assertEquals( J.$(list).not("{sex.equals(\"F\")}").size(), 12 );		
		assertEquals( J.$(list).not("{sex.equals(\"F\") and salary > 4000L}").size(), 16 );
		assertEquals( J.$(list).not("{sex.equals(\"F\") and salary > 4000L} & [interface=java.lang.Cloneable]").size(), 16 );
		assertEquals( J.$(list).not("{sex.equals(\"F\") and salary > 4000L} & [superclass=java.lang.Object]").size(), 16 );
		assertEquals( J.$(list).not("{sex.equals(\"F\") and salary > 4000L} & [class=com.krazylib.junit.Employee]").size(), 16 );
		assertEquals( J.$(list).not("({sex.equals(\"F\") and salary > 4000L} & [class=com.krazylib.junit.Employee]) | {employeeId in \\{119,114,113,106\\}}").size(), 16 );
		
		try{
			J.$(list).not("{sex.equals(\"F\")").size(); fail("Find: Syntax check failed.");
		}catch(JException je){ /* Let it pass */ }
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQList#project(java.lang.String)}.
	 */
	public void testProject()  throws JException{
		assertEquals( J.$(list).project("{salary * 2}").list().get(0), 10000L );
		assertEquals( J.$(list).project("{name + sex}").size(), 20 );
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQList#remove(java.lang.String)}.
	 */
	public void testRemove()  throws JException{
		assertEquals( J.$(list).remove("[:1-6]").list().size(), 14 );
		assertEquals( J.$(list).remove("[:1,2,3,4,5,6]").list().size(), 14 );
		assertEquals( J.$(list).remove("{salary > 5000L}").list().size(), 11 );
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQList#set(java.lang.String, java.lang.Object)}.
	 */
	public void testSet()  throws JException{
		assertEquals( J.$(list).set("salary", 100).list().get(0).getSalary(), 100 );
		assertEquals( J.$(list).set("name", "Test").list().get(0).getName(), "Test" );
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQList#toDSV(java.lang.String)}.
	 */
	public void testToDSVString()  throws JException{
		assertEquals( J.$(list).toDSV(","), "100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119" );
		assertEquals( J.$(list).toDSV("#"), "100#101#102#103#104#105#106#107#108#109#110#111#112#113#114#115#116#117#118#119" );
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQList#toDSV(java.lang.String, com.easyJ.javaQuery.StringConverter)}.
	 */
	public void testToDSVStringStringConverterOfT()  throws JException{
		assertEquals( J.$(list).toDSV(",", new StringConverter<Employee>() {
			
			@Override
			public String convertToStr(Employee t) {
				// TODO Auto-generated method stub
				return "" + t.getEmployeeId();
			}
		}), "100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119" );
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQList#toListFromDSV(java.lang.String, java.lang.String, com.easyJ.javaQuery.ObjectConverter)}.
	 */
	public void testToListFromDSV()  throws JException{
		assertEquals( J.$(new ArrayList<String>()).toListFromDSV("100,200,300", ",", new ObjectConverter<String>() {

			@Override
			public String convertFromStr(String str) {
				return str;
			}
		}).list().size(), 3 );
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQList#toMap(java.lang.String)}.
	 */
	public void testToMap()  throws JException{
		assertEquals( J.$(list).toMap("{name}").map().size(), 20 );
		assertEquals( J.$(list).toMap("{sex}").map().size(), 2 );
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQList#unique(java.lang.String)}.
	 */
	public void testUniqueString()  throws JException{
		assertEquals( J.$(list).unique("{salary}").list().size(), 8 );
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQList#unique()}.
	 */
	public void testUnique()  throws JException{
		assertEquals( J.$(list).unique().list().size(), 20 );
	}
}
