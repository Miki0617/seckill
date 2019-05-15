package com.demo.seckill.domain;

public class UserPasswdDO {
    private Long userId;

    private String encrptPasswd;

    private String telphone;

    private String name;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEncrptPasswd() {
        return encrptPasswd;
    }

    public void setEncrptPasswd(String encrptPasswd) {
        this.encrptPasswd = encrptPasswd == null ? null : encrptPasswd.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}