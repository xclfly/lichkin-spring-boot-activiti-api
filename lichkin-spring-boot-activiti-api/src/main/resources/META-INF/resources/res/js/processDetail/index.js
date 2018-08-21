$(function() {
  LK.ajax({
    url : '/UserEmployee/Activiti/GetDetailProcess',
    data : {
      processType : _processType,
      processInstanceId : _processInstanceId
    },
    success : function(responseDatas) {
      if (responseDatas) {
        var $lkAppContent = $('<div class="lk-app-content"></div>').appendTo($('body'));
        var $topTitle = $('<div class="top_title"></div>').appendTo($lkAppContent);
        var $processStartUserName = $('<div class="lk-processName"></div>').appendTo($topTitle);
        var $timeline = $('<section id="cd-timeline" class="cd-container"></section>').appendTo($lkAppContent);

        $processStartUserName.html(responseDatas[0].processName);
        // 业务表单ID
        var businessKey = responseDatas[0].businessKey;

        var processConfigId;
        var currentStep;
        var currentTaskName;
        for (var i = 0; i < responseDatas.length; i++) {
          var taskInfo = responseDatas[i];
          var $timelineBlock = $('<div class="cd-timeline-block"></div>').appendTo($timeline);
          var $timelineImg = $('<div class="cd-timeline-img"><img src="' + _CTX + '/res/img/processDetail/cd-icon-location.svg"></div>').appendTo($timelineBlock);
          var $timelineContent = $('<div class="cd-timeline-content"><h3>' + taskInfo.taskName + '</h3><p>' + taskInfo.approveUserName + '</p></div>').appendTo($timelineBlock);

          if (taskInfo.taskStartTime == null) {// 未到节点
            $timelineImg.addClass('cd-notStarted');
          } else if (taskInfo.taskStartTime != null && taskInfo.taskEndTime == null) {// 到此节点但未处理
            processConfigId = taskInfo.processConfigId;
            currentStep = taskInfo.taskId;
            currentTaskName = taskInfo.taskName;
            $timelineImg.addClass('cd-pending');
          } else if (taskInfo.taskEndTime != null) {// 节点已结束
            if (taskInfo.deleteReason) {
              $timelineImg.addClass('cd-reject');
              $('<p>' + $.LKGetI18N('Reject') + '</p><p>' + taskInfo.taskEndTime + '</p>').appendTo($timelineContent);
            } else {
              $timelineImg.addClass('cd-done');
              $('<p>' + (taskInfo.taskComment == null ? '' : taskInfo.taskComment) + '</p><p>' + taskInfo.taskEndTime + '</p>').appendTo($timelineContent);
            }
          }
        }
      }
    }
  });

});
