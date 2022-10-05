package ej2;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.ArrayList;
public class Ejercicio2App {
	final static double IVAMAX=0.21;
	final static double IVAMIN=0.04;
	public static void main(String[] args) {
		
		Hashtable<String,double[]> productos  = new Hashtable<String,double[]>();
		productos=crearDict();
		
		ArrayList<String> lista= new ArrayList<>();
		lista=pedirProductos(productos);
		imprimeProductos(productos,lista);
		

	}
	private static void imprimeProductos(Hashtable<String, double[]> productos, ArrayList<String> lista) {
		// funcion que imprime la lista de productos, el IVA aplicado, precio bruto, precio+IVA, numero de articulos comprados
		// cantidad pagada y cambio a devolver
		double precio;
		double total;
		String nombre;
		double precio_IVA
		int cantidad;
		System.out.println("Articulo \t Numero Articulos \t precio Bruto \t IVA aplicado \t precio+IVA \t");
		for (String objetos:lista) {
			nombre=objetos.split(",")[0];
			cantidad=Integer.parseInt(objetos.split(",")[1]);
			precio=productos.get(nombre)[0];
			precio_IVA = precio+(productos.get(nombre)[1]*precio);
			System.out.println(objetos.split(",")+" \t ");
		}
		
		
	}
	private static ArrayList<String> pedirProductos(Hashtable<String, double[]> productos) {
		// funcion que retorna una array list con el nombre de los productos añadidos
		Scanner sc= new Scanner(System.in);
		ArrayList<String> nombres = new ArrayList<>();
		String producto;
		Boolean comp;
		int j;
		System.out.print("Introduce cantidad total de productos: ");
		j= Integer.parseInt(sc.nextLine());
		for (int i=0;i<j;i++) {
			comp=true;
			do {
				System.out.print("Introduce nombre del producto: ");
				producto=sc.nextLine().toLowerCase();
				if(productos.containsKey(producto)) comp=false;
				if(comp) System.out.println("Producto no encontrado");
			}while(comp);
			
			System.out.print("Introduce la cantidad: ");
			
			nombres.add(producto+","+sc.nextLine());
		}
		return nombres;
	}
	private static Hashtable<String, double[]> crearDict() {
		// funcion que crea el diccionario de productos con su precio e IVA correspondiente
		Hashtable<String,double[]> dict  = new Hashtable<String,double[]>();
		double[] valores= new double[2];
		valores[0]=0.75;
		valores[1]=IVAMIN;
		dict.put("manzana", valores);
		valores[0]=3.99;
		valores[1]=IVAMAX;
		dict.put("ruffles", valores);
		valores[0]=10;
		valores[1]=IVAMAX;
		dict.put("pelota", valores);
		valores[0]=2.99;
		valores[1]=IVAMIN;
		dict.put("leche", valores);
		
		return dict;
	}

}
