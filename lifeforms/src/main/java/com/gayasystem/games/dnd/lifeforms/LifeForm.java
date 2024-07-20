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
    private final double speed;
    private final double sightDistance;
    private final SoundSpectrum soundSpectrum;
    private final double minSoundAmplitude;
    private final Collection<Class<? extends Thing>> scaredBy;
    private final Collection<Class<? extends Thing>> attractedBy;

    private Brain brain;

    @Autowired
    private BrainFactory brainFactory;

    public LifeForm(double mass, double speed, double sightDistance, SoundSpectrum soundSpectrum, double minSoundAmplitude, Collection<Class<? extends Thing>> scaredBy, Collection<Class<? extends Thing>> attractedBy) {
        super(mass);
        this.speed = speed;
        this.sightDistance = sightDistance;
        this.soundSpectrum = soundSpectrum;
        this.minSoundAmplitude = minSoundAmplitude;
        this.scaredBy = scaredBy;
        this.attractedBy = attractedBy;
    }

    @Override
    public void run() {
        if (brain == null)
            brain = brainFactory.create(this, speed, attractedBy, scaredBy);
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
