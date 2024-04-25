package com.dacha.model

import java.util.*

data class EnvStateEvent(val houseId: UUID, val envType: String, val amount: Int)
