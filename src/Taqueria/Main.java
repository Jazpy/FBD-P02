package Taqueria;

import java.util.*;

public class Main {

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
			Scanner reader = new Scanner(System.in);
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
					default: System.out.println("Opción inválida :(");
						break;
				}
			}catch(NumberFormatException e){
				System.out.println("Eso no es un número.");
			}

		}while(sel != 5);

	}

	private static void creaArchivo(){}

	private static void eliminaArchivo(){}

	private static void actualizaArchivo(){}

	private static void buscaArchivo(){}

}