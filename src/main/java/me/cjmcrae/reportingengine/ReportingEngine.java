/******************************************************************
 * Copyright 2018 Abstract Software Ltd. All rights reserved.
 *
 * No part of this code may be reproduced, distributed or ported
 * to another programming language without the express written
 * consent of Abstract Software Ltd.
 ******************************************************************/

package me.cjmcrae.reportingengine;

import me.cjmcrae.reportingengine.model.Instruction;
import java.util.List;
import me.cjmcrae.reportingengine.model.ProcessedInstruction;
import me.cjmcrae.reportingengine.service.InstructionService;
import me.cjmcrae.reportingengine.service.ReportingService;
import me.cjmcrae.reportingengine.service.impl.InstructionServiceImpl;
import me.cjmcrae.reportingengine.service.impl.ReportingServiceImpl;
import me.cjmcrae.reportingengine.util.InstructionGenerator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * Author: Colin J McRae
 * Date:   04-Jun-2018
 *
 */
public class ReportingEngine {
    private final Log log = LogFactory.getLog(ReportingEngine.class);
    
    public static void main(String[] args) {
        new ReportingEngine().runTest();
    }
    
    public void runTest() {
        log.info("Generating test instructions...");
        List<Instruction> testInstructions = new InstructionGenerator().generate(100, 3, 5);
        
        log.info("Processing instructions...");
        InstructionService instructionService = new InstructionServiceImpl();
        List<ProcessedInstruction> processedInstructions = instructionService.processInstructions(testInstructions);
        
        log.info("Running reports, directed to stdout...");
        ReportingService reportingService = new ReportingServiceImpl(System.out);
        reportingService.report(processedInstructions);
        
    }
    
    

}
