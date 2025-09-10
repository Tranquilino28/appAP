package org.friascop.appAP.dto;

public class UsuarioPerfil_dto {
    private String nombre;
    private String rol;
    private Integer empresaId;

    public UsuarioPerfil_dto() {}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    public Integer getEmpresaId() { return empresaId; }
}
