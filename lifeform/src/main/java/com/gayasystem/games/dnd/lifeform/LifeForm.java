package com.gayasystem.games.dnd.lifeform;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeform.brain.Brain;
import com.gayasystem.games.dnd.lifeform.brain.Image;
import com.gayasystem.games.dnd.lifeform.brain.Sound;
import com.gayasystem.games.dnd.lifeform.brain.SoundSpectrum;

public class LifeForm implements Thing {
    private Brain brain;
    private double mass;
    private double sightDistance;
    private SoundSpectrum soundSpectrum;
    private double minSoundAmplitude;

    void see(Image image) {
        brain.handle(image);
    }

    void ear(Sound sound) {
        if (sound.spectrum() == soundSpectrum && sound.amplitude() >= minSoundAmplitude) {
            brain.handle(sound);
        }
    }

    double sightDistance() {
        return sightDistance;
    }

    @Override
    public double mass() {
        return mass;
    }
}
