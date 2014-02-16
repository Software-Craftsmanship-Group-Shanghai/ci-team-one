<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.1.1/css/bootstrap.min.css" />
        <style>
            body {
            background-color: #eee;
            }
            form {
            margin-top: 80px;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            }
            .right {
            float:right;
            }
        </style>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script>
            function validateEmail(sEmail) {
                var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
                if (filter.test(sEmail)) {
                    return true;
                }
                else {
                    return false;
                }
            }
$(document).ready(function() {
        $('#submitButton').click(function() {
            var sEmail = $('#j_username').val();
            if ($.trim(sEmail).length == 0) {
            alert('Please enter valid email address');
            return false;
            }
            if (!validateEmail(sEmail)) {
            alert('Invalid Email Address');
            return false;
            }
            var sPassword = $('#j_password').val();
            if ($.trim(sPassword).length == 0) {
            alert('Please enter valid password');
            return false;
            }
            });

        });

</script>
</head>
<body>
    <div class="container">
        <div class="row">
            <form role="form" class="col-md-offset-4 col-md-4" action="/springTemplate/auth/logining" method="post">
                <div id="login-error">${error}</div>

                <p class="form-group">
                    <label for="j_username">Username</label> <input class="form-control" id="j_username"
                    name="j_username" type="text" />
                </p>

                <p class="form-group">
                    <label for="j_password">Password</label> <input class="form-control" id="j_password"
                    name="j_password" type="password" />
                </p>

                <p class="form-group">
                    <input class="btn btn-primary btn-block" id="submitButton" type="submit" value="Log in" />
                </p>
                <a class="right" href="/springTemplate/auth/register">No account? Get started here.</a>
            </form>
        </div>
    </div>
</body>
</html>
