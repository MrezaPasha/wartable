package org.sadr._core._type;

public enum TtFileType {

    Js(".js"),
    Css(".css"),
    Jsp(".jsp"),
    Html(".html"),
    Htm(".htm"),
    Json(".json"),
    Text(".txt"),
    Less(".less"),
    Scss(".scss"),
    Jpg(".jpg"),
    Png(".png"),
    Gif(".gif"),
    Font_Eot(".eot"),
    Font_Svg(".svg"),
    Font_Ttf(".ttf"),
    Font_Woff(".woff"),
    Font_Woff2(".woff"),
    Font_Otf(".otf"),;

    private final String key;

    private TtFileType(String k) {
        key = k;
    }

    public String getKey() {
        return key;
    }

}
