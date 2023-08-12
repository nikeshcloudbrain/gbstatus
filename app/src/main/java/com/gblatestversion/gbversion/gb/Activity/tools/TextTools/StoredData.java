package com.gblatestversion.gbversion.gb.Activity.tools.TextTools;

import androidx.exifinterface.media.ExifInterface;

import kotlin.jvm.internal.Intrinsics;

public class StoredData {
    public static final StoredData INSTANCE = new StoredData();
    private static final String[] allEmojis = {"😃", "😄", "😁", "😆", "😅", "🤣", "😂", "🙂", "🙃", "😉", "😊", "😇", "😍", "🤩", "😘", "😗", "😚", "😙", "😋", "😛", "😜", "🤪", "😝", "🤑", "🤗", "🤭", "🤫", "🤔", "🤐", "🤨", "😐", "😑", "😶", "😏", "😒", "🙄", "😬", "🤥", "😌", "😔", "😪", "🤤", "😴", "😷", "🤒", "🤕", "🤢", "🤮", "🤧", "😵", "🤯", "🤠", "😎", "🤓", "🧐", "😕", "😟", "🙁", "😮", "😯", "😲", "😳", "😦", "😧", "😨", "😰", "😥", "😢", "😭", "😱", "😖", "😣", "😞", "😓", "😩", "😫", "😤", "😡", "😠", "🤬", "😈", "👿"};
    private static final Art[] art_data = {new Art("▁ ▂ ▄ ▅ ▆ ▇ █", "█ ▇ ▆ ▅ ▄ ▂ ▁"), new Art("°°°·.°·..·°¯°·._.·", "·._.·°¯°·.·° .·°°°"), new Art("(¯`·.¸¸.·´¯`·.¸¸.->", "<-.¸¸.·´¯`·.¸¸.·´¯)"), new Art("ıllıllı", "ıllıllı"), new Art("¸,ø¤º°`°º¤ø,¸¸,ø¤º°", "°º¤ø,¸¸,ø¤º°`°º¤ø,¸"), new Art("(¯´•._.•", "•._.•´¯)"), new Art("꧁༒༻☬ད", "ཌ☬༺༒꧂"), new Art("¸„.-•~¹°”ˆ˜¨", "¨˜ˆ”°¹~•-.„¸"), new Art("˜”*°•.˜”*°•", "•°*”˜.•°*”˜"), new Art("♥", "♥"), new Art("♥♥♥", "♥♥♥"), new Art("✎🐠", "🍓💝"), new Art("(-_-)", "(-_-)"), new Art("¤¸¸.•´¯`•¸¸.•..>>", "<<..•.¸¸•´¯`•.¸¸¤"), new Art("*•.¸♡", "♡¸.•*"), new Art("◥꧁ད ॐ卐", "卐ॐ ཌ꧂◤"), new Art("🍑 ⋆ 🍎 🎀", "🎀 🍎 ⋆ 🍑"), new Art("░▒▓█►─═ ♥", "♥ ═─◄█▓▒░"), new Art(".•°¤*(¯`★´¯)*¤°", "°¤*(¯´★`¯)*¤°•."), new Art("🐣 🎀", "🎀 🐣"), new Art("╚»★«╝", "╚»★«╝"), new Art("∙∙·▫▫ᵒᴼᵒ▫ₒₒ▫ᵒᴼᵒ▫ₒₒ▫ᵒᴼᵒ", "ᵒᴼᵒ▫ₒₒ▫ᵒᴼᵒ▫ₒₒ▫ᵒᴼᵒ▫▫·∙∙"), new Art("¯_( ͡° ͜ʖ ͡°)_/¯", "¯_( ͡° ͜ʖ ͡°)_/¯")};
    private static final String[][] digit_styles;
    public static String finalEmojiString;
    private static final char[] mirror_small_capital_letters_and_digits = {592, 'q', 596, 'p', 477, 607, 595, 613, 7433, 383, 670, 'l', 623, 'u', 'o', 'd', 'b', 633, 's', 647, 'n', 652, 653, 'x', 654, 'z', 11375, 42221, 42203, 42231, 398, 42206, 42216, 'H', 'I', 383, 42200, 42230, 'W', 'N', 'O', 42194, 8184, 42212, 'S', 42197, 42229, 42213, 'M', 'X', 8516, 'Z', '0', 406, 1351, 400, 'h', 2796, '9', 'L', '8', '6'};
    private static final char[] simple_capital_letters_and_digits = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final char[] simple_small_capital_letters_and_digits = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final String[] stylish_text_1;
    private static final String[] stylish_text_10;
    private static final String[] stylish_text_11;
    private static final String[] stylish_text_12;
    private static final String[] stylish_text_13;
    private static final String[] stylish_text_14;
    private static final String[] stylish_text_15;
    private static final String[] stylish_text_16;
    private static final String[] stylish_text_17;
    private static final String[] stylish_text_18;
    private static final String[] stylish_text_19;
    private static final String[] stylish_text_2;
    private static final String[] stylish_text_20;
    private static final String[] stylish_text_21;
    private static final String[] stylish_text_22;
    private static final String[] stylish_text_23;
    private static final String[] stylish_text_3;
    private static final String[] stylish_text_4;
    private static final String[] stylish_text_5;
    private static final String[] stylish_text_6;
    private static final String[] stylish_text_7;
    private static final String[] stylish_text_8;
    private static final String[] stylish_text_9;
    private static final String[][] text_styles;

    static {
        String[] strArr = {"𝔞", "𝔟", "𝔠", "𝔡", "𝔢", "𝔣", "𝔤", "𝔥", "𝔦", "𝔧", "𝔨", "𝔩", "𝔪", "𝔫", "𝔬", "𝔭", "𝔮", "𝔯", "𝔰", "𝔱", "𝔲", "𝔳", "𝔴", "𝔵", "𝔶", "𝔷", "𝔄", "𝔅", "ℭ", "𝔇", "𝔈", "𝔉", "𝔊", "ℌ", "ℑ", "𝔍", "𝔎", "𝔏", "𝔐", "𝔑", "𝔒", "𝔓", "𝔔", "ℜ", "𝔖", "𝔗", "𝔘", "𝔙", "𝔚", "𝔛", "𝔜", "ℨ", "0", "1", ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D, "4", "5", "6", "7", "8", "9"};
        stylish_text_1 = strArr;
        String[] strArr2 = {"𝖆", "𝖇", "𝖈", "𝖉", "𝖊", "𝖋", "𝖌", "𝖍", "𝖎", "𝖏", "𝖐", "𝖑", "𝖒", "𝖓", "𝖔", "𝖕", "𝖖", "𝖗", "𝖘", "𝖙", "𝖚", "𝖛", "𝖜", "𝖝", "𝖞", "𝖟", "𝕬", "𝕭", "𝕮", "𝕯", "𝕰", "𝕱", "𝕲", "𝕳", "𝕴", "𝕵", "𝕶", "𝕷", "𝕸", "𝕹", "𝕺", "𝕻", "𝕼", "𝕽", "𝕾", "𝕿", "𝖀", "𝖁", "𝖂", "𝖃", "𝖄", "𝖅", "0", "1", ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D, "4", "5", "6", "7", "8", "9"};
        stylish_text_2 = strArr2;
        String[] strArr3 = {"𝓪", "𝓫", "𝓬", "𝓭", "𝓮", "𝓯", "𝓰", "𝓱", "𝓲", "𝓳", "𝓴", "𝓵", "𝓶", "𝓷", "𝓸", "𝓹", "𝓺", "𝓻", "𝓼", "𝓽", "𝓾", "𝓿", "𝔀", "𝔁", "𝔂", "𝔃", "𝓐", "𝓑", "𝓒", "𝓓", "𝓔", "𝓕", "𝓖", "𝓗", "𝓘", "𝓙", "𝓚", "𝓛", "𝓜", "𝓝", "𝓞", "𝓟", "𝓠", "𝓡", "𝓢", "𝓣", "𝓤", "𝓥", "𝓦", "𝓧", "𝓨", "𝓩", "0", "1", ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D, "4", "5", "6", "7", "8", "9"};
        stylish_text_3 = strArr3;
        String[] strArr4 = {"𝒶", "𝒷", "𝒸", "𝒹", "𝑒", "𝒻", "𝑔", "𝒽", "𝒾", "𝒿", "𝓀", "𝓁", "𝓂", "𝓃", "𝑜", "𝓅", "𝓆", "𝓇", "𝓈", "𝓉", "𝓊", "𝓋", "𝓌", "𝓍", "𝓎", "𝓏", "𝒜", "𝐵", "𝒞", "𝒟", "𝐸", "𝐹", "𝒢", "𝐻", "𝐼", "𝒥", "𝒦", "𝐿", "𝑀", "𝒩", "𝒪", "𝒫", "𝒬", "𝑅", "𝒮", "𝒯", "𝒰", "𝒱", "𝒲", "𝒳", "𝒴", "𝒵", "𝟢", "𝟣", "𝟤", "𝟥", "𝟦", "𝟧", "𝟨", "𝟩", "𝟪", "𝟫"};
        stylish_text_4 = strArr4;
        String[] strArr5 = {"𝕒", "𝕓", "𝕔", "𝕕", "𝕖", "𝕗", "𝕘", "𝕙", "𝕚", "𝕛", "𝕜", "𝕝", "𝕞", "𝕟", "𝕠", "𝕡", "𝕢", "𝕣", "𝕤", "𝕥", "𝕦", "𝕧", "𝕨", "𝕩", "𝕪", "𝕫", "𝔸", "𝔹", "ℂ", "𝔻", "𝔼", "𝔽", "𝔾", "ℍ", "𝕀", "𝕁", "𝕂", "𝕃", "𝕄", "ℕ", "𝕆", "ℙ", "ℚ", "ℝ", "𝕊", "𝕋", "𝕌", "𝕍", "𝕎", "𝕏", "𝕐", "ℤ", "𝟘", "𝟙", "𝟚", "𝟛", "𝟜", "𝟝", "𝟞", "𝟟", "𝟠", "𝟡"};
        stylish_text_5 = strArr5;
        String[] strArr6 = {"𝘢", "𝘣", "𝘤", "𝘥", "𝘦", "𝘧", "𝘨", "𝘩", "𝘪", "𝘫", "𝘬", "𝘭", "𝘮", "𝘯", "𝘰", "𝘱", "𝘲", "𝘳", "𝘴", "𝘵", "𝘶", "𝘷", "𝘸", "𝘹", "𝘺", "𝘻", "𝘈", "𝘉", "𝘊", "𝘋", "𝘌", "𝘍", "𝘎", "𝘏", "𝘐", "𝘑", "𝘒", "𝘓", "𝘔", "𝘕", "𝘖", "𝘗", "𝘘", "𝘙", "𝘚", "𝘛", "𝘜", "𝘝", "𝘞", "𝘟", "𝘠", "𝘡", "0", "1", ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D, "4", "5", "6", "7", "8", "9"};
        stylish_text_6 = strArr6;
        String[] strArr7 = {"𝙖", "𝙗", "𝙘", "𝙙", "𝙚", "𝙛", "𝙜", "𝙝", "𝙞", "𝙟", "𝙠", "𝙡", "𝙢", "𝙣", "𝙤", "𝙥", "𝙦", "𝙧", "𝙨", "𝙩", "𝙪", "𝙫", "𝙬", "𝙭", "𝙮", "𝙯", "𝘼", "𝘽", "𝘾", "𝘿", "𝙀", "𝙁", "𝙂", "𝙃", "𝙄", "𝙅", "𝙆", "𝙇", "𝙈", "𝙉", "𝙊", "𝙋", "𝙌", "𝙍", "𝙎", "𝙏", "𝙐", "𝙑", "𝙒", "𝙓", "𝙔", "𝙕", "0", "1", ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D, "4", "5", "6", "7", "8", "9"};
        stylish_text_7 = strArr7;
        String[] strArr8 = {"𝚊", "𝚋", "𝚌", "𝚍", "𝚎", "𝚏", "𝚐", "𝚑", "𝚒", "𝚓", "𝚔", "𝚕", "𝚖", "𝚗", "𝚘", "𝚙", "𝚚", "𝚛", "𝚜", "𝚝", "𝚞", "𝚟", "𝚠", "𝚡", "𝚢", "𝚣", "𝙰", "𝙱", "𝙲", "𝙳", "𝙴", "𝙵", "𝙶", "𝙷", "𝙸", "𝙹", "𝙺", "𝙻", "𝙼", "𝙽", "𝙾", "𝙿", "𝚀", "𝚁", "𝚂", "𝚃", "𝚄", "𝚅", "𝚆", "𝚇", "𝚈", "𝚉", "𝟶", "𝟷", "𝟸", "𝟹", "𝟺", "𝟻", "𝟼", "𝟽", "𝟾", "𝟿"};
        stylish_text_8 = strArr8;
        String[] strArr9 = {"ⓐ", "ⓑ", "ⓒ", "ⓓ", "ⓔ", "ⓕ", "ⓖ", "ⓗ", "ⓘ", "ⓙ", "ⓚ", "ⓛ", "ⓜ", "ⓝ", "ⓞ", "ⓟ", "ⓠ", "ⓡ", "ⓢ", "ⓣ", "ⓤ", "ⓥ", "ⓦ", "ⓧ", "ⓨ", "ⓩ", "Ⓐ", "Ⓑ", "Ⓒ", "Ⓓ", "Ⓔ", "Ⓕ", "Ⓖ", "Ⓗ", "Ⓘ", "Ⓙ", "Ⓚ", "Ⓛ", "Ⓜ", "Ⓝ", "Ⓞ", "Ⓟ", "Ⓠ", "Ⓡ", "Ⓢ", "Ⓣ", "Ⓤ", "Ⓥ", "Ⓦ", "Ⓧ", "Ⓨ", "Ⓩ", "⓪", "①", "②", "③", "④", "⑤", "⑥", "⑦", "⑧", "⑨"};
        stylish_text_9 = strArr9;
        String[] strArr10 = {"🅰", "🅱", "🅲", "🅳", "🅴", "🅵", "🅶", "🅷", "🅸", "🅹", "🅺", "🅻", "🅼", "🅽", "🅾", "🅿", "🆀", "🆁", "🆂", "🆃", "🆄", "🆅", "🆆", "🆇", "🆈", "🆉", "🅰", "🅱", "🅲", "🅳", "🅴", "🅵", "🅶", "🅷", "🅸", "🅹", "🅺", "🅻", "🅼", "🅽", "🅾", "🅿", "🆀", "🆁", "🆂", "🆃", "🆄", "🆅", "🆆", "🆇", "🆈", "🆉", "0", "1", ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D, "4", "5", "6", "7", "8", "9"};
        stylish_text_10 = strArr10;
        String[] strArr11 = {"ᗩ", "ᗷ", "ᑕ", "ᗪ", "ᗴ", "ᖴ", "Ǥ", "ᕼ", "Ꭵ", "ᒎ", "ᛕ", "ᒪ", "ᗰ", "ᑎ", "ᗝ", "ᑭ", "Ɋ", "ᖇ", "ᔕ", "丅", "ᑌ", "ᐯ", "ᗯ", "᙭", "Ƴ", "乙", "ᗩ", "ᗷ", "ᑕ", "ᗪ", "ᗴ", "ᖴ", "Ǥ", "ᕼ", "Ꭵ", "ᒎ", "ᛕ", "ᒪ", "ᗰ", "ᑎ", "ᗝ", "ᑭ", "Ɋ", "ᖇ", "ᔕ", "丅", "ᑌ", "ᐯ", "ᗯ", "᙭", "Ƴ", "乙", "0", "1", ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D, "4", "5", "6", "7", "8", "9"};
        stylish_text_11 = strArr11;
        String[] strArr12 = {"𝐚", "𝐛", "𝐜", "𝐝", "𝐞", "𝐟", "𝐠", "𝐡", "𝐢", "𝐣", "𝐤", "𝐥", "𝐦", "𝐧", "𝐨", "𝐩", "𝐪", "𝐫", "𝐬", "𝐭", "𝐮", "𝐯", "𝐰", "𝐱", "𝐲", "𝐳", "𝐀", "𝐁", "𝐂", "𝐃", "𝐄", "𝐅", "𝐆", "𝐇", "𝐈", "𝐉", "𝐊", "𝐋", "𝐌", "𝐍", "𝐎", "𝐏", "𝐐", "𝐑", "𝐒", "𝐓", "𝐔", "𝐕", "𝐖", "𝐗", "𝐘", "𝐙", "𝟎", "𝟏", "𝟐", "𝟑", "𝟒", "𝟓", "𝟔", "𝟕", "𝟖", "𝟗"};
        stylish_text_12 = strArr12;
        String[] strArr13 = {"🄰", "🄱", "🄲", "🄳", "🄴", "🄵", "🄶", "🄷", "🄸", "🄹", "🄺", "🄻", "🄼", "🄽", "🄾", "🄿", "🅀", "🅁", "🅂", "🅃", "🅄", "🅅", "🅆", "🅇", "🅈", "🅉", "🄰", "🄱", "🄲", "🄳", "🄴", "🄵", "🄶", "🄷", "🄸", "🄹", "🄺", "🄻", "🄼", "🄽", "🄾", "🄿", "🅀", "🅁", "🅂", "🅃", "🅄", "🅅", "🅆", "🅇", "🅈", "🅉", "0", "1", ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D, "4", "5", "6", "7", "8", "9"};
        stylish_text_13 = strArr13;
        String[] strArr14 = {"ａ", "ｂ", "ｃ", "ｄ", "ｅ", "ｆ", "ｇ", "ｈ", "ｉ", "ｊ", "ｋ", "ｌ", "ｍ", "ｎ", "ｏ", "ｐ", "ｑ", "ｒ", "ｓ", "ｔ", "ｕ", "ｖ", "ｗ", "ｘ", "ｙ", "ｚ", "Ａ", "Ｂ", "Ｃ", "Ｄ", "Ｅ", "Ｆ", "Ｇ", "Ｈ", "Ｉ", "Ｊ", "Ｋ", "Ｌ", "Ｍ", "Ｎ", "Ｏ", "Ｐ", "Ｑ", "Ｒ", "Ｓ", "Ｔ", "Ｕ", "Ｖ", "Ｗ", "Ｘ", "Ｙ", "Ｚ", "０", "１", "２", "３", "４", "５", "６", "７", "８", "９"};
        stylish_text_14 = strArr14;
        String[] strArr15 = {"卂", "乃", "匚", "ᗪ", "乇", "千", "Ꮆ", "卄", "丨", "ﾌ", "Ҝ", "ㄥ", "爪", "几", "ㄖ", "卩", "Ɋ", "尺", "丂", "ㄒ", "ㄩ", "ᐯ", "山", "乂", "ㄚ", "乙", "卂", "乃", "匚", "ᗪ", "乇", "千", "Ꮆ", "卄", "丨", "ﾌ", "Ҝ", "ㄥ", "爪", "几", "ㄖ", "卩", "Ɋ", "尺", "丂", "ㄒ", "ㄩ", "ᐯ", "山", "乂", "ㄚ", "乙", "0", "1", ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D, "4", "5", "6", "7", "8", "9"};
        stylish_text_15 = strArr15;
        String[] strArr16 = {"ᵃ", "ᵇ", "ᶜ", "ᵈ", "ᵉ", "ᶠ", "ᵍ", "ʰ", "ⁱ", "ʲ", "ᵏ", "ˡ", "ᵐ", "ⁿ", "ᵒ", "ᵖ", "q", "ʳ", "ˢ", "ᵗ", "ᵘ", "ᵛ", "ʷ", "ˣ", "ʸ", "ᶻ", "ᴬ", "ᴮ", "ᶜ", "ᴰ", "ᴱ", "ᶠ", "ᴳ", "ᴴ", "ᴵ", "ᴶ", "ᴷ", "ᴸ", "ᴹ", "ᴺ", "ᴼ", "ᴾ", "Q", "ᴿ", "ˢ", "ᵀ", "ᵁ", "ⱽ", "ᵂ", "ˣ", "ʸ", "ᶻ", "⁰", "¹", "²", "³", "⁴", "⁵", "⁶", "⁷", "⁸", "⁹"};
        stylish_text_16 = strArr16;
        String[] strArr17 = {"a͓̽", "b͓̽", "c͓̽", "d͓̽", "e͓̽", "f͓̽", "g͓̽", "h͓̽", "i͓̽", "j͓̽", "k͓̽", "l͓̽", "m͓̽", "n͓̽", "o͓̽", "p͓̽", "q͓̽", "r͓̽", "s͓̽", "t͓̽", "u͓̽", "v͓̽", "w͓̽", "x͓̽", "y͓̽", "z͓̽", "A͓̽", "B͓̽", "C͓̽", "D͓̽", "E͓̽", "F͓̽", "G͓̽", "H͓̽", "I͓̽", "J͓̽", "K͓̽", "L͓̽", "M͓̽", "N͓̽", "O͓̽", "P͓̽", "Q͓̽", "R͓̽", "S͓̽", "T͓̽", "U͓̽", "V͓̽", "W͓̽", "X͓̽", "Y͓̽", "Z͓̽", "0", "͓̽1", "͓̽2", "͓̽3", "͓̽4", "͓̽5", "͓̽6", "͓̽7", "͓̽8", "͓̽9̽"};
        stylish_text_17 = strArr17;
        String[] strArr18 = {"𝚊̷", "𝚋̷", "𝚌̷", "𝚍̷", "𝚎̷", "𝚏̷", "𝚐̷", "𝚑̷", "𝚒̷", "𝚓̷", "𝚔̷", "𝚕̷", "𝚖̷", "𝚗̷", "𝚘̷", "𝚙̷", "𝚚̷", "𝚛̷", "𝚜̷", "𝚝̷", "𝚞̷", "𝚟̷", "𝚠̷", "𝚡̷", "𝚢̷", "𝚣̷", "𝙰̷", "𝙱̷", "𝙲̷", "𝙳̷", "𝙴̷", "𝙵̷", "𝙶̷", "𝙷̷", "𝙸̷", "𝙹̷", "𝙺̷", "𝙻̷", "𝙼̷", "𝙽̷", "𝙾̷", "𝙿̷", "𝚀̷", "𝚁̷", "𝚂̷", "𝚃̷", "𝚄̷", "𝚅̷", "𝚆̷", "𝚇̷", "𝚈̷", "𝚉̷", "𝟶̷", "𝟷̷", "𝟸̷", "𝟹̷", "𝟺̷", "𝟻̷", "𝟼̷", "𝟽̷", "𝟾̷", "𝟿̷"};
        stylish_text_18 = strArr18;
        String[] strArr19 = {"ά", "в", "ς", "ȡ", "έ", "ғ", "ģ", "ħ", "ί", "ј", "ķ", "Ļ", "м", "ή", "ό", "ρ", "q", "ŕ", "ş", "ţ", "ù", "ν", "ώ", "x", "ч", "ž", "ά", "в", "ς", "ȡ", "έ", "ғ", "ģ", "ħ", "ί", "ј", "ķ", "Ļ", "м", "ή", "ό", "ρ", "q", "ŕ", "ş", "ţ", "ù", "ν", "ώ", "x", "ч", "ž", "0", "1", ExifInterface.GPS_MEASUREMENT_3D, "4", "5", "6", "7", "8", "9"};
        stylish_text_19 = strArr19;
        String[] strArr20 = {"ꋫ", "ꃃ", "ꏸ", "ꁕ", "ꍟ", "ꄘ", "ꁍ", "ꑛ", "ꂑ", "ꀭ", "ꀗ", "꒒", "ꁒ", "ꁹ", "ꆂ", "ꉣ", "ꁸ", "꒓", "ꌚ", "꓅", "ꐇ", "ꏝ", "ꅐ", "ꇓ", "ꐟ", "ꁴ", "ꋫ", "ꃃ", "ꏸ", "ꁕ", "ꍟ", "ꄘ", "ꁍ", "ꑛ", "ꂑ", "ꀭ", "ꀗ", "꒒", "ꁒ", "ꁹ", "ꆂ", "ꉣ", "ꁸ", "꒓", "ꌚ", "꓅", "ꐇ", "ꏝ", "ꅐ", "ꇓ", "ꐟ", "ꁴ", "0", "1", ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D, "4", "5", "6", "7", "8", "9"};
        stylish_text_20 = strArr20;
        String[] strArr21 = {"【a】", "【b】", "【c】", "【d】", "【e】", "【f】", "【g】", "【h】", "【i】", "【j】", "【k】", "【l】", "【m】", "【n】", "【o】", "【p】", "【q】", "【r】", "【s】", "【t】", "【u】", "【v】", "【w】", "【x】", "【y】", "【z】", "【A】", "【B】", "【C】", "【D】", "【E】", "【F】", "【G】", "【H】", "【I】", "【J】", "【K】", "【L】", "【M】", "【N】", "【O】", "【P】", "【Q】", "【R】", "【S】", "【T】", "【U】", "【V】", "【W】", "【X】", "【Y】", "【Z】", "【0】", "【1】", "【2】", "【3】", "【4】", "【5】", "【6】", "【7】", "【8】", "【9】"};
        stylish_text_21 = strArr21;
        String[] strArr22 = {"『a』", "『b』", "『c』", "『d』", "『e』", "『f』", "『g』", "『h』", "『i』", "『j』", "『k』", "『l』", "『m』", "『n』", "『o』", "『p』", "『q』", "『r』", "『s』", "『t』", "『u』", "『v』", "『w』", "『x』", "『y』", "『z』", "『A』", "『B』", "『C』", "『D』", "『E』", "『F』", "『G』", "『H』", "『I』", "『J』", "『K』", "『L』", "『M』", "『N』", "『O』", "『P』", "『Q』", "『R』", "『S』", "『T』", "『U』", "『V』", "『W』", "『X』", "『Y』", "『Z』", "『0』", "『1』", "『2』", "『3』", "『4』", "『5』", "『6』", "『7』", "『8』", "『9』"};
        stylish_text_22 = strArr22;
        String[] strArr23 = {"α", "в", "¢", "∂", "є", "ƒ", "g", "н", "ι", "נ", "к", "ℓ", "м", "η", "σ", "ρ", "q", "я", "ѕ", "т", "υ", "ν", "ω", "χ", "у", "z", "α", "в", "¢", "∂", "є", "ƒ", "g", "н", "ι", "נ", "к", "ℓ", "м", "η", "σ", "ρ", "q", "я", "ѕ", "т", "υ", "ν", "ω", "χ", "у", "z", "0", "1", ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D, "4", "5", "6", "7", "8", "9"};
        stylish_text_23 = strArr23;
        text_styles = new String[][]{strArr, strArr2, strArr3, strArr4, strArr5, strArr6, strArr7, strArr8, strArr9, strArr10, strArr11, strArr12, strArr13, strArr14, strArr15, strArr16, strArr17, strArr18, strArr19, strArr20, strArr21, strArr22, strArr23};
        digit_styles = new String[][]{strArr4, strArr5, strArr8, strArr9, strArr12, strArr14, strArr16, strArr17, strArr18, strArr21, strArr22};
    }

    private StoredData() {
    }

    public final String getFinalEmojiString() {
        String str = finalEmojiString;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("finalEmojiString");
        }
        return str;
    }

    public final void setFinalEmojiString(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        finalEmojiString = str;
    }

    public final Art[] getArt_data() {
        return art_data;
    }

    public final char[] getMirror_small_capital_letters_and_digits() {
        return mirror_small_capital_letters_and_digits;
    }

    public final char[] getSimple_small_capital_letters_and_digits() {
        return simple_small_capital_letters_and_digits;
    }

    public final char[] getSimple_capital_letters_and_digits() {
        return simple_capital_letters_and_digits;
    }

    public final String[] getAllEmojis() {
        return allEmojis;
    }

    public final String[][] getText_styles() {
        return text_styles;
    }

    public final String[][] getDigit_styles() {
        return digit_styles;
    }
}
