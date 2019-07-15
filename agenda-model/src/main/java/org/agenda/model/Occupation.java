/**
 * @since 30 juin 2019
 */
package org.agenda.model;

import java.time.LocalTime;

/**
 * @author LE MIERE Romain
 *
 */
public class Occupation {

	private OccupationType occupationType;
	private LocalTime beginingTime;
	private LocalTime endingTime;
	private DisplayInfos displayInfos;

	public Occupation() {
		super();
	}

	public Occupation(OccupationType occupationType, LocalTime beginingTime, LocalTime endingTime,
			DisplayInfos displayInfos) {
		super();
		this.occupationType = occupationType;
		this.beginingTime = beginingTime;
		this.endingTime = endingTime;
		this.displayInfos = displayInfos;
	}

	/**
	 * @return the occupationType
	 */
	public final OccupationType getOccupationType() {
		return occupationType;
	}

	/**
	 * @param occupationType the occupationType to set
	 */
	public final void setOccupationType(OccupationType occupationType) {
		this.occupationType = occupationType;
	}

	/**
	 * @return the beginingTime
	 */
	public final LocalTime getBeginingTime() {
		return beginingTime;
	}

	/**
	 * @param beginingTime the beginingTime to set
	 */
	public final void setBeginingTime(LocalTime beginingTime) {
		this.beginingTime = beginingTime;
	}

	/**
	 * @return the endingTime
	 */
	public final LocalTime getEndingTime() {
		return endingTime;
	}

	/**
	 * @param endingTime the endingTime to set
	 */
	public final void setEndingTime(LocalTime endingTime) {
		this.endingTime = endingTime;
	}

	/**
	 * @return the displayInfos
	 */
	public final DisplayInfos getDisplayInfos() {
		return displayInfos;
	}

	/**
	 * @param displayInfos the displayInfos to set
	 */
	public final void setDisplayInfos(DisplayInfos displayInfos) {
		this.displayInfos = displayInfos;
	}

}
