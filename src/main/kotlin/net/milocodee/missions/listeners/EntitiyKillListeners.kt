package net.milocodee.missions.listeners

import net.milocodee.missions.MissionManager
import net.milocodee.missions.MissionType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent

class EntityKillListener(private val missionManager: MissionManager) : Listener {
    @EventHandler
    fun onKill(e: EntityDeathEvent) {
        val killer = e.entity.killer ?: return
        val type = e.entity.type.name
        missionManager.addProgress(killer, MissionType.KILL_ENTITY, type)
    }
}
