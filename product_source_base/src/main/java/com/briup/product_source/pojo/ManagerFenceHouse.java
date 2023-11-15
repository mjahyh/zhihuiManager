package com.briup.product_source.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
/**
 * @author Hlmove
 */
@Data
public class ManagerFenceHouse {
    @JsonProperty("fhId")
    private String fhId;
    @JsonProperty("fhName")
    private String fhName;
    @JsonProperty("fhDesc")
    private String fhDesc;
    @JsonProperty("fhTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fhTime;
}
