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
@TableName("car")
@EqualsAndHashCode(callSuper = true)
public class Car extends Model<Car> implements Serializable {
	private static final long serialVersionUID = 1L;

		//
		@TableId
		private Integer carid;
		//
		private Date createdate;
		//
		private Integer deviceDeviceid;
		//电子围栏功能
		private String fence;
		//车牌号
		private String number;
		//车型
		private String type;
		//
		private Integer userUserid;
	
}
