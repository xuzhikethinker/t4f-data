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
package aos.lucene.delete;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.junit.Test;

import aos.lucene.helper.AosIndexUtil;

public class DeleteTest {
    private static final Logger LOGGER = LogManager.getLogger(DeleteTest.class);

    @Test
    public void test() throws IOException {

        IndexWriter writer = AosIndexUtil.newIndexWithDocuments();
        IndexReader reader = DirectoryReader.open(writer, true);

        Term term = new Term("path", "value");
        writer.deleteDocuments(term);

        LOGGER.info("deleted  documents containing " + term);

        // one can also delete documents by their internal id:
        for (int i = 0; i < reader.maxDoc(); i++) {
            LOGGER.info("Deleting document with id " + i);
            // writer.delete(i);
        }

        reader.close();
        writer.close();

    }

}
