import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class GraphPanel extends JPanel implements MouseListener
{
	JFrame graphWindow= new JFrame();
	JFrame xyWindow= new JFrame();
	
	JPanel xyPanel= new JPanel();
	JPanel graphArea= new JPanel();
	
	JLabel xLabel=new JLabel("X= ");
	JLabel yLabel=new JLabel("Y= ");
	
	JTextField xText=new JTextField();
	JTextField yText=new JTextField();
	
	// GOES In Expression Calculator JButton graphButton
	Graphics g;
	String expression;
	

	

public GraphPanel (double[] xValues, double[] yValues, String expression) throws IllegalArgumentException
    {
	this.expression=expression;
	//GUI BUILD HO
	xyWindow.getContentPane().add(xyPanel,"North");
	xyPanel.add(xLabel);
	xyPanel.add(xText);
	xyPanel.add(yLabel);
	xyPanel.add(yText);
	
	
	
    // To-dos for this constructor method:
    // 1 call addMouseListener(this); to register this panel as the MouseListener
    // 2 Calculate Y scale values (and save them) 
    // 3 Build the mini displayXYpairWindow (reuse for each mouse click!)
    }

@Override
public void paint(Graphics g) // overrides paint() in JPanel!
    {
    int windowWidth  = getWidth();  
    int windowHeight = getHeight(); 
 // call methods in JPanel to get the
    // *CURRENT* size of the window!
    
    // 4 Calculate x and y pixels-to-value conversion factors (can't do in CTOR!) 	 
    // 5 Do ALL drawing here in paint() 
    // draw x and y scales and the expression graph here.
    }

  public void mousePressed(MouseEvent me) // show tiny x,y values window
    {
    // xTextField and yTextField are in the mini displayXYpairWindow
    int xPixelsToValueConversionFactor= 1; ////////////Requires Fine Tuning
    int xInPixels = me.getX();
    double xValue = xInPixels * xPixelsToValueConversionFactor;
    String xValueString = String.valueOf(xValue);
    xText.setText( xValueString);
   
    String yValueString = ExpressionCalculator.evaluateExpression(expression.replace("x", xValueString)); //
    yText.setText( yValueString);

    // show mini x,y display window
   xyWindow.setLocation(me.getX(), me.getY());
   xyWindow.setVisible(true); 
    }

  public void mouseReleased(MouseEvent me) // hide tiny window
    {
    // "erase" mini x,y display window	
    xyWindow.setVisible(false);
    }

  public void mouseClicked(MouseEvent me){} // take no action
  public void mouseEntered(MouseEvent me){} // on these
  public void mouseExited(MouseEvent  me){} // window events
  

public void CaclulateYScale(int minY, int maxY)
  {
 // if (args.length != 2)
    // {
	// System.out.println("Provide two numeric values which are the min and max Y values to be plotted.");
	 System.out.println("This program will determine appropriate Y scale values to be printed on the Y axis."); 
	 //return;
   //  }
  
	 double yMin, yMax, dPlotRange;
  int    plotRange, initialIncrement, upperIncrement, 
         lowerIncrement, selectedIncrement, numberOfYscaleValues,
         lowestYscaleValue, highestYscaleValue;
  String zeros = "0000000000";
  try {
	  yMin = Double.parseDouble(String.valueOf(minY));
	  yMax = Double.parseDouble(String.valueOf(maxY));
	  if (yMin > yMax)
	     {
		 double temp = yMax;
		 yMax = yMin;
		 yMin = temp;
	     }
	  System.out.println("Entered values are: Ymin = " + yMin + " Ymax = " + yMax);
      }
  catch(NumberFormatException nfe)
      {
	  System.out.println("Both input parms must be numeric.");
	  return;
      }
  
  // 1) Determine the RANGE to be plotted.
  dPlotRange = yMax - yMin;
  System.out.println("Plot range (Ymax-Ymin) = " + dPlotRange);

  // 2) Determine an initial increment value.
  if (dPlotRange > 10)
     {
	 plotRange = (int)dPlotRange;
	 System.out.println("Rounded plot range = " + plotRange);
     }
  else
     {
	 System.out.println("Add handling of small plot range!");
	 return;
     }
/*ASSUME*/ // 10 scale values as a starting assumption.
  initialIncrement = plotRange/10;
  System.out.println("Initial increment value = " + initialIncrement);
  // Please excuse this clumsy "math"!
  String initialIncrementString = String.valueOf(initialIncrement);
  //System.out.println("InitialIncrementString = " + initialIncrementString + " (length = " + initialIncrementString.length() + ")");

  // 3) Find even numbers above and below the initial increment. 
  String leadingDigit = initialIncrementString.substring(0,1);
  int leadingNumber = Integer.parseInt(leadingDigit);
  int bumpedLeadingNumber = leadingNumber + 1;
  String bumpedLeadingDigit = String.valueOf(bumpedLeadingNumber);
  String upperIncrementString = bumpedLeadingDigit + zeros.substring(0,initialIncrementString.length()-1);
  String lowerIncrementString = leadingDigit       + zeros.substring(0,initialIncrementString.length()-1);
  upperIncrement = Integer.parseInt(upperIncrementString);
  lowerIncrement = Integer.parseInt(lowerIncrementString);
  System.out.println("Upper increment alternative = " + upperIncrement);
  System.out.println("Lower increment alternative = " + lowerIncrement);

  // 4) Pick the upper or lower even increment depending on which is closest.
  int distanceToUpper = upperIncrement - initialIncrement;
  int distanceToLower = initialIncrement - lowerIncrement;
  if (distanceToUpper > distanceToLower)
	  selectedIncrement = lowerIncrement;
    else
      selectedIncrement = upperIncrement;
  System.out.println("The closest even increment (and therefore the one chosen) = " + selectedIncrement);

  // 5) Determine lowest Y scale value
  numberOfYscaleValues = 0;
  lowestYscaleValue    = 0;
  if (yMin < 0)
     {
     for (; lowestYscaleValue > yMin; lowestYscaleValue-=selectedIncrement)
          numberOfYscaleValues++;
     }
  if (yMin > 0)
     {
	 for (; lowestYscaleValue < yMin; lowestYscaleValue+=selectedIncrement)
	      numberOfYscaleValues++;
     numberOfYscaleValues--;
     lowestYscaleValue -= selectedIncrement;
     }
  System.out.println("The lowest Y scale value will be " + lowestYscaleValue + ")");
  
  
  // 6) Determine upper Y scale value
  numberOfYscaleValues = 1;
  for (highestYscaleValue = lowestYscaleValue; highestYscaleValue < yMax; highestYscaleValue+=selectedIncrement)
	  numberOfYscaleValues++;
  System.out.println("The highest Y scale value will be " + highestYscaleValue);
  System.out.println("The number of Y scale click marks will be " + numberOfYscaleValues);
  if ((numberOfYscaleValues < 5) || (numberOfYscaleValues > 20))
     {
	 System.out.println("Number of Y scale click marks is too few or too many!");
	 return;
     }
  
  // 7) Determine if Y scale will be extended to include the 0 point.
  if ((lowestYscaleValue < 0) && (highestYscaleValue > 0))
       System.out.println("The Y scale includes the 0 point.");
    else // Y scale does not include 0.
     {   //	Should it be extended to include the 0 point?
     if ((lowestYscaleValue > 0) && (lowestYscaleValue/selectedIncrement <= 3))
        {
    	lowestYscaleValue = 0;
    	System.out.println("Lower Y scale value adjusted down to 0 to include 0 point. (Additional click marks added.)");
        }
     if ((highestYscaleValue < 0) && (highestYscaleValue/selectedIncrement <= 3))
        {
     	highestYscaleValue = 0;
    	System.out.println("Upper Y scale value adjusted up to 0 to include 0 point. (Additional click marks added.)");
        }
     }
  int yScaleValue = lowestYscaleValue;
  while(yScaleValue < highestYscaleValue)
       {
	   System.out.print(yScaleValue + ",");
	   yScaleValue += selectedIncrement;
       }
  System.out.println(yScaleValue);

  }      
}
