package com.briup.product_source.constant;

import lombok.Data;

/**
 * @author Hlmove
 */
@Data
public class Constants {
    // 成功响应码
    public static final Integer SUCCESS_CODE = 200;
    // 失败响应码
    public static final Integer ERROR_CODE = 500;
    // 文件存储路径
    public static final String FILE_LOCATION = "F:/Code2023/Code/workplace/zhihuiManager/images";
    // 端口文件资源访问地址
    public static final String SERVER = "http://localhost:9999/static/images/";

    // 移动端访问路径 映射本地的localhost:8081
    public static final String QRcodeServer = "http://5s29z8.natappfree.cc";
//    public static final String QRcodeServer = "http://192.168.226.152:9527/#/qr";

    public static final String Prefix = "?animalId=";

}
