package mah.se.patterns.strategy;


import mah.se.mvc.model.Array7x7;

/**
 * Created by Sebastian Börebäck on 2015-12-14.
 */

/**
 * Hjälpklassen FillerAlgoritm interfacet
 * Med detta interface så kan man byta mellan vilken fyll metod man
 * vill använda
 */
public interface FillAlgorithm {

    /**
     * Fyller Array7x7 med en typ av nummer/färg/tecken
     * @param type tar ett nummer värde av nummer/färg/tecken
     * @return en fylld Array7x7
     */
    public Array7x7 fillWithOneType(int type);

    /**
     * Fyller Array7x7 med slumpat nummer/färg/tecken
     * @return en fylld Array7x7 med slumpade värden
     */
    public Array7x7 fillWithRandom();

    /**
     * Fyller Array7x7 med ökande från 1-7 nummer
     * eller en färg från röd till blå
     * @return Array7x7 med ökande värden
     */
    public Array7x7 fillWithInGaining();



}
