package by.tomP.parserIMDB;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Parser
{
    public String[] readLines(String filename) throws IOException
    {
        FileReader fileReader = new FileReader(filename);

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;
        Pattern pattern =  Pattern.compile("/^[a-z]{2}[0-9]{7}$/");

        while ((line = bufferedReader.readLine()) != null)
        {


            lines.add(line.substring(0,line.indexOf('\t')));
        }

        bufferedReader.close();

        return lines.toArray(new String[lines.size()]);
    }
}
