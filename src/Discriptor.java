public class Discriptor
{
    int NumberOfZeroz = 0;
    int NumberOfAdditionalBits = 0;

    public Discriptor (){}

    public Discriptor (int num_zeroz, int num_bits)
    {
        this.NumberOfZeroz = num_zeroz;
        this.NumberOfAdditionalBits = num_bits;
    }

    public int getNumberOfZeroz() {
        return NumberOfZeroz;
    }

    public void setNumberOfZeroz(int numberOfZeroz) {
        NumberOfZeroz = numberOfZeroz;
    }

    public int getNumberOfAdditionalBits() {
        return NumberOfAdditionalBits;
    }

    public void setNumberOfAdditionalBits(int numberOfAdditionalBits) {
        NumberOfAdditionalBits = numberOfAdditionalBits;
    }

    @Override
    public String toString() {
        return (this.getNumberOfZeroz() + "/" + this.getNumberOfAdditionalBits());
    }
}
