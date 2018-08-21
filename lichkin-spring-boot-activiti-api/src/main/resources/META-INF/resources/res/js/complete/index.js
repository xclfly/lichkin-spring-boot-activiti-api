$(function() {
  LK.ajax({
    url : '/UserEmployee/Activiti/GetDetailProcess',
    data : {
      processType : _processType,
      processInstanceId : _processInstanceId
    },
    success : function(responseDatas) {
      if (responseDatas) {
        var $lkAppContent = $('<div class="lk-app-content"></div>').appendTo($('body'));
        var $topTitle = $('<div class="top_title"></div>').appendTo($lkAppContent);
        var $processStartUserName = $('<div class="lk-processName"></div>').appendTo($topTitle);

        $processStartUserName.html(responseDatas[0].processName);
        // 业务表单ID
        var businessKey = responseDatas[0].businessKey;

        var processConfigId;
        var currentStep;
        var currentTaskName;
        for (var i = 0; i < responseDatas.length; i++) {
          var taskInfo = responseDatas[i];
          // 到此节点审批
          if (taskInfo.taskStartTime != null && taskInfo.taskEndTime == null) {
            processConfigId = taskInfo.processConfigId;
            currentStep = taskInfo.taskId;
            currentTaskName = taskInfo.taskName;
            break;
          }
        }

        // 请求表单信息
        LK.ajax({
          url : '/UserEmployee/Activiti/GetOneForm',
          data : {
            id : businessKey,
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

                if (i != 0) {
                  $('<div class="top_title">' + oneForm[0] + '</div>').appendTo($lkAppContent);
                }

                var $form = LK.UI.form(formOptions);
                $form.LKFormBindData(JSON.parse(oneForm[2]));
              }

              if (_processState == 'pending') {
                LK.ajax({
                  url : '/UserEmployee/Activiti/GetProcessTaskForm',
                  data : {
                    processConfigId : processConfigId,
                    step : parseInt(currentStep.substr(4))
                  },
                  success : function(responseDatas) {
                    if (responseDatas) {
                      $('<div class="top_title">' + currentTaskName + '</div>').appendTo($lkAppContent);

                      var formPluginsJson = responseDatas.formJson;
                      var formOptions = $.extend({}, {
                        plugins : JSON.parse(formPluginsJson)
                      }, {
                        $appendTo : $lkAppContent
                      });
                      var $form = LK.UI.form(formOptions);
                      console.log($form.LKFormGetData());

                      var $btns = $('<div class="lk-app-form-btns"></div>').appendTo($lkAppContent);
                      var $submitBtn = $('<div class="lk-app-btn lk-app-agree-btn">' + $.LKGetI18N('Agree') + '</div>').appendTo($btns);
                      var $rejectBtn = $('<div class="lk-app-btn lk-app-reject-btn">' + $.LKGetI18N('Reject') + '</div>').appendTo($btns);

                      var paramData = {
                        userId : _userId,
                        processType : _processType,
                        processInstanceId : _processInstanceId
                      };

                      $submitBtn.click(function() {
                        paramData.formDataJson = currentTaskName + LK.SPLITOR + formPluginsJson + LK.SPLITOR + JSON.stringify($form.LKFormGetData());

                        LK.ajax({
                          url : '/UserEmployee/Activiti/CompleteProcess',
                          data : paramData,
                          success : function(responseDatas) {
                            if (responseDatas) {
                              LK.alert('The examination and approval success');
                              window.history.back();
                            }
                          }
                        });
                      });

                      $rejectBtn.click(function() {
                        paramData.formDataJson = currentTaskName + LK.SPLITOR + formPluginsJson + LK.SPLITOR + JSON.stringify($form.LKFormGetData());

                        LK.ajax({
                          url : '/UserEmployee/Activiti/RejectProcess',
                          data : paramData,
                          success : function(responseDatas) {
                            if (responseDatas) {
                              LK.alert('The examination and approval success');
                              window.history.back();
                            }
                          }
                        });
                      });

                    }
                  }
                });
              }

            }
          }
        });

      }
    }
  });

});
