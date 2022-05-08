package com.es.agriculturafamiliar.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.es.agriculturafamiliar.entity.Administrador;
import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.exception.ResourceNotFoundException;
import com.es.agriculturafamiliar.repository.AdministradorRepository;

@ExtendWith(MockitoExtension.class)
public class AdministradorServiceTests {
	@InjectMocks
	private AdministradorService administradorService;
	
	@Mock
	private AdministradorRepository administradorRepository;
	
	@Mock
	private JwtUserDetailsManager jwtUserDetailsManager;
	
	@Test
	public void save_shouldReturnSavedAdministrador_whenSuccessful() {
		User user = User.builder().id(1L).build();
		Administrador administrador = Administrador.builder().user(user).build();
		when(jwtUserDetailsManager.createUser(any(User.class))).thenReturn(user);
		
		administradorService.save(administrador);
		
		verify(administradorRepository, times(1)).save(any(Administrador.class));
		
		
	}
	
	@Test
	public void findByUserId_shouldReturnAdministrador_whenUserIsFound() {
		Administrador expected = Administrador.builder().build();
		when(administradorRepository.findAdministradorByUserId(anyLong())).thenReturn(Optional.of(expected));
		Administrador actual = administradorService.findByUserId(1L);		
		assertNotNull(actual);
	}
	
	@Test
	public void findByUserId_shouldThrowResourceNotFoundException_whenUserIsNotFound() {
		when(administradorRepository.findAdministradorByUserId(anyLong())).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> administradorService.findByUserId(1L));		
	}
	
	
	@Test
	public void findById_shouldReturnAdministrador_whenUserIsFound() {
		Administrador expected = Administrador.builder().build();
		when(administradorRepository.findById(anyLong())).thenReturn(Optional.of(expected));
		Administrador actual = administradorService.findById(1L);		
		assertNotNull(actual);
	}
	
	@Test
	public void findById_shouldThrowResourceNotFoundException_whenUserIsNotFound() {
		when(administradorRepository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> administradorService.findById(1L));		
	}

}
