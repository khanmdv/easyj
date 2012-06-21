package com.easyJ.junit;

import java.io.File;

import junit.framework.TestCase;

import com.easyJ.javaQuery.J;
import com.easyJ.util.Dir;
import com.easyJ.util.JException;

public class JQDirTest extends TestCase {
	
	private File dir;

	public JQDirTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		dir = new File("c:\\temp\\");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		dir = null; // GC
	}

	public void testCopy() throws JException  {
		assertEquals(J.$(new Dir(new File("c:\\temp\\f1\\")))
			.copy("[file.ext=pdf,wav]", new File("c:\\temp\\copy\\"), false).list().size(), 2);
		assertEquals(J.$(new Dir(new File("c:\\temp\\f1\\")))
			.copy("[file.ext=pdf,wav,java]", new File("c:\\temp\\copy\\"), true).list().size(), 8);
	}

	public void testDeleteString() throws JException {
		assertTrue( J.$(new Dir(new File("c:\\temp\\copy\\"))).delete("[file.ext=pdf,wav]", false) );
	}

	public void testDiff() throws JException {
		assertEquals(J.$(new Dir(new File("c:\\temp\\f1\\")))
				.diff(new File("c:\\temp\\f2\\"), false).get(">>>").size(), 1);
		assertEquals(J.$(new Dir(new File("c:\\temp\\f1\\")))
				.diff(new File("c:\\temp\\f2\\"), true).get(">>>").size(), 1);
	}

	public void testFind() throws JException {
		assertEquals( J.$(new Dir(new File("c:\\temp\\f1\\")))
				.find("[file.name=\\[a-zA-Z0-9\\_\\]\\.java]", true).size(), 5);
	}
	
	public void testFindInContent() throws JException {
		assertEquals( J.$(new Dir(new File("c:\\temp\\f1\\")))
				.findInContent("[file.content=TODO]", true).size(), 3);
	}

	public void testLs() throws JException {
		assertEquals( J.$(new Dir(new File("c:\\temp\\f1\\"))).ls(false).size(), 5);
	}

	public void testMove() throws JException {
		assertEquals(J.$(new Dir(new File("c:\\temp\\f1\\")))
				.move("[file.ext=pdf,wav]", new File("c:\\temp\\move\\"), false).list().size(), 2);
		assertEquals(J.$(new Dir(new File("c:\\temp\\f1\\")))
				.move("[file.ext=java]", new File("c:\\temp\\move\\"), true).list().size(), 5);
	}

	public void testZip() throws JException {
		assertTrue( J.$(new Dir(new File("c:\\temp\\f1\\"))).zip("c:\\temp\\dir.zip").me().exists() );
	}

	public void testDelete() throws JException {
		assertTrue( J.$(new Dir(new File("c:\\temp\\dir.zip"))).delete() );		
	}
}
