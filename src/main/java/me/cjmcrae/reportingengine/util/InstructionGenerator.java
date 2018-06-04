/******************************************************************
 * Copyright 2018 Abstract Software Ltd. All rights reserved.
 *
 * No part of this code may be reproduced, distributed or ported
 * to another programming language without the express written
 * consent of Abstract Software Ltd.
 ******************************************************************/

package me.cjmcrae.reportingengine.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import me.cjmcrae.reportingengine.model.Currency;
import me.cjmcrae.reportingengine.model.Instruction;
import me.cjmcrae.reportingengine.model.InstructionType;
import me.cjmcrae.reportingengine.service.CurrencyService;
import me.cjmcrae.reportingengine.service.impl.CurrencyServiceImpl;

/**
 *
 * Author: Colin J McRae
 * Date:   04-Jun-2018
 *
 */
public class InstructionGenerator {
    
    public List<Instruction> generate(int numberOfInstructions, int instructionDateSpread, int settlementDateSpread) {
        List<Instruction> instructions = new ArrayList<>();
        //get list of entities
        List<String> entities = Arrays.asList("ABF", "ADM", "AAL", "BRBY", "CCL", "BT.A");
        //create currency service and get all allowable currencies
        CurrencyService currencyService = new CurrencyServiceImpl();
        List<Currency> currencies = currencyService.getAllowableCurrencies();
        for(int i=0; i<numberOfInstructions; i++){
            Instruction instr = new Instruction();
            //get random entity
            int index = ThreadLocalRandom.current().nextInt(0, entities.size());
            instr.setEntity(entities.get(index));
            //get random instruction type - buy or sell
            instr.setType(ThreadLocalRandom.current().nextBoolean()?InstructionType.BUY:InstructionType.SELL);
            //get random currency
            index = ThreadLocalRandom.current().nextInt(0, currencies.size());
            instr.setCurrency(currencies.get(index));
            //get random fx or 1 if currency is USD
            if(instr.getCurrency().getCode().equals("USD")){
                instr.setAgreedFx(BigDecimal.ONE);
            } else {
                BigDecimal agreedFx = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0.1, 2.0)).setScale(2, RoundingMode.HALF_UP);
                instr.setAgreedFx(agreedFx);
            }
            //get random units
            instr.setUnits(new BigDecimal(ThreadLocalRandom.current().nextInt(10, 2000)));
            //get random price per unit
            BigDecimal pricePerUnit = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(100.0, 250.0)).setScale(2, RoundingMode.HALF_UP);
            instr.setPricePerUnit(pricePerUnit);
                        
            //instruction and settlement dates - instruction within instructionDateSpread days, settlement within an additional settlementDateSpread days
            LocalDate instructionDate = LocalDate.now();
            instructionDate = instructionDate.plusDays(ThreadLocalRandom.current().nextLong(0, instructionDateSpread+1L));
            LocalDate settlementDate = instructionDate.plusDays(ThreadLocalRandom.current().nextLong(1, settlementDateSpread+1L));
            instr.setInstructionDate(instructionDate);
            instr.setSettlementDate(settlementDate);
            
            instructions.add(instr);
        }
        
        return instructions;
    }

}
