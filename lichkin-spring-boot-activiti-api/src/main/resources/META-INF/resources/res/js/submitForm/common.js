function loadSubmitForm() {
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
          var dataJson = {
            userName : _userName,
            deptName : _deptName,
            entryDate : _entryDate
          };
          $form.LKFormBindData(dataJson);

          var $btns = $('<div class="lk-app-form-btns"></div>').appendTo($lkAppContent);
          var $saveBtn = $('<div class="lk-app-btn lk-app-save-btn" id="save_btn">' + $.LKGetI18N('save') + '</div>').appendTo($btns);

          $saveBtn.click(function() {
            if ($form.LKValidate()) {
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
            }

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
        id : _formId
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
            
            if (i != 0) {
              $('<div class="top_title">' + oneForm[0] + '</div>').appendTo($lkAppContent);
            }

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
                  businessKey : _formId
                },
                success : function(responseDatas) {
                  if (responseDatas) {
                    LK.alert('Submit successfully');
                    window.history.back();
                  }
                }
              });
            });

            var $cancelBtn = $('<div class="lk-app-btn" id="cancel_btn">' + $.LKGetI18N('Cancel application') + '</div>').appendTo($btns);
            $cancelBtn.click(function() {
              LK.ajax({
                url : '/UserEmployee/Activiti/UpdateForm',
                data : {
                  id : _formId,
                  usingStatus : 'DEPRECATED'
                },
                success : function(responseDatas) {
                  LK.alert('Cancel successfully');
                  window.history.back();
                }
              });
            });
          }

        }
      }
    });

  }

}