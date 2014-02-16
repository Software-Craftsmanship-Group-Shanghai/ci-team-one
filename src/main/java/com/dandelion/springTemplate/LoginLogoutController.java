package com.dandelion.springTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("auth")
public class LoginLogoutController {
    private static final Logger logger = LoggerFactory.getLogger(LoginLogoutController.class);

	/**
	 * 指向登录页面
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(
			@RequestParam(value = "error", required = false) boolean error,
			ModelMap model) {
		if (error == true) {
			// Assign an error message
			model.put("error",
					"You have entered an invalid username or password!");
		} else {
			model.put("error", "");
		}
		return "loginpage";
	}
    @RequestMapping(value = "/logining", method = RequestMethod.POST)
    public String login(
            @RequestParam(value = "error", required = false) boolean error,
            ModelMap model, WebRequest request) {
        model.put("serverTime","login sucessful");
        String name = request.getParameter("j_username");
        String password = request.getParameter("j_password");
        ProcessUser processor = new ProcessUserImpl();
        if(processor.validateUserIsEffictive(name, password)) {
        	return "home";
        } else {
        	return "errorpage";
        }
    }

	/**
	 * 指定无访问额权限页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String getDeniedPage() {
		return "deniedpage";
	}
}
