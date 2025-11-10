package net.milocodee.missions

enum class MissionType {
    BREAK_BLOCK,
    KILL_ENTITY
}

data class Mission(
    val id: String,
    val type: MissionType,
    val target: String,
    val goal: Int,
    val reward: Int
)
