package com.miaolegemitong.demo.springboot.service;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by miaolegemitong on 2017/6/16.
 */
public interface DemoService {
    JSONObject addRecord(String name, String password);

    JSONObject modifyRecord(int id, String name, String password);

    JSONObject deleteRecord(int id);

    JSONObject getAllRecords();

    JSONObject getOneRecord(int id);
}
