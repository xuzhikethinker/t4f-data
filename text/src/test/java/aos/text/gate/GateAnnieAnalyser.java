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
package aos.text.gate;

import gate.Annotation;
import gate.AnnotationSet;
import gate.Corpus;
import gate.Document;
import gate.Factory;
import gate.FeatureMap;
import gate.Gate;
import gate.GateConstants;
import gate.ProcessingResource;
import gate.corpora.RepositioningInfo;
import gate.creole.ANNIEConstants;
import gate.creole.ResourceInstantiationException;
import gate.creole.SerialAnalyserController;
import gate.util.GateException;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class GateAnnieAnalyser {
    private static final Logger LOGGER = Logger.getLogger(GateAnnieAnalyser.class);
    private static final String GATE_HOME_FOLDER = "/Users/echarles/opt/gate-7.0-build4195-BIN";
    private SerialAnalyserController _annieController;

    @Test
    public void test() throws MalformedURLException, GateException {

        System.setProperty("gate.home", GATE_HOME_FOLDER);

        Gate.init();
        File gateHome = Gate.getGateHome();
        File pluginsHome = new File(gateHome, "plugins");
        Gate.getCreoleRegister().registerDirectories(new File(pluginsHome, "ANNIE").toURL());

        initAnnie();

    }

    public List<GateAnalysisResult> analyseText(String text) {

        Corpus corpus = null;

        Document stringDoc = null;

        List<GateAnalysisResult> aosGateAnalysisResultList = new ArrayList<GateAnalysisResult>();

        try {

            corpus = (Corpus) Factory.createResource(gate.corpora.CorpusImpl.class.getName());

            FeatureMap params = Factory.newFeatureMap();
            params.put("stringContent", text);
            params.put("preserveOriginalContent", new Boolean(true));
            params.put("collectRepositioningInfo", new Boolean(true));
            stringDoc = (Document) Factory.createResource("gate.corpora.DocumentImpl", params);
            corpus.add(stringDoc);

            // tell the pipeline about the corpus and run it
            setCorpus(corpus);
            execute();

            aosGateAnalysisResultList = new ArrayList<GateAnalysisResult>();

            Iterator iter = corpus.iterator();

            while (iter.hasNext()) {

                Document doc = (Document) iter.next();

                AnnotationSet defaultAnnotSet = doc.getAnnotations();

                Set annotTypesRequired = new HashSet();
                annotTypesRequired.add("Token");
                annotTypesRequired.add("FirstPerson");
                annotTypesRequired.add("Person");
                annotTypesRequired.add("JobTitle");
                annotTypesRequired.add("Location");
                annotTypesRequired.add("Identifier");
                annotTypesRequired.add("Organization");
                annotTypesRequired.add("Address");
                annotTypesRequired.add("Date");
                annotTypesRequired.add("Money");

                AnnotationSet requiredAnnotationSet = defaultAnnotSet.get(annotTypesRequired);

                FeatureMap features = doc.getFeatures();
                String originalContent = (String) features.get(GateConstants.ORIGINAL_DOCUMENT_CONTENT_FEATURE_NAME);
                RepositioningInfo info = (RepositioningInfo) features
                        .get(GateConstants.DOCUMENT_REPOSITIONING_INFO_FEATURE_NAME);

                Iterator it = requiredAnnotationSet.iterator();
                Annotation currAnnot;

                SortedAnnotationList sortedAnnotationList = new SortedAnnotationList();

                while (it.hasNext()) {
                    currAnnot = (Annotation) it.next();
                    sortedAnnotationList.addSortedExclusive(currAnnot);
                }

                // insert anotation tags backward
                // LOGGER.debug("Unsorted annotations count: " +
                // requiredAnnotationSet.size());
                // LOGGER.debug("Sorted annotations count: " +
                // sortedAnnotationList.size());
                /*
                 * for (int i = sortedAnnotationList.size() - 1; i >= 0; --i) {
                 * 
                 * currAnnot = (Annotation) sortedAnnotationList.get(i);
                 * 
                 * FeatureMap featureMap = currAnnot.getFeatures();
                 * 
                 * if (featureMap.get("string") != null) {
                 * System.out.println("com.twitter.status." +
                 * currAnnot.getType().toLowerCase() + "." +
                 * featureMap.get("kind") + "." + ((String)
                 * featureMap.get("category")).toLowerCase() + ":" +
                 * featureMap.get("string")); } else {
                 * System.out.println("com.twitter.status." +
                 * currAnnot.getType().toLowerCase() + ":" +
                 * doc.getContent().getContent
                 * (currAnnot.getStartNode().getOffset(),
                 * currAnnot.getEndNode().getOffset()).toString()); }
                 * 
                 * }
                 */
                aosGateAnalysisResultList.add(new GateAnalysisResult(doc, sortedAnnotationList));

            }

        }

        catch (ResourceInstantiationException e) {
            e.printStackTrace();
        } catch (GateException e) {
            e.printStackTrace();
        }

        if (corpus != null) {
            Factory.deleteResource(corpus);
        }

        if (stringDoc != null) {
            Factory.deleteResource(stringDoc);
        }

        return aosGateAnalysisResultList;

    }

    /**
     * Initialise the ANNIE system. This creates a "corpus pipeline" application
     * that can be used to run sets of documents through the extraction system.
     */
    public void initAnnie() throws GateException {

        LOGGER.debug("Initializing Annie.");

        // Create a serial analyser controller to run ANNIE with
        _annieController = (SerialAnalyserController) Factory.createResource("gate.creole.SerialAnalyserController",
                Factory.newFeatureMap(), Factory.newFeatureMap(), "ANNIE_" + Gate.genSym());

        // Load each PR as defined in ANNIEConstants
        for (int i = 0; i < ANNIEConstants.PR_NAMES.length; i++) {

            FeatureMap params = Factory.newFeatureMap(); // use default
                                                         // parameters
            ProcessingResource pr = (ProcessingResource) Factory.createResource(ANNIEConstants.PR_NAMES[i], params);

            // Add the PR to the pipeline controller
            _annieController.add(pr);

        }

        LOGGER.debug("Annie is initialized.");

    }

    public void setCorpus(Corpus corpus) {
        _annieController.setCorpus(corpus);
    }

    public void execute() throws GateException {
        _annieController.execute();
    }

    public static class SortedAnnotationList extends Vector<Annotation> {
        private static final long serialVersionUID = 1L;

        public SortedAnnotationList() {
            super();
        }

        public boolean addSortedExclusive(Annotation annot) {

            Annotation currAnot = null;

            // overlapping check
            for (int i = 0; i < size(); ++i) {
                currAnot = (Annotation) get(i);
                if (annot.overlaps(currAnot)) {
                    return false;
                }
            }

            long annotStart = annot.getStartNode().getOffset().longValue();
            long currStart;

            // insert
            for (int i = 0; i < size(); ++i) {
                currAnot = (Annotation) get(i);
                currStart = currAnot.getStartNode().getOffset().longValue();
                if (annotStart < currStart) {
                    insertElementAt(annot, i);
                    /*
                     * LOGGER.debug("Insert start: "+annotStart+" at position: "+
                     * i+" size=" +size());
                     * LOGGER.debug("Current start: "+currStart);
                     */
                    return true;
                }
            }

            int size = size();
            insertElementAt(annot, size);

            // LOGGER.debug("Insert start: "+annotStart+" at size position: "+size);
            return true;

        }

    }

    public static class GateAnalysisResult {
        private Document document;
        private SortedAnnotationList sortedAnnotationList;

        public GateAnalysisResult(Document document, SortedAnnotationList sortedAnnotationList) {
            this.document = document;
            this.sortedAnnotationList = sortedAnnotationList;
        }

        public SortedAnnotationList getSortedAnnotationList() {
            return sortedAnnotationList;
        }

        public Document getDocument() {
            return document;
        }

        public void setSortedAnnotationList(SortedAnnotationList sortedAnnotationList) {
            this.sortedAnnotationList = sortedAnnotationList;
        }

        public void setDocument(Document document) {
            this.document = document;
        }

    }

}
