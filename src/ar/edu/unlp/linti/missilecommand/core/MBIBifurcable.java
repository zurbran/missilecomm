package ar.edu.unlp.linti.missilecommand.core;

import java.util.Random;

import ar.edu.unlp.linti.missilecommand.core.KrytolianFactory.KrytolianType;

/**
 * 
 * Representa un Misil Balistico Interplanetario de trayectoria recta que puede bifurcarse
 * en forma aleatoria.
 * 
 */
public class MBIBifurcable extends MBI
{
	private double distBifurcacion;
	private int cantBifurcacion;
	private boolean yaBifurco;
	
	private static final double MAX_BIFURCACION = 270.0;
	private static final int MAX_CANTBIFURCACION = 3;
	private static final int MIN_CANTBIFURCACION = 2;
	public MBIBifurcable(Position origen, Position destino, double velocidad)
	{
		super(origen, destino, velocidad);
		Random random = Game.getInstance().getLevel().getRandom();
		this.distBifurcacion = random.nextDouble() * MAX_BIFURCACION;
		this.cantBifurcacion = random.nextInt(MAX_CANTBIFURCACION - MIN_CANTBIFURCACION + 1) + MIN_CANTBIFURCACION;
	}
	
	protected void desplazamiento()
	{
		super.desplazamiento();
		// Ya llegamos a altura de bifurcacion?
		if(!this.yaBifurco && this.position.getEuclideanDistance(origen) > this.distBifurcacion)
		{
			bifurcar();
		}
	}
	
	private void bifurcar()
	{
		System.out.println("Bifurcando en " + this.cantBifurcacion + " MBIs");
		for(int i = 0; i < this.cantBifurcacion - 1; i++)
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
		
		this.yaBifurco = true;
	}

}
