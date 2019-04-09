package unitTestReport.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author lides
 * @Description 测试报告对象。存储参数时请使用LinkedHashMap，为了保证有序
 * @Date 18-7-29 09:25
 **/
@Data
@Accessors(chain = true)
public class TestReport implements Serializable {
    /**
     * 测试环境
     */
    private String environment;
    /**
     * 测试工具
     */
    private String tool;
    /**
     *测试类
     */
    private Class zlass;

    /**
     *测试日期
     */
    private Date testDate;
    /**
     * 测试主体集
     */
    private List<TestBody> testBodies;


    private  TestReport(String environment, String tool, Class zlass) {
            this.environment = environment;
            this.tool = tool;
            this.zlass = zlass;
            this.testDate =new Date();
            this.testBodies = new ArrayList<>();
    }
    public void add(TestBody testBody){
        testBodies.add(testBody);
    }
    public static TestReport getTestReport(String environment, String tool, Class zlass){
        if( environment == null || tool == null || zlass == null){
            return null;
        }
        return new TestReport(environment,tool,zlass);
    }

}
