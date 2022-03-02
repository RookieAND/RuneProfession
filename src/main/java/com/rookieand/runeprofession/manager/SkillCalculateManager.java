package com.rookieand.runeprofession.manager;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class SkillCalculateManager {

    public static Map<String, Integer> getWholeSkillChance(@NotNull String profession, int level) {
        Map<String, Integer> chanceMap = new HashMap<String, Integer>();
        switch (profession) {
            case "gathering":
                chanceMap.put("chanceCropDouble", chanceCropsDouble(level));
                chanceMap.put("chanceCropsTriple", chanceCropsTriple(level));
            case "mining":
                chanceMap.put("chanceOtherMineral", chanceOtherMineral(level));
                chanceMap.put("chanceMineralsDouble", chanceMineralsDouble(level));
                chanceMap.put("chanceMineralsTriple", chanceMineralsTriple(level));
        }
        return chanceMap;
    }

    // [채집] 전문기술의 [대지의 축복] 스킬 발동 여부를 리턴하는 메소드
    public static int chanceCropsDouble(int level) {
        int finalChance = (int) Math.pow(level, 2) * (6 / 25) + 600	;
        return finalChance;
    }

    // [채집] 전문기술의 [풍요로운 수확] 스킬 발동 여부를 리턴하는 메소드
    public static int chanceCropsTriple(int level) {
        if (level < 80) { return 0; }
        int finalChance = (int) Math.pow((level - 80), 2) * (7 / 4) + 300;
        return finalChance;
    }

    // [채광] 전문기술의 [광물 감별사] 스킬 발동 여부를 리턴하는 메소드
    public static int chanceOtherMineral(int level) {
        int finalChance = (int) Math.pow(level, 2) * (7 / 25) + 500;
        return finalChance;
    }

    // [채광] 전문기술의 [대지의 축복] 스킬 발동 여부를 리턴하는 메소드
    public static int chanceMineralsDouble(int level) {
        if (level < 20) { return 0; }
        int finalChance = (int) Math.pow((level - 20), 2) * (3 / 8) + 600;
        return finalChance;
    }

    // [채광] 전문기술의 [지열 제련소] 스킬 발동 여부를 리턴하는 메소드
    public static int chanceSmeltOre(int level) {
        if (level < 50) { return 0; }
        int finalChance = (int) Math.pow((level - 50), 2) * (8 / 5) + 1000;
        return finalChance;
    }

    // [채광] 전문기술의 [탐욕스러운 채굴] 스킬 발동 여부를 리턴하는 메소드
    public static int chanceMineralsTriple(int level) {
        if (level < 80) { return 0; }
        int finalChance = (int) Math.pow((level - 80), 2) * (3 / 4) + 200;
        return finalChance;
    }

}
