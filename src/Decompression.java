import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Decompression
{
    private ArrayList<TableItem> table;
    private String CompressedData;
    private String Decompressed_Data = "";

    public Decompression ()
    {
        this.table = new ArrayList<>();
        ReadFromFile();
    }

    public String getDecompressed_Data() {
        return Decompressed_Data;
    }

    public void decompress ()
    {
        int lnth = this.CompressedData.length();
        String bfr = "";
        for (int i=0; i < lnth; i++)
        {
            bfr += this.CompressedData.charAt(i);
            Discriptor disc = GetDiscriptor(bfr);
            if (disc != null)
            {
                for (int z=0; z < disc.getNumberOfZeroz(); z++) Decompressed_Data += "0 ";
                int n = disc.getNumberOfAdditionalBits();
                String AdditionalNumberStream = "";

                while (n > 0)
                {
                    i++;
                    AdditionalNumberStream += this.CompressedData.charAt(i);
                    n--;
                }

                int AdditionalNumber;
                if (AdditionalNumberStream.charAt(0) == '0')
                {
                    AdditionalNumberStream = ConvertStream(AdditionalNumberStream); // if this number is -ve ...
                    AdditionalNumber = Integer.parseInt(AdditionalNumberStream, 2);
                    AdditionalNumber *= -1;

                }else
                {
                    AdditionalNumber = Integer.parseInt(AdditionalNumberStream, 2);
                }

                this.Decompressed_Data += AdditionalNumber + " ";
                bfr = "";
            }
        }
    }


    private Discriptor GetDiscriptor (String code)
    {
        for (TableItem tableItem : this.table)
        {
            Discriptor currDisc = tableItem.getDiscriptor();
            if (code.equals(tableItem.getCode())) return currDisc;
        }
        return null;
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

    private void ReadFromFile ()
    {
        try
        {
            File myObj = new File("CompressedData.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine())
            {
                String data = myReader.nextLine();
                while (!data.equals("~"))
                {
                    String [] tbleItm = data.split(" ");
                    String [] disc = tbleItm[0].split("/");
                    int Nunzero = Integer.parseInt(disc[0]);
                    int AdditionalNumber = Integer.parseInt(disc[1]);
                    table.add(new TableItem(new Discriptor(Nunzero, AdditionalNumber),tbleItm[1]));
                    data = myReader.nextLine();
                }
                this.CompressedData = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
        }

    }
}
