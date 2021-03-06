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
package aos.camel;


import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import aos.camel.MyComponent;

/**
 * Test using MyComponent
 *
 * @version $Revision: 99 $
 */
public class MyComponentTest extends CamelTestSupport {

    @Test
    public void testMyComponent() throws Exception {
        // add my component to Camel
        context.addComponent("my", new MyComponent());

        // start my component
        context.getComponent("my", MyComponent.class).start();

        System.out.println("Waiting for 10 seconds before we shutdown");
        Thread.sleep(10000);
    }
}
