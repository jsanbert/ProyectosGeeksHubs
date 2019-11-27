package com.javierrodriguez.springsecurityexample.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private static final Logger LOG = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    public void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(req, res);
        if(!existeJWT(req, res)) {
            filterChain.doFilter(req, res);
            return;
        }
        Claims claims = this.validateJWT(req);
        if(claims.get("rol") != null) {
            this.setupSpringAuthentication(claims);
        } else {
            SecurityContextHolder.clearContext();
        }
        return;
    }

    private boolean existeJWT(HttpServletRequest req, HttpServletResponse res) {
        String authorizationHeader = req.getHeader("Authorization");
        return !(authorizationHeader == null || !authorizationHeader.startsWith("Bearer"));
    }

    private Claims validateJWT(HttpServletRequest req) {
        String jwToken = req.getHeader("Authorization").replace("Bearer", "");
                return Jwts.parser()
                        .setSigningKey("poiuaodfjsoidghapsduore$%IJOu5i-rjklpwqoiuwopgidajklñdsghasjdñglkjqwjr58252")
                        .parseClaimsJws(jwToken).getBody();
    }

    private void setupSpringAuthentication(Claims claims) {
        List<String> roles = (List<String>) claims.get("rol");
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
