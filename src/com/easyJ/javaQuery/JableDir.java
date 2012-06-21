/**
 * 
 */
package com.easyJ.javaQuery;

import java.io.File;
import java.util.Map;

import com.easyJ.util.JException;

/**
 * Interface for Directory operations.
 * @author khan.m
 *
 */
public interface JableDir extends JableFileIO{
	
	/**
	 * List all the files in the directory.
	 * @return
	 * @throws JException
	 */
	public JableList<String> ls(boolean deep) throws JException;
	
	/**
	 * List all the files in the directory.
	 * @param path
	 * @return
	 * @throws JException
	 */
	public JableList<String> dir(boolean deep) throws JException;
	
	/**
	 * Search for specific files in the directory. Specify "deep" as true
	 * if the files are located deep down the folder hierarchy.
	 * @param expression
	 * @param deep
	 * @return
	 * @throws JException
	 */
	public JableList<String> find(String expression, boolean deep) throws JException;
	
	/**
	 * Search for a pattern in the content of the files. Specify "deep" as true
	 * if the files are located deep down the folder hierarchy.
	 * @param expression
	 * @param deep
	 * @return
	 * @throws JException
	 */
	public JableList<String> findInContent(String expression, boolean deep) throws JException;
	
	/**
	 * Copy the files which match the expression to a destination folder.
	 * @param expression
	 * @param destination
	 * @param deep
	 * @return
	 * @throws JException
	 */
	public JableList<String> copy(String expression, File destination, boolean deep) throws JException;
	
	/**
	 * Move the files which match the expression to a destination folder.
	 * @param expression
	 * @param destination
	 * @param deep
	 * @return
	 * @throws JException
	 */
	public JableList<String> move(String expression, File destination, boolean deep) throws JException;
	
	/**
	 * Find the diff between two directories.
	 * @param dir2
	 * @param deep
	 * @return
	 * @throws JException
	 */
	public Map<String, JableList<String>> diff(File dir2, boolean deep) throws JException;
	
	/**
	 * Delete the files which match the expression.
	 * @param expression
	 * @param deep
	 * @return
	 * @throws JException
	 */
	public boolean delete(String expression, boolean deep) throws JException;
	
	/**
	 * Return the absolute of the directory.
	 * @return
	 */
	public String path();

}
