package com.briup.product_source.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
@Data
public class DiseaseRecord {
    @JsonProperty("drId")
    private Integer drId;
    @JsonProperty("drAnimalId")
    private String drAnimalId;
    @JsonProperty("drDesc")
    private String drDesc;
    @JsonProperty("drCure")
    private String drCure;
    @JsonProperty("drTime")
    private Date drTime;
    @JsonProperty("drStatus")
    private String drStatus;
    @JsonProperty("drDId")
    private Integer drDId;
}