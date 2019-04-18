package com.company.project.platform.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.company.project.platform.config.PlatformConfig;
import com.company.project.platform.util.AesCBC;
import com.company.project.platform.util.PlatformUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class PlatformRequestBody {
    @JSONField(name="OperatorID")
    private String operatorID;
    @JSONField(name="Data")
    private String data;
    @JSONField(name="TimeStamp")
    private String timeStamp;
    @JSONField(name="Seq")
    private String seq;
    @JSONField(name="Sig")
    private String sig;

    public PlatformRequestBody() {
    }

    public PlatformRequestBody(String data) {
        try {
            this.data = AesCBC.encrypt(data).replaceAll("\r","").replaceAll("\n","");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.operatorID = PlatformConfig.OperatorID;
        this.timeStamp = PlatformUtil.getTimeStamp();
        this.seq = "0001";
        this.sig = PlatformUtil.getRequestSign(this);
    }
    
    public static void main(String[] args) {
    	String str="{\r\n" + 
    			"  \"EquipAuthSeq\": \"123456789201805071630123456\",\r\n" + 
    			"  \"ConnectorID\": \"3702120244102\"\r\n" + 
    			"}";
    	String str2="{\r\n" + 
    			"      \"OperatorID\":\"123456789\",\r\n" + 
    			"      \"OperatorSecret\":\"1234567890abcdef\"\r\n" + 
    			"  }";
    	String str1="fngkRhzHsmpJU0aL5Y6/pipOyja3lXteJDXtmaHU4VWNAuOZiVstjWBqfnKAD141PBY8SLI0xWqgMCt0QHUwAwzLNw4XhLqZ/4z+t6UBAeNN33FLXaKpXpQCTuOYHQJz";
    	//PlatformRequestBody pb=new PlatformRequestBody(str);
    	String ss="";
    	String ss1="";
    	try {
    		ss=AesCBC.decrypt(str1);
    		ss1=AesCBC.encrypt(str);
    		System.err.println(AesCBC.encrypt(str2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.err.println("----------解密---------");
    	System.err.println(ss);
    	System.err.println("----------加密---------");
    	System.err.println(ss1);

	}
}
