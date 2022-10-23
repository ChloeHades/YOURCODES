package com.example.myspringboot.modulers.prloblemcode.entity;

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
 * @date 2022-10-06 15:32:08
 */
@Data
@TableName("tb_problemcode")
public class ProblemcodeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Integer id;
	/**
	 *
	 */
	private Long problem;
	/**
	 *
	 */
	private String code;
	/**
	 *
	 */
	private String username;
	/**
	 *
	 */
	private Date time;

	/**
	 *
	 */
	private String lang;

}
