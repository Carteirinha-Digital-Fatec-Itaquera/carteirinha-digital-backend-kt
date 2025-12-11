package com.fatecitaquera.carteirinhadigital.exceptions

import com.fatecitaquera.carteirinhadigital.exceptions.enums.RuntimeErrorEnum
import java.lang.RuntimeException

class OperationNotAllowedException(val error: RuntimeErrorEnum): RuntimeException()