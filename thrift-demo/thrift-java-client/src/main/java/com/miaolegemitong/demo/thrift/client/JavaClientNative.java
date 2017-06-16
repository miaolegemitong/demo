package com.miaolegemitong.demo.thrift.client;

import com.miaolegemitong.demo.thrift.client.autogenerated.DemoObject;
import com.miaolegemitong.demo.thrift.client.autogenerated.DemoSerivce;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @author miaolegemitong
 * @email mitong1993@gmail.com
 * @date 2016/12/12
 * @description 原生方法调用服务
 */
public class JavaClientNative {
    public static void main(String[] args) {
        TTransport transport;
        try {
            //修改服务器和端口
            transport = new TSocket("127.0.0.1", 10000);
            TProtocol protocol = new TBinaryProtocol(transport);
            DemoSerivce.Client client = new DemoSerivce.Client(protocol);
            transport.open();
            System.out.println(client.ping("test"));
            DemoObject object = client.getObject("xxx", 2);
            System.out.println(object.getX() + "\t" + object.getY());
            transport.close();
        } catch (TException e) {
            e.printStackTrace();
        }
    }
}
