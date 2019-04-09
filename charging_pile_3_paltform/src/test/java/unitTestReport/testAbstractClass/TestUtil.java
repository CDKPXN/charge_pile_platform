package unitTestReport.testAbstractClass;


import org.apache.commons.lang3.time.DateFormatUtils;
import unitTestReport.entity.TestBody;
import unitTestReport.entity.TestReport;
import unitTestReport.fileGenerator.Generator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Author lides
 * @Description
 * @Date 18-8-1 17:39
 **/
public class TestUtil {
    /**
     * 生成测试报告
     * @param testReport    测试报告对象
     */
    public  static void generateReport(TestReport testReport){
        Map<String, String> map = new LinkedHashMap<>();
        map.put("environment",testReport.getEnvironment());
        map.put("tool",testReport.getTool());
        map.put("zlass",testReport.getZlass().getSimpleName());
        map.put("date",DateFormatUtils.format(testReport.getTestDate(),"yyyy-MM-dd HH:mm:ss"));
        StringBuffer stringBuffer = new StringBuffer();
        List<TestBody> testBodies = testReport.getTestBodies();
        int failure = 0;
        for (int i = 0; i < testBodies.size(); i++) {
            TestBody testBody = testBodies.get(i);
            stringBuffer.append("<tr>" +
                    "<td>"+(i+1)+"</td>" +
                    "<td>"+testBody.getFunction()+"</td>" +
                    "<td>"+testBody.getContent()+"</td>" +
                    "<td>"+testBody.getInputParameter()+"</td>" +
                    "<td>"+testBody.getOutputParameter()+"</td>" +
                    "<td>"+testBody.getExpectOutputParameter()+"</td>" +
                    "<td>"+testBody.getResult()+"</td>" +
                    "</tr>");
            if(!testBody.getResult()){
                failure++;
            }
        }
        int total = testBodies.size();
        map.put("total",total+"");
        map.put("success",total-failure+"");
        map.put("failure",failure+"");
        map.put("data",stringBuffer.toString());
        Class zlass = testReport.getZlass();
        String dir = zlass.getResource("").getPath().substring(1)
                .replace("target/test-classes","src/test/java")
                .replace("target/classes", "src/test/java");
        String path = dir+zlass.getSimpleName()+".html";
        Generator.generator(path,map);
    }

    /**
     * 获取测试配置文件的参数
     * @param propertiName
     * @return
     */
    public static String getProperties(String propertiName){
        String path= System.getProperty("user.dir")+
                File.separator+"src"+
                File.separator+"test"+
                File.separator+"java"+
                File.separator+"testAbstractClass"+
                File.separator+"testConfigure.properties";


        Properties properties = new Properties();
        try {
            InputStream resourceAsStream =new FileInputStream(path);
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String property = properties.getProperty(propertiName);
        return property;
    }

    /**
     * 初始化测试报告
     * @param zlass 测试类对象
     * @return
     */
    public  static TestReport initReport(Class zlass){
        String environment = TestUtil.getProperties("test.environment");
        String tool = TestUtil.getProperties("test.tool");
        TestReport testReport =  TestReport.getTestReport(environment,tool,zlass);
        if(testReport == null){
            throw new IllegalArgumentException("初始化测试报告失败，存在为null的参数");
        }
        return testReport;
    }

    /**
     * 通过反射初始化对象
     * @param target    初始化对象
     * @param filed     初始化字段
     * @param source    初始化值
     */
    public static void  initParameterByReflect(Object target,String filed,Object source){
        try {
            Field tbAdminMapper = target.getClass().getDeclaredField("tbAdminMapper");
            tbAdminMapper.setAccessible(true);
            tbAdminMapper.set(target,source);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
