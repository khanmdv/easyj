/**
 * 
 */
package com.easyJ.junit;

import java.io.File;

import junit.framework.TestCase;

import com.easyJ.javaQuery.J;
import com.easyJ.util.JException;

/**
 * @author khan.m
 *
 */
public class JQFileTest extends TestCase {

	private File file;
	/**
	 * @param name
	 */
	public JQFileTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		this.file = new File("c:\\temp\\IMG_0067.JPG");
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		this.file = null;
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQFile#touch()}.
	 */
	public void testTouch() throws JException{
		assertTrue( J.$(new File("c:\\temp\\x.txt")).touch().me().exists() );
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQFile#append(java.lang.String)}.
	 */
	public void testAppend() throws JException {
		assertEquals( J.$(new File("c:\\temp\\x.txt")).append("x").content(), "x");
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQFile#concat(java.lang.String)}.
	 */
	public void testConcatString() throws JException {
		assertEquals( J.$(new File("c:\\temp\\x.txt")).concat("c:\\temp\\y.txt").content(), "xy");
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQFile#concat(java.io.File)}.
	 */
	public void testConcatFile() throws JException {
		assertEquals( J.$(new File("c:\\temp\\x.txt")).concat(new File("c:\\temp\\y.txt")).content(), "xyy");
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQFile#grep(java.lang.String)}.
	 */
	public void testLines() throws JException {
		assertEquals( J.$(new File("c:\\temp\\test.doc")).lines().length, 544 );		
	}
	
	/**
	 * Test method for {@link com.easyJ.javaQuery.JQFile#grep(java.lang.String)}.
	 */
	public void testGrep() throws JException {
		assertEquals( J.$(new File("c:\\temp\\test.doc")).grep("manager").length, 7 );		
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQFile#writeFile(java.lang.String)}.
	 */
	public void testWriteFile() throws JException {
		assertEquals( J.$(new File("c:\\temp\\y.txt")).writeFile("y").content(), "yy" );
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQFile#delete()}.
	 */
	public void testDelete() throws JException {
		assertTrue( J.$(new File("c:\\temp\\x.txt")).delete() );
	}

	/**
	 * Test method for {@link com.easyJ.javaQuery.JQFile#zip(java.lang.String)}.
	 */
	public void testZip() throws JException {
		assertTrue( J.$(new File("c:\\temp\\test.doc")).zip("c:\\temp\\yy.zip").me().exists() );
	}

}
