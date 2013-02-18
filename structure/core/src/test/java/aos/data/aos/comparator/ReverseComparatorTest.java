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
package aos.data.aos.comparator;

import aos.data.aos.comparator.NaturalComparator;
import aos.data.aos.comparator.ReverseComparator;
import junit.framework.TestCase;

/**
 * Test cases for {@link ReverseComparator}.
 * 
 */
public class ReverseComparatorTest extends TestCase {
    public void testLessThanBecomesGreaterThan() {
        ReverseComparator comparator = new ReverseComparator(NaturalComparator.INSTANCE);

        assertTrue(comparator.compare("A", "B") > 0);
    }

    public void testGreaterThanBecomesLessThan() {
        ReverseComparator comparator = new ReverseComparator(NaturalComparator.INSTANCE);

        assertTrue(comparator.compare("B", "A") < 0);
    }

    public void testEqualsRemainsUnchanged() {
        ReverseComparator comparator = new ReverseComparator(NaturalComparator.INSTANCE);

        assertTrue(comparator.compare("A", "A") == 0);
    }
}