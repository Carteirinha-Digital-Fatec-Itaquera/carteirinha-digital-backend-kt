package com.fatecitaquera.carteirinhadigital.exceptions

import com.fatecitaquera.carteirinhadigital.exceptions.enums.RuntimeErrorEnum

class InvalidArgumentsException(val error: RuntimeErrorEnum): RuntimeException()