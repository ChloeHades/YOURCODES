package com.example.myspringboot.modulers.problemdetaile.entity;

import lombok.Data;

/**
 * @Author
 * @Date2022/10/3 15:58
 * @PackageName: ${PACKAGE.NAME}
 * @Description:
 * @Version 1.0
 */
@Data
public class Problemvo {
    private Long id;
    private String description;
    private String topic;
    private String title;
    private String difficulty;
    private String input1;
    private String input2;
    private String  output1;
    private String output2;
    private String code;
    private String type;
    private String user;
    private Integer comment;
}
