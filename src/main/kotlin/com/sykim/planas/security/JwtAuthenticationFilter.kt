package com.sykim.planas.security

import com.sykim.planas.auth.service.CustomUserDetailsService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.extern.java.Log
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import kotlin.math.log

@Component
class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider,
    private val userDetailsService: CustomUserDetailsService
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val header = request.getHeader("Authorization")

        if (header != null && header.startsWith("Bearer ")) {
            val token = header.substring(7)
            logger.info("token: $token")

            try {
                if (jwtTokenProvider.validateToken(token)) {
                    val username = jwtTokenProvider.getUsername(token)
                    logger.info("username : $username")
                    if (SecurityContextHolder.getContext().authentication == null) {
                        val userDetails = userDetailsService.loadUserByUsername(username)

                        val auth = UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.authorities
                        )
                        auth.details = WebAuthenticationDetailsSource().buildDetails(request)
                        logger.info("auth details : ${auth.details}")

                        SecurityContextHolder.getContext().authentication = auth
                    }
                } else {
                    // 유효하지 않은 토큰 → 바로 401
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "INVALID_TOKEN")
                    logger.info("유효하지 않은 토큰 → 바로 401")
                    return
                }
            } catch (e: io.jsonwebtoken.ExpiredJwtException) {
                // 🔥 만료된 토큰 → 401
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "TOKEN_EXPIRED")
                logger.info("만료된 토큰 → 401")
                return
            } catch (e: Exception) {
                // 그 외 JWT 관련 예외도 전부 401로 통일
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "INVALID_TOKEN")
                logger.info("그 외 JWT 관련 예외도 전부 401로 통일")
                return
            }
        }

        filterChain.doFilter(request, response)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        val path = request.servletPath
        return path.startsWith("/api/v1/auth/")
    }

}