package ar.edu.unlp.linti.missilecommand.core;

/**
 * Representa una posicion en 2 dimensiones y permite operaciones matematicas
 * 
 */
public class Position
{

	private double x;
	private double y;

	/**
	 * @param x
	 * @param y
	 */
	public Position(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 * @param pos
	 *            crea un nuevo objeto Position con la misma posicion que pos
	 */
	public Position(Position pos)
	{
		this.x = pos.getX();
		this.y = pos.getY();
	}

	/**
	 * @return the x
	 */
	public Double getX()
	{
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(double x)
	{
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public Double getY()
	{
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(double y)
	{
		this.y = y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "[x=" + x + ", y=" + y + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

	/**
	 * Calcula la distancia euclidiana con la formula pitagorica
	 * 
	 * @param La
	 *            posicion con la que se quiere medir
	 * @return Distancia con precision doble
	 */
	public double getEuclideanDistance(Position other)
	{
		return Math.sqrt(Math.pow(this.x - other.getX(), 2)
				+ Math.pow(this.y - other.getY(), 2));
	}
}
