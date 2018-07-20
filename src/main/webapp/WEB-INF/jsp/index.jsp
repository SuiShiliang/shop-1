<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<t:layout title="首页">
  <form:form action="" method="get" commandName="cellphone">
    <div>
      <label for="brand">品牌</label>
      <form:select path="brand">
        <form:option value="">--请选择品牌--</form:option>
        <form:option value="苹果">苹果</form:option>
        <form:option value="锤子">锤子</form:option>
        <form:option value="华为">华为</form:option>
      </form:select>
    </div>
    
    <div>
      <label for="os">操作系统</label>
      <form:select path="os">
        <form:option value="">--请选择操作系统--</form:option>
        <form:option value="Android">Android</form:option>
        <form:option value="IOS">IOS</form:option>
        <form:option value="Windows Phone">Windows Phone</form:option>
      </form:select>      
    </div>
    
    <div>
      <label for="cpuBrand">CPU品牌</label>
      <form:select path="cpuBrand">
        <form:option value="">--请选择CPU品牌--</form:option>
        <form:option value="高通">高通</form:option>
        <form:option value="联发科">联发科</form:option>
      </form:select>
    </div>    
    
    <div>
      <label for=cpuCoreCount>CPU核心数</label>
      <form:input path="cpuCoreCount" type="number" min="1" max="16" />
    </div>
    
    <div>
      <label for=ram>运行内存</label>
      <form:input path="ram" type="number" min="1" max="16" placeholder="GB" />
    </div>
    
    <div>
      <label for=storage>机身存储</label>
      <form:input path="storage" type="number" min="16" max="256" placeholder="GB" />
    </div> 
    
    <div>
      <button type="submit">搜索</button>
    </div>
  </form:form>
  
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
      <th></th>
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
        <td><a href="${contextPath}/cellphones/${cellphone.id}">详情</a></td>
      </tr>
    </c:forEach>
  </table>
</t:layout>
