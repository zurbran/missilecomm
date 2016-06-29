package ar.edu.unlp.linti.missilecommand.core;

/**
 * 
 * Interface marker que determina si un objeto es destruible (por ejemplo por
 * una explosion)
 * 
 */
public interface Destroyable
{
	/**
	 * Este metodo sera llamado cuando se requiera que un objeto sea destruido.
	 * Debe encargarse el objeto de removerse a si mismo
	 */
	abstract void destroyMe();
}
