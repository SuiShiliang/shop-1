<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout title="首页">
  <form action="" method="get">
    <div>
      <label for="brand">品牌</label>
      <select name="brand">
        <option value="">--请选择品牌--</option>
        <option value="苹果">苹果</option>
        <option value="锤子">锤子</option>
        <option value="华为">华为</option>
      </select>
    </div>
    
    <div>
      <label for="os">操作系统</label>
      <select name="os">
        <option value="">--请选择操作系统--</option>
        <option value="Android">Android</option>
        <option value="IOS">IOS</option>
        <option value="Windows Phone">Windows Phone</option>
      </select>      
    </div>
    
    <div>
      <label for="cpuBrand">CPU品牌</label>
      <select name="cpuBrand">
        <option value="">--请选择CPU品牌--</option>
        <option value="高通">高通</option>
        <option value="联发科">联发科</option>
      </select>
    </div>    
    
    <div>
      <label for=cpuCoreCount>CPU核心数</label>
      <input name="cpuCoreCount" type="number" min="1" max="16" />
    </div>
    
    <div>
      <label for=ram>运行内存</label>
      <input name="ram" type="number" min="1" max="16" placeholder="GB" />
    </div>
    
    <div>
      <label for=storage>机身存储</label>
      <input name="storage" type="number" min="16" max="256" placeholder="GB" />
    </div>    
    
    <div>
      <button type="submit">搜索</button>
    </div>
  </form>
  
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
