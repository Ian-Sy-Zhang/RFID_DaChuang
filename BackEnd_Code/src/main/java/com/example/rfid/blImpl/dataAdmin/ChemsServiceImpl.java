package com.example.rfid.blImpl.dataAdmin;

import com.example.rfid.bl.dataAdmin.ChemsService;
import com.example.rfid.vo.ChemcasVO;
import com.example.rfid.vo.ResponseVO;
import org.springframework.stereotype.Service;

@Service
public class ChemsServiceImpl implements ChemsService {
    @Override
    public ResponseVO addChem(ChemcasVO chemVO) {
        return null;
    }

    @Override
    public ResponseVO removeChem(int chemId) {
        return null;
    }

    @Override
    public ResponseVO updateChem(ChemcasVO chemVO) {
        return null;
    }

    @Override
    public ResponseVO searchOneChem(int chemId) {
        return null;
    }

    @Override
    public ResponseVO searchChemList() {
        return null;
    }
}
