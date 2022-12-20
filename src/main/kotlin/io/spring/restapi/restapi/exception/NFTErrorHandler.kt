package io.spring.restapi.restapi.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class NFTErrorHandler  {
    @ExceptionHandler(NFTNotFoundException::class)
    fun handleNFTNotFoundException(
        servletRequest: HttpServletRequest,
        exception: Exception
    ): ResponseEntity<String> {
        return ResponseEntity("NFT not found", HttpStatus.NOT_FOUND)
    }
}