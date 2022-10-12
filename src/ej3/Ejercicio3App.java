package ej3;
import javax.swing.JOptionPane;

import java.util.Enumeration;
import java.util.Hashtable;

public class Ejercicio3App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hashtable<String,double[]> productos  = new Hashtable<String,double[]>();
		productos=creaBD();
		int menu=0;
		do {
			menu=Integer.parseInt(JOptionPane.showInputDialog("--------------------Menu--------------------\n1. Insertar nuevo producto\n2. Listar producto\n3. Listar todos los productos\n4,Salir\n Introduce valor de la opcion: "));
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
					JOptionPane.showMessageDialog(null, "Saliendo de la aplicación...");
					break;
				default:
					JOptionPane.showMessageDialog(null, "Valor incorrecto");
					break;
			}
			
		}while(menu!=4);
		
		
		
	}

	private static void imprimeProducto(Hashtable<String, double[]> productos) {
		// método que imprime el producto deseado
		boolean comp=false;
		String producto;
		do {
			producto=JOptionPane.showInputDialog("Introduce nombre del producto");
			if(productos.containsKey(producto)) comp=true;
			if(!comp) JOptionPane.showMessageDialog(null, "Producto no encontrado");
			
		}while(!comp);
		
		System.out.println("Nombre: "+producto+ " Precio: "+ productos.get(producto)[0]+ " Cantidad: "+productos.get(producto)[1]);
	}

	private static void imprimerProductos(Hashtable<String, double[]> productos) {
		// Método que imprime todos los productos del catálogo
		
		Enumeration<String> nombres;
		String nombre;
		nombres=productos.keys();
		
		while(nombres.hasMoreElements()) {	
			nombre=nombres.nextElement();	
			System.out.println("Nombre: "+nombre+ " Precio: "+ productos.get(nombre)[0]+ " Cantidad: "+productos.get(nombre)[1]);
		}
		
	}

	private static Hashtable<String, double[]> insertaProducto(Hashtable<String, double[]> productos) {
		//metodo para insertar un producto 
		String nombre=JOptionPane.showInputDialog("Introduce nombre del producto");
		double[] val = new double[2];
		val[1]=Double.parseDouble(JOptionPane.showInputDialog("Introduce la cantidad"));
		val[0]=Double.parseDouble(JOptionPane.showInputDialog("Introduce el precio del producto"));
		productos.put(nombre,val);
		return productos;
	}

	private static Hashtable<String, double[]> creaBD() {
		// método que crea una hastable con los productos.
		Hashtable<String, double[]> prod  = new Hashtable<String,double[]>();
		double[] valores= new double[2];
		valores[0]= 424.99;
		valores[1]=4;
		prod.put("PS5",valores);
		valores[0]= 420.99;
		valores[1]=6;
		prod.put("XBOX ONE",valores);
		valores[0]=325.49;
		valores[1]=20;
		prod.put("TV SAMSUNG", valores);
		
		return prod;
		
	}
}
