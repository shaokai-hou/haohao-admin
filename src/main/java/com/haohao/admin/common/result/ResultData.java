package com.haohao.admin.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "统一返回实体类")
public class ResultData<T> {

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "消息")
    private String message;

    @ApiModelProperty(value = "数据")
    private T data;

    public static <T> ResultData<T> success() {
        ResultData<T> result = new ResultData<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    public static <T> ResultData<T> success(T data) {
        ResultData<T> result = new ResultData<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    public static <T> ResultData<T> error() {
        ResultData<T> result = new ResultData<>();
        result.setCode(ResultCodeEnum.UNKNOWN_ERROR.getCode());
        result.setMessage(ResultCodeEnum.UNKNOWN_ERROR.getMessage());
        return result;
    }

    public static <T> ResultData<T> error(String message) {
        ResultData<T> result = new ResultData<>();
        result.setCode(ResultCodeEnum.UNKNOWN_ERROR.getCode());
        result.setMessage(message);
        return result;
    }

    public static <T> ResultData<T> error(ResultCodeEnum resultCode) {
        ResultData<T> result = new ResultData<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    }

    public static <T> ResultData<T> error(ResultCodeEnum resultCode, T data) {
        ResultData<T> result = new ResultData<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        result.setData(data);
        return result;
    }

    public static <T> ResultData<T> flag(Boolean flag) {
        return flag ? ResultData.success() : ResultData.error();
    }
}
