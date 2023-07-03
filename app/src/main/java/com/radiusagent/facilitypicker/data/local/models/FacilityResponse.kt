package com.radiusagent.facilitypicker.data.local.models

import io.realm.RealmList
import io.realm.RealmObject


open class FacilityResponse(
    var facilities: RealmList<Facility> = RealmList(),
    var exclusionOptions: RealmList<RealmList<ExclusionOptions>> = RealmList()
) : RealmObject()

