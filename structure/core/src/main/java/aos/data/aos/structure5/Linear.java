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
// An interface for LIFO/FIFO structures.
// (c) 1998,2001 duane a. bailey

package aos.data.aos.structure5;

/**
 * An interface describing the behavior of linear data structures, structures that 
 * that have completely determined  add and remove methods.  
 * Linear structures are often used to store the the state of a recursively
 * solved problem and stacks and queues are classic examples of such structures.
 * The structure package provides several implementations of the Linear interface, 
 * each of which has its particular strengths and weaknesses.
 *
 * @version $Id: Linear.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 * @see aos.data.aos.structure.Stack
 * @see aos.data.aos.structure.Queue
 */
public interface Linear<E> extends Structure<E>
{
    /**
     * Add a value to the structure.  The type of structure determines
     * the location of the value added.
     *
     * @pre value is non-null
     * @post the value is added to the collection,
     *       the consistent replacement policy is not specified
     * 
     * @param value The value to be added to the structure.
     */
    public void add(E value);

    /**
     * Preview the object to be removed.
     *
     * @pre structure is not empty
     * @post returns reference to next object to be removed
     *
     * @return A reference to the next object to be removed.
     */
    public E get();

    /**
     * Remove a value from the structure.  The particular value
     * to be removed is determined by the structure.
     *
     * @pre structure is not empty
     * @post removes an object from store
     * 
     * @return Value removed from structure.
     */
    public E remove();

    /**
     * Returns the number of elements in the linear structure.
     *
     * @post returns the number of elements in the structure
     * @return number of elements in structure.
     */
    public int size();

    /**
     * Returns true iff the structure is empty.
     *
     * @post returns true if and only if the linear structure is empty
     * 
     * @return True iff the linear structure is empty.
     */
    public boolean empty();
}