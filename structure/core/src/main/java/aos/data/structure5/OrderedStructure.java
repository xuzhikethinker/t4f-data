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
// Interface for container classes that manipulated ordered structures.
// (c) 1998, 2001 duane a. bailey

package aos.data.structure5;

/**
 * An interface that supports a Collection whose values are kept
 * in increasing order.  Values stored within ordered structures
 * should implement Comparable; ie. they should have an implemented
 * compareTo method.
 * 
 * @see java.lang.Comparable
 * @see java.lang.Comparable#compareTo
 * @version $Id: OrderedStructure.java 35 2007-08-09 20:38:38Z bailey $
 * @author, 2001 duane a. bailey
 */
public interface OrderedStructure<K extends Comparable<K>>
         extends Structure<K>
{
}
