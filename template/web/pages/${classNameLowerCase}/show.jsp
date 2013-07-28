<%@page import="${basepackage}.model.*" %>
<#include "/macro.include"/> 
<#include "/custom.include"/> 
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign classNameLowerCase = className?lower_case> 
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=${className}.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<form:form modelAttribute="${classNameLowerCase}"  >
		<c:choose>
			<c:when test="<@jspEl 'empty postmode'/>">
				<input type="button" value="返回列表" onclick="window.location='<@jspEl 'ctx'/>/${classNameLowerCase}'"/>
				<input type="button" value="后退" onclick="history.back();"/>
			</c:when>
			<c:otherwise>
				<input type="button" value="返回列表" onclick="window.location='<@jspEl 'ctx'/>/${classNameLowerCase}?postmode=<c:out value="<@jspEl 'postmode'/>" />'"/>
				<input type="button" value="关闭" onclick="window.close()"/>
			</c:otherwise>
		</c:choose>
		
	<#list table.columns as column>
	<#if column.pk>
		<input type="hidden" id="${column.columnNameLower}" name="${column.columnNameLower}" value="<@jspEl classNameLower+"."+column.columnNameLower/>"/>
	</#if>
	</#list>
	
		<table class="formTable">
		<#list table.columns as column>
		<#if !column.htmlHidden>
			<tr>	
				<td class="tdLabel"><%=${className}.ALIAS_${column.constantName}%></td>	
				<td><#rt>
				<#compress>
				<#if column.isDateTimeColumn>
				<c:out value='<@jspEl classNameLower+"."+column.columnNameLower+"String"/>'/>
				<#else>
				<c:out value='<@jspEl classNameLower+"."+column.columnNameLower/>'/>
				</#if>
				</#compress>
				<#lt></td>
			</tr>
		</#if>
		</#list>
		</table>
	</form:form>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>