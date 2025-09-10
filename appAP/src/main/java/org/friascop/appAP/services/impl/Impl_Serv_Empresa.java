package org.friascop.appAP.services.impl;

import org.friascop.appAP.auxdb.modelos.Empresa;
import org.friascop.appAP.repositories.InRepositorio_Empresa;
import org.friascop.appAP.services.interfaces.InServ_Empresa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Impl_Serv_Empresa implements InServ_Empresa {

    final private InRepositorio_Empresa repoEmpresa;

    public Impl_Serv_Empresa(InRepositorio_Empresa repoEmpresa) {
        this.repoEmpresa = repoEmpresa;
    }

    @Override
    public List<Empresa> saveAll(List<Empresa> empresa) {
        return null;
    }

    @Override
    public List<Empresa> findAll() {
        return (List<Empresa>) repoEmpresa.findAll();
    }

    @Override
    public Optional<Empresa> findById(Long id) {

return repoEmpresa.findById(id);
    }

    @Override
    public Empresa save(Empresa entity) {
        return repoEmpresa.save(entity);
    }

    @Override
    public Optional<Empresa> deleteById(Long id) {
        return null;
    }
}
