package com.fatecitaquera.carteirinhadigital.exceptions

import com.fatecitaquera.carteirinhadigital.exceptions.enums.RuntimeErrorEnum

class OperationNotAllowedException(val error: RuntimeErrorEnum): RuntimeException()