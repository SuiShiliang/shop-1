<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
</head>
<body>
  <h1>注册</h1>
  <form:form action="" method="post" commandName="registerForm">
    <div>
      <label for="username">用户名</label>
      <form:input path="username" />
      <form:errors path="username" />
    </div> 
    
    <div>
      <label for="password">密码</label>
      <form:input path="password" />
      <form:errors path="password" />
    </div>
    
    <div>
      <button type="submit">注册</button>
    </div>
  </form:form>
</body>
</html>