public class LongWordTester {
    public LongWordTester(){
        //new a long word and set 12345 bits to true
        LongWord longWord = new LongWord();
        longWord.setBit(1);
        longWord.setBit(2);
        longWord.setBit(3);
        longWord.setBit(4);
        longWord.setBit(5);

        //set the bit 3 to false
        longWord.clearBit(3);
        //flip the bit 0
        longWord.toggleBit(0);
        System.out.println(longWord.toString() + " getUnSigned: " + longWord.getUnsigned() + "\n");

        //new a long word and set to -1
        LongWord longWord2 = new LongWord();
        longWord2.set(-1);
        System.out.println(longWord2.toString() + " getUnSigned: " + longWord2.getUnsigned());

        System.out.println("1: shift left  " + longWord2.shiftLeftLogical(2).toString());
        System.out.println("2: shift right " + longWord2.shiftRightLogical(2).toString());
        System.out.println("3: shift Arith " + longWord2.shiftRightArithmetic(2).toString());
        System.out.println("4: not         " + longWord2.not().toString());
        System.out.println("5: and         " + longWord2.and(longWord).toString());
        System.out.println("6: or          " + longWord2.or(longWord).toString());
        System.out.println("7: xor         " + longWord2.xor(longWord).toString());

        System.out.println();
        //new a long word and copy from longWord
        LongWord longWord3 = new LongWord();
        longWord3.copy(longWord);
        longWord3.setBit(31);
        System.out.println(longWord3.toString() + " getUnSigned: " + longWord3.getUnsigned());
        System.out.println("1: shift left  " + longWord3.shiftLeftLogical(2).toString());
        System.out.println("2: shift right " + longWord3.shiftRightLogical(2).toString());
        System.out.println("3: shift Arith " + longWord3.shiftRightArithmetic(2).toString());
        System.out.println("4: not         " + longWord3.not().toString());
        System.out.println("5: and         " + longWord2.and(longWord3).toString());
        System.out.println("6: or          " + longWord2.or(longWord3).toString());
        System.out.println("7: xor         " + longWord2.xor(longWord3).toString());
    }
}
