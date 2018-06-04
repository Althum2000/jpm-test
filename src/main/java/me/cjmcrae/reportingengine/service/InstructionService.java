/******************************************************************
 * Copyright 2018 Abstract Software Ltd. All rights reserved.
 *
 * No part of this code may be reproduced, distributed or ported
 * to another programming language without the express written
 * consent of Abstract Software Ltd.
 ******************************************************************/

package me.cjmcrae.reportingengine.service;

import java.util.List;
import me.cjmcrae.reportingengine.model.Instruction;
import me.cjmcrae.reportingengine.model.ProcessedInstruction;

/**
 *
 * Author: Colin J McRae
 * Date:   04-Jun-2018
 *
 */
public interface InstructionService {
    List<ProcessedInstruction> processInstructions(List<Instruction> instructions);
}
