package aos.lucene2.field;

import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.FieldInfo.IndexOptions;

public class AosFieldType {

    public static final FieldType INDEXED_STORED_TERMVECTOR;
    public static final FieldType NOTINDEXED_STORED;
    public static final FieldType INDEXED_NOTSTORED_TERMVECTOR;
    public static final FieldType NOTINDEXED_NOTSTORED;

    static {

        INDEXED_STORED_TERMVECTOR = new FieldType();
        INDEXED_STORED_TERMVECTOR.setIndexed(true);
        INDEXED_STORED_TERMVECTOR.setStored(true);
        INDEXED_STORED_TERMVECTOR.setStoreTermVectors(true);
        INDEXED_STORED_TERMVECTOR.setStoreTermVectorOffsets(true);
        INDEXED_STORED_TERMVECTOR.setStoreTermVectorPayloads(true);
        INDEXED_STORED_TERMVECTOR.setStoreTermVectorPositions(true);
        INDEXED_STORED_TERMVECTOR.setIndexOptions(IndexOptions.DOCS_AND_FREQS);
        INDEXED_STORED_TERMVECTOR.freeze();

        NOTINDEXED_STORED = new FieldType();
        NOTINDEXED_STORED.setIndexed(false);
        NOTINDEXED_STORED.setStored(true);
        NOTINDEXED_STORED.setStoreTermVectors(false);
        NOTINDEXED_STORED.setStoreTermVectorOffsets(false);
        NOTINDEXED_STORED.setStoreTermVectorPayloads(false);
        NOTINDEXED_STORED.setStoreTermVectorPositions(false);
        NOTINDEXED_STORED.setIndexOptions(IndexOptions.DOCS_ONLY);
        NOTINDEXED_STORED.freeze();

        INDEXED_NOTSTORED_TERMVECTOR = new FieldType();
        INDEXED_NOTSTORED_TERMVECTOR.setIndexed(true);
        INDEXED_NOTSTORED_TERMVECTOR.setStored(false);
        INDEXED_NOTSTORED_TERMVECTOR.setStoreTermVectors(true);
        INDEXED_NOTSTORED_TERMVECTOR.freeze();

        NOTINDEXED_NOTSTORED = new FieldType();
        NOTINDEXED_NOTSTORED.setIndexed(false);
        NOTINDEXED_NOTSTORED.setStored(false);
        NOTINDEXED_NOTSTORED.setStoreTermVectors(false);
        NOTINDEXED_NOTSTORED.freeze();

    }

}
