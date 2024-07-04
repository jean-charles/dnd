package com.gayasystem.games.dnd.lifeform;

import com.gayasystem.games.dnd.common.Direction;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.lifeform.brain.Brain;
import com.gayasystem.games.dnd.lifeform.brain.images.Image;
import com.gayasystem.games.dnd.lifeform.brain.memories.SpatialEngram;
import com.gayasystem.games.dnd.lifeform.brain.sounds.Sound;
import com.gayasystem.games.dnd.lifeform.brain.sounds.SoundSpectrum;

import java.util.Collection;

public class LifeForm implements Thing {
    private final Brain brain;

    private double sightDistance;
    private SoundSpectrum soundSpectrum;
    private double minSoundAmplitude;
    private Velocity velocity;
    private double mass;

    public LifeForm(double speed, Collection<Class<? extends Thing>> scaredBy, Collection<Class<? extends Thing>> attractedBy) {
        brain = new Brain(this, speed, attractedBy, scaredBy);
    }

    public void see(Image image, Direction origin) {
        brain.handle(new SpatialEngram(image, origin));
    }

    public void ear(Sound sound, Direction origin) {
        if (sound.spectrum() == soundSpectrum && sound.amplitude() >= minSoundAmplitude) {
            brain.handle(new SpatialEngram(sound, origin));
        }
    }

    public double sightDistance() {
        return sightDistance;
    }

    @Override
    public Velocity velocity() {
        return velocity;
    }

    @Override
    public void setDirection(Direction direction) {
        this.velocity = new Velocity(brain.speed(), direction);
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
