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
package aos.format.table;
import java.text.*;
import java.applet.*;
import java.awt.*;

public class Table4Format extends Applet {

  NumberFormat myFormat = NumberFormat.getNumberInstance();
  FieldPosition fp = new FieldPosition(NumberFormat.INTEGER_FIELD);
  
  public void init() {

    this.setFont(new Font("Serif", Font.BOLD, 12));
    myFormat.setMaximumIntegerDigits(3);
    myFormat.setMaximumFractionDigits(2);
    myFormat.setMinimumFractionDigits(2);
  }
  
  public void paint(Graphics g) {
  
    FontMetrics fm = this.getFontMetrics(this.getFont()) ;
    int xmargin = 5;
    int lineHeight = fm.getMaxAscent() + fm.getMaxDescent();
    int y = lineHeight;
    int x = xmargin;
    int desiredPixelWidth = 3 * fm.getMaxAdvance();
    int fieldWidth = 6 * fm.getMaxAdvance();
    int headerWidth = fm.stringWidth("Degrees");
    g.drawString("Degrees", x + (fieldWidth - headerWidth)/2, y);
    headerWidth = fm.stringWidth("Radians");    
    g.drawString("Radians", x + fieldWidth + (fieldWidth - headerWidth)/2, y);
    headerWidth = fm.stringWidth("Grads");
    g.drawString("Grads", x + 2*fieldWidth + (fieldWidth - headerWidth)/2, y);
    
    for (double degrees = 0.0; degrees < 360.0; degrees++) {
      y += lineHeight;
      String degreeString = myFormat.format(degrees, new StringBuffer(), 
         fp).toString(); 
      String intPart = degreeString.substring(0, fp.getEndIndex());
      g.drawString(degreeString, xmargin + desiredPixelWidth 
         - fm.stringWidth(intPart), y);
      String radianString = myFormat.format(Math.PI*degrees/180.0, 
         new StringBuffer(), fp).toString();
      intPart = radianString.substring(0, fp.getEndIndex());
      g.drawString(radianString, 
          xmargin + fieldWidth + desiredPixelWidth - fm.stringWidth(intPart), y);
      String gradString = myFormat.format(400 * degrees / 360, 
         new StringBuffer(), fp).toString();
      intPart = gradString.substring(0, fp.getEndIndex());
      g.drawString(gradString, 
        xmargin + 2*fieldWidth + desiredPixelWidth - fm.stringWidth(intPart), y);
    }
  }
}
