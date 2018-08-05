$(function() {
  loadProcessList();

  $('#apply_btn').click(function() {
    loadProcessList();
  });

  $('#pending_btn').click(function() {
    $('#lk_activiti_ul').html('');
    LK.ajax({
      url : '/UserEmployee/Activiti/GetPendingProcess',
      data : {
        userId : _userId
      },
      success : function(responseDatas) {
        $('#lk_activiti_ul').html('');
        if (responseDatas) {
          for (var i = 0; i < responseDatas.length; i++) {
            var ary = responseDatas[i];
            var $li = $('<li></li>').appendTo($('#lk_activiti_ul'));

            $('<div class="lk-processName">' + ary.processName + '</div>').appendTo($li);
            $('<div class="lk-processStartUserName"><lable>' + LK.i18n.processStartUserName + '：</lable>' + ary.processStartUserName + '</div>').appendTo($li);
            $('<div class="lk-taskStartTime"><lable>' + LK.i18n.taskStartTime + '：</lable>' + ary.taskStartTime + '</div>').appendTo($li);

            (function(processInstanceId) {
              $li.click(function() {
                window.location.href = _CTX + '/processDetail/index' + _MAPPING_PAGES + '?processType=SINGLE_LINE&processInstanceId=' + processInstanceId + '&processState=pending';
              });
            })(ary.processInstanceId);
          }
        }
      }
    });
  });

  $('#done_btn').click(function() {
    LK.ajax({
      url : '/UserEmployee/Activiti/GetDoneProcess',
      data : {
        userId : _userId
      },
      success : function(responseDatas) {
        $('#lk_activiti_ul').html('');
        if (responseDatas) {
          for (var i = 0; i < responseDatas.length; i++) {
            var ary = responseDatas[i];
            var $li = $('<li></li>').appendTo($('#lk_activiti_ul'));

            $('<div class="lk-processName">' + ary.processName + '</div>').appendTo($li);
            $('<div class="lk-processStartUserName"><lable>' + LK.i18n.processStartUserName + '：</lable>' + ary.processStartUserName + '</div>').appendTo($li);
            $('<div class="lk-taskStartTime"><lable>' + LK.i18n.taskStartTime + '：</lable>' + ary.processStartTime + '</div>').appendTo($li);
            $('<div class="lk-taskEndTime"><lable>' + LK.i18n.taskEndTime + '：</lable>' + ary.taskEndTime + '</div>').appendTo($li);
            if (ary.processIsEnd) {
              $('<div class="lk-processStatus"><lable>' + LK.i18n.processStatus + '：</lable>' + LK.i18n.PassTheAudit + '</div>').appendTo($li);
              $('<div class="lk-processEndTime"><lable>' + LK.i18n.processEndTime + '：</lable>' + ary.processEndTime + '</div>').appendTo($li);
            } else {
              $('<div class="lk-processStatus"><lable>' + LK.i18n.processStatus + '：</lable>' + ary.activeTaskName + '</div>').appendTo($li);
            }

            (function(processInstanceId) {
              $li.click(function() {
                window.location.href = _CTX + '/processDetail/index' + _MAPPING_PAGES + '?processType=SINGLE_LINE&processInstanceId=' + processInstanceId + '&processState=done';
              });
            })(ary.processInstanceId);
          }
        }
      }
    });
  });

});

function loadProcessList() {
  $('#lk_activiti_ul').html('');
  LK.ajax({
    url : '/UserEmployee/Activiti/GetProcessList',
    data : {
      deptId : _deptId
    },
    success : function(responseDatas) {
      if (responseDatas) {
        for (var i = 0; i < responseDatas.length; i++) {
          var ary = responseDatas[i];
          var $li = $('<li>' + ary.processName + '</li>').appendTo($('#lk_activiti_ul'));
          (function(processId) {
            $li.click(function() {
              window.location.href = _CTX + '/startProcess/index' + _MAPPING_PAGES + '?processId=' + processId;
            });
          })(ary.processId);
        }
      }
    }
  });
}
