#### 定义thrfit文件

- 定义简单的结构体DemoObject
```
struct DemoObject {
    1: string x
    2: i32 y
}
```
- 定义服务端口DemoService
```
service DemoSerivce {
    string ping(1: string param)
    DemoObject getObject(1: string x, 2: i32 y)
}
```
DemoService类似于Java中的接口，其中有两个方法，ping函数输入一个字符串，返回字符串，getObject函数输入一个字符串和一个整数，返回一个DemoObject对象。

#### 生成接口代码

```
thrift --gen java demo.thrift
thrift --gen py demo.thrift
```

#### 部署服务端

- thrift-java-server、thrift-python-server分别为Java和Python版的服务端代码示例。
- 将thrift生成的代码拷贝进工程。
- 实现thrift文件定义的服务接口。
- 并编写启动服务的main函数，修改端口号，启动即可。

#### 客户端代码实现

- thrift-java-client、thrift-python-client分别为Java和Python版的客户端代码示例。
- 将thrift生成的代码拷贝进工程。
- 编写客户端代码，修改对应的服务端IP和端口，即可跨语言跨机器进行RPC调用。
