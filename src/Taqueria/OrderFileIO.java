package Taqueria;

import java.util.*;
import java.util.regex.Pattern;
import java.io.*;

public class OrderFileIO {
	private String filename;
	
	public OrderFileIO(String filename) {
		this.filename = filename;
	}
	
	public ArrayList<Order> getOrder(int id) {
		ArrayList<Order> orders = new ArrayList<Order>();
		
		try {
			// Set up file reading
			File file = new File(filename);
			
			FileReader in = new FileReader(file.getAbsolutePath());
			
			// Read the file
			BufferedReader br = new BufferedReader(in);
			String line;
			
			while ((line = br.readLine()) != null && !line.equals("")) {
				// Tokenize string with split, since we have delimiters in place
				String[] tokens = line.split(Pattern.quote("|"));
								
				// Check if it's the desired id
				if  (Integer.parseInt(tokens[0]) == (id)) {
					// Use tokens to create an Order object
					Order order = new Order(Integer.parseInt(tokens[0]),
							Integer.parseInt(tokens[1]),
							Integer.parseInt(tokens[2]),
							Integer.parseInt(tokens[3]));
					
					orders.add(order);
				}
			}
			
			br.close();
		} catch (IOException e) {
			// Print error if file could not be read
			e.printStackTrace();
		}
		
		return orders;
	}
	
	public void addOrder(Order toAdd) {
		
		try {
			File file = new File(filename);

			// Create BufferedWriter
			FileWriter out = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(out);
			
			// Append to file
			bw.write(toAdd.toString());
			bw.newLine();
			
			bw.close();
		} catch (IOException e) {
			// Print error if file could not be written
			e.printStackTrace();
		}
	}
}