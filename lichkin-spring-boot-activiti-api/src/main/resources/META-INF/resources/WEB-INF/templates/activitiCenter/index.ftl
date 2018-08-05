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
  <div class="lk-app-content">
  <div id="tab_btns" class="lk-tab_btns">
    <div class="lk-tab-btn" id="apply_btn">申请</div>
    <div class="lk-tab-btn" id="pending_btn">待办</div>
    <div class="lk-tab-btn" id="done_btn">已办</div>
  </div>
  <div id="tab_content" class="lk-tab-content">
  <ul id="lk_activiti_ul">
  </ul>
  </div>
  </div>
  </#if>
  <#if section="javascript-links">
  	<#-- 引入对应的脚本文件 -->
  	<@lichkin@jsTag/>
    <script type="text/javascript">
	var _deptId = '${deptId!}';
	var _userId = '${userId!}';
	</script>
  </#if>
</@html>
