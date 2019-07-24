/**
 * @since 24 juil. 2019
 */
package org.agenda.security.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @author LE MIERE Romain
 *
 */
@Entity(name = "userIds")
@Table(indexes = { @Index(unique = true, columnList = "userId") })
public class UserIdentifierBean {

	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true, nullable = false, updatable = false, columnDefinition = "user_id")
	private String userId;
	@Column(unique = true, nullable = false, updatable = false)
	private String token;

	/**
	 * 
	 */
	public UserIdentifierBean() {
		super();
	}

	/**
	 * @param id
	 * @param userId
	 * @param token
	 */
	public UserIdentifierBean(Long id, String userId, String token) {
		super();
		this.id = id;
		this.userId = userId;
		this.token = token;
	}

	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(Long id) {
		this.id = id;
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
	 * @return the token
	 */
	public final String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public final void setToken(String token) {
		this.token = token;
	}

}
