public class ALU {
    private Boolean ZF; //set true if all bit is 0
    private Boolean NF; //set true if negative
    private Boolean CF; //set true if last bit carry-out is 1
    private Boolean OF; //set true if overflow


    public ALU(){
        this.ZF = false;
        this.NF = false;
        this.CF = false;
        this.OF = false;
        //LongWord longWord = new LongWord();
    }

    public int getZF(){
        return this.ZF?1:0;
    }

    public int getNF(){
        return this.NF?1:0;
    }

    public int getCF(){
        return this.CF?1:0;
    }

    public int getOF(){
        return this.OF?1:0;
    }

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

    private LongWord add(LongWord op1, LongWord op2){
        LongWord longWord = new LongWord();
        longWord = rippleCarryAdd(op1,op2,false);
        this.NF = longWord.getBit(31);
        this.ZF = longWord.isZeor();
        return longWord;
    }

    private LongWord sub(LongWord op1, LongWord op2){
        LongWord longWord = new LongWord();
        longWord = rippleCarryAdd(op1,op2,true);
        this.NF = longWord.getBit(31);
        this.ZF = longWord.isZeor();
        return longWord;
    }

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

    public String toString(){
        return "ZF: " + getZF() + "  NF: " + getNF() + "  CF: " + getCF() + "  OF: " + getOF();
    }
}
