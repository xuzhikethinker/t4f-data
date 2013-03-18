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
package aos.data.comparator;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class ComparatorSample{
    public static void main(
            String args[]){
        new Worker().doIt();
    }// end main()
}// end class Comparator04

class Worker{
    public void doIt(){
        Iterator iter;
        Collection ref;
        
        ref = new TreeSet(new TheComparator());
        Populator.fillIt(ref);
        iter = ref.iterator();
        while(iter.hasNext()){
            System.out.print(
                    iter.next() + " ");
        }// end while loop
        System.out.println();
        
    }// end doIt()
}// end class Worker

class Populator{
    public static void fillIt(
            Collection ref){
        ref.add("Joe");
        ref.add("Bill");
        ref.add("Tom");
        ref.add("JOE");
        ref.add("BILL");
        ref.add("TOM");
    }// end fillIt()
}// end class Populator

class TheComparator  
implements Comparator,Serializable{
    
    public int compare(
            Object o1,Object o2){
        if(!(o1 instanceof String))
            throw new ClassCastException();
        if(!(o2 instanceof String))
            throw new ClassCastException();
        
        int result = ((String)o1).
        compareTo(((String)o2));
        return result*(-1);
    }// end compare()
    
    public boolean equals(Object o){
        if(!(o instanceof TheComparator))
            return false;
        else return true;
    }// end overridden equals()
}// end class TheComparator