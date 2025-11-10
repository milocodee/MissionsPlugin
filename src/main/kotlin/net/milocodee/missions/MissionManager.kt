package net.milocodee.missions

import net.milocodee.missions.data.PlayerMissionData
import org.bukkit.entity.Player
import java.io.File
import java.util.*

class MissionManager(private val plugin: MissionsPlugin) {
    private val playerData = mutableMapOf<UUID, PlayerMissionData>()
    private val missions = listOf(
        Mission("stone_break", MissionType.BREAK_BLOCK, "STONE", 10, 50),
        Mission("zombie_kill", MissionType.KILL_ENTITY, "ZOMBIE", 5, 100)
    )

    fun startMission(player: Player, missionId: String) {
        val mission = missions.find { it.id == missionId } ?: return player.sendMessage("§cUnbekannte Mission.")
        playerData[player.uniqueId] = PlayerMissionData(mission)
        player.sendMessage("§aMission gestartet: §e${mission.id} (§b${mission.goal} Ziel§a)")
    }

    fun addProgress(player: Player, type: MissionType, target: String) {
        val data = playerData[player.uniqueId] ?: return
        if (data.completed) return
        if (data.mission.type != type || data.mission.target != target) return

        data.progress++
        val mission = data.mission

        if (data.progress >= mission.goal) {
            data.completed = true
            player.sendMessage("§6Mission abgeschlossen! §e+${mission.reward} Punkte")
            // Beispiel: XP Belohnung
            player.giveExp(mission.reward)
        } else {
            player.sendMessage("§7Fortschritt: §e${data.progress}/${mission.goal}")
        }
    }

    fun getActiveMission(player: Player): PlayerMissionData? = playerData[player.uniqueId]

    fun listMissions(): List<Mission> = missions

    fun saveAll() {
        // Optional: persistieren (z. B. YAML oder JSON)
        val file = File(plugin.dataFolder, "missions.txt")
        file.writeText(playerData.toString())
    }
}
