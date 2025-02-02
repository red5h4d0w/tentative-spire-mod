package mysteryDungeon.potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import mysteryDungeon.abstracts.PokemonPotion;
import mysteryDungeon.actions.XSpAttackAction;
import mysteryDungeon.pokemons.Pikachu;


public class XSpAttackPotion extends PokemonPotion {

    public static final String POTION_ID = mysteryDungeon.MysteryDungeon.makeID(XSpAttackPotion.class);
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public static final String IMG_STRING = "x-sp-atk.png";

    public XSpAttackPotion() {
        // The bottle shape and inside is determined by potion size and color. The actual colors are the main MysteryDungeon.java
        super(NAME, POTION_ID, PotionRarity.RARE, IMG_STRING);
        
        // Potency is the damage/magic number equivalent of potions.
        potency = getPotency();
        
       // Do you throw this potion at an enemy or do you just consume it.
        isThrown = false;

        cardColor = Pikachu.CARD_COLOR;

        updateDescription();
        
        // Initialize the on-hover name + description
        tips.add(new PowerTip(name, description));
        
    }
    // See that description? It has DESCRIPTIONS[1] instead of just hard-coding the "text " + potency + " more text" inside.
    // DO NOT HARDCODE YOUR STRINGS ANYWHERE, it's really bad practice to have "Strings" in your code:

    /*
     * 1. It's bad for if somebody likes your mod enough (or if you decide) to translate it.
     * Having only the JSON files for translation rather than 15 different instances of "Dexterity" in some random cards is A LOT easier.
     *
     * 2. You don't have a centralised file for all strings for easy proof-reading. If you ever want to change a string
     * you don't have to go through all your files individually/pray that a mass-replace doesn't screw something up.
     *
     * 3. Without hardcoded strings, editing a string doesn't require a compile, saving you time (unless you clean+package).
     *
     */

    @Override
    public void use(AbstractCreature target) {
        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT)
          addToBot(new XSpAttackAction(this.potency)); 
      }
    
    @Override
    public AbstractPotion makeCopy() {
        return new XSpAttackPotion();
    }

    // This is your potency.
    @Override
    public int getPotency(final int potency) {
        return 1;
    }

    public void upgradePotion()
    {
        potency += 1;
        tips.clear();
        tips.add(new PowerTip(name, description));
    }

    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], potency);
    }
}
