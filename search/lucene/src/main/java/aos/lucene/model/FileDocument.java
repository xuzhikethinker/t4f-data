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
package aos.lucene.model;

import java.io.File;

import org.apache.lucene.document.Document;

/**
 * A utility for making Lucene Documents from a File.
 */
public class FileDocument {

    private FileDocument() {
    }

    /**
     * Makes a document for a File.
     * <p>
     * The document has three fields:
     * <ul>
     * <li><code>path</code>--containing the pathname of the file, as a stored,
     * untokenized field;
     * <li><code>modified</code>--containing the last modified date of the file
     * as a field as created by <a
     * href="lucene.document.DateTools.html">DateTools</a>; and
     * <li><code>contents</code>--containing the full contents of the file, as a
     * Reader field;
     */
    public static Document Document(File f) throws java.io.FileNotFoundException {

        // make a new, empty document
        Document doc = new Document();

        // Add the path of the file as a field named "path". Use a field that is
        // indexed (i.e. searchable), but don't tokenize the field into words.
        // doc.add(new Field("path", f.getPath(), Field.Store.YES,
        // field.Index.NOT_ANALYZED));

        // Add the last modified date of the file a field named "modified". Use
        // a field that is indexed (i.e. searchable), but don't tokenize the
        // field into words.
        // doc.add(new Field("modified",
        // dateTools.timeToString(f.lastModified(),
        // dateTools.Resolution.MINUTE),
        // field.Store.YES, Field.Index.NOT_ANALYZED));

        // Add the contents of the file to a field named "contents". Specify a
        // Reader,
        // so that the text of the file is tokenized and indexed, but not
        // stored.
        // Note that FileReader expects the file to be in the system's default
        // encoding.
        // If that's not the case searching for special characters will fail.
        // doc.add(new Field("contents", new FileReader(f)));

        return doc;

    }

}
