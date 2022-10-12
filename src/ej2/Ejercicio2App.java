package ej2;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.ArrayList;
public class Ejercicio2App {
	final static double IVAMAX=0.21;
	final static double IVAMIN=0.04;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Hashtable<String,double[]> productos  = new Hashtable<String,double[]>();
		productos=crearDict();
		
		ArrayList<String> lista= new ArrayList<>();
		lista=pedirProductos(productos,sc);
		imprimeProductos(productos,lista,sc);
		sc.close();

	}
	private static void imprimeProductos(Hashtable<String, double[]> productos, ArrayList<String> lista, Scanner sc) {
		// funcion que imprime la lista de productos, el IVA aplicado, precio bruto, precio+IVA, numero de articulos comprados
		// cantidad pagada y cambio a devolver
		double precio;
		double total = 0;
		String nombre;
		double precio_IVA;
		int cantidad,cantidadt=0;
		System.out.printf("%12s %12s %12s %12s %12s\n","Articulo"," Numero Articulos","precio Bruto","IVA aplicado","precio+IVA");
		for (String objetos:lista) {
			nombre=objetos.split(",")[0];
			cantidad=Integer.parseInt(objetos.split(",")[1]);
			cantidadt+=cantidad;
			precio=productos.get(nombre)[0]*cantidad;
			precio_IVA = precio+(productos.get(nombre)[1]*precio);
			total+=precio_IVA;
			System.out.printf("%12s %12s %12s %12s %12.2f\n",nombre,cantidad,precio,((productos.get(nombre)[1])*100),precio_IVA);
		}
		System.out.printf("%12s %12s %38.2f\n", "Total",cantidadt,total);
		double cantidadp;
		boolean comp=false;
		do {
			System.out.print("Entra cantidad pagada: ");
			cantidadp=Double.parseDouble(sc.nextLine());
			if(cantidadp<total) System.out.println("Valor menor al importe total. Vuelve a introducir cantidad...");
			else comp=true;
		}while(!comp);
		System.out.printf("El cambio sera de %.2f€\n",(cantidadp-total));
		
		
	}
	private static ArrayList<String> pedirProductos(Hashtable<String, double[]> productos, Scanner sc) {
		// funcion que retorna una array list con el nombre de los productos añadidos
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
		double[] valores= {0.75,IVAMIN};
		dict.put("manzana",valores.clone());
		valores[0]=3.99;
		valores[1]=IVAMAX;
		dict.put("ruffles", valores.clone());
		valores[0]=10;
		valores[1]=IVAMAX;
		dict.put("pelota", valores.clone());
		valores[0]=2.99;
		valores[1]=IVAMIN;
		dict.put("leche", valores.clone());
		
		return dict;
	}

}
