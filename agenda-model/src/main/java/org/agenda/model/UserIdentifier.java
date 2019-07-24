/**
 * @since 24 juil. 2019
 */
package org.agenda.model;

/**
 * @author LE MIERE Romain
 *
 */
public class UserIdentifier<TUserID, TToken> {

	private TUserID userId;
	private TToken token;

	/**
	 * 
	 */
	public UserIdentifier() {
		super();
	}

	/**
	 * 
	 * @param userId
	 * @param token
	 */
	public UserIdentifier(TUserID userId, TToken token) {
		super();
		this.userId = userId;
		this.token = token;
	}

	/**
	 * @return the userId
	 */
	public final TUserID getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public final void setUserId(TUserID userId) {
		this.userId = userId;
	}

	/**
	 * @return the token
	 */
	public final TToken getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public final void setToken(TToken token) {
		this.token = token;
	}

}
