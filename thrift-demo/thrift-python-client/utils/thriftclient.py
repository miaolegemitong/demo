from thrift.protocol import TBinaryProtocol
from thrift.protocol import TMultiplexedProtocol
from thrift.transport import TSocket
from thrift.transport import TTransport


class ThriftProxy(object):
    def __init__(self, cs, config):
        self.cs = cs
        self.client = None
        self.transport = None
        self.config = config
        super(ThriftProxy, self).__init__()

    def get_config(self, name, value):
        if name not in self.config:
            return value
        return self.config[name]

    def __getattr__(self, attribute):

        left = self.get_config('retry', 3)

        while True:
            try:
                if not self.transport:
                    host = self.get_config('host', '127.0.0.1')
                    port = self.get_config('port', 1235)
                    timeout = self.get_config('timeout', 10)
                    name = self.get_config('name', '')
                    transport = TSocket.TSocket(host, port)
                    transport.timeout = timeout
                    transport.open()
                    self.transport = transport

                    protocol = TBinaryProtocol.TBinaryProtocol(transport)
                    if name:
                        multi_protocal = TMultiplexedProtocol.TMultiplexedProtocol(protocol, name)
                        self.client = self.cs(multi_protocal)
                    else:
                        self.client = self.cs(protocol)
                return getattr(self.client, attribute)
            except TTransport.TTransportException, e:
                print '11'
                if self.transport:
                    self.transport.close()
                    self.transport = None
                if left > 0:
                    left -= 1
                    print left
                    continue
                raise e
            except Exception, e:
                raise e
