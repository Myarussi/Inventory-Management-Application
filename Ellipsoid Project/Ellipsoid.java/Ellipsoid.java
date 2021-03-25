import java.text.DecimalFormat;

/**
*
*class Ellipsoid.
*@author Michael Yarussi
*@version 1
*/


public class Ellipsoid {

/**
*This section contains the ellipsoid fields.
*These fields will be used later as values
*to be placed in a formula to calculate volume
*and surface area of an ellipsoid
*
*/

   private String label = "";
   private double a = 0;
   private double b = 0;
   private double c = 0;
  
   

   /**
   *This section contains a constructor that initializes
   *a set of variables.
   *@param labelIn initializes field label
   *@param aIn setter that initializes field a
   *@param bIn setter that initializes field b
   *@param cIn setter that initializes field c
   *args contain String, aAxes, bAxes, cAxes.
   */
   
   public Ellipsoid(String labelIn, double aIn, double bIn, double cIn) {
   
      setLabel(labelIn);
      setA(aIn);
      setB(bIn);
      setC(cIn);
      
   }
   
   /**
   *label is scanned from user and returned as string.
   *
   *@return result as type String
   */
   
   public String getLabel() {
   
      return label;
   
   }
   
   /**
   *This section creates a method that checks
   *if the label is null and returns false if it is.
   *
   *@param finalLabel for label describing the calculation
   *@return if String is not null
   *set the label to trimmed String
   *return true
   */
   
   public boolean setLabel(String finalLabel) {
   
      if (finalLabel != null) {
      
         this.label = finalLabel.trim();
         return true;
         
      }
         
      else {
        
         return false;
      
      }
   }
   
   /**
   *returns the value of a.
   *
   *@return field a as int
   */
   
   public double getA() {
    
      return a;
    
   }
   
   /**
   *checks if a is greater than 0 and returns the value.
   *if a is less than 0 it returns false
   *@param aAxes for varibale describing dimension
   *@return if a>0 set the a to passed value as arg
   *and reutrn true
   */
   
   public boolean setA(double aAxes) {
   
      if (aAxes > 0) {
      
         this.a = aAxes; 
         return true;
         
      }
      
      else {
         return false;
         
      }
   }
   
   /**
   *Creates a method that returns the value of b.
   *@return field bAxes as int
   *
   */
   
   public double getB() {
   
      return b;
   
   }
   
   /**
   *checks if b is greater than 0 and returns the value.
   *if b is less than 0 it returns false
   *@param bAxes for variable describing dimension
   *@set the b to passed value as arg
   *@return true if bAxes>0
   */
   
   public boolean setB(double bAxes) {
   
      if (bAxes > 0) {
      
         this.b = bAxes;
         return true;
         
      }
      
      else {
         return false;
         
      }
   }
   
   /**
   *Creates a method that returns the value of c.
   *@return c as int
   *
   */
   
   public double getC() {
   
      return c;
   
   }
  
   /**
   *checks if c is greater than 0 and returns the value.
   *if c is less than 0 it returns false
   *set c to passed value as arg.
   *@param cAxes for value describing dimension
   *@return true if c>0
   *
   */
   
   
   public boolean setC(double cAxes) {
   
      if (cAxes > 0) {
         this.c = cAxes;
         return true;
      }
      
      else {
         return false;
         
      }
   }
    
    
   /**
   *contains the formula for calulating the volume
   *of an ellipsoid.
   *@return volume calculated as double
   *
   */ 
    
   public double volume() {
   
      return ((4 * Math.PI * a * b * c) / (3));
   
   }
   
   /**
   *contains the formula for calculating the surface area
   *of an ellipsoid.
   *@return surface area calculated as double
   *
   */
   
   public double surfaceArea() {
   
      double surfaceA = (Math.pow((a * b), 1.6) + Math.pow((a * c), 1.6) 
         + Math.pow((b * c), 1.6)) / 3;
        
      double finalSurface = 4 * Math.PI * Math.pow(surfaceA, (1.0 / 1.6));
        
      return finalSurface;
   
   }
   
   /**
   *prints a string containing all of the elements of 
   *our ellipsoid object.
   *
   *@return String representation of Ellipsoid object
   *
   */
   @Override
   public String toString() {
   
      DecimalFormat decimalFormat = new DecimalFormat("#,##0.0###");
      
      return "Ellipsoid \"" + label + "\" with axes a = " + getA() + ", b = " 
         + getB() + ", c = " + getC() + " units has:\n\tvolume = " 
         + decimalFormat.format(volume()) + " cubic units\n\tsurface area = "
         + decimalFormat.format(surfaceArea()) + " square units";
         
   }
         
   
 
}
