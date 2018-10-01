package org.sadr.web.main._core.tools._type;

/**
 * @author masoud
 */
public enum TtThumbnailSize {

    //1
    _5X5(5, 5),
    _10X10(10, 10),
    _15X15(15, 15),
    _20X20(20, 20),
    _25X25(25, 25),
    _30X30(30, 30),
    _35X35(35, 35),
    _40X40(40, 40),
    _45X45(45, 45),
    _50X50(50, 50),
    _60X60(60, 60),
    _70X70(70, 70),
    _75X50(75, 50),
    _80X80(80, 80),
    _90X90(90, 90),
    _100X100(100, 100),
    _150X150(150, 150),
    _200X200(200, 200),
    _250X250(250, 250),
    _500X500(500, 500),
    //3x2
    _600X400(600, 400),
    _150X100(150, 100),
    //3x4
    _30X40(30, 40),
    _60X80(60, 80),
    _90X120(90, 120),
    _120X160(120, 160),
    _150X200(150, 200),
    _120X40(120, 40),
    //4x3
    _40X30(40, 30),
    _200X150(200, 150),
    _600X450(600, 450),
    //5x2
    _500X200(500, 200),
    _1000X400(1000, 400),
    //5x7
    _500X700(500, 700),
    //7x5
    _350X250(350, 250),
    _700X500(700, 500),
    //2
    _100X50(100, 50),
    _1000X500(1000, 500),
    //3
    _150X50(150, 50),
    _900X300(1500, 500),
    _1500X500(1500, 500),
    _2400X800(2400, 800),
    _3000X1000(3000, 1000),
    _3600X1200(3600, 1200),;
    private final int width;
    private final int height;

    private TtThumbnailSize(int w, int h) {
        width = w;
        height = h;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getRatio_HrW() {
        return (float) height / width;
    }

    public String getDirectoryName() {
        return ".thum_" + width + "X" + height;
    }

}
