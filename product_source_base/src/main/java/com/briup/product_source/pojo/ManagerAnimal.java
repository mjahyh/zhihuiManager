package com.briup.product_source.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
/**
 * @author Hlmove
 */
@Data
public class ManagerAnimal {
    @JsonProperty("aAnimalId")
    private String aAnimalId;
    @JsonProperty("aWeight")
    @NotBlank(message = "重量不能为空")
    private String aWeight;
    @JsonProperty("aGender")
    @NotBlank(message = "性别不能为空")
    private String aGender;
    @JsonProperty("aHealthy")
    @NotBlank(message = "健康状态不能为空")
    private String aHealthy;
    @JsonProperty("aStatus")
    @NotBlank(message = "过程状态不能为空")
    private String aStatus;
    @JsonProperty("aInoculate")
    private String aInoculate;
    @JsonProperty("aTime")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date aTime;
    @JsonProperty("aBatchId")
    private String aBatchId;
    @JsonProperty("aHurdlesId")
    private String aHurdlesId;
}
