package dk.marcusrokatis.broadcastForwarder

import com.tchristofferson.configupdater.ConfigUpdater
import org.bukkit.configuration.file.FileConfiguration
import java.io.File

class PluginConfig(plugin: BroadcastForwarder) {

    private val configFile: File = File(plugin.dataFolder, "config.yml")

    var forwardToChannel: String = ""
    var forwardAllBroadcasts: Boolean = false
    var allowedPrefixes: List<String> = listOf()

    init {
        plugin.saveDefaultConfig()
        update()

        val config: FileConfiguration = plugin.config

        forwardToChannel = config.getString("forwardToChannel") ?: ""
        forwardAllBroadcasts = config.getBoolean("forwardAllBroadcasts")
        allowedPrefixes = config.getList("allowedPrefixes")?.map { it.toString() } ?: listOf()
    }

    fun update() = ConfigUpdater.update(BroadcastForwarder.INSTANCE, "config.yml", configFile)
}