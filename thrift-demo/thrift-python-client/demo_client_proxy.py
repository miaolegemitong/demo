# -*-coding:utf-8-*-

from utils.thriftclient import ThriftProxy
from gen import DemoSerivce

if __name__ == '__main__':
    # 配置
    config = {'host': '127.0.0.1', 'port': 10000}
    # 创建代理对象
    proxy = ThriftProxy(DemoSerivce.Client, config)
    # 调用方法
    print proxy.ping('python')

    demo_obj = proxy.getObject('xxx', 2)
    print '%s\t%d' % (demo_obj.x, demo_obj.y)