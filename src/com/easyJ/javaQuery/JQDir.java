/**
 * 
 */
package com.easyJ.javaQuery;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.easyJ.parser.ExpressionTree;
import com.easyJ.util.IOUtil;
import com.easyJ.util.JException;

/**
 * @author khan.m
 *
 */
public class JQDir implements JableDir {

	private File file;
	
	/**
	 * 
	 * @param file
	 */
	public JQDir(File file)
	{
		this.file = file;
	}
	
	/* (non-Javadoc)
	 * @see com.krazylib.javaQuery.JableDir#copy(java.lang.String, boolean)
	 */
	@Override
	public JableList<String> copy(String expression, File destination, boolean deep)
			throws JException {
		ExpressionTree<File, Object> etree = null;
		try{			
			etree = (new ExpressionTree<File, Object>(expression)).compile();		
		}catch(Exception ex){
			throw new JException("Invalid Expression: " + expression, ex);
		}
		List<String> result = new ArrayList<String>();
		
		try{
			String[] files = deep ? IOUtil.getAllFiles(this.file, true) : IOUtil.getFiles(this.file, true);
			for( String fl : files ){
				File tFile = new File( fl );				
				if( etree.evaluate( tFile ) )
				{
					result.add( destination.getPath() + "/" + tFile.getName() );
					IOUtil.copyFile( tFile, new File(destination.getPath() + "/" + tFile.getName()) );
				}
			}				
		}catch(Exception ex2){
			throw new JException("Cannot find:", ex2);
		}
		return new JQList<String>(result);
	}

	/* (non-Javadoc)
	 * @see com.krazylib.javaQuery.JableDir#delete(java.lang.String)
	 */
	@Override
	public boolean delete(String expression, boolean deep) throws JException {
		ExpressionTree<File, Object> etree = null;
		try{			
			etree = (new ExpressionTree<File, Object>(expression)).compile();		
		}catch(Exception ex){
			throw new JException("Invalid Expression: " + expression, ex);
		}
		
		try{
			String[] files = deep ? IOUtil.getAllFiles(this.file, true) : IOUtil.getFiles(this.file, false);	
			for( String fl : files ){
				File tFile = new File( fl );
				if( etree.evaluate( tFile ) )					
					tFile.delete();			
			}				
		}catch(Exception ex2){
			throw new JException("Cannot delete:", ex2);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.krazylib.javaQuery.JableDir#diff(java.lang.String)
	 */
	@Override
	public Map<String, JableList<String>> diff(File dir2, boolean deep) throws JException {
		String[] myFiles = deep ? IOUtil.getAllFiles(this.file, false) : IOUtil.getFiles(this.file, false);
		String[] dir2Files = deep ? IOUtil.getAllFiles(dir2, false) : IOUtil.getFiles(dir2, false);
		
		List<String> in = new ArrayList<String>();
		List<String> out = new ArrayList<String>();
		try{
			List<String> myFileList = J.newlist(myFiles).list();
			List<String> dir2FilesList = J.newlist(dir2Files).list();
			Iterator<String> itrMy = myFileList.iterator();
			for(  ; itrMy.hasNext() ; )
			{
				String tFile = itrMy.next();
				if( !dir2FilesList.contains(tFile) )				
					out.add(tFile);				
				else				
					dir2FilesList.remove(tFile);
								
				itrMy.remove();				
			}
			
			if( dir2FilesList.size() > 0 )
				in.addAll(dir2FilesList);
			
			Map<String, JableList<String>> result = new HashMap<String, JableList<String>>();			
			result.put(">>>", new JQList<String>(out));
			result.put("<<<", new JQList<String>(in));
			return result;
		}catch(Exception ex2){
			throw new JException("Cannot find diff:", ex2);
		}		
	}

	/* (non-Javadoc)
	 * @see com.krazylib.javaQuery.JableDir#dir(java.lang.String)
	 */
	@Override
	public JableList<String> dir(boolean deep) throws JException {
		try{			
			String[] files = deep ? IOUtil.getAllFiles(this.file, false) : IOUtil.getFiles(this.file, false);			
			return J.newlist(files);
		}catch(Exception e){
			throw new JException("Cannot list files.", e);
		}
	}	

	/* (non-Javadoc)
	 * @see com.krazylib.javaQuery.JableDir#find(java.lang.String, boolean)
	 */
	@Override
	public JableList<String> find(String expression, boolean deep)
			throws JException {
		ExpressionTree<File, Object> etree = null;
		try{			
			etree = (new ExpressionTree<File, Object>(expression)).compile();		
		}catch(Exception ex){
			throw new JException("Invalid Expression: " + expression, ex);
		}
		List<String> result = new ArrayList<String>();
		
		try{
			String[] files = deep ? IOUtil.getAllFiles(this.file, true) : IOUtil.getFiles(this.file, true);
			for( String fl : files ){				
				File tFile = new File( fl );
				if( etree.evaluate( tFile ) )				
					result.add( fl );		
			}				
		}catch(Exception ex2){
			throw new JException("Cannot find:", ex2);
		}
		return new JQList<String>(result);
	}
	
	/* (non-Javadoc)
	 * @see com.krazylib.javaQuery.JableDir#find(java.lang.String, boolean)
	 */
	@Override
	public JableList<String> findInContent(String expression, boolean deep)
			throws JException {
		ExpressionTree<File, Object> etree = null;
		try{			
			etree = (new ExpressionTree<File, Object>(expression)).compile();		
		}catch(Exception ex){
			throw new JException("Invalid Expression: " + expression, ex);
		}
		List<String> result = new ArrayList<String>();
		
		try{
			String[] files = deep ? IOUtil.getAllFiles(this.file, true) : IOUtil.getFiles(this.file, false);	
			for( String fl : files ){
				File tFile = new File( fl );
				if( etree.evaluate( tFile ) )				
					result.add( fl );				
			}				
		}catch(Exception ex2){
			throw new JException("Cannot find:", ex2);
		}
		return new JQList<String>(result);
	}

	/* (non-Javadoc)
	 * @see com.krazylib.javaQuery.JableDir#ls(java.lang.String)
	 */
	@Override
	public JableList<String> ls(boolean deep) throws JException {
		try{			
			String[] files = deep ? IOUtil.getAllFiles(this.file, true) : IOUtil.getFiles(this.file, true);			
			return J.newlist(files);
		}catch(Exception e){
			throw new JException("Cannot list files.", e);
		}
	}

	/* (non-Javadoc)
	 * @see com.krazylib.javaQuery.JableDir#move(java.lang.String, boolean)
	 */
	@Override
	public JableList<String> move(String expression, File destination, boolean deep)
			throws JException {
		ExpressionTree<File, Object> etree = null;
		try{			
			etree = (new ExpressionTree<File, Object>(expression)).compile();		
		}catch(Exception ex){
			throw new JException("Invalid Expression: " + expression, ex);
		}
		List<String> result = new ArrayList<String>();
		
		try{
			String[] files = deep ? IOUtil.getAllFiles(this.file, true) : IOUtil.getFiles(this.file, true);	
			for( String fl : files ){
				File tFile = new File( fl );
				if( etree.evaluate( tFile ) )
				{
					result.add(destination.getPath() + "/" + tFile.getName());
					IOUtil.moveFile( tFile, new File(destination.getPath() + "/" + tFile.getName()) );
				}
			}				
		}catch(Exception ex2){
			throw new JException("Cannot move files:", ex2);
		}
		return new JQList<String>(result);
	}

	/* (non-Javadoc)
	 * @see com.krazylib.javaQuery.JableDir#zip()
	 */
	@Override
	public JableFile zip(String outputFileName) throws JException {
		try{ 
			ZipOutputStream zos = new 
	           ZipOutputStream(new FileOutputStream(new File(outputFileName)));
			
			this.zipDir(this.file.getPath(), zos);
			
			zos.close();
		    return new JQFile(new File(outputFileName));            
        } catch (Exception e) {
            throw new JException("Error Occurred while zipping directory. ", e);
        }
	}
	
	private void zipDir(String dir2zip, ZipOutputStream zos) throws IOException, JException
	{
		File zipDir = new File(dir2zip);
		//create a new File object based on the directory we 
		// have to zip File	   
		//get a listing of the directory content 
		List<String> dirList = (new JQDir(zipDir)).ls(false).list();
		byte[] readBuffer = new byte[2156]; 
		int bytesIn = 0; 
		
		//loop through dirList, and zip the files 
		for(int i = 0; i < dirList.size(); i++) 
		{ 
			File f = new File(dirList.get(i));
			
			if(f.isDirectory()) 
			{ 
		        //if the File object is a directory, call this 
				//function again to add its content recursively 
				String filePath = f.getPath(); 
				zipDir(filePath, zos); 
				//loop again 
				continue; 
			} 
		    //if we reached here, the File object f was not	a directory 
			//	create a FileInputStream on top of f 
		    FileInputStream fis = new FileInputStream(f); 
		    // create a new zip entry 
		    ZipEntry anEntry = new ZipEntry(f.getPath()); 
		    //place the zip entry in the ZipOutputStream object 
		    zos.putNextEntry(anEntry); 
		    //now write the content of the file to the ZipOutputStream 
		    while((bytesIn = fis.read(readBuffer)) != -1)			    
		       zos.write(readBuffer, 0, bytesIn);
		    
		    //close the Stream 
		    fis.close(); 
		}			   
	}
	
	/* (non-Javadoc)
	 * @see com.krazylib.javaQuery.JableFileIO#delete()
	 */
	@Override
	public boolean delete() throws JException {
		try{
			this.file.delete();
			return true;
		} catch (Exception e) {
            throw new JException("Cannot delete file: " + this.file.getPath(), e);
        }
	}	

	@Override
	public String path() {		
		return this.file.getAbsolutePath();
	}

	@Override
	public File me() {
		return this.file;
	}

}
