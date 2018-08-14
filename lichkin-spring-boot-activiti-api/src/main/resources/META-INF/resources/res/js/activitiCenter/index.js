var $lkAppUL;
var pageNumber = 0;
var isLastPage = false;
var currentPage = 'process';

$(function() {
  $(window).scroll(function() {
    if (isPageBottom()) {
      if (currentPage == 'pending') {
        loadPendingList();
      } else if (currentPage == 'done') {
        loadDoneList();
      }
    }
  });

  var $lkAppContent = $('<div class="lk-app-content"></div>').appendTo($('body'));
  var $lkTabBtns = $('<div class="lk-tab-btns"></div>').appendTo($lkAppContent);
  var $applyBtn = $('<div class="lk-tab-btn" id="apply_btn">申请</div>').appendTo($lkTabBtns);
  var $pendingBtn = $('<div class="lk-tab-btn" id="pending_btn">待办</div>').appendTo($lkTabBtns);
  var $doneBtn = $('<div class="lk-tab-btn" id="done_btn">已办</div>').appendTo($lkTabBtns);

  var $lkTabContent = $('<div class="lk-tab-content"></div>').appendTo($lkAppContent);
  $lkAppUL = $('<ul></ul>').appendTo($lkTabContent);

  loadProcessList();

  $applyBtn.click(function() {
    $lkAppUL.html('');
    loadProcessList();
  });

  $pendingBtn.click(function() {
    pageNumber = 0;
    isLastPage = false;
    currentPage = 'pending';
    $lkAppUL.html('');
    loadPendingList();
  });

  $doneBtn.click(function() {
    pageNumber = 0;
    isLastPage = false;
    currentPage = 'done';
    $lkAppUL.html('');
    loadDoneList();
  });

});

function loadProcessList() {
  LK.ajax({
    url : '/UserEmployee/Activiti/GetProcessList',
    data : {
      deptId : _deptId
    },
    success : function(responseDatas) {
      if (responseDatas) {
        for (var i = 0; i < responseDatas.length; i++) {
          var ary = responseDatas[i];
          var $li = $('<li>' + ary.processName + '</li>').appendTo($lkAppUL);
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

function loadPendingList() {
  if (isLastPage) {
    return;
  }
  LK.ajax({
    url : '/UserEmployee/Activiti/GetPendingProcess',
    data : {
      userId : _userId,
      pageNumber : pageNumber
    },
    success : function(responseDatas) {
      if (responseDatas) {
        if (responseDatas.length == 0) {
          isLastPage = true;
        }
        for (var i = 0; i < responseDatas.length; i++) {
          var ary = responseDatas[i];
          var $li = $('<li></li>').appendTo($lkAppUL);

          $('<div class="lk-processName">' + ary.processName + '</div>').appendTo($li);
          $('<div class="lk-processStartUserName"><lable>' + $.LKGetI18N('Process originator') + '：</lable>' + ary.processStartUserName + '</div>').appendTo($li);
          $('<div class="lk-taskStartTime"><lable>' + $.LKGetI18N('Initiation time') + '：</lable>' + ary.taskStartTime + '</div>').appendTo($li);

          (function(processInstanceId) {
            $li.click(function() {
              window.location.href = _CTX + '/processDetail/index' + _MAPPING_PAGES + '?processType=SINGLE_LINE&processInstanceId=' + processInstanceId + '&processState=pending';
            });
          })(ary.processInstanceId);
        }
      }
      pageNumber++;
    }
  });
}

function loadDoneList() {
  if (isLastPage) {
    return;
  }
  LK.ajax({
    url : '/UserEmployee/Activiti/GetDoneProcess',
    data : {
      userId : _userId,
      pageNumber : pageNumber
    },
    success : function(responseDatas) {
      if (responseDatas) {
        if (responseDatas.length == 0) {
          isLastPage = true;
        }
        for (var i = 0; i < responseDatas.length; i++) {
          var ary = responseDatas[i];
          var $li = $('<li></li>').appendTo($lkAppUL);

          $('<div class="lk-processName">' + ary.processName + '</div>').appendTo($li);
          $('<div class="lk-processStartUserName"><lable>' + $.LKGetI18N('Process originator') + '：</lable>' + ary.processStartUserName + '</div>').appendTo($li);
          $('<div class="lk-taskStartTime"><lable>' + $.LKGetI18N('Initiation time') + '：</lable>' + ary.processStartTime + '</div>').appendTo($li);
          $('<div class="lk-taskEndTime"><lable>' + $.LKGetI18N('Approval time') + '：</lable>' + ary.taskEndTime + '</div>').appendTo($li);
          if (ary.processIsEnd) {
            if (ary.delReason) {
              $('<div class="lk-processStatus"><lable>' + $.LKGetI18N('Process status') + '：</lable>' + $.LKGetI18N('Reject') + '</div>').appendTo($li);
            } else {
              $('<div class="lk-processStatus"><lable>' + $.LKGetI18N('Process status') + '：</lable>' + $.LKGetI18N('Pass the audit') + '</div>').appendTo($li);
            }
            $('<div class="lk-processEndTime"><lable>' + $.LKGetI18N('Finish time') + '：</lable>' + ary.processEndTime + '</div>').appendTo($li);
          } else {
            $('<div class="lk-processStatus"><lable>' + $.LKGetI18N('Process status') + '：</lable>' + ary.activeTaskName + '</div>').appendTo($li);
          }

          (function(processInstanceId) {
            $li.click(function() {
              window.location.href = _CTX + '/processDetail/index' + _MAPPING_PAGES + '?processType=SINGLE_LINE&processInstanceId=' + processInstanceId + '&processState=done';
            });
          })(ary.processInstanceId);
        }
      }
      pageNumber++;
    }
  });
}
