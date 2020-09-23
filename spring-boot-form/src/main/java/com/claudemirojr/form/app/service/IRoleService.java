package com.claudemirojr.form.app.service;

import java.util.List;

import com.claudemirojr.form.app.model.entity.Role;

public interface IRoleService {

	public List<Role> listar();

	public Role findBiId(Integer id);

}
