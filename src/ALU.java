public class ALU {
    private Boolean ZF; //set true if all bit is 0
    private Boolean NF; //set true if negative
    private Boolean CF; //set true if last bit carry-out is 1
    private Boolean OF; //set true if overflow
    private LongWord longWord;


    public ALU(){
        this.ZF = false;
        this.NF = false;
        this.CF = false;
        this.OF = false;
        this.longWord = new LongWord();
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

    public LongWord rippleCarryAdd(LongWord a, LongWord holderb, Boolean cin){
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
                    this.longWord.setBit(i);
                    carryOut = true;
                }else{
                    this.longWord.clearBit(i);
                    carryOut = true;
                }
            }else if(a.getBit(i) || b.getBit(i)){
                if(carryOut){
                    this.longWord.clearBit(i);
                    carryOut = true;
                }else{
                    this.longWord.setBit(i);
                    carryOut = false;
                }
            }else{
                if(carryOut){
                    this.longWord.setBit(i);
                    carryOut = false;
                }else{
                    this.longWord.clearBit(i);
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
        return this.longWord;
    }


}
