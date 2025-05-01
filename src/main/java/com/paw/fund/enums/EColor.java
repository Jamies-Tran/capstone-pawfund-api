package com.paw.fund.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EColor {
    BLACK("BLACK", "Đen"  ),
    WHITE("WHITE", "Trắng"  ),
    BROWN("BROWN", "Nâu"),
    GRAY("GRAY", "Xám"),
    GOLDEN("GOLDEN", "Vàng kim"),
    CREAM("CREAM", "Kem"),
    TAN("TAN", "Vàng nâu"),
    CHOCOLATE("CHOCOLATE", "Nâu sô cô la"),
    FAWN("FAWN", "Vàng nhạt"),
    SILVER("SILVER", "Bạc"),
    BLUE("BLUE", "Xanh xám"),
    BRINDLE("BRINDLE", "Vằn"),
    MERLE("MERLE", "Loang lổ"),
    TUXEDO("TUXEDO", "Lông hai màu kiểu áo vest"),
    CALICO("CALICO", "Tam thể (vàng, trắng, đen)"),
    TABBY("TABBY", "Vằn mướp (xám sọc)"),
    TRICOLOR("TRICOLOR", "Ba màu"),
    BICOLOR("BICOLOR", "Hai màu"),
    ORANGE("ORANGE", "Cam"),
    SEAL("SEAL", "Nâu đậm gần như đen"),
    SMOKE("SMOKE", "Xám khói"),
    POINT("POINT", "Lông nhạt thân, đậm ở mặt, tai, chân, đuôi"),;

    String code;
    String name;
}
