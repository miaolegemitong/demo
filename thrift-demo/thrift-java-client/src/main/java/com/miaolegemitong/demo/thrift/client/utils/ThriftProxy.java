package com.miaolegemitong.demo.thrift.client.utils;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ThriftProxy<T> implements InvocationHandler {
    private Class<T> ts = null;
    private ThriftConfig config = null;
    private TSocket transport = null;
    private T client = null;

    public ThriftProxy(Class<T> ts) {
        this(ts, new ThriftConfig());
    }

    public ThriftProxy(Class<T> ts, ThriftConfig config) {
        this.ts = ts;
        this.config = config;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TSocket tsocket = null;
        int left = config.getRetry();
        while (true) {
            try {
                if (this.transport == null) {
                    int timeout = config.getTimeout();
                    String host = config.getHost();
                    int port = config.getPort();

                    TSocket socket = new TSocket(host, port);
                    socket.setTimeout(timeout);
                    socket.open();
                    this.transport = socket;

                    TProtocol protocol = new TBinaryProtocol(transport);
                    if (this.config.getName() != null) {
                        TMultiplexedProtocol mp = new TMultiplexedProtocol(protocol, this.config.getName());
                        Constructor<T> cons = (Constructor<T>) ts.getConstructor(new Class[]{TProtocol.class});
                        this.client = (T) cons.newInstance(mp);
                    } else {
                        Constructor<T> cons = (Constructor<T>) ts.getConstructor(new Class[]{TProtocol.class});
                        this.client = (T) cons.newInstance(protocol);
                    }
                }
                return method.invoke(this.client, args);
            } catch (TTransportException e) {
                //出错断开
                if (this.transport != null) {
                    this.transport.close();
                    this.transport = null;
                }
                //重试
                if (left > 0) {
                    left -= 1;
                    continue;
                }
                //超过次数退出
                throw e;
            } catch (Exception e) {
                throw e;
            }
        }

    }
}