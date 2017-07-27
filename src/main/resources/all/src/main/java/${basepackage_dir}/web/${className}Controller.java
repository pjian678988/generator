
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameFirstLower = className?uncap_first>   
<#assign classNameLowerCase = className?lower_case>   
<#assign pkJavaType = table.idColumn.simpleJavaType>   

package ${basepackage}.web;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.eloancn.framework.orm.mybatis.paginator.domain.PageBounds;
import com.eloancn.framework.orm.mybatis.paginator.domain.Order;
import com.eloancn.framework.orm.mybatis.paginator.PaginatorUtil;
import com.eloancn.framework.annotation.Token;
import com.eloancn.framework.utils.BeanMapper;
import com.eloancn.framework.orm.mybatis.paginator.PaginatorUtil;
import ${basepackage}.service.${className}ServiceBean;
import ${basepackage}.model.${className};
import ${basepackage}.web.vo.${className}Vo;
<#include "/java_imports.include">
@Controller
@RequestMapping("/${classNameLowerCase}")
public class ${className}Controller{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private ${className}ServiceBean ${classNameFirstLower}ServiceBean;
	
	private final String LIST_ACTION = "redirect:/${classNameLowerCase}";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	@Autowired
	public void set${className}ServiceBean(${className}ServiceBean ${classNameFirstLower}ServiceBean) {
		this.${classNameFirstLower}ServiceBean = ${classNameFirstLower}ServiceBean;
	}
	
	/** 
	 * binder用于bean属性的设置 
	 */
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	       //eg: binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	   
	/**
	 * 增加了@ModelAttribute的方法可以在本controller方法调用前执行,可以存放一些共享变量,如枚举值,或是一些初始化操作
	 */
	@ModelAttribute
	public void init(ModelMap model) {
		//eg:model.put("now", new java.sql.Timestamp(System.currentTimeMillis()));
	}
	
	/** 
	 * 显示 
	 */
	@RequestMapping(value="/{id}")
	public String show(ModelMap model,@PathVariable ${pkJavaType} id) {
		${className} ${classNameFirstLower} = ${classNameFirstLower}ServiceBean.get(id);
		model.addAttribute("${classNameFirstLower}",BeanMapper.map(${classNameFirstLower},${className}Vo.class));
		return "/${classNameLowerCase}/show";
	}

	/** 
	 * 进入新增
	 */
	@RequestMapping(value="/new")
	@Token(saveToken=true)
	public String _new(ModelMap model,${className}Vo ${classNameFirstLower}) {
		model.addAttribute("${classNameFirstLower}",${classNameFirstLower});
		return "/${classNameLowerCase}/new";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	@Token(removeToken=true)
	public String create(ModelMap model,@Valid ${className}Vo ${classNameFirstLower},BindingResult errors){
		if(errors.hasErrors()) {
			return  "/${classNameLowerCase}/new";
		}	
		
		${classNameFirstLower}ServiceBean.insert(BeanMapper.map(${classNameFirstLower},${className}.class));
		return LIST_ACTION;
	}
	
	/** 进入编辑 */
	@RequestMapping(value="/{id}/edit")
	@Token(saveToken=true)
	public String edit(ModelMap model,@PathVariable ${pkJavaType} id){
		${className} ${classNameFirstLower} = ${classNameFirstLower}ServiceBean.get(id);
		model.addAttribute("${classNameFirstLower}",BeanMapper.map(${classNameFirstLower},${className}Vo.class));
		return "/${classNameLowerCase}/edit";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	@Token(removeToken=true)
	public String update(ModelMap model,@PathVariable ${pkJavaType} id,@Valid ${className}Vo ${classNameFirstLower},BindingResult errors) {
		if(errors.hasErrors()) {
			return "/${classNameLowerCase}/edit";
		}
		${classNameFirstLower}ServiceBean.update(BeanMapper.map(${classNameFirstLower},${className}.class));
		return LIST_ACTION;
	}
	@RequestMapping(value ="/list")
	public String list(ModelMap model,
	                 @RequestParam(required =false,defaultValue ="1")int page,
	                 @RequestParam(required =false,defaultValue ="20")int limit,
	                 @RequestParam(required =false) String sort,
	                 @RequestParam(required =false) String dir) {
		List<${className}> ${classNameFirstLower}List=${classNameFirstLower}ServiceBean.search(model,new PageBounds(page, limit, Order.create(sort,dir)));
		model.addAttribute("${classNameFirstLower}s",PaginatorUtil.toList(${classNameFirstLower}List,${className}Vo.class));
	    return LIST_ACTION;
	}
}

