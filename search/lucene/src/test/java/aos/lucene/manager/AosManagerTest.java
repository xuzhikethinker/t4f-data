package aos.lucene.manager;

import java.io.IOException;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.ReaderManager;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NRTManager;
import org.apache.lucene.search.NRTManager.TrackingIndexWriter;
import org.apache.lucene.search.SearcherFactory;
import org.apache.lucene.search.SearcherManager;
import org.junit.Test;

import aos.lucene.helper.AosDirectory;
import aos.lucene.helper.AosIndexUtil;

public class AosManagerTest {

    @Test
    public void testReaderManager() throws IOException {
        AosIndexUtil.newIndexWithDocuments();
        ReaderManager readerManager = new ReaderManager(AosIndexUtil.directory());
        IndexReader indexReader = null;
        try {
            indexReader = readerManager.acquire();
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        }
        finally {
            if (indexReader == null) {
                readerManager.release(DirectoryReader.open(AosIndexUtil.directory()));
            }
        }
    }

    @Test
    public void testSearcherManager() throws IOException {
        AosIndexUtil.newIndexWithDocuments();
        SearcherManager searcherManager = new SearcherManager(AosDirectory.newDirectory(), new SearcherFactory());
        IndexSearcher indexSearcher = null;
        try {
            indexSearcher = searcherManager.acquire();
        }
        finally {
            if (indexSearcher == null) {
                searcherManager.release(indexSearcher);
            }
        }
    }

    @Test
    public void testNrtManager() throws IOException {
        TrackingIndexWriter trackingIndexWriter = new TrackingIndexWriter(AosIndexUtil.newIndexWithDocuments());
        NRTManager nrtManager = new NRTManager(trackingIndexWriter, new SearcherFactory());
        IndexSearcher indexSearcher = null;
        try {
            indexSearcher = nrtManager.acquire();
        }
        finally {
            if (indexSearcher == null) {
                nrtManager.release(indexSearcher);
            }
        }
    }

}
