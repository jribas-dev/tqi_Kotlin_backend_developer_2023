package br.com.tqi.backend_2023.exception

import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class RestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerValidException(ex: MethodArgumentNotValidException): ResponseEntity<DetailsException> {
        val errors: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.stream().forEach { error: ObjectError ->
            val fieldName: String = (error as FieldError).field
            val messageError: String? = error.defaultMessage
            errors[fieldName] = messageError
        }
        return ResponseEntity(
            DetailsException(
                title = "Bad Request! Consult the documentation",
                timestamp = LocalDateTime.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                exception = ex.javaClass.toString(),
                details = errors
            ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(DataAccessException::class)
    fun handlerValidException(ex: DataAccessException): ResponseEntity<DetailsException> {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(
                DetailsException(
                    title = "Conflict! Consult the documentation",
                    timestamp = LocalDateTime.now(),
                    status = HttpStatus.CONFLICT.value(),
                    exception = ex.javaClass.toString(),
                    details = mutableMapOf(ex.cause.toString() to ex.message)
                )
            )
    }

    @ExceptionHandler(BusinessException::class)
    fun handlerValidException(ex: BusinessException): ResponseEntity<DetailsException> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                DetailsException(
                    title = "Bad Request! Consult the documentation",
                    timestamp = LocalDateTime.now(),
                    status = HttpStatus.BAD_REQUEST.value(),
                    exception = ex.javaClass.toString(),
                    details = mutableMapOf(ex.cause.toString() to ex.message)
                )
            )
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handlerValidException(ex: IllegalArgumentException): ResponseEntity<DetailsException> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                DetailsException(
                    title = "Bad Request! Consult the documentation",
                    timestamp = LocalDateTime.now(),
                    status = HttpStatus.BAD_REQUEST.value(),
                    exception = ex.javaClass.toString(),
                    details = mutableMapOf(ex.cause.toString() to ex.message)
                )
            )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handlerValidException(ex: HttpMessageNotReadableException): ResponseEntity<DetailsException> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                DetailsException(
                    title = "Badly formatted request! Consult the documentation",
                    timestamp = LocalDateTime.now(),
                    status = HttpStatus.BAD_REQUEST.value(),
                    exception = ex.javaClass.toString(),
                    details = mutableMapOf("msg" to ex.message)
                )
            )
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handlerValidException(ex: HttpRequestMethodNotSupportedException): ResponseEntity<DetailsException> {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
            .body(
                DetailsException(
                    title = "Method not supported! Consult the documentation",
                    timestamp = LocalDateTime.now(),
                    status = HttpStatus.METHOD_NOT_ALLOWED.value(),
                    exception = ex.javaClass.toString(),
                    details = mutableMapOf("msg" to ex.message)
                )
            )
    }

}