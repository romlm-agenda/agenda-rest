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

}
