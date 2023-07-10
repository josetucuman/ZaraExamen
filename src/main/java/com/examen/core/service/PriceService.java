package com.examen.core.service;

import com.examen.core.model.Price;

import java.util.Date;
import java.util.List;

public interface PriceService {
    List<Price> getPricesByDateAndProductAndBrand(Date startDate, Date endDate, Long productId, Long brandId);
}
