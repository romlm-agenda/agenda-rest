package org.agenda.data.proxies;
/**
 * @since 24 juil. 2019
 */

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author LE MIERE Romain
 *
 */
@FeignClient(name = "agenda-security")
@RibbonClient(name = "agenda-security")
@RequestMapping("/user/")
public interface SecurityUserIdProxy {

	@GetMapping
	String getInstance(@RequestParam("userId") String userId);

	@DeleteMapping
	void deleteUser(@RequestParam("userId") String userId);

}
