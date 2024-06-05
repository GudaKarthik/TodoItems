package com.example.consumerdashboard.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

@RealmClass
open class TodoItems  (

    @PrimaryKey
    var id: Int = 0,
    @Required
    var todoname: String? = null,
    var completed: Boolean? = null,
    var userId: String? = null) : RealmObject()

