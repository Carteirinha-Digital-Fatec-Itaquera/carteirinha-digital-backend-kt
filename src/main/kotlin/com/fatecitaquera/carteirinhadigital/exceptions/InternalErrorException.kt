package com.fatecitaquera.carteirinhadigital.exceptions

import com.fatecitaquera.carteirinhadigital.exceptions.enums.RuntimeErrorEnum

class InternalErrorException(val error: RuntimeErrorEnum): RuntimeException()