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

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mvel2.MVEL;
import org.mvel2.compiler.ExecutableAccessor;

public class MvelSimple2Test {

    @Test
    public void test() {

        Person personInst = new Person();
        personInst.setName("Mr. Foo");

        Object result = MVEL.eval("name == 'Mr. Foo'", personInst);

        String result2 = (String) MVEL.eval("name", personInst);
        assert "Mr. Foo".equals(result2);

        Map<String, Integer> vars = new HashMap<String, Integer>();
        vars.put("x", new Integer(5));
        vars.put("y", new Integer(10));

        Integer result3 = (Integer) MVEL.eval("x * y", vars);
        assert result3.intValue() == 50; // Mind the JDK 1.4 compatible code :)

        // The compiled expression is serializable and can be cached for re-use.
        ExecutableAccessor compiled = (ExecutableAccessor) MVEL.compileExpression("x * y");

        Map<String, Integer> vars2 = new HashMap<String, Integer>();
        vars2.put("x", new Integer(5));
        vars2.put("y", new Integer(10));

        // Executes the compiled expression
        Integer result4 = (Integer) MVEL.executeExpression(compiled, vars);
        assert result4.intValue() == 50;

    }

}

class Person {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
