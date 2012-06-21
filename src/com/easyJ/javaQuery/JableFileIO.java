/**
 * 
 */
package com.easyJ.javaQuery;

import java.io.File;

import com.easyJ.util.JException;

/**
 * Parent interface for the JableFile, JableDir.
 * @author khan.m
 *
 */
public interface JableFileIO {
	
	/**
	 * Delete the file/directory.
	 * @return
	 * @throws JException
	 */
	public boolean delete() throws JException;
	
	/**
	 * Return the original object.
	 * @return
	 */
	public File me();

	/**
	 * Zip the file/directory.
	 * @param outputFileName
	 * @return
	 * @throws JException
	 */
	public JableFileIO zip(String outputFileName) throws JException;
}
