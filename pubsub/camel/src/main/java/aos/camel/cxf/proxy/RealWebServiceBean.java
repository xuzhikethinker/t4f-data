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
package aos.camel.cxf.proxy;

import javax.xml.ws.Endpoint;

/**
 * A bean to start and stop the real webservice.
 * <p/>
 * This implementation uses pure JAX-WS (no CXF, no Camel).
 *
 * @version $Revision: 1001408 $
 */
public class RealWebServiceBean {

    // should be the same address as we have in our route
    private String url = "http://localhost:9081/real-webservice";

    private ReportIncidentEndpointService service = new ReportIncidentEndpointService();
    private Endpoint endpoint;

    public void start() throws Exception {
        System.out.println("Starting real web service...");
        endpoint = Endpoint.publish(url, service);
        System.out.println("Started real web service at: " + url);
    }

    public void stop() throws Exception {
        if (endpoint != null) {
            System.out.println("Stopping real webservice...");
            endpoint.stop();
            System.out.println("Stopped real webservice.");
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
