package com.rookieand.runeprofession.manager;

import net.Indyuce.mmocore.api.player.PlayerData;
import org.bukkit.entity.Player;

public class PlayerManager {

    // 해당 유저의 특정 전문기술 레벨을 로드하는 메소드
    public static int getProfessionLevel(Player player, String Profession) {
        return PlayerData.get(player.getUniqueId()).getCollectionSkills().getLevel(Profession);
    }

    // 해당 유저에게 액션바로 메세지를 출력시키는 메소드 (MMOCore 호환 전용)
    public static void sendActionBar(Player player, String Message) {
        PlayerData.get(player.getUniqueId()).displayActionBar(Message);
    }
}