package com.claudemirojr.form.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.claudemirojr.form.app.model.entity.Role;

@Service
public class RoleService implements IRoleService {

	private List<Role> roles = new ArrayList<>();

	public RoleService() {
		roles.add(new Role(1, "Administrador", "ROLE_ADM"));
		roles.add(new Role(1, "Usuário", "ROLE_USUARIO"));
		roles.add(new Role(1, "Leitor", "ROLE_LEITOR"));

	}

	@Override
	public List<Role> listar() {
		return roles;
	}

	@Override
	public Role findBiId(Integer id) {
		Role result = null;

		for (Role role : roles) {
			if (role.getId() == id) {
				result = role;
				break;
			}
		}

		return result;
	}

}
