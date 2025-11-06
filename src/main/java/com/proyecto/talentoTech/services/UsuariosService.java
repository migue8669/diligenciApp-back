package com.proyecto.talentoTech.services;

import com.proyecto.talentoTech.models.UsuariosModel;
import com.proyecto.talentoTech.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuariosService {
    @Autowired
    IUsuarioRepository usuarioRepository;

    public ArrayList<UsuariosModel> getUsuarios(){
        return (ArrayList<UsuariosModel>) usuarioRepository.findAll();
    }

    public UsuariosModel saveUsuarios(UsuariosModel user){
        return usuarioRepository.save(user);
    }

    public Optional<UsuariosModel> getById(Long id){
        return usuarioRepository.findById(id);
    }

    public Optional<UsuariosModel> authenticateUser(String username, String password) {
        // 1. El repositorio busca al usuario SÓLO si el username Y el password coinciden.
        Optional<UsuariosModel> user = usuarioRepository.findByUsernameAndPassword(username, password);

        // 2. Retorna el resultado del repositorio.
        // Si el usuario fue encontrado, devuelve Optional con el usuario.
        // Si no fue encontrado (credenciales incorrectas), devuelve Optional.empty().
        return user;
    }

    public UsuariosModel updateById(UsuariosModel request, Long id){
        // 1. Obtener el usuario existente como Optional
        Optional<UsuariosModel> userOptional = usuarioRepository.findById(id);

        if (userOptional.isPresent()) {
            UsuariosModel user = userOptional.get();

            // 2. FUSIONAR (Merge): Aplicar el cambio del PUT (userType)
            // Solo actualizamos userType si el valor viene en el cuerpo de la solicitud (request)
            if (request.getUserType() != null && !request.getUserType().isEmpty()) {
                user.setUserType(request.getUserType());
            }

            // 3. ✅ CRÍTICO: Guardar la entidad modificada en la base de datos.
            return usuarioRepository.save(user);
        } else {
            // Si el usuario no existe, lanzar una excepción (el controlador la convertirá a 404 o 500)
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }
    public Boolean deleteUsuario(Long id){
        try {
            usuarioRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
