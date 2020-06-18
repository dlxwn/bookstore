package com.ctgu.bookstore.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: xuzhen
 * @Description:
 * @Date: Created in 20:24 2020/6/18
 * @Modified By:
 */
@Data
@ApiModel(value = "返回图书分类数据对象", description = "")
public class ResultBookclassify {

    @ApiModelProperty(value = "每类数目")
    private int value;

    @ApiModelProperty(value = "类目名称")
    private String name;
}
