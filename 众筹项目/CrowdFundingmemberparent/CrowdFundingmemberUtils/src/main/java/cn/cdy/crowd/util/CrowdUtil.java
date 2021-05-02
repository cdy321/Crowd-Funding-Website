package cn.cdy.crowd.util;

import com.aliyun.gateway.demo.util.HttpUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CrowdUtil {
    public static ResultEntity<String> sendShortMessage(
            String host,
            String path,
            String method,
            String phoneNum,
            String appcode,
            String templateId){
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int random = (int)(Math.random() * 10);
            builder.append(random);
        }
        String code = builder.toString();
        querys.put("receive", phoneNum);
        querys.put("tag", code);
        querys.put("templateId", templateId);
        Map<String, String> bodys = new HashMap<String, String>();
        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            String reasonPhrase = statusLine.getReasonPhrase();
            if(statusCode == 200) {
                return ResultEntity.successWithData(code);
            }
            return ResultEntity.fail(reasonPhrase);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.fail(e.getMessage());
        }
    }

    public static ResultEntity<String> uploadFileToOSS(String endPoint,
                                                       String accessKeyId,
                                                       String accessKeySecret,
                                                       InputStream inputStream,
                                                       String bucketName,
                                                       String bucketDomain,
                                                       String originalName){

        // 创建 OSSClient 实例。
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
           // 生成上传文件的目录
        String folderName = new SimpleDateFormat("yyyyMMdd").format(new Date());
             // 生成上传文件在 OSS 服务器上保存时的文件名
             // 原始文件名：beautfulgirl.jpg
            // 生成文件名：wer234234efwer235346457dfswet346235.jpg
          // 使用 UUID 生成文件主体名称
        String fileMainName = UUID.randomUUID().toString().replace("-", "");
         // 从原始文件名中获取文件扩展名
        String extensionName = originalName.substring(originalName.lastIndexOf("."));
         // 使用目录、文件主体名称、文件扩展名称拼接得到对象名称
        String objectName = folderName + "/" + fileMainName + extensionName;
        try {
            // 调用 OSS 客户端对象的方法上传文件并获取响应结果数据
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectName, inputStream);
          // 从响应结果中获取具体响应消息
            ResponseMessage responseMessage = putObjectResult.getResponse();
              // 根据响应状态码判断请求是否成功
            if(responseMessage == null) {
                   // 拼接访问刚刚上传的文件的路径
                String ossFileAccessPath = bucketDomain + "/" + objectName;
                  // 当前方法返回成功
                return ResultEntity.successWithData(ossFileAccessPath);
            } else {
                 // 获取响应状态码
                int statusCode = responseMessage.getStatusCode();// 如果请求没有成功，获取错误消息
                String errorMessage = responseMessage.getErrorResponseAsString();
                  // 当前方法返回失败
                return ResultEntity.fail(" 当 前 响 应 状 态 码 ="+statusCode+" 错 误 消 息 ="+errorMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
                 // 当前方法返回失败
            return ResultEntity.fail(e.getMessage());
        } finally {
            if(ossClient != null) {
               // 关闭 OSSClient。
                ossClient.shutdown();
            }
        }
    }

    public static void main(String[] args)throws Exception {
        String endPoint = "http://oss-cn-beijing.aliyuncs.com";
        String accessKeyId = "LTAI5tN34mfYpxvTdgAjWbjD";
        String accessKeySecret = "mvlyCSyMxfyqvkMXJA9XKXaW9iDbAH";
        String bucketDomain = "http://cncdy123456.oss-cn-beijing.aliyuncs.com";
        String bucketName = "cncdy123456";
        String originalName = "3.jpg";
        InputStream is = new FileInputStream("C:/Users/程东耀/Pictures/3.jpg");
        ResultEntity<String> res = uploadFileToOSS(endPoint, accessKeyId, accessKeySecret, is, bucketName, bucketDomain, originalName);
        System.out.println(res);
    }
}
