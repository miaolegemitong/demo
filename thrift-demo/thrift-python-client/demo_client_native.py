from gen import DemoSerivce
from gen.ttypes import DemoObject

from thrift import Thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol

if __name__ == '__main__':
    # Make socket
    transport = TSocket.TSocket('127.0.0.1', 10000)

    # Buffering is critical. Raw sockets are very slow
    transport = TTransport.TBufferedTransport(transport)

    # Wrap in a protocol
    protocol = TBinaryProtocol.TBinaryProtocol(transport)

    # Create a client to use the protocol encoder
    client = DemoSerivce.Client(protocol)

    # Connect!
    transport.open()

    ping = client.ping("test")
    print ping

    demo_obj = client.getObject("xxx", 2)
    print '%s\t%d' % (demo_obj.x, demo_obj.y)

    # Close!
    transport.close()