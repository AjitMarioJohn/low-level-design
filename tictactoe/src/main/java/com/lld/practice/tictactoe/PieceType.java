package com.lld.practice.tictactoe;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PieceType {
    X("X"), O("O");
    private final String shape;
}
