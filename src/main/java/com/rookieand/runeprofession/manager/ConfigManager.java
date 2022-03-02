package com.rookieand.runeprofession.manager;

import com.rookieand.runeprofession.RuneProfession;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class ConfigManager {
    private final RuneProfession plugin;
    private FileConfiguration dataConfig = null;
    private File configFile = null;

    public ConfigManager(RuneProfession plugin) {
        this.plugin = plugin;
        // 초기 컨피그 파일을 저장하고 초기화하기 위한 장치.
        saveDefaultConfig();
    }

    public void reloadConfig() {
        // 파일이 없을 경우, 새로운 gui.yml 파일을 생성하여 이를 플러그인 폴더에 추가함.
        if (this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), "gui.yml");

        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultStream = this.plugin.getResource("gui.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults(defaultConfig);
            plugin.getLogger().log(Level.CONFIG, "성공적으로 해당 플러그인의 컨피그가 리로드 되었습니다 .");
        }
    }

    public FileConfiguration getConfig() {
        if (this.dataConfig == null)
            reloadConfig();
        return this.dataConfig;
    }

    public void saveConfig() {
        if (this.dataConfig == null || this.configFile == null)
            return;

        try {
            this.getConfig().save(this.configFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "현재 해당 플러그인의 컨피그를 저장할 수 없습니다." + this.configFile, e);
        }
    }

    public void saveDefaultConfig() {
        if (this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), "gui.yml");
        if (!this.configFile.exists()) {
            this.plugin.saveResource("gui.yml", false);
        }
    }
}
