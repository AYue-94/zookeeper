package org.apache.zookeeper.test;

import org.apache.jute.Record;
import org.apache.zookeeper.ZKTestCase;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.common.Time;
import org.apache.zookeeper.server.persistence.FileTxnLog;
import org.apache.zookeeper.txn.CreateTxn;
import org.apache.zookeeper.txn.TxnHeader;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by 2023/1/31.
 */
public class MyFileTxnLogTest extends ZKTestCase {
    protected static final Logger LOG = LoggerFactory.getLogger(MyFileTxnLogTest.class);
    @Test
    public void testFileTxnLogFlush0() throws Exception {
        File tmpDir = new File("/tmp");
        FileTxnLog txnLog = new FileTxnLog(tmpDir);
        TxnHeader txnHeader = new TxnHeader(0xabcd, 0x123, 0x123,
                Time.currentElapsedTime(), ZooDefs.OpCode.create);
        Record txn = new CreateTxn("/Test222", new byte[0], null, false, 1);
        txnLog.append(txnHeader, txn);
        txnLog.close();
    }


    @Test
    public void testFileTxnLogFlush1() throws Exception {
        File tmpDir = new File("/tmp");
        FileTxnLog txnLog = new FileTxnLog(tmpDir);
        TxnHeader txnHeader = new TxnHeader(0xabcd, 0x123, 0x123,
                Time.currentElapsedTime(), ZooDefs.OpCode.create);
        Record txn = new CreateTxn("/Test223", new byte[0], null, false, 1);
        txnLog.append(txnHeader, txn);
        txnLog.commit();
    }
}
