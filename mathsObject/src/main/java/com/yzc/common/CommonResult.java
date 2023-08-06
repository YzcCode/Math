package com.yzc.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yangzicheng
 * @Date Created in 9:40 2021/12/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    @ApiModelProperty("返回前端的响应状态码")
    private  Integer responseCode;

    @ApiModelProperty("返回前端的响应消息")
    private String responseMessage;

    @ApiModelProperty("返回前端的数据")
    private T responseData;

    public CommonResult(Integer responseCode, String responseMessage){
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }
}
