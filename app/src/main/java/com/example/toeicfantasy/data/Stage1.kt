package com.example.toeicfantasy.data

sealed class Stage1(
    id: String,
    name: String,
    point: Int,
    exp: Int
): Mission(id,name,point,exp) {

    data object C101 : Stage1("C101",
        "회사 청소하기",
        100,
        10)
    data object C102 : Stage1("C101",
        "회사 청소하기2",
        150,
        15)
    data object C103 : Stage1("C101",
        "회사 청소하기3",
        200,
        20)
}