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

import junit.framework.TestCase;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import aos.lucene.analyser.AosAnalyser;
import aos.lucene.field.AosFieldType;
import aos.lucene.util.TestUtil;

/**
 * #1 One initial document has bridges #2 Create writer with maxFieldLength 1 #3
 * Index document with bridges #4 Document can't be found
 */
public class IndexTest extends TestCase {
    protected String[] ids = { "1", "2" };
    protected String[] unindexed = { "Netherlands", "Italy" };
    protected String[] unstored = { "Amsterdam has lots of bridges", "Venice has lots of canals" };
    protected String[] text = { "Amsterdam", "Venice" };

    private Directory directory;

    @Override
    protected void setUp() throws Exception {

        directory = new RAMDirectory();

        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_50,
                AosAnalyser.NO_LIMIT_TOKEN_COUNT_WHITE_SPACE_ANALYSER);

        IndexWriter writer = new IndexWriter(directory, conf);

        for (int i = 0; i < ids.length; i++) {
            Document doc = new Document();
            doc.add(new Field("id", ids[i], AosFieldType.INDEXED_STORED_TERMVECTOR));
            doc.add(new Field("country", unindexed[i], AosFieldType.NOTINDEXED_STORED));
            doc.add(new Field("contents", unstored[i], AosFieldType.INDEXED_NOTSTORED_TERMVECTOR));
            doc.add(new Field("city", text[i], AosFieldType.INDEXED_STORED_TERMVECTOR));
            writer.addDocument(doc);
        }
        writer.close();
    }

    protected int getHitCount(String fieldName, String searchString) throws IOException {
        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);
        Term t = new Term(fieldName, searchString);
        Query query = new TermQuery(t);
        int hitCount = TestUtil.hitCount(searcher, query);
        reader.close();
        return hitCount;
    }

    public void testIndexWriter() throws IOException {
        IndexWriter writer = getWriter();
        assertEquals(ids.length, writer.numDocs());
        writer.close();
    }

    public void testIndexReader() throws IOException {
        IndexReader reader = DirectoryReader.open(directory);
        assertEquals(ids.length, reader.maxDoc());
        assertEquals(ids.length, reader.numDocs());
        reader.close();
    }

    /*
     * #1 Run before every test #2 Create IndexWriter #3 Add documents #4 Create
     * new searcher #5 Build simple single-term query #6 Get number of hits #7
     * Verify writer document count #8 Verify reader document count
     */
    public void testDeleteBeforeOptimize() throws IOException {
        IndexWriter writer = getWriter();
        assertEquals(2, writer.numDocs());
        writer.deleteDocuments(new Term("id", "1"));
        writer.commit();
        assertTrue(writer.hasDeletions());
        assertEquals(2, writer.maxDoc());
        assertEquals(1, writer.numDocs());
        writer.close();
    }

    public void testDeleteAfterOptimize() throws IOException {
        IndexWriter writer = getWriter();
        assertEquals(2, writer.numDocs());
        writer.deleteDocuments(new Term("id", "1"));
        // writer.optimize();
        writer.commit();
        assertFalse(writer.hasDeletions());
        assertEquals(1, writer.maxDoc());
        assertEquals(1, writer.numDocs());
        writer.close();
    }

    /**
     * #A 2 docs in the index #B Delete first document #C 1 indexed document, 0
     * deleted documents #1 Index contains deletions #2 1 indexed document, 1
     * deleted document #3 Optimize compacts deletes
     */
    public void testUpdate() throws IOException {

        assertEquals(1, getHitCount("city", "Amsterdam"));

        IndexWriter writer = getWriter();

        Document doc = new Document();
        doc.add(new StoredField("id", "1"));
        doc.add(new StoredField("country", "Netherlands"));
        doc.add(new Field("contents", "Den Haag has a lot of museums", AosFieldType.INDEXED_NOTSTORED_TERMVECTOR));
        doc.add(new Field("city", "Den Haag", AosFieldType.INDEXED_NOTSTORED_TERMVECTOR));

        writer.updateDocument(new Term("id", "1"), doc);
        writer.close();

        assertEquals(0, getHitCount("city", "Amsterdam"));
        assertEquals(1, getHitCount("city", "Haag"));
    }

    /**
     * #A Create new document with "Haag" in city field #B Replace original
     * document with new version #C Verify old document is gone #D Verify new
     * document is indexed
     */
    public void testMaxFieldLength() throws IOException {

        assertEquals(1, getHitCount("contents", "bridges"));

        IndexWriter writer = new IndexWriter(directory, new IndexWriterConfig(Version.LUCENE_50,
                AosAnalyser.ONE_TOKEN_COUNT_WHITE_SPACE_ANALYSER));
        Document doc = new Document();
        doc.add(new Field("contents", "these bridges can't be found", AosFieldType.INDEXED_NOTSTORED_TERMVECTOR));
        writer.addDocument(doc);
        writer.close();
        assertEquals(1, getHitCount("contents", "bridges"));
    }

    private IndexWriter getWriter() throws IOException {
        return new IndexWriter(directory, new IndexWriterConfig(Version.LUCENE_50,
                AosAnalyser.NO_LIMIT_TOKEN_COUNT_WHITE_SPACE_ANALYSER));
    }

}
