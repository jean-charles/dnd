package com.gayasystem.games.dnd.gametools.scores;

public record AbilityScores(
        Ability strength,
        Ability dexterity,
        Ability constitution,
        Ability intelligence,
        Ability wisdom,
        Ability charisma) {
    public AbilityScores() {
        this(new Ability(), new Ability(), new Ability(), new Ability(), new Ability(), new Ability());
    }
}
