#!/bin/bash

cd /o/hbase.git
git checkout hadoop3
source jdk6.sh
mvn clean package assembly:assembly help:active-profiles -DskipTests -P'!hadoop-1.0, hadoop-3.0'
rm -fr /w/opt/hbase-0.95-HADOOP3-SNAPSHOT
cp target/hbase-0.95-SNAPSHOT.tar.gz /w/opt
cd /w/opt
tar xvfz hbase-0.95-SNAPSHOT.tar.gz
rm hbase-0.95-SNAPSHOT.tar.gz
cd hbase-0.95-SNAPSHOT
cp /t/t4f-data.git/nosql/hbase/common/src/main/resources/aos/hbase/distributed/* conf/
