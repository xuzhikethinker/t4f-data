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

package aos.t4f.activemq.web;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * A simple bootstrap class for starting Jetty in your IDE using the local web
 * application.
 * 
 * @version $Revision: 565003 $
 */
public final class ActiveMQWebMain {

	public static final int PORT = 8080;

	public static final String WEBAPP_DIR = "src/main/webapp";

	public static final String WEBAPP_CTX = "/";

	private ActiveMQWebMain() {
	}

	public static void main(String... args) throws Exception {

		// now lets start the web server
		int port = PORT;
		if (args.length > 0) {
			String text = args[0];
			port = Integer.parseInt(text);
		}
		System.out.println("Starting Web Server on port: " + port);
		Server server = new Server();
		ServerConnector connector = new ServerConnector(server);
		connector.setPort(port);
		WebAppContext context = new WebAppContext();

		context.setResourceBase(WEBAPP_DIR);
		context.setContextPath(WEBAPP_CTX);
		context.setServer(server);
		server.setHandler(context);
		server.setConnectors(new Connector[] { connector });
		server.start();

		System.out.println();
		System.out.println("==============================================================================");
		System.out.println("Started the ActiveMQ Console: point your web browser at http://localhost:" + port + "/");
		System.out.println("==============================================================================");
		System.out.println();
	}
}
