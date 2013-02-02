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
package aos.nlp.regexp;

import org.junit.Test;

public class StringRegularExpressionTest {

    @Test
    public void testReplace() {
        String test = new String("lkfjdlkdfjqlkf<dc:creator");
        test = test.replaceAll("creator", "creator1");
        System.out.println(test);
        test = test.replaceAll("<dc:creator1", "<author");
        System.out.println(test);
    }

    @Test
    public void testStartWth() {
        String test = new String("/aos/test");
        System.out.println(test);
        System.out.println(test.startsWith("/aosd/"));
        System.out.println(test.startsWith("/aos/"));
        System.out.println(test.startsWith("//aos//"));
    }

}
