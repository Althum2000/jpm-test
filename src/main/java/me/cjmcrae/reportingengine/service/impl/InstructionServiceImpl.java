/******************************************************************
 * Copyright 2018 Abstract Software Ltd. All rights reserved.
 *
 * No part of this code may be reproduced, distributed or ported
 * to another programming language without the express written
 * consent of Abstract Software Ltd.
 ******************************************************************/

package me.cjmcrae.reportingengine.service.impl;

import java.util.ArrayList;
import java.util.List;
import me.cjmcrae.reportingengine.exception.InvalidCurrencyException;
import me.cjmcrae.reportingengine.model.Instruction;
import me.cjmcrae.reportingengine.model.ProcessedInstruction;
import me.cjmcrae.reportingengine.service.CalculationService;
import me.cjmcrae.reportingengine.service.CurrencyService;
import me.cjmcrae.reportingengine.service.InstructionService;

/**
 *
 * Author: Colin J McRae
 * Date:   04-Jun-2018
 *
 */
public class InstructionServiceImpl implements InstructionService {
    private final CurrencyService currencyService;
    private final CalculationService calculationService;
    
    public InstructionServiceImpl() {
        currencyService = new CurrencyServiceImpl();
        calculationService = new CalculationServiceImpl();
    }
    
    @Override
    public List<ProcessedInstruction> processInstructions(List<Instruction> instructions) {
        List<ProcessedInstruction> processedInstructions = new ArrayList<>();
        instructions.forEach(instruction -> {
            ProcessedInstruction processedInstruction = this.processInstruction(instruction);
            processedInstructions.add(processedInstruction);
        });
        return processedInstructions;
    }
    
    private ProcessedInstruction processInstruction(Instruction instruction) {
        ProcessedInstruction processedInstruction = new ProcessedInstruction(instruction);
        try{
            //check the currency of the instruction
            if(!currencyService.isAllowable(instruction.getCurrency().getCode())){
                throw new InvalidCurrencyException("Instruction has an invalid currency code.");
            }
            //calculate the total trade value
            processedInstruction.setTotalTradeAmount(calculationService.calculateTradeTotalValue(instruction.getPricePerUnit(),
                    instruction.getUnits(), instruction.getAgreedFx()));
            //update the settlement date if required
            processedInstruction.setRevisedSettlementDate(calculationService.calculateRevisedSettlementDate(instruction.getSettlementDate(), 
                    instruction.getCurrency()));
            processedInstruction.setProcessedSuccessfully(true);
        } catch(InvalidCurrencyException e){
            processedInstruction.setProcessedSuccessfully(false);
            processedInstruction.setErrorMessage(e.getMessage());
        }
        return processedInstruction;
    }
}
