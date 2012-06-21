package com.easyJ.junit;

import java.io.File;

import com.easyJ.javaQuery.J;
import com.easyJ.util.Dir;

public class Test {
	
	public static void main(String[] args) throws Exception{
		/*Pattern pattern = Pattern.compile("[a-z]\\.doc$");
		Matcher matcher = pattern.matcher("test.doc");
		System.out.println(matcher.find());*/
		/*StringBuffer buf = new StringBuffer();
		String x = "Hey";
		
		Field[] fields = x.getClass().getDeclaredFields();
		if( fields != null )	
		{
			
			for(  Field field : fields )
			{
				Method meth = null;
				
				try{				
					meth = x.getClass().getMethod( getter(field.getName()), null );
				}catch(NoSuchMethodException nsme){
					//
				}
				if( meth != null )
				{
					if( buf.length() == 0 )
						buf.append(field.getName()).append("=").append( meth.invoke(x, null) );
					else
						buf.append(", ").append(field.getName()).append("=").append( meth.invoke(x, null) );
				}					
			}
			
			if( buf.length() == 0)
				buf.append(x.toString());
		}
		
		System.out.println(buf.toString());*/
		//System.out.println((new File("c:\\temp\\f1\\IMG_0067.JPG")).getPath());
		//Employee emp = new Employee(102, "Mohtashim","M", 10000L, "Permanent");
		//for( Field f : emp.getClass().getFields())
		System.out.println( J.$(new Dir(new File("c:\\temp\\f1\\"))).zip("c:\\temp\\dir2.zip") );
	}
	
	private static String getter(String meth)
	{
		return "get" + (meth.charAt(0) + "").toUpperCase() + meth.substring(1);
	}

}
