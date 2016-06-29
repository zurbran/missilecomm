package ar.edu.unlp.linti.missilecommand.ui;

import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Resources
{
	private static Resources instance = null;

	private static final String SATELITE_SPRITE = "Sprites/satellite.png";
	private BufferedImage sateliteSprite;

	private static final String AVION_SPRITE = "Sprites/avion.png";
	private BufferedImage avionSprite;
	
	private static final String AVIONF_SPRITE = "Sprites/avionflipped.png";
	private BufferedImage avionFSprite;

	private static final String BACKGROUND_SPRITE = "Sprites/terreno.png";
	private BufferedImage backgroundSprite;

	private static final String CRUCERO_SPRITE = "Sprites/crucero.png";
	private BufferedImage cruceroSprite;

	private static final String LEVEL_SPRITE = "Sprites/leveloverlay.png";
	private BufferedImage levelSprite;

	private static final String MILBASE_SPRITE = "Sprites/missilesprites.png";
	private BufferedImage milbaseSprite;

	private static final String CITY_SPRITE = "Sprites/city.png";
	private BufferedImage citySprite;

	private static final String LOTL_FONT = "Fonts/layoftheland.ttf";
	private Font lotlFont;
	
	private static final String IMAGINE_FONT = "Fonts/imagine.ttf";
	private Font imagineFont;

	public BufferedImage loadImage(String resource, int transparencyType)
	{
		try
		{
			BufferedImage src = javax.imageio.ImageIO.read(getClass().getResourceAsStream(resource));
			// Images returned from ImageIO are NOT managedImages
			// Therefor, we copy it into a ManagedImage
			BufferedImage dst = GraphicsEnvironment.getLocalGraphicsEnvironment()
					.getDefaultScreenDevice().getDefaultConfiguration()
					.createCompatibleImage(src.getWidth(), src.getHeight(), transparencyType);
			Graphics2D g2d = dst.createGraphics();
			g2d.setComposite(AlphaComposite.Src);
			g2d.drawImage(src, 0, 0, null);
			g2d.dispose();
			return dst;
		}
		catch (java.io.IOException e)
		{
			return null;
		}
	}

	private Resources()
	{
		try
		{
			backgroundSprite = loadImage(BACKGROUND_SPRITE, Transparency.OPAQUE);			
			citySprite = loadImage(CITY_SPRITE, Transparency.TRANSLUCENT);
			sateliteSprite = loadImage(SATELITE_SPRITE, Transparency.TRANSLUCENT);
			avionSprite = loadImage(AVION_SPRITE, Transparency.TRANSLUCENT);
			avionFSprite = loadImage(AVIONF_SPRITE, Transparency.TRANSLUCENT);
			cruceroSprite = loadImage(CRUCERO_SPRITE, Transparency.TRANSLUCENT);
			levelSprite = loadImage(LEVEL_SPRITE, Transparency.TRANSLUCENT);
			milbaseSprite = loadImage(MILBASE_SPRITE, Transparency.TRANSLUCENT);

			lotlFont = Font.createFont(Font.TRUETYPE_FONT,
					getClass().getResourceAsStream(LOTL_FONT)).deriveFont(24f);
			
			imagineFont = Font.createFont(Font.TRUETYPE_FONT,
					getClass().getResourceAsStream(IMAGINE_FONT)).deriveFont(24f);
		}
		catch (IOException | FontFormatException e)
		{
			e.printStackTrace();
		}
	}

	public static Resources getInstance()
	{
		if (instance == null)
		{
			instance = new Resources();
		}
		return instance;
	}

	/**
	 * @return the sateliteSprite
	 */
	public BufferedImage getSateliteSprite()
	{
		return sateliteSprite;
	}

	/**
	 * @return the avionSprite
	 */
	public BufferedImage getAvionSprite()
	{
		return avionSprite;
	}
	
	/**
	 * @return the avionSprite
	 */
	public BufferedImage getAvionFSprite()
	{
		return avionFSprite;
	}

	/**
	 * @return the backgroundSprite
	 */
	public BufferedImage getBackgroundSprite()
	{
		return backgroundSprite;
	}

	/**
	 * @return the crucceroSprite
	 */
	public BufferedImage getCruceroSprite()
	{
		return cruceroSprite;
	}

	/**
	 * @return the levelSprite
	 */
	public BufferedImage getLevelSprite()
	{
		return levelSprite;
	}

	/**
	 * @return the levelSprite
	 */
	public BufferedImage getMilbaseSprite()
	{
		return milbaseSprite;
	}

	/**
	 * @return the citySprite
	 */
	public BufferedImage getCitySprite()
	{
		return citySprite;
	}

	/**
	 * @return the lotlFont
	 */
	public Font getLotlFont()
	{
		return lotlFont;
	}
	
	/**
	 * @return the imagineFont
	 */
	public Font getImagineFont()
	{
		return imagineFont;
	}

}
