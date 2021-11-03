/**
 * @author Yunrui Huang
 * 11/2/2021
 */

public class ALUTester {
    public ALUTester(){


        ALU alu = new ALU();
        LongWord longWord1 = new LongWord();
        LongWord longWord2 = new LongWord();
        LongWord longWord3 = new LongWord();

        longWord1.set(10);
        longWord2.set(30);
        System.out.println(longWord1.toString());
        System.out.println(longWord2.toString());

        /**
         * testing add
         */
        longWord3 = alu.operate(3,longWord1,longWord2);
        System.out.println(longWord3.toString() + " " + alu.toString());
        //System.out.println("2: " + longWord2.toString());

        /**
         * testing sub
         */
        longWord3 = alu.operate(4,longWord1,longWord2);
        System.out.println(longWord3.toString() + " " + alu.toString());

        /**
         * testing carry out flag
         */
        //System.out.println("3: " + longWord2.toString());
        longWord1 = alu.operate(3,longWord3,longWord2);
        System.out.println(longWord1.toString() + " " + alu.toString());

        /**
         * testing overflow flag
         */
        longWord2.set(-1);
        longWord3.set(-2147483648);
        longWord3 = alu.operate(3,longWord3, longWord2);
        System.out.println(longWord3.toString() + " " + alu.toString());

        /**
         * testing and
         */
        longWord1.set(10);
        longWord2.set(1000);
        System.out.printf("\n\ntesting and\n");
        System.out.println(longWord1.toString());
        System.out.println(longWord2.toString());
        longWord3 = alu.operate(0,longWord1,longWord2);
        System.out.println(longWord3.toString() + " " + alu.toString());

        /**
         * testing or
         */
        longWord1.set(10);
        longWord2.set(1000);
        System.out.printf("\n\ntesting or\n");
        System.out.println(longWord1.toString());
        System.out.println(longWord2.toString());
        longWord3 = alu.operate(1,longWord1,longWord2);
        System.out.println(longWord3.toString() + " " + alu.toString());

        /**
         * testing xor
         */
        longWord1.set(10);
        longWord2.set(1000);
        System.out.printf("\n\ntesting xor\n");
        System.out.println(longWord1.toString());
        System.out.println(longWord2.toString());
        longWord3 = alu.operate(2,longWord1,longWord2);
        System.out.println(longWord3.toString() + " " + alu.toString());

        /**
         * testing Not
         */
        longWord1.set(10);
        longWord2.set(-1);
        System.out.printf("\n\ntesting not\n");
        System.out.println(longWord1.toString());
        System.out.println(longWord2.toString());
        longWord3 = alu.operate(2,longWord1,longWord2);
        System.out.println(longWord3.toString() + " " + alu.toString());

        /**
         * testing SLL
         */
        longWord1.set(-1);
        longWord1.clearBit(30);
        longWord2.set(1);
        System.out.printf("\n\ntesting sll\n");
        System.out.println(longWord1.toString());
        System.out.println(longWord2.toString());
        longWord3 = alu.operate(5,longWord1,longWord2);
        System.out.println(longWord3.toString() + " " + alu.toString());

        /**
         * testing SRL
         */
        longWord1.set(1000);
        longWord2.set(2);
        System.out.printf("\n\ntesting srl\n");
        System.out.println(longWord1.toString());
        System.out.println(longWord2.toString());
        longWord3 = alu.operate(6,longWord1,longWord2);
        System.out.println(longWord3.toString() + " " + alu.toString());

        /**
         * testing SRA
         */
        longWord1.set(-1000);
        longWord2.set(3);
        System.out.printf("\n\ntesting sra\n");
        System.out.println(longWord1.toString());
        System.out.println(longWord2.toString());
        longWord3 = alu.operate(7,longWord1,longWord2);
        System.out.println(longWord3.toString() + " " + alu.toString());

    }
}
