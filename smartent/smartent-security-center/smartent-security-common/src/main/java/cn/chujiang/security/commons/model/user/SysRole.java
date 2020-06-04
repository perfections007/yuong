package cn.chujiang.security.commons.model.user;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 角色
 * @author zhejun
 *
 */
@Data
public class SysRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4497149010220586111L;
	private Long id;
	private String code;
	private String name;
	private Date createTime;
	private Date updateTime;
	private Long userId;
}
