package Taqueria;


import java.nio.file.*;
import java.util.*;
import java.io.*;

public class Main {

	private static Scanner reader;
	public static void main(String[] args){
		// Clientes.txt testing
	    ClientFileIO clientDB = new ClientFileIO("db/Clientes.txt");
	    
	    clientDB.addClient("Juan Perez");
	    clientDB.addClient("Rosa Melo");
	    clientDB.addClient("Benito Camelo");
	    
	    clientDB.removeClient("Benito Camelo");
	    
	    clientDB.addClient("Flex Plexico");
	    
	    clientDB.removeClient("Flex Plexico");
	    
	    clientDB.addClient("A");
	    clientDB.addClient("B");
	    	    
	    clientDB.writeChanges();
	    
	    // Ordenes.txt testing
	    OrderFileIO orderDB = new OrderFileIO("db/Ordenes.txt");
	    
	    Order o1 = new Order(1, 1, 1, 1);
	    Order o2 = new Order(1, 2, 2, 2);
	    Order o3 = new Order(2, 2, 2, 2);
	    Order o4 = new Order(3, 3, 3, 3);
	    
	    orderDB.addOrder(o1);
	    orderDB.addOrder(o2);
	    orderDB.addOrder(o3);
	    orderDB.addOrder(o4);
	    
	    ArrayList<Order> orders = orderDB.getOrder(1);
		menu();
	}

	// Display menu and handle its options
	private static void menu(){
		int sel = 0;
		do{
			ArrayList<String> opciones = new ArrayList<>();
			reader = new Scanner(System.in);
			opciones.add("Taquería - Sistema de archivos - Menu");
			opciones.add("Elija una opción escribiendo el número correspondiente:");
			opciones.add("1. Crear archivo.");
			opciones.add("2. Eliminar archivo.");
			opciones.add("3. Actualizar archivo.");
			opciones.add("4. Buscar.");
			opciones.add("5. Salir.");

			opciones.forEach(opcion -> System.out.println(opcion));
			try{
				sel = Integer.parseInt(reader.next().trim());
				switch (sel){
					case 1: creaArchivo();
						break;
					case 2: eliminaArchivo();
						break;
					case 3: actualizaArchivo();
						break;
					case 4: buscaArchivo();
						break;
					case 5: System.out.println("Bye bye");
						break;
					default: System.out.println("Opción inválida.");
						break;
				}
			}catch(NumberFormatException e){
				System.out.println("Eso no es un número.");
			}

		}while(sel != 5);

	}

	private static void creaArchivo(){
		System.out.println("Introduzca el nombre del archivo a crear; se creará un archivo vacío con ese nombre en extensión .txt en la carpeta ../../db.");
		String filename = reader.next().trim();
		try{
			Path path = Paths.get("db/" + filename + ".txt");
			//Files.createDirectories(path.getParent());
			Files.createFile(path);
		}catch(FileAlreadyExistsException e){
			System.out.println("Ya existe ese archivo!");
		}catch(IOException e){
			System.out.println("Algo salió mal.");
		}
	}

	private static void eliminaArchivo(){
		System.out.println("Introduzca el nombre del archivo a eliminar (sin extensión).");
		String filename =reader.next().trim();
		try{
			if(filename.equals("Clientes")){
				throw new IllegalArgumentException("No se puede eliminar el archivo Clientes!");
			}
			Path path = Paths.get("db/" + filename + ".txt");
			Files.delete(path);

		}catch(NoSuchFileException e){
			System.out.println("No existe tal archivo!");
		}catch(IOException e){
			System.out.println("Algo salió mal.");
		}catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
	}

	private static void actualizaArchivo(){
		System.out.println("Elija la opción escribiendo el número correspondiente:");
		System.out.println("1. Agregar una orden");
		System.out.println("2. Agregar un cliente");
		System.out.println("3. Eliminar un cliente");
		System.out.println("4. Cambiar nombre de un cliente");
		System.out.println("5. Cancelar");

		Integer opcion = Integer.parseInt(reader.next().trim());
		try {
			switch (opcion) {
				case 1:
					int[] ord = new int[4];
					System.out.println("Escriba el id del cliente");
					ord[0] = Integer.parseInt(reader.next().trim());
					System.out.println("Escriba el id del platillo");
					ord[1] = Integer.parseInt(reader.next().trim());
					System.out.println("Escriba la cantidad del platillo:");
					ord[2] = Integer.parseInt(reader.next().trim());
					System.out.println("Escriba el total del costo de la orden:");
					ord[3] = Integer.parseInt(reader.next().trim());
					new OrderFileIO("db/Ordenes.txt").addOrder(new Order(ord[0], ord[1], ord[2], ord[3]));
					break;
				case 2:
					String nombre;
					System.out.println("Escriba el nombre del cliente");
					nombre = reader.next().trim();
					new ClientFileIO("db/Clientes.txt").addClient(nombre);
					break;
				case 3:
					System.out.println("Escriba el id del cliente:");
					int id = Integer.parseInt(reader.next().trim());
					ClientFileIO clientfileio = new ClientFileIO("db/Clientes.txt");
					System.out.println(clientfileio.getClient(id).getName());
					clientfileio.removeClient(clientfileio.getClient(id));
					clientfileio.writeChanges();
					break;
				case 4:
					System.out.println("Escriba el id del cliente:");
					int id2 = Integer.parseInt(reader.next().trim());
					ClientFileIO clientfileio2 = new ClientFileIO("db/Clientes.txt");
					System.out.println(clientfileio2.getClient(id2).getName());
					System.out.println("Escriba el nuevo nombre:");
					String name = reader.next().trim();
					clientfileio2.getClient(id2).setName(name);
					clientfileio2.writeChanges();
					break;
				case 5:
					break;
				default:
					System.out.println("Opción inválida");
					break;
			}
		}catch(Exception e){
			e.printStackTrace();
		}


	}

	private static void buscaArchivo(){
		ArrayList<String> opciones = new ArrayList<>();
		Scanner reader = new Scanner(System.in);
		opciones.add("Búsquedas:");
		opciones.add("Introduzca el ID del cliente del que se debe hacer el reporte:");

		opciones.forEach((opcion) -> System.out.println(opcion));
		Integer idClient = Integer.parseInt(reader.next().trim());

		Client client = null;
		ArrayList<Client> clients = new Taqueria.ClientFileIO("db/Clientes.txt").getClients();
		for(Client client1 : clients){
			if(client1.getID() == idClient){
				client = client1;
				break;
			}
		}
		if(client == null){
			System.out.println("Persona no es Cliente");
			return;
		}
		System.out.println(client.getName());
		ArrayList<Order> orders = new OrderFileIO("db/Ordenes.txt").getOrders();
		int totalOrders = 0;
		for(Order order1 : orders){
			if(order1.getClientID() == client.getID())
				totalOrders++;
		}

		try{
			Files.write(Paths.get("reporte_"+client.getID()+".txt"), Arrays.asList(client.getName() + ":" + totalOrders));
		}catch(IOException e){
			System.out.println("Algo salió mal.");
		}
	}

}