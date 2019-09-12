/**
 * @since 12 sept. 2019
 */
package org.agenda.security.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author LE MIERE Romain
 *
 */
@Entity(name = "access_tokens_beans")
public class AccessTokenBean {

	@Id
	private Long id;
	@Column(updatable = false, unique = true, nullable = false)
	private String accessToken;

	/**
	 * 
	 */
	public AccessTokenBean() {
	}

	/**
	 * @param accessToken
	 */
	public AccessTokenBean(String accessToken) {
		super();
		this.accessToken = accessToken;
	}

	/**
	 * @param id
	 * @param accessToken
	 */
	public AccessTokenBean(Long id, String accessToken) {
		super();
		this.id = id;
		this.accessToken = accessToken;
	}

	/**
	 * @return the id
	 */
	public final Long getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(Long id)
	{
		this.id = id;
	}

	/**
	 * @return the accessToken
	 */
	public final String getAccessToken()
	{
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public final void setAccessToken(String accessToken)
	{
		this.accessToken = accessToken;
	}

}
