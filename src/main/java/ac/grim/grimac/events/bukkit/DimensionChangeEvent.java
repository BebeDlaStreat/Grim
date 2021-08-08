package ac.grim.grimac.events.bukkit;

import ac.grim.grimac.GrimAC;
import ac.grim.grimac.player.GrimPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class DimensionChangeEvent implements Listener {
    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onPlayerTeleportEvent(PlayerTeleportEvent event) {
        // How can getTo be null?
        if (event.getTo() != null && event.getFrom().getWorld() != event.getTo().getWorld()) {
            GrimPlayer player = GrimAC.playerGrimHashMap.get(event.getPlayer());
            if (player != null) {
                player.sendAndFlushTransactionOrPingPong();
                player.compensatedEntities.teleportWorldQueue.add(player.lastTransactionSent.get());
            }
        }
    }
}