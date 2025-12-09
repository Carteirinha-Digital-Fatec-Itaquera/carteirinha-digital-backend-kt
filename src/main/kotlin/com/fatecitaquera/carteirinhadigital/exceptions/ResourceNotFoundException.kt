package com.fatecitaquera.carteirinhadigital.exceptions

import com.fatecitaquera.carteirinhadigital.exceptions.enums.ErrorRuntimeEnum
import java.lang.RuntimeException

class ResourceNotFoundException(val error: ErrorRuntimeEnum): RuntimeException()