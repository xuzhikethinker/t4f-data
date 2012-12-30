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
package aos.t4f.mvel;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Ignore;
import org.junit.Test;

import aos.t4f.mvel.script.AbstractMvelScriptTest;

public class MvelScriptSwingTest extends AbstractMvelScriptTest {

    @Test
    @Ignore
    public void testShell() throws IOException, InterruptedException, ClassNotFoundException {
        MvelShell.main(getScriptPath("swing.mvel"));
        System.out.print("Hey Guy, you have 10 seconds to look at the displayed Swing Frame");
        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.print(".");
        }

        System.out.println();
    }

}
