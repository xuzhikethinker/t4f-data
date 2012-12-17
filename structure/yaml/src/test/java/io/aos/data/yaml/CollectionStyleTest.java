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
package io.aos.data.yaml;

import junit.framework.TestCase;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

public class CollectionStyleTest extends TestCase {
    public void testNestedStyle() {
        Yaml yaml = new Yaml();
        String document = "  a: 1\n  b:\n    c: 3\n    d: 4\n";
        assertEquals("a: 1\nb: {c: 3, d: 4}\n", yaml.dump(yaml.load(document)));
    }

    public void testNestedStyle2() {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);
        String document = "  a: 1\n  b:\n    c: 3\n    d: 4\n";
        assertEquals("a: 1\nb:\n  c: 3\n  d: 4\n", yaml.dump(yaml.load(document)));
    }
}
