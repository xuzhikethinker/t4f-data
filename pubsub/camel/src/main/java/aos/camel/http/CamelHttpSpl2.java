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
package aos.camel.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

/**
 *
 */
public class CamelHttpSpl2 {

	public static void main(String... args) throws Exception {

		CamelContext context = new DefaultCamelContext();

		context.start();

		ProducerTemplate template = context.createProducerTemplate();

		Exchange exchange = template.send("http://www.google.com/search",
				new Processor() {
			       public void process(Exchange exchange) throws Exception {
			    	  System.out.println("Adding query header to http request");
				      exchange.getIn().setHeader(Exchange.HTTP_QUERY, "hl=en&q=activemq");
                   }
		});

		Thread.sleep(10000);

		Message out = exchange.getOut();
		int responseCode = out.getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
		System.out.println("responseCode=" + responseCode);

   		HttpServletRequest request = out.getHeader(Exchange.HTTP_SERVLET_REQUEST, HttpServletRequest.class);
		System.out.println("httpRequest=" + request);
   		request = out.getBody(HttpServletRequest.class);
		System.out.println("httpRequest=" + request);
   		HttpServletResponse response = out.getHeader(Exchange.HTTP_SERVLET_RESPONSE, HttpServletResponse.class);
		System.out.println("httpResponse=" + response);
   		response = out.getBody(HttpServletResponse.class);
		System.out.println("httpResponse=" + response);

		context.stop();

	}
	
}
