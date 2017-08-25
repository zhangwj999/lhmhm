package com.lhmh.controller.patieninfo;

import org.jeecgframework.core.util.ApplicationContextUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 启动录像任务
 * 
 */
public class ExcuteJob implements Job {


    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    	System.out.println("job testssss。。execute。。。。begin");
        //获取taskId
    	Connection conn = null;  
        String taskId=jobExecutionContext.getJobDetail().getName() ;
        BillMainDaoImpl  billMainDaoImpl = (BillMainDaoImpl)ApplicationContextUtil.getContext().getBean("billMainDaoImpl");
        List dblist=billMainDaoImpl.querydb(taskId);
    	Map dbMap=(Map) dblist.get(0);
		String dbtype=(String) dbMap.get("DB_TYPE");
		String UserName =(String) dbMap.get("USER_ID");// 数据库用户登陆名 ( 也有说是 schema 名字的 )  
		String Password = dbMap.get("PASSWORD")==null?"":(String) dbMap.get("PASSWORD");//"inspur69";// 密码 
		String dbdriver=(String) dbMap.get("DB_DRIVER");
		String ip=(String) dbMap.get("IP");
		String dbName=(String) dbMap.get("DB_NAME");
		String port=(String) dbMap.get("PORT");
		String where=(String) dbMap.get("OFWHERE");
		if (where==null) where="";
    	List list =new ArrayList();
    	System.out.println("Password"+Password);
    	try {
    		Class.forName(dbdriver);
    		String url = "";
    		if (dbtype.equals("oracle")){
    			url = "jdbc:oracle:thin:@"+ip+":"+port+":"+dbName;// 数据库连接，oracle代表链接的是oracle数据库；thin:@MyDbComputerNameOrIP代表的是数据库所在的IP地址（可以保留thin:）；1521代表链接数据库的端口号；ORCL代表的是数据库名称  
    		}else if (dbtype.equals("db2")){
    			url = "jdbc:db2://"+ip+":"+port+":"+dbName;// 数据库连接，oracle代表链接的是oracle数据库；thin:@MyDbComputerNameOrIP代表的是数据库所在的IP地址（可以保留thin:）；1521代表链接数据库的端口号；ORCL代表的是数据库名称  
    			
    		}else if (dbtype.equals("mysql")){
    			url="jdbc:mysql://"+ip+":"+port+"/"+dbName;
    			if (Password.equals("null")||Password.equals("NULL"))
    			Password="";
      		}
        	System.out.println("Password"+Password+"URL="+url);
			conn = DriverManager.getConnection(url, UserName, Password);
			System.out.println("sucess"+conn);
			Statement pmts = conn.createStatement();
			//String sql="select patient_id,Name,'0' f_age,'0' f_sex,'10' f_type,'' f_memo, patient_id id from yg_v_operation_info where operation_date>=''";
			//默认查询当天
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			
			String tody = sdf.format(new Date());
			
			String sql="SELECT yv.operation_date,PATIENT_VISIT_ID, patient_id,	NAME,OPERATION_NO,OPERATION,DEPT_CODE, DEPT_NAME,"
					+"SURGEON,SURGEON1,FIRST_ASSISTANT,FIRST_ASSISTANT1,ANESTHESIA_DOCTOR,ANESTHESIA_DOCTOR1,FIRST_OPERATION_NURSE,"
					+"FIRST_OPERATION_NURSE1,FIRST_SUPPLY_NURSE, FIRST_SUPPLY_NURSE1,operating_room_no  "
					+"from YG_V_OPERATION_INFO yv where  1=1 "+where
					+" and (operation_date = to_char(sysdate,'yyyy-mm-dd') or operation_date is null) order by operation_date desc,patient_id ";  //AND yv.operation_date >= '%s'                                                                                               
			System.out.println(sql);
			ResultSet set = pmts.executeQuery(sql);
			//mselect patient_id,Name,'0' f_age,'0' f_sex,'10' f_type,'' f_memo, patient_id id from yg_v_operation_info
			@SuppressWarnings("rawtypes")
			Map map=new HashMap();
		    StringBuffer sbmx=new StringBuffer();
		    //sbmx.append("insert into patieninfob (f_id,f_name,f_age,f_sex,f_type,f_memo,id)");
		    sbmx.append("insert into YG_V_OPERATION_INFO (operation_date,PATIENT_VISIT_ID, patient_id,NAME,OPERATION_NO,OPERATION,"
					+"DEPT_CODE, DEPT_NAME,SURGEON,SURGEON1,FIRST_ASSISTANT,FIRST_ASSISTANT1,ANESTHESIA_DOCTOR,ANESTHESIA_DOCTOR1,"
					+"FIRST_OPERATION_NURSE,FIRST_OPERATION_NURSE1,FIRST_SUPPLY_NURSE, FIRST_SUPPLY_NURSE1,operating_room_no)");
		    //   sb.append("values(?,?,?,?,?,?,?)");
		    int i=0;
			while (set.next()){
				/*map=new HashMap();
				map.put("patient_id", set.getString(1));
				map.put("name", set.getString(2));
				map.put("f_age", set.getString(3));
				map.put("f_sex", set.getString(4));
				map.put("f_type", set.getString(5));
				map.put("f_memo", set.getString(6));
				map.put("id", set.getString(7));*/
				if (i>0){
					sbmx.append(",");
				}else{
					sbmx.append(" values ");
				}
				sbmx.append("('"+set.getString(1)+"','"+set.getString(2)+"','"
				+set.getString(3)+"','"+set.getString(4)+"','"+set.getString(5)
						+"','"+set.getString(6)
						+"','"+set.getString(7)
						+"','"+set.getString(8)
						+"','"+set.getString(9)
						+"','"+set.getString(10)
						+"','"+set.getString(11)
						+"','"+set.getString(12)
						+"','"+set.getString(13)
						+"','"+set.getString(14)
						+"','"+set.getString(15)
						+"','"+set.getString(16)
						+"','"+set.getString(17)
						+"','"+set.getString(18)
						+"','"+set.getString(19)+"')");
				i++;
			}
			if (i>0){
			    StringBuffer sb=new StringBuffer();  
			    sb.append("delete from YG_V_OPERATION_INFO");
				billMainDaoImpl.insertPatieninfo(sb);
				billMainDaoImpl.insertPatieninfo(sbmx);	           
	    	}
			System.out.println("sucess");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	

    	
    	
    	
        //BillMainDaoImpl billMainDaoImpl = (BillMainDaoImpl) ApplicationContextUtil.getContext().getBean("billMainDaoImpl");
       /* for (int i=0;i<list.size();i++){
            Map map = (Map)list.get(i);
           billMainDaoImpl.insertPatieninfob(map);
        }*/
    	


        //查询当天业务
       // BillMainDaoImpl billMainDaoImpl = (BillMainDaoImpl) ApplicationContextUtil.getContext().getBean("billMainDaoImpl");
      // List list =  billMainDaoImpl.queryTermina(taskId);
        
        System.out.println("调用logafter。。。。。。。");
 
    }
    public List getpationinfo(Map dbMap){
    	List newList=new ArrayList();
		
		return newList;
    }

}
