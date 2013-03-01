package aos.lucene.manager;

import java.io.IOException;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.ReaderManager;
import org.apache.lucene.search.IndexSearcher;
import org.junit.Test;

import aos.lucene.util.AosIndexUtil;

public class AosManagerTest {

    @Test
    public void test() throws IOException {

        AosIndexUtil.newIndexWithDocuments();
        ReaderManager readerManager = new ReaderManager(AosIndexUtil.directory());

        // NRTManager nrtManager = new NRTManager(AosDirectory.newDirectory());
        // SearcherManager searcherManager = new
        // SearcherManager(AosDirectory.newDirectory());

        IndexReader indexReader = readerManager.acquire();

        try {
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        }
        finally {
            readerManager.release(DirectoryReader.open(AosIndexUtil.directory()));
        }

    }

}
