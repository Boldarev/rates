
import java.io.BufferedReader;
import java.io.File;
import com.google.common.base.CharMatcher;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFileLineByLine implements ReadFile {
        double totalSum = 0;


    // построчное считывание файла https://javadevblog.com/kak-postrochno-schity-vat-fajl-v-java.html

    //////////////////////////// Method returnDouble to get average buy rate
    public double returnBuySellRate() {
        try {
            String fileLocation = "C:\\Users\\Administrator\\Desktop\\curs2.txt";
            File file = new File(fileLocation);
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();

            //  create array to store all buy rates
            ArrayList<Double> rates = new ArrayList<Double>();

            // go through each line of the file, if a line has string buy-rate\ and regex ">\d.\d{4} add to the lines array
            while (line != null) {
                if (line.contains("buy-rate\">")) {
                    Pattern pattern = Pattern.compile("\">\\d.\\d{4}");
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        String buyCourse = line.substring(matcher.start(), matcher.end());
                        String cleanBuyCourse = CharMatcher.inRange('0', '9').retainFrom(buyCourse);

                        // add a dot after the first digit
                        StringBuffer sb = new StringBuffer(cleanBuyCourse);
                        sb.insert(1, ".");

                        // convert String buffer to double and add double to the array
                        double rate = Double.parseDouble(sb.toString());
                        System.out.println(rate);
                        rates.add(rate);


                    }
                }


                // считываем остальные строки в цикле while
                line = reader.readLine();


            }
            // get total sum of buying rates

            int length = rates.size();
            for (int i=0;i<length;i=i+2){
                totalSum = totalSum + rates.get(i);


//
            }
            double halfRates = rates.size()/2;
            totalSum = totalSum / halfRates;
            System.out.println(totalSum);





        }

         catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
            }
          return  totalSum;


    }

    //////////////////////////// Method to get average sell rate not implemented yet



    }


