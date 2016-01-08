package com.xunyun.infanteduplatform.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
public class ReadInputStreamUtils {
	/***
	 * 读取输入流
	 * 
	 * @param inStream 
	 * 
	 * @return
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception{
		
		//创建ByteArrayOutputStream类实例
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();    
        
        //开辟缓冲区
        byte[] buffer = new byte[2048];    
        int len = 0;    
        
        // 将内容读到buffer中，读到末尾为-1 
        while( (len=inStream.read(buffer)) != -1 ){
        	
        	//将每次读到字节数组(buffer变量)内容写到内存缓冲区中，起到保存每次内容的作用
            outStream.write(buffer, 0, len);    
        }
        
        //关闭读取
        inStream.close();    
        
        return outStream.toByteArray();    
 }    
}
