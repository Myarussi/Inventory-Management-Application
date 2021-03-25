import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
*This program will generate a menu for the 
*user to choose from that will enable them 
*to update or access certain data from 
*files.
*@author Michael Yarussi
*@version 1
*/

public class EllipsoidListMenuApp {

   /**
   *This method esatablishes a menu that allows
   *the user to read a file and create ellipsoid
   *list, print the ellipsoid list, print a summary,
   *add an ellipsoid, delete and ellipsoid, find an ellipsoid,
   *edit an ellipsoid or quit the program. 
   *@param args menu options for user input
   *@throws FileNotFoundException if file is not found
   */
   

   public static void main(String[] args) throws FileNotFoundException {
   
      String eListName = "*** no list name assigned ***";
      ArrayList<Ellipsoid> myList = new ArrayList<Ellipsoid>();
      EllipsoidList myEList = new EllipsoidList(eListName, myList);
      String fileName = "no file name";
      
      String label;
      
      double a, b, c;
      
      String code = "";
      
      Scanner scan = new Scanner(System.in);
      
   
      System.out.println("Ellipsoid List System Menu\n"
                    + "R - Read File and Create Ellipsoid List\n"
                    + "P - Print Ellipsoid List\n"
                    + "S - Print Summary\n"
                    + "A - Add Ellipsoid\n"
                    + "D - Delete Ellipsoid\n"
                    + "F - Find Ellipsoid\n"
                    + "E - Edit Ellipsoid\n"
                    + "Q - Quit");
                 
      do {
         System.out.print("Enter Code [R, P, S, A, D, F, E, Q]: ");
         code = scan.nextLine();
         if (code.length() == 0) {
            continue;
         }
         code = code.toUpperCase();
         char codeChar = code.charAt(0);
         switch(codeChar) {
            case 'R':
               System.out.print("\tFile name: ");
            
               String fileNameIn = scan.nextLine();
               myEList = myEList.readFile(fileNameIn); 
            
               System.out.println("\tFile read in and "
                           + "Ellipsoid List created\n");
               break;
            
            case 'P':
               System.out.println(myEList);
               break;
            
            case 'S':
               System.out.println("\n" + myEList.summaryInfo()
                      + "\n");
               break;
         
            case 'A':
               System.out.print("\tlabel: ");
               label = scan.nextLine();
               System.out.print("\ta: ");
               a = Double.parseDouble(scan.nextLine());
               System.out.print("\tb: ");
               b = Double.parseDouble(scan.nextLine());
               System.out.print("\tc: ");
               c = Double.parseDouble(scan.nextLine());
                    
               myEList.addEllipsoid(label, a, b, c);
               System.out.println("\t*** Ellipsoid Added ***\n");
               break;
            
            case 'D':
               
               System.out.print("\tLabel: ");
               label = scan.nextLine();
               label = label.substring(0, 1).toUpperCase()
                           + label.substring(1).toLowerCase();
             
               if (myEList.deleteEllipsoid(label) == null) {
                     
                  System.out.println("\t\"" + label + "\" not found\n");
                  
               }
               else {
                  System.out.println("\t\"" + label + "\" deleted\n");
               }
                  
               break;
                  
               
                  
            case 'F':
            
               System.out.print("\tLabel: ");
               label = scan.nextLine();
               Ellipsoid f = myEList.findEllipsoid(label);
             
               if (f == null) {
                  
                  System.out.println("\t\"" + label + "\" not found\n");
               }
               else {
                  
                  System.out.println(f.toString() + "\n");
                  
               }
               break;
          
            case 'E':   
             
               System.out.print("\tLabel: ");
               label = scan.nextLine();
               label = label.substring(0, 1).toUpperCase()
                           + label.substring(1).toLowerCase();
               System.out.print("\ta: ");
               a = Double.parseDouble(scan.nextLine());
               System.out.print("\tb: ");
               b = Double.parseDouble(scan.nextLine());
               System.out.print("\tc: ");
               c = Double.parseDouble(scan.nextLine());
             
               if (myEList.findEllipsoid(label) == null) {
                  System.out.println("\t\"" + label + "\" not found\n");
               }
               else {
                  myEList.editEllipsoid(label, a, b, c);
                  System.out.println("\t\"" + label
                                   + "\" successfully edited\n");
               }
               break;
          
            case 'Q':  
            case 'q':
             
               break;
             
            default:   
             
               System.out.println("\t*** invalid code ***\n");
               break;
         }
      } while (!code.equalsIgnoreCase("Q"));                  
   }
}