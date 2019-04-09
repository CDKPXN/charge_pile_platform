package com.company.project.platform.util;

import com.company.project.platform.config.PlatformConfig;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HMacMD5
{
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String key = "v3i46R8Rsh1CEstf";
        String OperatorID = "123456789";
        String Time = "20160729142400";
        String seq = "0001";
        String data = "123456789il7B0BSEjFdzpyKzfOFpvg/Se1CP802RItKYFPfSLRxJ3jf0bVl9hvYOEktPAYW2nd7S8MBcyHYy\n" +
                "acHKbISq5iTmDzG+ivnR+SZJv3USNTYVMz9rCQVSxd0cLlqsJauko79NnwQJbzDTyLooYoIwz7\n" +
                "5qBOH2/xOMirpeEqRJrF/EQjWekJmGk9RtboXePu2rka+Xm51syBPhiXJAq0GfbfaFu9tNqs/e2Vjj\n" +
                "a/ltE1M0lqvxfXQ6da6HrThsm5id4ClZFIi0acRfrsPLRixS/IQYtksxghvJwbqOsbIsITail9Ayy4tKcoge\n" +
                "EZiOO+4Ed264NSKmk7l3wKwJLAFjCFogBx8GE3OBz4pqcAn/ydA=201607291424000001\n" +
                "9CF107B283E6C9DB44D1CC810476B498";
        System.out.println(data);
        String hmacMd5Str = HMacMD5.getHmacMd5Str(key, data);
        System.out.println(new String(hmacMd5Str));

    }
    /**
     * 计算参数的md5信息
     * @param str 待处理的字节数组
     * @return md5摘要信息
     * @throws NoSuchAlgorithmException
     */
    private static byte[] md5(byte[] str)
            throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str);
        return md.digest();
    }

    /**
     * 将待加密数据data，通过密钥key，使用hmac-md5算法进行加密，然后返回加密结果。
     * 参照rfc2104 HMAC算法介绍实现。
     * @author 尹星
     * @param key 密钥
     * @param data 待加密数据
     * @return 加密结果
     * @throws NoSuchAlgorithmException
     */
    public static byte[] getHmacMd5Bytes(byte[] key,byte[] data) throws NoSuchAlgorithmException
    {
        /* HmacMd5 calculation formula: H(K XOR opad, H(K XOR ipad, text))
         * HmacMd5 计算公式：H(K XOR opad, H(K XOR ipad, text))
         * H代表hash算法，本类中使用MD5算法，K代表密钥，text代表要加密的数据
         * ipad为0x36，opad为0x5C。
         */
        int length = 64;
        byte[] ipad = new byte[length];
        byte[] opad = new byte[length];
        for(int i = 0; i < 64; i++)
        {
            ipad[i] = 0x36;
            opad[i] = 0x5C;
        }
        byte[] actualKey = key; //Actual key.
        byte[] keyArr = new byte[length]; //Key bytes of 64 bytes length
        /*If key's length is longer than 64,then use hash to digest it and use the result as actual key.
         * 如果密钥长度，大于64字节，就使用哈希算法，计算其摘要，作为真正的密钥。
         */
        if(key.length>length)
        {
            actualKey = md5(key);
        }
        for(int i = 0; i < actualKey.length; i++)
        {
            keyArr[i] = actualKey[i];
        }

        /*append zeros to K
         * 如果密钥长度不足64字节，就使用0x00补齐到64字节。
         */
        if(actualKey.length < length)
        {
            for(int i = actualKey.length; i < keyArr.length; i++)
                keyArr[i] = 0x00;
        }

        /*calc K XOR ipad
         * 使用密钥和ipad进行异或运算。
         */
        byte[] kIpadXorResult = new byte[length];
        for(int i = 0; i < length; i++)
        {
            kIpadXorResult[i] = (byte) (keyArr[i] ^ ipad[i]);
        }

        /*append "text" to the end of "K XOR ipad"
         * 将待加密数据追加到K XOR ipad计算结果后面。
         */
        byte[] firstAppendResult = new byte[kIpadXorResult.length+data.length];
        for(int i=0;i<kIpadXorResult.length;i++)
        {
            firstAppendResult[i] = kIpadXorResult[i];
        }
        for(int i=0;i<data.length;i++)
        {
            firstAppendResult[i+keyArr.length] = data[i];
        }

        /*calc H(K XOR ipad, text)
         * 使用哈希算法计算上面结果的摘要。
         */
        byte[] firstHashResult = md5(firstAppendResult);

        /*calc K XOR opad
         * 使用密钥和opad进行异或运算。
         */
        byte[] kOpadXorResult = new byte[length];
        for(int i = 0; i < length; i++)
        {
            kOpadXorResult[i] = (byte) (keyArr[i] ^ opad[i]);
        }

        /*append "H(K XOR ipad, text)" to the end of "K XOR opad"
         * 将H(K XOR ipad, text)结果追加到K XOR opad结果后面
         */
        byte[] secondAppendResult = new byte[kOpadXorResult.length+firstHashResult.length];
        for(int i=0;i<kOpadXorResult.length;i++)
        {
            secondAppendResult[i] = kOpadXorResult[i];
        }
        for(int i=0;i<firstHashResult.length;i++)
        {
            secondAppendResult[i+keyArr.length] = firstHashResult[i];
        }

        /*H(K XOR opad, H(K XOR ipad, text))
         * 对上面的数据进行哈希运算。
         */
        byte[] hmacMd5Bytes = md5(secondAppendResult);

        return hmacMd5Bytes;

    }
    public static String getHmacMd5Str(String data){
        String hmacMd5Str = getHmacMd5Str(PlatformConfig.SigSecret, data);
        return hmacMd5Str;
    }
    public static String getHmacMd5Str(String key,String data)
    {
        data = data.replaceAll("\n","").replaceAll("\r","");
        String result = "";
        try
        {
            byte[] keyByte = key.getBytes("UTF-8");
            byte[] dataByte = data.getBytes("UTF-8");
            byte[] hmacMd5Byte = getHmacMd5Bytes(keyByte,dataByte);
            StringBuffer md5StrBuff = new StringBuffer();
            for(int i=0;i<hmacMd5Byte.length;i++)
            {
                if(Integer.toHexString(0xFF&hmacMd5Byte[i]).length()==1){

                    md5StrBuff.append("0").append(Integer.toHexString(0xFF&hmacMd5Byte[i]));
                }else{
                    md5StrBuff.append(Integer.toHexString(0xFF&hmacMd5Byte[i]));
                }
            }
            result = md5StrBuff.toString().toUpperCase();

        }
        catch(Exception e)
        {

        }
        return result;

    }
}