$(function() {
  var $lkAppContent = $('<div class="lk-app-content"></div>').appendTo($('body'));

  LK.ajax({
    url : '/UserEmployee/Activiti/GetProcessTaskForm',
    data : {
      processConfigId : _processId,
      step : 1
    },
    success : function(responseDatas) {
      if (responseDatas) {
        var plugins = JSON.parse('[' + responseDatas.formJson + ']');
        var formOptions = $.extend({}, {
          plugins : plugins
        }, {
          $appendTo : $lkAppContent
        });
        var $form = LK.UI.form(formOptions);

        var $btns = $('<div class="lk-app-form-btns"></div>').appendTo($lkAppContent);
        var $submitBtn = $('<div class="lk-app-btn lk-app-save-btn" id="save_btn">' + $.LKGetI18N('save') + '</div>').appendTo($btns);
        var $submitBtn = $('<div class="lk-app-btn lk-app-submit-btn" id="submit_btn">' + $.LKGetI18N('submit') + '</div>').appendTo($btns);
        var $cancelBtn = $('<div class="lk-app-btn" id="cancel_btn">' + $.LKGetI18N('cancel') + '</div>').appendTo($btns);

        $submitBtn.click(function() {
          LK.ajax({
            url : '/UserEmployee/Activiti/StartProcess',
            data : {
              userId : _userId,
              userName : _userName,
              processConfigId : _processId,
              comment : $form.LKFormGetData().comment
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

      }
    }
  });

});
