package cn.cdy.crowd.util;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CrowdUtil {
    //判断当前请求类型是否ajax
    public static boolean getrequestType(HttpServletRequest request){
        String Acceptheader = request.getHeader("Accept");
        String xRequestheader = request.getHeader("X-Requested-With");
        if((Acceptheader != null && Acceptheader.contains("application/json"))
                || (xRequestheader != null && xRequestheader.equals("XMLHttpRequest"))
                ){
            return true;
        }
        return false;
    }
    //md5加密
    public static String md5(String source){
        if(source == null || source.length() == 0){
            throw new RuntimeException(CrowdConstant.STRING_INVALIDATE);
        }
        String algorithm = "md5";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            byte[] input = source.getBytes();
            byte[] output = messageDigest.digest(input);
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum,output);
            int radix = 16;
            String encoded = bigInteger.toString(radix);
            return encoded;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
