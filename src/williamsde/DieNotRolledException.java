/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * DieNotRolledException class
 * Name: Demarion williams
 * Last Updated: 9/11/24
 */
package williamsde;

public class DieNotRolledException extends RuntimeException {

        @Override
    public String getMessage(){

            return "Die not rolled";
        }

    }

