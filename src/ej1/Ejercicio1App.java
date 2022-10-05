package ej1;
import java.util.Scanner;
import java.util.Enumeration;
import java.util.Hashtable;
public class Ejercicio1App {

	public static void main(String[] args) {
		Hashtable<String,String> notas_medias = new Hashtable<String,String>();
		Enumeration<String> nombres;
		String nombre;
		notas_medias=insertarAlumnos();		//llamamos a la funcion insertarAlumnos para guardar los datos en el diccionario
		nombres=notas_medias.keys();		//usamos una variable tipo enum para enumerar las llaves del diccionario
	
		while(nombres.hasMoreElements()) {	//recorremos el diccionario por sus claves
			nombre=nombres.nextElement();	//guardamos siguiente elemento en un String
			System.out.println(""+nombre+": "+notas_medias.get(nombre));	//Imprimimos por pantalla el valor de la llave y su respectiva nota media
		}

	}

	private static Hashtable<String, String> insertarAlumnos() {
		
		Scanner sc= new Scanner(System.in);
		int alumnos, materias;
		double nota,notamedia=0;
		String nombre,s_notamedia;
		Boolean comp;
		Hashtable<String,String> alumnos_notas= new Hashtable<String,String>();
		
		System.out.print("Inserta alumnos totales: ");//Pedimos total de alumnos
		alumnos=Integer.parseInt(sc.nextLine());
		
		System.out.print("Inserta materias totales: ");//pedimos total de materias
		materias=Integer.parseInt(sc.nextLine());
		
		for(int i=0;i<alumnos;i++) {//iteramos por la cantidad de alumnos que se quieren añadir
			System.out.print("Inserta nombre del alumno: ");//pedimos el nombre del alumno
			nombre=sc.nextLine();
			notamedia=0;									//reiniciamos notas por cada alumno nuevo
			for(int j=0;j<materias;j++) {
				comp=true;				//usamos booleano para comprobar si los valores estan entre los rangos normales, se reincia al inicio de cada materia correcta
				do {
					System.out.print("Inserta nota de la materia"+" "+(j+1)+": "); //j+1 para que no aparezca materia 0
					nota=Double.parseDouble(sc.nextLine());
					if(nota>=0 || nota<=10) comp=false;								//comprobamos si la nota esta dentro del rango, si es así el comprobador es false
					else System.out.println("Nota incorrecta.");
				}while(comp);														//Si es incorrecta sigue en el bucle, si es correcta sale de el
				notamedia+=nota;													//cada nota correcta se suma a la media total
			}
			s_notamedia=""+(notamedia/materias);									//al finalizar de añadir notas se hace la media y se guarda en un string
			alumnos_notas.put(nombre, s_notamedia);									//se añade al diccionario por el nombre y su nota media.
		}
		return alumnos_notas;														//retornamos el diccionario
	}

}
