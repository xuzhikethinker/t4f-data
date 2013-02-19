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
package aos.lucene2.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.util.StopwordAnalyzerBase;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import aos.lucene2.field.AosFieldType;

/**
 * #1 Get category #2 Pull fields #3 Add fields to Document instance #4 Flag
 * subject field #5 Add catch-all contents field #6 Custom analyzer to override
 * multi-valued position increment
 */
public class CreateTestIndex {
    private static final Logger LOGGER = LogManager.getLogger(CreateTestIndex.class);

    public static void main(String[] args) throws IOException {
        String dataDir = args[0];
        String indexDir = args[1];
        List<File> results = new ArrayList<File>();
        findFiles(results, new File(dataDir));
        LOGGER.info(results.size() + " books to index");
        Directory dir = FSDirectory.open(new File(indexDir));
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_41, new MyStandardAnalyzer(Version.LUCENE_41));
        IndexWriter w = new IndexWriter(dir, conf);
        for (File file : results) {
            Document doc = getDocument(dataDir, file);
            w.addDocument(doc);
        }
        w.close();
        dir.close();
    }

    public static Document getDocument(String rootDir, File file) throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream(file));

        Document doc = new Document();

        // category comes from relative path below the base directory
        String category = file.getParent().substring(rootDir.length());
        category = category.replace(File.separatorChar, '/');

        String isbn = props.getProperty("isbn");
        String title = props.getProperty("title");
        String author = props.getProperty("author");
        String url = props.getProperty("url");
        String subject = props.getProperty("subject");

        String pubmonth = props.getProperty("pubmonth");

        LOGGER.info(title + "\n" + author + "\n" + subject + "\n" + pubmonth + "\n" + category + "\n---------");

        doc.add(new StoredField("isbn", isbn));
        doc.add(new StoredField("category", category));
        doc.add(new Field("title", title, AosFieldType.INDEXED_STORED_TERMVECTOR));
        doc.add(new Field("title2", title.toLowerCase(), AosFieldType.INDEXED_STORED_TERMVECTOR));

        // split multiple authors into unique field instances
        String[] authors = author.split(",");
        for (String a : authors) {
            doc.add(new Field("author", a, AosFieldType.INDEXED_STORED_TERMVECTOR));
        }

        doc.add(new StoredField("url", url));
        doc.add(new Field("subject", subject, AosFieldType.INDEXED_NOTSTORED_TERMVECTOR));

        doc.add(new StoredField("pubmonth", Integer.parseInt(pubmonth)));

        Date d;
        try {
            d = DateTools.stringToDate(pubmonth);
        }
        catch (ParseException pe) {
            throw new RuntimeException(pe);
        }
        doc.add(new StoredField("pubmonthAsDay", (int) (d.getTime() / (1000 * 3600 * 24))));

        for (String text : new String[] { title, subject, author, category }) {
            doc.add(new Field("contents", text, AosFieldType.INDEXED_NOTSTORED_TERMVECTOR));
        }

        return doc;
    }

    private static String aggregate(String[] strings) {
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < strings.length; i++) {
            buffer.append(strings[i]);
            buffer.append(" ");
        }

        return buffer.toString();
    }

    private static void findFiles(List<File> result, File dir) {
        for (File file : dir.listFiles()) {
            if (file.getName().endsWith(".properties")) {
                result.add(file);
            }
            else if (file.isDirectory()) {
                findFiles(result, file);
            }
        }
    }

    private static class MyStandardAnalyzer extends StopwordAnalyzerBase {

        /** Default maximum allowed token length */
        public static final int DEFAULT_MAX_TOKEN_LENGTH = 255;

        private final int maxTokenLength = DEFAULT_MAX_TOKEN_LENGTH;

        public MyStandardAnalyzer(Version matchVersion) {
            super(matchVersion);
        }

        @Override
        public int getPositionIncrementGap(String field) {
            if (field.equals("contents")) {
                return 100;
            }
            else {
                return 0;
            }
        }

        @Override
        protected TokenStreamComponents createComponents(final String fieldName, final Reader reader) {
            final StandardTokenizer src = new StandardTokenizer(matchVersion, reader);
            src.setMaxTokenLength(maxTokenLength);
            TokenStream tok = new StandardFilter(matchVersion, src);
            tok = new LowerCaseFilter(matchVersion, tok);
            tok = new StopFilter(matchVersion, tok, stopwords);
            return new TokenStreamComponents(src, tok) {
                @Override
                protected void setReader(final Reader reader) throws IOException {
                    src.setMaxTokenLength(MyStandardAnalyzer.this.maxTokenLength);
                    super.setReader(reader);
                }
            };
        }

    }

}
