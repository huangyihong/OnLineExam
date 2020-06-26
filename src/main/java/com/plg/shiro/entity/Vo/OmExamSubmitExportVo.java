package com.plg.shiro.entity.Vo;

import java.util.Date;

public class OmExamSubmitExportVo extends OmExamSubmitVo {
    private String userName;//学号
    
    private String groupName;//单位

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}