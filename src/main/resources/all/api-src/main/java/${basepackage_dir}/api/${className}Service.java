<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.api;

import com.eloancn.framework.sevice.api.PageResultDTO;
import com.eloancn.framework.sevice.api.ResultDTO;
import com.eloancn.framework.sevice.api.PageParsDTO;
import ${basepackage}.dto.${className}Dto;
<#include "/java_imports.include">
public interface ${className}Service{
	/**  
     *   
     * <save one>  
     * @param ${classNameLower}  
     * @return ResultDTO<Integer>
     */ 
	ResultDTO<Integer> insert${className}(${className}Dto ${classNameLower}Dto);
	 /**  
     *   
     * <update one>  
     * @param ${classNameLower}  
     * @return ResultDTO<Integer>  
     */ 
	ResultDTO<Integer> update${className}(${className}Dto ${classNameLower}Dto);
	 /**  
     *   
     * <find one by id>  
     * @param id  
     * @return ResultDTO<${className}Dto>
     */ 
	ResultDTO<${className}Dto> get${className}ById(${table.idColumn.simpleJavaType} id);
	 /**  
     *   
     * <query all>  
     * @param page  
     * @param limit  
     * @param sort  
     * @param dir  
     * @return ResultDTO<List<${className}Dto>>
     */ 
	ResultDTO<PageResultDTO<${className}Dto>> search${className}(PageParsDTO<Map> pageParsDTO);

}