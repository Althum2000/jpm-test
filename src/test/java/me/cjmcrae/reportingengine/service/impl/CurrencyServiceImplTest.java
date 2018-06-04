/*
 * Copyright 2018 Abstract Software Ltd. All rights reserved.
 *
 * No part of this code may be reproduced, distributed or ported
 * to another programming language without the express written
 * consent of Abstract Software Ltd.
 */
package me.cjmcrae.reportingengine.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import me.cjmcrae.reportingengine.model.Currency;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author c_j_m
 */
public class CurrencyServiceImplTest {
    
    public CurrencyServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllowableCurrencies method, of class CurrencyServiceImpl.
     */
    @Test
    public void test_getAllowableCurrencies_expect_success() {
        System.out.println("getAllowableCurrencies");
        
        //1.0. create list of expected results from service
        List<Currency> expResult = new ArrayList<>();
        expResult.add(new Currency("USD", "US Dollar"));
        expResult.add(new Currency("GBP", "Pound Sterling"));
        expResult.add(new Currency("EUR", "Euro"));
        expResult.add(new Currency("AUD", "Australian Dollar"));
        expResult.add(new Currency("AED", "UAE Dirham"));
        expResult.add(new Currency("SGD", "Singapore Dollar"));
        expResult.add(new Currency("INR", "Indian Rupee"));
        expResult.add(new Currency("SAR", "Saudi Riyal"));
        
        //2.0. create instance of service implementation and invoke method
        CurrencyServiceImpl instance = new CurrencyServiceImpl();
        List<Currency> result = instance.getAllowableCurrencies();
        
        //3.0. sort the collections for bulk comparison
        Collections.sort(expResult, (Currency c1, Currency c2) -> c1.getCode().compareTo(c2.getCode()));
        Collections.sort(result, (Currency c1, Currency c2) -> c1.getCode().compareTo(c2.getCode()));
        
        //4.0. assert that the contents of both currency lists are equal, then test fields of first one
        assertEquals("Returned list is not as expected, ", expResult, result);
        assertEquals("Code field is not as expected, ", expResult.get(0).getCode(), result.get(0).getCode());
        assertEquals("Name field is not as expected, ", expResult.get(0).getName(), result.get(0).getName());
        
    }

    /**
     * Test of isAllowable method, of class CurrencyServiceImpl.
     */
    @Test
    public void test_isAllowable_and_expect_fail() {
        System.out.println("isAllowable_and_expect_fail");
        
        //1.0. a bogus currency code not in the system
        String currencyCode = "BOG";
        
        //2.0. create instance and execute
        CurrencyServiceImpl instance = new CurrencyServiceImpl();
        boolean result = instance.isAllowable(currencyCode);
        
        //3.0. make assertion
        assertFalse("Service returns true when it should be false", result);
    }
    
    /**
     * Test of isAllowable method, of class CurrencyServiceImpl.
     */
    @Test
    public void test_isAllowable_and_expect_success() {
        System.out.println("isAllowable_and_expect_success");
        
        //1.0. a bogus currency code not in the system
        String currencyCode = "AUD";
        
        //2.0. create instance and execute
        CurrencyServiceImpl instance = new CurrencyServiceImpl();
        boolean result = instance.isAllowable(currencyCode);
        
        //3.0. make assertion
        assertTrue("Service returns true when it should be false", result);
    }
    
}
