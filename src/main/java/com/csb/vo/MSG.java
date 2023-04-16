package com.csb.vo;

import com.csb.utils.Assert;

public record MSG(int code, String msg, Object data) {
    public static final String SUCCESS = "成功";
    public static final String FAIL = "失败";
    public static final String ILLEGAL_PARAM = "参数非法";
    public static final String ILLEGAL_AUTHORITY = "越权操作";
    public static final String ILLEGAL_CODE = "验证码错误";
    public static final MSG MSG_SUCCESS = new MSG(1,SUCCESS,null);
    public static final MSG MSG_FAIL = new MSG(0,FAIL,null);
    public static final MSG MSG_ILLEGAL_PARAM = new MSG(-1,ILLEGAL_PARAM,null);
    public static final MSG MSG_ILLEGAL_AUTHORITY = new MSG(-2,ILLEGAL_AUTHORITY,null);
    public static final MSG MSG_ILLEGAL_CODE = new MSG(-2,ILLEGAL_CODE,null);

    public static MSG getMsgSuccessWithData(Object data){
        if (Assert.isNull(data))return MSG_SUCCESS;
        return new MSG(1,SUCCESS,data);
    }
}
