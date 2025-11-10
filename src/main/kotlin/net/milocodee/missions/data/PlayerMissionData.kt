package net.milocodee.missions.data

import net.milocodee.missions.Mission

data class PlayerMissionData(
    val mission: Mission,
    var progress: Int = 0,
    var completed: Boolean = false
)
