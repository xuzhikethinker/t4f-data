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
package lia.benchmark;

/**
 * Copyright Manning Publications Co.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific lan      
*/

import java.io.File;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.*;
import org.apache.lucene.benchmark.quality.*;
import org.apache.lucene.benchmark.quality.utils.*;
import org.apache.lucene.benchmark.quality.trec.*;

// From appendix C

/* This code was extracted from the Lucene
   contrib/benchmark sources */

public class PrecisionRecall {

  public static void main(String... args) throws Throwable {

    File topicsFile = new File("src/lia/benchmark/topics.txt");
    File qrelsFile = new File("src/lia/benchmark/qrels.txt");
    Directory dir = FSDirectory.open(new File("indexes/MeetLucene"));
    Searcher searcher = new IndexSearcher(dir, true);

    String docNameField = "filename"; 
    
    PrintWriter LOGGER = new PrintWriter(System.out, true); 

    TrecTopicsReader qReader = new TrecTopicsReader();   //#1
    QualityQuery qqs[] = qReader.readQueries(            //#1
        new BufferedReader(new FileReader(topicsFile))); //#1
    
    Judge judge = new TrecJudge(new BufferedReader(      //#2
        new FileReader(qrelsFile)));                     //#2
    
    judge.validateData(qqs, LOGGER);                     //#3
    
    QualityQueryParser qqParser = new SimpleQQParser("title", "contents");  //#4
    
    QualityBenchmark qrun = new QualityBenchmark(qqs, qqParser, searcher, docNameField);
    SubmissionReport submitLog = null;
    QualityStats stats[] = qrun.execute(judge,           //#5
            submitLog, LOGGER);
    
    QualityStats avg = QualityStats.average(stats);      //#6
    avg.log("SUMMARY",2,LOGGER, "  ");
    dir.close();
  }
}

/*
#1 Read TREC topics as QualityQuery[]
#2 Create Judge from TREC Qrel file
#3 Verify query and Judge match
#4 Create parser to translate queries into Lucene queries
#5 Run benchmark
#6 Print precision and recall measures
*/
