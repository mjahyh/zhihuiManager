package com.briup.product_source.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Hlmove
 */
@Data
public class QuarantineRegistration {
    @JsonProperty("grId")
    @NotBlank(message = "id不能为空")
    private Integer grId;
    @JsonProperty("grTime")
    @NotBlank(message = "时间 不能为空")
    private String grTime;
    @JsonProperty("grImg")
    @NotBlank(message = "图片 不能为空")
    private String grImg;
    @JsonProperty("grMechanism")
    @NotBlank(message = "检疫机构 不能为空")
    private String grMechanism;
    @JsonProperty("grBatchId")
    @NotBlank(message = "批次id 不能为空")
    private String grBatchId;
    @JsonProperty("bQualified")
    @NotBlank(message = "检疫结果 不能为空")
    private String bQualified;
}
