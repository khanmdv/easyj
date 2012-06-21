/**
 * 
 */
package com.easyJ.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.easyJ.javaQuery.J;

/**
 * @author khan.m
 *
 */
public class IOUtil {

	/**
	 * Copy File from one location to another.
	 * @param from
	 * @param to
	 * @throws IOException
	 */
	public static void copyFile(File from, File to) throws IOException{
		if( !to.exists() ) to.createNewFile();
		
		InputStream in = new FileInputStream(from);
		OutputStream out = new FileOutputStream(to);
		byte[] buf = new byte[1024];
		int len;	 
		while ((len = in.read(buf)) > 0)
			out.write(buf, 0, len);		
	 
	    in.close();
	    out.close();
	}
	
	/**
	 * Move file from one location to another.
	 * @param from
	 * @param to
	 * @throws IOException
	 */
	public static void moveFile(File from, File to) throws IOException{
		if( !to.exists() ) to.createNewFile();
		
		InputStream in = new FileInputStream(from);
		OutputStream out = new FileOutputStream(to);
		byte[] buf = new byte[1024];
		int len;	 
		while ((len = in.read(buf)) > 0)
			out.write(buf, 0, len);		
	 
	    in.close();
	    out.close();
	    from.delete();
	}
	
	/**
	 * Get all the files redcursivly in a folder.
	 * @param dir
	 * @param pathInfo
	 * @return
	 */
	public static String[] getAllFiles(File dir, boolean pathInfo){
		String[] result = null;
		List<String> list = new ArrayList<String>();
		
		File[] files = dir.listFiles();
		
		for( File file : files )
		{			
			if( file.isDirectory() )
				list.addAll( J.newlist( getAllFiles(file, pathInfo) ).list() ) ;
			else			
				list.add( pathInfo ? file.getPath() : file.getName() );	
		}
		
		result = new String[list.size()];
		list.toArray(result);
		return result;
	}
	
	/**
	 * Get the files present in a folder.
	 * @param dir
	 * @param pathInfo
	 * @return
	 */
	public static String[] getFiles(File dir, boolean pathInfo){
		String[] result = null;
		List<String> list = new ArrayList<String>();
		
		File[] files = dir.listFiles();
		
		for( File file : files )
		{
			if( !file.isDirectory() )
				list.add( pathInfo ? file.getPath() : file.getName() );
		}
		
		result = new String[list.size()];
		list.toArray(result);
		return result;
	}
	
	/**
	 * Returns only the file name minus the path.
	 * @param fullName
	 * @return
	 */
	public static String stripFileName(String fullName){
		if( fullName.indexOf("/") >= 0)
			return fullName.substring( fullName.lastIndexOf("/") );
		else if( fullName.indexOf("\\") >= 0 )
			return fullName.substring( fullName.lastIndexOf("\\") );
		else return fullName;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
