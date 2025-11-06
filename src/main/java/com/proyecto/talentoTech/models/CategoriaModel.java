package com.proyecto.talentoTech.models;


import jakarta.persistence.*;

@Entity
@Table(name = "categorias") // Mapea a la tabla 'categorias' en la BD
public class CategoriaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoria_id; // Usamos 'int' ya que los IDs en SQL eran más pequeños

    @Column(name = "nombre_principal", nullable = false, length = 100)
    private String nombrePrincipal;

    @Column(name = "nombre_subcategoria", nullable = false, length = 100, unique = true)
    private String nombreSubcategoria;

    @Column(name = "descripcion", length = 255)
    private String descripcion;


    // --- Getters y Setters ---

    public int getId() {
        return categoria_id;
    }

    public void setId(int id) {
        this.categoria_id = id;
    }

    public String getNombrePrincipal() {
        return nombrePrincipal;
    }

    public void setNombrePrincipal(String nombrePrincipal) {
        this.nombrePrincipal = nombrePrincipal;
    }

    public String getNombreSubcategoria() {
        return nombreSubcategoria;
    }

    public void setNombreSubcategoria(String nombreSubcategoria) {
        this.nombreSubcategoria = nombreSubcategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}