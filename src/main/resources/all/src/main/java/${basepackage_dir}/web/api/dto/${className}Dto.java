<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.web.api.dto;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
<#include "/java_imports.include">
@ApiModel( value = "${className}Dto", description = "${table.remarks}")
public class ${className}Dto implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	<#list table.columns as column>
	/**  
     * ${column.remarks}  
     */
	@ApiModelProperty( value = "${column.remarks}", required = true )
	${column.hibernateValidatorExprssion}
	private ${column.simpleJavaType} ${column.columnNameLower};
	</#list>

<@generateJavaColumns/>
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
<#macro generateJavaColumns>
	<#list table.columns as column>
	public void set${column.columnName}(${column.simpleJavaType} value) {
		this.${column.columnNameLower} = value;
	}
	
	public ${column.simpleJavaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	</#list>
</#macro>