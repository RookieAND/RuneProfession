package com.rookieand.runeprofession.manager;

import com.rookieand.runeprofession.RuneProfession;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

@SuppressWarnings("deprecation")
public class GatheringSkillManager extends PlayerManager {

    // 작물을 캐서 얻은 ItemStack 의 수량을 2배로 늘려주는 메소드
    public static void makeCropsDouble(Player player, Block cropBlock) {
        World world = player.getWorld();
        Collection<ItemStack> cropDrop = cropBlock.getDrops(player.getInventory().getItemInMainHand());
        PlayerManager.sendActionBar(player, RuneProfession.getPrefix() + "§e[채집] §7기술의 영향으로 작물을 §e두 배 §7얻었습니다!");
        // 작물을 캐서 얻은 ItemStack Collection 을 순회하여 수량을 2배로 늘려줌
        for (ItemStack drop : cropDrop) {
            drop.setAmount(drop.getAmount() * 2);
            world.dropItemNaturally(cropBlock.getLocation(), drop);
        }
    }

    // 작물을 캐서 얻은 ItemStack 의 수량을 3배로 늘려주는 메소드
    public static void makeCropsTriple(Player player, Block cropBlock) {
        World world = player.getWorld();
        Collection<ItemStack> cropDrop = cropBlock.getDrops(player.getInventory().getItemInMainHand());
        PlayerManager.sendActionBar(player, RuneProfession.getPrefix() + "§e[채집] §7기술의 영향으로 작물을 §e세 배 §7얻었습니다!");
        // 작물을 캐서 얻은 ItemStack Collection 을 순회하여 수량을 3배로 늘려줌
        for (ItemStack drop : cropDrop) {
            drop.setAmount(drop.getAmount() * 3);
            world.dropItemNaturally(cropBlock.getLocation(), drop);
        }
    }
}
