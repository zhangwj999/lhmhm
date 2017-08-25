package com.lhmh.controller.patieninfo;

//import com.lhmh.financialbusinessmanagement.financialregistration.dao.BillMainDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by gang on 2015/10/20.
 */
public class Import {

    public void run() throws Exception {
        try {
        	System.out.println("Import begin");
        	String taskId="lhmh";
      	 /*  String taskId="lhmh";
            BillMainDaoImpl  billMainDaoImpl = (BillMainDaoImpl)ApplicationContextUtil.getContext().getBean("billMainDaoImpl");
            List list=billMainDaoImpl.querydb(taskId);
*/
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();

            Scheduler scheduler = schedulerFactory.getScheduler();
            //System.out.println("List ="+list);
          // if (list.size()==1){

               JobDetail jobDetail1 = new JobDetail(taskId, "jGroup1", ExcuteJob.class);
               SimpleTrigger simpleTrigger1 = new SimpleTrigger(taskId+"trigger1_1","tgroup1");
               scheduler.scheduleJob(jobDetail1, simpleTrigger1);
          // }
           scheduler.start();// �ݵ�������
           //
           System.out.println("Import end");

        } catch (Exception e) {

            e.printStackTrace();

        }

}
    public static void main(String[] args) throws Exception {

        Import example = new Import();
        example.run();
    }

}
