package org.sadr.web.main._core.ui;

import org.sadr.web.main._core._type.TtUiFonts;
import org.sadr.web.main._core._type.TtUiStyle;

public class UiBag {

    public UiBag() {
    }

    private TtUiFonts font;
    private TtUiStyle style;


    public TtUiFonts getFont() {
        return font;
    }

    public void setFont(TtUiFonts font) {
        this.font = font;
    }

    public TtUiStyle getStyle() {
        return style;
    }

    public void setStyle(TtUiStyle style) {
        this.style = style;
    }
}
