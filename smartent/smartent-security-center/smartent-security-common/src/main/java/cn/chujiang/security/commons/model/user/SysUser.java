package cn.chujiang.security.commons.model.user;

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
