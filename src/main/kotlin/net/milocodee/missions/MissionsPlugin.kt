package net.milocodee.missions

import net.milocodee.missions.commands.MissionCommand
import net.milocodee.missions.listeners.BlockBreakListener
import net.milocodee.missions.listeners.EntityKillListener
import org.bukkit.plugin.java.JavaPlugin

class MissionsPlugin : JavaPlugin() {
    lateinit var missionManager: MissionManager
        private set

    override fun onEnable() {
        missionManager = MissionManager(this)

        server.pluginManager.registerEvents(BlockBreakListener(missionManager), this)
        server.pluginManager.registerEvents(EntityKillListener(missionManager), this)

        getCommand("mission")?.setExecutor(MissionCommand(missionManager))

        logger.info("MissionsPlugin enabled!")
    }

    override fun onDisable() {
        missionManager.saveAll()
        logger.info("MissionsPlugin disabled.")
    }
}
