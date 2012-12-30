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
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package aos.lucene.xmlparser;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.xml.CorePlusExtensionsParser;
import org.apache.lucene.queryparser.xml.QueryTemplateManager;
import org.apache.lucene.search.IndexSearcher;

/**
 * Example servlet that uses the XML queryparser.
 * <p>
 * NOTE: you must provide CSV data in <code>/WEB-INF/data.tsv</code>
 * for the demo to work!
 */
public class FormBasedXmlQueryDemo {

  private QueryTemplateManager queryTemplateManager;
  private CorePlusExtensionsParser xmlParser;
  private IndexSearcher searcher;
  private Analyzer analyzer = new StandardAnalyzer(org.apache.lucene.util.Version.LUCENE_CURRENT);
/*
  public static void main(String... args) {
      openExampleIndex();

      //load servlet configuration settings
      String xslFile = config.getInitParameter("xslFile");
      String defaultStandardQueryParserField = config.getInitParameter("defaultStandardQueryParserField");


      //Load and cache choice of XSL query template using QueryTemplateManager
      queryTemplateManager = new QueryTemplateManager(
          getServletContext().getResourceAsStream("/WEB-INF/" + xslFile));

      //initialize an XML Query Parser for use by all threads
      xmlParser = new CorePlusExtensionsParser(defaultStandardQueryParserField, analyzer);
  }

  protected void doPost()  {
    //Take all completed form fields and add to a Properties object
    Properties completedFormFields = new Properties();

    Enumeration<?> pNames = request.getParameterNames();
    while (pNames.hasMoreElements()) {
      String propName = (String) pNames.nextElement();
      String value = request.getParameter(propName);
      if ((value != null) && (value.trim().length() > 0)) {
        completedFormFields.setProperty(propName, value);
      }

    }

      //Create an XML query by populating template with given user criteria
      org.w3c.dom.Document xmlQuery = queryTemplateManager.getQueryAsDOM(completedFormFields);

      //Parse the XML to produce a Lucene query
      Query query = xmlParser.getQuery(xmlQuery.getDocumentElement());

      //Run the query
      TopDocs topDocs = searcher.search(query, 10);

      //and package the results and forward to JSP
      if (topDocs != null) {
        ScoreDoc[] sd = topDocs.scoreDocs;
        Document[] results = new Document[sd.length];
        for (int i = 0; i < results.length; i++) {
          results[i] = searcher.doc(sd[i].doc);
          request.setAttribute("results", results);
        }
      }
  }

  private void openExampleIndex() throws IOException {
    //Create a RAM-based index from our test data file
    RAMDirectory rd = new RAMDirectory();
    IndexWriterConfig iwConfig = new IndexWriterConfig(Version.LUCENE_40, analyzer);
    IndexWriter writer = new IndexWriter(rd, iwConfig);
    InputStream dataIn = getClass().getResourceAsStream("/WEB-INF/data.tsv");
    BufferedReader br = new BufferedReader(new InputStreamReader(dataIn, IOUtils.CHARSET_UTF_8));
    String line = br.readLine();
    final FieldType textNoNorms = new FieldType(TextField.TYPE_STORED);
    textNoNorms.setOmitNorms(true);
    while (line != null) {
      line = line.trim();
      if (line.length() > 0) {
        //parse row and create a document
        StringTokenizer st = new StringTokenizer(line, "\t");
        Document doc = new Document();
        doc.add(new Field("location", st.nextToken(), textNoNorms));
        doc.add(new Field("salary", st.nextToken(), textNoNorms));
        doc.add(new Field("type", st.nextToken(), textNoNorms));
        doc.add(new Field("description", st.nextToken(), textNoNorms));
        writer.addDocument(doc);
      }
      line = br.readLine();
    }
    writer.close();

    //open searcher
    // this example never closes it reader!
    IndexReader reader = DirectoryReader.open(rd);
    searcher = new IndexSearcher(reader);
  }
*/  
}
