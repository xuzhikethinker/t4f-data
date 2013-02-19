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
package aos.lucene2.delete;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * Deletes documents from an index that do not contain a term.
 */
public class DeleteFiles {
    private static final Logger LOGGER = LogManager.getLogger(DeleteFiles.class);

    private DeleteFiles() {
    } // singleton

    /** Deletes documents from an index that do not contain a term. */
    public static void main(String... args) {

        String usage = "java org.apache.lucene.demo.DeleteFiles <unique_term>";

        if (args.length == 0) {
            System.err.println("Usage: " + usage);
            System.exit(1);
        }

        try {

            Directory directory = FSDirectory.open(new File("index"));
            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_41, new StandardAnalyzer(Version.LUCENE_41));
            IndexWriter writer = new IndexWriter(directory, config);

            IndexReader reader = DirectoryReader.open(FSDirectory.open(new File("index")));

            Term term = new Term("path", args[0]);
            writer.deleteDocuments(term);

            // LOGGER.info("deleted " + deleted + " documents containing " +
            // term);

            // one can also delete documents by their internal id:
            /*
             * for (int i = 0; i < reader.maxDoc(); i++) {
             * LOGGER.info("Deleting document with id " + i); reader.delete(i);
             * }
             */

            reader.close();
            directory.close();

        }
        catch (Exception e) {
            LOGGER.info(" caught a " + e.getClass() + "\n with message: " + e.getMessage());
        }
    }

}
