package com.easyJ.junit;

import junit.framework.TestCase;

import com.easyJ.javaQuery.J;
import com.easyJ.util.JException;

public class JQObjectTest extends TestCase {
	Employee emp, emp2;
	String str = null;

	public JQObjectTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		emp = new Employee(102, "Mohtashim","M", 10000L, "Permanent");
		emp2 = new Employee(101, "Amit","M", 3000L, "Permanent");
		str = "Test String";
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		// Garbage collect
		emp = null;
		str = null;
	}
	
	public void testGrep() throws JException{
		assertEquals( J.$(emp).grep("Mohtashim"), "Mohtashim" );
	}

	public void testCopy() throws JException{
		assertEquals( J.$(emp2).copy(emp, "employeeId").me().getEmployeeId(), 102 );
		assertEquals( J.$(emp2).copy(emp, "employeeId,name").me().getName(), "Mohtashim" );
	}

	public void testDoesImplementAll() throws JException{
		assertTrue( J.$(emp).doesImplementAll("Cloneable,Serializable") );
	}

	public void testDoesImplementAny() throws JException{
		assertTrue( J.$(emp).doesImplementAll("Cloneable,JParseable") );
	}	

	public void testHasMethod() throws JException{
		assertTrue( J.$(emp).hasMethod("getEmployeeId") );
	}

	public void testHasProperty() throws JException{
		assertTrue( J.$(emp).hasProperty("alias") );
	}

	public void testSerializeProps() throws JException{
		assertEquals( J.$(emp).serializeProps(), 
					"employeeId=102,name=Mohtashim,sex=M,salary=10000,type=Permanent,alias=Mohtashim");
	}

	public void testSet() throws JException{
		assertEquals( J.$(emp).set("employeeId", 104).me().getEmployeeId(), 104);
	}

}
