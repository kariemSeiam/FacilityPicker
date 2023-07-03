package com.radiusagent.facilitypicker.data.local.models

import io.realm.RealmObject


open class Option(
    var name: String = "",
    var icon: String = "",
    var id: String = ""
) : RealmObject()