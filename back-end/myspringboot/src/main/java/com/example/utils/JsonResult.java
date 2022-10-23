package com.example.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author frankie
 * @Date2022/10/18 10:01
 * @PackageName: ${PACKAGE.NAME}
 * @Description:
 * @Version 1.0
 */
@Data
public class JsonResult {
   private Integer error;
   private String msg;
   private String[] data;
}
