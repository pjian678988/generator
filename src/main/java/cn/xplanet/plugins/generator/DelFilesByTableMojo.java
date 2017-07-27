package cn.xplanet.plugins.generator;

import cn.org.rapid_framework.generator.GeneratorFacade;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * goal del : delete template file
 */
@Mojo(name = "del")
public class DelFilesByTableMojo extends AbstarctGeneratorMojo {
	/**
	 * -Dtable=* -Dtable=user_info
	 */
	@Parameter(property = "table")
	public String tableParameter;
	/**
	 * -Dtemplate=* -Dtemplate=dao
	 */
	@Parameter(property = "template", defaultValue = "all")
	public String template;
	private GeneratorFacade g;

	public void execute()
			throws MojoExecutionException, MojoFailureException {
		Thread currentThread = Thread.currentThread();
		ClassLoader oldClassLoader = currentThread.getContextClassLoader();
		try {
			currentThread.setContextClassLoader(getClassLoader());

			System.out.println("current project to build: " + getProject().getName());

			this.g = new GeneratorFacade();
			if (null == this.template)
				this.template = "all";
			this.g.getGenerator().setTemplateRootDirs(new String[]{"classpath:" + this.template});
			delByTable(parseStringArray(this.tableParameter));
		} finally {
			currentThread.setContextClassLoader(oldClassLoader);
		}
	}

	public void delByTable(String[] table) {
		try {
			this.g.deleteByTable(table);
			AbstarctGeneratorMojo.MojoUtil.openFileIfOnWindows();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}