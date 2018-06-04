/*
 * Copyright 2018 Abstract Software Ltd. All rights reserved.
 *
 * No part of this code may be reproduced, distributed or ported
 * to another programming language without the express written
 * consent of Abstract Software Ltd.
 */
package me.cjmcrae.reportingengine.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import me.cjmcrae.reportingengine.model.Instruction;
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
public class InstructionGeneratorTest {
    
    public InstructionGeneratorTest() {
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
     * Test of generate method, of class InstructionGenerator.
     */
    @Test
    public void test_generate() {
        System.out.println("generate");
        
        //set parameters for test
        int numberOfInstructions = 5000;
        int instructionDateSpread = 3;
        int settlementDateSpread = 5;
        
        //execute utility method
        List<Instruction> result = new InstructionGenerator().generate(numberOfInstructions, instructionDateSpread, settlementDateSpread);
        
        //assert
        assertEquals("Generated number of tests is not as expected, ", numberOfInstructions, result.size());
        
        //ensure that the dates are within expectations
        for(Instruction instr : result) {
            LocalDate today = LocalDate.now();
            LocalDate instructionDate = instr.getInstructionDate();
            LocalDate settlementDate = instr.getSettlementDate();
            
            int actualInstrDateSpread = Period.between(instructionDate, today).getDays();
            int actualSettlementDateSpread = Period.between(settlementDate, instructionDate).getDays();
            
            assertTrue("Instruction date spread not within bounds", actualInstrDateSpread <= instructionDateSpread);
            assertTrue("Settlement date spread not within bounds", actualSettlementDateSpread <= settlementDateSpread);
        }
        
    }
    
}
