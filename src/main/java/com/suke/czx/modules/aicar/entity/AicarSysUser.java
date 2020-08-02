package com.suke.czx.modules.aicar.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author yutang.jin
 * @email 15251524420@163.com
 * @date 2020-08-02 21:05:13
 */
@Data
@TableName("sys_user")
@EqualsAndHashCode(callSuper = true)
public class AicarSysUser extends Model<AicarSysUser> implements Serializable {
	private static final long serialVersionUID = 1L;

		//
		@TableId
		private Long userId;
		//
		private String username;
		//
		private String password;
		//
		private String salt;
		//
		private String email;
		//
		private String mobile;
		//
		private String status;
		//
		private Long createUserId;
		//创建日期
		private Date createTime;
		//
		private String openid;
		//
		private String unionid;
		//
		private String nickname;
		//头像
		private String avatar;
		//性别
		private String gender;
		//
		private Integer del;
	
}
