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

        longWord3 = alu.rippleCarryAdd(longWord1,longWord2,false);
        System.out.println(longWord3.toString());
        //System.out.println("2: " + longWord2.toString());

        longWord3 = alu.rippleCarryAdd(longWord1,longWord2,true);
        System.out.println(longWord3.toString());

        //System.out.println("3: " + longWord2.toString());
        longWord1 = alu.rippleCarryAdd(longWord3,longWord2,false);
        System.out.println(longWord1.toString() + " " + alu.getCF() + " " + alu.getOF());


    }
}
