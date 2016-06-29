package ar.edu.unlp.linti.missilecommand.core;

/**
 * Crucero Tonto. Se comporta de forma identica a un MBI, pero tiene forma cuadrada
 * 
 */
public class CruceroTonto extends Crucero
{

	/**
	 * 
	 * @param origen Posicion en la que aparece
	 * @param destino Posicion a la que se dirige y en la que explotara
	 * @param velocidad Velocidad a la que se desplaza, cantidad de unidades, por unidad de tiempo
	 */
	public CruceroTonto(Position origen, Position destino, double velocidad)
	{
		super(origen, destino, velocidad);
		System.out.println("Crucero Tonto generado origen=" + origen.toString() + " destino=" + destino.toString() + " velocidad=" + velocidad);
	}

}
