package com.example.myspringboot.modulers.login.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author
 * @Date2022/10/5 13:37
 * @PackageName: ${PACKAGE.NAME}
 * @Description:
 * @Version 1.0
 */
@Data
public class UserVo {

    /**
     * username
     */
    private String username;
    /**
     * password
     */
    private String password;
    /**
     * passwordnew
     */
    private String passwordnew;
}
