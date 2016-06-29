package ar.edu.unlp.linti.missilecommand.core;

import java.awt.Color;

import ar.edu.unlp.linti.missilecommand.ui.Drawable;
import ar.edu.unlp.linti.missilecommand.ui.GameObjectRenderer;

public class Estela extends GameObject implements Drawable
{
	private Position startPos;
	private Color color;
	
	public Estela(Position pos, Color color)
	{
		super(pos);
		this.startPos = new Position(pos);
		this.color = color;
	}

	@Override
	void update()
	{

	}

	public void setPosition(Position pos)
	{
		this.position = new Position(pos);
	}
	
	/**
	 * @return the startPos
	 */
	public Position getStartPos()
	{
		return startPos;
	}

	@Override
	public void draw(GameObjectRenderer visitor)
	{
		visitor.draw(this);
	}

	/**
	 * @return the color
	 */
	public Color getColor()
	{
		return color;
	}

}
