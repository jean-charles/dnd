package com.gayasystem.games.dnd.lifeform;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.lifeform.brain.Brain;
import com.gayasystem.games.dnd.lifeform.brain.images.Image;
import com.gayasystem.games.dnd.lifeform.brain.sounds.Sound;
import com.gayasystem.games.dnd.lifeform.brain.sounds.SoundSpectrum;

public class LifeForm implements Thing, Runnable {
    private final Brain brain = new Brain();

    private double sightDistance;
    private SoundSpectrum soundSpectrum;
    private double minSoundAmplitude;
    private double speed;
    private Velocity velocity;
    private double mass;

    public LifeForm(double speed) {
        this.speed = speed;
    }

    public void see(Image image) {
        brain.handle(image);
    }

    public void ear(Sound sound) {
        if (sound.spectrum() == soundSpectrum && sound.amplitude() >= minSoundAmplitude) {
            brain.handle(sound);
        }
    }

    public double sightDistance() {
        return sightDistance;
    }

    public Velocity velocity() {
        return velocity;
    }

    @Override
    public double mass() {
        return mass;
    }

    @Override
    public void run() {
        brain.run();
    }
}
