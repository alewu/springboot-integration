package com.ale.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : xiesn
 * @version V1.0
 * @Description: 带分页请求类
 */

@Data
@ApiModel("带有分页信息的请求入参")
public class RequestPageData<T> extends RequestData<T> {

    /**
     * 分页信息
     */
    @ApiModelProperty("分页信息")
    private PageInfo pageInfo = new PageInfo();

    public RequestPageData() {
    }

    public RequestPageData(T params, PageInfo pageInfo) {
        super(params);
        this.pageInfo = pageInfo;
    }

    public RequestPageData(T params) {
        super(params);
    }

    @Override
    public String toString() {
        return "RequestPageData{" +
                "pageInfo=" + pageInfo +
                "} " + super.toString();
    }
}
