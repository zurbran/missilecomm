package ar.edu.unlp.linti.missilecommand.core;

import java.util.Random;

/**
 * 
 * Factory que se encarga de crear los Krytolianos.
 * 
 */
public class KrytolianFactory
{
	public enum KrytolianType
	{
		MBI, CRUCERO_INTELIGENTE, CRUCERO_TONTO, SATELITE, AVION, MBI_BIFURCABLE

	}

	/**
	 * Constructor que se usa cuando no se especifica una posicion inicial
	 * 
	 * @param krytolian
	 *            el tipo de krytoliano a crear
	 * @return
	 */
	public static GameObject createKrytolian(KrytolianFactory.KrytolianType krytolian) throws CreateFailureException
	{
		return createKrytolian(krytolian, null);
	}

	/**
	 * @param krytolian
	 *            el tipo de krytoliano a crear
	 * @param startPos
	 *            para algunos tipos de krytolianos, se puede especificar una
	 *            posicion inicial. Si esta vale null, se encargara
	 *            KrytolianFactory de elegir una posicion
	 * @return
	 */
	public static GameObject createKrytolian(KrytolianFactory.KrytolianType krytolian,
			Position startPos) throws CreateFailureException
	{
		Random random = Game.getInstance().getLevel().getRandom();
		int level = Game.getInstance().getLevel().getNroNivel();
		if (krytolian == KrytolianFactory.KrytolianType.MBI)
		{
			if (startPos == null)
			{
				// Aparecer desde cualquier parte del cielo
				startPos = new Position(random.nextInt(Game.GAME_WIDTH + 1), 0);
			}

			// Elegir una ciudad o base militar viva aleatoriamente
			Position endPos;
			try
			{
				endPos = new Position(Game.getInstance().zardonianoAleatorio(random)
						.getPosition());
			}
			catch (NoTargetException e)
			{
				throw new CreateFailureException();
			}
			return new MBI(startPos, endPos, Math.min(15.0, 0.5 + 0.2 * level));
		}
		else if (krytolian == KrytolianFactory.KrytolianType.MBI_BIFURCABLE)
		{
			if (startPos == null)
			{
				// Aparecer desde cualquier parte del cielo
				startPos = new Position(random.nextInt(Game.GAME_WIDTH + 1), 0);
			}

			// Elegir una ciudad o base militar viva aleatoriamente
			Position endPos;
			try
			{
				endPos = new Position(Game.getInstance().zardonianoAleatorio(random)
						.getPosition());
			}
			catch (NoTargetException e)
			{
				throw new CreateFailureException();
			}
			return new MBIBifurcable(startPos, endPos, Math.min(15.0, 0.5 + 0.2 * level));
		}
		else if (krytolian == KrytolianFactory.KrytolianType.CRUCERO_INTELIGENTE)
		{
			if (startPos == null)
			{
				// Aparecer desde cualquier parte del cielo
				startPos = new Position(random.nextInt(Game.GAME_WIDTH + 1), 0);
			}

			// Elegir una ciudad o base militar viva aleatoriamente
			Position endPos;
			try
			{
				endPos = new Position(Game.getInstance().zardonianoAleatorio(random)
						.getPosition());
			}
			catch (NoTargetException e)
			{
				throw new CreateFailureException();
			}
			return new CruceroInteligente(startPos, endPos, Math.min(15.0, 0.7 + 0.2 * level));
		}
		else if (krytolian == KrytolianFactory.KrytolianType.CRUCERO_TONTO)
		{
			if (startPos == null)
			{
				// Aparecer desde cualquier parte del cielo
				startPos = new Position(random.nextInt(Game.GAME_WIDTH + 1), 0);
			}

			// Elegir una ciudad o base militar viva aleatoriamente
			Position endPos;
			try
			{
				endPos = new Position(Game.getInstance().zardonianoAleatorio(random)
						.getPosition());
			}
			catch (NoTargetException e)
			{
				throw new CreateFailureException();
			}
			return new CruceroTonto(startPos, endPos, Math.min(15.0, 0.7 + 0.2 * level));
		}
		else if (krytolian == KrytolianFactory.KrytolianType.SATELITE)
		{
			// Limitamos la altura
			double altura = Math.min(Game.GAME_HEIGHT * 0.75, level * 36.0);

			Sentido sentido = random.nextBoolean() ? Sentido.DERECHA : Sentido.IZQUIERDA;

			return new Satelite(altura, sentido);
		}
		else if (krytolian == KrytolianFactory.KrytolianType.AVION)
		{
			// Limitamos la altura
			double altura = Math.min(Game.GAME_HEIGHT * 0.75, level * 36.0);

			Sentido sentido = random.nextBoolean() ? Sentido.DERECHA : Sentido.IZQUIERDA;

			return new Avion(altura, sentido);
		}
		throw new CreateFailureException();
	}
}
