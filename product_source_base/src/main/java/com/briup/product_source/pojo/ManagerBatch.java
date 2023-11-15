package com.briup.product_source.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ManagerBatch {
    @JsonProperty("bSerialId")
    private String bSerialId;
    @JsonProperty("bDesc")
    private String bDesc;
    @JsonProperty("bQuarantine")
    private String bQuarantine;
    @JsonProperty("bQualified")
    private String bQualified;
    @JsonProperty("bTime")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date bTime;
}