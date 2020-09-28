/*
 * 
 * 	Subject: REST Spring framework 5.4
 * 	생성일자(Create Date): 2020-09-28
 * 	파일명(Filename): FileController.java
 *  저자(Author): Dodo(rabbit.white at daum dot net)
 *  설명(Description):
 *  1. REST 기반의 파일업로드 시스템 구현, 도도(Dodo), 2020-09-28
 * 
 */

package com.example.restexample2.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.restexample2.model.Board;
import com.example.restexample2.model.FileInfo;
import com.example.restexample2.util.HttpUtil;

@RestController
@RequestMapping ("/file")
public class FileController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
     
     /**
      * 파일 멀티파트 업로드 Rest
      * {/
      * @param inputFile
      * @return 
      *  (주석 스타일 참고)
      */
     @RequestMapping(value = "/uploadFileModelAttribute/new", 
    		 						method = {RequestMethod.POST },
    		 						produces="text/plain;charset=UTF-8")
     public String multiFileUpload(@ModelAttribute Board boardVO, 
    		 												@RequestParam("mediaFile")MultipartFile[] files, 
    		 												Model model,
    		 												HttpServletRequest req,
    		 												HttpServletResponse res) throws IOException {
    	 
    	 boolean filechk = false;
    	 
         //디스크상의 프로젝트 실제 경로얻기
         //String contextRootPath = "c:" + File.separator + "upload";
    	 // String charset = "UTF-8";

    	 // req.setAttribute("charset", charset);
    	 // req.setCharacterEncoding(charset);
    	 // res.setContentType("text/html; charset=" + charset);
    	 
    	 String dirName = "upload" ; 
		 String contextRootPath = req.getSession().getServletContext().getRealPath("/") + dirName;
		 
         System.out.println("실제경로:" + contextRootPath);

         //1. 메모리나 파일로 업로드 파일 보관하는 FileItem의 Factory 설정
         DiskFileItemFactory diskFactory = new DiskFileItemFactory(); //디스크 파일 아이템 공장
         diskFactory.setSizeThreshold(4096); //업로드시 사용할 임시 메모리
         diskFactory.setRepository(new File(contextRootPath + "/WEB-INF/temp")); //임시저장폴더
         
         //2. 업로드 요청을 처리하는 ServletFileUpload생성
         ServletFileUpload upload = new ServletFileUpload(diskFactory);
         upload.setSizeMax(3 * 1024 * 1024); //3MB : 전체 최대 업로드 파일 크기
         
         
         // 한글 깨짐 해결(버그)
         // String kor_a = new String(boardVO.getSubject().getBytes("8859_1"), "UTF-8");		 
         System.out.println("게시물제목:" + HttpUtil.getISO8859toUTF8( boardVO.getSubject()) );
		 System.out.println("게시물작성자:" + HttpUtil.getISO8859toUTF8( boardVO.getName()) );
		 System.out.println("게시물내용:" + HttpUtil.getISO8859toUTF8( boardVO.getMemo()) );
		 System.out.println("파일(길이):" + files.length );
		 
		 
         for(MultipartFile mFile : files) {

             // 3. 파일 가져오기
    		 if ( mFile.getOriginalFilename().isEmpty() && 
    			  filechk == false ) {

         	  	String msg = "Please select at least one mediaFile.<br/>(미디어 파일을 하나 이상 선택하십시오.)";
         	  	model.addAttribute("msg", msg);
         	  	
         	  	return model.getAttribute("msg").toString();
    		 }
    		 
             // 4. 파일명 - 현재시간으로 생성
             String uploadedFileName = System.currentTimeMillis() + ""; 
             
        	 if (!mFile.getOriginalFilename().isEmpty()) {
        		 
        		 	BufferedOutputStream outputStream = new BufferedOutputStream(
        		 			new FileOutputStream(
        		 					new File( contextRootPath + File.separator + "upload" + File.separator, uploadedFileName )
		 					));
        		 	
        		 	
                 	System.out.println("파일명:" + mFile.getOriginalFilename());
                 	
                 	outputStream.write(mFile.getBytes());
                 	outputStream.flush();
                 	outputStream.close();
                 	
                 	filechk = true;                 	
              } 
        	 
         }
         
    	return "fileUploadForm";
    	
     }
     
}