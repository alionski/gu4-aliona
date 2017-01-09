package final_version;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Loads characters from a an image font and stores them in an array of Array7x7s.
 * getCharacter(Character) returns an Array7x7 containing the given character.
 * If the given character does not exist it returns the symbol for an unknown character.
 * <p>
 * This class is a Singleton, only one instance is ever needed so only exactly one can be created.
 * The constructor is private, it's not possible to create instances with the "new" keyword.
 * Use characters.getInstance() instead.
 * <p>
 * Created by Martin on 2016-12-19.
 */
public class Characters {
    // Constants for convenience, never used outside of this class.
    private final int SPACE = 0;
    private final int EXCLAMATION_MARK = 1;
    private final int DOUBLE_QUOTATION_MARK = 2;
    private final int HASH = 3;
    private final int DOLLAR_SIGN = 4;
    private final int PERCENTAGE = 5;
    private final int AND = 6;
    private final int RIGHT_APOSTROPHE = 7;
    private final int LEFT_PARENTHESIS = 8;
    private final int RIGHT_PARENTHESIS = 9;
    private final int STAR = 10;
    private final int PLUS = 11;
    private final int COMMA = 12;
    private final int HYPHEN_MINUS = 13;
    private final int PERIOD = 14;
    private final int FORWARD_SLASH = 15;
    private final int ZERO = 16;
    private final int ONE = 17;
    private final int TWO = 18;
    private final int THREE = 19;
    private final int FOUR = 20;
    private final int FIVE = 21;
    private final int SIX = 22;
    private final int SEVEN = 23;
    private final int EIGHT = 24;
    private final int NINE = 25;
    private final int COLON = 26;
    private final int SEMI_COLON = 27;
    private final int LEFT_ANGLE_BRACKET = 28;
    private final int EQUALS = 29;
    private final int RIGHT_ANGLE_BRACKET = 30;
    private final int QUESTION_MARK = 31;
    private final int AT = 32;
    private final int A = 33;
    private final int B = 34;
    private final int C = 35;
    private final int D = 36;
    private final int E = 37;
    private final int F = 38;
    private final int G = 39;
    private final int H = 40;
    private final int I = 41;
    private final int J = 42;
    private final int K = 43;
    private final int L = 44;
    private final int M = 45;
    private final int N = 46;
    private final int O = 47;
    private final int P = 48;
    private final int Q = 49;
    private final int R = 50;
    private final int S = 51;
    private final int T = 52;
    private final int U = 53;
    private final int V = 54;
    private final int W = 55;
    private final int X = 56;
    private final int Y = 57;
    private final int Z = 58;
    private final int LEFT_SQUARE_BRACKET = 59;
    private final int BACK_SLASH = 60;
    private final int RIGHT_SQUARE_BRACKET = 61;
    private final int CARET = 62;
    private final int UNDER_SCORE = 63;
    private final int LEFT_APOSTROPHE = 64;
    private final int a = 65;
    private final int b = 66;
    private final int c = 67;
    private final int d = 68;
    private final int e = 69;
    private final int f = 70;
    private final int g = 71;
    private final int h = 72;
    private final int i = 73;
    private final int j = 74;
    private final int k = 75;
    private final int l = 76;
    private final int m = 77;
    private final int n = 78;
    private final int o = 79;
    private final int p = 80;
    private final int q = 81;
    private final int r = 82;
    private final int s = 83;
    private final int t = 84;
    private final int u = 85;
    private final int v = 86;
    private final int w = 87;
    private final int x = 88;
    private final int y = 89;
    private final int z = 90;
    private final int LEFT_CURLY_BRACKET = 91;
    private final int BROKEN_BAR = 92;
    private final int RIGHT_CURLY_BRACKET = 93;
    private final int TILDE = 94;
    private final int UNKNOWN = 95;

    // HashMap that maps characters to corresponding Array7x7
    private HashMap<Character, Array7x7> charMap = new HashMap<>();

    // Array of Array7x7 that holds color information used to display characters
    private Array7x7[] characters = new Array7x7[96];

    // Private constructor so we can't create more than one object.
    private Characters() {
        loadCharactersFromImage();
        initCharMap();
    }

    // Maps characters to corresponding Array7x7 representation.
    // Use getCharacter to return the Array7x7 associated with the key.
    private void initCharMap() {
        // Numbers
        charMap.put('0', characters[ZERO]);
        charMap.put('1', characters[ONE]);
        charMap.put('2', characters[TWO]);
        charMap.put('3', characters[THREE]);
        charMap.put('4', characters[FOUR]);
        charMap.put('5', characters[FIVE]);
        charMap.put('6', characters[SIX]);
        charMap.put('7', characters[SEVEN]);
        charMap.put('8', characters[EIGHT]);
        charMap.put('9', characters[NINE]);

        // Letters
        charMap.put('A', characters[A]);
        charMap.put('a', characters[a]);
        charMap.put('B', characters[B]);
        charMap.put('b', characters[b]);
        charMap.put('C', characters[C]);
        charMap.put('c', characters[c]);
        charMap.put('D', characters[D]);
        charMap.put('d', characters[d]);
        charMap.put('E', characters[E]);
        charMap.put('e', characters[e]);
        charMap.put('F', characters[F]);
        charMap.put('f', characters[f]);
        charMap.put('G', characters[G]);
        charMap.put('g', characters[g]);
        charMap.put('H', characters[H]);
        charMap.put('h', characters[h]);
        charMap.put('I', characters[I]);
        charMap.put('i', characters[i]);
        charMap.put('J', characters[J]);
        charMap.put('j', characters[j]);
        charMap.put('K', characters[K]);
        charMap.put('k', characters[k]);
        charMap.put('L', characters[L]);
        charMap.put('l', characters[l]);
        charMap.put('M', characters[M]);
        charMap.put('m', characters[m]);
        charMap.put('N', characters[N]);
        charMap.put('n', characters[n]);
        charMap.put('O', characters[O]);
        charMap.put('o', characters[o]);
        charMap.put('P', characters[P]);
        charMap.put('p', characters[p]);
        charMap.put('Q', characters[Q]);
        charMap.put('q', characters[q]);
        charMap.put('R', characters[R]);
        charMap.put('r', characters[r]);
        charMap.put('S', characters[S]);
        charMap.put('s', characters[s]);
        charMap.put('T', characters[T]);
        charMap.put('t', characters[t]);
        charMap.put('U', characters[U]);
        charMap.put('u', characters[u]);
        charMap.put('V', characters[V]);
        charMap.put('v', characters[v]);
        charMap.put('W', characters[W]);
        charMap.put('w', characters[w]);
        charMap.put('X', characters[X]);
        charMap.put('x', characters[x]);
        charMap.put('Y', characters[Y]);
        charMap.put('y', characters[y]);
        charMap.put('Z', characters[Z]);
        charMap.put('z', characters[z]);

        // Punctuation, special chars
        charMap.put(' ', characters[SPACE]);
        charMap.put('.', characters[PERIOD]);
        charMap.put(',', characters[COMMA]);
        charMap.put('!', characters[EXCLAMATION_MARK]);
        charMap.put('?', characters[QUESTION_MARK]);
        charMap.put('{', characters[LEFT_CURLY_BRACKET]);
        charMap.put('}', characters[RIGHT_CURLY_BRACKET]);
        charMap.put('<', characters[LEFT_ANGLE_BRACKET]);
        charMap.put('>', characters[RIGHT_ANGLE_BRACKET]);
        charMap.put(')', characters[RIGHT_PARENTHESIS]);
        charMap.put('(', characters[LEFT_PARENTHESIS]);
        charMap.put(':', characters[COLON]);
        charMap.put(';', characters[SEMI_COLON]);
        charMap.put('+', characters[PLUS]);
        charMap.put('-', characters[HYPHEN_MINUS]);
        charMap.put('=', characters[EQUALS]);
        charMap.put('&', characters[AND]);
        charMap.put('#', characters[HASH]);
        charMap.put('@', characters[AT]);
        charMap.put('$', characters[DOLLAR_SIGN]);
        charMap.put('%', characters[PERCENTAGE]);
        charMap.put('/', characters[FORWARD_SLASH]);
        charMap.put('\\', characters[BACK_SLASH]);
        charMap.put('[', characters[LEFT_SQUARE_BRACKET]);
        charMap.put(']', characters[RIGHT_SQUARE_BRACKET]);
        charMap.put('_', characters[UNDER_SCORE]);
        charMap.put('�', characters[RIGHT_APOSTROPHE]);
        charMap.put('`', characters[LEFT_APOSTROPHE]);
        charMap.put('^', characters[CARET]);
        charMap.put('*', characters[STAR]);
        charMap.put('"', characters[DOUBLE_QUOTATION_MARK]);
        charMap.put('~', characters[TILDE]);
    }

    private void loadCharactersFromImage() {
        int maxNumOfChars = 95;
        int arrayWidth = 6;
        int arrayHeight = 6;

        int currentRow;
        int currentCol;

        BufferedImage image = null;

        try {
            image = ImageIO.read(new File("font/font_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int currentCharacter = 0; currentCharacter <= maxNumOfChars; currentCharacter++) {
            characters[currentCharacter] = new Array7x7();

            currentCol = currentCharacter / 8;
            currentRow = currentCharacter % 8;

            int start_x = currentRow * 7;
            int start_y = currentCol * 7;

            int arrayIndex = 0;
            int arrayPos_x;
            int arrayPos_y;

            //System.out.println("-------" + currentCol + "," + currentRow + "-------");
            for (int x = start_x; x <= start_x + arrayWidth; x++) {
                for (int y = start_y; y <= start_y + arrayHeight; y++) {

                    arrayPos_x = arrayIndex % 7;
                    arrayPos_y = arrayIndex / 7;

                    characters[currentCharacter].setElement(arrayPos_x, arrayPos_y, image.getRGB(x, y));

                    arrayIndex++;
                    //System.out.println("[" + arrayPos_x + "," + arrayPos_y + "]" + "[" + currentCharacter + "]" + "[" + x + "," + y + "]");

                }
            }
        }
    }

    // Returns the Array7x7 mapped to the given character.
    // Returns the symbol for an unknown character if the character does not exist in the HashMap.
    public Array7x7 getCharacter(char character) {
        if(charMap.containsKey(character)) {
            return charMap.get(character);
        }
        return characters[UNKNOWN];
    }
    
    // Returns a reversed version of the character, used when scrolling text right.
    // Messy solution since Array7x7.getArray() doesn't return a copy, just the reference.
    public Array7x7 getCharacterReversed(char character) {
        if (charMap.containsKey(character)) {
            // Get the source array
            int[][] src = charMap.get(character).getArray();
            // Create a new array
            int[][] reverse = new int[src.length][src[0].length];
            // Copy the source array into the new array
            for(int i = 0; i < src.length; i++) {
                System.arraycopy(src[i], 0, reverse[i], 0, src[i].length);
            }

            // Reverse the new array
            for(int j = 0; j < reverse.length; j++) {
                for(int i = 0; i < reverse[j].length / 2; i++) {
                    int temp = reverse[j][i];
                    reverse[j][i] = reverse[j][reverse[j].length - i - 1];
                    reverse[j][reverse[j].length - i - 1] = temp;
                }
            }
            return new Array7x7(reverse);
        }
        return characters[UNKNOWN];
}

    // Used for Singleton-functionality.
    private static class CharactersHolder {
        private static final Characters INSTANCE = new Characters();
    }

    // Returns a reference to a characters object, only one instance can be created.
    public static Characters getInstance() {
        return CharactersHolder.INSTANCE;
    }
}