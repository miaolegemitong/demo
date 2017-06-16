package com.miaolegemitong.demo.thrift.client.utils;

import java.lang.reflect.Proxy;

public class ThriftProxyFactory {
	
	public static Object createProxy(Class<?> ts, ThriftConfig config) {

		ThriftProxy proxy = new ThriftProxy(ts, config);
		
		return Proxy.newProxyInstance(ts.getClassLoader(), ts.getInterfaces(), proxy);
	} 
}
