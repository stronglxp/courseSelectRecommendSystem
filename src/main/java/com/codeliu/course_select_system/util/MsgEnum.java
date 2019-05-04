package com.codeliu.course_select_system.util;

/**
 * @ENUMName liuxiaoping
 * @Description 状态码和提示信息统一定义
 * @Author liu
 * @Date 2019/2/1 12:12
 * @Version 1.0
 */
public enum MsgEnum {

    SUCCESS(200, "成功"),
    FAILD(-1, "失败"),
    PARAM_ERROR(-2, "参数错误"),
    ;

    // code状态码
    private Integer code;
    // msg提示信息
    private String msg;

    private MsgEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
