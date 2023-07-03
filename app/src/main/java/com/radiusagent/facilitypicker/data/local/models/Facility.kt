package com.radiusagent.facilitypicker.data.local.models

import io.realm.RealmList
import io.realm.RealmObject


open class Facility(
    var facilityId: String = "",
    var name: String = "",
    var options: RealmList<Option> = RealmList()
) : RealmObject()

