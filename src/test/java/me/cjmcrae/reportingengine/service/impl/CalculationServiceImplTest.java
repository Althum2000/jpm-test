/*
 * Copyright 2018 Abstract Software Ltd. All rights reserved.
 *
 * No part of this code may be reproduced, distributed or ported
 * to another programming language without the express written
 * consent of Abstract Software Ltd.
 */
package me.cjmcrae.reportingengine.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import me.cjmcrae.reportingengine.model.Currency;
import me.cjmcrae.reportingengine.model.DailySettlementRecord;
import me.cjmcrae.reportingengine.model.EntityRankingRecord;
import me.cjmcrae.reportingengine.model.Instruction;
import me.cjmcrae.reportingengine.model.InstructionType;
import me.cjmcrae.reportingengine.model.ProcessedInstruction;
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
public class CalculationServiceImplTest {
    
    public CalculationServiceImplTest() {
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
     * Test of calculateTradeTotalValue method, of class CalculationServiceImpl.
     */
    @Test
    public void test_calculateTradeTotalValue() {
        System.out.println("calculateTradeTotalValue");
        
        //set parameters
        BigDecimal pricePerUnit = new BigDecimal(100.25);
        BigDecimal units = new BigDecimal(200);
        BigDecimal agreedFx = new BigDecimal(0.5);
        BigDecimal expResult = new BigDecimal(10025);
        
        //create instance and execute
        CalculationServiceImpl instance = new CalculationServiceImpl();
        
        //assert that calculation is correct
        BigDecimal result = instance.calculateTradeTotalValue(pricePerUnit, units, agreedFx);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of calculateRevisedSettlementDate method, of class CalculationServiceImpl.
     */
    @Test
    public void test_calculateRevisedSettlementDate_for_AED_currency_on_Friday_expect_Sunday() {
        System.out.println("calculateRevisedSettlementDate_for_AED_currency_on_Friday_expect_Sunday");
        
        //set parameters - original day Friday, currency AED, expect Sunday
        LocalDate originalSettlementDate = LocalDate.of(2018, Month.JUNE, 1);   
        Currency currency = new Currency("AED", "UAE Dirham");
        LocalDate expResult = LocalDate.of(2018, Month.JUNE, 3);
        
        //create instance and execute
        CalculationServiceImpl instance = new CalculationServiceImpl();
        LocalDate result = instance.calculateRevisedSettlementDate(originalSettlementDate, currency);
        
        //make assertions
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calculateRevisedSettlementDate method, of class CalculationServiceImpl.
     */
    @Test
    public void test_calculateRevisedSettlementDate_for_SAR_currency_on_Friday_expect_Sunday() {
        System.out.println("calculateRevisedSettlementDate_for_SAR_currency_on_Friday_expect_Sunday");
        
        //set parameters - original day Friday, currency AED, expect Sunday
        LocalDate originalSettlementDate = LocalDate.of(2018, Month.JUNE, 1);   
        Currency currency = new Currency("SAR", "Saudi Riyal");
        LocalDate expResult = LocalDate.of(2018, Month.JUNE, 3);
        
        //create instance and execute
        CalculationServiceImpl instance = new CalculationServiceImpl();
        LocalDate result = instance.calculateRevisedSettlementDate(originalSettlementDate, currency);
        
        //make assertions
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calculateRevisedSettlementDate method, of class CalculationServiceImpl.
     */
    @Test
    public void test_calculateRevisedSettlementDate_for_GBP_currency_on_Friday_expect_Friday() {
        System.out.println("calculateRevisedSettlementDate_for_GBP_currency_on_Friday_expect_Friday");
        
        //set parameters - original day Friday, currency GBP, expect Friday
        LocalDate originalSettlementDate = LocalDate.of(2018, Month.JUNE, 1);   
        Currency currency = new Currency("GBP", "Pound Sterling");
        LocalDate expResult = LocalDate.of(2018, Month.JUNE, 1);
        
        //create instance and execute
        CalculationServiceImpl instance = new CalculationServiceImpl();
        LocalDate result = instance.calculateRevisedSettlementDate(originalSettlementDate, currency);
        
        //make assertions
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calculateRevisedSettlementDate method, of class CalculationServiceImpl.
     */
    @Test
    public void test_calculateRevisedSettlementDate_for_AED_currency_on_Saturday_expect_Sunday() {
        System.out.println("calculateRevisedSettlementDate_for_AED_currency_on_Saturday_expect_Sunday");
        
        //set parameters - original day Saturday, currency AED, expect Sunday
        LocalDate originalSettlementDate = LocalDate.of(2018, Month.JUNE, 2);   
        Currency currency = new Currency("AED", "UAE Dirham");
        LocalDate expResult = LocalDate.of(2018, Month.JUNE, 3);
        
        //create instance and execute
        CalculationServiceImpl instance = new CalculationServiceImpl();
        LocalDate result = instance.calculateRevisedSettlementDate(originalSettlementDate, currency);
        
        //make assertions
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calculateRevisedSettlementDate method, of class CalculationServiceImpl.
     */
    @Test
    public void test_calculateRevisedSettlementDate_for_SAR_currency_on_Saturday_expect_Sunday() {
        System.out.println("calculateRevisedSettlementDate_for_SAR_currency_on_Saturday_expect_Sunday");
        
        //set parameters - original day Saturday, currency SAR, expect Sunday
        LocalDate originalSettlementDate = LocalDate.of(2018, Month.JUNE, 2);   
        Currency currency = new Currency("SAR", "Saudi Riyal");
        LocalDate expResult = LocalDate.of(2018, Month.JUNE, 3);
        
        //create instance and execute
        CalculationServiceImpl instance = new CalculationServiceImpl();
        LocalDate result = instance.calculateRevisedSettlementDate(originalSettlementDate, currency);
        
        //make assertions
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calculateRevisedSettlementDate method, of class CalculationServiceImpl.
     */
    @Test
    public void test_calculateRevisedSettlementDate_for_GBP_currency_on_Saturday_expect_Monday() {
        System.out.println("calculateRevisedSettlementDate_for_GBP_currency_on_Saturday_expect_Monday");
        
        //set parameters - original day Saturday, currency GBP, expect Monday
        LocalDate originalSettlementDate = LocalDate.of(2018, Month.JUNE, 2);   
        Currency currency = new Currency("GBP", "Pound Sterling");
        LocalDate expResult = LocalDate.of(2018, Month.JUNE, 4);
        
        //create instance and execute
        CalculationServiceImpl instance = new CalculationServiceImpl();
        LocalDate result = instance.calculateRevisedSettlementDate(originalSettlementDate, currency);
        
        //make assertions
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calculateRevisedSettlementDate method, of class CalculationServiceImpl.
     */
    @Test
    public void test_calculateRevisedSettlementDate_for_GBP_currency_on_Sunday_expect_Monday() {
        System.out.println("calculateRevisedSettlementDate_for_GBP_currency_on_Sunday_expect_Monday");
        
        //set parameters - original day Sunday, currency GBP, expect Monday
        LocalDate originalSettlementDate = LocalDate.of(2018, Month.JUNE, 3);   
        Currency currency = new Currency("GBP", "Pound Sterling");
        LocalDate expResult = LocalDate.of(2018, Month.JUNE, 4);
        
        //create instance and execute
        CalculationServiceImpl instance = new CalculationServiceImpl();
        LocalDate result = instance.calculateRevisedSettlementDate(originalSettlementDate, currency);
        
        //make assertions
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calculateRevisedSettlementDate method, of class CalculationServiceImpl.
     */
    @Test
    public void test_calculateRevisedSettlementDate_for_AED_currency_on_Sunday_expect_Sunday() {
        System.out.println("calculateRevisedSettlementDate_for_AED_currency_on_Sunday_expect_Sunday");
        
        //set parameters - original day Sunday, currency AED, expect Sunday
        LocalDate originalSettlementDate = LocalDate.of(2018, Month.JUNE, 3);   
        Currency currency = new Currency("AED", "UAE Dirham");
        LocalDate expResult = LocalDate.of(2018, Month.JUNE, 3);
        
        //create instance and execute
        CalculationServiceImpl instance = new CalculationServiceImpl();
        LocalDate result = instance.calculateRevisedSettlementDate(originalSettlementDate, currency);
        
        //make assertions
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDailySettlementRecords method, of class CalculationServiceImpl.
     */
    @Test
    public void test_getDailySettlementRecords_BUY_3_instructions_over_2_days_expect_2_records_in_date_order() {
        System.out.println("getDailySettlementRecords_BUY_3_instructions_over_2_days_expect_2_records_in_date_order");
        
        //prepare 3 processed instructions with minimum required fields
        Instruction ins1 = new Instruction();
        ins1.setType(InstructionType.BUY);
        ProcessedInstruction p1 = new ProcessedInstruction(ins1);
        p1.setRevisedSettlementDate(LocalDate.of(2018, Month.JUNE, 4));
        p1.setTotalTradeAmount(new BigDecimal(10000));
        
        Instruction ins2 = new Instruction();
        ins2.setType(InstructionType.BUY);
        ProcessedInstruction p2 = new ProcessedInstruction(ins2);
        p2.setRevisedSettlementDate(LocalDate.of(2018, Month.JUNE, 5));
        p2.setTotalTradeAmount(new BigDecimal(15000));
        
        Instruction ins3 = new Instruction();
        ins3.setType(InstructionType.BUY);
        ProcessedInstruction p3 = new ProcessedInstruction(ins3);
        p3.setRevisedSettlementDate(LocalDate.of(2018, Month.JUNE, 5));
        p3.setTotalTradeAmount(new BigDecimal(11000));
        
        List<ProcessedInstruction> processedInstructions = Arrays.asList(p1, p2, p3);
                
        //create instance and execute
        CalculationServiceImpl instance = new CalculationServiceImpl();
        List<DailySettlementRecord> result = instance.getDailySettlementRecords(InstructionType.BUY, processedInstructions);
        
        //make assertions
        assertEquals("Number of records not as expected, ", 2, result.size());
        assertEquals(LocalDate.of(2018, Month.JUNE, 4), result.get(0).getSettlementDate());
    }
    
    /**
     * Test of getDailySettlementRecords method, of class CalculationServiceImpl.
     */
    @Test
    public void test_getDailySettlementRecords_SELL_2_instructions_over_1_day_expect_1_record_and_correct_total() {
        System.out.println("getDailySettlementRecords_SELL_2_instructions_over_1_day_expect_1_record_and_correct_total");
        
        //prepare 3 processed instructions with minimum required fields
        Instruction ins1 = new Instruction();
        ins1.setType(InstructionType.SELL);
        ProcessedInstruction p1 = new ProcessedInstruction(ins1);
        p1.setRevisedSettlementDate(LocalDate.of(2018, Month.JUNE, 4));
        p1.setTotalTradeAmount(new BigDecimal(10000));
        
        Instruction ins2 = new Instruction();
        ins2.setType(InstructionType.SELL);
        ProcessedInstruction p2 = new ProcessedInstruction(ins2);
        p2.setRevisedSettlementDate(LocalDate.of(2018, Month.JUNE, 4));
        p2.setTotalTradeAmount(new BigDecimal(15000));
                
        List<ProcessedInstruction> processedInstructions = Arrays.asList(p1, p2);
                
        //create instance and execute
        CalculationServiceImpl instance = new CalculationServiceImpl();
        List<DailySettlementRecord> result = instance.getDailySettlementRecords(InstructionType.SELL, processedInstructions);
        
        //make assertions
        assertEquals("Number of records not as expected, ", 1, result.size());
        assertTrue(new BigDecimal(25000).compareTo(result.get(0).getAmount()) == 0);
    }
    
    /**
     * Test of getEntityRankingRecords method, of class CalculationServiceImpl.
     */
    @Test
    public void test_getEntityRankingRecords_BUY_3_instructions_over_2_entities_expect_2_records_in_desc_value_order() {
        System.out.println("getDailySettlementRecords_BUY_3_instructions_over_2_entities_expect_2_records_in_desc_value_order");
        
        //prepare 3 processed instructions with minimum required fields
        Instruction ins1 = new Instruction();
        ins1.setType(InstructionType.BUY);
        ins1.setEntity("BT.A");
        ProcessedInstruction p1 = new ProcessedInstruction(ins1);
        p1.setTotalTradeAmount(new BigDecimal(10000));
        
        Instruction ins2 = new Instruction();
        ins2.setType(InstructionType.BUY);
        ins2.setEntity("BT.A");
        ProcessedInstruction p2 = new ProcessedInstruction(ins2);
        p2.setTotalTradeAmount(new BigDecimal(15000));
        
        Instruction ins3 = new Instruction();
        ins3.setType(InstructionType.BUY);
        ins3.setEntity("BT.B");
        ProcessedInstruction p3 = new ProcessedInstruction(ins3);
        p3.setTotalTradeAmount(new BigDecimal(11000));
        
        List<ProcessedInstruction> processedInstructions = Arrays.asList(p1, p2, p3);
                
        //create instance and execute
        CalculationServiceImpl instance = new CalculationServiceImpl();
        List<EntityRankingRecord> result = instance.getEntityRankingRecords(InstructionType.BUY, processedInstructions);
        
        //make assertions
        assertEquals("Number of records not as expected, ", 2, result.size());
        assertEquals("BT.A", result.get(0).getEntity());
        assertTrue(new BigDecimal(25000).compareTo(result.get(0).getAmount()) == 0);
    }
}
