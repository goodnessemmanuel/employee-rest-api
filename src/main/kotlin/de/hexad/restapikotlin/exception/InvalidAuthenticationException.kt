package de.hexad.restapikotlin.exception;

class InvalidAuthenticationException (private val msg: String) :Exception(msg)
