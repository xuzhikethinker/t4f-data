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
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Fields;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.MultiFields;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class IndexHtml {

    private IndexHtml() {
    }

    private static boolean deleting = false; // true during deletion pass
    private static IndexReader reader; // existing index
    private static IndexWriter writer; // new index being built

    /** Indexer for HTML files. */
    public static void main(String... argv) {
        try {
            File index = new File("index");
            boolean create = false;
            File root = null;

            String usage = "IndexHTML [-create] [-index <index>] <root_directory>";

            if (argv.length == 0) {
                System.err.println("Usage: " + usage);
                return;
            }

            for (int i = 0; i < argv.length; i++) {
                if (argv[i].equals("-index")) { // parse -index option
                    index = new File(argv[++i]);
                }
                else if (argv[i].equals("-create")) { // parse -create option
                    create = true;
                }
                else if (i != argv.length - 1) {
                    System.err.println("Usage: " + usage);
                    return;
                }
                else
                    root = new File(argv[i]);
            }

            if (root == null) {
                System.err.println("Specify directory to index");
                System.err.println("Usage: " + usage);
                return;
            }

            Date start = new Date();

            if (!create) { // delete stale docs
                deleting = true;
                indexDocs(root, index, create);
            }

            IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_50, new StandardAnalyzer(Version.LUCENE_50));

            writer = new IndexWriter(FSDirectory.open(index), conf);

            indexDocs(root, index, create);

            System.out.println("Optimizing index...");
            // writer.optimize();
            writer.close();

            Date end = new Date();

            System.out.print(end.getTime() - start.getTime());
            System.out.println(" total milliseconds");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Walk directory hierarchy in uid order, while keeping uid iterator from /*
     * existing index in sync. Mismatches indicate one of: (a) old documents to
     * /* be deleted; (b) unchanged documents, to be left alone; or (c) new /*
     * documents, to be indexed.
     */

    private static void indexDocs(File file, File index, boolean create) throws Exception {
        if (!create) { // incrementally update
            reader = DirectoryReader.open(FSDirectory.open(index)); // open
                                                                    // existing
                                                                    // index

            Fields fields = MultiFields.getFields(reader);
            Iterator<String> fieldsEnum = fields.iterator();
            // uidIter = reader.terms(new Term("uid", "")); // init uid iterator
            /*
             * uidIter = fieldsEnum.terms();
             */
            indexDocs(file);

            if (deleting) { // delete rest of stale docs
                /*
                 * while (uidIter.term() != null && uidIter.term().field() ==
                 * "uid") { System.out.println("deleting " +
                 * HTMLDocument.uid2url(uidIter.term().text()));
                 * reader.deleteDocuments(uidIter.term()); uidIter.next(); }
                 */
                deleting = false;
            }

            reader.close(); // close existing index

        }

        else {
            indexDocs(file);
        }

    }

    private static void indexDocs(File file) throws Exception {
        if (file.isDirectory()) { // if a directory
            String[] files = file.list(); // list its files
            Arrays.sort(files); // sort the files
            for (int i = 0; i < files.length; i++)
                // recursively index them
                indexDocs(new File(file, files[i]));

        }
        else if (file.getPath().endsWith(".html") || // index .html files
                file.getPath().endsWith(".htm") || // index .htm files
                file.getPath().endsWith(".txt")) { // index .txt files
            /*
             * if (uidIter != null) { String uid = HTMLDocument.uid(file); //
             * construct uid for doc
             * 
             * while (uidIter.term() != null && uidIter.term().field() == "uid"
             * && uidIter.term().text().compareTo(uid) < 0) { if (deleting) { //
             * delete stale docs System.out.println("deleting " +
             * HTMLDocument.uid2url(uidIter.term().text()));
             * reader.deleteDocuments(uidIter.term()); } uidIter.next(); }
             * 
             * if (uidIter.term() != null && uidIter.term().field() == "uid" &&
             * uidIter.term().text().compareTo(uid) == 0) { uidIter.next(); //
             * keep matching docs }
             * 
             * else if (!deleting) { // add new docs Document doc =
             * HTMLDocument.Document(file); System.out.println("adding " +
             * doc.get("path")); writer.addDocument(doc); }
             * 
             * }
             * 
             * else { // creating a new index Document doc =
             * HTMLDocument.Document(file); System.out.println("adding " +
             * doc.get("path")); writer.addDocument(doc); // add docs
             * unconditionally }
             */
        }
    }
}
