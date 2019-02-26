package isel.ps.employbox

import isel.ps.employbox.exceptions.*
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalControllerExceptionHandler {

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequest(exception: BadRequestException): ResponseEntity<ErrorModel> {
        val error = ErrorModel("http://example.com/error/types/rels/some-type", HttpStatus.BAD_REQUEST.value(), exception.message ?: "", HttpStatus.BAD_REQUEST.reasonPhrase)
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(UnauthorizedException::class)
    fun handleUnauthorized(exception: UnauthorizedException): ResponseEntity<ErrorModel> {
        val headers = HttpHeaders()
        headers.add("WWW-Authenticate", "Basic realm = \"checklists\" charset=\"UTF-8\"")
        val error = ErrorModel(BLANK, HttpStatus.UNAUTHORIZED.value(), exception.message ?: "", HttpStatus.UNAUTHORIZED.reasonPhrase)
        return ResponseEntity(error, headers, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(ForbiddenException::class)
    fun handleForbiddenRequest(exception: ForbiddenException): ResponseEntity<ErrorModel> {
        val error = ErrorModel(BLANK, HttpStatus.FORBIDDEN.value(), exception.message ?: "", HttpStatus.FORBIDDEN.reasonPhrase)
        return ResponseEntity(error, HttpStatus.FORBIDDEN)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFound(exception: ResourceNotFoundException): ResponseEntity<ErrorModel> {
        val error = ErrorModel(BLANK, HttpStatus.NOT_FOUND.value(), exception.message ?: "", HttpStatus.NOT_FOUND.reasonPhrase)
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(ConflictException::class)
    fun handleConflict(exception: ConflictException): ResponseEntity<ErrorModel> {
        val error = ErrorModel(BLANK, HttpStatus.CONFLICT.value(), exception.message ?: "", HttpStatus.CONFLICT.reasonPhrase)
        return ResponseEntity(error, HttpStatus.CONFLICT)
    }

    class ErrorModel(val type: String, val status: Int, val detail: String, val title: String)

    companion object {
        private val BLANK = "about:BLANK"
    }
}