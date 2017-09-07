package com.cn.vanke.junit;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
/***
 * 添加@WebAppConfiguration是为了解决swagger+springmvc时报错的问题
 * @author zengh05
 */
@RunWith(SpringJUnit4ClassRunner.class) 
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:/spring/springmvc.xml","classpath*:/spring/applicationContext.xml"})
public class BaseJunitTest {
}
