package com.es.agriculturafamiliar.service;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.es.agriculturafamiliar.constants.RoleType;
import com.es.agriculturafamiliar.entity.Administrador;
import com.es.agriculturafamiliar.entity.Role;
import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.exception.ResourceNotFoundException;
import com.es.agriculturafamiliar.repository.AdministradorRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdministradorService {
	
	private final AdministradorRepository administradorRepository;
	private final ICustomUserDetailsService<User> userDetailsService;
	private static final Set<Role> ADMIN_ROLES =  Set.of(Role.builder().role(RoleType.ADMIN).build());
	
	@Transactional
	public void save(Administrador administrador) {
		administrador.getUser().setRoles(ADMIN_ROLES);
		User createdUser = userDetailsService.createUser(administrador.getUser());
		administrador.setUser(createdUser);
		administradorRepository.save(administrador);		
	}

	public Administrador findByUserId(Long id) throws ResourceNotFoundException {
        return this.administradorRepository.findAdministradorByUserId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador não econtrado"));		
	}
	
	public boolean existsById(Long id) {
		return administradorRepository.existsById(id);
	}
	
	public Administrador findById(Long id) throws ResourceNotFoundException {
        return this.administradorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador não econtrado"));		
	}

}
