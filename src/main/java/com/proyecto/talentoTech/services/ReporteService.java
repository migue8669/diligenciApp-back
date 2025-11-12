package com.proyecto.talentoTech.services;

import com.proyecto.talentoTech.models.ReporteModel;
import com.proyecto.talentoTech.repositories.IReporteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ReporteService {
    @Autowired
    IReporteRepository reporteRepository;

    public ArrayList<ReporteModel> getReporte(){
        return (ArrayList<ReporteModel>) reporteRepository.findAll();
    }

    public ReporteModel saveReporte(ReporteModel report){
        return reporteRepository.save(report);
    }

    public Optional<ReporteModel> getById(Long id){
        return reporteRepository.findById(id);
    }
    public ReporteModel updateById(ReporteModel request, Long id){

        ReporteModel report = reporteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reporte con ID " + id + " no encontrado para actualizar."));        report.setTitulo(request.getTitulo());
        report.setServicio(request.getServicio());
        report.setPrecio(request.getPrecio());
        report.setDireccion(request.getDireccion());
        report.setTelefono(request.getTelefono());
        report.setCategoria(request.getCategoria());
        report.setLat(request.getLat());
        report.setLng(request.getLng());
        report.setEstado(request.getEstado());
        report.setTomadoPor(request.getTomadoPor());
        report.setUsuario(request.getUsuario());
        report.setUsuarioTipo(request.getUsuarioTipo());
        report.setImagen(request.getImagen());
        return reporteRepository.save(report);
    }
    public Boolean deleteReporte(Long id){
        try {
            reporteRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
