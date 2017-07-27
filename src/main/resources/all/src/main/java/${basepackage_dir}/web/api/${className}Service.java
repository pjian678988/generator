<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.web.api;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eloancn.framework.utils.BeanMapper;
import com.eloancn.framework.orm.mybatis.paginator.domain.Order;
import com.eloancn.framework.orm.mybatis.paginator.domain.PageBounds;
import com.eloancn.framework.utils.ObjectUtils;

import ${basepackage}.model.${className};
import ${basepackage}.service.${className}ServiceBean;

import com.eloancn.framework.sevice.api.MessageConstant;
import com.eloancn.framework.sevice.api.PageParsDTO;
import com.eloancn.framework.sevice.api.MessageCode;
import com.eloancn.framework.sevice.api.MessageStatus;
import com.eloancn.framework.sevice.api.ResultDTO;
import com.eloancn.framework.sevice.api.PageResultDTO;
import com.eloancn.framework.orm.mybatis.paginator.PaginatorUtil;
import ${basepackage}.web.api.dto.${className}Dto;

<#include "/java_imports.include">
@Api(value = "${classNameLower}Service", description = "${table.remarks}", produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
@RequestMapping("/${classNameLower}Service")
public class ${className}Service {

	private static final Logger logger = LoggerFactory.getLogger(${className}Service.class);
	
	
	private ${className}ServiceBean ${classNameLower}ServiceBean;
	
	@Autowired
	public void set${className}ServiceBean(${className}ServiceBean ${classNameLower}ServiceBean) {
		this.${classNameLower}ServiceBean = ${classNameLower}ServiceBean;
	}
	
	/**  
     *   
     * <save one>  
     * @param ${classNameLower}  
     * @return ResultDTO<Integer>
     */ 
	@ApiOperation(value = "save ${className}", notes = "save one", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,response = ResultDTO.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = MessageConstant.INTERNAL_ERROR, message = MessageConstant.INTERNAL_ERROR_MSG)})
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody 
	ResultDTO<Integer> insert${className}(@ApiParam(value = "Json参数", required = true) @RequestBody @Valid ${className}Dto ${classNameLower}Dto){
		if(logger.isDebugEnabled()){
			logger.debug("${className}Service.insert${className}(${className}Dto ${classNameLower}Dto) start-->", ${classNameLower}Dto);
		}		
		ResultDTO<Integer> result= new ResultDTO<Integer>();
		${className} ${classNameLower} = null;
		try{
			${classNameLower}=BeanMapper.map(${classNameLower}Dto,${className}.class);
			${classNameLower}ServiceBean.insert(${classNameLower});
			result.setData(${classNameLower}.getId());
		}catch (Exception e) {
			result.setStatus(MessageStatus.SUCCESS.getValue());
			//result.setMessage(MessageCode.SUCCESS.getMessage());
			//result.setCode(MessageCode.SUCCESS.getCode());
			if(logger.isErrorEnabled()){
				logger.error("${className}Service.insert${className}(${className}Dto ${classNameLower}Dto) error\n", e);
			}
		}	
		if(logger.isDebugEnabled()){
			logger.debug("${className}Service.insert${className}(${className}Dto ${classNameLower}Dto) end-->", ${classNameLower});
		}
		return result;
	}
	 /**  
     *   
     * <update one>  
     * @param ${classNameLower}  
     * @return ResultDTO<Integer>  
     */ 
	@ApiOperation(value = "update ${className}", notes = "update one", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE,response = ResultDTO.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = MessageConstant.INTERNAL_ERROR, message = MessageConstant.INTERNAL_ERROR_MSG)})
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	ResultDTO<Integer> update${className}(@ApiParam(value = "Json参数", required = true) @RequestBody @Valid ${className}Dto ${classNameLower}Dto){
		if(logger.isDebugEnabled()){
			logger.debug("${className}Service.update${className}(${className}Dto ${classNameLower}Dto) start-->", ${classNameLower}Dto);
		}		
		ResultDTO<Integer> result= new ResultDTO<Integer>();
		int u=0;
		try{
			${className} ${classNameLower}=BeanMapper.map(${classNameLower}Dto,${className}.class);
			u=${classNameLower}ServiceBean.update(${classNameLower});
			result.setData(u);
		}catch (Exception e) {
			result.setStatus(MessageStatus.SUCCESS.getValue());
			//result.setMessage(MessageCode.SUCCESS.getMessage());
			//result.setCode(MessageCode.SUCCESS.getCode());
			if(logger.isErrorEnabled()){
				logger.error("${className}Service.update${className}(${className}Dto ${classNameLower}Dto) error\n", e);
			}
		}	
		if(logger.isDebugEnabled()){
			logger.debug("${className}Service.update${className}(${className}Dto ${classNameLower}Dto) end-->", u);
		}
		return result;
	}
	 /**  
     *   
     * <find one by id>  
     * @param id  
     * @return ResultDTO<${className}Dto>
     */ 
	@ApiOperation(value = "query ${className}", notes = "find one by id", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE,response = ResultDTO.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = MessageConstant.INTERNAL_ERROR, message = MessageConstant.INTERNAL_ERROR_MSG),
//			@ApiResponse(code = MessageConstant.NO_DATA, message = MessageConstant.NO_DATA_MSG)})
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@ResponseBody
	ResultDTO<${className}Dto> get${className}ById(@ApiParam(value = "/{id}路径参数", required = true)@PathVariable(value="id") ${table.idColumn.simpleJavaType} id){
		if(logger.isDebugEnabled()){
			logger.debug("${className}Service.get${className}ById(${table.idColumn.simpleJavaType} id) start-->", id);
		}		
		ResultDTO<${className}Dto> result= new ResultDTO<${className}Dto>();
		${className} ${classNameLower}=null;
		try{			
			${classNameLower}=${classNameLower}ServiceBean.get(id);
			${className}Dto ${classNameLower}Dto=BeanMapper.map(${classNameLower},${className}Dto.class);
			result.setData(${classNameLower}Dto);
		}catch (Exception e) {
			result.setStatus(MessageStatus.SUCCESS.getValue());
			//result.setMessage(MessageCode.SUCCESS.getMessage());
			//result.setCode(MessageCode.SUCCESS.getCode());
			if(logger.isErrorEnabled()){
				logger.error("${className}Service.get${className}ById(${table.idColumn.simpleJavaType} id) error\n", e);
			}
		}	
		if(logger.isDebugEnabled()){
			logger.debug("${className}Service.get${className}ById(${table.idColumn.simpleJavaType} id) end-->", ${classNameLower});
		}
		return result;
	}
	 /**  
     *   
     * <query all>  
     * @param pageParsDTO 
     * @return ResultDTO<List<${className}Dto>>
     */ 
	@ApiOperation(value = "query all ${className}", notes = "Paging query ${className}", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE,response = ResultDTO.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = MessageConstant.INTERNAL_ERROR, message = MessageConstant.INTERNAL_ERROR_MSG),
//			@ApiResponse(code = MessageConstant.NO_DATA, message = MessageConstant.NO_DATA_MSG)})
	@RequestMapping(value = "/query",method = RequestMethod.GET)
	@ResponseBody
	ResultDTO<PageResultDTO<${className}Dto>> search${className}(@ApiParam(value = "Page to fetch", required = true) @RequestBody PageParsDTO<Map> pageParsDTO){
		if(logger.isDebugEnabled()){
			logger.debug("${className}Service.search${className}(PageParsDTO<Map> pageParsDTO) start-->", pageParsDTO);
		}		
		ResultDTO<PageResultDTO<${className}Dto>> result= new ResultDTO<PageResultDTO<${className}Dto>>();
		List<${className}> ${classNameLower}List = null;
		try{
			${classNameLower}List=${classNameLower}ServiceBean.search(pageParsDTO.getParam(),new PageBounds(pageParsDTO.getPage(), pageParsDTO.getLimit(),  Order.formString(pageParsDTO.getSort())));
			PageResultDTO<${className}Dto> pageResultDTO=PaginatorUtil.mapList(${classNameLower}List,${className}Dto.class);
			result.setData(pageResultDTO);
		}catch (Exception e) {
			result.setStatus(MessageStatus.SUCCESS.getValue());
			//result.setMessage(MessageCode.SUCCESS.getMessage());
			//result.setCode(MessageCode.SUCCESS.getCode());
			if(logger.isErrorEnabled()){
				logger.error("${className}Service.search${className}(PageParsDTO<Map> pageParsDTO) error\n", e);
			}
		}	
		if(logger.isDebugEnabled()){
			logger.debug("${className}Service.search${className}(PageParsDTO<Map> pageParsDTO) end-->", ${classNameLower}List);
		}
		return result;
	}
}
