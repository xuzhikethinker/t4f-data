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
package aos.format.table;

import java.text.FieldPosition;
import java.text.NumberFormat;

public class Table3Format {

    public static void main(String... args) {

        NumberFormat myFormat = NumberFormat.getNumberInstance();
        FieldPosition fp = new FieldPosition(NumberFormat.INTEGER_FIELD);
        myFormat.setMaximumIntegerDigits(3);
        myFormat.setMaximumFractionDigits(2);
        myFormat.setMinimumFractionDigits(2);

        System.out.println("Degrees  Radians  Grads");
        for (double degrees = 0.0; degrees < 360.0; degrees++) {
            String radianString = myFormat.format(Math.PI * degrees / 180.0, new StringBuffer(), fp).toString();
            radianString = getSpaces(3 - fp.getEndIndex()) + radianString;
            String gradString = myFormat.format(400 * degrees / 360, new StringBuffer(), fp).toString();
            gradString = getSpaces(3 - fp.getEndIndex()) + gradString;
            String degreeString = myFormat.format(degrees, new StringBuffer(), fp).toString();
            degreeString = getSpaces(3 - fp.getEndIndex()) + degreeString;
            System.out.println(degreeString + "  " + radianString + "  " + gradString);
        }
    }

    public static String getSpaces(int n) {
        StringBuffer sb = new StringBuffer(n);
        for (int i = 0; i < n; i++)
            sb.append(' ');
        return sb.toString();
    }

}
