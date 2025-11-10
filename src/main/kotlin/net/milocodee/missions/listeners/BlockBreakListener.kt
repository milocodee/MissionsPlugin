package net.milocodee.missions.listeners

import net.milocodee.missions.MissionManager
import net.milocodee.missions.MissionType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class BlockBreakListener(private val missionManager: MissionManager) : Listener {
    @EventHandler
    fun onBreak(e: BlockBreakEvent) {
        val material = e.block.type.name
        missionManager.addProgress(e.player, MissionType.BREAK_BLOCK, material)
    }
}
