package com.rookieand.runeprofession.gui;

import com.rookieand.runeprofession.RuneProfession;
import net.Indyuce.mmocore.api.player.PlayerData;
import net.Indyuce.mmocore.experience.PlayerProfessions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

@SuppressWarnings("deprecation")
public class GeneratedInventory {

    protected final ConfigurationSection config;
    protected final Inventory infoInv;
    protected final PlayerProfessions playerProf;
    private final GeneratedIcon genIcon;

    public GeneratedInventory(Player player) {
        this.config = RuneProfession.plugin.configManager.getConfig();
        this.infoInv = Bukkit.createInventory(null, 45, ChatColor.translateAlternateColorCodes('&', config.getString("GUI.Titles")));
        this.playerProf = PlayerData.get(player.getUniqueId()).getCollectionSkills();
        this.genIcon = new GeneratedIcon(player);
        createDefaultFrame();
        createInfoIcon();
        createProfIcon();
    }

    // 인벤토리를 새롭게 생성시키는 메소드
    private void createInfoIcon() {
        // config 의 Item Section Path 를 지칭하는 String 을 변수로 저장.
        String itemPath = "GUI.Items.";
        // config 에 작성된 항목을 리딩하여, 하단 child 에 기술된 ItemStack 의 정보를 읽어 적용시킴.
        for (String section : (config.getConfigurationSection(itemPath)).getKeys(false)) {
            // Integer[] 으로 되어 있는 slot 의 내부 index 를 파악하여 아이템을 적용시킴.
            for (int slot : config.getIntegerList(itemPath + section + ".Slot")) {
                infoInv.setItem(slot, genIcon.generateInfoIcon(section, itemPath));
            }
        }
    }

    // 인벤토리를 새롭게 생성시키는 메소드
    private void createProfIcon() {
        // config 의 Item Section Path 를 지칭하는 String 을 변수로 저장.
        String itemPath = "GUI.Professions.";
        // config 에 작성된 항목을 리딩하여, 하단 child 에 기술된 ItemStack 의 정보를 읽어 적용시킴.
        for (String section : (config.getConfigurationSection(itemPath)).getKeys(false)) {
            // Integer[] 으로 되어 있는 slot 의 내부 index 를 파악하여 아이템을 적용시킴.
            for (int slot : config.getIntegerList(itemPath + section + ".Slot")) {
                infoInv.setItem(slot, genIcon.generateProfInfo(section, itemPath));
            }
        }
    }

    // 각 전문기술의 타입에 따른 스킬을 나열시킴.
    private void createSkillsList(String profession) {
        // config 의 Item Section Path 를 지칭하는 String 을 변수로 저장.
        String itemPath = "GUI.Skills.";
        // config 에 작성된 항목을 리딩하여, 하단 child 에 기술된 ItemStack 의 정보를 읽어 적용시킴.
        for (String section : (config.getConfigurationSection(itemPath)).getKeys(false)) {
            // Integer[] 으로 되어 있는 slot 의 내부 index 를 파악하여 아이템을 적용시킴.
            for (int slot : config.getIntegerList(itemPath + section + ".Slot")) {
                infoInv.setItem(slot, genIcon.generateSkillInfo(section, itemPath));
            }
        }
    }

    // 인벤토리의 기본 GUI 틀을 미리 생성시키는 메소드
    private void createDefaultFrame() {
        ItemStack[] blackFrame = new ItemStack[45];
        ItemStack whiteFrame = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);

        Arrays.fill(blackFrame, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        infoInv.setContents(blackFrame);
        for (int i = 0 ; i < 7 ; i++) {
            infoInv.setItem(10 + i, whiteFrame);
            infoInv.setItem(19 + i, whiteFrame);
            infoInv.setItem(28 + i, whiteFrame);
        }
    }

    public void openInfoInventory(HumanEntity player) {
        player.openInventory(infoInv);
    }

    public Inventory getInfoInventory() {
        return this.infoInv;
    }
}
