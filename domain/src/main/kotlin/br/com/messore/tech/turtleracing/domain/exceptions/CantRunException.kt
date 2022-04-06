package br.com.messore.tech.turtleracing.domain.exceptions

class CantRunException(turtleId: String) : Throwable("The turtle $turtleId not able to run!")
