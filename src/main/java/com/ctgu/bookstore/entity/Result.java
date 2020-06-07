package com.ctgu.bookstore.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.servlet.http.HttpSession;

@Data
@ApiModel(value = "登陆验证返回对象", description = "")
public class Result {

    @ApiModelProperty(value = "返回code,0为失败，1为成功")
    private int code;

    @ApiModelProperty(value = "返回提示信息")
    private String msg;

    @ApiModelProperty(value = "返回数据")
    private Object data;

    @ApiModelProperty(value = "返回token")
    private String token;
}
