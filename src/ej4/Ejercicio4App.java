package ej4;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Ejercicio4App {

	final static double IVAMAX=0.21;
	final static double IVAMIN=0.04;
	
	public static void main(String[] args) {
		
		Hashtable<String,double[]> productos  = new Hashtable<String,double[]>();
		productos=creaBD();
		int menu=0;
		do {
			menu=Integer.parseInt(JOptionPane.showInputDialog("--------------------Menu--------------------\n1. Insertar nuevo producto\n2. Listar producto\n3. Listar todos los productos\n"
					+ "4.Comprar Productos\n5,Salir\n Introduce valor de la opcion: "));
			switch(menu) {
				case 1:
					productos=insertaProducto(productos);
					break;
				case 2:
					imprimeProducto(productos);
					break;
				case 3:
					imprimerProductos(productos);
					break;
				case 4:
					comprarProductos(productos);
					break;
				case 5:
					JOptionPane.showMessageDialog(null, "Saliendo de la aplicación...");
					break;
				default:
					JOptionPane.showMessageDialog(null, "Valor incorrecto");
					break;
			}
			
		}while(menu!=5);

	}
	
	private static void comprarProductos(Hashtable<String, double[]> productos) {
		// TODO Auto-generated method stub
		
		ArrayList<String> lista= new ArrayList<>();
		lista=pedirProductos(productos);
		imprimeProductos(productos,lista);
	}

	private static void imprimeProductos(Hashtable<String, double[]> productos, ArrayList<String> lista) {
		// funcion que imprime la lista de productos, el IVA aplicado, precio bruto, precio+IVA, numero de articulos comprados
		// cantidad pagada y cambio a devolver
		double precio;
		double total = 0;
		String nombre;
		double precio_IVA;
		double cantidad,cantidadt=0;
		System.out.printf("%15s %15s %15s %15s %15s\n","Articulo"," Numero Articulos","precio Bruto","IVA aplicado","precio+IVA");
		for (String objetos:lista) {
			nombre=objetos.split(",")[0];
			cantidad=Double.parseDouble(objetos.split(",")[1]);
			cantidadt+=cantidad;
			precio=productos.get(nombre)[0]*cantidad;
			precio_IVA = precio+(productos.get(nombre)[2]*precio);
			total+=precio_IVA;
			System.out.printf("%15s %15.0f %15.2f %15s %15.2f\n",nombre,cantidad,precio,((productos.get(nombre)[2])*100),precio_IVA);
		}
		System.out.printf("%15s %15.0f %47.2f\n", "Total",cantidadt,total);
		double cantidadp;
		boolean comp=false;
		do {
			cantidadp=Double.parseDouble(JOptionPane.showInputDialog("Entra cantidad pagada: "));
			if(cantidadp<total) JOptionPane.showMessageDialog(null,"Valor menor al importe total. Vuelve a introducir cantidad...");
			else comp=true;
		}while(!comp);
		System.out.printf("El cambio sera de %.2f€\n",(cantidadp-total));
	}

	private static ArrayList<String> pedirProductos(Hashtable<String, double[]> productos) {
		// funcion que retorna una array list con el nombre de los productos añadidos
		ArrayList<String> nombres = new ArrayList<>();
		String producto;
		double cantidad;
		Boolean comp;
		int j;
		j= Integer.parseInt(JOptionPane.showInputDialog("Introduce cantidad total de productos: "));
		for (int i=0;i<j;i++) {
			comp=true;
			do {
				producto=JOptionPane.showInputDialog("Introduce nombre del producto: ");
				if(productos.containsKey(producto)) comp=false;
				if(comp) JOptionPane.showMessageDialog(null,"Producto no encontrado");
			}while(comp);
			comp=true;		
			do {
				cantidad=Double.parseDouble(JOptionPane.showInputDialog("Introduce la cantidad: "));
				if(productos.get(producto)[1]>=cantidad) comp=false;
				if(comp) JOptionPane.showMessageDialog(null,"No hat suficiente stock. Elija una cantidad menor..");
			}while(comp);
				
			nombres.add(producto+","+cantidad);
		}
		return nombres;
	}

	private static void imprimeProducto(Hashtable<String, double[]> productos) {
		// método que imprime el producto deseado
		boolean comp=false;
		String producto;
		do {
			producto=(JOptionPane.showInputDialog("Introduce nombre del producto").toLowerCase());
			if(productos.containsKey(producto)) comp=true;
			if(!comp) JOptionPane.showMessageDialog(null, "Producto no encontrado");
			
		}while(!comp);
		
		System.out.printf("Nombre: %s Precio: %.2f Cantidad: %.0f IVA aplicado: %.0f\n ",producto,(productos.get(producto)[0]),(productos.get(producto)[1]),((productos.get(producto)[2])*100));
	}

	private static void imprimerProductos(Hashtable<String, double[]> productos) {
		// Método que imprime todos los productos del catálogo
		
		Enumeration<String> nombres;
		String nombre;
		nombres=productos.keys();
		
		while(nombres.hasMoreElements()) {	
			nombre=nombres.nextElement();	
			System.out.printf("Nombre: %s Precio: %.2f Cantidad: %.0f IVA aplicado: %.0f\n ",nombre,(productos.get(nombre)[0]),(productos.get(nombre)[1]),((productos.get(nombre)[2])*100));
		}
		
	}

	private static Hashtable<String, double[]> insertaProducto(Hashtable<String, double[]> productos) {
		//metodo para insertar un producto 
		String nombre=(JOptionPane.showInputDialog("Introduce nombre del producto").toLowerCase());
		double[] val = new double[3];
		val[1]=Double.parseDouble(JOptionPane.showInputDialog("Introduce la cantidad"));
		val[0]=Double.parseDouble(JOptionPane.showInputDialog("Introduce el precio del producto"));
		val[2]=Double.parseDouble(JOptionPane.showInputDialog("Introduce el IVA a aplicar"));
		productos.put(nombre,val);
		return productos;
	}

	private static Hashtable<String, double[]> creaBD() {
		// método que crea una hastable con los productos.
		Hashtable<String, double[]> prod  = new Hashtable<String,double[]>();
		double[] valores= new double[3];
		
		valores[0]= 424.99;
		valores[1]=4;
		valores[2]=IVAMAX;
		prod.put("ps5",valores.clone());
		
		valores[0]= 420.99;
		valores[1]=6;
		valores[2]=IVAMAX;
		prod.put("xboxone",valores.clone());
		
		valores[0]=325.49;
		valores[1]=20;
		valores[2]=IVAMAX;
		prod.put("tv", valores.clone());
		
		valores[0]=0.75;
		valores[1]=50;
		valores[2]=IVAMIN;
		prod.put("manzana", valores.clone());
		
		valores[0]=3.99;
		valores[1]=40;
		valores[2]=IVAMAX;
		prod.put("ruffles", valores.clone());
		
		valores[0]=10;
		valores[1]=30;
		valores[2]=IVAMAX;
		prod.put("pelota", valores.clone());
		
		valores[0]=2.99;
		valores[1]=60;
		valores[2]=IVAMIN;
		prod.put("leche", valores.clone());
		
		return prod;
		
	}

}
