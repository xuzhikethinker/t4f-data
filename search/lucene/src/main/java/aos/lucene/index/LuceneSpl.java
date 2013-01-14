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

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;

public class LuceneSpl {

    public static void main(String... args) {
        try {
            IndexFile("/Users/androidyou/Documents/lucence/data/test.txt",
                    "/Users/androidyou/Documents/lucence/index");
            Search("/Users/androidyou/Documents/lucence/index","nonexistedkeyworld");
            Search("/Users/androidyou/Documents/lucence/index","apache");

        } catch (Exception e) {
            // TODO Auto-generated catch block
        }
        System.out.println("done");
    }

    private static void Search(String indexpath, String keyword) throws Exception, IOException {
        IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(indexpath)));
        IndexSearcher searcher = new IndexSearcher(reader);
        System.out.println("Search  keyword " + keyword);
        Query query=new QueryParser(Version.LUCENE_40, "content", new StandardAnalyzer(Version.LUCENE_40)).parse(keyword);

        TopDocs docs= searcher.search(query, 10);
        System.out.println("hits " + docs.totalHits);
        for(ScoreDoc doc: docs.scoreDocs)
        {
            System.out.println("doc id" + doc.doc + "doc filename" + searcher.doc(doc.doc).get("filename")) ;
        }

    }

    private static void IndexFile(String datafolder, String indexfolder) throws CorruptIndexException, LockObtainFailedException, IOException {
        Analyzer a=new StandardAnalyzer(Version.LUCENE_40);
        Directory d=FSDirectory.open(new File(indexfolder));
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, new StandardAnalyzer(Version.LUCENE_40));
        IndexWriter indexWriter = new IndexWriter(d, config);

        Document doc=new Document();
//        Fieldable contentfield=new Field("content", new FileReader(datafolder));
//        doc.add(contentfield);
//        Fieldable namefield=new Field("filename",datafolder, Store.YES, Index.NOT_ANALYZED);
//        doc.add(namefield);

        indexWriter.addDocument(doc);
        indexWriter.commit();

    }

}
