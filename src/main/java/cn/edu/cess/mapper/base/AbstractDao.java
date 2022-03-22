/*
* Copyright (c) 2015-2018 SHENZHEN JST SCIENCE AND TECHNOLOGY DEVELOP CO., LTD. All rights reserved.
*
* 注意：本内容仅限于深圳市捷顺金科研发有限公司内部传阅，禁止外泄以及用于其他的商业目的 
*/
package cn.edu.cess.mapper.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

public class AbstractDao {
	
	 @Resource public BaseDao baseDaoXxl ;

	 protected Logger  log = LoggerFactory.getLogger(getClass());

}
