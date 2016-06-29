package ar.edu.unlp.linti.missilecommand.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * Clase principal tipo singleton que contiene el nivel actual, puntuaciones, y
 * comportamiento relacionado al inicio/fin y cambio de nivel
 * 
 * Contiene la coleccion de gameObjects, ya que algunas (como las ciudades)
 * deben permanecer a traves de los niveles
 * 
 * Esta clase se utiliza para iniciar el juego
 * 
 */
public class Game
{
	/**
	 * Ancho del juego
	 */
	public final static int GAME_WIDTH = 525;
	/**
	 * Alto del juego
	 */
	public final static int GAME_HEIGHT = 480;

	/**
	 * Cantidad de ciudades con las que se inicia
	 */
	private final static int STARTING_CITIES = 6;

	private static Game game = null;
	private Level level;
	private Score score;
	private List<GameObject> gameObjects = null;
	private Thread thread;
	private DrawListener drawListener = null;
	private BlockingQueue<Runnable> runQueue = null;

	private Game()
	{
		runQueue = new ArrayBlockingQueue<Runnable>(100);
	}

	public void setDrawListener(DrawListener drawListener)
	{
		this.drawListener = drawListener;
	}

	class GameTiming extends TimerTask
	{

		long lastFrame;
		public void sync(int sync) {
	         if (sync!=-1) {
	            long diff = 1000000000L / sync + lastFrame;
	            long now = System.nanoTime();
	            
	            try {
	               while (diff > now) {
	                  Thread.sleep((diff-now) / 2000000L);
	                  now = System.nanoTime();
	               }
	            } catch (Exception e) {}

	            lastFrame = now;
	         } 
	    }
		
		@Override
		public void run()
		{
			showLevelBlocking(level.getNroNivel(), 1600);
			runQueue.clear();
			while (true)
			{
				if (!level.isLevelTerminado())
				{
					level.tick();
					drawListener.drawEvent(gameObjects, level.getNroNivel(), score);
				}
				else
				{
					score.procesarFinalNivel(level.getNroNivel(), gameObjects);

					if (level.isLevelPerdido())
					{
						showGameOverBlocking(2000);
						drawListener.showMainScreen(gameObjects, level.getNroNivel(), score);
						return;
					}
					else
					{
						showLevelBlocking(level.getNroNivel() + 1, 2200);
						changeLevel(level.getNroNivel() + 1);
					}

					// Limpiamos la cola de eventos (como por ejemplo clicks)
					// que se haya producido mientras se cambiaba de nivel
					runQueue.clear();
				}

				if (!runQueue.isEmpty())
				{
					runQueue.remove().run();
				}
				
				sync(60);

			}
		}

		private void showLevelBlocking(int level, int milliseconds)
		{
			long startTime = System.nanoTime() / 1000000;
			while (System.nanoTime() / 1000000 - startTime < milliseconds)
			{
				drawListener.drawNextLevelEvent(gameObjects, level, score);
				sync(60);
			}
		}

		private void showGameOverBlocking(int milliseconds)
		{
			long startTime = System.nanoTime() / 1000000;
			while (System.nanoTime() / 1000000 - startTime < milliseconds)
			{
				drawListener.drawGameOverEvent(gameObjects, level.getNroNivel(), score);
				sync(60);
			}
		}

	}

	private void changeLevel(int level)
	{
		for (GameObject ent : gameObjects)
		{
			if (!(ent instanceof City))
			{
				ent.toRemove = true;
			}
		}

		// Remover las entidades que haya que remover en forma segura
		Iterator<GameObject> i = gameObjects.iterator();
		while (i.hasNext())
		{
			GameObject ent = i.next();
			if (ent.toRemove)
			{
				ent.dispose();
				i.remove();
			}
		}

		this.level = new Level(level);

		this.level.startLevel();
	}

	/**
	 * Inicia el juego a partir de cierto nivel
	 * 
	 * @param level
	 *            el nivel del que se parte
	 */
	public void start(int level)
	{
		System.out.println("Iniciando juego en nivel " + level);
		this.gameObjects = new ArrayList<GameObject>();
		this.score = new Score();

		System.out.println("Creando " + STARTING_CITIES + " ciudades");
		for (int i = 0; i < STARTING_CITIES; i++)
		{
			agregarGameObject(ZardonFactory.createCity());
		}

		// this.level = new Level(level);
		// Utilizo una semilla especifica para poder repetir en forma exacta el
		// nivel al realizar debugging
		this.level = new Level(level);

		this.level.startLevel();

		thread = new Thread(new GameTiming(), "MainGameThread");
		thread.start();
	}

	public static Game getInstance()
	{
		if (game == null)
		{
			game = new Game();
		}
		return game;
	}

	/**
	 * @return the level
	 */
	public Level getLevel()
	{
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(Level level)
	{
		this.level = level;
	}

	/**
	 * Permite crear en forma inmediata un GameObject al juego
	 * 
	 * @param entidad
	 *            GameObject a agregar
	 */
	public void agregarGameObject(GameObject entidad)
	{
		if (entidad != null)
		{
			this.gameObjects.add(entidad);
		}
	}

	/**
	 * Permite borrar en forma inmediata un GameObject al juego
	 * 
	 * @param entidad
	 *            GameObject a borrar
	 */
	public void removerGameObject(GameObject entidad)
	{
		this.gameObjects.remove(entidad);
	}

	/**
	 * @return the gameObjects
	 */
	public List<GameObject> getGameObjects()
	{
		return gameObjects;
	}

	/**
	 * @return Devuelve la cantidad de krytolianos vivos
	 */
	public int contarKrytolianos()
	{
		int c = 0;
		for (GameObject ent : gameObjects)
		{
			if (ent instanceof Krytolian)
			{
				c++;
			}
		}

		return c;
	}

	/**
	 * @return Devuelve la cantidad de Ciudades vivos
	 */
	public int contarCiudades()
	{
		int c = 0;
		for (GameObject ent : gameObjects)
		{
			if (ent instanceof City)
			{
				c++;
			}
		}

		return c;
	}

	/**
	 * @return Devuelve la cantidad de Bases militares vivas
	 */
	public int contarBasesMilitares()
	{
		int c = 0;
		for (GameObject ent : gameObjects)
		{
			if (ent instanceof MilitaryBase)
			{
				c++;
			}
		}

		return c;
	}

	/**
	 * Destruye un GameObject, y llama al metodo encargado de actualizar la
	 * puntuacion
	 * 
	 * @param obj
	 *            GameObject a destruir
	 * @param ignoreSccore
	 *            No procesa score
	 * 
	 */
	public void callDestroy(GameObject obj, boolean ignoreSccore)
	{
		// Actualizar puntaje

		// Llamar al metodo del GameObject
		if (!ignoreSccore)
		{
			this.score.procesarDestruccion(level.getNroNivel(), obj);
		}
		((Destroyable) obj).destroyMe();

	}

	/**
	 * Elige en forma aleatoria un zardoniano que se encuentre vivo (base
	 * militar o ciudad)
	 * 
	 * @return GameObject del zardoniano
	 */
	public GameObject zardonianoAleatorio(Random random) throws NoTargetException
	{
		List<GameObject> potenciales = new ArrayList<GameObject>();

		for (GameObject obj : gameObjects)
		{
			if (obj instanceof Zardonian && !obj.toRemove)
			{
				potenciales.add(obj);
			}
		}

		if (potenciales.size() == 0)
		{
			throw new NoTargetException();
		}

		return potenciales.get(random.nextInt(potenciales.size()));
	}

	public GameObject zardonianoAleatorio() throws NoTargetException
	{
		Random random = new Random();
		return zardonianoAleatorio(random);
	}

	public int contarExplosiones()
	{
		int c = 0;
		for (GameObject ent : gameObjects)
		{
			if (ent instanceof Explosion)
			{
				c++;
			}
		}

		return c;
	}

	public BlockingQueue<Runnable> getRunQueue()
	{
		return runQueue;
	}
}
