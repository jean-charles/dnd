package com.gayasystem.games.dnd.characters.scores;

public record Skills(
        // Strength
        int athletics,
        // Dexterity
        int acrobatics,
        int sleightOfHand,
        int stealth,
        // Intelligence
        int arcana,
        int history,
        int investigation,
        int nature,
        int religion,
        // Wisdom
        int animalHandling,
        int insight,
        int medicine,
        int perception,
        int survival,
        // Charisma
        int deception,
        int intimidation,
        int performance,
        int persuasion) {
}
