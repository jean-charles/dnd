package com.gayasystem.games.dnd.lifeform.brain.images;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeform.brain.memories.Engram;

public record Image(Thing thing) implements Engram {
}
