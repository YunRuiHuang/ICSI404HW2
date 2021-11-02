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
}
