package com.test.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import com.test.entity.User;
import com.test.entity.UserBorrowDetail;
import com.test.service.BorrowService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;

@RestController
public class BorrowController {

    @Resource
    BorrowService service;

    @RequestMapping("/borrow/{uid}")
    UserBorrowDetail findUserBorrows(@PathVariable("uid") int uid){
        return service.getUserBorrowDetailByUid(uid);
    }

//    @RequestMapping("/borrow2/{uid}")
//    UserBorrowDetail findUserBorrows2(@PathVariable("uid") int uid){
//        return service.getUserBorrowDetailByUid(uid);
//    }

    @RequestMapping("/blocked")
    JSONObject blocked(){
        JSONObject object = new JSONObject();
        object.put("code", 403);
        object.put("success", false);
        object.put("massage", "您的请求频率过快，请稍后再试！");
        return object;
    }



//    @RequestMapping("/test")
//    @SentinelResource(value = "test",
//            fallback = "except",    //fallback指定出现异常时的替代方案
//            exceptionsToIgnore = IOException.class)  //忽略那些异常，也就是说这些异常出现时不使用替代方案
//    String test(){
//        throw new RuntimeException("HelloWorld！");
//    }
//
//    //替代方法必须和原方法返回值和参数一致，最后可以添加一个Throwable作为参数接受异常
//    String except(Throwable t){
//        return t.getMessage();
//    }



    @RequestMapping("/test")
    @SentinelResource("test")   //注意这里需要添加@SentinelResource才可以，用户资源名称就使用这里定义的资源名称
    String findUserBorrows2(@RequestParam(value = "a", required = false) String a,
                            @RequestParam(value = "b", required = false) String b,
                            @RequestParam(value = "c",required = false) String c) {
        return "请求成功！a = "+a+", b = "+b+", c = "+c;
    }

//    /**
//     * Sentinel熔断策略：
//     * 慢调用比例
//     * @param uid
//     * @return
//     * @throws InterruptedException
//     */
//    @RequestMapping("/borrow2/{uid}")
//    String findUserBorrows2(@PathVariable("uid") int uid) throws InterruptedException {
//        Thread.sleep(1000);
//        return "Hellow World";
//    }

//    /**
//     * Sentinel熔断策略:
//     * 异常比例
//     * @param uid
//     * @return
//     */
//    @RequestMapping("/borrow2/{uid}")
//    UserBorrowDetail findUserBorrows2(@PathVariable("uid") int uid) {
//        throw new RuntimeException();
//    }


    @RequestMapping("/borrow2/{uid}")
    @SentinelResource(value = "findUserBorrows2", blockHandler = "test")
    UserBorrowDetail findUserBorrows2(@PathVariable("uid") int uid) {
        throw new RuntimeException();
    }

    UserBorrowDetail test(int uid, BlockException e){
        return new UserBorrowDetail(new User(), Collections.emptyList());
    }
}
