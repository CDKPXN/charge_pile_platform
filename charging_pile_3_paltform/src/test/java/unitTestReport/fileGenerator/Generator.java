package unitTestReport.fileGenerator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.java.Log;

import java.io.*;
import java.util.Map;

/**
 * @Author lides
 * @Description
 * @Date 18-7-31 16:35
 **/
@Log
public class Generator {



    public static void generator(String filePath, Map<String, String> data)  {
        log.info("目录文件路径："+filePath);
        Template template = getTemplate();
        if (template == null) {
            log.info("模板初始化失败");
            return;
        }

        Writer outputStreamWriter = null;
        try {
            outputStreamWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.info("test工程文件目录不匹配");
        }
        try {
            template.process(data,outputStreamWriter);
            outputStreamWriter.flush();
            log.info("生成模板成功");
        } catch (IOException e) {
            e.printStackTrace();
            log.info("创建文件错误");
        } catch (TemplateException e) {
            e.printStackTrace();
            log.info("生成失败，缺少必要参数");
        } finally {
            try {
                outputStreamWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
                log.info("关闭流失败");
            }
        }
    }

    private static Template getTemplate() {
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        String uri = System.getProperty("user.dir") +
                File.separator + "src" +
                File.separator + "test" +
                File.separator + "java" +
                File.separator + "fileGenerator" +
                File.separator;
        log.info("模板所在目录："+uri);
        File dirFile = new File(uri);
        try {
            configuration.setDirectoryForTemplateLoading(dirFile);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("模板文件目录错误");
        }
        Template template = null;
        try {
            template = configuration.getTemplate("template.ftl");
        } catch (IOException e) {
            e.printStackTrace();
            log.info("模板文件不存在");
        }
        return template;
    }
}
