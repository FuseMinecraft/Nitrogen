package com.fusenetworks.fuse

import com.fusenetworks.fuse.commands.CMD_Handler
import com.fusenetworks.fuse.commands.CMD_Loader
import com.fusenetworks.fuse.listener.*
import com.fusenetworks.fuse.util.NLog
import com.fusenetworks.fuse.util.NUtil
import org.bukkit.Bukkit
import org.bukkit.Server
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

class Fuse : JavaPlugin() {
    internal var jarFile = this.file

    override fun onLoad() {
        NLog.setServerLogger(server.logger)
        NLog.setServerLogger(server.logger)
    }

    override fun onEnable() {
        server.pluginManager.registerEvents(AutoUpdate(), Fuse())
        server.pluginManager.registerEvents(CommandBlocker(), Fuse())
        server.pluginManager.registerEvents(Commandspy(), Fuse())
        server.pluginManager.registerEvents(ConfigListener(), Fuse())
        server.pluginManager.registerEvents(Developer(), Fuse())
        server.pluginManager.registerEvents(Launchpads(), Fuse())
        server.pluginManager.registerEvents(LoginMessages(), Fuse())
        server.pluginManager.registerEvents(NoFall(), Fuse())
        server.pluginManager.registerEvents(NoHunger(), Fuse())
        server.pluginManager.registerEvents(PotionListener(), Fuse())
        server.pluginManager.registerEvents(SignPatch(), Fuse())
        Config.loadConfigs()
        object : BukkitRunnable() {
            override fun run() {
                CMD_Loader.commandMap
                CMD_Loader.scan()
            }
        }.runTaskLater(Fuse(), 20L)
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("Fuse"), {
            if (NUtil.NEntityWiper.wipeEntities(true, true) == 1) {
                NLog.info(NUtil.NEntityWiper.wipeEntities(true, true).toString() + " entity removed")
            } else if (NUtil.NEntityWiper.wipeEntities(true, true) != 0) {
                NLog.info(NUtil.NEntityWiper.wipeEntities(true, true).toString() + " entities removed")
            }
        }, 1L, 300.toLong() * 20)
    }

    override fun onDisable() {}

    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {
        return CMD_Handler.handleCommand(sender, cmd, commandLabel, args)
    }

    class JavaClient {
        public static getInstance(): Fuse? {
            return instance
        }
    }

    companion object {

        var plugin: Fuse? = null
        var server: Server? = null
        var instance: Fuse? = null


        var buildDate = "8/19/17"
        var buildCreator = "Telesphoreo"
    }
}
}