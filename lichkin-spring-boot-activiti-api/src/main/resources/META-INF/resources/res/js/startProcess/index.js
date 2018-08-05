$(function() {
  $('<div class="lk-app-form">' + LK.i18n.taskComment + '：<textarea style="width:300px;height:50px;" id="comment"></textarea></div>').appendTo($('#lk_app_content'));
  var $btns = $('<div class="lk-app-form-btns"></div>').appendTo($('#lk_app_content'));
  var $submitBtn = $('<div class="lk-app-btn" id="submit_btn">' + LK.i18n.confirm + '</div>').appendTo($btns);
  var $cancelBtn = $('<div class="lk-app-btn" id="cancel_btn">' + LK.i18n.cancel + '</div>').appendTo($btns);
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
          LK.alert(LK.i18n.successPromptMsg);
          window.history.back();
        }
      }
    });

  });

  $cancelBtn.click(function() {
    window.history.back();
  });

});
