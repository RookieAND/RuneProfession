package com.rookieand.runeprofession.listener;

import com.rookieand.runeprofession.RuneProfession;
import com.rookieand.runeprofession.gui.GeneratedInventory;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class InventoryListener implements Listener {

    private ConfigurationSection config;
    private String invTitles;
    private HashMap<Integer, String> profList;

    public InventoryListener() {
        this.config = RuneProfession.plugin.configManager.getConfig();
        this.invTitles = config.getString("GUI.Titles");
        String itemPath = "GUI.Professions";
        // config 의 Professions 항목에 저장된 각 슬롯 별로 지정된 전문기술 목록을 HashMap으로 불러옴
        for (String section : (config.getConfigurationSection(itemPath)).getKeys(false)) {
            // Integer[] 으로 되어 있는 slot 의 내부 index 를 파악하여 아이템을 적용시킴.
            for (int slot : config.getIntegerList(itemPath + section + ".Slot")) {
                profList.put(slot, section);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        // 현재 유저가 클릭한 인벤토리가 전문기술 안내 GUI인지를 먼저 판별.
        if (e.getView().getTitle().equalsIgnoreCase(invTitles)) {
            // 먼저 이벤트를 캔슬시키고, 클릭한 아이템의 슬롯이 전문기술 아이콘인지를 판별함.
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
            if (profList.containsKey(e.getSlot())) {
                //GeneratedInventory.showSkillsList(profList.get(e.getSlot()));
            }
        }
    }

    // 전문기술 GUI 에서 특정 아이템을 드래그하는 이벤트는 전부 캔슬시킴.
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase(invTitles)) {
            e.setCancelled(true);
        }
    }
}
