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
/**
 * A class of error indicating failure.
 *
 * @version $Id: FailedAssertion.java 23 2006-08-21 20:50:47Z bailey $
 * @author, 2001 duane a. bailey
 * @see Assert#fail
 */
class FailedAssertion extends Error
{
    final static long serialVersionUID = 0;
    /**
     * Constructs an error indicating failure to meet condition.
     *
     * @post Constructs a new failed assertion error
     * 
     * @param reason String describing failed condition.
     */
    public FailedAssertion(String reason)
    {
        super("\nAssertion that failed: " + reason);
    }
}
