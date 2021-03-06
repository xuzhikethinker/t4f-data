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
package aos.math.factorial;

/**
 * This program computes the factorial of a number
 */
public class FactorialMain1 {
  public static void main(String... args) {
    int input = Integer.parseInt(args[0]);
    double result = factorial(input);
    System.out.println(result);
  }

  public static double factorial(int x) {
    if (x < 0) {
      return 0.0;
    }
    double fact = 1.0;
    while (x > 1) {
      fact = fact * x;
      x--;
    }
    return fact;
  }
}
