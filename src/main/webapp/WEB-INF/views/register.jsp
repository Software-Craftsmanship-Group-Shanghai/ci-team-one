<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script>
    var showRegisterError = function(errmsg) {
        $('#register-error').text(errmsg);
    }
	var validateEmail = function(sEmail) {
		var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
		if (filter.test(sEmail)) {
			return true;
		} else {
			return false;
		}
    }
    var validatePassword = function(password) {
        return password !== ''; 
    };
    var confirmPassword = function(password, confirmPassword) {
        return password === confirmPassword;
    };
    var validateForm = function() {
        var email = $('#j_username').val().trim(),
            password = $('#j_password').val().trim(),
            confirmPass = $('#j_confirm_password').val().trim();
        if (!validateEmail(email)) {
            showRegisterError('Please input correct email.');
            return false;
        }
        if (!validatePassword(password)) {
            showRegisterError('Please input password');
            return false;
        }
        if (!confirmPassword(password, confirmPass)) {
            showRegisterError('Please make sure two password match.');
            return false;
        }
        return true;
    };
	$(document).ready(function() {

        $('#register_form').on('submit', function() {
            return validateForm();
        });
	});
</script>
</head>
<body>

	<h1>Register</h1>

	<div id="register-error" style="color:red;">${error}</div>

	<form id="register_form" action="/springTemplate/auth/registering?error=true" method="post">

		<p>
			<label for="j_username">Username</label> <input id="j_username"
				name="j_username" type="text" />
		</p>

		<p>
			<label for="j_password">Password</label> <input id="j_password"
				name="j_password" type="password" />
		</p>
		<p>
			<label for="j_confirm_password">Again</label> <input id="j_confirm_password"
				name="j_confirm_password" type="password" />
		</p>

		<input id="submitButton" type="submit" value="Register" />

	</form>

</body>
</html>
