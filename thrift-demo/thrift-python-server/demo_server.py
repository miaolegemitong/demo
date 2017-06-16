from gen import DemoSerivce
from gen.ttypes import DemoObject

from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
from thrift.server import TServer


class DemoServer:
    def __init__(self):
        self.log = {}

    def ping(self, param):
        return "Got ping request: " + param

    def getObject(self, x, y):
        return DemoObject(x, y)

if __name__ == '__main__':
    handler = DemoServer()
    processor = DemoSerivce.Processor(handler)
    transport = TSocket.TServerSocket(port=10000)
    tfactory = TTransport.TBufferedTransportFactory()
    pfactory = TBinaryProtocol.TBinaryProtocolFactory()

    server = TServer.TThreadPoolServer(processor, transport, tfactory, pfactory)
    server.serve()
