/**
 * @since 29 sept. 2019
 */
package org.agenda.gateway.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author LE MIERE Romain
 *
 */
@Component
@ConfigurationProperties(prefix = "agenda")
public class SecurityProperties {

	private final Security security = new Security();

	public static class Security {
		private Set<String> securedPaths = new HashSet<>();

		/**
		 * @return the securedPaths
		 */
		public final Set<String> getSecuredPaths()
		{
			return securedPaths;
		}

		/**
		 * @param securedPaths the securedPaths to set
		 */
		public final void setSecuredPaths(Set<String> securedPaths)
		{
			this.securedPaths = securedPaths;
		}

	}

	/**
	 * @return the security
	 */
	public final Security getSecurity()
	{
		return security;
	}

}
