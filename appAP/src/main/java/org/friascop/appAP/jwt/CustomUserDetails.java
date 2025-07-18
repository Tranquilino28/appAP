package org.friascop.appAP.jwt;

import org.friascop.appAP.entities.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {


        private final Usuario usuario;

        public CustomUserDetails(Usuario usuario) {
            this.usuario = usuario;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            // Devuelve el rol como autoridad
            return List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));
        }

        @Override
        public String getPassword() {
            return usuario.getHashpassword(); // no se usa directamente si manejas el verificador manual
        }

        @Override
        public String getUsername() {
            return usuario.getNombreUsuario();
        }

        @Override
        public boolean isAccountNonExpired() { return true; }

        @Override
        public boolean isAccountNonLocked() { return true; }

        @Override
        public boolean isCredentialsNonExpired() { return true; }

        @Override
        public boolean isEnabled() { return true; }

        public String getSalt() {
            return usuario.getHashsalt(); // útil si lo necesitas en el verificador
        }

        public String getRol() {
            return usuario.getRol();
        }

        public Usuario getUsuario() {
            return usuario;
        }


}
