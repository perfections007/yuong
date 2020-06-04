package cn.chujiang.provider.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 用户实体
 * @author zhejun
 *
 */
@Data
public class SysUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5886012896705137070L;
	private Long id;
	private String username;
	private String password;
	private String nickname;
	private String headImgUrl;
	private String phone;
	private Integer sex;
	private Boolean enabled;
	private String type;
	private Date createTime;
	private Date updateTime;

	private List<SysRole> roles;

	private String roleId;


	private String oldPassword;
	private String newPassword;

}
