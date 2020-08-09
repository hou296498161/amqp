package com.ali.amqp.publish;

import com.ali.amqp.subscribe.AmqpJavaClient;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.iot.model.v20180120.PubRequest;
import com.aliyuncs.iot.model.v20180120.PubResponse;
import com.aliyuncs.profile.DefaultProfile;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

/**
 * 云端下发消息
 */
public class PopPubServer {
    private final static Logger logger = LoggerFactory.getLogger(PopPubServer.class);


    public static boolean sendToTopic(String topic) throws UnsupportedEncodingException {
        String regionId = "cn-shanghai";
        String accessKey = "你的accessKey";
        String accessSecret = "你的accessSecret";
        final String productKey = "设备productKey";
        //设置client的参数
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKey, accessSecret);
        IAcsClient autoClient = new DefaultAcsClient(profile);
        PubRequest request = new PubRequest();
        request.setQos(0);
        //设置发布消息的topic
        request.setTopicFullName("/a14UPaWxJCF/test_device/user/test");
        request.setProductKey(productKey);
        //设置消息的内容，一定要用base64编码，否则乱码
        Base64 base64 = new Base64();
        request.setMessageContent(base64.encodeToString("hello".getBytes("UTF-8")));
        try {
            PubResponse response = autoClient.getAcsResponse(request);
            Boolean success = response.getSuccess();
            return success;
        } catch (Exception e) {
           logger.warn("阿里云消息发送异常，topic:{},异常信息：{}",topic,e.getMessage());
           return false;
        }


    }

}
