package org.friascop.appAP.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}


