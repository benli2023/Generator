<#include "/macro.include"/>
<#include "/custom.include"/>  
<#assign className = table.className>   
<#assign classNameFirstLower = className?uncap_first> 
<#assign classNameLowerCase = className?lower_case> 
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<%@ include file="../../commons/noty-bottom-right-import.jsp" %>
	<#if table.defineForeignKey>
	<%@ include file="../../commons/opera-maskui-dialog-import.jsp" %>
	<link href="<c:url value="<@jspEl 'ctx'/>/scripts/plugins/popup-input/popup-input.css"/>" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<@jspEl 'ctx'/>/scripts/plugins/popup-input/popup_selection.js"></script>
	</#if>
	<title><%=${className}.TABLE_ALIAS%>新增</title>
</rapid:override>

<rapid:override name="content">
	<form:form method="post" action="<@jspEl "ctx"/>/${classNameLowerCase}" modelAttribute="${classNameFirstLower}" >
		<input id="submitButton" name="submitButton" type="submit" value="提交" />

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
		
		<table class="formTable">
		<%@ include file="form_include.jsp" %>
		</table>
	</form:form>
	<%@ include file="../../commons/ajaxpost-import.jsp" %>
	
	<script>
			function getJsonUrl() {
					return '<@jspEl 'ctx'/>/${classNameLowerCase}/save.json';
			}
			function getPostMethod() {
				return '<@jspEl 'postmode'/>' ;
			}
			function validationCallback(form,result) {
			   return result;
			}
	</script>
	
	<#if table.defineForeignKey>
	<script type="text/javascript">
	 var popupOption={
	 	<#list table.popupOptions as current>
		 '${current.fieldId}': {url:'${current.url}',title:'${current.title}',textColumn:'${current.textColumn}',valueColumn:'${current.valueCoumn}'<#if current.definedCopyColumn>,fields:${current.copyColumnAsString}</#if>}<#if current_has_next>,</#if>
		</#list>
	 };
	 PopupSelection.initOption(popupOption); 	
	</script>
	  <div id="dialog-modal" title="">
        <iframe frameborder="0" style="width:100%;height:99%;height:100%\9;" src="about:blank"></iframe>
    </div>
	</#if>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>
