package com.zookeeper.controller;

import com.zookeeper.service.ZKservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2019-02-01 17:23
 **/

@RestController
public class ZKcontroller {


    @Autowired private ZKservice zKservice;

    @RequestMapping(method = RequestMethod.POST,value = "/zklock")
    public String ZKLock(){
        zKservice.operateZKlock();
        return "success";
    }
}
