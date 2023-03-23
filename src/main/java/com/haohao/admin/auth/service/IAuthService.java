package com.haohao.admin.auth.service;

import com.haohao.admin.auth.param.LoginParam;
import com.haohao.admin.auth.service.impl.UserDetailsImpl;
import com.haohao.admin.common.result.ResultData;

import java.util.Map;

public interface IAuthService {

    /**
     * 登录
     *
     * @param loginParam 登录参数
     * @return result
     */
    ResultData<Map<String, Object>> login(LoginParam loginParam);

    /**
     * 登录用户信息
     *
     * @return result
     */
    ResultData<UserDetailsImpl> getInfo();

    /**
     * 退出登录
     *
     * @return 退出结果
     */
    ResultData<String> logout();
}
