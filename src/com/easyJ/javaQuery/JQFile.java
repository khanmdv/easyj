/**
 * 
 */
package com.easyJ.javaQuery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.easyJ.util.JException;

/**
 * @author khan.m
 *
 */
public class JQFile implements JableFile {

	private File file;
	
	/**
	 * 
	 * @param file
	 */
	public JQFile(File file)
	{
		this.file = file;
	}
	
	/* (non-Javadoc)
	 * @see com.krazylib.javaQuery.JableFileIO#delete()
	 */
	@Override
	public JableFile touch() throws JException{
		try{
			if( !this.file.exists() )
				this.file.createNewFile();
		}catch(IOException ioex){
			throw new JException("Cannot create file " + this.file.getPath(), ioex);
		}
		return this;
	}
	
	/* (non-Javadoc)
	 * @see com.krazylib.javaQuery.JableFile#append(java.lang.String)
	 */
	@Override
	public JableFile append(String data) throws JException {
		try{
			FileOutputStream appendedFile = new FileOutputStream(this.file, true);
			appendedFile.write(data.getBytes());
			appendedFile.close();
		}catch(IOException ioex){
			throw new JException("Cannot append file " + this.file.getPath(), ioex);
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see com.krazylib.javaQuery.JableFile#concat(java.lang.String)
	 */
	@Override
	public JableFile concat(String fileName) throws JException {
		try{
			FileOutputStream appendedFile = new FileOutputStream(this.file, true);
			appendedFile.write( (new JQFile(new File(fileName))).content().getBytes() );
			appendedFile.close();
		}catch(IOException ioex){
			throw new JException("Cannot concat file " + this.file.getPath(), ioex);
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see com.krazylib.javaQuery.JableFile#concat(java.io.File)
	 */
	@Override
	public JableFile concat(File file) throws JException {
		try{
			FileOutputStream appendedFile = new FileOutputStream(this.file, true);
			appendedFile.write( (new JQFile(file)).content().getBytes() );
			appendedFile.close();
		}catch(IOException ioex){
			throw new JException("Cannot concat file " + this.file.getPath(), ioex);
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see com.krazylib.javaQuery.JableFile#content()
	 */
	@Override
	public String content() throws JException {
		StringBuffer sb = new StringBuffer();
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(this.file));
			
			String temp = "";
			
			while( ( temp = br.readLine()) != null )
				sb.append(temp);
			
			br.close();
		}catch(IOException ioex){
			throw new JException("Cannot get content " + this.file.getPath(), ioex);
		}
		return sb.toString();
	}
	
	public String[] lines() throws JException{
		String[] result = null;
		BufferedReader reader = null;
		List<String> list = new ArrayList<String>();
		try{			
			String temp = null;
			reader = new BufferedReader(
											new InputStreamReader(new FileInputStream(this.file))
											);
			
			while( (temp = reader.readLine()) != null )
				list.add(temp);
	        
			reader.close();
		}catch(Exception ioex){
			throw new JException("Cannot grep file " + this.file.getPath(), ioex);
		}finally{
			try{
				if( reader != null ) reader.close();
			}catch(IOException ioe){}
		}
		result = new String[list.size()];
		list.toArray(result);
        return result;
	}

	/* (non-Javadoc)
	 * @see com.krazylib.javaQuery.JableFile#grep(java.lang.String)
	 */
	@Override
	public String[] grep(String regex) throws JException {
		String[] result = null;
		List<String> list = new ArrayList<String>();
		try{			
			String[] lines = this.lines();
			Pattern pattern = Pattern.compile(regex);
			if( lines != null )
			{	
				for(String line : lines )
				{
					Matcher m = pattern.matcher(line);        
		        	if(m.find()) 
		        		list.add(line); 
				}
			}	        
		}catch(Exception ioex){
			throw new JException("Cannot grep file " + this.file.getPath(), ioex);
		}
		result = new String[list.size()];
		list.toArray(result);
        return result;
	}
	
	/* (non-Javadoc)
	 * @see com.krazylib.javaQuery.JableFile#writeFile(java.lang.String)
	 */
	@Override
	public JableFile writeFile(String data) throws JException {
		try{
			FileOutputStream appendedFile = new FileOutputStream(this.file, true);
			appendedFile.write(data.getBytes());
			appendedFile.close();
		}catch(IOException ioex){
			throw new JException("Cannot write file " + this.file.getPath(), ioex);
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see com.krazylib.javaQuery.JableFileIO#delete()
	 */
	@Override
	public boolean delete() throws JException{
		try{
			this.file.delete();
		}catch(Exception ioex){
			throw new JException("Cannot delete file " + this.file.getPath(), ioex);
		}
		return true;
	}

	@Override
	public JableFileIO zip(String outputFileName) throws JException {
		try {
            byte[] buf = new byte[1024];
            FileInputStream fis = new FileInputStream(this.file);
            fis.read(buf,0,buf.length);
            
            CRC32 crc = new CRC32();
            ZipOutputStream s = new ZipOutputStream(
                    (OutputStream)new FileOutputStream(outputFileName));
            
            s.setLevel(6);
            
            ZipEntry entry = new ZipEntry(this.file.getAbsolutePath());
            entry.setSize((long)buf.length);
            crc.reset();
            crc.update(buf);
            entry.setCrc( crc.getValue());
            s.putNextEntry(entry);
            s.write(buf, 0, buf.length);
            s.finish();
            s.close();
            
            return new JQFile(new File(outputFileName));
            
        } catch (Exception e) {
            throw new JException("Error Occurred while zipping file " + this.file.getPath(), e);
        }
	}

	@Override
	public File me() {		
		return this.file;
	}
}
