package com.csb.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class  MSG {
    public static MSG ILLEAGAL_PARAM=new MSG(-2,"参数非法",null);
    public static MSG SUCESS_EMP=new MSG(1,"成功",null);
    public static MSG FAIL_EMP=new MSG(0,"失败",null);
    public static MSG ILLEAGAL_AUTH=new MSG(0,"权限不足",null);

    public MSG(Object success){
        this(1,"成功",success);
    }

    private int code;
    private String msg;
    private Object data;
}
