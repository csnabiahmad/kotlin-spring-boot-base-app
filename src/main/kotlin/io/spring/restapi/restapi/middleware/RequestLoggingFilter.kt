package io.spring.restapi.restapi.middleware

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component


@Component
class RequestLoggingFilter : Filter {
    val loggerFactory = LoggerFactory.getLogger("Logger")!!

    override fun doFilter(
        servletRequest: ServletRequest,
        servletResponse: ServletResponse,
        filterChain: FilterChain
    ) {
        val parameter = servletRequest.getParameter("name")
        loggerFactory.info("Logging source: $parameter")
        filterChain.doFilter(servletRequest, servletResponse)
    }
}