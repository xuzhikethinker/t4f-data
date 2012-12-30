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
package aos.cache.guava;

import static org.junit.Assert.fail;

import java.util.concurrent.ExecutionException;

import org.junit.Test;

import aos.cache.guava.AosGuava;

public class AosGuavaTest {
    private AosGuava aosGuava = new AosGuava();
    private static final String KEY_1 = "key1";
    private static final String VALUE_1 = "value1";
    private static final String KEY_2 = "key2";
    private static final String VALUE_2 = "value2";
    
    @Test
    public void test() throws ExecutionException {
        try {
            aosGuava.get(KEY_1);
            fail();
        }
        catch (ExecutionException e) {
            
        }
        aosGuava.put(KEY_1, VALUE_1);
        aosGuava.get(KEY_1);
    }
    
}
