package com.example.myspringboot.modulers.discuss.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 *
 * @author ""
 * @email ""
 * @date 2022-10-03 20:46:44
 */
@Data
@TableName("tb_discuss")
public class DiscussEntity implements Serializable {
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
	private String discussdetaile;
	/**
	 * time
	 */
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone="GMT+12")
	private Date time;
	/**
	 * commentator
	 */
	private String discussdeuser;

}
