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
public class InstructionServiceImplTest {
    
    public InstructionServiceImplTest() {
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
     * Test of processInstructions method, of class InstructionServiceImpl.
     */
    @Test
    public void test_processInstructions_invalid_currency_expect_failed_processed_instruction() {
        System.out.println("processInstructions_invalid_currency_expect_failed_processed_instruction");
        
        //create instruction with invalid currency code and add to array for passing into method
        Instruction instruction = new Instruction();
        instruction.setEntity("BT.A");
        instruction.setType(InstructionType.BUY);
        instruction.setAgreedFx(BigDecimal.ONE);
        instruction.setCurrency(new Currency("BOB", "Bob Dollar"));
        instruction.setInstructionDate(LocalDate.now());
        instruction.setSettlementDate(instruction.getInstructionDate().plusDays(2));
        instruction.setUnits(new BigDecimal(200));
        instruction.setPricePerUnit(new BigDecimal(100.25));
        List<Instruction> instructions = Arrays.asList(instruction);
        
        //create instance and invoke method
        InstructionServiceImpl instance = new InstructionServiceImpl();
        List<ProcessedInstruction> result = instance.processInstructions(instructions);
        
        //make assertions
        assertFalse(result.get(0).getProcessedSuccessfully());
        assertNotNull(result.get(0).getErrorMessage());
    }
    
    /**
     * Test of processInstructions method, of class InstructionServiceImpl.
     */
    @Test
    public void test_processInstructions_expect_success_and_no_revised_settlement_date() {
        System.out.println("processInstructions_expect_success_and_no_revised_settlement_date");
        
        //create instruction with and add to array for passing into method
        Instruction instruction = new Instruction();
        instruction.setEntity("BT.A");
        instruction.setType(InstructionType.BUY);
        instruction.setAgreedFx(new BigDecimal(0.5));
        instruction.setCurrency(new Currency("GBP", "Pound Sterling"));
        instruction.setInstructionDate(LocalDate.of(2018, Month.JUNE, 4));
        instruction.setSettlementDate(instruction.getInstructionDate().plusDays(2));
        instruction.setUnits(new BigDecimal(200));
        instruction.setPricePerUnit(new BigDecimal(100.25));
        List<Instruction> instructions = Arrays.asList(instruction);
        
        //expected total value and revised settlement date
        BigDecimal expTotalValue = new BigDecimal(10025);
        LocalDate expRevisedSettlementDate = instruction.getSettlementDate();
        
        //create instance and invoke method
        InstructionServiceImpl instance = new InstructionServiceImpl();
        List<ProcessedInstruction> result = instance.processInstructions(instructions);
        
        //make assertions
        assertTrue(result.get(0).getProcessedSuccessfully());
        assertNull(result.get(0).getErrorMessage());
        assertTrue(expTotalValue.compareTo(result.get(0).getTotalTradeAmount()) == 0);
        assertEquals(expRevisedSettlementDate, result.get(0).getRevisedSettlementDate());
    }
    
    /**
     * Test of processInstructions method, of class InstructionServiceImpl.
     */
    @Test
    public void test_processInstructions_GBP_currency_expect_success_and_revised_settlement_date() {
        System.out.println("processInstructions_GBP_currency_expect_success_and_revised_settlement_date");
        
        //create instruction with and add to array for passing into method
        Instruction instruction = new Instruction();
        instruction.setEntity("BT.A");
        instruction.setType(InstructionType.BUY);
        instruction.setAgreedFx(new BigDecimal(0.5));
        instruction.setCurrency(new Currency("GBP", "Pound Sterling"));
        instruction.setInstructionDate(LocalDate.of(2018, Month.MAY, 31));
        instruction.setSettlementDate(LocalDate.of(2018, Month.JUNE, 2));
        instruction.setUnits(new BigDecimal(200));
        instruction.setPricePerUnit(new BigDecimal(100.25));
        List<Instruction> instructions = Arrays.asList(instruction);
        
        //expected total value and revised settlement date
        BigDecimal expTotalValue = new BigDecimal(10025);
        LocalDate expRevisedSettlementDate = LocalDate.of(2018, Month.JUNE, 4);
        
        //create instance and invoke method
        InstructionServiceImpl instance = new InstructionServiceImpl();
        List<ProcessedInstruction> result = instance.processInstructions(instructions);
        
        //make assertions
        assertTrue(result.get(0).getProcessedSuccessfully());
        assertNull(result.get(0).getErrorMessage());
        assertTrue(expTotalValue.compareTo(result.get(0).getTotalTradeAmount()) == 0);
        assertEquals(expRevisedSettlementDate, result.get(0).getRevisedSettlementDate());
    }
    
    /**
     * Test of processInstructions method, of class InstructionServiceImpl.
     */
    @Test
    public void test_processInstructions_AED_currency_expect_success_and_revised_settlement_date() {
        System.out.println("processInstructions_AED_currency_expect_success_and_revised_settlement_date");
        
        //create instruction with and add to array for passing into method
        Instruction instruction = new Instruction();
        instruction.setEntity("BT.A");
        instruction.setType(InstructionType.BUY);
        instruction.setAgreedFx(new BigDecimal(0.5));
        instruction.setCurrency(new Currency("AED", "UAE Dirham"));
        instruction.setInstructionDate(LocalDate.of(2018, Month.MAY, 30));
        instruction.setSettlementDate(LocalDate.of(2018, Month.JUNE, 1));
        instruction.setUnits(new BigDecimal(200));
        instruction.setPricePerUnit(new BigDecimal(100.25));
        List<Instruction> instructions = Arrays.asList(instruction);
        
        //expected total value and revised settlement date
        BigDecimal expTotalValue = new BigDecimal(10025);
        LocalDate expRevisedSettlementDate = LocalDate.of(2018, Month.JUNE, 3);
        
        //create instance and invoke method
        InstructionServiceImpl instance = new InstructionServiceImpl();
        List<ProcessedInstruction> result = instance.processInstructions(instructions);
        
        //make assertions
        assertTrue(result.get(0).getProcessedSuccessfully());
        assertNull(result.get(0).getErrorMessage());
        assertTrue(expTotalValue.compareTo(result.get(0).getTotalTradeAmount()) == 0);
        assertEquals(expRevisedSettlementDate, result.get(0).getRevisedSettlementDate());
    }
}
