package aos.lucene.field;

import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.FieldInfo.IndexOptions;

public class AosFieldType {

    public static final FieldType INDEXED_STORED_TERMVECTOR;
    public static final FieldType NOTINDEXED_STORED_TERMVECTOR;
    public static final FieldType INDEXED_NOTSTORED_TERMVECTOR;
    public static final FieldType NOTINDEXED_NOTSTORED_TERMVECTOR;

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

        NOTINDEXED_STORED_TERMVECTOR = new FieldType();
        NOTINDEXED_STORED_TERMVECTOR.setIndexed(false);
        NOTINDEXED_STORED_TERMVECTOR.setStored(true);
        NOTINDEXED_STORED_TERMVECTOR.setStoreTermVectors(true);
        NOTINDEXED_STORED_TERMVECTOR.setStoreTermVectorOffsets(true);
        NOTINDEXED_STORED_TERMVECTOR.setStoreTermVectorPayloads(true);
        NOTINDEXED_STORED_TERMVECTOR.setStoreTermVectorPositions(true);
        NOTINDEXED_STORED_TERMVECTOR.setIndexOptions(IndexOptions.DOCS_AND_FREQS);
        NOTINDEXED_STORED_TERMVECTOR.freeze();

        INDEXED_NOTSTORED_TERMVECTOR = new FieldType();
        INDEXED_NOTSTORED_TERMVECTOR.setIndexed(true);
        INDEXED_NOTSTORED_TERMVECTOR.setStored(false);
        INDEXED_NOTSTORED_TERMVECTOR.setStoreTermVectors(true);
        INDEXED_NOTSTORED_TERMVECTOR.setStoreTermVectorOffsets(true);
        INDEXED_NOTSTORED_TERMVECTOR.setStoreTermVectorPayloads(true);
        INDEXED_NOTSTORED_TERMVECTOR.setStoreTermVectorPositions(true);
        INDEXED_NOTSTORED_TERMVECTOR.setIndexOptions(IndexOptions.DOCS_AND_FREQS);
        INDEXED_NOTSTORED_TERMVECTOR.freeze();

        NOTINDEXED_NOTSTORED_TERMVECTOR = new FieldType();
        NOTINDEXED_NOTSTORED_TERMVECTOR.setIndexed(false);
        NOTINDEXED_NOTSTORED_TERMVECTOR.setStored(false);
        NOTINDEXED_NOTSTORED_TERMVECTOR.setStoreTermVectors(true);
        NOTINDEXED_NOTSTORED_TERMVECTOR.setStoreTermVectorOffsets(true);
        NOTINDEXED_NOTSTORED_TERMVECTOR.setStoreTermVectorPayloads(true);
        NOTINDEXED_NOTSTORED_TERMVECTOR.setStoreTermVectorPositions(true);
        NOTINDEXED_NOTSTORED_TERMVECTOR.setIndexOptions(IndexOptions.DOCS_AND_FREQS);
        NOTINDEXED_NOTSTORED_TERMVECTOR.freeze();

    }

}
