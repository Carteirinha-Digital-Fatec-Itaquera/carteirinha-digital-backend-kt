package com.fatecitaquera.carteirinhadigital.exceptions

import com.fatecitaquera.carteirinhadigital.exceptions.enums.RuntimeErrorEnum
import org.apache.tomcat.websocket.AuthenticationException
import java.lang.RuntimeException

class AuthenticationFailedException(val error: RuntimeErrorEnum): AuthenticationException(error.description)