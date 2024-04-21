package com.dacha.core.util

import java.util.UUID

fun String.toUuid(): UUID = UUID.fromString(this)