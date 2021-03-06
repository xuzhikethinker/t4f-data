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
package aos.data.structure;
import java.util.Map;
public class Entry implements java.util.Map.Entry
{
    protected Object theKey; // the key of the key-value pair
    /**
     * The mutable value.  An arbitrary object.
     */
    protected Object theValue; // the value of the key-value pair

    /**
     * Constructs a pair from a key and value.
     *
     * @pre Key is non-null
     * @post Constructs a key-value pair
     * 
     * @param key A non-null object.
     * @param value A (possibly null) object.
     */
    public Entry(Object key, Object value)
    {
        Assert.pre(key != null, "Key must not be null.");
        theKey = key;
        theValue = value;
    }

    /**
     * Constructs a pair from a key; value is null.
     *
     * @pre Key is non-null
     * @post Constructs a key-value pair
     * 
     * @param key A non-null key value.
     */
    public Entry(Object key)
    {
        this(key,null);
    }

    /**
     * Standard comparison function.  Comparison based on keys only.
     *
     * @pre Other is non-null Entry
     * @post Returns true iff the keys are equal
     * 
     * @param other Another association.
     * @return True iff the keys are equal.
     */
    public boolean equals(Object other)
    {
        Map.Entry otherEntry = (Map.Entry)other;
        return getKey().equals(otherEntry.getKey());
    }
    
    /**
     * Standard hashcode function.
     *
     * @post Return hash code association with this association
     * 
     * @return A hash code for association.
     * @see Hashtable
     */
    public int hashCode()
    {
        return getKey().hashCode();
    }
    
    /**
     * Fetch value from association.  May return null.
     *
     * @post Returns value from association
     * 
     * @return The value field of the association.
     */
    public Object getValue()
    {
        return theValue;
    }

    /**
     * Fetch key from association.  Should not return null.
     *
     * @post Returns key from association
     * 
     * @return Key of the key-value pair.
     */
    public Object getKey()
    {
        return theKey;
    }

    /**
     * Sets the value of the key-value pair.
     *
     * @post Sets association's value to value
     * 
     * @param value The new value.
     */
    public Object setValue(Object value)
    {
        Object result = theValue;
        theValue = value;
        return result;
    }

    /**
     * Standard string representation of an association.
     *
     * @post Returns string representation
     * 
     * @return String representing key-value pair.
     */
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        s.append("<Entry: "+getKey()+"="+getValue()+">");
        return s.toString();
    }
}
