package com.gayasystem.games.dnd.lifeform;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.lifeform.brain.Brain;
import com.gayasystem.games.dnd.lifeform.brain.Moveable;
import com.gayasystem.games.dnd.lifeform.brain.images.Image;
import com.gayasystem.games.dnd.lifeform.brain.sounds.Sound;
import com.gayasystem.games.dnd.lifeform.brain.sounds.SoundSpectrum;

import java.util.Collection;

public class LifeForm implements Moveable, Thing, Runnable {
    private final Brain brain;

    private double sightDistance;
    private SoundSpectrum soundSpectrum;
    private double minSoundAmplitude;
    private double speed;
    private Velocity velocity;
    private double mass;

    public LifeForm(double speed, Collection<Class<? extends Thing>> scaredBy, Collection<Class<? extends Thing>> attractedBy) {
        brain = new Brain(this, attractedBy, scaredBy);
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
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
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
