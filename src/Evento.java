
import java.util.Iterator;

public class Evento  implements Comparable<Evento> {

	private String nombre;
	private int precio;

	
	public Evento(String nombre, int precio)   throws
    NumeroNegativoException {
		super();
		
		if (precio > 0) {
			
			this.precio = precio;
			this.nombre = nombre;

		}
		
		else {
	         throw new NumeroNegativoException(precio);

			
		}
	}

	
	

	public Evento() {
		// TODO Auto-generated constructor stub
	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public int getPrecio() {
		return precio;
	}




	public void setPrecio(int precio) {
		this.precio = precio;
	}




	@Override
	public String toString() {
		return "El evento "+ nombre + " tiene un precio de "+ precio;
	}


    @Override
    public int compareTo(Evento o) {
        if (precio < o.precio) {
            return -1;
        }
        if (precio > o.precio) {
            return 1;
        }
        return 0;
    }






}
