package com.derry.hadoop.rpc;

import com.derry.hadoop.rpc.utils.ConstantsUtils;
import org.apache.hadoop.ipc.ProtocolSignature;

import java.io.IOException;

/**
 * @Author:derry
 * @Description:
 * @Date: Created in 15:21 2021/7/25
 * @Modified By:
 */
public class HadoopProtocolServer implements HadoopProtocol {

    @Override
    public long getProtocolVersion(String s, long l) throws IOException {
        return HadoopProtocol.versionID;
    }

    @Override
    public ProtocolSignature getProtocolSignature(String s, long l, int i) throws IOException {
        return null;
    }

    @Override
    public String findNameByStudentNumber(String number) {
        if(ConstantsUtils.MY_NUMBER.equals(number)) {
            return ConstantsUtils.MY_NAME;
        }
        return null;
    }
}
