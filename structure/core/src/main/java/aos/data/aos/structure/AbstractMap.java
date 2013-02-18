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
package aos.data.aos.structure;
import java.util.Iterator;

public abstract class AbstractMap implements Map
{
    /**
     * @pre other is a valid map
     * @post adds the map entries of other map into this, possibly
     * replacing value
     */
    public void putAll(Map other)
    {
        Iterator i = other.keySet().iterator();
        while (i.hasNext())
        {
            Object k = i.next();
            put(k,other.get(k));
        }
    }

    /**
     * Compute the hashCode for elements of this map
     */
    public int hashCode()
    {
        return values().hashCode();
    }
}