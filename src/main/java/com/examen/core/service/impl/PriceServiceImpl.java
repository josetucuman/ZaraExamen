package com.examen.core.service.impl;

import com.examen.core.model.Price;
import com.examen.core.repository.PriceRepository;
import com.examen.core.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class PriceServiceImpl implements PriceService {
    private final PriceRepository priceRepository;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public List<Price> getPricesByDateAndProductAndBrand(Date startDate, Date endDate, Long productId, Long brandId) {
        return priceRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
                startDate, endDate, productId, brandId);
    }
}
