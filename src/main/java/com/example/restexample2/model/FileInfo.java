/*
 * 
 * 	Subject: REST Spring framework 5.4
 * 	생성일자(Create Date): 2020-09-28
 * 	파일명(Filename): FileInfo.java
 *  저자(Author): Dodo(rabbit.white at daum dot net)
 *  설명(Description):
 *   1. 파일 정보 모델, Dodo, 2020-09-28
 *  
 * 
 */

package com.example.restexample2.model;

import org.springframework.web.multipart.MultipartFile;

public class FileInfo {

	private long num;
	private String filename;
	private long filesize;
	private MultipartFile mediaFile;
	

	public long getNum() {
		return num;
	}
	
	public void setNum(long num) {
		this.num = num;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public long getFilesize() {
		return filesize;
	}
	
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	
	public MultipartFile getMediaFile() {
		return mediaFile;
	}

	public void setMediaFile(MultipartFile mediaFile) {
		this.mediaFile = mediaFile;
	}
	
}
