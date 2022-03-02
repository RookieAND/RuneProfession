package com.rookieand.runeprofession.listener;

import com.rookieand.runeprofession.RuneProfession;
import com.rookieand.runeprofession.manager.PlayerManager;
import net.Indyuce.mmocore.api.event.CustomPlayerFishEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class FishingListener implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void successFishing(CustomPlayerFishEvent e) {
        Player player = e.getPlayer();
        // 먼저, 플레이어가 오른손으로 낚시를 진행했는지를 판별해야 함.
        if (player.getInventory().getItemInMainHand().getType() == Material.FISHING_ROD) {
            ItemStack caught = e.getCaught();
            int fishingLevel = PlayerManager.getProfessionLevel(player, "fishing");

        }
        else {
            player.sendMessage(RuneProfession.getPrefix() + "§e[오픈 필드] §7내에서는 낚시 행위가 §c불가합니다. §7섬이나 스폰의 낚시터에서 진행해주세요.");
        }

    }
}
