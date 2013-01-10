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
package aos.format.url;

import java.net.URL;
import java.util.Formattable;
import java.util.FormattableFlags;
import java.util.Formatter;
import java.util.IllegalFormatFlagsException;
import java.util.IllegalFormatPrecisionException;
import java.util.IllegalFormatWidthException;
import java.util.Locale;

public class FormattableURL implements Formattable {

    private URL delegate;

    public FormattableURL(URL url) {
        this.delegate = url;
    }

    public void formatTo(Formatter formatter, int flags, int width, int precision) {

        if (precision < -1) {
            throw new IllegalFormatPrecisionException(precision);
        }
        if (width < -1) {
            throw new IllegalFormatWidthException(width);
        }
        if (precision > width) {
            throw new IllegalFormatPrecisionException(precision);
        }

        // Check to see if we've been asked to use any flags we don't interpret
        int recognizedFlags = FormattableFlags.UPPERCASE | FormattableFlags.LEFT_JUSTIFY;
        boolean unsupportedFlags = ((~recognizedFlags) & flags) != 0;
        if (unsupportedFlags) {
            // We should really pass the flags to this constructor.
            // However, Java doesn't offer any reasonable way to get these.
            throw new IllegalFormatFlagsException("#");
        }

        boolean upperCase = (flags & FormattableFlags.UPPERCASE) != 0;

        StringBuffer sb = new StringBuffer();

        String scheme = delegate.getProtocol();
        if (upperCase && scheme != null) {
            scheme = scheme.toUpperCase(Locale.ENGLISH);
        }

        String hostname = delegate.getHost();
        if (upperCase && hostname != null) {
            hostname = hostname.toUpperCase(Locale.ENGLISH);
        }

        String userInfo = delegate.getUserInfo();

        int port = delegate.getPort();
        String path = delegate.getPath();
        String query = delegate.getQuery();
        String fragment = delegate.getRef();

        if (scheme != null) {
            sb.append(scheme);
            sb.append("://");
        }

        if (userInfo != null) {
            sb.append(userInfo);
            sb.append("@");
        }

        if (hostname != null) {
            sb.append(hostname);
        }

        if (port != -1) {
            sb.append(':');
            sb.append(port);
        }

        if (path != null) {
            sb.append(path);
        }

        if (query != null) {
            sb.append('?');
            sb.append(query);
        }

        if (fragment != null) {
            sb.append('#');
            sb.append(fragment);
        }

        boolean leftJustify = (flags & FormattableFlags.LEFT_JUSTIFY) != 0;

        // Truncate on the right if necessary
        if (precision < sb.length()) {
            sb.setLength(precision);
        } else {// Pad with spaces if necessary
            while (sb.length() < width) {
                if (leftJustify)
                    sb.append(' ');
                else
                    sb.insert(0, ' ');
            }
        }

        formatter.format(sb.toString());

    }

}
