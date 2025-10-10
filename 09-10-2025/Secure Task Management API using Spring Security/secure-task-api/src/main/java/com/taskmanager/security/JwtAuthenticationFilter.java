package com.taskmanager.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        
        // Skip filter for auth endpoints
        final String requestPath = request.getServletPath();
        if (requestPath.startsWith("/api/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }
        
        // Extract JWT from Authorization header
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;
        
        // Check if Authorization header exists and starts with Bearer
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        
        // Extract token
        jwt = authHeader.substring(7);
        username = jwtService.extractUsername(jwt);
        
        // If username is extracted and user is not authenticated yet
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            
            // Load user details
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            
            // Validate token
            if (jwtService.isTokenValid(jwt, userDetails)) {
                
                // Create authentication token
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                
                // Set additional details
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                
                // Update security context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        
        // Continue filter chain
        filterChain.doFilter(request, response);
    }
}