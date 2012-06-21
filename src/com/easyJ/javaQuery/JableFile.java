/**
 * 
 */
package com.easyJ.javaQuery;

import java.io.File;

import com.easyJ.util.JException;

/**
 * Interface for File operations.
 * @author khan.m
 *
 */
public interface JableFile extends JableFileIO{
	
	/**
	 * Creates an empty file.
	 * @return
	 * @throws JException
	 */
	public JableFile touch() throws JException;
	
	/**
	 * Writes string to the file. Overwrites the content of the existing file.
	 * @param data
	 * @return
	 * @throws JException
	 */
	public JableFile writeFile(String data) throws JException;
	
	/**
	 * Appends the string to the file at the end.
	 * @param data
	 * @return
	 * @throws JException
	 */
	public JableFile append(String data) throws JException;
	
	/**
	 * Returns all lines of the file.
	 * @return
	 * @throws JException
	 */
	public String[] lines() throws JException;
	
	/**
	 * Searches for a pattern in the file and returns the lines matching the pattern.
	 * @param regex
	 * @return
	 * @throws JException
	 */
	public String[] grep(String regex) throws JException;
	
	/**
	 * Concats the two files.
	 * @param fileName
	 * @return
	 * @throws JException
	 */
	public JableFile concat(String fileName) throws JException;
	
	/**
	 * Concats the two files.
	 * @param file
	 * @return
	 * @throws JException
	 */
	public JableFile concat(File file) throws JException;
	
	/**
	 * Returns the file content.
	 * @return
	 * @throws JException
	 */
	public String content() throws JException;
}
