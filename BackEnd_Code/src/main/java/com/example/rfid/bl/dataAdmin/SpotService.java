package com.example.rfid.bl.dataAdmin;

import com.example.rfid.vo.BatchVO;
import com.example.rfid.vo.ResponseVO;
import com.example.rfid.vo.SpotVO;

public interface SpotService {
    // spot表的管理工作，增删改查方法

    // 增加Spot
    public ResponseVO addSpot(SpotVO spotVO);

    // 删除Spot
    public ResponseVO removeSpot(int SpotId);

    // 修改Spot
    public ResponseVO updateSpot(SpotVO spotVO);

    // 查询一条Spot信息
    public ResponseVO searchOneSpot(int SpotId);

    // 查询所有Spot信息
    public ResponseVO searchSpotList();
}
