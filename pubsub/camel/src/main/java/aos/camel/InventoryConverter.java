/****************************************************************
 * Licensed to the AOS Community (AOS) under one or more        *
 * contributor license agreements.  See the NOTICE file         *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The AOS licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/
package aos.camel;

import camelinaction.inventory.UpdateInventoryInput;
import org.apache.camel.Converter;

/**
 * A Camel converter which can convert from CSV (String) to model objects.
 * <p/>
 * By annotation this class with @Converter we tell Camel this is a converter class
 * it should scan and register methods as type converters.
 *
 * @version $Revision: 162 $
 */
@Converter
public final class InventoryConverter {

    private InventoryConverter() {
    }

    /**
     * This method can convert from CSV (String) to model object.
     * <p/>
     * By annotation this method with @Converter we tell Camel to include this method
     * as a type converter in its type converter registry.
     *
     * @param csv the from type
     * @return the to type
     */
    @Converter
    public static UpdateInventoryInput toInput(String csv) {
        String[] lines = csv.split(",");
        if (lines == null || lines.length != 4) {
            throw new IllegalArgumentException("CSV line is not valid: " + csv);
        }

        UpdateInventoryInput input = new UpdateInventoryInput();
        input.setSupplierId(lines[0]);
        input.setPartId(lines[1]);
        input.setName(lines[2]);
        input.setAmount(lines[3]);

        return input;
    }

}
