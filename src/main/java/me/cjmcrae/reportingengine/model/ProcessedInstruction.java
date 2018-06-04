/******************************************************************
 * Copyright 2018 Abstract Software Ltd. All rights reserved.
 *
 * No part of this code may be reproduced, distributed or ported
 * to another programming language without the express written
 * consent of Abstract Software Ltd.
 ******************************************************************/

package me.cjmcrae.reportingengine.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * Author: Colin J McRae
 * Date:   04-Jun-2018
 *
 */
public class ProcessedInstruction {
    private final Instruction originalInstruction;
    private LocalDate revisedSettlementDate;
    private BigDecimal totalTradeAmount;
    private boolean processedSuccessfully;
    private String errorMessage;
    
    public ProcessedInstruction(Instruction originalInstruction) {
        this.originalInstruction = originalInstruction;
    }
    
    public InstructionType getInstructionType() {
        return originalInstruction.getType();
    }
    
    public String getEntity() {
        return originalInstruction.getEntity();
    }

    /**
     * @return the revisedSettlementDate
     */
    public LocalDate getRevisedSettlementDate() {
        return revisedSettlementDate;
    }

    /**
     * @param revisedSettlementDate the revisedSettlementDate to set
     */
    public void setRevisedSettlementDate(LocalDate revisedSettlementDate) {
        this.revisedSettlementDate = revisedSettlementDate;
    }

    /**
     * @return the totalTradeAmount
     */
    public BigDecimal getTotalTradeAmount() {
        return totalTradeAmount;
    }

    /**
     * @param totalTradeAmount the totalTradeAmount to set
     */
    public void setTotalTradeAmount(BigDecimal totalTradeAmount) {
        this.totalTradeAmount = totalTradeAmount;
    }

    /**
     * @return the processedSuccessfully
     */
    public boolean getProcessedSuccessfully() {
        return processedSuccessfully;
    }

    /**
     * @param processedSuccessfully the processedSuccessfully to set
     */
    public void setProcessedSuccessfully(boolean processedSuccessfully) {
        this.processedSuccessfully = processedSuccessfully;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
}
