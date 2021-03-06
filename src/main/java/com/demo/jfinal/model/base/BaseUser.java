package com.demo.jfinal.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseUser<M extends BaseUser<M>> extends Model<M> implements IBean {

	/**
	 * 用户主键ID
	 */
	public M setUserId(java.lang.String userId) {
		set("user_id", userId);
		return (M)this;
	}
	
	/**
	 * 用户主键ID
	 */
	public java.lang.String getUserId() {
		return getStr("user_id");
	}

	/**
	 * 用户昵称
	 */
	public M setUserName(java.lang.String userName) {
		set("user_name", userName);
		return (M)this;
	}
	
	/**
	 * 用户昵称
	 */
	public java.lang.String getUserName() {
		return getStr("user_name");
	}

	/**
	 * 用户手机号
	 */
	public M setUserPhone(java.lang.String userPhone) {
		set("user_phone", userPhone);
		return (M)this;
	}
	
	/**
	 * 用户手机号
	 */
	public java.lang.String getUserPhone() {
		return getStr("user_phone");
	}

	/**
	 * 用户登录密码
	 */
	public M setUserPassword(java.lang.String userPassword) {
		set("user_password", userPassword);
		return (M)this;
	}
	
	/**
	 * 用户登录密码
	 */
	public java.lang.String getUserPassword() {
		return getStr("user_password");
	}

	/**
	 * 用户头像
	 */
	public M setUserIcon(java.lang.String userIcon) {
		set("user_icon", userIcon);
		return (M)this;
	}
	
	/**
	 * 用户头像
	 */
	public java.lang.String getUserIcon() {
		return getStr("user_icon");
	}

	/**
	 * 用户邮箱地址
	 */
	public M setUserEmail(java.lang.String userEmail) {
		set("user_email", userEmail);
		return (M)this;
	}
	
	/**
	 * 用户邮箱地址
	 */
	public java.lang.String getUserEmail() {
		return getStr("user_email");
	}

	/**
	 * 用户身份认证 (0未认证,1已认证)
	 */
	public M setUserAutonym(java.lang.Integer userAutonym) {
		set("user_autonym", userAutonym);
		return (M)this;
	}
	
	/**
	 * 用户身份认证 (0未认证,1已认证)
	 */
	public java.lang.Integer getUserAutonym() {
		return getInt("user_autonym");
	}

	/**
	 * 用户地址
	 */
	public M setUserSite(java.lang.String userSite) {
		set("user_site", userSite);
		return (M)this;
	}
	
	/**
	 * 用户地址
	 */
	public java.lang.String getUserSite() {
		return getStr("user_site");
	}

	/**
	 * 用户性别( 0女,1男)
	 */
	public M setUserSex(java.lang.Integer userSex) {
		set("user_sex", userSex);
		return (M)this;
	}
	
	/**
	 * 用户性别( 0女,1男)
	 */
	public java.lang.Integer getUserSex() {
		return getInt("user_sex");
	}

	/**
	 * 用户出生日期
	 */
	public M setUserDateBirth(java.util.Date userDateBirth) {
		set("user_date_birth", userDateBirth);
		return (M)this;
	}
	
	/**
	 * 用户出生日期
	 */
	public java.util.Date getUserDateBirth() {
		return get("user_date_birth");
	}

	/**
	 * 用户个性签名
	 */
	public M setUserSignature(java.lang.String userSignature) {
		set("user_signature", userSignature);
		return (M)this;
	}
	
	/**
	 * 用户个性签名
	 */
	public java.lang.String getUserSignature() {
		return getStr("user_signature");
	}

	/**
	 * 用户是否会员 ( 0否1是)
	 */
	public M setUserVip(java.lang.Integer userVip) {
		set("user_vip", userVip);
		return (M)this;
	}
	
	/**
	 * 用户是否会员 ( 0否1是)
	 */
	public java.lang.Integer getUserVip() {
		return getInt("user_vip");
	}

	/**
	 * 移动设备唯一cid
	 */
	public M setCId(java.lang.String cId) {
		set("c_id", cId);
		return (M)this;
	}
	
	/**
	 * 移动设备唯一cid
	 */
	public java.lang.String getCId() {
		return getStr("c_id");
	}

	/**
	 * 用户注册时间
	 */
	public M setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
		return (M)this;
	}
	
	/**
	 * 用户注册时间
	 */
	public java.util.Date getCreateTime() {
		return get("create_time");
	}

}
