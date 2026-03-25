package com.fatecitaquera.carteirinhadigital.exceptions

import com.fatecitaquera.carteirinhadigital.exceptions.enums.RuntimeErrorEnum

class ResourceNotFoundException(val error: RuntimeErrorEnum): RuntimeException()