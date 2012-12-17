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

/**
 * Demonstrate Pattern.matches().
 *
 * Created: April, 2002
 * @author Ron Hitchens (ron@ronsoft.com)
 * @version $Id: RegexMatches.java,v 1.1 2002/05/07 02:21:08 ron Exp $
 */
public class RegexMatches
{
	public static void main (String [] argv)
	{
		if (goodAnswer (argv [0])) {
			System.out.println ("Good Answer");
		} else {
			System.out.println ("Sorry, answer is no");
		}
	}

	private static boolean goodAnswer (String answer)
	{
		return (Pattern.matches ("[Yy]es|[Yy]|[Oo][Kk]|[Tt]rue", answer));
	}
}
