$(function() {
  LK.ajax({
    url : '/UserEmployee/Activiti/GetDetailProcess',
    data : {
      userId : _userId,
      processType : _processType,
      processInstanceId : _processInstanceId
    },
    success : function(responseDatas) {
      if (responseDatas) {
        $('#lk-processStartUserName').html(responseDatas[0].processName);
        for (var i = 0; i < responseDatas.length; i++) {
          var taskInfo = responseDatas[i];
          var $timelineBlock = $('<div class="cd-timeline-block"></div>').appendTo($('#cd-timeline'));
          var $timelineImg = $('<div class="cd-timeline-img"><img src="' + _CTX + '/res/img/processDetail/cd-icon-location.svg"></div>').appendTo($timelineBlock);
          var $timelineContent = $('<div class="cd-timeline-content"><h2>' + taskInfo.taskName + '</h2><p>' + taskInfo.approveUserName + '</p></div>').appendTo($timelineBlock);

          if (taskInfo.taskStartTime == null) {// 未到节点
            $timelineImg.addClass('cd-notStarted');
          } else if (taskInfo.taskStartTime != null && taskInfo.taskEndTime == null) {// 到此节点但未处理
            $timelineImg.addClass('cd-pending');
          } else if (taskInfo.taskEndTime != null) {// 节点已结束
            $timelineImg.addClass('cd-done');
            $('<p>' + (taskInfo.taskComment == null ? '' : taskInfo.taskComment) + '</p><p>' + taskInfo.taskEndTime + '</p>').appendTo($timelineContent);
          }
        }

        if (_processState == 'pending') {
          $('<div class="lk-app-form">' + $.LKGetI18N('Comments') + '：<textarea style="width:300px;height:50px;" id="comment"></textarea></div>').appendTo($('#lk_app_content'));
          var $btns = $('<div class="lk-app-form-btns"></div>').appendTo($('#lk_app_content'));
          var $submitBtn = $('<div class="lk-app-btn" id="submit_btn">' + $.LKGetI18N('ok') + '</div>').appendTo($btns);
          var $cancelBtn = $('<div class="lk-app-btn" id="cancel_btn">' + $.LKGetI18N('cancel') + '</div>').appendTo($btns);
          $submitBtn.click(function() {
            LK.ajax({
              url : '/UserEmployee/Activiti/CompleteProcess',
              data : {
                userId : _userId,
                processType : _processType,
                processInstanceId : _processInstanceId,
                comment : $('#comment').val()
              },
              success : function(responseDatas) {
                if (responseDatas) {
                  LK.alert('The examination and approval success');
                  window.history.back();
                }
              }
            });
          });

          $cancelBtn.click(function() {
            window.history.back();
          });
        }

      }
    }
  });

});
