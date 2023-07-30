package com.example.testdemo;

public enum LuckSSQEnums {


    SSQ_LEVEL_ONE("61", "N0", "一等奖"),
    SSQ_LEVEL_TWO("60", "N1", "二等奖"),
    SSQ_LEVEL_THREE("51", "3000", "三等奖"),
    SSQ_LEVEL_FOUR1("41", "200", "四等奖"),
    SSQ_LEVEL_FOUR2("50", "200", "四等奖"),
    SSQ_LEVEL_FIVE1("40", "10", "五等奖"),
    SSQ_LEVEL_FIVE2("31", "10", "五等奖"),
    SSQ_LEVEL_SIX1("21", "5", "六等奖"),
    SSQ_LEVEL_SIX2("11", "5", "六等奖"),
    SSQ_LEVEL_SIX3("01", "5", "六等奖"),
    ;

    private String level;
    private String money;
    private String rmk;

    LuckSSQEnums(String level, String money, String rmk) {
        this.level = level;
        this.money = money;
        this.rmk = rmk;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRmk() {
        return rmk;
    }

    public void setRmk(String rmk) {
        this.rmk = rmk;
    }

    public static LuckSSQEnums getByLevel(String level){

        for (LuckSSQEnums value : LuckSSQEnums.values()) {
            if(level.equalsIgnoreCase(value.level)) {
                return value;
            }
        }

        return null;
    }
}
