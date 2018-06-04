/******************************************************************
 * Copyright 2018 Abstract Software Ltd. All rights reserved.
 *
 * No part of this code may be reproduced, distributed or ported
 * to another programming language without the express written
 * consent of Abstract Software Ltd.
 ******************************************************************/

package me.cjmcrae.reportingengine.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import me.cjmcrae.reportingengine.model.Currency;
import me.cjmcrae.reportingengine.model.DailySettlementRecord;
import me.cjmcrae.reportingengine.model.EntityRankingRecord;
import me.cjmcrae.reportingengine.model.InstructionType;
import me.cjmcrae.reportingengine.model.ProcessedInstruction;

/**
 *
 * Author: Colin J McRae
 * Date:   04-Jun-2018
 *
 */
public interface CalculationService {
    public BigDecimal calculateTradeTotalValue(BigDecimal pricePerUnit, BigDecimal units, BigDecimal agreedFx);
    public LocalDate calculateRevisedSettlementDate(LocalDate originalSettlementDate, Currency currency);
    public List<DailySettlementRecord> getDailySettlementRecords(InstructionType type, List<ProcessedInstruction> processedInstructions);
    public List<EntityRankingRecord> getEntityRankingRecords(InstructionType type, List<ProcessedInstruction> processedInstructions);
}
