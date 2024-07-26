package com.gayasystem.games.dnd.lifeforms;

import com.gayasystem.games.dnd.common.LifeEnvironment;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.coordinates.Orientation;
import com.gayasystem.games.dnd.common.coordinates.SphericalCoordinate;
import com.gayasystem.games.dnd.common.hear.Hearing;
import com.gayasystem.games.dnd.common.hear.SoundSpectrum;
import com.gayasystem.games.dnd.common.sight.Sighted;
import com.gayasystem.games.dnd.lifeforms.brain.Brain;
import com.gayasystem.games.dnd.lifeforms.brain.BrainFactory;
import com.gayasystem.games.dnd.lifeforms.brain.images.Image;
import com.gayasystem.games.dnd.lifeforms.brain.memories.SpatialEngram;
import com.gayasystem.games.dnd.lifeforms.brain.sounds.Sound;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public abstract class LifeForm extends Thing implements Sighted, Hearing {
    private final Gender gender;
    private final double speed;
    private final double sightDistance;
    private final SoundSpectrum soundSpectrum;
    private final double minSoundAmplitude;
    private final Collection<Class<? extends Thing>> attractedBy;
    private final Collection<Class<? extends Thing>> scaredBy;

    private Brain brain;

    @Autowired
    private LifeEnvironment environment;

    @Autowired
    private BrainFactory brainFactory;

    public LifeForm(double mass, Gender gender, double speed, double sightDistance, SoundSpectrum soundSpectrum, double minSoundAmplitude, Collection<Class<? extends Thing>> attractedBy, Collection<Class<? extends Thing>> scaredBy) {
        super(mass);
        this.gender = gender;
        this.speed = speed;
        this.sightDistance = sightDistance;
        this.soundSpectrum = soundSpectrum;
        this.minSoundAmplitude = minSoundAmplitude;
        this.attractedBy = attractedBy;
        this.scaredBy = scaredBy;
    }

    @Override
    public void run() {
        if (brain == null)
            brain = brainFactory.create(this, speed, attractedBy, scaredBy);
        environment.show(this, sightDistance);
        environment.listen(this, minSoundAmplitude);
        brain.run();
    }

    @Override
    public void see(Thing thing, SphericalCoordinate origin, Orientation orientation) {
        Image image = new Image(thing.getClass());
        brain.handle(new SpatialEngram(image, origin));
    }

    @Override
    public void ear(Thing thing, SoundSpectrum spectrum, double amplitude, SphericalCoordinate origin) {
        var sound = new Sound(thing.getClass(), spectrum, amplitude);
        if (sound.spectrum().equals(soundSpectrum) && sound.amplitude() >= minSoundAmplitude) {
            brain.handle(new SpatialEngram(sound, origin));
        }
    }
}
