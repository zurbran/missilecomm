package ar.edu.unlp.linti.missilecommand.core;

/**
 * Se encarga de la creacion de los Zardones. Para esto, se divide la pantalla
 * verticalmente en 11 espacios, siendo cada uno usado de la siguiente forma 1er
 * espacio: vacio 2do: Base militar 3er, 4to, 5to: Ciudad 6to: Base militar 7mo,
 * 8vo, 9no: Ciudad 10mo: Base militar 11vo: vacio
 */
public class ZardonFactory
{
	private static final int[][] CITY_POS = { {102, 455}, {152, 455}, {202, 455}, {328, 455}, {378, 455}, {428, 455} };
	private static final int[][] MILBASE_POS = { {54, 440}, {270, 445}, {488, 440}};
	

	public static GameObject createCity()
	{
		// Buscamos un lugar libre para ubicar nuestra ciudad
		if (Game.getInstance().contarCiudades() < CITY_POS.length)
		{
			Position pos = null;
			for (int[] slot : CITY_POS)
			{
				boolean used = false;
				for (GameObject ent : Game.getInstance().getGameObjects())
				{
					if (ent instanceof City)
					{
						if (ent.getPosition().getX().intValue() == slot[0])
						{
							used = true;
						}
					}
				}

				if (!used)
				{
					pos = new Position(slot[0], slot[1]);
					break;
				}
			}
			
			return new City(pos);
		}
		else
		{
			// Error, no hay mas lugar para ciudades
			return null;
		}
	}
	
	public static GameObject createMilitaryBase()
	{
		// Buscamos un lugar libre para ubicar nuestra ciudad
		if (Game.getInstance().contarBasesMilitares() < MILBASE_POS.length)
		{
			Position pos = null;
			for (int[] slot : MILBASE_POS)
			{
				boolean used = false;
				for (GameObject ent : Game.getInstance().getGameObjects())
				{
					if (ent instanceof MilitaryBase)
					{
						if (ent.getPosition().getX().intValue() == slot[0])
						{
							used = true;
						}
					}
				}
				
				if (!used)
				{
					pos = new Position(slot[0], slot[1]);
					break;
				}
			}
			
			return new MilitaryBase(pos);
		}
		else
		{
			// Error, no hay mas lugar para ciudades
			return null;
		}
	}
}
