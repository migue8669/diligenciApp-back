package com.proyecto.talentoTech.services;

import com.proyecto.talentoTech.models.CategoriaModel;
import com.proyecto.talentoTech.models.ReporteModel;
import com.proyecto.talentoTech.repositories.ICategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service

public class CategoriaService {
    @Autowired
    ICategoriaRepository categoriaRepository;

    public ArrayList<CategoriaModel> getCategorias(){
        return (ArrayList<CategoriaModel>) categoriaRepository.findAll();
    }
    public Optional<CategoriaModel> getCategoriaById(Long id){
        return categoriaRepository.findById(id);
    }


}
