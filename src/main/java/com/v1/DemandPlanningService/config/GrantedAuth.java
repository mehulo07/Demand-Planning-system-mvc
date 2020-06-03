package com.v1.DemandPlanningService.config;


import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

public class GrantedAuth implements GrantedAuthority {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	private final String role;
	private Map<String, List<String>> pageFun; 

	public GrantedAuth(String role, Map<String, List<String>> pageFun) {
		Assert.hasText(role, "A granted authority textual representation is required");
		this.role = role;
		this.pageFun = pageFun;
	}

	@Override
	public String getAuthority() {
		return role;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj instanceof GrantedAuth) {
			return role.equals(((GrantedAuth) obj).role);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return this.role.hashCode();
	}
	
	@Override
	public String toString() {
		return "GrantedAuth [role=" + role + ", pageFun=" + pageFun + "]";
	}

	public Map<String, List<String>> getPageFun() {
		return pageFun;
	}

	public void setPageFun(Map<String, List<String>> pageFun) {
		this.pageFun = pageFun;
	}

}
