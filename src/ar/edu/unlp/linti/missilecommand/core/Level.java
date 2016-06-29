package ar.edu.unlp.linti.missilecommand.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * 
 * Contiene configuracion del nivel y comportamiento relacionado al progreso del
 * nivel, como la invocacion al procesamiento de cada GameObject
 * 
 * Se encarga tambien de limpiar los GabeObjects que no se necesiten mas al
 * terminar el nivel
 */
public class Level
{
	/**
	 * Cantidad minima de MBIs
	 */
	private static final int MIN_MISILES = 10;
	/**
	 * Cantidad maxima de MBIs
	 */
	private static final int MAX_MISILES = 15;

	/**
	 * Cantidad minima de Aviones
	 */
	private static final int MIN_AVIONES = 0;
	/**
	 * Cantidad maxima de Aviones
	 */
	private static final int MAX_AVIONES = 2;

	/**
	 * Cantidad minima de Satelites
	 */
	private static final int MIN_SATELITES = 0;
	/**
	 * Cantidad maxima de Satelites
	 */
	private static final int MAX_SATELITES = 2;

	/**
	 * Cantidad minima de Cruceros
	 */
	private static final int MIN_CRUCEROS = 0;
	/**
	 * Cantidad maxima de Cruceros
	 */
	private static final int MAX_CRUCEROS = 2;

	/**
	 * Cuando hay menos de esta cantidad de enemigos vivos, se procede a generar
	 * nuevos
	 */
	private static final int MIN_RESPAWN = 2;

	/**
	 * No crear mas de esta cantidad de enemigos en simultaneo
	 */
	private static final int MAX_SPAWN = 4;

	/**
	 * Cantidad de bases militares. Cada nivel se renuevan
	 */
	private static final int MIL_BASES = 3;

	private boolean misilCruceroInteligente;
	private boolean hayBombarderos;
	private int nroNivel;
	private Random random;
	private Queue<KrytolianFactory.KrytolianType> enemigosRestantes;
	private Queue<GameObject> objetosACrear;
	private boolean levelTerminado;
	private boolean levelPerdido;

	/**
	 * @param nroNivel
	 *            numero de nivel a iniciar
	 */
	public Level(int nroNivel)
	{
		this(nroNivel, null);
	}

	public Level(int nroNivel, Long seed)
	{
		if (seed == null)
			this.random = new Random();
		else
			this.random = new Random(seed);

		System.out.println("Creando " + MIL_BASES + " bases militares");
		for (int i = 0; i < MIL_BASES; i++)
		{
			Game.getInstance().agregarGameObject(ZardonFactory.createMilitaryBase());
		}

		enemigosRestantes = new LinkedList<KrytolianFactory.KrytolianType>();
		objetosACrear = new LinkedList<GameObject>();

		this.nroNivel = nroNivel;
		if (this.nroNivel % 4 == 1 || this.nroNivel % 4 == 2)
		{
			this.misilCruceroInteligente = false;
		}
		else
		{
			this.misilCruceroInteligente = true;
		}

		this.hayBombarderos = this.nroNivel >= 2;
	}

	/**
	 * Inicia el nivel. Se ejecuta en forma sincronica hasta que el nivel termina
	 */
	public void startLevel()
	{
		this.levelTerminado = false;
		this.levelPerdido = false;

		encolarEnemigos();
	}

	/**
	 * Encolam los enemigos para el nivel actual
	 */
	private void encolarEnemigos()
	{
		List<KrytolianFactory.KrytolianType> enemigos = new ArrayList<KrytolianFactory.KrytolianType>();
		int MBIs = random.nextInt(MAX_MISILES - MIN_MISILES + 1) + MIN_MISILES;
		System.out.println("Encolando " + MBIs + " MBIs");
		for (int i = 0; i < MBIs; i++)
		{
			// Generemos algunos bifurcables
			if(this.nroNivel >= 3 && random.nextBoolean())
			{
				enemigos.add(KrytolianFactory.KrytolianType.MBI_BIFURCABLE);
			}
			else
			{
				enemigos.add(KrytolianFactory.KrytolianType.MBI);
			}
		}

		if (this.hayBombarderos)
		{
			int aviones = random.nextInt(MAX_AVIONES - MIN_AVIONES + 1) + MIN_AVIONES;
			System.out.println("Encolando " + aviones + " Aviones");
			for (int i = 0; i < aviones; i++)
			{
				enemigos.add(KrytolianFactory.KrytolianType.AVION);
			}

			int satelites = random.nextInt(MAX_SATELITES - MIN_SATELITES + 1) + MIN_SATELITES;
			System.out.println("Encolando " + satelites + " Satelites");
			for (int i = 0; i < satelites; i++)
			{
				enemigos.add(KrytolianFactory.KrytolianType.SATELITE);
			}
		}

		int cruceros = random.nextInt(MAX_CRUCEROS - MIN_CRUCEROS + 1) + MIN_CRUCEROS;
		System.out.println("Encolando " + cruceros + " Cruceros");
		for (int i = 0; i < cruceros; i++)
		{
			if (this.misilCruceroInteligente)
			{
				enemigos.add(KrytolianFactory.KrytolianType.CRUCERO_INTELIGENTE);
			}
			else
			{
				enemigos.add(KrytolianFactory.KrytolianType.CRUCERO_TONTO);
			}
		}

		// Insertamos en forma aleatoria en una cola
		int i;
		while (!enemigos.isEmpty())
		{
			i = random.nextInt(enemigos.size());
			enemigosRestantes.add(enemigos.get(i));
			enemigos.remove(i);
		}
	}

	/**
	 * Procesa todas las entidades y elementos del juego. Se debe llamar
	 * periodicamente para que el juego avance. Encuentra y pone en fin el nivel
	 * cuando se destruyen todas las ciudades, o todos los krytolianos
	 */
	public void tick()
	{
		List<GameObject> entidades = Game.getInstance().getGameObjects();

		// Crear las entidades encoladas
		while (!objetosACrear.isEmpty())
		{
			Game.getInstance().agregarGameObject(objetosACrear.remove());
		}

		// Remover las entidades que haya que remover en forma segura
		Iterator<GameObject> i = entidades.iterator();
		while (i.hasNext())
		{
			GameObject ent = i.next();
			if (ent.toRemove)
			{
				ent.dispose();
				i.remove();
			}
		}
		
		// Darles tiempo para "pensar" a cada entidad
		for (GameObject ent : entidades)
		{
			ent.update();
		}

		if (Game.getInstance().contarCiudades() == 0)
		{
			this.levelPerdido = true;
		}
		
		if (Game.getInstance().contarKrytolianos() == 0 && objetosACrear.isEmpty() && Game.getInstance().contarExplosiones() == 0)
		{
			if(this.levelPerdido || enemigosRestantes.isEmpty())
			{
				this.levelTerminado = true;
			}
		}
		
		if (!this.levelPerdido && Game.getInstance().contarKrytolianos() < MIN_RESPAWN && !enemigosRestantes.isEmpty())
		{
				generarEnemigos();
		}

	}

	/**
	 * Se encarga de generar un grupo de enemigos, de los que se encuentran en
	 * la cola
	 */
	private void generarEnemigos()
	{
		int aGenerar = Math.min(enemigosRestantes.size(), random.nextInt(MAX_SPAWN - MIN_RESPAWN)
				+ MIN_RESPAWN);
		for (int i = 0; i < aGenerar; i++)
		{
			try
			{
				Game.getInstance().agregarGameObject(
						KrytolianFactory.createKrytolian(enemigosRestantes.remove()));
			}
			catch (CreateFailureException e)
			{

			}
		}
	}

	/**
	 * Se deberia llamar cuando el jugador hace click en una parte de la
	 * pantalla. Elige automaticamente la base militar con misiles, y dispara
	 * desde ella un MAB
	 * 
	 * @param dest
	 *            Posicion a la que se quiere disparar
	 */
	public void MABautomatico(Position dest)
	{
		MilitaryBase mejor = null;
		double mejorDist = Double.MAX_VALUE;
		for (GameObject ent : Game.getInstance().getGameObjects())
		{
			if (ent instanceof MilitaryBase)
			{
				MilitaryBase base = (MilitaryBase) ent;
				if (base.getMissileCount() > 0)
				{
					double dist = dest.getEuclideanDistance(base.getPosition());
					if (dist < mejorDist)
					{
						mejorDist = dist;
						mejor = base;
					}
				}
			}
		}

		if (mejor != null)
		{
			mejor.fireMissile(dest);
		}
	}

	/**
	 * Agrega un GameObject a una cola para que sea agregado en forma segura
	 * durante el proximo tick. Este metodo se debe usar principalmente para
	 * agregar GameObjects desde un GameObject que esta siendo procesado
	 * 
	 * @param ent
	 *            Objeto a encolar
	 */
	public void encolarGameObject(GameObject ent)
	{
		objetosACrear.add(ent);
	}

	/**
	 * @return the random
	 */
	public Random getRandom()
	{
		return random;
	}

	/**
	 * @return the nroNivel
	 */
	public int getNroNivel()
	{
		return nroNivel;
	}

	/**
	 * @return the levelTerminado
	 */
	public boolean isLevelTerminado()
	{
		return levelTerminado;
	}

	/**
	 * @return the levelPerdido
	 */
	public boolean isLevelPerdido()
	{
		return levelPerdido;
	}

}
