package com.example.myspringboot.modulers.login.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 *
 * @author ""
 * @email ""
 * @date 2022-09-09 16:00:25
 */
@Data
@TableName("tb_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Integer id;
	/**
	 * username
	 */
	private String username;
	/**
	 * password
	 */
	private String password;
	/**
	 * email
	 */
	private String email;
	/**
	 * phone
	 */
	private String phone;
	/**
	 * sex
	 */
	private String sex;
	/**
	 * selfintroduction
	 */
	private String selfintroduction;
}
