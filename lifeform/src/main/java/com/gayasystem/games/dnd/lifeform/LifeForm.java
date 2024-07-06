package com.gayasystem.games.dnd.lifeform;

import com.gayasystem.games.dnd.common.SpericalCoordinate;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeform.brain.Brain;
import com.gayasystem.games.dnd.lifeform.brain.images.Image;
import com.gayasystem.games.dnd.lifeform.brain.memories.SpatialEngram;
import com.gayasystem.games.dnd.lifeform.brain.sounds.Sound;
import com.gayasystem.games.dnd.lifeform.brain.sounds.SoundSpectrum;

import java.util.Collection;

public class LifeForm extends Thing {
    private final Brain brain;

    private double sightDistance;
    private SoundSpectrum soundSpectrum;
    private double minSoundAmplitude;

    public LifeForm(double mass, double speed, Collection<Class<? extends Thing>> scaredBy, Collection<Class<? extends Thing>> attractedBy) {
        super(mass);
        brain = new Brain(this, speed, attractedBy, scaredBy);
    }

    public void see(Image image, SpericalCoordinate origin) {
        brain.handle(new SpatialEngram(image, origin));
    }

    public void ear(Sound sound, SpericalCoordinate origin) {
        if (sound.spectrum() == soundSpectrum && sound.amplitude() >= minSoundAmplitude) {
            brain.handle(new SpatialEngram(sound, origin));
        }
    }

    public double sightDistance() {
        return sightDistance;
    }

    @Override
    public void run() {
        brain.run();
    }
}
