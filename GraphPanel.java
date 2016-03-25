import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

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
	double minY, maxY;
	int yScaleValue;
	double[] xVals;
	double[] yVals;
	
	double[] xTickVal, yTickVal;

	

public GraphPanel (double[] xValues, double[] yValues, String expression) throws IllegalArgumentException
    {
    	//Save arguments to global variable
	this.expression=expression;
	this.xVals = xValues;
	this.yVals = yValues;
	
	xyWindow.getContentPane().add(xyPanel,"North");
	graphWindow.getContentPane().add(this, "Center");
	
	xyPanel.add(xLabel);
	xyPanel.add(xText);
	xyPanel.add(yLabel);
	xyPanel.add(yText);
	
	graphWindow.setLocation(200,200);
	graphWindow.setSize(500, 500);
	
	graphWindow.setTitle("Graph of: " + expression);
	graphWindow.setVisible(true);
	g = graphWindow.getGraphics();

	

    // To-dos for this constructor method:
    
	// 1 call addMouseListener(this); to register this panel as the MouseListener
    	xyWindow.addMouseListener(this);
    	 
	// 2 Calculate Y scale values (and save them)
     maxY= yValues[0];
     minY= yValues[0];
    	for (int counter = 1; counter < yValues.length ; counter++)//COmputes max y
    	{
    	     if (yValues[counter] > maxY)
    	     {
    	      maxY = yValues[counter];
    	     }
    	}
    	for (int counter = 1; counter < yValues.length ; counter++)//Computes min y
    	{
    	     if (yValues[counter] < minY)
    	     {
    	      minY = yValues[counter];
    	     }
    	}
    	//calculateYScale(minY, maxY); //Changes yScaleValue which is a global variable
    
   // 3 Build the mini displayXYpairWindow (reuse for each mouse click!)
    	
    	xyWindow.getContentPane().add(xyPanel,"North");
    	
    	xyPanel.add(xLabel);
    	xyPanel.add(xText);
    	xyPanel.add(yLabel);
    	xyPanel.add(yText);
    }

@Override
public void paint(Graphics g) // overrides paint() in JPanel!
    {
	@SuppressWarnings("unused")

	
    int windowWidth  = getWidth();  
    int windowHeight = getHeight(); 
    int initialPixelSpace=30;//pixels between edge of window and beginning of axis of graph
  
    int graphWidth=windowWidth-2*initialPixelSpace;// in pixels
    int tickWidth=graphWidth/10;

   
    
 // call methods in JPanel to get the
    // *CURRENT* size of the window!
    
    // 4 Calculate x and y pixels-to-value conversion factors (can't do in CTOR!) 	 
    	
    // 5 Do ALL drawing here in paint() 
    // draw x and y scales and the expression graph here.
    //
    //Upper_Lower_Bounds()
    DrawTicks(g);
    DrawAxis(g);
    }
  public void DrawTicks(Graphics g){
   
    	double stepSize= xVals[1]-xVals[0];
    	double tickValue;
    	String tickVal;
    	
    	Stroke stroke1 = new BasicStroke(2f);
	    ((Graphics2D) g).setStroke(stroke1);
	     //THE Y AXIS TICK MARKS
	    	for(int i=0; i <=10; i++){
	    		 tickValue=yTickVal[i];
	    		 tickVal= String.valueOf(tickValue);
	        	graphWindow.getGraphics();
	        	g.drawLine(57, (this.getHeight()-60)-((i*(this.getHeight()-120))/10), 63, (this.getHeight() - 60)-((i*(this.getHeight()-120))/10));
	        	g.drawString(tickVal, 50, (this.getHeight()-60)-((i*(this.getHeight()-120))/10));
	    	}

	    //THE X AXIS TICK MARKS
	
	    	for(int j=0; j <=10; j++){
	    		
	    		 tickValue=xTickVal[j];
	    		 tickVal= String.valueOf(tickValue);
	        	graphWindow.getGraphics();
	        	g.drawLine((60+((j*(this.getWidth()-120))/10)),(this.getHeight() - 57) , (60+((j*(this.getWidth()-120))/10)), (this.getHeight() - 63));
	        	g.drawString(tickVal, (58+((j*(this.getWidth()-120))/10)), (this.getHeight() - 30));
	 		
	    	}
    }
  
    public void DrawAxis(Graphics g){
	
	Stroke stroke = new BasicStroke(2f);
	((Graphics2D) g).setStroke(stroke);
	g.drawLine(60, 60, 60, (this.getHeight() - 60));
	g.drawLine(60, (this.getHeight() - 60), (this.getWidth() - 60) , (this.getHeight() - 60));
	
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
  
/*
public void calculateYScale(double minY, double maxY)
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
//ASSUME/ // 10 scale values as a starting assumption.
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
   yScaleValue = lowestYscaleValue;
  while(yScaleValue < highestYscaleValue)
       {
	   System.out.print(yScaleValue + ",");
	   yScaleValue += selectedIncrement;
       }
  System.out.println(yScaleValue);

  }     */
}
