package com.plg.shiro.util;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author vincent
 *
 */
public class DownloadUtils {
	
	public static void downloadExcel(HttpServletResponse response, InputStream in, String fileName) throws Exception {
        try {  
          response.reset(); 
          //设置要下载的文件的名称
          response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
          //通知客服文件的MIME类型
          response.setContentType("application/vnd.ms-excel;charset=UTF-8");
          OutputStream out = response.getOutputStream();
          byte[] b = new byte[2048];
          int len;
          while ((len = in.read(b)) != -1) {
              out.write(b, 0, len);
          }
          response.setHeader("Content-Length", String.valueOf(in.available()));
          out.close();
        } catch (FileNotFoundException e) {  
        } finally {  
          if (in != null) {  
            try {  
              in.close();  
            } catch (Exception e) {  
              throw new RuntimeException(e);  
            }  
          }  
        }
	}
}