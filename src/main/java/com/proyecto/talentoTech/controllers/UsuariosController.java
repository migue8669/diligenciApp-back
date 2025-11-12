package com.proyecto.talentoTech.controllers;

import com.proyecto.talentoTech.models.UsuariosModel;
import com.proyecto.talentoTech.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin(
        origins = "http://localhost:4200",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE, RequestMethod.OPTIONS},
        allowedHeaders = "*"
)@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @GetMapping
    public ArrayList<UsuariosModel> getUsuarios(){
        return this.usuariosService.getUsuarios();
    }

    @PostMapping
    public UsuariosModel saveUsuario(@RequestBody UsuariosModel user) {
        return this.usuariosService.saveUsuarios(user);
    }
   @GetMapping(path="/{id}")
    public Optional<UsuariosModel> getUsuarioById(@PathVariable("id") Long id){
        return this.usuariosService.getById(id);
   }
    // Nuevo método para manejar la petición GET de autenticación
    @GetMapping("/login") // EJEMPLO: http://localhost:8081/usuarios/login?username=X&password=Y
    public ResponseEntity<UsuariosModel> authenticate(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {

        Optional<UsuariosModel> authenticatedUser = usuariosService.authenticateUser(username, password);

        if (authenticatedUser.isPresent()) {
            // Usuario y contraseña correctos: Devuelve 200 OK con los datos del usuario.
            return ResponseEntity.ok(authenticatedUser.get());
        } else {
            // Credenciales incorrectas: Devuelve 401 Unauthorized.
            // Esto es crucial para que Angular sepa que falló.
            return ResponseEntity.status(401).build();
        }
    }
   @PutMapping(path="/{id}")
    public UsuariosModel updateUsuarioById(@RequestBody UsuariosModel request, @PathVariable("id") Long id ){
      return this.usuariosService.updateById(request,id);
   }
   @DeleteMapping(path = "/{id}")
    public String deleteUsuarioById(@PathVariable("id") Long id){
        boolean ok = this.usuariosService.deleteUsuario(id);
        if(ok){
            return id+" eliminado";
        }else {
            return "Error al eliminar";
        }
   }
}
