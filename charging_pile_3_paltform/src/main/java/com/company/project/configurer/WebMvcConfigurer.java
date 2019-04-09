package com.company.project.configurer;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.company.project.core.*;
import com.company.project.utils.JavaWebToken;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Spring MVC 配置
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);



    @Value("${portrait.path}")
    public String portraitPath;
    @Value("${adv.path}")
    public String advPath;
    @Value("${qrCode.path}")
    public String qrCodePath;
    @Value("${spring.profiles.active}")
    private String env;//当前激活的配置文件
    

   
    //使用阿里 FastJson 作为JSON MessageConverter
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //此解析用于返回response时使用
        FastJsonHttpMessageConverter converter=new FastJsonHttpMessageConverter();

    	//FastJsonHttpMessageConverter4 converter = new FastJsonHttpMessageConverter4();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(
        		SerializerFeature.WriteMapNullValue,//保留空的字段
        		SerializerFeature.WriteNullStringAsEmpty,//String null -> ""
        		SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.DisableCircularReferenceDetect);//禁止循环引用
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converters.add(converter);
        
    }


    //统一异常处理
  @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {   	
    	exceptionResolvers.add(new HandlerExceptionResolver() {
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
                Result result = new Result();
                if (e instanceof AuthoriedException) {
                    result = ResultGenerator.unauthorized(e.getMessage());
                }
                else if (e instanceof ServiceException) {//业务失败的异常，如“账号或密码错误”
                	String message = e.getMessage();
                    result.setCode(ResultCode.FAIL).setMessage(e.getMessage());                                                       
                    if (handler instanceof HandlerMethod) {
                        HandlerMethod handlerMethod = (HandlerMethod) handler;
                        message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                                request.getRequestURI(),
                                handlerMethod.getBean().getClass().getName(),
                                handlerMethod.getMethod().getName(),
                                e.getMessage());
                    } 
                    logger.info(message);
                } else if (e instanceof NoHandlerFoundException) {
                    result.setCode(ResultCode.NOT_FOUND).setMessage("接口 [" + request.getRequestURI() + "] 不存在");
                } else if (e instanceof ServletException) {
               	
                    result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
               } else if(e instanceof DataIntegrityViolationException ) {
                		result.setCode(ResultCode.FAIL).setMessage("参数异常：缺少必要的参数");
                		logger.error(e.getMessage(), e);
                }else {
                    result.setCode(ResultCode.INTERNAL_SERVER_ERROR)
                    .setMessage("接口 [" + request.getRequestURI() + "] 内部错误 "+e.getClass());
                    String message;
                    if (handler instanceof HandlerMethod) {
                        HandlerMethod handlerMethod = (HandlerMethod) handler;
                        message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                                request.getRequestURI(),
                                handlerMethod.getBean().getClass().getName(),
                                handlerMethod.getMethod().getName(),
                                e.getMessage());
                    } else {
                        message = e.getMessage();
                    }
                    logger.error(message, e);
                }
                responseResult(response, result);
                return new ModelAndView();
            }

        });
    }

    //解决跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	
        registry.addMapping("/**").allowedMethods("PUT","POST","GET","DELETE","OPTIONS")
        						.allowCredentials(true)
        						.allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept", "client_id", "uuid", "Authorization","token")
        						.allowedOrigins("*");
    }
    
      //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //接口签名认证拦截器，该签名认证比较简单，实际项目中可以使用Json Web Token或其他更好的方式替代。
            registry.addInterceptor(new HandlerInterceptorAdapter() {
                @Override
                public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {                	
                	String  method = request.getMethod();
                	String path = request.getRequestURI();
                	if(method.equals("OPTIONS")||((method.equals("GET")))) {
                		return true;
                	}
                	//验证签名
                    ImmutablePair pass = validateSign(request);
                    if ((boolean)pass.getLeft()) {
                        return true;
                    } else {
                        logger.warn("签名认证失败，请求接口：{}，请求IP：{}，请求参数：{}",
                                request.getRequestURI(), getIpAddress(request), JSON.toJSONString(request.getParameterMap()));
                        Result result = new Result();
                        result.setCode(ResultCode.UNAUTHORIZED).setMessage(pass.getRight()+"");
                        responseResult(response, result);
                        return false;
                    }
                }
            })
            .excludePathPatterns("/api/auth","/app","/app/auth","/app/chargingNet","/app/personalCenter/rechargeOrder/status"
            ,"/app/siteAuxiliary/site/*/chargingRecord","/api/h5pay/notifyUrl","/api/h5pay/returnUrl"
            ,"/app/personalCenter/password/update","/evcs/20160701/*");
    }

    private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    /**
     * 一个简单的签名认证，规则：
     * 1. 将请求参数按ascii码排序
     * 2. 拼接为a=value&b=value...这样的字符串（不包含sign）
     * 3. 混合密钥（secret）进行md5获得签名，与请求的签名进行比较
     * @throws ServletException 
     */
    private ImmutablePair<Boolean, String> validateSign(HttpServletRequest request) throws ServletException {
    	String id = JavaWebToken.parserStaffIdByToken(request);
    	String token = request.getHeader("token");
    	String message = "";
        boolean flag = StringUtils.isEmpty(id)?false:true;

        return ImmutablePair.of(flag,message);
    }

    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 如果是多级代理，那么取第一个ip为客户端ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(0, ip.indexOf(",")).trim();
        }

        return ip;
    }
   //静态资源配置
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/**")
                .addResourceLocations("file:"+portraitPath,"file:"+advPath,"file:"+qrCodePath);
    }
}
