package com.pog.passionfruitOrangeGuava.networking

sealed class ResponseState<out T> constructor(data: T?, message: String?)

class StateLoading<out T>(val data: T? = null, val message: String? = null): ResponseState<T>(data, message)
class StateSuccess<out T>(val data: T?, val message: String? = null): ResponseState<T>(data, message)
class StateFailed<out T>(val data: T? = null, val message: String?): ResponseState<T>(data, message)
