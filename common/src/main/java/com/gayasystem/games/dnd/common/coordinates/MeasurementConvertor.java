package com.gayasystem.games.dnd.common.coordinates;

import org.springframework.stereotype.Service;

@Service
public class MeasurementConvertor {
    public static final MeasurementConvertor c = new MeasurementConvertor();
    private final double FEET_IN_MILE = 5280;
    private final double INCH_IN_FOOT = 12;
    private final double FEET_IN_METER = 3.28084;
    private final double POUNDS_IN_KG = 2.20462;

    public double feet2Miles(double feet) {
        return feet / FEET_IN_MILE;
    }

    public double miles2Feet(double miles) {
        return miles * FEET_IN_MILE;
    }

    public double inches2Miles(double inches) {
        return inches / (FEET_IN_MILE * INCH_IN_FOOT);
    }

    public double miles2Inches(double miles) {
        return miles * FEET_IN_MILE * INCH_IN_FOOT;
    }

    public double inches2Feet(double inches) {
        return inches / INCH_IN_FOOT;
    }

    public double feet2Inches(double feet) {
        return feet * INCH_IN_FOOT;
    }

    public double feetInMeters(double feet) {
        return feet / FEET_IN_METER;
    }

    public double poundInKg(double pound) {
        return pound / POUNDS_IN_KG;
    }
}
