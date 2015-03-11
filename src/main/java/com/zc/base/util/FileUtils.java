/**  
 * <b>项目名：</b>表单自定义系统<br/>  
 * <b>包名：</b>com.winston.base.util.impl<br/>  
 * <b>文件名：</b>FileUtils.java<br/>  
 * <b>版本信息：</b><br/>  
 * <b>日期：</b>2013-10-8-上午10:32:23<br/>  
 * <b>Copyright (c)</b> 2013太极开放公司-版权所有<br/>   
 */
package com.zc.base.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

/**
 * <b>类名称：</b>FileUtils<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>Winston<br/>
 * <b>修改人：</b>Winston<br/>
 * <b>修改时间：</b>2013-10-8 上午10:32:23<br/>
 * <b>修改备注：</b><br/>
 * 
 * @version 1.0.0<br/>
 */
public class FileUtils {
	protected static final Log log = LogFactory.getLog(FileUtils.class);

	/**
	 * @param path
	 *            文件路径
	 * @param suffix
	 *            后缀名, 为空则表示所有文件
	 * @param isdepth
	 *            是否遍历子目录
	 * @return list
	 */
	public static List<String> getFileList(String dir, String suffix, boolean isdepth) {
		Assert.hasText(dir);
		File file = new File(dir);
		Assert.notNull(file);
		Assert.isTrue(file.isDirectory());
		List<String> fileList = new ArrayList<String>();
		return getFileList(fileList, file, suffix, isdepth);
	}

	private static List<String> getFileList(List<String> fileList, File file, String suffix, boolean isdepth) {
		// 若是目录, 采用递归的方法遍历子目录
		if (file.isDirectory()) {
			File[] t = file.listFiles();

			for (int i = 0; i < t.length; i++) {
				if (isdepth || t[i].isFile()) {
					getFileList(fileList, t[i], suffix, isdepth);
					log.debug(file.getAbsolutePath());
				}
			}
		} else {
			String filePath = file.getAbsolutePath();
			if (!suffix.equals("")) {
				int begIndex = filePath.lastIndexOf("."); // 最后一个.(即后缀名前面的.)的索引
				String tempsuffix = "";

				if (begIndex != -1) {
					tempsuffix = filePath.substring(begIndex + 1, filePath.length());
					if (tempsuffix.equals(suffix)) {
						fileList.add(filePath);
						log.debug(file.getAbsolutePath());
					}
				}
			} else {
				fileList.add(filePath);
				log.debug(file.getAbsolutePath());
			}
		}
		return fileList;
	}

	public static String readFile(File file, long position, long size) throws IOException {
		FileChannel fc = null;
		StringBuffer str = new StringBuffer();
		MappedByteBuffer mbb = null;
		try {
			fc = new RandomAccessFile(file, "r").getChannel();
			mbb = fc.map(MapMode.READ_ONLY, position, size);
			//mbb.flip();

			for (int j = 0; j < size; j++) {
				str.append((char) mbb.get(j));
			}
		} catch (Exception ex) {
			log.error("", ex);
		} finally {
			fc.close();
			mbb = null;
		}

		return str.toString();
	}
	 /**保存文件
     * @param stream
     * @param path
     * @param filename
     * @throws IOException
     */
    public static String saveFileFromInputStream(InputStream stream,String path,String filename) throws IOException
    {
    	String location= "images/content/"+new FileUtils().randomName() + filename.substring(filename.lastIndexOf("."));
    	//System.out.println(path + location);
    	File f = new File(path + location);
    	if(!f.exists()){
    		f.getParentFile().mkdirs();
    	}
        FileOutputStream fs=new FileOutputStream(path + location);
        byte[] buffer =new byte[1024*1024];
        int bytesum = 0;
        int byteread = 0; 
        while ((byteread=stream.read(buffer))!=-1)
        {
           bytesum+=byteread;
           fs.write(buffer,0,byteread);
           fs.flush();
        } 
        fs.close();
        stream.close();
        return location.replace("\\", "/");
    }
    public static String saveFileFromInputStreamBypath(InputStream stream,String path,String filename) throws IOException
    {
    	String location= new FileUtils().randomName() + filename.substring(filename.lastIndexOf("."));
    	//System.out.println(path + location);
    	File f = new File(path + location);
    	if(!f.exists()){
    		f.getParentFile().mkdirs();
    	}
        FileOutputStream fs=new FileOutputStream(path + location);
        byte[] buffer =new byte[1024*1024];
        int bytesum = 0;
        int byteread = 0; 
        while ((byteread=stream.read(buffer))!=-1)
        {
           bytesum+=byteread;
           fs.write(buffer,0,byteread);
           fs.flush();
        } 
        fs.close();
        stream.close();
        return location.replace("\\", "/");
    }
    private String randomName(){
    	Date date = new Date();
    	StringBuilder str = new StringBuilder();
    	str.append(date.getYear());
    	str.append(File.separator);
    	str.append(date.getMonth()+1);
    	str.append(File.separator);
    	str.append(date.getDate());
    	str.append(File.separator);
    	str.append(date.getTime());
    	return str.toString();
    }
    public static void main(String[]args){
    	
    }
}
