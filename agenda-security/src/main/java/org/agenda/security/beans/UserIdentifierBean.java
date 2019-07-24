/**
 * @since 24 juil. 2019
 */
package org.agenda.security.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author LE MIERE Romain
 *
 */
@Entity(name = "userIds")
public class UserIdentifierBean {

	@Id
	@Column(unique = true, nullable = false, updatable = false)
	private String userId;
	@Column(unique = true, nullable = false)
	private String authKey;

	/**
	 * 
	 */
	public UserIdentifierBean() {
		super();
	}

	/**
	 * @param userId
	 * @param authKey
	 */
	public UserIdentifierBean(String userId, String authKey) {
		super();
		this.userId = userId;
		this.authKey = authKey;
	}

	/**
	 * @return the userId
	 */
	public final String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public final void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the authKey
	 */
	public final String getAuthKey() {
		return authKey;
	}

	/**
	 * @param authKey the authKey to set
	 */
	public final void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

}
