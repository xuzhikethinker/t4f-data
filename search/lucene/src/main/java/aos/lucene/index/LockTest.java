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

import junit.framework.TestCase;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;

import aos.lucene.analyser.AosAnalyser;
import aos.lucene.util.TestUtil;

/**
 * #A Expected exception: only one IndexWriter allowed at once
 */
public class LockTest extends TestCase {

    private Directory dir;
    private File indexDir;

    @Override
    protected void setUp() throws IOException {

        indexDir = new File(System.getProperty("java.io.tmpdir", "tmp") + System.getProperty("file.separator")
                + "index");
        dir = FSDirectory.open(indexDir);

    }

    public void testWriteLock() throws IOException {

        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_50,
                AosAnalyser.NO_LIMIT_TOKEN_COUNT_SIMPLE_ANALYSER);
        IndexWriter writer1 = new IndexWriter(dir, config);

        IndexWriter writer2 = null;
        try {
            writer2 = new IndexWriter(dir, config);
            fail("We should never reach this point");
        }
        catch (LockObtainFailedException e) {
            // e.printStackTrace(); // #A
        }
        finally {
            writer1.close();
            assertNull(writer2);
            TestUtil.rmDir(indexDir);
        }
    }
}
