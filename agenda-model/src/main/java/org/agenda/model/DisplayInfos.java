/**
 * @since 30 juin 2019
 */
package org.agenda.model;

/**
 * @author LE MIERE Romain
 *
 */
public class DisplayInfos {

	private String color;
	private String backgroundColor;

	public DisplayInfos() {
		super();
	}

	public DisplayInfos(String color, String backgroundColor) {
		super();
		this.color = color;
		this.backgroundColor = backgroundColor;
	}

	/**
	 * @return the color
	 */
	public final String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public final void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the backgroundColor
	 */
	public final String getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * @param backgroundColor the backgroundColor to set
	 */
	public final void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

}
