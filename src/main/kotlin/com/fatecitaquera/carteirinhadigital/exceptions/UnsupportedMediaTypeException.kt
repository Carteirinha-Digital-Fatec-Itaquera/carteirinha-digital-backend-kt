package com.fatecitaquera.carteirinhadigital.exceptions

import com.fatecitaquera.carteirinhadigital.exceptions.enums.RuntimeErrorEnum

class UnsupportedMediaTypeException(val error: RuntimeErrorEnum): RuntimeException()