/******************************************************************
 * Copyright 2018 Abstract Software Ltd. All rights reserved.
 *
 * No part of this code may be reproduced, distributed or ported
 * to another programming language without the express written
 * consent of Abstract Software Ltd.
 ******************************************************************/

package me.cjmcrae.reportingengine.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.cjmcrae.reportingengine.model.Currency;
import me.cjmcrae.reportingengine.service.CurrencyService;

/**
 *
 * Author: Colin J McRae
 * Date:   04-Jun-2018
 *
 */
public class CurrencyServiceImpl implements CurrencyService {
    
    private Map<String, Currency> currencies;
    
    public CurrencyServiceImpl() {
        loadDefaultCurrencies();
    }
    
    private void loadDefaultCurrencies() {
        currencies = new HashMap<>();
        currencies.put("USD", new Currency("USD", "US Dollar"));
        currencies.put("GBP", new Currency("GBP", "Pound Sterling"));
        currencies.put("EUR", new Currency("EUR", "Euro"));
        currencies.put("AUD", new Currency("AUD", "Australian Dollar"));
        currencies.put("AED", new Currency("AED", "UAE Dirham"));
        currencies.put("SGD", new Currency("SGD", "Singapore Dollar"));
        currencies.put("INR", new Currency("INR", "Indian Rupee"));
        currencies.put("SAR", new Currency("SAR", "Saudi Riyal"));
    }
    
    @Override
    public List<Currency> getAllowableCurrencies() {
        return new ArrayList(currencies.values());
    }
    
    @Override
    public boolean isAllowable(String currencyCode) {
        return currencies.containsKey(currencyCode.toUpperCase());
    }
}
