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
public class Instruction {
    private String entity;
    private InstructionType type;
    private BigDecimal agreedFx;
    private Currency currency;
    private LocalDate instructionDate;
    private LocalDate settlementDate;
    private BigDecimal units;
    private BigDecimal pricePerUnit;

    /**
     * @return the entity
     */
    public String getEntity() {
        return entity;
    }

    /**
     * @param entity the entity to set
     */
    public void setEntity(String entity) {
        this.entity = entity;
    }

    /**
     * @return the type
     */
    public InstructionType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(InstructionType type) {
        this.type = type;
    }

    /**
     * @return the agreedFx
     */
    public BigDecimal getAgreedFx() {
        return agreedFx;
    }

    /**
     * @param agreedFx the agreedFx to set
     */
    public void setAgreedFx(BigDecimal agreedFx) {
        this.agreedFx = agreedFx;
    }

    /**
     * @return the currency
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * @return the instructionDate
     */
    public LocalDate getInstructionDate() {
        return instructionDate;
    }

    /**
     * @param instructionDate the instructionDate to set
     */
    public void setInstructionDate(LocalDate instructionDate) {
        this.instructionDate = instructionDate;
    }

    /**
     * @return the settlementDate
     */
    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    /**
     * @param settlementDate the settlementDate to set
     */
    public void setSettlementDate(LocalDate settlementDate) {
        this.settlementDate = settlementDate;
    }

    /**
     * @return the units
     */
    public BigDecimal getUnits() {
        return units;
    }

    /**
     * @param units the units to set
     */
    public void setUnits(BigDecimal units) {
        this.units = units;
    }

    /**
     * @return the pricePerUnit
     */
    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    /**
     * @param pricePerUnit the pricePerUnit to set
     */
    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}
