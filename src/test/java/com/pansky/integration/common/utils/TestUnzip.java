package com.pansky.integration.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.List;

import junit.framework.TestCase;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.junit.Test;

import com.pansky.integration.modules.map.entity.MapData;
import com.pansky.integration.modules.map.utils.MapUtils;

public class TestUnzip {

	/**
	 * @param args
	 * @throws IOException 
	 */
	//@Test
	public void unZip() throws IOException {
		String targetFullPath="D:\\apache\\apache-tomcat-6.0.36\\webapps\\site\\temp\\mapZip\\3.zip";
		File tempZ=new File(targetFullPath);
		ZipFile zipFile=new ZipFile(tempZ);
		@SuppressWarnings("rawtypes")
		Enumeration enums = zipFile.getEntries();
		ZipEntry entry = null;
		try {
			while(enums.hasMoreElements()){
				entry = (ZipEntry) enums.nextElement();
				// 从ZIP条目获得输入流
				InputStream in = zipFile.getInputStream(entry);
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(in,"utf-8"));
				StringBuilder sb = new StringBuilder();
				String line = null;
				try {
					while ((line = reader.readLine()) != null) {
						sb.append(line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				List<MapData> l=MapUtils.parseSpotJs(sb.toString());
				TestCase.assertNotSame(l.size(), 0);
			}
		} catch (Exception e) {
		}
	}

}
