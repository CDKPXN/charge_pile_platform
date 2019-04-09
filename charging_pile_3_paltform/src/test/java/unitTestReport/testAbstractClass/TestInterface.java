package unitTestReport.testAbstractClass;


/**
 * @Author lides
 * @Description
 * @Date 18-7-29 16:58
 **/

public interface  TestInterface {

    public static final String USABLE = "测试可用";
    public static final String HAS_BEEN_AVAILABLE = "测试一直可用";
    public static final String BOUNDARY = "边界测试";
    public static final String BRANCH = "分支测试";
    public static final String EXCEPTION = "异常测试";



    /**
     * 测试可用
     */
    public void usable() ;


    /**
     * 测试一直可用
     */
    public void hasBeenAvailable() ;


    /**
     * 边界测试
     */
    public void boundary();

    /**
     * 分支测试
     */
    public void branch();

    /**
     * 异常测试
     */
    public void exception() ;

    /**
     * 执行器
     */
    default void executor(){
        usable();
        hasBeenAvailable();
        boundary();
        branch();
        exception();
    }
}
