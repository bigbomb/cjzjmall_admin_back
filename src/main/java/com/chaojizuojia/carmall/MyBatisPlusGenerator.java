package com.chaojizuojia.carmall;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class MyBatisPlusGenerator {
	private static final String PROJECT_PATH = System.getProperty("user.dir");// 项目在硬盘上的基础路径
	private static final String JAVA_PATH = "/src/main/java"; // java文件路径
	private static final String RESOURCES_PATH = "/src/main/resources";// 资源文件路径
	public static void main(String[] args) throws SQLException {

		//1. 全局配置
				GlobalConfig config = new GlobalConfig();
				config.setActiveRecord(true) // 是否支持AR模式
					  .setAuthor("Bean") // 作者
					  //.setOutputDir("D:\\workspace_mp\\mp03\\src\\main\\java") // 生成路径
					  .setOutputDir((PROJECT_PATH + JAVA_PATH)) // 生成路径
					  .setFileOverride(true)  // 文件覆盖
					  .setEnableCache(false)
					  .setIdType(IdType.AUTO) // 主键策略
					  .setServiceName("%sService")  // 设置生成的service接口的名字的首字母是否为I
					  					   // IEmployeeService
		 			  .setBaseResultMap(true)//生成基本的resultMap
		 			  .setBaseColumnList(true);//生成基本的SQL片段 
				
				//2. 数据源配置
				DataSourceConfig  dsConfig  = new DataSourceConfig();
				dsConfig.setDbType(DbType.MYSQL)  // 设置数据库类型
						.setDriverName("com.mysql.cj.jdbc.Driver")
						.setUrl("jdbc:mysql://localhost:3306/test")
						.setUsername("root")
						.setPassword("123456");
				 
				//3. 策略配置globalConfiguration中
				StrategyConfig stConfig = new StrategyConfig();
				stConfig.setCapitalMode(true) //全局大写命名
						.setDbColumnUnderline(true)  // 指定表名 字段名是否使用下划线
						.setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
						//.setTablePrefix("tbl_")
						.setInclude("sys_user");  // 生成的表
				
				//4. 包名策略配置 
				PackageConfig pkConfig = new PackageConfig();
				pkConfig.setParent("com.chaojizuojia.carmall")
						.setMapper("dao")//dao
						.setService("service")//servcie
						.setServiceImpl("service.impl")
						.setController("controller")//controller
						.setEntity("model")
						.setXml("mapper");//mapper.xml
				
				
				//5. 整合配置
				AutoGenerator  ag = new AutoGenerator();
				ag.setGlobalConfig(config)
				  .setDataSource(dsConfig)
				  .setStrategy(stConfig)
				  .setPackageInfo(pkConfig);
				InjectionConfig abc = new InjectionConfig() {
		            @Override
		            public void initMap() {
		              
		            }
		        };
		        //自定义文件输出位置（非必须）
		        List<FileOutConfig> fileOutList = new ArrayList<FileOutConfig>();
		        fileOutList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
		            @Override
		            public String outputFile(TableInfo tableInfo) {
		                return PROJECT_PATH + RESOURCES_PATH+"/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
		            }

		        });
		        abc.setFileOutConfigList(fileOutList);
		        ag.setCfg(abc);
		        ag.setTemplate(
		                // 关闭默认 xml 生成，调整生成 至 根目录
		                new TemplateConfig().setXml(null)
		        );
		        ag.setTemplateEngine(new FreemarkerTemplateEngine());
				//6. 执行
				ag.execute();
	}

}
