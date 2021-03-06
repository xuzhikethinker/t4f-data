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

package com.wideplay.warp.persist.jpa;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.matcher.Matchers;
import com.wideplay.warp.persist.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * On: 2/06/2007
 *
 * @author Dhanji R. Prasanna (dhanji@gmail.com)
 * @since 1.0
 */
public class JpaWorkManagerTest {
    private Injector injector;
    private static final String UNIQUE_TEXT_3 = JpaWorkManagerTest.class.getSimpleName()
            + "CONSTRAINT_VIOLATING some other unique text" + new Date();

    @BeforeClass
    public void pre() {
        injector = Guice.createInjector(PersistenceService.usingJpa()
            .across(UnitOfWork.REQUEST)
            .forAll(Matchers.any())
            .buildModule(),
                new AbstractModule() {

                    protected void configure() {
                        bindConstant().annotatedWith(JpaUnit.class).to("testUnit");
                    }
                });

        //startup persistence
        injector.getInstance(PersistenceService.class)
                .start();
    }

    @AfterClass public void post() {
        injector.getInstance(EntityManagerFactory.class).close();
    }

    
    @Test
    public void workManagerSessionTest() {
        injector.getInstance(WorkManager.class).beginWork();
        try {
            injector.getInstance(TransactionalObject.class).runOperationInTxn();
        } finally {
            injector.getInstance(WorkManager.class).endWork();

        }


        injector.getInstance(WorkManager.class).beginWork();
        injector.getInstance(EntityManager.class).getTransaction().begin();
        try {
            final Query query = injector.getInstance(EntityManager.class).createQuery("select e from JpaTestEntity as e where text = :text");

            query.setParameter("text", UNIQUE_TEXT_3);
            final Object o = query.getSingleResult();

            assert null != o : "no result!!";
            assert o instanceof JpaTestEntity : "Unknown type returned " + o.getClass();
            JpaTestEntity ent = (JpaTestEntity)o;

            assert UNIQUE_TEXT_3.equals(ent.getText()) : "Incorrect result returned or not persisted properly"
                    + ent.getText();

        } finally {
            injector.getInstance(EntityManager.class).getTransaction().commit();
            injector.getInstance(WorkManager.class).endWork();
        }
    }

    @Test
    public void testCloseMoreThanOnce() {
        injector.getInstance(PersistenceService.class).shutdown();
        injector.getInstance(PersistenceService.class).shutdown();
    }

    public static class TransactionalObject {
        @Inject
        EntityManager em;

        @Transactional
        public void runOperationInTxn() {
            JpaTestEntity testEntity = new JpaTestEntity();

            testEntity.setText(UNIQUE_TEXT_3);
            em.persist(testEntity);
        }

        @Transactional
        public void runOperationInTxnError() {
            
            JpaTestEntity testEntity = new JpaTestEntity();

            testEntity.setText(UNIQUE_TEXT_3 + "transient never in db!" + hashCode());
            em.persist(testEntity);
        }
    }
}
