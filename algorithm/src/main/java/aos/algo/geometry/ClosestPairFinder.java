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
package aos.algo.geometry;

import aos.data.geometry.Point;
import aos.data.set.Set;

/**
 * Generic interface for closest pair of points algorithms.
 *
 */
public interface ClosestPairFinder {
    /**
     * Find the closest pair of {@link Point}s.
     *
     * @param points The set of points to consider.
     * @return The the closest pair of {@link Point}s.
     */
    public Set findClosestPair(Set points);
}
