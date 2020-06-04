package cn.chujiang.security.commons.model.user;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import lombok.Data;


/**
 * 权限标识
 * @author zhejun
 *
 */
@Data
public class SysPermission implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1389727646460449239L;
	private Long id;
	private String permission;
	private String name;
	private Date createTime;
	private Date updateTime;

	private Long roleId;
	private Set<Long> authIds;

}
