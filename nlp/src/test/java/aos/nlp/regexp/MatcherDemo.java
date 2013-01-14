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
package aos.nlp.regexp;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 
Characters
x     The character x
\\     The backslash character
\0n     The character with octal value 0n (0 <= n <= 7)
\0nn     The character with octal value 0nn (0 <= n <= 7)
\0mnn     The character with octal value 0mnn (0 <= m <= 3, 0 <= n <= 7)
\xhh     The character with hexadecimal value 0xhh
\ uhhhh     The character with hexadecimal value 0xhhhh
\t     The tab character ('\u0009')
\n     The newline (line feed) character ('\u000A')
\r     The carriage-return character ('\u000D')
\f     The form-feed character ('\u000C')
\a     The alert (bell) character ('\u0007')
\e     The escape character ('\u001B')
\cx     The control character corresponding to x
 
Character classes
[abc]     a, b, or c (simple class)
[^abc]     Any character except a, b, or c (negation)
[a-zA-Z]     a through z or A through Z, inclusive (range)
[a-d[m-p]]     a through d, or m through p: [a-dm-p] (union)
[a-z&&[def]]     d, e, or f (intersection)
[a-z&&[^bc]]     a through z, except for b and c: [ad-z] (subtraction)
[a-z&&[^m-p]]     a through z, and not m through p: [a-lq-z](subtraction)
 
Predefined character classes
.     Any character (may or may not match line terminators)
\d     A digit: [0-9]
\D     A non-digit: [^0-9]
\s     A whitespace character: [ \t\n\x0B\f\r]
\S     A non-whitespace character: [^\s]
\w     A word character: [a-zA-Z_0-9]
\W     A non-word character: [^\w]
 
POSIX character classes (US-ASCII only)
\p{Lower}     A lower-case alphabetic character: [a-z]
\p{Upper}     An upper-case alphabetic character:[A-Z]
\p{ASCII}     All ASCII:[\x00-\x7F]
\p{Alpha}     An alphabetic character:[\p{Lower}\p{Upper}]
\p{Digit}     A decimal digit: [0-9]
\p{Alnum}     An alphanumeric chara * 
 * Construct      Matches
cter:[\p{Alpha}\p{Digit}]
\p{Punct}     Punctuation: One of !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~
\p{Graph}     A visible character: [\p{Alnum}\p{Punct}]
\p{Print}     A printable character: [\p{Graph}]
\p{Blank}     A space or a tab: [ \t]
\p{Cntrl}     A control character: [\x00-\x1F\x7F]
\p{XDigit}     A hexadecimal digit: [0-9a-fA-F]
\p{Space}     A whitespace character: [ \t\n\x0B\f\r]
 
Classes for Unicode blocks and categories
\p{InGreek}     A character in the Greek block (simple block)
\p{Lu}     An uppercase letter (simple category)
\p{Sc}     A currency symbol
\P{InGreek}     Any character except one in the Greek block (negation)
[\p{L}&&[^\p{Lu}]]      Any letter except an uppercase letter (subtraction)
 
Boundary matchers
^     The beginning of a line
$     The end of a line
\b     A word boundary
\B     A non-word boundary
\A     The beginning of the input
\G     The end of the previous match
\Z     The end of the input but for the final terminator, if any
\z     The end of the input
 
Greedy quantifiers
X?     X, once or not at all
X*     X, zero or more times
X+     X, one or more times
X{n}     X, exactly n times
X{n,}     X, at least n times
X{n,m}     X, at least n but not more than m times
 
Reluctant quantifiers
X??     X, once or not at all
X*?     X, zero or more times
X+?     X, one or more times
X{n}?     X, exactly n times
X{n,}?     X, at least n times
X{n,m}?     X, at least n but not more than m times
 
Possessive quantifiers
X?+     X, once or not at all
X*+     X, zero or more times
X++     X, one or more times
X{n}+     X, exactly n times
X{n,}+     X, at least n times
X{n,m}+     X, at least n but not more than m times
 
Logical operators
XY     X followed by Y
X|Y     Either X or Y
(X)     X, as a capturing group
 
Back references
\n     Whatever the nth capturing group matched
 
Quotation
\     Nothing, but quotes the following character
\Q     Nothing, but quotes all characters until \E
\E     Nothing, but ends quoting started by \Q
 
Special constructs (non-capturing)
(?:X)     X, as a non-capturing group
(?idmsux-idmsux)      Nothing, but turns match flags on - off
(?idmsux-idmsux:X)       X, as a non-capturing group with the given flags on - off
(?=X)     X, via zero-width positive lookahead
(?!X)     X, via zero-width negative lookahead
(?<=X)     X, via zero-width positive lookbehind
(?<!X)     X, via zero-width negative lookbehind
(?>X)     X, as an independent, non-capturing group

 *
 */
public class MatcherDemo {

    public static void main(String... args) {
//        match("Exemple dog", "Exempl. ", "");
//        match("Exemple dog", "Exe.++", "");
//        match("Exemple http://dog dfd", "://", "");
//        match("Exemple dog dddoggg dfd", "\\bdog\\b", "");
//        match("http://dfq Exemple dog http://dfq.com/sfdqf?dsf&% dsfqd", "http://[a-zA-Z0-9./&\"'%?]*", "");
//        match("Exemple dog http://dfq.com/sfdqf%213\"dfq fds  ff f  http://sdklfj332/é%& dsfqd é\"éçà_\"é http://sdklfj332/%&/dfez&&%%545", "\\bhttp://[a-zA-Z0-9./&\"'%?]++\\b", "");
//        match("@test Exemple @test dog http://dfq.com/sfdqf dsfqd @ldkjf", "@[a-zA-Z./]*", "");
//        match("#ldf dog dog #ldfjds #dlkfj dskfjqks#333trtrdkfljqkdf #3333dkfjdkf.", "#[a-zA-Z/1-9]*", "");
//        match("dog dog #ldfjds #dlkfj dskfjqks#333trtrdkfljqkdf #3333dkfjdkf.", "\\b#[a-zA-Z/1-9]++\\b", "");
//        match("dog dog dog doggie dogg", "\\bdog\\b ", "");
//        match("dog  dog       dog              doggie dogg", "\\p{Space}++", " ");
//        match("RT rt RT sqdkflj RT", "\\bRT\\b", " ");
        match("RT rt RT sqdkflj RT", "\\bRT\\b", "\\bRT\\b");
    }
        
    private static void match(String input, String regexp, String replace) {
        System.out.println("input=\"" + input + "\" - regexp=\"" + regexp + "\" - replace=\"" + replace + "\"");
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(input);
        int count = 0;
        while (matcher.find()) {
            count++;
            System.out.println(count + ". start:" + matcher.start() + " - end:" + matcher.end() + " = \"" + input.substring(matcher.start(), matcher.end()) + "\"");
        }
        System.out.println("output=\"" + input.replaceAll(regexp, replace) + "\"");
        System.out.println("-----------------------------------------------------");
    }

}
