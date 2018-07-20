package shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mapper.CellphoneMapper;
import shop.model.Cellphone;
import shop.service.CellphoneService;

@Service
public class CellphoneServiceImpl implements CellphoneService {
    private CellphoneMapper cellphoneMapper;

    @Autowired
    public CellphoneServiceImpl(CellphoneMapper cellphoneMapper) {
        this.cellphoneMapper = cellphoneMapper;
    }

    @Override
    public List<Cellphone> search(Cellphone cellphone) {
        return cellphoneMapper.search(cellphone);
    }

    @Override
    public Cellphone findOne(Long id) {
        return cellphoneMapper.findOne(id);
    }
}
