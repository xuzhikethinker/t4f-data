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
package aos.lucene.frequency;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Fields;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiFields;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Bits;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.PriorityQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AosFrequencyTerms {

    private static final Logger LOGGER = LoggerFactory.getLogger(AosFrequencyTerms.class);

    // The top numTerms will be displayed
    public static final int DEFAULTnumTerms = 100;
    public static int numTerms = DEFAULTnumTerms;

    public static void main(String... args) throws Exception {
        IndexReader reader = null;
        FSDirectory dir = null;
        String field = null;
        boolean IncludeTermFreqs = false;

        if (args.length == 0 || args.length > 4) {
            usage();
            System.exit(1);
        }

        if (args.length > 0) {
            dir = FSDirectory.open(new File(args[0]));
        }

        for (int i = 1; i < args.length; i++) {
            if (args[i].equals("-t")) {
                IncludeTermFreqs = true;
            }
            else {
                try {
                    numTerms = Integer.parseInt(args[i]);
                }
                catch (NumberFormatException e) {
                    field = args[i];
                }
            }
        }
        String[] fields = field != null ? new String[] { field } : null;
        reader = DirectoryReader.open(dir);
        AosTermStats[] terms = getHighFreqTerms(reader, numTerms, fields);
        if (!IncludeTermFreqs) {
            // default HighFreqTerms behavior
            for (int i = 0; i < terms.length; i++) {
                System.out.printf("%s:%s %,d \n", terms[i].field, terms[i].termtext.utf8ToString(), terms[i].docFreq);
            }
        }
        else {
            AosTermStats[] termsWithTF = sortByTotalTermFreq(reader, terms);
            for (int i = 0; i < termsWithTF.length; i++) {
                System.out.printf("%s:%s \t totalTF = %,d \t doc freq = %,d \n", termsWithTF[i].field,
                        termsWithTF[i].termtext.utf8ToString(), termsWithTF[i].totalTermFreq, termsWithTF[i].docFreq);
            }
        }
        reader.close();
    }

    private static void usage() {
        System.out
                .println("\n\n"
                        + "java org.apache.lucene.misc.HighFreqTerms <index dir> [-t][number_terms] [field]\n\t -t: include totalTermFreq\n\n");
    }

    private static final AosTermStats[] EMPTY_STATS = new AosTermStats[0];

    /**
     * 
     * @param reader
     * @param numTerms
     * @param field
     * @return TermStats[] ordered by terms with highest docFreq first.
     * @throws Exception
     */
    public static AosTermStats[] getHighFreqTerms(IndexReader reader, int numTerms, String[] fieldNames)
            throws Exception {
        TermStatsQueue tiq = null;
        TermsEnum te = null;

        if (fieldNames != null) {
            Fields fields = MultiFields.getFields(reader);
            if (fields == null) {
                LOGGER.info("Index with no fields - probably empty or corrupted");
                return EMPTY_STATS;
            }
            tiq = new TermStatsQueue(numTerms);
            for (String field : fieldNames) {
                Terms terms = fields.terms(field);
                if (terms != null) {
                    te = terms.iterator(te);
                    fillQueue(te, tiq, field);
                }
            }
        }
        else {
            Fields fields = MultiFields.getFields(reader);
            if (fields == null) {
                LOGGER.info("Index with no fields - probably empty or corrupted");
                return EMPTY_STATS;
            }
            tiq = new TermStatsQueue(numTerms);
            Iterator<String> fieldsEnum = fields.iterator();
            while (true) {
                /*
                 * String field = fieldsEnum.next();
                 * 
                 * if (field != null) { Terms terms = fieldsEnum.terms(); te =
                 * terms.iterator(te); fillQueue(te, tiq, field); } else {
                 * break; }
                 */}
        }

        AosTermStats[] result = new AosTermStats[tiq.size()];
        // we want highest first so we read the queue and populate the array
        // starting at the end and work backwards
        int count = tiq.size() - 1;
        while (tiq.size() != 0) {
            result[count] = tiq.pop();
            count--;
        }
        return result;
    }

    /**
     * Takes array of TermStats. For each term looks up the tf for each doc
     * containing the term and stores the total in the output array of
     * TermStats. Output array is sorted by highest total tf.
     * 
     * @param reader
     * @param terms
     *            TermStats[]
     * @return TermStats[]
     * @throws Exception
     */

    public static AosTermStats[] sortByTotalTermFreq(IndexReader reader, AosTermStats[] terms) throws Exception {
        AosTermStats[] ts = new AosTermStats[terms.length]; // array for sorting
        long totalTF;
        for (int i = 0; i < terms.length; i++) {
            totalTF = getTotalTermFreq(reader, terms[i].field, terms[i].termtext);
            ts[i] = new AosTermStats(terms[i].field, terms[i].termtext, terms[i].docFreq, totalTF);
        }

        Comparator<AosTermStats> c = new TotalTermFreqComparatorSortDescending();
        Arrays.sort(ts, c);

        return ts;
    }

    public static long getTotalTermFreq(IndexReader reader, String field, BytesRef termtext) throws Exception {
        BytesRef br = termtext;
        long totalTF = 0;
        try {
            Bits liveDocs = MultiFields.getLiveDocs(reader);
            totalTF = MultiFields.totalTermFreq(reader, field, termtext);
            return totalTF;
        }
        catch (Exception e) {
            return 0;
        }
    }

    public static void fillQueue(TermsEnum termsEnum, TermStatsQueue tiq, String field) throws Exception {

        while (true) {
            BytesRef term = termsEnum.next();
            if (term != null) {
                BytesRef r = new BytesRef();
                r.copyBytes(term);
                tiq.insertWithOverflow(new AosTermStats(field, r, termsEnum.docFreq()));
            }
            else {
                break;
            }
        }
    }
}

/**
 * Comparator
 * 
 * Reverse of normal Comparator. i.e. returns 1 if a.totalTermFreq is less than
 * b.totalTermFreq So we can sort in descending order of totalTermFreq
 */

final class TotalTermFreqComparatorSortDescending implements Comparator<AosTermStats> {

    @Override
    public int compare(AosTermStats a, AosTermStats b) {
        if (a.totalTermFreq < b.totalTermFreq) {
            return 1;
        }
        else if (a.totalTermFreq > b.totalTermFreq) {
            return -1;
        }
        else {
            return 0;
        }
    }
}

/**
 * Priority queue for TermStats objects ordered by docFreq
 **/
final class TermStatsQueue extends PriorityQueue<AosTermStats> {
    TermStatsQueue(int size) {
        super(size);
    }

    @Override
    protected boolean lessThan(AosTermStats termInfoA, AosTermStats termInfoB) {
        return termInfoA.docFreq < termInfoB.docFreq;
    }
}
