<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout title="首页">
   <table>
    <tr>
      <th>品牌</th>
      <th>型号</th>
      <th>操作系统</th>
      <th>CPU</th>
      <th>RAM</th>
      <th>存储</th>
      <th>颜色</th>
      <th>价格</th>
    </tr>
    <c:forEach items="${cellphones}" var="cellphone">
      <tr>
        <td>${cellphone.brand}</td>
        <td>${cellphone.model}</td>
        <td>${cellphone.os}</td>
        <td>${cellphone.cpuBrand} ${cellphone.cpuCoreCount}</td>
        <td>${cellphone.ram}</td>
        <td>${cellphone.storage}</td>
        <td>${cellphone.color}</td>
        <td>${cellphone.price}</td>
      </tr>
    </c:forEach>
  </table>
</t:layout>
