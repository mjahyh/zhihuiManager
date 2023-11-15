package com.briup.product_source.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ManagerDisease {
    @JsonProperty("dId")
    private Integer dId;
    @JsonProperty("dName")
    private String dName;
    @JsonProperty("dDesc")
    private String dDesc;
    @JsonProperty("dType")
    private String dType;
    @JsonProperty("dEtiology")
    private String dEtiology;
    @JsonProperty("dSymptom")
    private String dSymptom;
    @JsonProperty("dPrevention")
    private String dPrevention;
}