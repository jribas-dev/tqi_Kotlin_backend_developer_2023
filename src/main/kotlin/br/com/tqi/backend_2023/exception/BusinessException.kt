package br.com.tqi.backend_2023.exception

data class BusinessException(override val message: String?) : RuntimeException(message)