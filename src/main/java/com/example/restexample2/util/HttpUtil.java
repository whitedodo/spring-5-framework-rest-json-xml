/*
 * 
 * 	Subject: REST Spring framework 5.4
 * 	생성일자(Create Date): 2020-09-28
 * 	파일명(Filename): HttpUtil.java
 *  저자(Author): Dodo(rabbit.white at daum dot net)
 *  설명(Description):
 *   1. 한글 언어 깨짐 문제, Dodo, 2020-09-28
 *  
 * 
 */

package com.example.restexample2.util;

import java.io.UnsupportedEncodingException;

public class HttpUtil {


    // 버그 개선: euc-kr 검증	
    public static boolean isEucKr(String s) {
        int len = s.length();
        char c;
        for (int i = 0; i < len; i++) {
            c = s.charAt(i);
            /// System.out.println("" + c + " = " + toHex(c));
            if (((c & 0xFFFF) >= 0xAC00) && ((c & 0xFFFF) <= 0xD7A3))
                return true;
            /// else if (((c & 0xFF00) != 0) && ((c & 0x00) == 0))
            ///     return false;
        }
        return false;
    }
	
    // 버그 개선: ISO8859-1 검증
    public static boolean isISO8859(String s) {
   	 
        int len = s.length();
        char c;
        for (int i = 0; i < len; i++) {
            c = s.charAt(i);
            /// System.out.println("" + c + " = " + toHex(c));
            if ((c & 0xFF00) != 0)
                return false;
        }
        
        return true;
    }
    
    public static String getISO8859toUTF8(String s) {
    	
    	try {
			return new String(s.getBytes("8859_1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return s;
		}
    	
    }
	
}
