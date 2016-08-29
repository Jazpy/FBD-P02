
package Taqueria;

import java.util.*;
import java.util.regex.Pattern;
import java.io.*;

public class MenuFileIO {
    private final String filename;
    
    public MenuFileIO(String filename){
        this.filename = filename;
    }
    
    public ArrayList<Menu> getOrder(String dish){
        ArrayList<Menu> menu = new ArrayList<>();
        try {
            // Set up file reading
            File file = new File(filename);
			
            FileReader in = new FileReader(file.getAbsolutePath());
			
            // Read the file
            BufferedReader br = new BufferedReader(in);
            String line;
			
            while ((line = br.readLine()) != null && !line.equals("")) {
                // Tokenize string with split, since we have delimiters in place
                String[] tokens = line.split(Pattern.quote(","));
								
                // Check if it's the desired dish
		if  (tokens[0].equals(dish)){
                    // Use tokens to create a Menu object
                    Menu dish2 = new Menu(Integer.parseInt(tokens[0]),
                    tokens[1],
                    Double.parseDouble(tokens[2]));
					
                    menu.add(dish2);
		}
            }
            br.close();
	} catch (IOException e) {
            // Print error if file could not be read
            e.printStackTrace();
	}
		
	return menu;
    } 
    
    public void changePrice(Menu toChange){
         ArrayList<Menu> menu = new ArrayList<>();
        try{
            File fileOld = new File(filename);
            FileReader in = new FileReader(fileOld.getAbsolutePath());

            // Read the file
            BufferedReader br = new BufferedReader(in);
            String line;
            
             while ((line = br.readLine()) != null && !line.equals("")) {
                // Tokenize string with split, since we have delimiters in place
                String[] tokens = line.split(Pattern.quote(","));
								
                // Check if it's the desired dish
		if  (tokens[1].equals(toChange.getDish())){
                    // Use tokens to find the dish to replace.
                    menu.add(toChange);
		}else{
                    Menu dish2 = new Menu(Integer.parseInt(tokens[0]),
                    tokens[1],
                    Double.parseDouble(tokens[2]));
					
                    menu.add(dish2);
                }
            }
            br.close();
            fileOld.delete();

            File fileNew = new File(filename);

            //Create BufferedWritter
            FileWriter out = new FileWriter(fileNew.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(out);
            
            for(Menu dish : menu){
                bw.write(dish.toString());  
            }
            
            bw.close();
            
        }catch(IOException e){
            // Print error if file could not be written
            e.printStackTrace();
        }
    }
}
