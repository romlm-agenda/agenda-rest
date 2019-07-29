/**
 * @since 25 juil. 2019
 */
package org.agenda.gateway.config;

import org.springframework.context.annotation.Configuration;

import brave.sampler.Sampler;

/**
 * @author LE MIERE Romain
 *
 */
@Configuration
public class SleuthConfig {

	public Sampler sleuthPolicy()
	{
		return Sampler.ALWAYS_SAMPLE;
	}

}
