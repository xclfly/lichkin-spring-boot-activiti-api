<#include "/macro/html-lichkin-simple.ftl"/>

<@html "",true;section>
  <#if section="javascript-contents-before-links">
	var _deptId = '${deptId!}';
	var _userId = '${userId!}';
  </#if>
  <#if section="javascript-links">
    <@lichkin@jsTag url="/res/js/common.js" />
  </#if>
</@html>
