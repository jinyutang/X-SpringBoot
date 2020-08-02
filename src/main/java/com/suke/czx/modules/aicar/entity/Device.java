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
 * @email 619366438@qq.com
 * @date 2020-08-01 16:07:45
 */
@Data
@TableName("device")
@EqualsAndHashCode(callSuper = true)
public class Device extends Model<Device> implements Serializable {
	private static final long serialVersionUID = 1L;

		//
		private Date createdate;
		//截止日期
		private Date deadline;
		//
		@TableId
		private Integer deviceid;
		//启用日期
		private Date enabledate;
		//
		private String mobile;
		//
		private String model;
		//
		private String modelid;
		//
		private String qrcode;
		//
		private String sn;
		//VIP状态
		private String vipstatus;
		//VIP类型
		private String viptype;
	
}
