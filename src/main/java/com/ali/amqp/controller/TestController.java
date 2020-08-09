package com.ali.amqp.controller;

import com.ali.amqp.publish.PopPubServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * @author beauhou
 * @version 1.0
 * @date 2020/8/9 0009 21:52
 */
@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping("/send")
    public String sendTest () throws UnsupportedEncodingException {
        boolean result = PopPubServer.sendToTopic("/a14UPaWxJCF/test_device/user/test");
        if (result){
            return "success";
        }else {
            return "fail";
        }
    }
}
