package ${bussiPackage}.page.${entityPackage};

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

<#list subTab as sub>
import ${bussiPackage}.entity.${sub.entityPackage}.${sub.entityName}Entity;
</#list>

/**   
 * @Title: Entity
 * @Description: ${ftl_description}
 * @author onlineGenerator
 * @date ${ftl_create_time}
 * @version V1.0   
 *
 */
public class ${entityName}Page implements java.io.Serializable {
	<#list subTab as sub>
	/**保存-${sub.ftlDescription}*/
	private List<${sub.entityName}Entity> ${sub.entityName?uncap_first}List = new ArrayList<${sub.entityName}Entity>();
	public List<${sub.entityName}Entity> get${sub.entityName}List() {
		return ${sub.entityName?uncap_first}List;
	}
	public void set${sub.entityName}List(List<${sub.entityName}Entity> ${sub.entityName?uncap_first}List) {
		this.${sub.entityName?uncap_first}List = ${sub.entityName?uncap_first}List;
	}
	</#list>

	<#list columns as po>
	/**${po.content}*/
	private ${po.type} ${po.fieldName};
	</#list>
	
	<#list columns as po>
	/**
	 *方法: 取得${po.type}
	 *@return: ${po.type}  ${po.content}
	 */
	public ${po.type} get${po.fieldName?cap_first}(){
		return this.${po.fieldName};
	}

	/**
	 *方法: 设置${po.type}
	 *@param: ${po.type}  ${po.content}
	 */
	public void set${po.fieldName?cap_first}(${po.type} ${po.fieldName}){
		this.${po.fieldName} = ${po.fieldName};
	}
	</#list>
}
