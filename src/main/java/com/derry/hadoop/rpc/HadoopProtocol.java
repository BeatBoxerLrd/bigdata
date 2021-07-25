package com.derry.hadoop.rpc;

import org.apache.hadoop.ipc.VersionedProtocol;

/**
 * @Author:derry
 * @Description:
 * @Date: Created in 15:18 2021/7/25
 * @Modified By:
 */
public interface HadoopProtocol extends VersionedProtocol {
    long versionID = 1L;
    String findNameByStudentNumber(String number);
}
