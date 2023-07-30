package com.example.testdemo;

public enum LuckEnums {

    DLT_LEVEL_ONE("5+2", "N0", "一等奖"),
    DLT_LEVEL_TWO("5+1", "N1", "二等奖"),
    DLT_LEVEL_THREE("5+0", "10000", "三等奖"),
    DLT_LEVEL_FOUR("4+2", "3000", "四等奖"),
    DLT_LEVEL_FIVE("4+1", "300", "五等奖"),
    DLT_LEVEL_SIX("3+2", "200", "六等奖"),
    DLT_LEVEL_SEVEN("4+0", "100", "七等奖"),
    DLT_LEVEL_EIGHT1("2+2", "15", "八等奖"),
    DLT_LEVEL_EIGHT2("3+1", "15", "八等奖"),
    DLT_LEVEL_NINE1("1+2", "5", "九等奖"),
    DLT_LEVEL_NINE2("1+2", "5", "九等奖"),
    DLT_LEVEL_NINE3("3+0", "5", "九等奖"),
    DLT_LEVEL_NINE4("2+1", "5", "九等奖"),


    SSQ_LEVEL_ONE("6+1", "N0", "一等奖"),
    SSQ_LEVEL_TWO("6+0", "N1", "二等奖"),
    SSQ_LEVEL_THREE("5+1", "3000", "三等奖"),
    SSQ_LEVEL_FOUR1("4+1", "200", "四等奖"),
    SSQ_LEVEL_FOUR2("5+0", "200", "四等奖"),
    SSQ_LEVEL_FIVE1("4+0", "10", "五等奖"),
    SSQ_LEVEL_FIVE2("3+1", "10", "五等奖"),
    SSQ_LEVEL_SIX1("2+1", "5", "六等奖"),
    SSQ_LEVEL_SIX2("1+1", "5", "六等奖"),
    SSQ_LEVEL_SIX3("0+1", "5", "六等奖"),
    ;

    private String level;
    private String money;
    private String rmk;

    LuckEnums(String level, String money, String rmk) {
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

    public static LuckEnums getByLevel(int flag, String level){

        for (LuckEnums value : LuckEnums.values()) {

            if(flag == 0 && !value.name().contains("DLT_LEVEL")) {
                continue;
            }

            if(flag == 1 && !value.name().contains("SSQ_LEVEL")) {
                continue;
            }

            if(level.equalsIgnoreCase(value.level)) {
                return value;
            }
        }

        return null;
    }
}
