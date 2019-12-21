import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Rle_HuffmanPlatform {

    private JTextField TxtFileNameCompress;
    private JButton BtnCompress;
    private JTextField TxtFileNameDecompress;
    private JButton BtnDecompress;
    private JPanel jpanel;



    public Rle_HuffmanPlatform()
    {
        BtnCompress.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                String Data = ReadFromFile(TxtFileNameCompress.getText());
                //String Data = "-2 0 0 2 0 0 3 2 0 1 0 0 -2 0 -1 0 0 1 0 0 -1 0 0 0 0 0 0 9";
                Compression compression = new Compression(Data);
                compression.compress();

                JOptionPane.showMessageDialog(null,"File compressed successfully !");
            }
        });


        BtnDecompress.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                Decompression decompression = new Decompression();
                decompression.decompress();
                String decomp = decompression.getDecompressed_Data();
                WriteToFile(decomp);
                JOptionPane.showMessageDialog(null,"File Decompressed successfully !");
            }
        });

    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    private String ReadFromFile (String fname)
    {
        try
        {
            File myObj = new File(fname);
            Scanner myReader = new Scanner(myObj);
            return myReader.nextLine();

        } catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
        }
        return "";
    }

    private void WriteToFile (String decomp)
    {
        try
        {
            File myObj = new File("DecompressedData.txt");
            if (myObj.createNewFile())
            {
                FileWriter myWriter = new FileWriter("DecompressedData.txt");
                myWriter.write(decomp);
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



    public static void main(String[] args){
        JFrame jfrm = new JFrame("Rle_HuffmanPlatform");
        jfrm.setContentPane(new Rle_HuffmanPlatform().jpanel);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.pack();
        jfrm.setVisible(true);
    }

}
