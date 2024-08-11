package com.gayasystem.games.dnd.ecosystem.classes;

import com.gayasystem.games.dnd.ecosystem.armors.Armor;
import com.gayasystem.games.dnd.ecosystem.weapons.Weapon;
import com.gayasystem.games.dnd.gametools.dices.Die;
import com.gayasystem.games.dnd.gametools.scores.AbilityScores;

import java.util.Collection;

public abstract class Class {
    private Die hitDie;
    private AbilityScores primaryAbility;
    private AbilityScores savingThrowProficiencies;
    private Collection<Armor> armorProficiencies;
    private Collection<Weapon> weaponProficiencies;

    public Class(Die hitDie, AbilityScores primaryAbility, AbilityScores savingThrowProficiencies, Collection<Armor> armorProficiencies, Collection<Weapon> weaponProficiencies) {
        this.hitDie = hitDie;
        this.primaryAbility = primaryAbility;
        this.savingThrowProficiencies = savingThrowProficiencies;
        this.armorProficiencies = armorProficiencies;
        this.weaponProficiencies = weaponProficiencies;
    }
}
