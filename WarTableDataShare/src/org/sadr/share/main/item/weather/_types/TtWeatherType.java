package org.sadr.share.main.item.weather._types;

public enum TtWeatherType {

    Cloudy("ابری"),
    LowCloudey("کمی ابری"),
    Raining("بارانی"),
    LowRaining("کمی بارانی"),
    Sunny("آفتابی"),
    Thunder("طوفانی"),
    Wind("باد"),
    CloudSunny("ابری آفتابی"),
    Snow("برف");

    private String name;

    TtWeatherType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static TtWeatherType getByOrdinal(int o){
        for (TtWeatherType f:values()) {
            if(f.ordinal()==o){
                return  f;
            }
        }
        return null;
    }
}
