/*
 * 
 * 	Subject: REST Spring framework 5.4
 * 	생성일자(Create Date): 2020-09-28
 * 	파일명(Filename): HomeController.java
 *  저자(Author): Dodo(rabbit.white at daum dot net)
 *  설명(Description):
 *   1. REST Client 구현함, Dodo, 2020-09-28
 *  
 * 
 */

package com.example.restexample2.controller;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.restexample2.model.Board;
import com.example.restexample2.model.Greeting;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate.Param;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

    /**
     * 파일 업로드 입력 화면
     */
	@RequestMapping(value = "/fileUploadView", method = RequestMethod.GET)
	public String fileUploadView(Locale locale, Model model) {
		
		logger.info("Welcome FileUpload! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "file/upload";
	}


    /**
     * GET 방식 - 클라이언트
     */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/client/listMapGet", method = RequestMethod.GET)
	public String helloClient(String regId, String time) throws UnsupportedEncodingException{
		
		// Get 응답 방법론
		String url = "http://localhost:8080/restexample2/testValue2";
        String serviceKey = "서비스키";
        String decodeServiceKey = URLDecoder.decode(serviceKey, "UTF-8");
        
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));    //Response Header to UTF-8  
        
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("serviceKey", decodeServiceKey)
                .queryParam("regId", regId)
                .queryParam("tmFc", time)
                .queryParam("_type", "json")
                .build(false);    //자동으로 encode해주는 것을 막기 위해 false
        
        //Object response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<String>(headers), String.class);
        
        // Object response = restTemplate.getForEntity(builder.toUriString(), List.class);
        Object response = restTemplate.getForObject(builder.toUriString(), List.class);
        
        if ( response != null) {

            List<Integer> map = (List<Integer>) response;
        	System.out.println("map" + map);
        }
        
        //return response;

		
		return "home";
	}

    /**
     * POST 방식 - 클라이언트
     */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/client/listMapPost")
	public String helloClient2(String regId, String time) throws UnsupportedEncodingException{
		
		// POST 응답 방법
		//String url = "http://..............";
		String url = "http://localhost:8080/restexample2/testValue2";
        String serviceKey = "서비스키";
        String decodeServiceKey = URLDecoder.decode(serviceKey, "UTF-8");
        
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));    //Response Header to UTF-8  
        
        /*
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("serviceKey", decodeServiceKey)
                .queryParam("regId", regId)
                .queryParam("tmFc", time)
                .queryParam("_type", "json")
                .build(false);    //자동으로 encode해주는 것을 막기 위해 false
        */
        
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("servicekey", decodeServiceKey);
        parameters.add("regId", regId);
        parameters.add("tmFc", time);
        parameters.add("_type", "json");
        
        //Object response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<String>(headers), String.class);
        
        // Object response = restTemplate.getForEntity(builder.toUriString(), List.class);
        Object response = restTemplate.postForObject(url, parameters, List.class);
        // Object response = restTemplate.postForEntity(url, parameters, List.class);
        
        if ( response != null) {

            List<Integer> map = (List<Integer>) response;
        	System.out.println("map" + map.get(0));
        }
        
        //return response;

		
		return "home";
	}
	
    /**
     * PUT 방식 - 클라이언트
     */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/client/listMapPut/{boardIdx}")
	public String helloClient3(String regId, 
											String time,
											@PathVariable(name="boardIdx", required=true) int boardIdx)
													throws  UnsupportedEncodingException{
		
		// PUT 방법
		String url = "http://localhost:8080/restexample2/v1/api/board/2";
        String serviceKey = "서비스키";
        String decodeServiceKey = URLDecoder.decode(serviceKey, "UTF-8");
        
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));    //Response Header to UTF-8  
        
        // 파라메터
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("servicekey", decodeServiceKey);
        parameters.add("regId", regId);
        parameters.add("tmFc", time);
        parameters.add("_type", "json");
        
        //Object response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<String>(headers), String.class);
        
        Board updatedBoard = new Board();
        updatedBoard.setId(boardIdx);
        updatedBoard.setSubject("앙하하");
        updatedBoard.setName("수정했다.");
        updatedBoard.setMemo("메모지");
        
        
        // Object response = restTemplate.getForEntity(builder.toUriString(), List.class);
        //Object response = restTemplate.postForObject(url, parameters, List.class);
        restTemplate.put ( url, updatedBoard, parameters );
        
        // 데이터 확인하기
        url = "http://localhost:8080/restexample2/v1/api/board";

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("serviceKey", decodeServiceKey)
                .build(false);

        Board[] response = restTemplate.getForObject(builder.toUriString(), Board[].class );
        // Object response = restTemplate.postForEntity(url, parameters, List.class);
        
        if ( response != null) {

            List<Board> listData = Arrays.asList(response);
        	System.out.println("list(Size):" + listData.size());
        	
        	try {
	        	if ( listData.size() != 0 && (boardIdx - 1) >= 0) {
	            	System.out.println("boardIdx:" + (boardIdx - 1));
	        		Board boardTmp = listData.get(boardIdx - 1);
	        		System.out.println("board:" + boardTmp.getId() + "/" + boardTmp.getSubject());
	        	}
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        	
        }
        
        //return response;
		return "home";
	}
	
	

    /**
     * PUT 방식 - 클라이언트
     */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/client/listMapDelete/{boardIdx}")
	public String helloClient4(String regId, 
											String time,
											@PathVariable(name="boardIdx", required=true) int boardIdx)
													throws  UnsupportedEncodingException{
		
		// PUT 방법
		String url = "http://localhost:8080/restexample2/v1/api/board/" + boardIdx;
        String serviceKey = "서비스키";
        String decodeServiceKey = URLDecoder.decode(serviceKey, "UTF-8");
        
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));    //Response Header to UTF-8  
        
        // 파라메터
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("servicekey", decodeServiceKey);
        parameters.add("regId", regId);
        parameters.add("tmFc", time);
        parameters.add("_type", "json");
        
        //Object response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<String>(headers), String.class);
        
        // Object response = restTemplate.getForEntity(builder.toUriString(), List.class);
        //Object response = restTemplate.postForObject(url, parameters, List.class);
        restTemplate.delete ( url, parameters );
        
        // 데이터 확인하기
        url = "http://localhost:8080/restexample2/v1/api/board";

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("serviceKey", decodeServiceKey)
                .build(false);

        Board[] response = restTemplate.getForObject(builder.toUriString(), Board[].class );
        // Object response = restTemplate.postForEntity(url, parameters, List.class);
        
        if ( response != null) {

            List<Board> listData = Arrays.asList(response);
        	System.out.println("list(Size):" + listData.size());
        	
        	try {
	        	if ( listData.size() != 0 && (boardIdx - 1) >= 0) {
	            	System.out.println("boardIdx:" + (boardIdx - 1));
	        		Board boardTmp = listData.get(boardIdx - 1);
	        		System.out.println("board:" + boardTmp.getId() + "/" + boardTmp.getSubject());
	        	}
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        	
        }
        
        //return response;	
		return "home";
		
	}
	
	
}
