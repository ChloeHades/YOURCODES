package com.example.myspringboot.modulers.problams.entity;


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
 * @date 2022-10-02 19:24:31
 */
@Data
@TableName("tb_problem")
public class ProblemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * Title
	 */
	private String title;
	/**
	 * Solution
	 */
	private Integer solution;
	/**
	 * Difficulty
	 */
	private String difficulty;
	/**
	 * Submissions
	 */
	private Integer submissions;
	/**
	 * Rating
	 */
	private Double rating;
	/**
	 * Type
	 */
	private String type;
	/**
	 * topics
	 */
	private String topics;
	/**
	 * user
	 */
	private String user;

}
