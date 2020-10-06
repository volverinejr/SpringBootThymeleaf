package com.claudemirojr.app.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.claudemirojr.app.model.entity.Cliente;
import com.claudemirojr.app.model.service.ClienteService;
import com.claudemirojr.app.util.paginator.PageRender;

@Controller
@RequestMapping("/clientes")
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	
	private final static String UPLOADS_FOLDER = "uploads";

	@GetMapping(value = { "/listar", "/", "" })
	public String listar(
			@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "5") int size,
			Model model,
			Authentication authentication,
			HttpServletRequest request) {
		

		if ( authentication != null ) {
			System.out.println( ">>>>>>>>>>> CLIENTE: " + authentication.getName() + " <<<<<<<<<<<<<<<<");
		}
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ( auth != null ) {
			System.out.println( ">>>>>>>>>>> CLIENTE: " + auth.getName() + " <<<<<<<<<<<<<<<<");
		}
		
		
		SecurityContextHolderAwareRequestWrapper securityContex = new SecurityContextHolderAwareRequestWrapper(request, "ROLE_");
		if ( securityContex.isUserInRole("ADMIN") ) {
			System.out.println( ">>>>>>>>>>> CLIENTE: " + auth.getName() + " <<<<<<<<<<<<<<<<");
		}
		
		if ( request.isUserInRole("ROLE_ADMIN") ) {
			System.out.println( ">>>>>>>>>>> CLIENTE: " + auth.getName() + " <<<<<<<<<<<<<<<<");
		}

		
		
		Pageable pageable = PageRequest.of(page, size);

		Page<Cliente> clientes = clienteService.findAll(pageable);

		PageRender<Cliente> pageRender = new PageRender<>("/clientes/listar", clientes);

		model.addAttribute("titulo", "Lista de Clientes");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);

		return "listar";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/form")
	public String criar(Model model) {
		Cliente cliente = new Cliente();

		model.addAttribute("titulo", "Formulário de Cliente");
		model.addAttribute("cliente", cliente);

		return "form";
	}

	@PostMapping("/form")
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulário de Cliente");
			return "form";
		}

		if (!foto.isEmpty()) {
			
			if (  cliente.getId() != null && cliente.getFoto() != null && cliente.getFoto().length() > 0 ) {
				Path rootPath = Paths.get(UPLOADS_FOLDER).resolve(cliente.getFoto()).toAbsolutePath();
				File arquivo = rootPath.toFile();
				
				if ( arquivo.exists() && arquivo.canRead() ) {
					arquivo.delete();
				}
			}
			
			
			
			String uniqueFilename = UUID.randomUUID().toString() + "_" + foto.getOriginalFilename();

			Path rootPath = Paths.get(UPLOADS_FOLDER).resolve(uniqueFilename);
			Path rootAbsolutPath = rootPath.toAbsolutePath();

			try {
				Files.copy(foto.getInputStream(), rootAbsolutPath);

				flash.addFlashAttribute("info", "Upload realizado com sucesso " + uniqueFilename + " !");

				cliente.setFoto(uniqueFilename);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		clienteService.save(cliente);

		status.isComplete();

		flash.addFlashAttribute("success", "Cliente salvo com sucesso!");

		return "redirect:listar";
	}

	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/form/{id}")
	public String editar(@PathVariable Long id, Model model) {
		Cliente cliente = clienteService.findById(id);

		model.addAttribute("titulo", "Editando o Cliente");
		model.addAttribute("cliente", cliente);

		return "form";
	}

	@GetMapping("/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes flash) {
		Cliente cliente = clienteService.findById(id);
		
		Path rootPath = Paths.get(UPLOADS_FOLDER).resolve(cliente.getFoto()).toAbsolutePath();
		File arquivo = rootPath.toFile();
		
		clienteService.deleteById(id);
		
		if ( arquivo.exists() && arquivo.canRead() ) {
			arquivo.delete();
		}
		

		flash.addFlashAttribute("success", "Cliente eliminado com sucesso!");

		return "redirect:listar";
	}

	@GetMapping("/ver/{id}")
	public String ver(@PathVariable Long id, Model model, RedirectAttributes flash) {
		//Cliente cliente = clienteService.findById(id);
		Cliente cliente = clienteService.fetchClienteByIdWithFatura(id);

		model.addAttribute("titulo", "Detalhe do Cliente: " + cliente.getNome());
		model.addAttribute("cliente", cliente);

		return "ver";
	}
	
	
	
	private boolean hasRole(String role) {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null) {
			return false;
		}
		
		Authentication auth = context.getAuthentication();
		if (auth == null) {
			return false;
		}
		
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		
		return authorities.contains(new SimpleGrantedAuthority(role));
		
		/*
		for (GrantedAuthority authority: authorities) {
			if (role.equals(authority.getAuthority())) {
				return true;
			}
		}
		
		return false;
		*/
	}

	/*
	 * @GetMapping(value="/uploads/{filename:.+}") public ResponseEntity<Resource>
	 * verFoto(@PathVariable String filename) { Path pathFoto =
	 * Paths.get("uploads").resolve(filename).toAbsolutePath(); Resource recurso =
	 * null; try { recurso = new UrlResource(pathFoto.toUri()); if(!recurso.exists()
	 * || !recurso.isReadable()) { throw new
	 * RuntimeException("Error: no se puede cargar la imagen: " +
	 * pathFoto.toString()); } } catch (MalformedURLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * return ResponseEntity.ok() .header(HttpHeaders.CONTENT_DISPOSITION,
	 * "attachment; filename=\"" + recurso.getFilename() +"\"") .body(recurso); }
	 */

}
