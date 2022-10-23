package com.example.myspringboot.modulers.problemdetaile.entity;

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
 * @date 2022-10-03 15:42:06
 */
@Data
@TableName("problemdetaile")
public class ProblemdetaileEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * problem
	 */
	private Long problemid;
	/**
	 * input1
	 */
	private String input1;
	/**
	 * input2
	 */
	private String input2;
	/**
	 * output1
	 */
	private String output1;
	/**
	 * output2
	 */
	private String output2;
	/**
	 * code
	 */
	private String code;
	/**
	 * description
	 */
	private String description;
}
