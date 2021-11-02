package ua.com.alevel.level1.entity;

import ua.com.alevel.util.CharacterUtil;


public class ChessPosition {
    private int numberPosition;
    private char CharacterPosition;

    public ChessPosition(int numberPosition, char CharacterPosition) {
        this.numberPosition = numberPosition;
        this.CharacterPosition = CharacterPosition;
    }

    public int getNumberPosition() {
        return this.numberPosition;
    }

    public void setNumberPosition(int numberPosition) {
        this.numberPosition = numberPosition;
    }

    public char getCharacterPosition() {
        return this.CharacterPosition;
    }

    public void setCharacterPosition(char CharacterPosition) {
        if (!CharacterUtil.isLatinLetter(CharacterPosition)) {
            throw new IllegalArgumentException();
        }
        this.CharacterPosition = Character.toUpperCase(CharacterPosition);
    }

}
