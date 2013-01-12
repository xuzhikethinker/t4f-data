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
package aos.server.db.driver;

import org.apache.derby.jdbc.ClientDriver;
import org.apache.derby.jdbc.EmbeddedDriver;

/**
 * DERBY -----
 * 
 * jdbc:derby:[subprotocol:][databaseName][;attribute=value]*
 * 
 * Embedded org.apache.derby.jdbc.EmbeddedDriver in derby.jar
 * jdbc:derby:database-name;create=true
 * 
 * Embedded Directory org.apache.derby.jdbc.EmbeddedDriver in derby.jar
 * jdbc:derby:directory:/var/store/derby;create=true
 * 
 * Embedded Memory org.apache.derby.jdbc.EmbeddedDriver in derby.jar
 * jdbc:derby:memory:MyDB;create=true
 * 
 * Network org.apache.derby.jdbc.ClientDriver in derbyclient.jar
 * jdbc:derby://localhost:1527/sample;user=judy;password=no12see
 * jdbc:derby://localhost
 * :1527/c:/my-db-dir/my-db-name;user=judy;password=no12see
 * jdbc:derby:net://localhost
 * :1621/DBAOS;create=true;upgrade=true;retrieveMessagesFromServerOnGetMessage
 * =true;deferPrepares=true;
 * 
 * H2 -- inmemory MySql compatible: jdbc:h2:mem:test;MODE=MySQL
 * 
 * MySQL -----
 * 
 * http://dev.mysql.com/doc/refman/5.0/en/connector-j-reference-configuration-
 * properties.html
 * 
 * com.mysql.jdbc.Driver com.mysql.jdbc.jdbc2.optional.MysqlDataSource
 * com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource
 * 
 * jdbc:mysql://[host][,failoverhost...][:port]/[database] »
 * [?propertyName1][=propertyValue1][&propertyName2][=propertyValue2]...
 * 
 * jdbc:mysql://localhost:3306/sakila?profileSQL=true
 */
public interface JDBCDriver {

    // Embedded
    // ========
    // org.apache.derby.jdbc.EmbeddedDriver in derby.jar
    // jdbc:derby:database-name;create=true
    public static final Class DERBY_JDBC_EMBEDDED_CLASS = EmbeddedDriver.class;

    // Embedded Directory
    // ==================
    // org.apache.derby.jdbc.EmbeddedDriver in derby.jar
    // jdbc:derby:directory:C:\aos\apache\james\server\container-spring\target\james-server-container-spring-3.0-M3-SNAPSHOT-bin\james-server-container-spring-3.0-M3-SNAPSHOT\var\store\derby
    public static final Class DERBY_JDBC_EMBEDDED_DIRECTORY_CLASS = EmbeddedDriver.class;

    // Embedded Memory
    // ===============
    // org.apache.derby.jdbc.EmbeddedDriver in derby.jar
    // jdbc:derby:memory:MyDB;create=true
    public static final Class DERBY_JDBC_EMBEDDED_MEMORY_CLASS = EmbeddedDriver.class;

    // Network
    // =======
    // org.apache.derby.jdbc.ClientDriver in derbyclient.jar
    // jdbc:derby://localhost:1527/sample;user=judy;password=no12see
    // jdbc:derby://localhost:1527/c:/my-db-dir/my-db-name;user=judy;password=no12see
    // jdbc:derby:net://localhost:1621/DBAOS;create=true;upgrade=true;retrieveMessagesFromServerOnGetMessage=true;deferPrepares=true;
    public static final Class DERBY_JDBC_NETTWORK_CLASS = ClientDriver.class;

}
