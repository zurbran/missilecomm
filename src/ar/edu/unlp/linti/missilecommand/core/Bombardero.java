package ar.edu.unlp.linti.missilecommand.core;

import java.util.Random;

import ar.edu.unlp.linti.missilecommand.core.KrytolianFactory.KrytolianType;

/**
 * 
 * Clase abstracta que implementa la mayoria de la funcionalidad utilizada por
 * los aviones y satelites bombarderos
 * 
 */
public abstract class Bombardero extends GameObject implements Destroyable, Krytolian
{

	private double altura;
	// Distancia a la que una vez recorrida, se dispara
	private double distDisparo;
	// Cantidad de misiles a disparar
	private int cantMisiles;
	// Direccion en la que se desplaza el bombardero
	private Sentido sentido;
	// Posicion en X de la que se parte
	private double origenX;

	private static final double velocidad = 1.2;
	private static final int minMisiles = 2;
	private static final int maxMisiles = 4;

	public Bombardero(double altura, Sentido sentido)
	{
		Random random = Game.getInstance().getLevel().getRandom();
		this.altura = altura;
		this.sentido = sentido;
		this.cantMisiles = random.nextInt(maxMisiles - minMisiles + 1) + minMisiles;
		this.distDisparo = random.nextDouble() * Game.GAME_WIDTH;

		// Si el sentido es hacia la derecha, empezamos a la izquierda de la
		// pantalla (x=0). De lo contrario x=GAME_WIDTH
		if(this.sentido == Sentido.DERECHA)
		{
			this.origenX = 0;
		}
		else
		{
			this.origenX = Game.GAME_WIDTH;
		}
		this.position = new Position(this.origenX, this.altura);
	}

	@Override
	public void destroyMe()
	{
		this.toRemove = true;
		Game.getInstance().getLevel().encolarGameObject(new Explosion(this.position, false));
	}

	@Override
	void update()
	{
		desplazamiento();
	}

	private void desplazamiento()
	{
		double dist = Math.abs(this.position.getX() - this.origenX);
		// Ya recorrimos la distancia necesaria para disparar?
		if (this.cantMisiles > 0 && dist >= this.distDisparo)
		{
			System.out.println("Crucero disparando " + this.cantMisiles + " misiles");
			for (int i = 0; i < this.cantMisiles; i++)
			{
				try
				{
					Game.getInstance()
							.getLevel()
							.encolarGameObject(
									KrytolianFactory.createKrytolian(KrytolianType.MBI, this.position));
				}
				catch (CreateFailureException e)
				{

				}
			}
			this.cantMisiles = 0;
		}

		// Ya nos salimos de la pantalla?
		if (dist >= Game.GAME_WIDTH)
		{
			this.toRemove = true;
		}
		else
		{
			if(this.sentido == Sentido.DERECHA)
			{
				this.position.setX(this.position.getX() + velocidad);
			}
			else
			{
				this.position.setX(this.position.getX() - velocidad);
			}
		}
		
	}

	/**
	 * @return the sentido
	 */
	public Sentido getSentido()
	{
		return sentido;
	}

}
