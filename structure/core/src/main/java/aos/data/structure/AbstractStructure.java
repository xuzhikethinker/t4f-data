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
// A basic, abstract implementation of a Structure.
// (c) 2001 duane a. bailey
package aos.data.structure;
import java.util.Collection;
import java.util.Iterator;

/**
 * An abstract implementation of a basic, mutable data structure.
 * <p>
 * This abstract implementation of the <code>Structure</code> interface
 * provides a good starting point for the implementation of a basic,
 * mutable data structure.  This implementation provides a workable
 * implementation of <code>isEmpty</code>, <code>contains</code>, and
 * <code>values</code>.
 * <p>
 * Where more efficient implementations are possible, the user may wish
 * to override these methods.  For example, an implementor may have
 * a structure directly implement the <code>java.util.Collection</code>
 * interface and have the <code>value</code> method simply return
 * <code>this</code>.  Because of peculiarities of both systems of
 * designing data structures, it is often best to avoid direct implementation
 * <code>java.util.Collection</code> and <code>Structure</code> in one class.
 *
 * @author, 2001 duane a. bailey
 * @version $Id: AbstractStructure.java 8 2006-08-02 19:03:11Z bailey $
 * @since Java Structures, 2nd edition
 */
public abstract class AbstractStructure implements Structure
{
    /**
     * The default constructor.  Initializes any internal variables.
     *
     * @post initializes internal variables
     */
    public AbstractStructure()
    {
    }

    /**
     * Determine if there are elements within the structure.
     *
     * @post return true iff the structure is empty
     * @return true if the structure is empty; false otherwise
     */
    public boolean isEmpty()
    {
        return size() == 0;
    }

    /**
     * Return an enumeration associated with this structure.
     * This implementation returns an AbstractIterator which supports
     * both enumeration at iterator techniques.
     *
     * @pre this implementation assumes the structure returns an
     *      AbstractIterator, which may then be used for generating the
     *      Enumeration.
     * @post return an enumeration for traversing the struture;
     *       all <code>structure</code> package implementations return
     *       an <code>AbstractIterator</code>
     * @return a Enumeration for traversing the structure
     * @see AbstractIterator
     * @see java.util.Iterator
     * @see java.util.Enumeration
     */
    public java.util.Enumeration elements()
    {
        return (AbstractIterator)iterator();
    }

    /**
     * Determines if the structure contains a value.
     *
     * @param value non-null value to be found within structure
     * @pre value is non-null
     * @post returns true iff value.equals some value in structure
     * @return true when some value equals value
     */
    public boolean contains(Object value)
    {
        Iterator i = iterator();
        while (i.hasNext())
        {
            if (i.next().equals(value)) return true;
        }
        return false;
    }

    /**
     * @post generate a hashcode for the structure: sum of
     * all the hash codes of elements
     */
    public int hashCode()
    {
        Iterator i = iterator();
        int result = 0;
        while (i.hasNext())
        {
            Object o = i.next();
            if (o != null) result += o.hashCode();
        }
        return result;
    }

    /**
     * Returns a java.util.Collection wrapping this structure.
     * This particular implementation returns a <code>StructCollection</code>
     * whose methods may not provide the most efficent implementations
     * of non-<code>Structure</code> <code>Collection</code> methods.
     *
     * @post returns a <code>Collection</code> that may be used with
     *       Java's Collection Framework
     * @return a Collection that is equivalent to this structure
     * @see aos.data.structure.StructCollection
     * @see java.util.Collection
     */
    public Collection values()
    {
        return new StructCollection(this);
    }
}
