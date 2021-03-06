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
public class DailySettlementRecord {
    private final LocalDate settlementDate;
    private final BigDecimal amount;

    public DailySettlementRecord(LocalDate settlementDate, BigDecimal amount) {
        this.settlementDate = settlementDate;
        this.amount = amount;
    }
    
    /**
     * @return the settlementDate
     */
    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }
}
