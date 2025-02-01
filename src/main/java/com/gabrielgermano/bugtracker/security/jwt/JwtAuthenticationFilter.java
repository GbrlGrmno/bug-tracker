package com.gabrielgermano.bugtracker.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    public JwtAuthenticationFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        logger.info("Processing JWT authentication request: {}", request.getRequestURI());

        final String authHeader = request.getHeader("Authorization");
        String jwtToken = null;
        String username = null;


        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            logger.info("Authentication header found and starts with 'Bearer '");

            jwtToken = authHeader.substring(7);
            username = jwtUtils.getUsernameFromToken(jwtToken);

            logger.debug("Extracted JWT token: {}", jwtToken);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                logger.info("No existing authentication found for username {}", username);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                logger.debug("Loaded userdetails for username: {}", username);

                if (jwtUtils.isTokenValid(jwtToken, userDetails)) {
                    logger.info("JWT token is valid for username {}", username);
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    logger.info("Authentication set in SecurityContext for username: {}", username);
                } else {
                    logger.warn("JWT token is invalid or expired for username: {}", username);
                }


            }
        }
        logger.info("Passing request to the next filter in the filter chain");
        filterChain.doFilter(request,response);
    }
}
