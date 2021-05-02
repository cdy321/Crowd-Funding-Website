package cn.cdy.crowd;

import cn.cdy.crowd.api.RedisRemoteService;
import cn.cdy.crowd.util.CrowdUtil;
import cn.cdy.crowd.util.ResultEntity;
import com.aliyun.api.gateway.demo.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootApplication
public class AuthenticationConsumerTest {
    @Autowired
    private RedisRemoteService redisRemoteService;
    @Test
    public void test01(){
        String host = "https://smssend.shumaidata.com";
        String path = "/sms/send";
        String method = "POST";
        String appcode = "cb74ba74cd4e4c6fb6c2dcea9deb0bd2";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("receive", "17760474359");
        querys.put("tag", "654321");
        querys.put("templateId", "M09DD535F4");
        Map<String, String> bodys = new HashMap<String, String>();

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
