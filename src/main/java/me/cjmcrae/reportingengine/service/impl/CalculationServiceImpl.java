/******************************************************************
 * Copyright 2018 Abstract Software Ltd. All rights reserved.
 *
 * No part of this code may be reproduced, distributed or ported
 * to another programming language without the express written
 * consent of Abstract Software Ltd.
 ******************************************************************/

package me.cjmcrae.reportingengine.service.impl;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.cjmcrae.reportingengine.model.Currency;
import me.cjmcrae.reportingengine.model.DailySettlementRecord;
import me.cjmcrae.reportingengine.model.EntityRankingRecord;
import me.cjmcrae.reportingengine.model.InstructionType;
import me.cjmcrae.reportingengine.model.ProcessedInstruction;
import me.cjmcrae.reportingengine.service.CalculationService;

/**
 *
 * Author: Colin J McRae
 * Date:   04-Jun-2018
 *
 */
public class CalculationServiceImpl implements CalculationService {

    @Override
    public BigDecimal calculateTradeTotalValue(BigDecimal pricePerUnit, BigDecimal units, BigDecimal agreedFx) {
        return agreedFx.multiply(units).multiply(pricePerUnit);
    }

    @Override
    public LocalDate calculateRevisedSettlementDate(LocalDate originalSettlementDate, Currency currency) {
        String currencyCode = currency.getCode();
        DayOfWeek originalSettlementDay = originalSettlementDate.getDayOfWeek();
        LocalDate revisedSettlementDate = originalSettlementDate;
        if(originalSettlementDay.equals(DayOfWeek.FRIDAY) && (currencyCode.equals("AED") || currencyCode.equals("SAR"))){
            revisedSettlementDate = originalSettlementDate.plusDays(2);
        } else if(originalSettlementDay.equals(DayOfWeek.SATURDAY) && (currencyCode.equals("AED") || currencyCode.equals("SAR"))){
            revisedSettlementDate = originalSettlementDate.plusDays(1);
        } else if(originalSettlementDay.equals(DayOfWeek.SATURDAY) && (!currencyCode.equals("AED") && !currencyCode.equals("SAR"))){
            revisedSettlementDate = originalSettlementDate.plusDays(2);
        } else if(originalSettlementDay.equals(DayOfWeek.SUNDAY) && (!currencyCode.equals("AED") && !currencyCode.equals("SAR"))){
            revisedSettlementDate = originalSettlementDate.plusDays(1);
        } 
        return revisedSettlementDate;
    }

    @Override
    public List<DailySettlementRecord> getDailySettlementRecords(InstructionType type, List<ProcessedInstruction> processedInstructions) {
        List<DailySettlementRecord> records = new ArrayList<>();
        
        Map<LocalDate, BigDecimal> dataMap = new HashMap<>();
        
        processedInstructions.stream().filter(instruction -> (instruction.getInstructionType().equals(type))).forEachOrdered(instruction -> {
            if(dataMap.containsKey(instruction.getRevisedSettlementDate())){
                dataMap.put(instruction.getRevisedSettlementDate(), dataMap.get(instruction.getRevisedSettlementDate()).add(instruction.getTotalTradeAmount()));
            } else {
                dataMap.put(instruction.getRevisedSettlementDate(), instruction.getTotalTradeAmount());
            }
        });
        
        dataMap.keySet().stream().map(settlementDate -> new DailySettlementRecord(settlementDate, dataMap.get(settlementDate))).forEachOrdered(records::add);
        
        Collections.sort(records, (r1, r2) -> r1.getSettlementDate().compareTo(r2.getSettlementDate()));
        
        return records;
    }

    @Override
    public List<EntityRankingRecord> getEntityRankingRecords(InstructionType type, List<ProcessedInstruction> processedInstructions) {
        List<EntityRankingRecord> records = new ArrayList<>();
        
        Map<String, BigDecimal> dataMap = new HashMap<>();
        
        processedInstructions.stream().filter(instruction -> (instruction.getInstructionType().equals(type))).forEachOrdered(instruction -> {
            if(dataMap.containsKey(instruction.getEntity())){
                dataMap.put(instruction.getEntity(), dataMap.get(instruction.getEntity()).add(instruction.getTotalTradeAmount()));
            } else {
                dataMap.put(instruction.getEntity(), instruction.getTotalTradeAmount());
            }
        });
        
        dataMap.keySet().stream().map(entity -> new EntityRankingRecord(entity, dataMap.get(entity))).forEachOrdered(records::add);
        
        Collections.sort(records, (r1, r2) -> r2.getAmount().compareTo(r1.getAmount()));
        
        return records;
    }

}
