package com.briup.product_source.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
/**
 * @author Hlmove
 */
@Data
public class ManagerHurdles {
    @JsonProperty("hId")
    private String hId;
    @JsonProperty("hName")
    private String hName;
    @JsonProperty("hDesc")
    private String hDesc;
    @JsonProperty("hMax")
    private Integer hMax;
    @JsonProperty("hSaved")
    private Integer hSaved;
    @JsonProperty("hTime")
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date hTime;
    @JsonProperty("hEnable")
    private String hEnable;
    @JsonProperty("hFull")
    private String hFull;
    @JsonProperty("hFenceId")
    private String hFenceId;
}
