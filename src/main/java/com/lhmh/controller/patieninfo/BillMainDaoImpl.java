package com.lhmh.controller.patieninfo;

import org.jeecgframework.core.common.dao.jdbc.SimpleJdbcTemplate;
import org.jeecgframework.core.util.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务审核jdbc DAO层
 *
 * @author jarod
 */
@Repository("billMainDaoImpl")
public class BillMainDaoImpl extends SimpleJdbcTemplate {
    @Autowired
    public BillMainDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * 根据taskId，查询NVR信息
     * @param taskId
     * @return
     */
    public List querydb(String taskId){
        StringBuilder billSql = new StringBuilder();
        billSql.append(" select DB_DRIVER,IP,DB_NAME,USER_ID,PASSWORD,DB_TYPE,PORT,OFWHERE from pi_com_db ");
        if(taskId!=null){
            billSql.append(" where com_name='"+taskId+"'");
        }
        return   jdbcTemplate.queryForList(billSql.toString());
    }


    /**
     *   业务单删除
     *   删除删除bill和bill_product、bill_detail、bill_photo
     *   @param billId 业务流水号
     */

    public int deleteBillByBillId(String billId) {
         int count =0;
            //删除bill和bill_product、bill_detail
        StringBuilder billSql = new StringBuilder();
        billSql.append("SELECT ID,");
        billSql.append("    BILL_ID,");
        billSql.append("    OFFICE_ID,");
        billSql.append("    USER_ID,");
        billSql.append("    USER_NAME,");
        billSql.append("    CRT_DATE,");
        billSql.append("    CUST_ID,");
        billSql.append("    BILL_TYPE,");
        billSql.append("    BANK_ID,");
        billSql.append("    STORAGE_ID,");
        billSql.append("    TERMINAL_ID,");
        billSql.append("    SIGN_STATUS,");
        billSql.append("    CRT_TIME,");
        billSql.append("    BEGIN_TIME,");
        billSql.append("    END_TIME,");
        billSql.append("    FBEGIN_TIME,");
        billSql.append("    FEND_TIME,");
        billSql.append("    STATUS,");
        billSql.append("    B_STATUS,");
        billSql.append("    FILE_COUNT,");
        billSql.append("    NOTE");
        billSql.append(" FROM bill");
        billSql.append(" where  BILL_ID=?");
        Map billMap = jdbcTemplate.queryForMap(billSql.toString(), billId);
        String id = (String) billMap.get("ID");
        jdbcTemplate.update("DELETE  from bill_product where  BILL_ID=?",id);
        jdbcTemplate.update("DELETE  from bill_detail where  BILL_ID=?",billId);
        jdbcTemplate.update("DELETE  from bill_img where  BILL_ID=?",billId);
        count =  jdbcTemplate.update("DELETE  from bill where  BILL_ID=?",billId);
        return count;
    }
    public int insertPatieninfob(Map map) { 
       System.out.println(map);
       //jdbcTemplate.update("DELETE  from patieninfob ");
       String patient_id=(String)map.get("patient_id");
       String name=(String)map.get("name");
       String f_age=(String)map.get("f_age");
       String f_sex=(String) map.get("f_sex");
       String f_type=(String)map.get("f_type");
       String f_memo=(String) map.get("f_memo");
       String id=(String) map.get("id");
       StringBuffer sb=new StringBuffer();
       sb.append("insert into patieninfob (f_id,f_name,f_age,f_sex,f_type,f_memo,id)");
       sb.append("values(?,?,?,?,?,?,?)");
      // count = 
       //jdbcTemplate.update("DELETE  from bill where  BILL_ID=?",billId);
    	//systemService.executeSql(sb.toString());
      // String updateSql = "update bill set beginTime='"+beginTime+"',endtime='"+endtime+"',status='0'"+" where bill_id='"+billId+"'";
       // systemService.executeSql(updateSql);
       return jdbcTemplate.update(sb.toString(),UUIDGenerator.generate(),patient_id,name,f_age,f_sex,f_type,f_memo,id);
    } 
    public int insertPatieninfo(StringBuffer sb) {
    	System.out.println(sb.toString());
   //systemService.executeSql(updateSql);
       return jdbcTemplate.update(sb.toString());
    } 
}