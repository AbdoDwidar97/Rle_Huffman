import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Compression
{
    private ArrayList<Discriptor> discriptors;
    private ArrayList<Integer> Additional_numbers;
    private ArrayList<TableItem> table;
    private String CompressedData = "";

    public Compression(String data)
    {
        TableBuilder tableBuilder = new TableBuilder(data);
        this.table = tableBuilder.Build_table();
        this.discriptors = tableBuilder.getDiscriptors();
        this.Additional_numbers = tableBuilder.getAdditional_Numbers();
    }


    public String getCompressedData()
    {
        return CompressedData;
    }

    public ArrayList<TableItem> getTable() {
        return table;
    }

    public void compress ()
    {
        int lnth = this.discriptors.size(); // Hint : number of discriptors equals number of Additional numbers.
        for (int i=0; i < lnth; i++)
        {
            Discriptor currDisc = this.discriptors.get(i);
            int additional_number = this.Additional_numbers.get(i);

            String DiscriptorCode = getCode(currDisc);
            String AdditionalNumber_Code = "";
            if (additional_number < 0)
            {
                additional_number *= -1;
                AdditionalNumber_Code = Integer.toBinaryString(additional_number);
                AdditionalNumber_Code = ConvertStream(AdditionalNumber_Code);

            }else
            {
                AdditionalNumber_Code = Integer.toBinaryString(additional_number);
            }

            this.CompressedData += DiscriptorCode + AdditionalNumber_Code;
        }

        WriteToFile();
    }

    private String getCode (Discriptor disc)
    {
        for (TableItem tableItem : this.table)
        {
            Discriptor currentDisc = tableItem.getDiscriptor();
            if (disc.getNumberOfZeroz() == currentDisc.getNumberOfZeroz() &&
                    disc.getNumberOfAdditionalBits() == currentDisc.getNumberOfAdditionalBits())
            {
                return tableItem.getCode();
            }
        }

        return "";
    }

    private String ConvertStream (String cde)
    {
        String Converted_Code = "";
        for (int i=0; i < cde.length(); i++)
        {
            if (cde.charAt(i) == '0') Converted_Code += '1';
            else Converted_Code += '0';
        }
        return Converted_Code;
    }


    private void WriteToFile ()
    {
        try
        {
            File myObj = new File("CompressedData.txt");
            if (myObj.createNewFile())
            {
                FileWriter myWriter = new FileWriter("CompressedData.txt");
                for (TableItem t : this.table)
                {
                    myWriter.write(t.discriptor.getNumberOfZeroz() + "/" + t.discriptor.getNumberOfAdditionalBits() + " " + t.Code + "\n");
                }
                myWriter.write("~" + "\n");
                myWriter.write(this.CompressedData);
                myWriter.close();
            } else
            {
                System.out.println("File already exists.");
            }

        } catch (IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
