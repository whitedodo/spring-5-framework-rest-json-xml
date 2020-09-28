/*
 * 
 * 	Subject: REST Spring framework 5.4
 * 	생성일자(Create Date): 2020-09-28
 * 	파일명(Filename): Greeting.java
 *  저자(Author): Dodo(rabbit.white at daum dot net)
 *  설명(Description):
 *   1. Greeting 모델, Dodo, 2020-09-28
 *  
 * 
 */

package com.example.restexample2.model;

public class Greeting {

	private final long id;
	private final String content;

	public Greeting(long id, String content) {
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}