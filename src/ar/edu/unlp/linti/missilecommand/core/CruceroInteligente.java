package ar.edu.unlp.linti.missilecommand.core;

/**
 * 
 * Implementacion de un CruceroInteligente. Cuando detecta una explosion por
 * debajo, intenta esquivarla moviendose para la derecha o izquierda hasta que
 * considere que esta libre de peligro
 * 
 */
public class CruceroInteligente extends Crucero
{

	/**
	 * 
	 * @param origen Posicion en la que aparece
	 * @param destino Posicion a la que se dirige y en la que explotara
	 * @param velocidad Velocidad a la que se desplaza, cantidad de unidades, por unidad de tiempo
	 */
	public CruceroInteligente(Position origen, Position destino, double velocidad)
	{
		super(origen, destino, velocidad);
		System.out.println("Crucero Inteligente generado origen=" + origen.toString() + " destino="
				+ destino.toString() + " velocidad=" + velocidad);
	}

	@Override
	public void update()
	{
		desplazamiento();
	}

	protected void desplazamiento()
	{
		double dist = this.origen.getEuclideanDistance(destino);
		double actDist = this.position.getEuclideanDistance(destino);

		for (GameObject obj : Game.getInstance().getGameObjects())
		{
			if (obj instanceof Explosion)
			{
				Explosion explosion = (Explosion) obj;
				// Esta por debajo nuestro?
				if (explosion.getPosition().getY() > this.position.getX())
				{
					// Esta cerca como para ser un peligro?
					if (this.position.getEuclideanDistance(explosion.getPosition()) < ((CircleShape) explosion
							.getShape()).getRadius() + 20)
					{
						if (this.position.getX() > explosion.getPosition().getX())
						{
							this.position.setX(this.position.getX() + this.velocidad);
						}
						else
						{
							this.position.setX(this.position.getX() - this.velocidad);
						}
					}
				}
			}
		}

		this.position.setX(this.position.getX()
				+ ((this.destino.getX() - this.position.getX()) / actDist) * this.velocidad);
		this.position.setY(this.position.getY()
				+ ((this.destino.getY() - this.position.getY()) / actDist) * this.velocidad);

		estela.setPosition(this.position);
		
		// Ya llegamos a destino?
		if (this.position.getEuclideanDistance(origen) > dist)
		{
			explotar(true);
		}
	}
}
