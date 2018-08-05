<#include "/macro/html-lichkin-h5.ftl"/>

<@html "";section>
  <#if section="link">
    <#-- 引入对应的样式文件 -->
    <@lichkin@cssTag/>
  </#if>
  <#if section="style">
  </#if>
  <#if section="body-attributes">style="background-color:#fff;"</#if>
  <#if section="body-content">
  <div class="lk-app-content" id="lk_app_content">
  </div>
  </#if>
  <#if section="javascript-links">
  	<#-- 引入对应的脚本文件 -->
  	<@lichkin@jsTag/>
    <script type="text/javascript">
	var _processId = '${processId!}';
	var _userId = '${userId!}';
	var _userName = '${userName!}';
	</script>
  </#if>
</@html>
