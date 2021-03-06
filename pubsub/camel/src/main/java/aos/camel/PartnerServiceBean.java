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
package aos.camel;

import org.apache.camel.language.XPath;

/**
 * @version $Revision: 19 $
 */
public class PartnerServiceBean {

    public String toSql(@XPath("partner/@id") int partnerId,
                        @XPath("partner/date/text()") String date,
                        @XPath("partner/code/text()") int statusCode,
                        @XPath("partner/time/text()") long responsTime) {

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO PARTNER_METRIC (partner_id, time_occurred, status_code, perf_time) VALUES (");
        sb.append("'").append(partnerId).append("', ");
        sb.append("'").append(date).append("', ");
        sb.append("'").append(statusCode).append("', ");
        sb.append("'").append(responsTime).append("') ");

        return sb.toString();
    }
}
