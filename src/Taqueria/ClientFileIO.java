package Taqueria;

import java.util.*;
import java.io.*;

public class ClientFileIO {
	private String filename;
	private int nextID;
	public ArrayList<Client> clientList;
	
	public ClientFileIO(String filename) {
		this.filename = filename;
		clientList = new ArrayList<Client>();
		nextID = 0;
		
		try {
			// Set up file reading
			File file = new File(filename);
						
			FileReader in = new FileReader(file.getAbsolutePath());
			
			// Read the file
			BufferedReader br = new BufferedReader(in);
			String line;
			
			// Add to ArrayList
			while ((line = br.readLine()) != null) {
				// Tokenize string to read id and name separately
				StringTokenizer st = new StringTokenizer(line);
				
				// Assume first token is id number
				nextID = Integer.parseInt(st.nextToken());
								
				// Paste together name
				String name  = "";
			
				while (st.hasMoreTokens())
					name += st.nextToken() + " ";
				
				// Remove trailing space
				name = name.trim();
				
				// Create client
				Client curr = new Client(nextID, name);
								
				// Add the client to the ArrayList, note
				// that client ID will correspond to
				// ArrayList index.
				clientList.add(curr);
			}
			
			br.close();
		} catch (IOException e) {
			// Print error if file could not be read
			e.printStackTrace();
		}
	}
	
	public Client getClient(int id) {
		Iterator<Client> iter = clientList.iterator();
		
		while (iter.hasNext()){
			Client curr = iter.next();
			
			if(curr.getID() == id)
				return curr;
		}
		
		return null;
	}
	
	public void addClient(String toAdd) {
		Iterator<Client> iter = clientList.iterator();
		
		while (iter.hasNext()){
			
			if(iter.next().getName().equals(toAdd))
				return;
		}
		
		Client add = new Client(nextID, toAdd);
		
		clientList.add(add);
		
		++nextID;
	}
	
	public void removeClient(Client toRemove) {
		if(clientList.get(clientList.size() - 1).equals(toRemove))
			++nextID;
		
		clientList.remove(toRemove);
	}
	
	public void removeClient(int id) {
		Iterator<Client> iter = clientList.iterator();
		
		while (iter.hasNext()){
			Client curr = iter.next();
			
			if(curr.getID() == id) {
				clientList.remove(curr);
				
				if(!iter.hasNext())
					++nextID;
					
				return;
			}
		}
	}
	
	public void removeClient(String name) {
		Iterator<Client> iter = clientList.iterator();
		
		while (iter.hasNext()){
			Client curr = iter.next();
			
			if(curr.getName() == name) {
				clientList.remove(curr);
				
				if(!iter.hasNext())
					++nextID;
				
				return;
			}
		}
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
				bw.write(clientList.get(i).getID() + " " + clientList.get(i).getName());
				bw.newLine();
			}
			
			bw.close();
		} catch (IOException e) {
			// Print error if file could not be written
			e.printStackTrace();
		}
	}
}