package ar.edu.unlp.linti.missilecommand.core;

import java.util.List;

/**
 * 
 * Almacena y procesa la puntuacion del juego.
 *
 */
public class Score
{
	private static final int SCORE_MBI = 25;
	private static final int SCORE_CRUCERO = 125;
	private static final int SCORE_MBA = 5;
	private static final int SCORE_CITY = 100;
	
	private int scoreMBI;
	private int scoreCrucero;
	private int scoreMBA;
	private int scoreCity;
	
	private int lastScoreMBA;
	private int lastScoreCity;
	
	/**
	 * Inicia en cero la puntuacion
	 */
	public Score()
	{
		this.scoreMBI = 0;
		this.scoreCrucero = 0;
		this.scoreMBA = 0;
		this.scoreCity = 0;
	}
	
	/**
	 * Se encarga de sumar la puntuacion al final del nivel de las ciudades y MABs restantes
	 * @param level nro de Nivel
	 * @param objects Lista de GameObjects para revisar la cantidad de ciudades y MABs restantes
	 */
	public void procesarFinalNivel(int level, List<GameObject> objects)
	{
		this.lastScoreCity = 0;
		this.lastScoreMBA = 0;
		for(GameObject obj : objects)
		{
			if(obj instanceof City)
			{
				this.lastScoreCity += SCORE_CITY * getMultiplicador(level);
			}
			else if(obj instanceof MilitaryBase)
			{
				this.lastScoreMBA += SCORE_MBA * ((MilitaryBase)obj).getMissileCount() * getMultiplicador(level);
			}
		}
		this.scoreCity += this.lastScoreCity;
		this.scoreMBA += this.lastScoreMBA;		
	}
	
	/**
	 * Cuando obj es destruido, se actualiza la puntuancion en funcion del nivel y tipo de objeto
	 * @param level nro de nivel
	 * @param obj objeto que fue destruido
	 */
	public void procesarDestruccion(int level, GameObject obj)
	{
		if(obj instanceof MBI)
		{
			this.scoreMBI += SCORE_MBI * getMultiplicador(level);
		}
		else if(obj instanceof Crucero)
		{
			this.scoreCrucero += SCORE_CRUCERO * getMultiplicador(level);
		}
	}
	
	/**
	 * Segun el nivel, hay un multiplicador de puntuacion
	 * @param level nro de nivel
	 * @return multiplicador de puntuacion
	 */
	private int getMultiplicador(int level)
	{
		return Math.min(6, (level + 1) / 2);
	}

	/**
	 * @return the scoreMBI
	 */
	public int getScoreMBI()
	{
		return scoreMBI;
	}

	/**
	 * @return the scoreCrucero
	 */
	public int getScoreCrucero()
	{
		return scoreCrucero;
	}

	/**
	 * @return the scoreMBA
	 */
	public int getScoreMBA()
	{
		return scoreMBA;
	}

	/**
	 * @return the scoreCity
	 */
	public int getScoreCity()
	{
		return scoreCity;
	}
	
	public int getTotal()
	{
		return scoreCity + scoreCrucero + scoreMBA + scoreMBI;
	}

	/**
	 * @return the lastScoreMBA
	 */
	public int getLastScoreMBA()
	{
		return lastScoreMBA;
	}

	/**
	 * @return the lastScoreCity
	 */
	public int getLastScoreCity()
	{
		return lastScoreCity;
	}

}
