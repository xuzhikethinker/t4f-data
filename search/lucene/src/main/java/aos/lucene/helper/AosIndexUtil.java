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
package aos.lucene.helper;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;

public class AosIndexUtil {

    public static final String FIELD_ID = "id";
    public static final String FIELD_CITY = "city";
    public static final String FIELD_COUNTRY = "country";
    public static final String FIELD_CONTENT = "content";

    private static String[] keywords = { "1", "2", "3" };
    private static String[] unindexed = { "Netherlands", "Italy", "Italy" };
    private static String[] unstored = { "Amsterdam has lots of bridges",
            "Venice has lots of canals, yes yes yes canals", "Venice has lots of canals" };
    private static String[] city = { "Amsterdam", "Venice", "Venice" };

    private static Directory indexDirectory;

    static {
        try {
            indexDirectory = AosDirectory.newDirectory();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static IndexWriter newIndexWithDocuments() throws IOException {
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_41, getAnalyzer());
        IndexWriter writer = new IndexWriter(indexDirectory, config);
        for (int i = 0; i < keywords.length; i++) {
            Document doc = new Document();
            doc.add(new Field(FIELD_ID, keywords[i], AosFieldType.INDEXED_STORED_TERMVECTOR));
            doc.add(new Field(FIELD_COUNTRY, unindexed[i], AosFieldType.NOTINDEXED_STORED));
            doc.add(new Field(FIELD_CITY, city[i], AosFieldType.INDEXED_STORED_TERMVECTOR));
            doc.add(new Field(FIELD_CONTENT, unstored[i], AosFieldType.INDEXED_NOTSTORED_TERMVECTOR));
            writer.addDocument(doc);
        }
        writer.commit();
        return writer;
    }

    public static Directory directory() {
        return indexDirectory;
    }

    private static Analyzer getAnalyzer() {
        return new SimpleAnalyzer(Version.LUCENE_41);
    }

}
