import java.util.ArrayList;
import java.text.DecimalFormat;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
*This program creates methods that calculate volume,
*surface area, average volume and average surface area
*of ellipsoid objects contained in a file.
*
*@author Michael Yarussi
*@version 1
*/

public class EllipsoidList {

   private String listName;
   private ArrayList<Ellipsoid> elipList;
   
   /**
   *Constructor containing instance variables that initialize
   *the name of the ellipsoid list.
   *@param listNameIn list name of the ellipsoid file
   *@param elipListIn contains the list of the ellipsoid file
   */
   public EllipsoidList(String listNameIn, ArrayList<Ellipsoid> elipListIn) {
   
      listName = listNameIn;
      elipList = elipListIn;
   
   }
   
   /**
   *Method that gets the name of the list.
   *@return listName name of list
   */
   
   public String getName()
   {
   
      return listName;
      
   }
   
   /**
   *Method that determines the size of the list.
   *
   *@return size of the ellipsoid list
   */
   public int numberOfEllipsoids()
   {
      
      return elipList.size();
      
   }
   
   /**
   *Method that calculates total volume.
   *@return 0 if the size of the list equals 0
   *
   */
   public double totalVolume()
   {
      
      double totalV = 0;
      
      if (elipList.size() == 0)
      {
         return 0;
      }
         
      int index = 0;
      while (index < elipList.size())
      {
         
         totalV += elipList.get(index).volume();
         index++;
            
      }
         
      return totalV;
      
   }
   
   /**
   *Method that adds and returns Surface area of all ellipsoids.
   *@return 0 if size of list equals 0
   *
   */
   
   public double totalSurfaceArea()
   {
      double totalSA = 0;
      
      if (elipList.size() == 0)
      {
         return 0;
      }
      
      int index = 0;
      while (index < elipList.size())
      {
      
         totalSA += elipList.get(index).surfaceArea();
         index++;
         
      }
      
      return totalSA;
   }

   /**
   *Method that reutrns the volume of all the elipsoids.
   *@return 0 if size of list equals 0
   *
   */
   public double averageVolume()
   {
      
      if (elipList.size() == 0)
      {
         return 0;
      }
     
      return totalVolume() / elipList.size();
      
   }
   
   /**
   *Method that returns the average surface area of all
   *the ellipsoids in the list. 
   *@return 0 if list size equals 0
   *@reutrn average surface area of ellipsoids in list
   */
   public double averageSurfaceArea()
   {
     
      if (elipList.size() == 0)
      {
         return 0;
      }
     
      return totalSurfaceArea() / elipList.size();
   }
   
   /**
   *Method that returns a string of the name of the 
   *ellipsoid list.
   *
   *@return listName of the ellipsoid list
   */
   
   public String toString()
   {
   
      String result = "\n" + listName + "\n";
      int index = 0;
      while (index < elipList.size())
      {
      
         result += "\n" + elipList.get(index).toString() + "\n";
         index++;
        
      }
      
      return result;
      
   }
   
   /**
   *Method that returns a series of strings that contain
   *information/summary of all the results of the previous
   *methods. 
   *@return string containing summary
   */
   public String summaryInfo()
   {
   
      DecimalFormat df = new DecimalFormat("#,##0.0##");
      String result = "";
      
      result += "\n----- Summary for " + getName() + " -----";
      result += "\nNumber of Ellipsoid Objects: " 
         + numberOfEllipsoids();
      result += "\nTotal Volume: " + df.format(totalVolume()) + " cubic units";
      result += "\nTotal Surface Area: "
         + df.format(totalSurfaceArea()) + " square units";
      result += "\nAverage Volume: "
         + df.format(averageVolume()) + " cubic units";
      result += "\nAverage Surface Area: "
         + df.format(averageSurfaceArea()) + " square units";
   
      return result;
   }
   
   /**
   *This method returns an array list of ellipsoid objects.
   *
   *@return ArrayList of ellipsoid objects
   *
   */
   public ArrayList<Ellipsoid> getList()
   {
      return elipList;
   
   }
   
   /**
   *This method reads in a file.
   *@return EllipsoidList
   *@param fileNameIn for fileName to read
   *@throws FileNotFoundException if the file cannot be opened.
   */
   public EllipsoidList readFile(String fileNameIn)
                           throws FileNotFoundException
   {
   
      Scanner scanFile = new Scanner(new File(fileNameIn));
      ArrayList<Ellipsoid> myList = new ArrayList<Ellipsoid>();
      String eListName = "";
      double a = 0, b = 0, c = 0;
      
      listName = scanFile.nextLine();
      
      while (scanFile.hasNext()) {
         String label = scanFile.nextLine();
         a = Double.parseDouble(scanFile.next());
         b = Double.parseDouble(scanFile.next());
         c = Double.parseDouble(scanFile.next());
         String after = scanFile.nextLine();
         
         Ellipsoid e = new Ellipsoid(label, a, b, c);
         myList.add(e);
      }
      
      EllipsoidList eL = new EllipsoidList(listName, myList);
      
      return eL;
   }
   
   /**
   *This method creates a new ellipsoid object and adds it to the 
   *ellipsoid list object.
   *
   *@param labelIn string label
   *@param aIn axes parameter
   *@param bIn axes parameter
   *@param cIn axes parameter
   */
   public void addEllipsoid(String labelIn, double aIn, double bIn, double cIn)
   {
      Ellipsoid e = new Ellipsoid(labelIn, aIn, bIn, cIn);
      
      elipList.add(e);
   
   
   }
   
   /**
   *This method finds ellipsoid objects.
   *@param label of ellipsoid object
   *@return ellipsoid list
   */
   public Ellipsoid findEllipsoid(String label)
   {
      Ellipsoid result = null;
    
      for (int i = 0; i < elipList.size(); i++) {
         
         if (elipList.get(i).getLabel().equalsIgnoreCase(label)) {
            result = elipList.get(i);
            break;
         }
      }
      return result;
         
   }
   /**
   *Method that takes a label and returns the corresponding Ellipsoid
   *object if it exists. Otherwise returns null.
   *@param label of deleted ellipsoid
   *@return e object
   */
   public Ellipsoid deleteEllipsoid(String label)
   {
   
      Ellipsoid e = findEllipsoid(label);
      if (e != null) {
         
         elipList.remove(e);
         
      }
         
      return e;
       
   }
     
   
   /**
   *This method edits ellipsoid objects.
   *@return e ellipsoid object
   *@param label edits label of object
   *@param a edits axes of object
   *@param b edits axes of object
   *@param c edits axes of object
   */
   public Ellipsoid editEllipsoid(String label, double a, double b, double c)
   {
      
      Ellipsoid result = null;
    
      for (int i = 0; i < elipList.size(); i++) {
         
         if (elipList.get(i).getLabel().equalsIgnoreCase(label)) {
           
            result = elipList.get(i);
         
            elipList.get(i).setA(a);
            elipList.get(i).setB(b);
            elipList.get(i).setC(c);
          
         }
        
      }
      
      return result;
      
   }
   
}
