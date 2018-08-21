$(function() {
  var $lkAppContent = $('<div class="lk-app-content"></div>').appendTo($('body'));

  // 发起表单
  if (_processId) {
    LK.ajax({
      url : '/UserEmployee/Activiti/GetProcessTaskForm',
      data : {
        processConfigId : _processId,
        step : 1
      },
      success : function(responseDatas) {
        if (responseDatas) {
          var formPluginsJson = responseDatas.formJson;
          var formOptions = $.extend({}, {
            plugins : JSON.parse(formPluginsJson)
          }, {
            $appendTo : $lkAppContent
          });
          var $form = LK.UI.form(formOptions);

          var $btns = $('<div class="lk-app-form-btns"></div>').appendTo($lkAppContent);
          var $saveBtn = $('<div class="lk-app-btn lk-app-save-btn" id="save_btn">' + $.LKGetI18N('save') + '</div>').appendTo($btns);
          var $cancelBtn = $('<div class="lk-app-btn" id="cancel_btn">' + $.LKGetI18N('cancel') + '</div>').appendTo($btns);

          $saveBtn.click(function() {
            LK.ajax({
              url : '/UserEmployee/Activiti/SubmitForm',
              data : {
                formDataJson : 'step1' + LK.SPLITOR + formPluginsJson + LK.SPLITOR + JSON.stringify($form.LKFormGetData()),
                formTypeCode : _processCode,
                processConfigId : _processId
              },
              success : function(responseDatas) {
                if (responseDatas) {
                  LK.alert('Save successfully');
                  window.location.href = _CTX + '/submitForm/' + _processCode + _MAPPING_PAGES + '?formId=' + responseDatas.id;
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
  }

  // 提交表单
  if (_formId) {
    LK.ajax({
      url : '/UserEmployee/Activiti/GetOneForm',
      data : {
        id : _formId,
      },
      success : function(responseDatas) {
        if (responseDatas) {
          var formData = responseDatas.formDataJson;
          var forms = formData.split('@#@');
          for (var i = 0; i < forms.length; i++) {
            var oneForm = forms[i].split(LK.SPLITOR);

            var plugins = JSON.parse(oneForm[1]);

            $(plugins).each(function(i, obj) {
              obj.options.readonly = true;
            });

            var formOptions = $.extend({}, {
              plugins : plugins
            }, {
              $appendTo : $lkAppContent
            });
            var $form = LK.UI.form(formOptions);

            $form.LKFormBindData(JSON.parse(oneForm[2]));
          }

          var $btns = $('<div class="lk-app-form-btns"></div>').appendTo($lkAppContent);
          if (responseDatas.approvalStatus == 'PENDING') {
            var $submitBtn = $('<div class="lk-app-btn lk-app-submit-btn">' + $.LKGetI18N('submit') + '</div>').appendTo($btns);
            $submitBtn.click(function() {
              LK.ajax({
                url : '/UserEmployee/Activiti/StartProcess',
                data : {
                  userId : _userId,
                  userName : _userName,
                  processConfigId : responseDatas.processConfigId,
                  businessKey : _formId,
                // comment : $form.LKFormGetData().comment
                },
                success : function(responseDatas) {
                  if (responseDatas) {
                    LK.alert('Submit successfully');
                    window.history.back();
                  }
                }
              });
            });
          }

          var $cancelBtn = $('<div class="lk-app-btn" id="cancel_btn">' + $.LKGetI18N('cancel') + '</div>').appendTo($btns);

          $cancelBtn.click(function() {
            window.history.back();
          });

        }
      }
    });

  }

});
