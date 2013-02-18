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
package aos.data.aos.hash;

import aos.data.aos.hash.Hashtable;
import junit.framework.TestCase;

/**
 * Abstract base class for testing {@link Hashtable} implementations.
 *
 */
public abstract class AbstractHashtableTestCase extends TestCase {
    private static final int TEST_SIZE = 1000;

    private Hashtable _hashtable;

    protected void setUp() throws Exception {
        super.setUp();

        _hashtable = createTable(TEST_SIZE);

        for (int i = 0; i < TEST_SIZE; ++i) {
            _hashtable.add(String.valueOf(i));
        }
    }

    protected abstract Hashtable createTable(int capacity);

    public void testAddingTheSameValuesDoesntChangeTheSize() {
        assertEquals(TEST_SIZE, _hashtable.size());

        for (int i = 0; i < TEST_SIZE; ++i) {
            _hashtable.add(String.valueOf(i));
            assertEquals(TEST_SIZE, _hashtable.size());
        }
    }

    public void testContains() {
        for (int i = 0; i < TEST_SIZE; ++i) {
            assertTrue(_hashtable.contains(String.valueOf(i)));
        }
    }

    public void testDoesntContain() {
        for (int i = 0; i < TEST_SIZE; ++i) {
            assertFalse(_hashtable.contains(String.valueOf(i + TEST_SIZE)));
        }
    }
}