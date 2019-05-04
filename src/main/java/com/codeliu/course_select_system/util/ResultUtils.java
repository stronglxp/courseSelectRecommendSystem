package com.codeliu.course_select_system.util;

import org.springframework.stereotype.Component;

/**
 * @ClassName liuxiaoping
 * @Description 返回结果统一格式
 * @Author liu
 * @Date 2019/2/1 12:06
 * @Version 1.0
 */
@Component
public class ResultUtils<T> {

    // 状态码
    private Integer code;
    // 信息
    private String msg;
    // 数据
    private T data;

    public ResultUtils() {}

    /**
     * @Author liuxiaoping
     * @Description 成功时返回
     * @Date 12:09 2019/2/1
     * @Param []
     * @return ResultUtils<T>
     **/
    public ResultUtils<T> isOk(T data) {
        ResultUtils<T> result = new ResultUtils<>();
        result.code = MsgEnum.SUCCESS.getCode();
        result.msg = MsgEnum.SUCCESS.getMsg();
        result.data = data;
        return result;
    }

    /**
     * @Author liuxiaoping
     * @Description 失败时返回
     * @Date 12:11 2019/2/1
     * @Param []
     * @return ResultUtils<T>
     **/
    public ResultUtils<T> isFaild() {
        ResultUtils<T> result = new ResultUtils<>();
        result.code = MsgEnum.FAILD.getCode();
        result.msg = MsgEnum.FAILD.getMsg();
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
