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
package aos.lucene.search.ext.payloads;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

public class BulletinPayloadsAnalyzer extends Analyzer {

    private boolean isBulletin;
    private final float boost;

    BulletinPayloadsAnalyzer(float boost) {
        this.boost = boost;
    }

    void setIsBulletin(boolean v) {
        isBulletin = v;
    }

    @Override
    public TokenStream tokenStream(String fieldName, Reader reader) {
        BulletinPayloadsFilter stream = new BulletinPayloadsFilter(new StandardAnalyzer(Version.LUCENE_41).tokenStream(
                fieldName, reader), boost);
        stream.setIsBulletin(isBulletin);
        return stream;
    }

}
