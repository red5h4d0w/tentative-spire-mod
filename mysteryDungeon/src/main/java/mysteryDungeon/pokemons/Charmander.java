package mysteryDungeon.pokemons;

import static mysteryDungeon.MysteryDungeon.makeBackSpritePath;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;

import mysteryDungeon.cards.Charmander.CharmanderDefend;
import mysteryDungeon.cards.Charmander.CharmanderEmber;
import mysteryDungeon.cards.Charmander.CharmanderScratch;
import mysteryDungeon.characters.Pokemon;

public class Charmander extends AbstractPokemon {
    
    public static String NAME = "Charmander";
    public static int MAX_HP = 30;
    public static int ORB_SLOTS = 1;
    public static AbstractCard[] STARTING_DECK = new AbstractCard[]{new CharmanderScratch(), new CharmanderScratch(), new CharmanderDefend(), new CharmanderDefend(), new CharmanderEmber()};
    public static Color COLOR = Color.RED;
    public static CardColor CARD_COLOR = Pokemon.Enums.CHARMANDER_RED;
    public static String PATH_TO_BACK_SPRITE = makeBackSpritePath(Charmander.class.getSimpleName()+".png");

    public Charmander(){
        super(NAME, MAX_HP, ORB_SLOTS, STARTING_DECK, COLOR, CARD_COLOR, PATH_TO_BACK_SPRITE);
    }
}
