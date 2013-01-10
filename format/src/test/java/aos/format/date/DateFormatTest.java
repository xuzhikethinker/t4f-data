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
package aos.format.date;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Test;

public class DateFormatTest {
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Test
    public void test1() {
        /*
         * on some JDK, the default TimeZone is wrong we must set the TimeZone
         * manually!!! Calendar cal =
         * Calendar.getInstance(TimeZone.getTimeZone("EST"));
         */
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        /*
         * * on some JDK, the default TimeZone is wrong* we must set the
         * TimeZone manually!!!* sdf.setTimeZone(TimeZone.getTimeZone("EST"));
         */
        sdf.setTimeZone(TimeZone.getDefault());

        System.out.println("Now : " + sdf.format(calendar.getTime()));

        calendar.add(Calendar.HOUR, 12);
        System.out.println("Now + 12 hours : " + sdf.format(calendar.getTime()));

    }

    @Test
    public void test2() {
        System.out.println(easyDateFormat("dd MMMMM yyyy"));
        System.out.println(easyDateFormat("yyyyMMdd"));
        System.out.println(easyDateFormat("dd.MM.yy"));
        System.out.println(easyDateFormat("MM/dd/yy"));
        System.out.println(easyDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z"));
        System.out.println(easyDateFormat("EEE, MMM d, ''yy"));
        System.out.println(easyDateFormat("h:mm a"));
        System.out.println(easyDateFormat("H:mm:ss:SSS"));
        System.out.println(easyDateFormat("K:mm a,z"));
        System.out.println(easyDateFormat("yyyy.MMMMM.dd GGG hh:mm aaa"));
    }

    @Test
    public void test3() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, 1);
        assertNotNull(getHtmlExpiresDateFormatted().format(cal.getTime()));
    }

    @Test
    public void test4() throws ParseException {
        /*
         * * we specify Locale.US since months are in english * we want to parse
         * a TimeStamp
         */
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.US);
        /*
         * * fix timezone in the SimpleDateFormat * bug in JDK1.1
         */
        sdf.setCalendar(Calendar.getInstance());
        /*
         * * create a Date (no choice, parse returns a Date object)
         */
        Date d = sdf.parse("24-Feb-1998 17:39:35");
        System.out.println(d.toString());
        /*
         * * create a GregorianCalendar from a Date object
         */
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(d);
        System.out.println(gc.getTime().toString());
    }

    private SimpleDateFormat getHtmlExpiresDateFormatted() {
        SimpleDateFormat httpDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        httpDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return httpDateFormat;
    }

    private String easyDateFormat(String format) {
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String datenewformat = formatter.format(today);
        return datenewformat;
    }

}
