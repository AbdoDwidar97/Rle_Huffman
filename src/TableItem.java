public class TableItem
{
    Discriptor discriptor;
    String Code;

    public TableItem(){}

    public TableItem (Discriptor dis, String code)
    {
        this.discriptor = dis;
        this.Code = code;
    }

    public Discriptor getDiscriptor() {
        return discriptor;
    }

    public void setDiscriptor(Discriptor discriptor) {
        this.discriptor = discriptor;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }


    @Override
    public String toString() {
        return ("Discriptor : "+ this.discriptor.getNumberOfZeroz() + "/" + this.discriptor.getNumberOfAdditionalBits() + " ,Code : " + this.Code);
    }
}
