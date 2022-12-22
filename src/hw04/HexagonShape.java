package hw04;
/** 
 * This class will represent a single tile which including String array to store 
 * its color
 * @author dtnng, 402005276, 2013-07
 *
 */

public class HexagonShape {
	/**this {@code String} array to store its color*/
	public String[] base;
	/** Initializes the HexagonShape Object using the given parameter color 
	 * values*/
	public HexagonShape(String[] colors)
	{
		base = colors;
	}
	
	/**
	 * Set the color at specific index
	 * @param n				the index
	 * @param color			the Color
	 */
	public void setColor(int n, String color)
	{
		base[n] = color;
	}
	/**
	 * Get the color at specific index
	 * @param n			the index
	 * @return			return the color at specific index
	 */
	public String getColor(int n)
	{
		return base[n];
	}
	/** Get the size of object array*/
	public int getSize()
	{
		return base.length;
	}
	/** Perform a rotate color within the array by shifting everything to the 
	 * right*/
	public void rotateColor()
	{
		int i;
		/** Store the first data value*/
		String temp = this.getColor(this.getSize() - 1);
		/** Start shifting data except the last data, in asc order*/
		for(i = this.getSize() - 1; i > 0; i--)
		{
			this.setColor(i, this.getColor(i-1));
		}
		/** The last data will equal the first data that stored earlier*/
		this.setColor(i, temp);
	}
}
