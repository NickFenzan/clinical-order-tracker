package com.millervein.clinicalordertracker.authorization;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authorization")
public class AuthorizationController {

	@RequestMapping(value = "/member-of", method = RequestMethod.GET)
	public List<String> memberOf() {
		return SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getAuthorities()
				.stream()
				.map(auth -> auth.getAuthority())
				.collect(Collectors.toList());
	}
	
}
