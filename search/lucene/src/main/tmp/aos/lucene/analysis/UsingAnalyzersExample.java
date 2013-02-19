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
package aos.lucene.analysis;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

public class UsingAnalyzersExample {

    /**
     * This method doesn't do anything, except compile correctly. This is used
     * to show snippets of how Analyzers are used.
     */
    public void someMethod() throws IOException, ParseException {
        RAMDirectory directory = new RAMDirectory();

        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_41);
        IndexWriter writer = new IndexWriter(directory, analyzer, IndexWriter.MaxFieldLength.UNLIMITED);

        Document doc = new Document();
        doc.add(new Field("title", "This is the title", Field.Store.YES, Field.Index.ANALYZED));
        doc.add(new Field("contents", "...document contents...", Field.Store.NO, Field.Index.ANALYZED));
        writer.addDocument(doc);

        writer.addDocument(doc, analyzer);

        String expression = "some query";

        Query query = new QueryParser(Version.LUCENE_41, "contents", analyzer).parse(expression);

        QueryParser parser = new QueryParser(Version.LUCENE_41, "contents", analyzer);
        query = parser.parse(expression);
    }
}
