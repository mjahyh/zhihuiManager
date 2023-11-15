package com.briup.product_source.pojo.ext;

import com.briup.product_source.pojo.ManagerAnimal;
import com.briup.product_source.pojo.ManagerBatch;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Hlmove
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ManagerAnimalExt extends ManagerAnimal {
    @JsonProperty("managerHurdles")
    private String managerHurdles;
    @JsonProperty("managerFenceHouse")
    private String managerFenceHouse;
    @JsonProperty("aBackup3")
    private String url;
    @JsonProperty("managerBatch")
    private ManagerBatch managerBatch;
}
