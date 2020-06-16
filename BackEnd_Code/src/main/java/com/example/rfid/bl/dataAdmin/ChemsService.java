package com.example.rfid.bl.dataAdmin;

import com.example.rfid.vo.BatchVO;
import com.example.rfid.vo.ChemcasVO;
import com.example.rfid.vo.ResponseVO;

public interface ChemsService {
    // chemcas表的管理工作，增删改查方法

    // 增加chem
    public ResponseVO addChem(ChemcasVO chemVO);

    // 删除chem
    public ResponseVO removeChem(int chemId);

    // 修改chem
    public ResponseVO updateChem(ChemcasVO chemVO);

    // 查询一条chem信息
    public ResponseVO searchOneChem(int chemId);

    // 查询所有chem信息
    public ResponseVO searchChemList();
}
