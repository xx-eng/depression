package com.syzc.Kit;

import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.druid.DruidPlugin;
import com.syzc.config.MyConfig;

import javax.sql.DataSource;

/**
 * @author xueli
 * @email 2632624281@qq.com
 * @date 2022-01-17 11:35
 * @description
 */
public class GeneratorModel {
    public static DataSource getDataSource() {
        DruidPlugin druidPlugin = MyConfig.createDruidPlugin();
        druidPlugin.start();
        return druidPlugin.getDataSource();
    }

    public static void main(String[] args){
        //base model使用的包名
        String baseModelPackageName = "com.syzc.model.base";
        //base model 文件保存路径
        String baseModelOutputDir = System.getProperty("user.dir")+"/src/main/java/com/syzc/model/base";
        //model使用的包名
        String modelPackageName = "com.syzc.model";
        //model文件保存路径，（String baseModelOutputDir = PathKit.getWebRootPath()+"/src/main/java/com/syzc/model/base";
        String modelOutPutDir = baseModelOutputDir + "/..";
        //创建生成器
        Generator generator = new Generator(getDataSource(),baseModelPackageName,baseModelOutputDir,modelPackageName,modelOutPutDir);

        // 配置是否生成备注
        //    generator.setGenerateRemarks(true);
        // 设置数据库方言
        generator.setDialect(new MysqlDialect());
        // 设置是否生成链式 setter 方法
        //generator.setGenerateChainSetter(false);
        // 添加不需要生成的表名
        //    generator.addExcludedTable("adv");
        // 设置是否在 Model 中生成 dao 对象
        generator.setGenerateDaoInModel(true);
        //设置是否生成字典文件
        generator.setGenerateDataDictionary(false);
        // 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
        //    generator.setRemovedTableNamePrefixes("t_");
        // 生成
        generator.generate();
    }
}
