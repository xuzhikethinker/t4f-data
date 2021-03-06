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
package aos.algo.sort.insertion;

import aos.algo.list.ListSorter;
import aos.data.comparator.Comparator;
import aos.data.list.List;

/**
 * Sample solution to exercise 7-4.
 * A {@link aos.algo.list.ListSorter} that uses an
 * in-place insertion sort algorithm.
 *
 */
public class InPlaceInsertionSortListSorter implements ListSorter {
    private final Comparator _comparator;

    /**
     * @param comparator the comparator to control the order of the sorted objects.
     */
    public InPlaceInsertionSortListSorter(Comparator comparator) {
        assert comparator != null : "comparator cannot be null";
        _comparator = comparator;
    }

    /**
     * Sorts a list using the inseriton sort algorithm.
     *
     * @param list The list to sort.
     * @return the input list with items in sorted order.
     */
    public List sort(List list) {
        assert list != null : "list cannot be null";

        for (int i = 1; i < list.size(); ++i) {
            Object value = list.get(i);
            int j;
            for (j = i; j > 0; --j) {
                Object previousValue = list.get(j - 1);
                if (_comparator.compare(value, previousValue) >= 0) {
                    break;
                }
                list.set(j, previousValue);
            }
            list.set(j, value);
        }

        return list;
    }
}
