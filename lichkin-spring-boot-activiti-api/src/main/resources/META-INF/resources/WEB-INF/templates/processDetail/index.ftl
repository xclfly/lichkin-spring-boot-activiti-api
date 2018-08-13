<#include "/macro/html-lichkin-simple.ftl"/>

<@html "",true;section>
  <#if section="javascript-contents-before-links">
	var _userId = '${userId!}';
	var _processType = '${processType!}';
	var _processInstanceId = '${processInstanceId!}';
	var _processState = '${processState!}';
  </#if>
</@html>
