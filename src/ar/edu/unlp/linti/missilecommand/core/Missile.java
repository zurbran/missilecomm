package ar.edu.unlp.linti.missilecommand.core;

import java.awt.Color;

/**
 * Clase abstracta que contiene propiedades y comportamiento comun a todos los
 * misiles, como desplazamiento y explosion.
 * 
 * 
 */
public abstract class Missile extends GameObject
{

	protected Position origen;
	protected Position destino;
	protected double velocidad;
	protected Estela estela;

	/**
	 * @param origen
	 * @param destino
	 */

	public Missile(Position origen, Position destino, double velocidad, Color color)
	{
		super(origen);
		this.origen = new Position(origen);
		this.destino = new Position(destino);
		this.velocidad = velocidad;
		estela = new Estela(origen, color);
		Game.getInstance().getLevel().encolarGameObject(estela);
	}

	public void update()
	{
		desplazamiento();
	}

	protected void desplazamiento()
	{
		double dist = this.origen.getEuclideanDistance(destino);
		this.position.setX(this.position.getX()
				+ ((this.destino.getX() - this.origen.getX()) / dist)
				* this.velocidad);
		this.position.setY(this.position.getY()
				+ ((this.destino.getY() - this.origen.getY()) / dist)
				* this.velocidad);
		
		estela.setPosition(this.position);
		
		// Ya llegamos a destino?
		if(this.position.getEuclideanDistance(origen) > dist)
		{
			explotar(!(this instanceof MAB));
		}
	}
	
	protected void explotar(boolean ignoreScore)
	{
		this.toRemove = true;
		this.estela.toRemove = true;
		Game.getInstance().getLevel().encolarGameObject(new Explosion(this.position, ignoreScore));
	}
}
