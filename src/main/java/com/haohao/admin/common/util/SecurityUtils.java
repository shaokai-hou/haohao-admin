package com.haohao.admin.common.util;

import com.haohao.admin.auth.service.impl.UserDetailsImpl;
import com.haohao.admin.common.exception.BaseException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletResponse;

public class SecurityUtils {

    /**
     * 用户当前用户ID
     **/
    public static Long getUserId() {
        try {
            return getUserDetails().getSysUser().getId();
        } catch (Exception e) {
            throw new BaseException(HttpServletResponse.SC_UNAUTHORIZED, "获取用户ID异常");
        }
    }

    /**
     * 获取当前用户
     **/
    public static UserDetailsImpl getUserDetails() {
        try {
            return (UserDetailsImpl) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new BaseException(HttpServletResponse.SC_UNAUTHORIZED, "获取用户信息异常");
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 密码加密
     *
     * @param password 密码
     * @return result
     */
    public static String passwordEncode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
