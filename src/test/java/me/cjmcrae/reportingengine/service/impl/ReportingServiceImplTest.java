/*
 * Copyright 2018 Abstract Software Ltd. All rights reserved.
 *
 * No part of this code may be reproduced, distributed or ported
 * to another programming language without the express written
 * consent of Abstract Software Ltd.
 */
package me.cjmcrae.reportingengine.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
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
public class ReportingServiceImplTest {
    
    public ReportingServiceImplTest() {
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
     * Test of report method, of class ReportingServiceImpl.
     */
    @Test
    public void testReport() {
        System.out.println("report");
        
        //create sample of processed instructions 
        Instruction ins1 = new Instruction();
        ins1.setType(InstructionType.BUY);
        ins1.setEntity("BT.A");
        ProcessedInstruction p1 = new ProcessedInstruction(ins1);
        p1.setRevisedSettlementDate(LocalDate.of(2018, Month.JUNE, 4));
        p1.setTotalTradeAmount(new BigDecimal(10000));
        List<ProcessedInstruction> processedInstructions = Arrays.asList(p1);
        
        //create instance, passing in byte array as printstream, and executing method
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        ReportingServiceImpl instance = new ReportingServiceImpl(new PrintStream(outContent));
        instance.report(processedInstructions);
        
        //make assertions using the byte array to ensure that the output is as expected
        StringBuilder expectedOutput = new StringBuilder();
        expectedOutput.append("Outgoing USD Daily Settlements")
                      .append(System.getProperty("line.separator"))
                      .append("==============================\n")
                      .append("2018-06-04 : 10000.00")
                      .append(System.getProperty("line.separator"))
                      .append(System.getProperty("line.separator"))
                      .append("Incoming USD Daily Settlements")
                      .append(System.getProperty("line.separator"))
                      .append("==============================\n")
                      .append(System.getProperty("line.separator"))
                      .append("Outgoing USD Entity Rankings")
                      .append(System.getProperty("line.separator"))
                      .append("==============================\n")
                      .append("BT.A : 10000.00").append(System.getProperty("line.separator"))
                      .append(System.getProperty("line.separator"))
                      .append("Incoming USD Entity Rankings")
                      .append(System.getProperty("line.separator"))
                      .append("==============================\n")
                      .append(System.getProperty("line.separator"));
        assertEquals(expectedOutput.toString(), outContent.toString());
    }
}
