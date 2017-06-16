struct DemoObject {
    1: string x
    2: i32 y
}

service DemoSerivce {
    string ping(1: string param)
    DemoObject getObject(1: string x, 2: i32 y)
}
