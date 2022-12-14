package mysteryDungeon.pokemons;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;

import mysteryDungeon.characters.Pokemon;

public class Feraligatr extends AbstractPokemon {

    public static final Color COLOR = Color.BLUE.cpy();
    public static final CardColor CARD_COLOR = Pokemon.Enums.TOTODILE_BLUE;

    public Feraligatr(){
        super(COLOR, CARD_COLOR);
    }
}
