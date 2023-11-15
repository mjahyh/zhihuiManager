package com.briup.product_source.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class IssueRecord {
    @JsonProperty("isrId")
    private Integer isrId;
    @JsonProperty("isrTime")
    private Date isrTime;
    @JsonProperty("isrNum")
    private Integer isrNum;
    @JsonProperty("isrPrice")
    private Double isrPrice;
    @JsonProperty("isrDeliver")
    private Date isrDeliver;
    @JsonProperty("isrStatus")
    private String isrStatus;
    @JsonProperty("isrCustomerId")
    private Integer isrCustomerId;
}