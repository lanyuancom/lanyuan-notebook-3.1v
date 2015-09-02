package com.lanyuan.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownloadUtils {
	private static Logger logger = LoggerFactory.getLogger(DownloadUtils.class);
	public static void download(String fileName,String filepath, HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("UTF-8");
			java.io.BufferedInputStream bis = null;
			java.io.BufferedOutputStream bos = null;
	
			String ctxPath = request.getSession().getServletContext().getRealPath("/") +filepath;
			try {
				File file = new File(ctxPath);
				if(!file.exists())
					logger.warn("download agent is error ! messages --->> "+ctxPath+" is not exists !");
				response.setContentType("application/x-msdownload;");
				response.setHeader("Content-disposition",
						"attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
				response.setHeader("Content-Length", String.valueOf(file.length()));
				bis = new BufferedInputStream(new FileInputStream(ctxPath));
				bos = new BufferedOutputStream(response.getOutputStream());
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (Exception e) {
				logger.warn("download agent is error ! messages --->> "+e.fillInStackTrace());
			} finally {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			}
		}
}

