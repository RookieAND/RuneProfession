package com.rookieand.runeprofession.listener;

import com.rookieand.runeprofession.manager.SkillCalculateManager;
import com.rookieand.runeprofession.manager.GatheringSkillManager;
import com.rookieand.runeprofession.manager.MiningSkillManager;
import com.rookieand.runeprofession.manager.PlayerManager;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BlockListener implements Listener {

    // 전문기술의 영향을 받을 작물의 목록을 List<Material> 로 선언 (밀, 감자, 당근, 비트, 네더 사마귀, 코코아 콩)
    private final List<Material> cropList = Arrays.asList(Material.WHEAT, Material.POTATOES, Material.CARROTS, Material.BEETROOTS, Material.NETHER_WART, Material.COCOA);
    // 전문기술의 영향을 받을 광물의 목록을 List<Material> 로 선언 (돌, 철, 금, 석탄, 청금석, 다이아, 에메랄드 윈석)
    private final List<Material> oreList = Arrays.asList(Material.STONE, Material.LAPIS_ORE, Material.COAL_ORE, Material.IRON_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE, Material.EMERALD_ORE);
    private final List<Material> moreDropOreList = Arrays.asList(Material.LAPIS_ORE, Material.COAL_ORE, Material.DIAMOND_ORE, Material.EMERALD_ORE);
    private final List<Material> autoSmeltOreList = Arrays.asList(Material.IRON_ORE, Material.GOLD_ORE);


    @EventHandler(priority = EventPriority.HIGH)
    public void breakCropBlock(BlockBreakEvent e) {
        Player player = e.getPlayer();
        // 해당 유저가 서바이벌 상태인지를 먼저 판별해야 함.
        if (player.getGameMode() == GameMode.SURVIVAL) {
            // 현재 플레이어의 손에 들고 있는 ItemStack 에 섬세한 손길이 있는지 없는지를 판별
            if (!player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
                // 현재 유저가 파괴한 블럭이 작물 관련 블럭이며, 작물이 다 자란 상태인지를 판별
                Block cropBlock = e.getBlock();
                if (cropList.contains(cropBlock.getType()) && isFullyGrown(cropBlock)) {
                    // 해당 유저의 채집 전문기술 등급을 로드하고, 채집 기술 스킬을 발동시킴
                    int gatheringLevel = PlayerManager.getProfessionLevel(player, "Gathering");
                    int skillChance = ThreadLocalRandom.current().nextInt(10000);
                    // 전문 기술 등급이 80급 이상인 경우 [풍요로운 수확] 기술을 발동시킴
                    if (skillChance <= SkillCalculateManager.chanceCropsTriple(gatheringLevel)) {
                        e.setDropItems(false);
                        GatheringSkillManager.makeCropsTriple(player, cropBlock);
                    // 그렇지 않을 경우, [자연의 축복] 기술을 발동시킴
                    } else if (skillChance <= SkillCalculateManager.chanceCropsDouble(gatheringLevel)) {
                        e.setDropItems(false);
                        GatheringSkillManager.makeCropsDouble(player, cropBlock);
                    }
                }
            }
        }
    }

    // 작물이 다 자랐는지를 판별하는 메소드
    public static boolean isFullyGrown(Block block) {
        Ageable ageable = (Ageable) block.getBlockData();
        int maximumAge = ageable.getMaximumAge();
        return ageable.getAge() == maximumAge;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void breakOreBlock(BlockBreakEvent e) {
        Player player = e.getPlayer();
        // 해당 유저가 서바이벌 상태인지를 먼저 판별해야 함.
        if (player.getGameMode() == GameMode.SURVIVAL) {
            // 현재 플레이어의 손에 들고 있는 ItemStack 에 섬세한 손길이 있는지 없는지를 판별
            if (!player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
                // 현재 유저가 파괴한 블럭이 광물 관련 블럭인지를 판별
                Block mineBlock = e.getBlock();
                if (oreList.contains(mineBlock.getType())) {
                    // 해당 유저의 채광 전문기술 등급을 로드하고, 채광 관련 스킬을 발동시킴
                    int miningLevel = PlayerManager.getProfessionLevel(player, "Mining");
                    int skillChance = ThreadLocalRandom.current().nextInt(10000);
                    // 돌을 채광할 경우, 일정 확률로 [광물 감별사] 스킬을 발동시킴
                    if (mineBlock.getType().equals(Material.STONE)) {
                        if (skillChance <= SkillCalculateManager.chanceOtherMineral(miningLevel))
                            e.setDropItems(false);
                            MiningSkillManager.findOtherMineral(player, mineBlock.getLocation());
                    }
                    // 자원을 채광할 경우, 일정 확률로 [탐욕스러운 채굴] 혹은 [대지의 축복] 스킬을 발동시킴
                    if (miningLevel > 20 && moreDropOreList.contains(mineBlock.getType())) {
                        if (skillChance <= SkillCalculateManager.chanceMineralsTriple(miningLevel)) {
                            e.setDropItems(false);
                            MiningSkillManager.makeMineralsTriple(player, mineBlock);
                        } else if (skillChance <= SkillCalculateManager.chanceMineralsDouble(miningLevel)) {
                            e.setDropItems(false);
                            MiningSkillManager.makeMineralsDouble(player, mineBlock);
                        }
                    }
                    // 철광석 혹은 금광석을 채광할 경우, 일정 확률로 [지열 제련소] 스킬을 발동시킴
                    if (miningLevel > 50 && autoSmeltOreList.contains(mineBlock.getType())) {
                        if (skillChance <= SkillCalculateManager.chanceSmeltOre(miningLevel)) {
                            e.setDropItems(false);
                            MiningSkillManager.autoSmeltOre(player, mineBlock);
                        }
                    }
                }
            }
        }
    }
}
