package com.rookieand.runeprofession.manager;

import com.rookieand.runeprofession.RuneProfession;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("deprecation")
public class MiningSkillManager extends PlayerManager {

    private static final HashMap<Material, Integer> otherMineralChance = new HashMap<Material, Integer>(6){{
        put(Material.COAL, 300);
        put(Material.LAPIS_LAZULI, 400);
        put(Material.IRON_ORE, 150);
        put(Material.GOLD_ORE, 100);
        put(Material.DIAMOND, 30);
        put(Material.EMERALD, 20);
    }};

    private static final HashMap<Material, Material> smeltOreToIngot = new HashMap<Material, Material>(2){{
        put(Material.IRON_ORE, Material.IRON_INGOT);
        put(Material.GOLD_ORE, Material.GOLD_INGOT);
    }};

    // 돌 대신 다른 광물을 얻을 수 있게 해주는 메소드
    public static void findOtherMineral(Player player, Location loc) {
        World world = player.getWorld();
        PlayerManager.sendActionBar(player, RuneProfession.getPrefix() + "§e[채광] §7기술을 통해 돌에서 §e다른 자원 §7을 골라냈습니다!");
        // 돌 대신 제공할 광물을 선택하기 위한 랜덤 변수 두 개를 선언 및 초기화
        int mineralChance = ThreadLocalRandom.current().nextInt(1000);
        int currentChance = 0;
        // 각 광물들의 Material 과 확률이 들어간 Map 을 순회하여, 해당 확률에 맞는 광물을 제공함.
        for (Map.Entry<Material, Integer> entry : otherMineralChance.entrySet()) {
            currentChance += entry.getValue();
            // 만약 확률에 맞는 Material 을 찾았다면, 이를 ItemStack 으로 치환한 후 유저에게 제공함.
            if (currentChance >= mineralChance) {
                ItemStack drop = new ItemStack(entry.getKey(), 1);
                world.dropItemNaturally(loc, drop);
                break;
            }
        }
    }

    // 나오는 광물의 양을 기존의 2배로 늘려주는 메소드
    public static void makeMineralsDouble(Player player, Block mineBlock) {
        World world = player.getWorld();
        PlayerManager.sendActionBar(player, RuneProfession.getPrefix() + "§e[채광] §7기술을 통해 자원을 §e두 배로 §7획득했습니다!");
        Collection<ItemStack> mineDrop = mineBlock.getDrops(player.getInventory().getItemInMainHand());
        // 광물을 캐서 얻은 ItemStack Collection 을 순회하여 수량을 2배로 늘려줌
        for (ItemStack drop : mineDrop) {
            drop.setAmount(drop.getAmount() * 2);
            world.dropItemNaturally(mineBlock.getLocation(), drop);
        }
    }

    // 나오는 광물의 양을 기존의 3배로 늘려주는 메소드
    public static void makeMineralsTriple(Player player, Block mineBlock) {
        World world = player.getWorld();
        PlayerManager.sendActionBar(player, RuneProfession.getPrefix() + "§e[채광] §7기술을 통해 자원을 §e세 배로 §7획득했습니다!");
        Collection<ItemStack> mineDrop = mineBlock.getDrops(player.getInventory().getItemInMainHand());
        // 광물을 캐서 얻은 ItemStack Collection 을 순회하여 수량을 3배로 늘려줌
        for (ItemStack drop : mineDrop) {
            drop.setAmount(drop.getAmount() * 3);
            world.dropItemNaturally(mineBlock.getLocation(), drop);
        }
    }

    // 철광석, 금광석을 자동으로 철괴, 금괴로 제련시켜주는 메소드
    public static void autoSmeltOre(Player player, Block mineBlock) {
        World world = player.getWorld();
        PlayerManager.sendActionBar(player, RuneProfession.getPrefix() + "§e[채광] §7기술을 통해 원석을 §e자동으로 §7제련했습니다!");
        // 광물을 캐서 얻은 ItemStack Collection 을 순회하여 수량을 3배로 늘려줌
        ItemStack drop = new ItemStack(smeltOreToIngot.get(mineBlock.getType()), 1);
        world.dropItemNaturally(mineBlock.getLocation(), drop);
    }
}
