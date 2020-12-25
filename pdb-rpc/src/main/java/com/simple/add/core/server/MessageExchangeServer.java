package com.simple.add.core.server;

import com.simple.add.core.thrift.MessageExchangeService;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : Zhuang Jialong
 * @description :
 * @date : 2020/12/25 下午 2:34
 * @Copyright: Copyright(c)2020 RedaFlight.com All Rights Reserved
 */
public class MessageExchangeServer implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MessageExchangeServer.class);
    /**
     * 服务绑定的端口
     */
    private int port;
    /**
     * 服务具体的实现
     */
    private MessageExchangeService.Iface service;

    public MessageExchangeServer(MessageExchangeService.Iface service, int port) {
        this.service = service;
        this.port = port;
    }

    @Override
    public void run() {
        TNonblockingServerSocket socket = null;
        try {
            socket = new TNonblockingServerSocket(port);
        } catch (TTransportException e) {
            throw new RuntimeException("======>port:" + port + "has been in use!" + e.getMessage(), e);
        }
        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        MessageExchangeService.Processor<MessageExchangeService.Iface> processor = new MessageExchangeService.Processor<>(service);
        arg.protocolFactory(new TCompactProtocol.Factory());
        arg.transportFactory(new TFramedTransport.Factory());
        arg.processorFactory(new TProcessorFactory(processor));
        //构建服务
        TServer server = new THsHaServer(arg);
        logger.info("Thrift Server Started! Using port :" + port);
        server.serve();
    }

}
