<#include "/macro/html-lichkin-h5.ftl"/>

<@html "";section>
  <#if section="link">
    <#-- 引入对应的样式文件 -->
    <@lichkin@cssTag/>
  </#if>
  <#if section="style">
  </#if>
  <#if section="body-attributes">style="background-color:#e9f0f5;"</#if>
  <#if section="body-content">
  <div class="lk-app-content" id="lk_app_content">
    <div class="lk-process-detail">
      <div class="top_title">
        <div id="lk-processStartUserName" class="lk-processName"></div>
      </div>
      <section id="cd-timeline" class="cd-container">
      </section>
    </div>
  </div>
  </#if>
  <#if section="javascript-links">
  	<#-- 引入对应的脚本文件 -->
  	<@lichkin@jsTag/>
    <script type="text/javascript">
	var _userId = '${userId!}';
	var _processType = '${processType!}';
	var _processInstanceId = '${processInstanceId!}';
	var _processState = '${processState!}';
	</script>
  </#if>
</@html>
