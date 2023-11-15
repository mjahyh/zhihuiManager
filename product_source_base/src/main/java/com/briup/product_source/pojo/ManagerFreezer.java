package com.briup.product_source.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ManagerFreezer {
    @JsonProperty("fzId")
    private Integer fzId;
    @JsonProperty("fzName")
    private String fzName;
    @JsonProperty("fzNum")
    private Integer fzNum;
    @JsonProperty("fzArea")
    private String fzArea;
    @JsonProperty("fzQuantity")
    private Integer fzQuantity;
    @JsonProperty("fzTemperature")
    private String fzTemperature;
    @JsonProperty("fzHumidity")
    private String fzHumidity;
}