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
@TableName("recharge")
@EqualsAndHashCode(callSuper = true)
public class Recharge extends Model<Recharge> implements Serializable {
	private static final long serialVersionUID = 1L;

		//
		private Date createdate;
		//
		private String data;
		//
		@TableId
		private Integer rechargeid;
		//
		private Integer userUserid;
	
}
