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
  var $applyBtn = $('<div class="lk-tab-btn">申请</div>').appendTo($lkTabBtns);
  var $myApplicationBtn = $('<div class="lk-tab-btn">我的</div>').appendTo($lkTabBtns);
  var $pendingBtn = $('<div class="lk-tab-btn">待办</div>').appendTo($lkTabBtns);
  var $doneBtn = $('<div class="lk-tab-btn">已办</div>').appendTo($lkTabBtns);

  var $lkTabContent = $('<div class="lk-tab-content"></div>').appendTo($lkAppContent);
  $lkAppUL = $('<ul></ul>').appendTo($lkTabContent);

  loadProcessList();

  $applyBtn.click(function() {
    $lkAppUL.html('');
    loadProcessList();
  });

  $myApplicationBtn.click(function() {
    $lkAppUL.html('');
    loadMyApplicationList();
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

// 流程列表
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
              window.location.href = _CTX + '/submitForm/' + ary.processCode + _MAPPING_PAGES + '?processId=' + processId;
            });
          })(ary.processId);
        }
      }
    }
  });
}

// 我的申请
function loadMyApplicationList() {
  LK.ajax({
    url : '/UserEmployee/Activiti/GetFormList',
    success : function(responseDatas) {
      if (responseDatas) {
        for (var i = 0; i < responseDatas.length; i++) {
          var ary = responseDatas[i];
          var $li = $('<li></li>').appendTo($lkAppUL);

          $('<div class="lk-processName">' + ary.formType + '</div>').appendTo($li);
          $('<div class="lk-processStartUserName"><lable>' + $.LKGetI18N('Approval status') + '：</lable>' + $.LKGetI18N('APPROVAL_STATUS.' + ary.approvalStatus) + '</div>').appendTo($li);
          $('<div class="lk-taskStartTime"><lable>' + $.LKGetI18N('Create time') + '：</lable>' + formatterTime(ary.insertTime) + '</div>').appendTo($li);

          (function(formId) {
            $li.click(function() {
              window.location.href = _CTX + '/submitForm/' + ary.formTypeCode + _MAPPING_PAGES + '?formId=' + formId;
            });
          })(ary.id);
        }
      }
    }
  });
}

// 我的待办
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
          var $btns = $('<div class="lk-app-form-btns"></div>').appendTo($li);
          var $completeBtn = $('<div class="lk-app-btn">' + $.LKGetI18N('Transact') + '</div>').appendTo($btns);
          var $traceBtn = $('<div class="lk-app-btn">' + $.LKGetI18N('Trace') + '</div>').appendTo($btns);

          (function(processInstanceId) {
            $completeBtn.click(function() {
              window.location.href = _CTX + '/complete/index' + _MAPPING_PAGES + '?processType=SINGLE_LINE&processInstanceId=' + processInstanceId + '&processState=pending';
            });

            $traceBtn.click(function() {
              window.location.href = _CTX + '/processDetail/index' + _MAPPING_PAGES + '?processType=SINGLE_LINE&processInstanceId=' + processInstanceId + '&processState=pending';
            });
          })(ary.processInstanceId);

        }
      }
      pageNumber++;
    }
  });
}

// 我的已办
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
              $('<div class="lk-processStatus"><lable>' + $.LKGetI18N('Approval status') + '：</lable>' + $.LKGetI18N('APPROVAL_STATUS.REJECT') + '</div>').appendTo($li);
            } else {
              $('<div class="lk-processStatus"><lable>' + $.LKGetI18N('Approval status') + '：</lable>' + $.LKGetI18N('APPROVAL_STATUS.APPROVED') + '</div>').appendTo($li);
            }
            $('<div class="lk-processEndTime"><lable>' + $.LKGetI18N('Finish time') + '：</lable>' + ary.processEndTime + '</div>').appendTo($li);
          } else {
            $('<div class="lk-processStatus"><lable>' + $.LKGetI18N('Approval status') + '：</lable>' + ary.activeTaskName + '</div>').appendTo($li);
          }
          
          var $btns = $('<div class="lk-app-form-btns"></div>').appendTo($li);
          var $viewBtn = $('<div class="lk-app-btn">' + $.LKGetI18N('View') + '</div>').appendTo($btns);
          var $traceBtn = $('<div class="lk-app-btn">' + $.LKGetI18N('Trace') + '</div>').appendTo($btns);

          (function(processInstanceId) {
            $viewBtn.click(function() {
              window.location.href = _CTX + '/complete/index' + _MAPPING_PAGES + '?processType=SINGLE_LINE&processInstanceId=' + processInstanceId + '&processState=done';
            });
            
            $traceBtn.click(function() {
              window.location.href = _CTX + '/processDetail/index' + _MAPPING_PAGES + '?processType=SINGLE_LINE&processInstanceId=' + processInstanceId + '&processState=done';
            });
          })(ary.processInstanceId);
        }
      }
      pageNumber++;
    }
  });
}
