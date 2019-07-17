/**
 * @since 16 juil. 2019
 */
package org.agenda.gateway.proxies;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author LE MIERE Romain
 *
 */
@FeignClient(name = "agenda-security")
@RibbonClient(name = "agenda-security")
@RequestMapping("/token/")
public interface SecurityProxy {

	@GetMapping
	String getToken();

	@PostMapping
	boolean isTokenValid(@RequestBody String token);

	@DeleteMapping
	void deleteToken(@RequestBody String token);

}
