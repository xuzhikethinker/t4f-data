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

package com.wideplay.warp.persist;

/**
 *
 * <p>
 * This interface is used to gain manual control over the unit of work. This
 * is mostly to do work in non-request, non-transactional threads. Or where more
 * fine-grained control over the unit of work is required. Starting and ending a
 * unit of work directly corresponds to opening and closing a {@code Session},
 * {@code EntityManager} or {@code ObjectContainer} respectively.
 * </p>
 *
 * <p>
 * The Unit of Work referred to by WorkManager will always be local to the
 * calling thread. Be careful to endWork() in a finally block. Neither JPA,
 * nor Hibernate supports threadsafe sessions (reasoning behind thread-locality
 * of Unit of Work semantics).
 * </p>
 *
 * <ul>
 *   <li>Using WorkManager with the PersistenceFilter inside a request is not recommended.</li>
 *
 *   <li>Using WorkManager with session-per-txn strategy is not terribly clever either.</li>
 *
 *   <li>Using WorkManager with session-per-request strategy but *outside* a request
 * (i.e. in a background or bootstrap thread) is probably a good use case.</li>
 * </ul>
 *
 * @author Dhanji R. Prasanna (dhanji gmail com)
 */
public interface WorkManager {

    /**
     * Starts a Unit Of Work. Underneath, causes a session to
     * the data layer to be opened. If there is already one open,
     * the invocation will do nothing. In this way, you can define
     * arbitrary units-of-work that nest within one another safely.
     *
     * Transaction semantics are not affected.
     */
    void beginWork();

    /**
     * Declares an end to the current Unit of Work. Underneath, causes any
     * open session to the data layer to close. If there is no Unit of work
     * open, then the call returns silently. You can safely invoke endWork()
     * repeatedly.
     *
     * Transaction semantics are not affected.
     *
     */
    void endWork();

    /** Returns a short description that uniquely identifies the WorkManager. */
    String toString();
}
