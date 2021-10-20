import java.util.BitSet;

/**
 * @author Yunrui Huang
 * 09/21/2021
 */
public class LongWord {

    private BitSet longWord;

    /**
     * The Constructor of the LongWord class
     * set all the bit to zero(or false)
     */
    public LongWord(){
        this.longWord = new BitSet(32);
        for (int i = 0; i < 32; i++) {
            this.longWord.set(i,false);
        }
    }

    /**
     * Set the bit of this index place to 1(or true)
     * @param i
     * The index of the bit need to set to true
     */
    public void setBit(int i){
        this.longWord.set(i,true);
    }

    /**
     * Set the bit of the place of index to the status
     * @param i
     * The index of bit need to set
     * @param status
     * the status of bit need to set(true as 1, false as 0)
     */
    private void setBit(int i,boolean status){
        this.longWord.set(i,status);
    }

    /**
     * return the bit of the place of index
     * @param i
     * The index of bit that need to define status
     * @return
     * The status of bit in this index
     */
    public boolean getBit(int i){
        return this.longWord.get(i);
    }

    /**
     * Set the status of the bit at this index place to 0(or false)
     * @param i
     * The index of bit need to set to 0
     */
    public void clearBit(int i){
        this.longWord.set(i,false);
    }

    /**
     * flips the bit of the index place
     * @param i
     * The index of the bit that need to flip
     */
    public void toggleBit(int i){

        if(this.longWord.get(i)){
            this.longWord.set(i,false);
        }else{
            this.longWord.set(i,true);
        }
    }

    /**
     * Set the value of the bits of this long word (used for testing only)
     * @param value
     * an integer number for set to Binary
     */
    public void set(int value){
        /*
        if(value == 1){
            for (int i = 0; i < 32; i++) {
                this.longWord.set(i,true);
            }
        }else if(value == 0){
            for (int i = 0; i < 32; i++) {
                this.longWord.set(i,false);
            }
        }
         */

        if(value>=0){
            for (int i = 31; i >= 0; i--) {
                this.longWord.set(i,false);
                if(value >= Math.pow(2,i)){
                    value = value - (int)Math.pow(2,i);
                    this.longWord.set(i,true);
                }
            }
        }else{
            this.longWord.set(31);
            value = value + 2147483647 + 1;
            for (int i = 30; i >= 0; i--) {
                this.longWord.set(i,false);
                if(value >= Math.pow(2,i)){
                    value = value - (int)Math.pow(2,i);
                    this.longWord.set(i,true);
                }
            }
        }


    }

    /**
     * Copies the values of the bit from another LongWord into this one
     * @param other
     * The another LongWord that used to copy
     */
    public void copy(LongWord other){
        for (int i = 0; i < 32; i++) {
            this.longWord.set(i,other.getBit(i));
        }
    }


    /**
     * left-shift this LongWord by amount bit (padding with 0's)
     * Creates a new LongWord
     * @param amount
     * the number of bits need to shift
     * @return
     * The new LongWord after shift
     */
    public LongWord shiftLeftLogical(int amount){
        LongWord leftLongWord = new LongWord();
        leftLongWord.copy(this);
        for(int i=0; i < amount; i++){
            for(int a = 31; a > 0; a--){
                leftLongWord.setBit(a,leftLongWord.getBit(a-1));
            }
            leftLongWord.clearBit(0);
        }
        return leftLongWord;
    }

    /**
     * Right-shift this LongWord by amount bits (padding with 0')
     * Creates a new LongWord
     * @param amount
     * the number of bits need to shift
     * @return
     * The new LongWord after shift
     */
    public LongWord shiftRightLogical(int amount){
        LongWord rightLongWord = new LongWord();
        rightLongWord.copy(this);
        for(int i=0; i < amount; i++){
            for(int a = 0; a < 31; a++){
                rightLongWord.setBit(a,rightLongWord.getBit(a+1));
            }
            rightLongWord.clearBit(31);
        }
        return rightLongWord;
    }

    /**
     * Right-shift this LongWord by amount bits (sign-extending)
     * Creates a new LongWord
     * @param amount
     * the number of bit need to shift
     * @return
     * The new LongWord after shift
     */
    public LongWord shiftRightArithmetic(int amount){
        LongWord newLongWord = new LongWord();
        newLongWord.copy(this);
        for(int i=0; i < amount; i++){
            for(int a = 0; a < 31; a++){
                newLongWord.setBit(a,newLongWord.getBit(a+1));
            }
            //newLongWord.clearBit(30);
        }
        return newLongWord;
    }

    /**
     * negate this LongWOrd, creating a new LongWord
     * @return
     * the new LongWord after negate
     */
    public LongWord not(){
        LongWord notLongWord = new LongWord();
        notLongWord.copy(this);
        for (int i = 0; i < 32; i++) {
            notLongWord.toggleBit(i);
        }

        return notLongWord;
    }

    /**
     * And two LongWord, creating a new LongWord
     * @param other
     * another LongWord use for and
     * @return
     * the new LongWord after two LongWord be And
     */
    public LongWord and(LongWord other){
        LongWord andLongWord = new LongWord();
        andLongWord.copy(this);
        for (int i = 0; i < 32; i++) {
            if(andLongWord.getBit(i) && other.getBit(i)){
                andLongWord.setBit(i);
            }else{
                andLongWord.clearBit(i);
            }
        }
        return andLongWord;
    }

    /**
     * or two LongWord, creating a new LongWord
     * @param other
     * Another LongWord use for or
     * @return
     * the new LongWord after two LongWord be Or
     */
    public LongWord or(LongWord other){
        LongWord orLongWord = new LongWord();
        orLongWord.copy(this);
        for (int i = 0; i < 32; i++) {
            if(orLongWord.getBit(i) || other.getBit(i)){
                orLongWord.setBit(i);
            }else{
                orLongWord.clearBit(i);
            }
        }
        return orLongWord;
    }

    /**
     * Xor two LongWords,creating a new LongWord
     * @param other
     * Another LongWord use for Xor
     * @return
     * the new LongWord after two LongWord be Xor
     */
    public LongWord xor(LongWord other){
        LongWord xorLongWord = new LongWord();
        xorLongWord.copy(this);
        for (int i = 0; i < 32; i++) {
            if(xorLongWord.getBit(i) != other.getBit(i)){
                xorLongWord.setBit(i);
            }else{
                xorLongWord.clearBit(i);
            }
        }
        return xorLongWord;
    }

    /**
     * define this LongWord equal zero or not
     * @return
     * return true if equal zero, else return false
     */
    public boolean isZeor(){
        if(this.getSigned() == 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * override the toString method to print the binary and hex digits
     * @return
     * A string with binary, hex and Signed dec
     */
    public String toString(){
        String bit = "";
        for (int i = 31; i >= 0; i--) {
            if(this.longWord.get(i)){
                bit = bit + "1";
            }else{
                bit = bit + "0";
            }
            if( i%4 == 0){
                bit = bit + " ";
            }
        }
        return bit + "\t" + bitToHex() + " getSigned : " + getSigned();
    }

    /**
     * process the binary to hexadecimal
     * @return
     * The string begin with 0x
     */
    private String bitToHex(){
        String hac = "0x";
        for (int i = 32; i > 0; i=i-4) {
            int dec = 0;
            for(int a=i-4; a < i; a++){
                if(this.longWord.get(a)){
                    dec = (int) (dec + Math.pow(2,4-(i-a)));
                }
            }
            if(dec == 10){
                hac = hac + "A";
            }else if(dec == 11){
                hac = hac + "B";
            }else if(dec == 12){
                hac = hac + "C";
            }else if(dec == 13){
                hac = hac + "D";
            }else if(dec == 14){
                hac = hac + "E";
            }else if(dec == 15){
                hac = hac + "F";
            }else {
                hac = hac + dec;
            }
            //System.out.println(dec +" " + hac);
        }
        return hac;
    }

    /**
     * process the binary to signed integer
     * @return
     * the integer of this LongWord
     */
    public int getSigned(){
        int dec = 0;
        for (int i = 0; i < 31; i++) {
            if(this.longWord.get(i)){
                dec = (int) (dec + Math.pow(2,i));
            }
        }

        if(this.longWord.get(31)){
            dec = dec - 2147483647 - 1;
        }

        return dec;
    }

    /**
     * process the binary to unsigned long int
     * @return
     * the long int of this LongWord
     */
    public long getUnsigned(){
        long dec = 0;
        for (int i = 0; i < 32; i++) {
            if(this.longWord.get(i)){
                dec = dec + (long)Math.pow(2,i);
            }
        }
        return dec;
    }

}
