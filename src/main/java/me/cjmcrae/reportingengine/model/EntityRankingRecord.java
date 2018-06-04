/******************************************************************
 * Copyright 2018 Abstract Software Ltd. All rights reserved.
 *
 * No part of this code may be reproduced, distributed or ported
 * to another programming language without the express written
 * consent of Abstract Software Ltd.
 ******************************************************************/

package me.cjmcrae.reportingengine.model;

import java.math.BigDecimal;

/**
 *
 * Author: Colin J McRae
 * Date:   04-Jun-2018
 *
 */
public class EntityRankingRecord {
    private final String entity;
    private final BigDecimal amount;
    
    public EntityRankingRecord(String entity, BigDecimal amount) {
        this.entity = entity;
        this.amount = amount;
    }

    /**
     * @return the entity
     */
    public String getEntity() {
        return entity;
    }

    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }
}
