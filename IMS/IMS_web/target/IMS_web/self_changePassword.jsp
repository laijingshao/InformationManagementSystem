<%--
  Created by IntelliJ IDEA.
  User: 11518
  Date: 2019-12-27
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=8" >
    <title>修改密码</title>
    <link rel="stylesheet" type="text/css" href="../css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../css/thems.css">
    <script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
        $(function(){
            //自适应屏幕宽度
            window.onresize=function(){ location=location };

            var main_h = $(window).height();
            $('.hy_list').css('height',main_h-45+'px');

            var main_w = $(window).width();
            $('.xjhy').css('width',main_w-40+'px');

        });
    </script>
</head>

<body onLoad="Resize();">
<div id="right_ctn">
    <div class="right_m">
        <div class="hy_list">
            <div class="box_t">
                <span class="name">修改密码</span>
            </div>
            <div class="space_hx">&nbsp;</div>
            <form action="changePassword.do" method="post" name="addForm">
                <div class="xjhy">
                    <!--高级配置-->
                    <ul class="hypz gjpz clearfix">
                        <li class="clearfix">
                            <span class="title">账户名：</span>
                            <div class="li_r">${USER.account}</div>
                        </li>
                        <li class="clearfix">
                            <span class="title">原密码：</span>
                            <div class="li_r">
                                <input class="chang" name="password" type="password">
                            </div>
                            <div class="li_r">${msg}</div>
                        </li>
                        <li class="clearfix">
                            <span class="title">新密码：</span>
                            <div class="li_r">
                                <input class="chang" name="password1" type="password">
                            </div>
                        </li>
                        <li class="clearfix">
                            <span class="title">确认新密码：</span>
                            <div class="li_r">
                                <input class="chang" name="password2" type="password">
                            </div>
                            <div class="li_r">${msg2}</div>
                        </li>

                        <li class="tj_btn">
                            <a href="javascript:history.go(-1);" class="back">返回</a>
                            <a href="javascript:addForm.submit();">保存</a>
                        </li>
                    </ul>
                    <!--高级配置-->
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
