package cn.chujiang.security.commons.model.user;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * 用户实体绑定spring security
 * @author zhejun
 *
 */
@Data
public class LoginAppUser extends SysUser implements UserDetails {


	private Set<SysRole> sysRoles;

	private Set<String> permissions;

	/***
	 * 权限重写
	 */
	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new HashSet<>();
		if (!CollectionUtils.isEmpty(sysRoles)) {
			sysRoles.parallelStream().forEach(role -> {
				if (role.getCode().startsWith("ROLE_")) {
					collection.add(new SimpleGrantedAuthority(role.getCode()));
				} else {
					collection.add(new SimpleGrantedAuthority("ROLE_" + role.getCode()));
				}
			});
		}

		if (!CollectionUtils.isEmpty(permissions)) {
			permissions.parallelStream().forEach(per -> {
				collection.add(new SimpleGrantedAuthority(per));
			});
		}

		return collection;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return getEnabled();
	}
}
