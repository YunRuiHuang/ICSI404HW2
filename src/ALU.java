/**
 * @author Yunrui Huang
 * 11/2/2021
 */

public class ALU {

    /**
     * four status flags
     */
    private Boolean ZF; //set true if all bit is 0
    private Boolean NF; //set true if negative
    private Boolean CF; //set true if last bit carry-out is 1
    private Boolean OF; //set true if overflow


    /**
     * The constructor of the ALU class
     * set all the status flags to false(0)
     */
    public ALU(){
        this.ZF = false;
        this.NF = false;
        this.CF = false;
        this.OF = false;
    }

    /**
     * get the status of ZF flag
     * @return
     * if true return 1, false return 0
     */
    public int getZF(){
        return this.ZF?1:0;
    }

    /**
     * get the status of NF flag
     * @return
     * if true return 1, false return 0
     */
    public int getNF(){
        return this.NF?1:0;
    }

    /**
     * get the status of CF flag
     * @return
     * if true return 1, false return 0
     */
    public int getCF(){
        return this.CF?1:0;
    }

    /**
     * get the status of OF flag
     * @return
     * if true return 1, false return 0
     */
    public int getOF(){
        return this.OF?1:0;
    }

    /**
     * the only method to control the operate
     * @param code
     * the code of what operate to do base on the table
     * @param op1
     * the first LongWord to operate
     * @param op2
     * the second LongWord to operate
     * @return
     * the LongWord after operate
     */
    public LongWord operate(int code, LongWord op1, LongWord op2){
        if(code == 0){
            return and(op1,op2);
        }else if(code == 1){
            return or(op1,op2);
        }else if(code == 2){
            return xor(op1,op2);
        }else if(code == 3){
            return add(op1,op2);
        }else if(code == 4){
            return sub(op1,op2);
        }else if(code == 5){
            return sll(op1,op2);
        }else if(code == 6){
            return srl(op1,op2);
        }else if(code == 7){
            return sra(op1,op2);
        }else{
            return null;
        }
    }

    /**
     * to add or subtraction two LongWord, dependent on cin
     * @param a
     * the first LongWord to be add or subtraction
     * @param holderb
     * the second LongWord to add or to subtraction to first LongWord
     * @param cin
     * If cin as true then subtraction, false then add
     * @return
     * the LongWord after add or subtraction
     */
    private LongWord rippleCarryAdd(LongWord a, LongWord holderb, Boolean cin){
        LongWord longWord = new LongWord();

        LongWord b = new LongWord();
        b.copy(holderb);

        //cin == true is subtraction
        //cin == false is addition
        if(cin){
            for(int i = 0; i<32; i++){
                b.toggleBit(i);
            }
            LongWord one = new LongWord();
            one.setBit(0);
            b = rippleCarryAdd(b,one,false);
        }

        boolean carryOut = false;
        boolean carrythirthfirst = false;
        for(int i=0; i<32; i++){
            if(a.getBit(i) && b.getBit(i)){
                if(carryOut){
                    longWord.setBit(i);
                    carryOut = true;
                }else{
                    longWord.clearBit(i);
                    carryOut = true;
                }
            }else if(a.getBit(i) || b.getBit(i)){
                if(carryOut){
                    longWord.clearBit(i);
                    carryOut = true;
                }else{
                    longWord.setBit(i);
                    carryOut = false;
                }
            }else{
                if(carryOut){
                    longWord.setBit(i);
                    carryOut = false;
                }else{
                    longWord.clearBit(i);
                    carryOut = false;
                }
            }
            if(i == 30){
                carrythirthfirst = carryOut;
            }
        }
        this.CF = carryOut;
        if(carryOut != carrythirthfirst){
            this.OF = true;
        }else{
            this.OF = false;
        }
        return longWord;
    }

    /**
     * And two LongWord, return a new LongWord
     * @param op1
     * the first LongWord to operate
     * @param op2
     * the second LongWord to operate
     * @return
     * the LongWord after operate
     */
    private LongWord and(LongWord op1, LongWord op2){
        LongWord longWord = new LongWord();
        longWord.copy(op1);
        longWord = longWord.and(op2);
        this.NF = longWord.getBit(31);
        this.ZF = longWord.isZeor();
        this.CF = false;
        this.OF = false;
        return longWord;
    }

    /**
     * Or two LongWord, return a new LongWord
     * @param op1
     * the first LongWord to operate
     * @param op2
     * the second LongWord to operate
     * @return
     * the LongWord after operate
     */
    private LongWord or(LongWord op1, LongWord op2){
        LongWord longWord = new LongWord();
        longWord.copy(op1);
        longWord = longWord.or(op2);
        this.NF = longWord.getBit(31);
        this.ZF = longWord.isZeor();
        this.CF = false;
        this.OF = false;
        return longWord;
    }

    /**
     * Xor two LongWord, return a new LongWord
     * @param op1
     * the first LongWord to operate
     * @param op2
     * the second LongWord to operate
     * @return
     * the LongWord after operate
     */
    private LongWord xor(LongWord op1, LongWord op2){
        LongWord longWord = new LongWord();
        longWord.copy(op1);
        longWord = longWord.xor(op2);
        this.NF = longWord.getBit(31);
        this.ZF = longWord.isZeor();
        this.CF = false;
        this.OF = false;
        return longWord;
    }

    /**
     * Add two LongWord, return a new LongWord
     * @param op1
     * the first LongWord to operate
     * @param op2
     * the second LongWord to operate
     * @return
     * the LongWord after operate
     */
    private LongWord add(LongWord op1, LongWord op2){
        LongWord longWord = new LongWord();
        longWord = rippleCarryAdd(op1,op2,false);
        this.NF = longWord.getBit(31);
        this.ZF = longWord.isZeor();
        return longWord;
    }

    /**
     * Subtract two LongWord, return a new LongWord
     * @param op1
     * the first LongWord to operate
     * @param op2
     * the second LongWord to operate
     * @return
     * the LongWord after operate
     */
    private LongWord sub(LongWord op1, LongWord op2){
        LongWord longWord = new LongWord();
        longWord = rippleCarryAdd(op1,op2,true);
        this.NF = longWord.getBit(31);
        this.ZF = longWord.isZeor();
        return longWord;
    }

    /**
     * left-shift this LongWord by amount bit (padding with 0's)
     * @param op1
     * the first LongWord to operate
     * @param op2
     * the amount of bit need to shift, in signed
     * @return
     * the LongWord after operate
     */
    private LongWord sll(LongWord op1, LongWord op2){
        LongWord longWord = new LongWord();
        longWord.copy(op1);
        longWord = longWord.shiftLeftLogical(op2.getSigned());
        this.NF = longWord.getBit(31);
        this.ZF = longWord.isZeor();
        this.CF = false;
        if(op2.getSigned() == 1){
            if(longWord.getBit(31) != op1.getBit(31)){
                this.OF = true;
            }else{
                this.OF = false;
            }
        }else{
            this.OF = false;
        }
        return longWord;
    }

    /**
     * Right-shift this LongWord by amount bits (padding with 0')
     * @param op1
     * the first LongWord to operate
     * @param op2
     * the amount of bit need to shift, in signed
     * @return
     * the LongWord after operate
     */
    private LongWord srl(LongWord op1, LongWord op2){
        LongWord longWord = new LongWord();
        longWord.copy(op1);
        longWord = longWord.shiftRightLogical(op2.getSigned());
        this.NF = longWord.getBit(31);
        this.ZF = longWord.isZeor();
        this.CF = false;
        this.OF = false;
        return longWord;
    }

    /**
     * Right-shift this LongWord by amount bits (sign-extending)
     * @param op1
     * the first LongWord to operate
     * @param op2
     * the amount of bit need to shift, in signed
     * @return
     * the LongWord after operate
     */
    private LongWord sra(LongWord op1, LongWord op2){
        LongWord longWord = new LongWord();
        longWord.copy(op1);
        longWord = longWord.shiftRightArithmetic(op2.getSigned());
        this.NF = longWord.getBit(31);
        this.ZF = longWord.isZeor();
        this.CF = false;
        this.OF = false;
        return longWord;
    }

    /**
     * override the toString method to print all four status flags
     * @return
     * A string with all four status flags
     */
    public String toString(){
        return "ZF: " + getZF() + "  NF: " + getNF() + "  CF: " + getCF() + "  OF: " + getOF();
    }
}
