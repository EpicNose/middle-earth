package net.jukoz.me.item.utils;

import net.minecraft.util.StringIdentifiable;

public enum ModHoods implements StringIdentifiable {

    BASE_HOOD("base_hood"),
    FUR_HOOD("FUR_HOOD"),

    GALADHRIM_HOOD("galadhrim_hood"),
    ;

    private final String name;

    private ModHoods(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String asString() {
        return this.name;
    }
}
