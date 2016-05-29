package com.sell.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sell.dao.SellDao;


public class WebUtils {

	public static boolean doUpload(HttpServletRequest request, String uppath) throws FileSizeLimitExceededException {
		try{
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(new File(request.getSession().getServletContext().getRealPath("/temp")));
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			upload.setFileSizeMax(1000*1000*500);
			List<FileItem> list = upload.parseRequest(request);
			String id=request.getParameter("id");
			String mPath="";
			
			for(FileItem item : list){
				if(item.isFormField()){
					String name = item.getFieldName();  //username=aaa  description=bbb
					String value = item.getString("UTF-8");
					id =value;
				}else{
					//�õ��ϴ��ļ���
					String filename = item.getName().substring(item.getName().lastIndexOf("\\")+1);
					//�õ��ļ��ı������
					String uuidname = generateFilename(filename);
					//�õ��ļ��ı���·��
					String savepath = generateSavePath(uppath,uuidname);
					
					InputStream in = item.getInputStream();
					int len = 0;
					byte buffer[] = new byte[1024];
					FileOutputStream out = new FileOutputStream(savepath + "\\" + uuidname);
					while((len=in.read(buffer))>0){
						out.write(buffer, 0, len);
					}
					in.close();
					out.close();
					item.delete();
					mPath = (savepath + "\\" + uuidname).substring((savepath + "\\" + uuidname).indexOf("upload"));
				}
			}
			SellDao mSellDao = new SellDao();
			mSellDao.update(id,mPath);
			return true;
		}catch (FileUploadBase.FileSizeLimitExceededException e) {
			throw e;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static String generateFilename(String filename){
		String ext = filename.substring(filename.lastIndexOf(".")+1);
		return UUID.randomUUID().toString()+ "." + ext;
	}
	
	private static String generateSavePath(String path,String filename){
		int hashcode = filename.hashCode();  //121221
		int dir1 = hashcode&15;
		int dir2 = (hashcode>>4)&0xf;
		
		String savepath = path + File.separator + dir1 + File.separator + dir2;
		File file = new File(savepath);
		if(!file.exists()){
			file.mkdirs();
		}
		return savepath;
	}

}
