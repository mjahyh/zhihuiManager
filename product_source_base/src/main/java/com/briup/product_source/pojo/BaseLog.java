package com.briup.product_source.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
@Data
public class BaseLog {
    @JsonProperty("logId")
    private Long logId;
    @JsonProperty("logUsername")
    private String logUsername;
    @JsonProperty("logHost")
    private String logHost;
    @JsonProperty("logRealname")
    private String logRealname;
    @JsonProperty("logRequestMethod")
    private String logRequestMethod;
    @JsonProperty("logRequestUri")
    private String logRequestUri;
    @JsonProperty("logTime")
    private Date logTime;
}