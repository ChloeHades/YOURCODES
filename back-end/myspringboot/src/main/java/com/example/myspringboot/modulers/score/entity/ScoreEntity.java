package com.example.myspringboot.modulers.score.entity;

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
 * @date 2022-10-04 10:33:41
 */
@Data
@TableName("tb_score")
public class ScoreEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Integer id;
	/**
	 *
	 */
	private Long problemid;
	/**
	 *
	 */
	private String user;
	/**
	 *
	 */
	private Integer score;

}
