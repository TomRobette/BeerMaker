package com.example.beermaker;

import android.view.View;

public class Recette {
    private int id;
    public int volAlc;
    public int degAlc;
    public double ebc;
    public double malt;
    public double eauBra;
    public double eauRin;
    public double houblonAm;
    public double houblonAr;
    public double levure;
    public String color;
    public double mcu;
    public double srm;

    public Recette(int id){}

    public Recette(int id, int volAlc, int degAlc, double ebc, double malt, double eauBra, double eauRin, double houblonAm, double houblonAr, double levure, String color, double mcu, double srm) {
        this.id = id;
        this.volAlc = volAlc;
        this.degAlc = degAlc;
        this.ebc = ebc;
        this.malt = malt;
        this.eauBra = eauBra;
        this.eauRin = eauRin;
        this.houblonAm = houblonAm;
        this.houblonAr = houblonAr;
        this.levure = levure;
        this.color = color;
        this.mcu = mcu;
        this.srm = srm;
    }

    public void calculUnites(){
        malt = (volAlc*degAlc)/20;
        eauBra = malt*2.8;
        eauRin = (volAlc*1.25)-(eauBra*0.7);
        mcu = (4.23*(ebc*malt))/volAlc;
        ebc = 2.9396 * Math.pow(mcu, 0.6859);
        srm = 0.508*ebc;
        color = srmToRGB(srm);
        houblonAm = volAlc*3;
        houblonAr = volAlc;
        levure = volAlc/2;

    }


    private String srmToRGB(double srm) {
// Returns an RGB value based on SRM
        Double r, g, b;
        r= g= b= (double) 0;
        if (srm>=0 && srm<=1) {
            r = (double) 240; g = (double) 239; b = (double) 181;
        } else if (srm>1 && srm<=2) {
            r = (double) 233; g = (double) 215; b = (double) 108;
        } else if (srm>2) {
// Set red decimal
            if (srm<70.6843) { r = 243.8327-(6.4040*srm)+(0.0453*srm*srm);
            } else { r = 17.5014; }
// Set green decimal
            if (srm<35.0674) { g = 230.929-12.484*srm+0.178*srm*srm;
            } else { g = 12.0382; }
// Set blue decimal
            if (srm<4) { b = (double) -54*srm+216;
            } else if (srm>=4 && srm<7) { b = (double) 0;
            } else if (srm>=7 && srm<9) { b = (double) 13*srm-91;
            } else if (srm>=9 && srm<13) { b = (double) 2*srm+8;
            } else if (srm>=13 && srm<17) { b = -1.5*srm+53.5;
            } else if (srm>=17 && srm<22) { b = 0.6*srm+17.8;
            } else if (srm>=22 && srm<27) { b = -2.2*srm+79.4;
            } else if (srm>=27 && srm<34) { b = -0.4285*srm + 31.5714;
            } else { b = (double) 17; }
        }
        Integer red = r.intValue(); Integer green = g.intValue(); Integer blue = b.intValue();
        String rr = red.toHexString(red);
        String gg = green.toHexString(green);
        String bb = blue.toHexString(blue);
        String rgb = "#";
        if (rr.length()<2){ rr="0"+rr;
        }else if (gg.length()<2){ gg="0"+gg;
        }else if (bb.length()<2){ bb="0"+bb; }
        rgb = rgb+rr+gg+bb;
        return rgb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
