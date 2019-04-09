import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.company.project.platform.common.PlatformRequestBody;
import com.company.project.platform.config.PlatformConfig;
import com.company.project.platform.model.ChargeDetail;
import com.company.project.platform.util.AesCBC;
import com.company.project.platform.util.HMacMD5;
import com.company.project.platform.util.PlatformToken;
import com.company.project.platform.util.PlatformUtil;
import com.company.project.utils.HttpClientUtil;
import com.company.project.utils.JsonUtil_My;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author lides
 * @Description
 * @Date 18-8-2 14:58
 **/


public class MainTest {
    public static void main(String[] args) throws Exception {

        thread(2,1000);
        thread(3,0);

    }

    public static void thread(final int a,long time){
        ThreadLocal<Object> threadLocal = new ThreadLocal<>();
        Thread thread = new Thread(() -> {
            threadLocal.set(a);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object o = threadLocal.get();
            System.out.println(o);
        });
        thread.start();
    }
}


