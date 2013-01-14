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
package aos.algo.sort;

import aos.algo.list.ListSorter;
import aos.algo.sort.bubble.BubblesortListSorter;
import aos.algo.sort.insertion.InsertionSortListSorter;
import aos.algo.sort.selection.SelectionSortListSorter;
import aos.data.aos.comparator.NaturalComparator;
import aos.data.aos.list.ArrayList;
import aos.data.aos.list.List;
import junit.framework.TestCase;

/**
 * An example answer to exercise 6-1.
 * Create a test to prove that the three basic sorting algorithms
 * can sort a randomly generated list of Double objects.
 *
 */
public class ListSorterRandomDoublesTest extends TestCase {
    private static final int TEST_SIZE = 1000;

    private final List _randomList = new ArrayList(TEST_SIZE);
    private final NaturalComparator _comparator = NaturalComparator.INSTANCE;

    protected void setUp() throws Exception {
        super.setUp();

        for (int i = 1; i < TEST_SIZE; ++i) {
            _randomList.add(new Double((TEST_SIZE * Math.random())));
        }
    }

    public void testsortingRandomDoublesWithBubblesort() {
        ListSorter listSorter = new BubblesortListSorter(_comparator);
        List result = listSorter.sort(_randomList);
        assertSorted(result);
    }

    public void testsortingRandomDoublesWithSelectionsort() {
        ListSorter listSorter = new SelectionSortListSorter(_comparator);
        List result = listSorter.sort(_randomList);
        assertSorted(result);
    }

    public void testsortingRandomDoublesWithInsertionsort() {
        ListSorter listSorter = new InsertionSortListSorter(_comparator);
        List result = listSorter.sort(_randomList);
        assertSorted(result);
    }

    private void assertSorted(List list) {
        for (int i = 1; i < list.size(); i++) {
            Object o = list.get(i);
            assertTrue(_comparator.compare(list.get(i - 1), list.get(i)) <= 0);
        }
    }
}
