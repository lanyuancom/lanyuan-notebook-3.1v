package com.lanyuan.mapper;

import java.util.List;

import com.lanyuan.entity.UserFormMap;
import com.lanyuan.mapper.base.BaseMapper;


public interface UserMapper extends BaseMapper{

	public List<UserFormMap> findUserPage(UserFormMap userFormMap);
	
}
