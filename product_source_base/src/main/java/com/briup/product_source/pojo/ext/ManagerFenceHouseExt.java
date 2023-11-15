package com.briup.product_source.pojo.ext;

import com.briup.product_source.pojo.ManagerFenceHouse;
import com.briup.product_source.pojo.ManagerHurdles;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
/**
 * @author Hlmove
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ManagerFenceHouseExt extends ManagerFenceHouse {
    @JsonProperty("mhs")
    private List<ManagerHurdles> mhs;
}
