package net.milocodee.missions.commands

import net.milocodee.missions.MissionManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class MissionCommand(private val missionManager: MissionManager) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("Nur Spieler können Missionen benutzen.")
            return true
        }

        if (args.isEmpty()) {
            val data = missionManager.getActiveMission(sender)
            if (data == null) {
                sender.sendMessage("§eKeine aktive Mission. Benutze §a/mission list§e.")
            } else {
                sender.sendMessage("§aAktive Mission: §e${data.mission.id} §7(${data.progress}/${data.mission.goal})")
            }
            return true
        }

        when (args[0].lowercase()) {
            "list" -> {
                sender.sendMessage("§6Verfügbare Missionen:")
                missionManager.listMissions().forEach {
                    sender.sendMessage("§7- §a${it.id} §f(${it.goal} Ziel)")
                }
            }
            "start" -> {
                if (args.size < 2) {
                    sender.sendMessage("§cVerwendung: /mission start <id>")
                    return true
                }
                missionManager.startMission(sender, args[1])
            }
            else -> sender.sendMessage("§eVerwendung: /mission [list|start]")
        }
        return true
    }
}
