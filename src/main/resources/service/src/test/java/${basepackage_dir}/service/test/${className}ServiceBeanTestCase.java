<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.service.test;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.spring.annotation.SpringBeanByType;

import com.eloancn.framework.orm.mybatis.paginator.domain.PageBounds;
import com.eloancn.framework.test.TestBase;
import com.eloancn.framework.test.dataset.xml.XMLDataSetBeanFactory;

import ${basepackage}.model.${className};
import ${basepackage}.service.${className}ServiceBean;

<#include "/java_imports.include">
public class ${className}ServiceBeanTestCase extends TestBase {

	@SpringBeanByType
	private ${className}ServiceBean ${classNameLower}ServiceBean;

	@Test
	// @ExpectedDataSet("/testCase/${className}ServiceBeanTestCase_data.xls")
	@ExpectedDataSet("/testCase/${className}ServiceBeanTestCase_data.xml")
	public void insertTest() {
		try {
			${className} ${classNameLower} = XMLDataSetBeanFactory.createBean(
					"/testCase/${className}ServiceBeanTestCase_data.xml", "city",
					${className}.class);
			this.${classNameLower}ServiceBean.insert(${classNameLower});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	// @ExpectedDataSet("/testCase/${className}ServiceBeanTestCase_data.xls")
	@ExpectedDataSet("/testCase/${className}ServiceBeanTestCase_data.xml")
	public void updateTest() {
		try {
			${className} ${classNameLower} = XMLDataSetBeanFactory.createBean(
					"/testCase/${className}ServiceBeanTestCase_data.xml", "city",
					${className}.class);
			this.${classNameLower}ServiceBean.update(${classNameLower});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	// @DataSet("/testCase/${className}ServiceBeanTestCase_data.xls")
	@DataSet("/testCase/${className}ServiceBeanTestCase_data.xml")
	public void getTest() {
		${className} ${classNameLower} = this.${classNameLower}ServiceBean.get(1);
		assertNotNull("${className} does not exist", ${classNameLower});
		//assertEquals("jan", ${classNameLower}.getName());
	}

	@Test
	// @DataSet("/testCase/${className}ServiceBeanTestCase_data.xls")
	@DataSet("/testCase/${className}ServiceBeanTestCase_data.xml")
	public void searchTest() {
		Map paramMap = new HashMap();
		// paramMap.put(key, value);
		List<${className}> list = this.${classNameLower}ServiceBean.search(paramMap, new PageBounds(
				1, 10));
		assertNotNull("list.get(0) does not exist", list.get(0));
		// assertEquals("jan", list.get(0).getName());
	}
}
