/******************************************************************
 * Copyright 2018 Abstract Software Ltd. All rights reserved.
 *
 * No part of this code may be reproduced, distributed or ported
 * to another programming language without the express written
 * consent of Abstract Software Ltd.
 ******************************************************************/

package me.cjmcrae.reportingengine.service.impl;

import java.io.PrintStream;
import java.time.format.DateTimeFormatter;
import java.util.List;
import me.cjmcrae.reportingengine.model.DailySettlementRecord;
import me.cjmcrae.reportingengine.model.EntityRankingRecord;
import me.cjmcrae.reportingengine.model.InstructionType;
import me.cjmcrae.reportingengine.model.ProcessedInstruction;
import me.cjmcrae.reportingengine.service.CalculationService;
import me.cjmcrae.reportingengine.service.ReportingService;

/**
 *
 * Author: Colin J McRae
 * Date:   04-Jun-2018
 *
 */
public class ReportingServiceImpl implements ReportingService {
    
    private final CalculationService calculationService;
    
    private final PrintStream printStream;
    
    public ReportingServiceImpl(PrintStream printStream){
        this.printStream = printStream;
        this.calculationService = new CalculationServiceImpl();
    }

    @Override
    public void report(List<ProcessedInstruction> processedInstructions) {
        
        List<DailySettlementRecord> outgoingDaily = calculationService.getDailySettlementRecords(InstructionType.BUY, processedInstructions);
        this.printDailySettlementRecords(InstructionType.BUY, outgoingDaily);
        List<DailySettlementRecord> incomingDaily = calculationService.getDailySettlementRecords(InstructionType.SELL, processedInstructions);
        this.printDailySettlementRecords(InstructionType.SELL, incomingDaily);
        List<EntityRankingRecord> outgoingEntity = calculationService.getEntityRankingRecords(InstructionType.BUY, processedInstructions);
        this.printEntityRankingRecords(InstructionType.BUY, outgoingEntity);
        List<EntityRankingRecord> incomingEntity = calculationService.getEntityRankingRecords(InstructionType.SELL, processedInstructions);
        this.printEntityRankingRecords(InstructionType.SELL, incomingEntity);
    }
    
    private void printDailySettlementRecords(InstructionType type, List<DailySettlementRecord> records){
        printStream.printf("%s USD Daily Settlements%n", type.equals(InstructionType.BUY)?"Outgoing":"Incoming");
        printStream.print("==============================\n");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd" );
        records.forEach(record -> 
            printStream.printf("%s : %.2f%n", record.getSettlementDate().format(formatter), record.getAmount())
        );
        printStream.println();
    }
    
    private void printEntityRankingRecords(InstructionType type, List<EntityRankingRecord> records){
        printStream.printf("%s USD Entity Rankings%n", type.equals(InstructionType.BUY)?"Outgoing":"Incoming");
        printStream.print("==============================\n");
        records.forEach(record -> 
            printStream.printf("%s : %.2f%n", record.getEntity(), record.getAmount())
        );
        printStream.println();
    }
}
