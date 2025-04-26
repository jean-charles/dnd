package com.gayasystem.games.dnd.lifeforms.organs;

import com.gayasystem.games.dnd.lifeforms.sensitive.Pain;
import com.gayasystem.games.dnd.lifeforms.sensitive.Pressure;
import com.gayasystem.games.dnd.lifeforms.sensitive.Thermoception;
import com.gayasystem.games.dnd.lifeforms.sensitive.Touch;

public class Skin implements Organ, Pain, Pressure, Touch, Thermoception {
    @Override
    public void press(double pressure) {
    }
}