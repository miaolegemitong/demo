package com.miaolegemitong.demo.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.miaolegemitong.demo.springboot.service.DemoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by miaolegemitong on 2017/6/16.
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
    @Resource
    private DemoService demoService;

    @RequestMapping("/add")
    public JSONObject addRecord(@RequestParam String name, @RequestParam String password) {
        return demoService.addRecord(name, password);
    }

    @RequestMapping("/modify")
    public JSONObject modifyRecord(@RequestParam int id, @RequestParam(required = false) String name, @RequestParam(required = false) String password) {
        return demoService.modifyRecord(id, name, password);
    }

    @RequestMapping("/delete")
    public JSONObject deleteRecord(@RequestParam int id) {
        return demoService.deleteRecord(id);
    }

    @RequestMapping("/all")
    public JSONObject getAllRecords() {
        return demoService.getAllRecords();
    }

    @RequestMapping("/get")
    public JSONObject getOneRecord(@RequestParam int id) {
        return demoService.getOneRecord(id);
    }
}
