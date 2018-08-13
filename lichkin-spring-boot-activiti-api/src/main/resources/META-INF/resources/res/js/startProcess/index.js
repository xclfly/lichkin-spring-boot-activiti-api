$(function() {
  var $lkAppContent = $('<div class="lk-app-content"></div>').appendTo($('body'));
  
  $('<div class="lk-app-form">' + $.LKGetI18N('Comments') + '：<textarea style="width:300px;height:50px;" id="comment"></textarea></div>').appendTo($lkAppContent);
  var $btns = $('<div class="lk-app-form-btns"></div>').appendTo($lkAppContent);
  var $submitBtn = $('<div class="lk-app-btn" id="submit_btn">' + $.LKGetI18N('ok') + '</div>').appendTo($btns);
  var $cancelBtn = $('<div class="lk-app-btn" id="cancel_btn">' + $.LKGetI18N('cancel') + '</div>').appendTo($btns);
  $submitBtn.click(function() {
    LK.ajax({
      url : '/UserEmployee/Activiti/StartProcess',
      data : {
        userId : _userId,
        userName : _userName,
        processConfigId : _processId,
        comment : $('#comment').val()
      },
      success : function(responseDatas) {
        if (responseDatas) {
          LK.alert('Submit successfully');
          window.history.back();
        }
      }
    });

  });

  $cancelBtn.click(function() {
    window.history.back();
  });

});
