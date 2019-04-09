package unitTestReport.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author lides
 * @Description
 * @Date 18-8-1 09:08
 **/
@Data
@Accessors(chain = true)
public class TestBody {
    /**
     *测试方法
     */
    private String function;
    /**
     *测试内容
     */
    private String content;
    /**
     *测试输入参数
     */
    private String inputParameter;
    /**
     *输出结果
     */
    private String outputParameter;
    /**
     *测试预期输出参数
     */
    private String expectOutputParameter;

    /**
     *  测试结论
     */
    private Boolean result;
}
