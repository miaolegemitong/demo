package com.miaolegemitong.demo.springboot.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.miaolegemitong.demo.springboot.dao.DemoPOMapper;
import com.miaolegemitong.demo.springboot.model.DemoPO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by miaolegemitong on 2017/6/16.
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Resource
    private DemoPOMapper demoMapper;

    @Override
    public JSONObject addRecord(String name, String password) {
        JSONObject result = new JSONObject();
        if (name == null || name.equals("") || name.length() > 100 ||
                password == null || password.equals("") || password.length() > 100) {
            result.put("status", 1);
            result.put("message", "Parameters are not valid!");
        } else {
            DemoPO demoPO = new DemoPO();
            demoPO.setName(name);
            demoPO.setPassword(password);
            int count = demoMapper.insert(demoPO);
            result.put("status", count == 1 ? 0 : 2);
            result.put("message", count == 1 ? "success" : "Insertion failed!");
        }
        return result;
    }

    @Override
    public JSONObject modifyRecord(int id, String name, String password) {
        JSONObject result = new JSONObject();
        if (name == null && password == null) {
            result.put("status", "1");
            result.put("message", "Parameters are not valid!");
        } else {
            DemoPO demoPO = new DemoPO();
            demoPO.setId(id);
            demoPO.setName(name);
            demoPO.setPassword(password);
            int count = demoMapper.updateByPrimaryKeySelective(demoPO);
            result.put("status", count == 1 ? 0 : 2);
            result.put("message", count == 1 ? "success" : "Record not exists!");
        }
        return result;
    }

    @Override
    public JSONObject deleteRecord(int id) {
        JSONObject result = new JSONObject();
        int count = demoMapper.deleteByPrimaryKey(id);
        result.put("status", count == 1 ? 0 : 2);
        result.put("message", count == 1 ? "success" : "Record not exists!");
        return result;
    }

    @Override
    public JSONObject getAllRecords() {
        JSONObject result = new JSONObject();
        List<DemoPO> list = demoMapper.selectByExample(null);
        result.put("status", 0);
        result.put("message", "success");
        JSONArray array = new JSONArray();
        array.addAll(list);
        result.put("data", array);
        return result;
    }

    @Override
    public JSONObject getOneRecord(int id) {
        DemoPO demoPO = demoMapper.selectByPrimaryKey(id);
        JSONObject result = new JSONObject();
        result.put("status", demoPO == null ? 2 : 0);
        result.put("message", demoPO == null ? "Record not exists!" : "success");
        result.put("data", demoPO);
        return result;
    }
}
