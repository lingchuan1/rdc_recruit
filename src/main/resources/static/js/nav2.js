$(function () {

  var a = {

    // 封面壁纸尺寸调整
    winHeight: 0,
    winWidth: 0,
    ua: navigator.userAgent,
    address: 'http://rdc2019.cn/user',

    findDimensions: function () {
      var that = this;
      //获取窗口宽度
      if (window.innerWidth)
        that.winWidth = window.innerWidth;
      else if ((document.body) && (document.body.clientWidth))
        that.winWidth = document.body.clientWidth;
      //获取窗口高度
      if (window.innerHeight)
        that.winHeight = window.innerHeight;
      else if ((document.body) && (document.body.clientHeight))
        that.winHeight = document.body.clientHeight;
      //通过深入Document内部对body进行检测，获取窗口大小
      if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth) {
        that.winHeight = document.documentElement.clientHeight;
        that.winWidth = document.documentElement.clientWidth;
      }
      // //跟div赋值
      // var detail = document.getElementById("detail");
      // detail.style.width = winWidth-4;
      $('.banner').css('min-height', that.winHeight);
      if (/Android (\d+\.\d+)/.test(that.ua)) {
        $('.banner').css('min-height', that.winHeight * 1.5);
      }
    },

    //页面效果
    fn: function () {
      var that = this;
      // h-nav滚动弹出
      $(window).scroll(function () {

        if ($(window).scrollTop() > 100) {
          $('.main_h').addClass('sticky');
        } else {
          $('.main_h').removeClass('sticky');
        }
      });
      // Navigation Scroll - ljepo radi materem
      $('.h-nav a,.footer-links a').click(function (event) {
        var id = $(this).attr("href");
        var offset = 70;
        var target = $(id).offset().top - offset;
        $('html, body').animate({
          scrollTop: target
        }, 500);
        event.preventDefault();
      });

      $('.mouse').click(function (event) {
        var offset = 70;
        var target = $('.sec03').offset().top - offset;
        $('html, body').animate({
          scrollTop: target
        }, 500);
        event.preventDefault();
      });

      $('.snip1091').click(function (event) {
        var index = $(this).parent().index();
        $('input:radio[name="rb"]').eq(index).attr("checked", true);
        // $('input:radio[name="rb"]').not($('input:radio[name="rb"]').eq(index)).attr("checked", false);
        that.changeTeam();
        var offset = 70;
        var target = $('.sec03').offset().top - offset;
        $('html, body').animate({
          scrollTop: target
        }, 500);
        event.preventDefault();
      })

      // Mobile Navigation
      $('.mobile-toggle').click(function () {
        if ($('.main_h').hasClass('open-nav')) {
          $('.main_h').removeClass('open-nav');
        } else {
          $('.main_h').addClass('open-nav');
        }
      });

      $('.main_h li a').click(function () {
        if ($('.main_h').hasClass('open-nav')) {
          $('.navigation').removeClass('open-nav');
          $('.main_h').removeClass('open-nav');
        }
      });
      // img-hover
      $("figure").mouseleave(
        function () {
          $(this).removeClass("hover");
        }
      );
    },

    //验证格式
    validate: function (a, text) {
      var re = new RegExp(text);
      if (!(re.test(a.value))) {
        $(a).next().show();
      }
      else { $(a).next().hide(); }
    },

    //报名系统
    fn2: function () {
      var that = this;
      //输入框验证
      $('#name').blur(function () { that.validate(this, '^[\u4E00-\u9FA5]{2,4}$') });
      $('#studentId').blur(function () { that.validate(this, '^[1-9][0-9]{7,12}$') });
      $('#contact').blur(function () { that.validate(this, '^[1][0-9]{10}$') });
      $('input:radio[name="rb"]').click(function () { that.changeTeam(); });
      //报名成功后点击oj按钮
      $(".okoButton").click(function () {
        $(".tips").addClass("none");
        $(".okoButton").removeClass("redOkoButton");
      })
    },

    //报名表标题变更时触发
    changeTeam: function () {
      var team = $('input:radio[name="rb"]:checked').val();
      switch (team) {
        case '前端':
          $('.form-countainer h1').eq(0).css({ "background-color": "#21a675" });
          break;
        case '后台':
          $('.form-countainer h1').eq(0).css({ "background-color": "#177cb0" });
          break;
        case '安卓':
          $('.form-countainer h1').eq(0).css({ "background-color": "#ff9090" });
          break;
        case '大数据':
          $('.form-countainer h1').eq(0).css({ "background-color": "#804bbb" });
          break;
      }
      if (team) {
        $('#team-choice').text("——" + team + "组");
      }
    },

    //函数节流
    throttle: function (method, context) {
      clearTimeout(method.tId);
      method.tId = setTimeout(function () {
        method.call(context);
      }, 500);
    },

    //gt验证回调函数
    handler2: function (captchaObj) {
      var submitMethod = function () {
        var result = captchaObj.getValidate();
        if (!result) {
          $('#sendmessage').text('请先完成验证');
          $("#sendmessage").show();
          $('html, body').animate({ scrollTop: $("#sendmessage").offset().top - 100 }, 500);
          setTimeout(function () {
            $("#sendmessage").hide();
          }, 2000);
        } else {
          var name = $('#name').val();
          var contact = $('#contact').val();
          var professionClass = $('#professionClass').val();
          var introduction = $('#introduction').val();
          var studentId = $('#studentId').val();
          var sex = $('input:radio[name="mb"]:checked').val();
          var direction = $('input:radio[name="rb"]:checked').val();
          if (name && contact && professionClass && introduction && studentId && sex && direction) {
            $.ajax({
              url: a.address + '/validateAndAdd',
              type: 'POST',
              dataType: 'json',
              // xhrFields: {
              //   withCredentials: true
              // },
              data: {
                // sessionId: sessionId,
                name: name,
                contact: contact,
                professionClass: professionClass,
                introduction: introduction,
                studentId: studentId,
                sex: sex,
                direction: direction,
                challenge: result.geetest_challenge,
                validate: result.geetest_validate,
                seccode: result.geetest_seccode
              },
              beforeSend: function() {
                a.init();
              },
              success: function (data) {
                console.log(data)
                if (data.result == 'success') {
                  $(".success .dec_txt").text("报名成功！");
                  b.successRender();
                  $('#sendmessage').text('报名成功！');
                  $("#sendmessage").show();
                  $('#name').val('');
                  $('#contact').val('');
                  $('#professionClass').val('');
                  $('#studentId').val('');
                  $('#introduction').val('');
                  $('input:radio[name="rb"]').attr("checked", false);
                  $('input:radio[name="mb"]').attr("checked", false);
                  setTimeout(function () {
                    $("#sendmessage").hide();
                  }, 6000);
                  // $('html, body').animate({ scrollTop: $("#sendmessage").offset().top - 100 }, 500);
                } else if (data.result == 'error') {
                  $(".lose .dec_txt").text(data.message);
                  b.failRender();
                  $('#sendmessage').text(data.message);
                  $("#sendmessage").show();
                  // $('html, body').animate({ scrollTop: $("#sendmessage").offset().top - 100 }, 500);
                  setTimeout(function () {
                    $("#sendmessage").hide();
                  }, 2000);
                }
              },
              error: function (err) {
                // 请求出错处理
                $(".warning .dec_txt").text("服务器错误");
                b.errorRender();
              }
            })
          }
          else {
            $('#sendmessage').text('请输入必填信息');
            $("#sendmessage").show();
            $('html, body').animate({ scrollTop: $("#sendmessage").offset().top - 100 }, 500);
            setTimeout(function () {
              $("#sendmessage").hide();
            }, 2000);
          }
        }
      };
      $("#submit2").click(function (e) {
        e.preventDefault();
        a.throttle(submitMethod, this);
      });
      // 将验证码加到id为captcha的元素里，同时会有三个input的值用于表单提交
      captchaObj.appendTo("#captcha2");
      captchaObj.onReady(function () {
        $("#wait2").hide();
      });
    },

    //gt验证初始化
    init: function () {
      var that = this;
      $.ajax({
        url: that.address + '/init', // 加随机数防止缓存
        type: "get",
        dataType: "json",
        success: function (data) {
          // if (data.sessionId) {
          //   sessionId = data.sessionId
          // }
          // 调用 initGeetest 初始化参数
          // 参数1：配置参数
          // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它调用相应的接口
          initGeetest({
            gt: data.gt,
            challenge: data.challenge,
            new_captcha: data.new_captcha, // 用于宕机时表示是新验证码的宕机
            offline: !data.success, // 表示用户后台检测极验服务器是否宕机，一般不需要关注
            product: "popup", // 产品形式，包括：float，popup
            width: "100%"
            // 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
          }, that.handler2);
        }
      });
    }

  };

  var b = {
    //自定义弹出提示框
    successRender: function () {
      $(".box_overlay,.okoButton").removeClass("none");
      $("#animationTipBox").removeClass("none");
      $(".success").removeClass("none");
      $("#animationTipBox").mousedown();
    },
    failRender: function () {
      $(".box_overlay,.okoButton").removeClass("none");
      $("#animationTipBox").removeClass("none");
      $(".lose").removeClass("none");
      $(".okoButton").addClass("redOkoButton");
    },
    errorRender: function () {
      $(".box_overlay,.okoButton").removeClass("none");
      $("#animationTipBox").removeClass("none");
      $(".warning").removeClass("none");
    },
    // //loading效果显示
    // loadRender: function () {
    //   $(".box_overlay").removeClass("none");
    //   $("#animationTipBox").removeClass("none");
    //   $(".load").removeClass("none");
    // },
    // //loading失败显示
    // finishLoadingRender2: function () {
    //   $(".box_overlay,#animationTipBox,.load").addClass("none");
    //   failRender();
    // },
    // //loading成功显示
    // finishLoadingRender3: function () {
    //   $(".box_overlay,#animationTipBox,.load").addClass("none");
    //   successRender();
    // },
    // //loading错误显示
    // finishLoadingRender4: function () {
    //   $(".box_overlay,#animationTipBox,.load").addClass("none");
    //   errorRender();
    // },
  }

  a.findDimensions();
  if (!(/Android (\d+\.\d+)/.test(a.ua))) {
    window.onresize = a.findDimensions();
  }
  a.fn();
  a.fn2();
  a.init();

})