/**
 *
 */
package com.piggymetrics.account;

import org.mybatis.generator.api.ShellRunner;


/**
 * @author heroy
 *
 */
public class MybatisGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.setProperty("user.name", "Terry");
		String config = com.piggymetrics.account.MybatisGenerator.class.getClassLoader().getResource("generatorConfig.xml").getFile();
		String[] arg = { "-configfile", config, "-overwrite" };
		ShellRunner.main(arg);
	}
}
