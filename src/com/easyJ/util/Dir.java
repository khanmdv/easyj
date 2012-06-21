/**
 * 
 */
package com.easyJ.util;

import java.io.File;

/**
 * 
 * Wrapper class to differentiate between a Directory and a File 
 * when using java.io.File class.
 * @author khan.m
 *
 */
public class Dir {
	
	private File dir;
	
	/**
	 * 
	 */
	public Dir() {
		super();
	}


	/**
	 * @param dir
	 */
	public Dir(File dir) {
		super();
		this.dir = dir;
	}


	/**
	 * @return the dir
	 */
	public File getDir() {
		return dir;
	}


	/**
	 * @param dir the dir to set
	 */
	public void setDir(File dir) {
		this.dir = dir;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
