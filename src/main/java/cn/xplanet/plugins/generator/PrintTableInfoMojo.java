package cn.xplanet.plugins.generator;

import cn.org.rapid_framework.generator.GeneratorFacade;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * goal print : print all table names
 */
@Mojo(name = "print")
public class PrintTableInfoMojo extends AbstarctGeneratorMojo {

	public void execute()
			throws MojoExecutionException, MojoFailureException {
		Thread currentThread = Thread.currentThread();
		ClassLoader oldClassLoader = currentThread.getContextClassLoader();
		try {
			currentThread.setContextClassLoader(getClassLoader());
			System.out.println("current project to build: " + getProject().getName() + "\n" + getProject().getFile().getParent());

			printAllTableNames();
		} finally {
			currentThread.setContextClassLoader(oldClassLoader);
		}
	}

	public static void printAllTableNames() {
		try {
			GeneratorFacade.printAllTableNames();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		printAllTableNames();
	}
}