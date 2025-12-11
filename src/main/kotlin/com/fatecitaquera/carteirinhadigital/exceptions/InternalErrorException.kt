package com.fatecitaquera.carteirinhadigital.exceptions

import com.fatecitaquera.carteirinhadigital.exceptions.enums.RuntimeErrorEnum
import java.lang.RuntimeException

class InternalErrorException(val error: RuntimeErrorEnum): RuntimeException()