package com.gayasystem.games.dnd.lifeforms;

import com.gayasystem.games.dnd.common.SphericalCoordinate;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.brain.Brain;
import com.gayasystem.games.dnd.lifeforms.brain.BrainFactory;
import com.gayasystem.games.dnd.lifeforms.brain.images.Image;
import com.gayasystem.games.dnd.lifeforms.brain.memories.SpatialEngram;
import com.gayasystem.games.dnd.lifeforms.brain.sounds.Sound;
import com.gayasystem.games.dnd.lifeforms.brain.sounds.SoundSpectrum;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public abstract class LifeForm extends Thing {
    private final Brain brain;
    private final double speed;

    private double sightDistance;
    private SoundSpectrum soundSpectrum;
    private double minSoundAmplitude;

    @Autowired
    private BrainFactory brainFactory;

    public LifeForm(double mass, double speed, Collection<Class<? extends Thing>> scaredBy, Collection<Class<? extends Thing>> attractedBy) {
        super(mass);
        brain = brainFactory.create(this, speed, attractedBy, scaredBy);
        this.speed = speed;
    }

    @Override
    public void run() {
        brain.run();
    }

    public void see(Image image, SphericalCoordinate origin) {
        brain.handle(new SpatialEngram(image, origin));
    }

    public void ear(Sound sound, SphericalCoordinate origin) {
        if (sound.spectrum() == soundSpectrum && sound.amplitude() >= minSoundAmplitude) {
            brain.handle(new SpatialEngram(sound, origin));
        }
    }

    public double sightDistance() {
        return sightDistance;
    }
}
