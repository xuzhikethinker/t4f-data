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
package aos.lucene.index;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import aos.lucene.helper.AosDirectory;
import aos.lucene.helper.AosFieldType;

public class BasicIndexTest {
    private static Directory dir;

    private static String[] keywords = { "1", "2" };
    private static String[] unindexed = { "Netherlands", "Italy" };
    private static String[] unstored = { "Amsterdam has lots of bridges", "Venice has lots of canals" };
    private static String[] text = { "Amsterdam", "Venice" };

    @BeforeClass
    public static void setUp() throws IOException {
        dir = AosDirectory.newDirectory();
        addDocuments(dir);
    }

    @Test
    public void testWriter() throws IOException {
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_41, getAnalyzer());
        IndexWriter writer = new IndexWriter(dir, config);
        Assert.assertEquals(keywords.length, writer.maxDoc());
        writer.close();
    }

    @Test
    public void testReader() throws IOException {
        IndexReader reader = DirectoryReader.open(dir);
        Assert.assertEquals(keywords.length, reader.maxDoc());
        Assert.assertEquals(keywords.length, reader.numDocs());
        reader.close();
    }

    private static void addDocuments(Directory dir) throws IOException {
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_41, getAnalyzer());
        IndexWriter writer = new IndexWriter(dir, config);
        for (int i = 0; i < keywords.length; i++) {
            Document doc = new Document();
            doc.add(new Field("id", keywords[i], AosFieldType.INDEXED_STORED_TERMVECTOR));
            doc.add(new Field("country", unindexed[i], AosFieldType.NOTINDEXED_STORED));
            doc.add(new Field("contents", unstored[i], AosFieldType.INDEXED_NOTSTORED_TERMVECTOR));
            doc.add(new Field("city", text[i], AosFieldType.INDEXED_STORED_TERMVECTOR));
            writer.addDocument(doc);
        }
        writer.forceMerge(1);
        writer.close();
    }

    private static Analyzer getAnalyzer() {
        return new SimpleAnalyzer(Version.LUCENE_41);
    }

}
