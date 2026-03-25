package com.fatecitaquera.carteirinhadigital.exceptions

import com.fatecitaquera.carteirinhadigital.exceptions.enums.RuntimeErrorEnum
import org.apache.tomcat.websocket.AuthenticationException

class AuthenticationFailedException(val error: RuntimeErrorEnum): AuthenticationException(error.description)