package com.rookieand.runeprofession.gui;

import com.rookieand.runeprofession.manager.SkillCalculateManager;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public class GeneratedIcon extends GeneratedInventory{

    private final DecimalFormat decformat = new DecimalFormat("#0.0#");
    private final DecimalFormat expformat = new DecimalFormat("###,###");
    private HashMap<String, Object> placeholder = new HashMap<>();

    public GeneratedIcon(Player player) {
        super(player);
    }

    private void initializePlaceholder(String profession) {
        int profLevel = this.playerProf.getLevel(profession);
        int currentExp = this.playerProf.getExperience(profession);
        int maxExp = this.playerProf.getLevelUpExperience(profession);

        this.placeholder.put("{level}", expformat.format(profLevel));
        this.placeholder.put("{current}", expformat.format(currentExp));
        this.placeholder.put("{max}", expformat.format(this.playerProf.getLevelUpExperience(profession)));
        this.placeholder.put("{percent}", decformat.format((double) (currentExp / maxExp) * 100.0));
    }

    // 해당 전문기술의 스킬 정보를 저장시키는 플레이스 홀더
    private void initializePlaceholder(String profession, String skill) {
        int profLevel = this.playerProf.getLevel(profession);
        Map<String, Integer> skillChance = SkillCalculateManager.getWholeSkillChance(profession, profLevel);

        this.placeholder.put("{level}", expformat.format(profLevel));
        this.placeholder.put("{percent}", decformat.format(skillChance.get(skill)));
    }

    protected ItemStack generateInfoIcon(String section, String itemPath) {
        ItemStack icon = new ItemStack(Material.valueOf(config.getString(itemPath + section + ".Material")), config.getInt("GUI.Items." + section + ".Amount"));
        ItemMeta iconMeta = icon.getItemMeta();

        // 해당 ItemStack 의 이름과 모델 데이터를 먼저 적용시킴.
        iconMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString(itemPath + section + ".Name")));
        iconMeta.setCustomModelData(config.getInt((itemPath) + section + ".ModelData"));

        ArrayList<String> iconLore = new ArrayList<>();
        // config 의 Lore 섹션에 작성된 List<String> 을 순회하여 색 코드를 변환시킴.
        for (String desc : config.getStringList(itemPath + section + ".Lore")) {
            iconLore.add(ChatColor.translateAlternateColorCodes('&', desc));
        }

        iconMeta.setLore(iconLore);
        icon.setItemMeta(iconMeta);

        return icon;
    }

    protected ItemStack generateProfInfo(String profession, String profPath) {
        int profLevel = this.playerProf.getLevel(profession);
        int currentExp = this.playerProf.getExperience(profession);
        int maxExp = this.playerProf.getLevelUpExperience(profession);
        String expPercent = decformat.format((double) (currentExp / maxExp) * 100.0);

        ItemStack icon = new ItemStack(Material.getMaterial(config.getString(profPath + profession + ".Material")), config.getInt(profPath + profession + ".Amount"));
        ItemMeta iconMeta = icon.getItemMeta();

        // 해당 ItemStack 의 이름과 모델 데이터를 먼저 적용시킴.
        iconMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString(profPath + profession + ".Name")));
        iconMeta.setCustomModelData(config.getInt((profPath) + profession + ".ModelData"));

        ArrayList<String> iconLore = new ArrayList<>();
        // config 의 Lore 섹션에 작성된 List<String> 을 순회하여 색 코드를 변환시킴.
        for (String desc : config.getStringList(profPath + profession + ".Lore")) {
            String newDesc = desc;
            newDesc = newDesc.replace("{level}", expformat.format(profLevel));
            newDesc = newDesc.replace("{current}", expformat.format(currentExp));
            newDesc = newDesc.replace("{max}", expformat.format(maxExp));
            newDesc = newDesc.replace("{percent}", expPercent);
            iconLore.add(ChatColor.translateAlternateColorCodes('&', newDesc));
        }

        iconMeta.setLore(iconLore);
        icon.setItemMeta(iconMeta);

        return icon;
    }

    protected ItemStack generateSkillInfo(String profession, String profPath) {
        int profLevel = this.playerProf.getLevel(profession);
        int currentExp = this.playerProf.getExperience(profession);
        int maxExp = this.playerProf.getLevelUpExperience(profession);
        String expPercent = decformat.format((double) (currentExp / maxExp) * 100.0);

        ItemStack icon = new ItemStack(Material.getMaterial(config.getString(profPath + profession + ".Material")), config.getInt(profPath + profession + ".Amount"));
        ItemMeta iconMeta = icon.getItemMeta();

        // 해당 ItemStack 의 이름과 모델 데이터를 먼저 적용시킴.
        iconMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString(profPath + profession + ".Name")));
        iconMeta.setCustomModelData(config.getInt((profPath) + profession + ".ModelData"));

        ArrayList<String> iconLore = new ArrayList<>();
        // config 의 Lore 섹션에 작성된 List<String> 을 순회하여 색 코드를 변환시킴.
        for (String desc : config.getStringList(profPath + profession + ".Lore")) {
            String newDesc = desc;
            newDesc = newDesc.replace("{level}", expformat.format(profLevel));
            newDesc = newDesc.replace("{current}", expformat.format(currentExp));
            newDesc = newDesc.replace("{max}", expformat.format(maxExp));
            newDesc = newDesc.replace("{percent}", expPercent);
            iconLore.add(ChatColor.translateAlternateColorCodes('&', newDesc));
        }

        iconMeta.setLore(iconLore);
        icon.setItemMeta(iconMeta);

        return icon;
    }

}
