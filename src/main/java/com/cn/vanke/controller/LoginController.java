package com.cn.vanke.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cn.vanke.security.service.OAuthConfigService;
import com.cn.vanke.security.service.OAuthService;
import com.cn.vanke.util.CookieUtil;

/**
 * 处理登录认证相关的控制器
 */
@Controller
@SessionAttributes("state")
public class LoginController {
	@Autowired(required = false)
    private OAuthConfigService oAuthConfigService;

    @Autowired
    private OAuthService oAuthService;

    @Autowired(required = false)
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) throws Exception {
        String state = UUID.randomUUID().toString().replace("-", "");
        model.addAttribute("state", state);
        String redirectUrl = "redirect:" + oAuthConfigService.getRedirectUrl(httpServletRequest, null, state);
        return redirectUrl;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes, @CookieValue("access_token") String accessToken) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            oAuthService.logout(accessToken);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        CookieUtil.clear(request, response);
        request.getSession().invalidate();
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/";
    }

    @ResponseBody
    @RequestMapping(value = "/access_token", method = RequestMethod.GET)
    public String getAccessToken(@CookieValue("access_token") String accessToken) {
        return accessToken;
    }

    @RequestMapping(value = "/401", method = RequestMethod.GET)
    public String unAuthentication() {
        return "error/401";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String unAuthorized() {
        return "error/403";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome() {
        return "error/welcome";
    }
}
