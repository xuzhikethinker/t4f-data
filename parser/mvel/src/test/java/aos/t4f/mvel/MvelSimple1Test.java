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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.mvel2.MVEL;

public class MvelSimple1Test {

    @Test
    public void test1() {
        String expression = "foobar > 99";
        Map<String, Integer> vars = new HashMap<String, Integer>();
        vars.put("foobar", new Integer(100));
        Boolean result = (Boolean) MVEL.eval(expression, vars);
        Assert.assertEquals(true, result.booleanValue());
    }

    @Test
    public void test2() {
        String expression = "foobar > 99";
        Serializable compiled = MVEL.compileExpression(expression);
        Map<String, Integer> vars = new HashMap<String, Integer>();
        vars.put("foobar", new Integer(100));
        Boolean result = (Boolean) MVEL.executeExpression(compiled, vars);
        Assert.assertEquals(true, result.booleanValue());
    }

}
