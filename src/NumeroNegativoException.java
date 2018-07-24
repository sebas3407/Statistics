
public class NumeroNegativoException extends Exception
{
   private int cantidad;
   public NumeroNegativoException (int cantidad)
   {
      this.cantidad = cantidad;
   }
   public double getCantidad()
   {
      return cantidad;
   }
}