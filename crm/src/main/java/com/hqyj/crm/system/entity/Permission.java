package com.hqyj.crm.system.entity;

import java.util.Date;

public class Permission {
    private Integer permissionId;

    private Integer pId;

    private String name;

    private String url;

    private String percode;

    private Date createTime;

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPercode() {
        return percode;
    }

    public void setPercode(String percode) {
        this.percode = percode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "Permission [permissionId=" + permissionId + ", pId=" + pId + ", name=" + name + ", url=" + url
				+ ", percode=" + percode + ", createTime=" + createTime + "]";
	}

	public Permission(Integer permissionId, Integer pId, String name, String url, String percode, Date createTime) {
		super();
		this.permissionId = permissionId;
		this.pId = pId;
		this.name = name;
		this.url = url;
		this.percode = percode;
		this.createTime = createTime;
	}

	public Permission() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}