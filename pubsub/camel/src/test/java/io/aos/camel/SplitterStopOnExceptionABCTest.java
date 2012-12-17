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

import org.apache.camel.CamelExchangeException;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

/**
 * The Splitter using its build in Aggregator example.
 * <p/>
 * This example will split a message into 3 message each containing the letters A, F and C.
 * Each of those message is then translated into a quote using the {@link WordTranslateBean} bean.
 * The Splitter will then aggregate those messages into a single combined outgoing message.
 * This is done using the {@link MyAggregationStrategy}.
 * <p/>
 * In this example we see what happens when one of the splitted message fails with an exception.
 *
 * @version $Revision: 135 $
 */
public class SplitterStopOnExceptionABCTest extends CamelTestSupport {

    @Test
    public void testSplitStopOnException() throws Exception {
        MockEndpoint split = getMockEndpoint("mock:split");
        // we expect 1 messages to be split since the 2nd message should fail
        split.expectedBodiesReceived("Camel rocks");

        // and no combined aggregated message since we stop on exception
        MockEndpoint result = getMockEndpoint("mock:result");
        result.expectedMessageCount(0);

        // now send a message with an unknown letter (F) which forces an exception to occur
        try {
            template.sendBody("direct:start", "A,F,C");
            fail("Should have thrown an exception");
        } catch (CamelExecutionException e) {
            CamelExchangeException cause = assertIsInstanceOf(CamelExchangeException.class, e.getCause());
            IllegalArgumentException iae = assertIsInstanceOf(IllegalArgumentException.class, cause.getCause());
            assertEquals("Key not a known word F", iae.getMessage());
        }

        assertMockEndpointsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                    // tell Splitter to use the aggregation strategy
                    .split(body(), new MyAggregationStrategy())
                        // configure the splitter to stop on exception
                        .stopOnException()
                        // log each splitted message
                        .log("Split line ${body}")
                        // and have them translated into a quote
                        .bean(WordTranslateBean.class)
                        // and send it to a mock
                        .to("mock:split")
                    .end()
                    // log the outgoing aggregated message
                    .log("Aggregated ${body}")
                    // and send it to a mock as well
                    .to("mock:result");
            }
        };
    }
}
