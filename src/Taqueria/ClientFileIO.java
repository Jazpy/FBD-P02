package Taqueria;

import java.util.*;
import java.io.*;

public class ClientFileIO {
	private String filename;
	public ArrayList<String> clientList;
	
	public ClientFileIO(String filename) {
		this.filename = filename;
		clientList = new ArrayList<String>();
		
		try {
			FileReader in = new FileReader(filename);
			
			// Read the file
			BufferedReader br = new BufferedReader(in);
			String line;
			
			while ((line = br.readLine()) != null) {
				// Tokenize string to read id and name separately
				StringTokenizer st = new StringTokenizer(line);
				
				// Assume first token is id number, we throw it away
				st.nextToken();
				
				// Paste together name
				String name  = "";
			
				while (st.hasMoreTokens())
					name += st.nextToken() + " ";
				
				// Remove trailing space
				name = name.trim();
								
				// Add the client to the ArrayList, note
				// that client ID will correspond to
				// ArrayList index.
				clientList.add(name);
			}
			
			br.close();
		} catch (IOException e) {
			// Print error if file could not be read
			e.printStackTrace();
		}
	}
	
	public Client getClient(int id) {
		return new Client(id, clientList.get(id));
	}
	
	public void addClient(String toAdd) {
		
		if(!clientList.contains(toAdd))
			clientList.add(toAdd);
	}
	
	public void removeClient(String toRemove) {
		clientList.remove(toRemove);
	}
	
	// No changes will be saved unless this method is called
	public void writeChanges() {
		
		try {
			File file = new File(filename);

			// Create BufferedWriter
			FileWriter out = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(out);
			
			// Write a line for every element in ArrayList
			for(int i = 0; i != clientList.size(); ++i) {
				bw.write(i + " " + clientList.get(i));
				bw.newLine();
			}
			
			bw.close();
		} catch (IOException e) {
			// Print error if file could not be written
			e.printStackTrace();
		}
	}
}