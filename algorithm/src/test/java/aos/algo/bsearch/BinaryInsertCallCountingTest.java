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
package aos.algo.bsearch;

import aos.algo.bsearch.IterativeBinaryListSearcher;
import aos.algo.list.ListInserter;
import aos.algo.list.ListSorter;
import aos.algo.sort.merge.MergesortListSorter;
import aos.algo.sort.quick.QuicksortListSorter;
import aos.algo.sort.shell.ShellsortListSorter;
import aos.data.comparator.CallCountingComparator;
import aos.data.comparator.NaturalComparator;
import aos.data.list.ArrayList;
import aos.data.list.List;
import junit.framework.TestCase;

/**
 * Compares the performance of binary insertion versus sorting.
 *
 */
public class BinaryInsertCallCountingTest extends TestCase {
    private static final int TEST_SIZE = 4091;

    private List _list;
    private CallCountingComparator _comparator;

    protected void setUp() throws Exception {
        super.setUp();

        _list = new ArrayList(TEST_SIZE);
        _comparator = new CallCountingComparator(NaturalComparator.INSTANCE);
    }

    public void testBinaryInsert() {
        ListInserter inserter = new ListInserter(new IterativeBinaryListSearcher(_comparator));

        for (int i = 0; i < TEST_SIZE; ++i) {
            inserter.insert(_list, new Integer((int) (TEST_SIZE * Math.random())));
        }

        reportCalls();
    }

    public void testMergeSort() {
        populateAndSort(new MergesortListSorter(_comparator));
    }

    public void testShellsort() {
        populateAndSort(new ShellsortListSorter(_comparator));
    }

    public void testQuicksort() {
        populateAndSort(new QuicksortListSorter(_comparator));
    }

    private void populateAndSort(ListSorter sorter) {
        for (int i = 0; i < TEST_SIZE; ++i) {
            _list.add(new Integer((int) (TEST_SIZE * Math.random())));
        }

        _list = sorter.sort(_list);

        reportCalls();
    }

    private void reportCalls() {
        System.out.println(getName() + ": " + _comparator.getCallCount() + " calls");
    }
}
