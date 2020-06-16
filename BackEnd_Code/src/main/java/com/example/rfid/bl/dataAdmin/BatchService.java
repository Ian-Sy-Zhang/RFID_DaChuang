package com.example.rfid.bl.dataAdmin;

import com.example.rfid.vo.BatchVO;
import com.example.rfid.vo.ResponseVO;

public interface BatchService {
    // batch表的管理工作，增删改查方法

    // 增加batch
    public ResponseVO addBatch(BatchVO batchVO);

    // 删除batch
    public ResponseVO removeBatch(int batchId);

    // 修改batch
    public ResponseVO updateBatch(BatchVO batchVO);

    // 查询一条batch信息
    public ResponseVO searchOneBatch(int batchId);

    // 查询所有batch信息
    public ResponseVO searchBatchList();
}
