package com.haohao.admin.common.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.servlet.http.HttpServletResponse;

@Getter
@AllArgsConstructor
public enum ResultCodeEnum {

    SUCCESS(HttpServletResponse.SC_OK, "操作成功"),
    UNAUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, "未授权"),
    FORBIDDEN(HttpServletResponse.SC_FORBIDDEN, "无访问权限"),
    NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "资源不存在"),
    UNKNOWN_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "操作失败"),
    PARAM_ERROR(201, "参数错误"),
    NULL_POINT(202, "空指针异常");

    /**
     * code编码
     */
    final int code;
    /**
     * message描述
     */
    final String message;
}
