package testing;

import mah.se.mvc.controller.Controller;
import mah.se.mvc.model.Array7;

/**
 * Created by seb on 2015-12-18.
 */
public interface controllerImpl {

        /**
         * Shifta Array7x7 och fyller på med
         * en rad med röda rutor
         * @return det som ramlar över
         */
        public Array7 shiftWithRedColor();


        /**
         * Sätter vilket håll vi ska shifta
         * @param dir riktigt vi shiftar
         */
        public void setDirection(Controller.DIRECTION dir);

         /**
         * btn click
         * Visa slumpade färger i vyn
         */
        public void showRandomColor();

        /**
         * Visa en färg i på hela Array7x7 i vyn
         */
        public void showSameColor(int color);

        /**
         * Visar en graident färg mellan 2 färger
         * i vyn
         */
        public void showGradiantColor();

        /**
         * Visar en slumpad bokstav i vyn
         */
        public void showRanomChar();


        /**
         * Btn click
         * Visar rinande text på texy som kommer in från vyn
         * @param texy
         */
        public void flowText(String texy);






}
