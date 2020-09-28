<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>다중 파일 업로드</title>
	<meta charset="UTF-8">
</head>
<body>
<h3>다중 파일 업로드 및 다중 변수</h3>

<form action="file/uploadFileModelAttribute/new" method="POST"
	  enctype="multipart/form-data">
    <table>
        <tr>
            <td>
            	제목:
            	<input type="text" name="subject" >
            </td>
            <td>
            	이름:
            	<input type="text" name="name" >
            </td>
            <td>
            	내용:
            	<input type="text" name="memo" >
            </td>
        </tr>
        <tr>
            <td>Select Files(파일을 선택하시오)</td>
            <td>
            	<input type="file" name="mediaFile" multiple>
            </td>
            <td>
            	<input type="file" name="mediaFile" multiple>
            </td>
        </tr>
        <tr>
        	<td colspan="3">
                <button type="submit">Upload(업로드)</button>
        	</td>
        </tr>
    </table>
</form>
</body>
</html>
