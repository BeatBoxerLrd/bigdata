package com.derry.hadoop.rpc;

import com.derry.hadoop.rpc.utils.ConstantsUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @Author:derry
 * @Description:
 * @Date: Created in 15:35 2021/7/25
 * @Modified By:
 */
public class HadoopRpcClient {
    public HadoopProtocol getProxy() throws IOException {
        String addr = "127.0.0.1";
        int port = 12345;
        HadoopProtocol proxy = RPC.getProxy(HadoopProtocol.class, 1L, new InetSocketAddress(addr, port), new Configuration());
        return proxy;
    }

    @Test
    public void testFindNameByStudentNumber() throws IOException {
        String name = getProxy().findNameByStudentNumber(ConstantsUtils.MY_NUMBER);
        Assert.assertEquals(name, ConstantsUtils.MY_NAME);
        String name1 = getProxy().findNameByStudentNumber("ddadaddad");
        Assert.assertEquals(name1, null);
    }

    public static void main(String[] args) throws IOException {
        String addr = "127.0.0.1";
        int port = 12345;
        if (args != null && args.length > 0 && args[0] != null) {
            addr = args[0];
        }
        if (args != null && args.length > 0 && args[1] != null) {
            port = Integer.parseInt(args[1]);
        }
        HadoopProtocol proxy = RPC.getProxy(HadoopProtocol.class, 1L, new InetSocketAddress(addr, port), new Configuration());
        String name = proxy.findNameByStudentNumber(ConstantsUtils.MY_NUMBER);
        System.out.println(name);
    }
}
