package com.suke.czx.modules.aicar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.suke.czx.modules.aicar.mapper.CarMapper;
import com.suke.czx.modules.aicar.entity.Car;
import com.suke.czx.modules.aicar.service.CarService;



@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {
	
}
