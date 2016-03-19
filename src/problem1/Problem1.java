package problem1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author melika barzegaran hosseini
 */
public class Problem1
{
    private String path;
    private boolean detailedPrinting;

    public Problem1(String path)
    {
        this.path = path;
        detailedPrinting = false;
    }

    public void enableDetailedPrinting()
    {
        detailedPrinting = true;
    }

    public void disableDetailedPrinting()
    {
        detailedPrinting = false;
    }

    private Float[] read(String path)
    {
        if(path == null || path.isEmpty())
        {
            System.err.println("error: the path provided is null or empty.");
            System.exit(1);
        }

        BufferedReader reader = null;
        Float[] input = null;
        try
        {
            reader = new BufferedReader(new FileReader(path));

            String line;
            Integer n;
            if((line = reader.readLine()) != null && !line.isEmpty())
            {
                try
                {
                    n = Integer.parseInt(line);
                    if(n <= 0)
                    {
                        throw new NumberFormatException();
                    }
                }
                catch (NumberFormatException e)
                {
                    throw new Exception("error: the first line of the file '" + path + "' is not structured properly" +
                            ".\n it must represent the number of days.\n it must be a positive integer number with " +
                            "the max value of '" + Integer.MAX_VALUE + "'.");
                }
            }
            else
            {
                throw new Exception("error: the first line of the file '" + path + "' is null or empty.\n it must " +
                        "represent the number of days.");
            }

            if(detailedPrinting)
            {
                System.out.println("the number of days = " + n);
            }

            input = new Float[n];

            if((line = reader.readLine()) != null && !line.isEmpty())
            {
                String[] tokens = line.trim().split("\\s+");
                if(tokens.length == n)
                {
                    try
                    {
                        for(int i = 0; i < n; i++)
                        {
                            input[i] = Float.parseFloat(tokens[i]);
                        }
                    }
                    catch (NumberFormatException e)
                    {
                        throw new Exception("error: the second line of the file '" + path + "' is not structured " +
                                "properly.\n it must represent some data in the form of a sequence of stock changes" +
                                ".\n each value representing a change must be a float number with the min value of '"
                                + Float.MIN_VALUE + "' and the max value of '" + Float.MAX_VALUE + "'.");
                    }
                }
                else
                {
                    throw new Exception("error: the second line of the file '" + path + "' is not structured properly" +
                            ".\n it must represent some data in the form a sequence of stock changes.");
                }
            }
            else
            {
                throw new Exception("error: the second line of the file '" + path + "' is null or empty.\n it must " +
                        "represent some data.");
            }

            if(detailedPrinting)
            {
                StringBuilder log = new StringBuilder("the stock changes = ");
                for(Float value : input)
                {
                    log.append(value).append(", ");
                }
                log.setLength(log.length() - 2);
                System.out.println(log);
            }
        }
        catch (FileNotFoundException e)
        {
            System.err.println("error: the file '" + path + "' doesn't exist or is a directory.");
            System.exit(1);
        }
        catch (IOException e)
        {
            System.err.println("error: an error occurred while reading from the file '" + path + "'.");
            System.exit(1);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        finally
        {
            try
            {
                if(reader != null)
                {
                    reader.close();
                }
            }
            catch (IOException e)
            {
                System.err.println("error: an error occurred while closing the file '" + path + "'.");
                System.exit(1);
            }
        }

        return input;
    }

    private Output solve(Float[] input)
    {
        return null;
    }

    public void solve()
    {
        Float[] input = read(path);
        Output output = solve(input);
        if(detailedPrinting)
        {
            System.out.println(output);
        }
        else
        {
            System.out.println(output.getProfit());
            System.out.println(output.getBuyingDay() + ", " + output.getSellingDay());
        }
    }

    private class Output
    {
        private Float profit;
        private Integer buyingDay;
        private Integer sellingDay;

        public Output(Float profit, Integer buyingDay, Integer sellingDay)
        {
            this.profit = profit;
            this.buyingDay = buyingDay;
            this.sellingDay = sellingDay;
        }

        public Float getProfit()
        {
            return profit;
        }

        public Integer getBuyingDay()
        {
            return buyingDay;
        }

        public Integer getSellingDay()
        {
            return sellingDay;
        }

        @Override
        public String toString()
        {
            return "the output = (profit=" + profit + "), (buyingDay=" + buyingDay + "), (sellingDay=" + sellingDay +
                    ")";
        }
    }

    public static void main(String args[])
    {
        if(args.length == 1)
        {
            Problem1 problem = new Problem1(args[0]);
            problem.enableDetailedPrinting();
            problem.solve();
        }
        else
        {
            System.err.println("error: the input is invalid. it should be the path of the input file.");
        }
    }
}