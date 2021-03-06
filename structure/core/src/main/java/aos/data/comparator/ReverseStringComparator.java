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
package aos.data.comparator;



/**
 * A {@link aos.data.comparator.Comparator} that compares strings in reverse.
 *
 */
public final class ReverseStringComparator implements Comparator {
    /** The single, publicly accessible, instance of the comparator. */
    public static final ReverseStringComparator INSTANCE = new ReverseStringComparator();

    /**
     * Constructor marked private to prevent instantiation.
     */
    private ReverseStringComparator() {
    }

    public int compare(Object left, Object right) throws ClassCastException {
        assert left != null : "left can't be null";
        assert right != null : "right can't be null";

        return reverse((String) left).compareTo(reverse((String) right));
    }

    private String reverse(String s) {
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < s.length(); ++i) {
            result.append(s.charAt(s.length() - 1 - i));
        }

        return result.toString();
    }
}
