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
package io.aos.camel;

import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The XML example for using the Aggregator EIP.
 * <p/>
 * This example have XML messages send to the aggregator to demonstrate
 * using XPath for correlation expression. And we have two completion
 * conditions which is based on size and timeout
 * <p/>
 * See the class {@link MyAggregationStrategy} for how the messages
 * are actually aggregated together.
 *
 * @see io.aos.camel.MyAggregationStrategy
 * @version $Revision: 117 $
 */
public class SpringAggregateXMLTest extends CamelSpringTestSupport {

    @Override
    protected AbstractXmlApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("META-INF/spring/aggregate-xml.xml");
    }

    @Test
    public void testXML() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMessageCount(2);

        template.sendBody("direct:start", "<order name=\"motor\" amount=\"1000\" customer=\"honda\"/>");
        template.sendBody("direct:start", "<order name=\"motor\" amount=\"500\" customer=\"toyota\"/>");
        template.sendBody("direct:start", "<order name=\"gearbox\" amount=\"200\" customer=\"toyota\"/>");

        assertMockEndpointsSatisfied();
    }

}
