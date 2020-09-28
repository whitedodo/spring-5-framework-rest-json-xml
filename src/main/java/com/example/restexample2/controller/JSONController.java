/*
 * 
 * 	Subject: REST Spring framework 5.4
 * 	생성일자(Create Date): 2020-09-28
 * 	파일명(Filename): JSONController.java
 *  저자(Author): Dodo(rabbit.white at daum dot net)
 *  설명(Description):
 *   1. REST 입문 - 간단한 출력, Dodo, 2020-09-28
 *  
 * 
 */

package com.example.restexample2.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restexample2.model.Greeting;

/**
 * Handles requests for the application home page.
 */
@RestController
public class JSONController {
	
	private static final Logger logger = LoggerFactory.getLogger(JSONController.class);

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
	
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	@GetMapping(value="/testValue") 
	public String getTestValue(){
		String TestValue = "레스트컨트롤러 테스트";
		return TestValue;
	}

	@GetMapping(value="/testValue2") 
	public List<Integer> getTestValue2(){
		
		List<Integer> mList = new ArrayList<Integer>();
		
		mList.add(1);
		mList.add(2);
		mList.add(3);
		mList.add(4);
		
		return mList;
		
		/* 
		 * Error: pom.xml의 jackson-databind, jackson-core 추가할 것
		 * onverter found for return value of type: class java.util.ArrayList]
		 */
	}
	
	@PostMapping(value="/testValue2") 
	public List<Integer> getTestValue3(){
		
		List<Integer> mList = new ArrayList<Integer>();
		
		mList.add(1);
		mList.add(2);
		mList.add(3);
		mList.add(4);
		
		return mList;
		
		/* 
		 * Error: pom.xml의 jackson-databind, jackson-core 추가할 것
		 * onverter found for return value of type: class java.util.ArrayList]
		 */
	}
	
	@GetMapping(value="/getMap")
	public Map<String, Greeting> getMap(){
		Map<String, Greeting> map = new HashMap<>();
		
		map.put("First", new Greeting(1, "Hello"));
		map.put("Second", new Greeting(2, "Rest"));
		
		return map;
	}
	
}
