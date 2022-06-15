package com.orion.ops.entity.request.sftp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sftp 创建文件请求
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2021/6/23 20:09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "创建文件请求")
public class FileTouchRequest extends FileBaseRequest {

    @ApiModelProperty(value = "绝对路径")
    private String path;

}
