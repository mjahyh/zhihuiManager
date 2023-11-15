package com.briup.product_source.pojo.ext;

import com.briup.product_source.pojo.ManagerFenceHouse;
import com.briup.product_source.pojo.ManagerHurdles;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Hlmove
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ManagerHurdlesExt extends ManagerHurdles {
    @JsonProperty("managerFenceHouse")
    private ManagerFenceHouse managerFenceHouse;
}
