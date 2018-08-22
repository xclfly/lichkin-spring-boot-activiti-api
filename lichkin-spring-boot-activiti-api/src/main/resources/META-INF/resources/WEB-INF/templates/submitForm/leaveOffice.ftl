<#include "/macro/html-lichkin-h5.ftl"/>

<@html "",true;section>
  <#if section="javascript-contents-before-links">
	var _processId = '${processId!}';
    var _processCode = '${processCode!}';
    var _formId = '${formId!}';
    var _userId = '${userId!}';
    var _userName = '${userName!}';
    var _deptName = '${deptName!}';
    var _entryDate = '${entryDate!}';
  </#if>
  <#if section="javascript-links">
    <@lichkin@jsTag url="/res/js/submitForm/common.js" />
  </#if>
</@html>
