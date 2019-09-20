package com.demo.jfinal.util;

import com.demo.jfinal.config.AppConfig;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.druid.DruidPlugin;

import javax.sql.DataSource;

/**
 * JFinalDemoGenerator:配置数据库生成实体类和映射等
 * (注意报空指针是需要检查配置类关于插件的配置哪里需要_MappingKit.mapping(arp))
 *
 * @author zhangxiaoxiang
 * @date 2019/9/20
 */
public class JfinalModelGenerator {

    public static DataSource getDataSource() {
        DruidPlugin druidPlugin = AppConfig.createDruidPlugin();
        druidPlugin.start();
        return druidPlugin.getDataSource();
    }

    public static void main(String[] args) {
        // base model 所使用的包名
        String baseModelPackageName = "com.demo.jfinal.model.base";
        // base model 文件保存路径
        String baseModelOutputDir = PathKit.getWebRootPath() + "/src/main/java/com/demo/jfinal/model/base";

        // model 所使用的包名 (MappingKit 默认使用的包名)
        String modelPackageName = "com.demo.jfinal.model";
        // model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
        String modelOutputDir = baseModelOutputDir + "/..";

        // 创建生成器
        Generator generator = new Generator(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);


        // 配置是否生成备注
        generator.setGenerateRemarks(true);

        // 设置数据库方言
        generator.setDialect(new MysqlDialect());

        // 设置是否生成链式 setter 方法
        // generator.setGenerateChainSetter(false);
        generator.setGenerateChainSetter(true);

        // 添加不需要生成的表名
        generator.addExcludedTable("blog");

        // 设置是否在 Model 中生成 dao 对象(一般在service层)
        generator.setGenerateDaoInModel(false);


        // 设置是否生成字典文件
        generator.setGenerateDataDictionary(false);

        // 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
        generator.setRemovedTableNamePrefixes("t_");

        // 生成
        generator.generate();
    }
}




