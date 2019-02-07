package de.ralfhergert.dota2.autochess.modifier;

public interface ChanceOfBeingHitModifier {

    /**
     * @param chanceOfBeingHit 0 is 0% and 1.0 is 100%
     */
    double modChanceOfBeingHit(double chanceOfBeingHit);
}
