package test.com.demo.utils;

import java.io.File;

public class Tools
{
    //判断文件是否存在 
    public static boolean isFileExists(String filepath){
    	boolean result = false;
    	 try
		{
			File file = new File(filepath);
			    if (file.exists()) {
			    	result = true;
			    }else{
			    	
			    }
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return result;
    }     

}
