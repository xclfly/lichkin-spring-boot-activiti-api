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
        var $lkAppContent = $('<div class="lk-app-content"></div>').appendTo($('body'));
        var $topTitle = $('<div class="top_title"></div>').appendTo($lkAppContent);
        var $processStartUserName = $('<div class="lk-processName"></div>').appendTo($topTitle);
        var $timeline = $('<section id="cd-timeline" class="cd-container"></section>').appendTo($lkAppContent);

        $processStartUserName.html(responseDatas[0].processName);
        for (var i = 0; i < responseDatas.length; i++) {
          var taskInfo = responseDatas[i];
          var $timelineBlock = $('<div class="cd-timeline-block"></div>').appendTo($timeline);
          var $timelineImg = $('<div class="cd-timeline-img"><img src="' + _CTX + '/res/img/processDetail/cd-icon-location.svg"></div>').appendTo($timelineBlock);
          var $timelineContent = $('<div class="cd-timeline-content"><h3>' + taskInfo.taskName + '</h3><p>' + taskInfo.approveUserName + '</p></div>').appendTo($timelineBlock);

          if (taskInfo.taskStartTime == null) {// 未到节点
            $timelineImg.addClass('cd-notStarted');
          } else if (taskInfo.taskStartTime != null && taskInfo.taskEndTime == null) {// 到此节点但未处理
            $timelineImg.addClass('cd-pending');
          } else if (taskInfo.taskEndTime != null) {// 节点已结束
            if (taskInfo.deleteReason) {
              $timelineImg.addClass('cd-reject');
              $('<p>' + taskInfo.deleteReason + '</p><p>' + taskInfo.taskEndTime + '</p>').appendTo($timelineContent);
            } else {
              $timelineImg.addClass('cd-done');
              $('<p>' + (taskInfo.taskComment == null ? '' : taskInfo.taskComment) + '</p><p>' + taskInfo.taskEndTime + '</p>').appendTo($timelineContent);
            }
          }
        }

        if (_processState == 'pending') {
          $('<div class="lk-app-form">' + $.LKGetI18N('Comments') + '：<textarea style="width:300px;height:50px;" id="comment"></textarea></div>').appendTo($lkAppContent);
          var $btns = $('<div class="lk-app-form-btns"></div>').appendTo($lkAppContent);
          var $submitBtn = $('<div class="lk-app-btn lk-app-agree-btn">' + $.LKGetI18N('Agree') + '</div>').appendTo($btns);
          var $rejectBtn = $('<div class="lk-app-btn lk-app-reject-btn">' + $.LKGetI18N('Reject') + '</div>').appendTo($btns);

          var $cancelBtn = $('<div class="lk-app-btn lk-app-cancel-btn" id="cancel_btn">' + $.LKGetI18N('cancel') + '</div>').appendTo($btns);
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

          $rejectBtn.click(function() {
            LK.ajax({
              url : '/UserEmployee/Activiti/RejectProcess',
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
