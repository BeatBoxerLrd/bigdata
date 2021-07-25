package com.derry.hadoop.rpc;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

/**
 * @Author:derry
 * @Description:
 * @Date: Created in 15:25 2021/7/25
 * @Modified By:
 */
@Slf4j
public class HadoopRpcServer {
    public static void main(String[] args) throws IOException {
        String addr = "127.0.0.1";
        int port = 12345;
        RPC.Builder build = new RPC.Builder(new Configuration());
        if (args != null && args.length > 0 && args[0] != null) {
            addr = args[0];
        }
        if (args != null && args.length > 0 && args[1] != null) {
            port = Integer.parseInt(args[1]);
        }
        build.setBindAddress(addr).setPort(port)
                .setProtocol(HadoopProtocol.class)
                .setInstance(new HadoopProtocolServer());
        RPC.Server server = build.build();
        log.debug("Server start to listen on addr-{}:{}", addr, port);
        server.start();
    }
}
