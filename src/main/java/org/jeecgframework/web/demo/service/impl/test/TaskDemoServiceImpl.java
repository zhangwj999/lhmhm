package org.jeecgframework.web.demo.service.impl.test;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.jeecgframework.web.demo.service.test.TaskDemoServiceI;
import com.lhmh.controller.patieninfo.Import;

@Service("taskDemoService")
public class TaskDemoServiceImpl implements TaskDemoServiceI {

	
	public void work() {
		org.jeecgframework.core.util.LogUtil.info(new Date().getTime());
		org.jeecgframework.core.util.LogUtil.info("----------任务测试-------");
		Import importjob = new Import();
		try {
			importjob.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
