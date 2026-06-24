package com.uade.tpejemplo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.uade.tpejemplo.dto.request.PermisosRequest;
import com.uade.tpejemplo.dto.response.UsuarioResponse;
import com.uade.tpejemplo.model.Rol;
import com.uade.tpejemplo.model.Usuario;
import com.uade.tpejemplo.repository.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')") // Protege toda la clase
public class AdminController {

    private final UsuarioRepository usuarioRepository;

    public AdminController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/usuarios")
    public List<UsuarioResponse> listarUsuarios() {
        // Usamos el nuevo método del repositorio para traer SOLAMENTE a los USER
        return usuarioRepository.findByRol(Rol.USER).stream()
            .map(u -> new UsuarioResponse(
                u.getId(), 
                u.getUsername(), 
                u.getRol().name(), 
                u.isPuedeAnularCredito(), 
                u.isPuedeAnularCobranza()
            ))
            .collect(Collectors.toList());
    }
    @PutMapping("/usuarios/{id}/permisos")
    public UsuarioResponse actualizarPermisos(@PathVariable Long id, @RequestBody PermisosRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            
        usuario.setPuedeAnularCredito(request.isPuedeAnularCredito());
        usuario.setPuedeAnularCobranza(request.isPuedeAnularCobranza());
        
        usuarioRepository.save(usuario);
        
        return new UsuarioResponse(
            usuario.getId(), 
            usuario.getUsername(), 
            usuario.getRol().name(), // <-- ACÁ ESTÁ EL CAMBIO (.name())
            usuario.isPuedeAnularCredito(), 
            usuario.isPuedeAnularCobranza()
        );
    }
}

