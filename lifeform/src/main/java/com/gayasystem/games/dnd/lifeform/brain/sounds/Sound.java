package com.gayasystem.games.dnd.lifeform.brain.sounds;

import com.gayasystem.games.dnd.lifeform.brain.memories.Engram;

public record Sound(SoundSpectrum spectrum, double amplitude) implements Engram {
}
