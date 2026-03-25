package com.fatecitaquera.carteirinhadigital.exceptions

import com.fatecitaquera.carteirinhadigital.exceptions.enums.RuntimeErrorEnum

class DuplicateResourceException(val error: RuntimeErrorEnum): RuntimeException()