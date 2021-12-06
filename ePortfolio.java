package ePortfolio;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.attribute.Size2DSyntax;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;


import javax.swing.*;
import java.awt.*;

import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.io.File;

import java.io.FileInputStream;

import java.io.IOException;



public class ePortfolio extends JFrame{


    public ePortfolio()
    {
        

    }


    double commision = 9.99;
    public static Scanner sc = new Scanner(System.in);
    
    /** 
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws IOException  
    {
        
        
         ArrayList<investment> investment_Array = new ArrayList<>();
        HashMap<String, ArrayList<Integer>> index = new HashMap<String, ArrayList<Integer>>();
        ArrayList<Integer> value = new ArrayList<Integer>();

        investment stock = new investment();
        investment mutualfunds = new investment();
        ePortfolio obj = new ePortfolio();
        String filename = "";

        ePortfolioPanel gui = new ePortfolioPanel();
        
        

        int x = 0;
        while(x == 0)
        {
                
                System.out.println("Enter a command \n1. buy\n2. sell\n3. update \n4. getGain \n5. search \n6. quit\n");
                String userInput = sc.nextLine();  
                           
            
            System.out.println(userInput);
            if(userInput.equals("buy") || userInput.equals("Buy"))
            {
                System.out.println("Enter '1' to buy manually or '2' to load from file");
                String buy_method = sc.nextLine(); 
                
                if(buy_method.equals("1"))
                {

                        // getting user input for type of investment and and the symbol
                        System.out.println("Enter the kind of investment followed by symbol \n1. stock \n2.mutualfunds");
                        String buyOption_input = sc.nextLine();
                        String [] Strings = buyOption_input.split(" "); // splitting investment from symbol

                        if(Strings.length == 2)
                        {
                        String buyOption = Strings[0];
                        String symbol = Strings[1];
                        
                        if (buyOption.equals("stock") || buyOption.equals("Stock"))
                        {
                            
                            // checking if if symbol exist 
                            if(stock.check_exist_investment(investment_Array, symbol))
                            {
                                if(!(mutualfunds.check_exist_investment(investment_Array, symbol)))
                                {

                                

                                
                                System.out.println("\n");
                                //stock.diplay_stock_attribute(investment_array, symbol, stock);
                                System.out.println("\n");

                                System.out.println("you have already invested in this stock, would you like to invest more?(Yes/No) \n");
                                
                                String invest_more = sc.nextLine();

                                if(invest_more.equals("Yes") || invest_more.equals("yes") || invest_more.equals("y"))
                                {
                                    //updating price
                                    System.out.println("Enter price \n");
                                    double new_price = sc.nextDouble();
                                    stock.setPrice(new_price);

                                    //updating quantity
                                    System.out.println("Enter quantity \n");
                                    int new_quantity = sc.nextInt();
                                    stock.setQuantity(new_quantity);

                                    

                                    // go to investment and update BOOkValue
                                    for(investment d : investment_Array)
                                    {
                                        if(d.getSybmol() != null && d.getSybmol().contains(symbol))
                                        {
                                            d.Price = new_price;
                                            stock.setPrice(new_price);
                                            d.Quantity += new_quantity;
                                            

                                            double current_bookValue = d.getBookValue();
                                            

                                            double stock_ADD_bv = d.compute_bookValue(stock);
                                            System.out.println("compute ; " + stock_ADD_bv);
                                            double final_BookValue = current_bookValue + stock_ADD_bv;
                                            System.out.println("final compute0 ; " + current_bookValue);
                                            System.out.println("final compute ; " + final_BookValue);

                                            //.setBookValue(final_BookValue);
                                            d.setBookValue(final_BookValue);
                                            stock.setQuantity(d.Quantity);   

                                        }
                                    }
                                    
                                }          
                            }
                            else
                            {
                                System.out.println("Symbol Already exist in mutualfunds, try a diffrenet one!");
                                continue;
                            }
                            }
                            else
                            {
                                stock.setSymbol(symbol);
                                

                                System.out.println("Enter a name:\n");
                                String invest_name = sc.nextLine();
                                stock.setName(invest_name);

                                

                                System.out.println("Enter a quantity:\n");
                                int quan = sc.nextInt();
                                stock.setQuantity(quan);
                            

                                System.out.println("Enter a price:\n");
                                double price = sc.nextDouble();
                                stock.setPrice(price);

                                double stock_bv = stock.compute_bookValue(stock);
                                stock.setBookValue(stock_bv);

                                double stock_gain = 0.00;
                                
                                investment_Array.add(new stock(symbol,invest_name,quan,price,stock_bv, stock_gain));

                                StringTokenizer defaultTokenizer = new StringTokenizer(invest_name);
                                while (defaultTokenizer.hasMoreTokens())
                                {
                                    String key = defaultTokenizer.nextToken();

                                    for(investment s : investment_Array)
                                    {
                                        String temp = s.getName().toLowerCase();
                                        if(temp.contains(key.toLowerCase()))
                                        {
                                                System.out.println(investment_Array.indexOf(s));
                                                index.put(key, new ArrayList<Integer>());
                                                index.get(key).add(investment_Array.indexOf(s));           
                                        }
                                    }                            
                                }


                            }

                        }
                        else if(buyOption.equals("mutualfunds") || buyOption.equals("Mutualfunds"))
                        {    
                            // checking if if symbol exist 
                            if(mutualfunds.check_exist_investment(investment_Array, symbol))
                            {
                                if(!(stock.check_exist_investment(investment_Array, symbol)))
                                {    
                                System.out.println("\n");
                                
                                System.out.println("\n");

                                System.out.println("you have already invested in this mutualfunds, would you like to invest more?(Yes/No) \n");
                                
                                String invest_more = sc.nextLine();

                                if(invest_more.equals("Yes") || invest_more.equals("yes") || invest_more.equals("y"))
                                {
                                    //updating price
                                    System.out.println("Enter price \n");
                                    double new_price = sc.nextDouble();
                                    mutualfunds.setPrice(new_price);

                                    //updating quantity
                                    System.out.println("Enter quantity \n");
                                    int new_quantity = sc.nextInt();
                                    mutualfunds.setQuantity(new_quantity);

                                    // go to investment and update BOOkValue and prices and quantity
                                    for(investment d : investment_Array)
                                    {
                                        if(d.getSybmol() != null && d.getSybmol().contains(symbol))
                                        {
                                            d.Price = new_price;
                                            mutualfunds.setPrice(new_price);
                                            d.Quantity += new_quantity;
                                            

                                            double current_bookValue = d.getBookValue();
                                            double stock_ADD_bv = d.compute_bookValue(mutualfunds);
                                            double final_BookValue = current_bookValue + stock_ADD_bv;
                                            
                                            d.setBookValue(final_BookValue);
                                            mutualfunds.setBookValue(final_BookValue);
                                            mutualfunds.setQuantity(d.Quantity);   

                                        }
                                    }
                                }      
                            }
                            else
                            {
                                System.out.println("Symbol Already exist in stocks, try a diffrenet one!");
                                continue;
                            }
                            }
                            else
                            {
                                mutualfunds.setSymbol(symbol);
                                

                                System.out.println("Enter a name:\n");
                                String invest_name = sc.nextLine();
                                mutualfunds.setName(invest_name);
                                

                                System.out.println("Enter a quantity:\n");
                                int quan = sc.nextInt();
                                mutualfunds.setQuantity(quan);
                            

                                System.out.println("Enter a price:\n");
                                double price = sc.nextDouble();
                                mutualfunds.setPrice(price);

                                double stock_bv = mutualfunds.compute_bookValue(mutualfunds);
                                mutualfunds.setBookValue(stock_bv);

                                double mutualfunds_gain = 0.00;
                                
                                investment_Array.add(new mutualfunds(symbol,invest_name,quan,price,stock_bv, mutualfunds_gain));
                                
                                StringTokenizer defaultTokenizer = new StringTokenizer(invest_name);

                                while (defaultTokenizer.hasMoreTokens())
                                {
                                    String key = defaultTokenizer.nextToken();

                                    for(investment s : investment_Array)
                                    {
                                        String temp = s.getName().toLowerCase();
                                        if(temp.contains(key.toLowerCase()))
                                        {

                                            System.out.println(investment_Array.indexOf(s));
                                            index.put(key, new ArrayList<Integer>());
                                            index.get(key).add(investment_Array.indexOf(s));
   
                                        }
                                    }    
                                }

                            }

                        }
                    }
                    else
                    {
                        System.out.println("Invalid Input\n");

                    }

                }
                else if(buy_method.equals("2"))
                {
                        
                        if(args.length == 1)
                        {
                            filename = args[0];
                        }
                        else
                        {
                            System.out.println("No file to read from");
                            filename = "test.txt";

                        }
 
                        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Bahaa\\OneDrive\\Desktop\\F21\\CIS2430\\bzuraik_a1\\eportfolio\\" + filename));
                        String str;
                        String[] Attributes =new String [1000];
                        int inv_num = 0;
                        int j = 0;
                        int counter = 0;
                        int f = 0; 

                        ArrayList<String> list = new ArrayList<String>();
                        ArrayList<String> att = new ArrayList<String>();
                        
                        while((str = in.readLine()) != null){
                            list.add(str);
                            
                        }

                        String[] stringArr = list.toArray(new String[list.size()]);
                        
                        for(int i = 0 ; i < stringArr.length; i++)
                        {
                            if(stringArr[i].contains("type"))
                            {
                                counter++;
                            }
                        }
                        
                        for(int i = 0; i < stringArr.length + 2; i++)
                        {
                            if(stringArr[i].contains("type"))
                            {
                                inv_num++;
                                // System.out.println("type" + i);
                                int count = i;

                                for(j = i ; j < i + 6; j++)
                                {
                                    Pattern p = Pattern.compile("\"([^\"]*)\"");
                                    Matcher m = p.matcher(stringArr[j]);


                                    while( m.find() )
                                    {
                                        att.add(m.group(1));
                                    }
                                    
                                    Attributes = att.toArray(new String[list.size()]);
                                    
                                }


                                if(stringArr[i].contains("stock"))
                                {
                                    if(inv_num == 1)
                                    {

                                        int new_q = Integer.parseInt(Attributes[i + 3]);
                                    

                                        double new_p = Double.parseDouble(Attributes[ i + 4]);
                                        

                                        double new_bv = Double.parseDouble(Attributes[i + 5]);
                                        double gain = 0.00;
                                        investment_Array.add(new stock(Attributes[i + 1],Attributes[ i + 2],new_q,new_p,new_bv, gain));


                                        //adding to hashmap
                                        StringTokenizer defaultTokenizer = new StringTokenizer(Attributes[ i + 2]);
                                        while (defaultTokenizer.hasMoreTokens())
                                        {
                                            String key = defaultTokenizer.nextToken();

                                            for(investment s : investment_Array)
                                            {
                                                String temp = s.getName().toLowerCase();
                                                if(temp.contains(key.toLowerCase()))
                                                {
                                                    
                                                    index.put(key, new ArrayList<Integer>());
                                                    index.get(key).add(investment_Array.indexOf(s));
                                                        
                                                }
                                            }
                                            
                                        }
                                        // System.out.println("passed1 ");

                                        if(inv_num == counter)
                                        {
                                            break;
                                        }

                                    }
                                    else
                                    {


                                        int new_q = Integer.parseInt(Attributes[i + 2]);
                                    
                                        //System.out.println("p " + Attributes[i + 3]);
                                        double new_p = Double.parseDouble(Attributes[ i + 3]);
                                        //System.out.println("bv " + Attributes[i + 4]);
                                        double new_bv = Double.parseDouble(Attributes[i + 4]);
                                        double gain = 0.00;
                                        //System.out.println("lin1 " + i);

                                        investment_Array.add(new stock(Attributes[i],Attributes[ i + 1],new_q,new_p,new_bv, gain));

                                        //adding to hashmap
                                        StringTokenizer defaultTokenizer = new StringTokenizer(Attributes[ i + 1]);
                                        while (defaultTokenizer.hasMoreTokens())
                                        {
                                            String key = defaultTokenizer.nextToken();

                                            for(investment s : investment_Array)
                                            {
                                                String temp = s.getName().toLowerCase();
                                                if(temp.contains(key.toLowerCase()))
                                                {  
                                                    index.put(key, new ArrayList<Integer>());
                                                    index.get(key).add(investment_Array.indexOf(s));
        
                                                }
                                            }

                                            
                                            
                                        }

                                        if(inv_num == counter)
                                        {
                                            break;
                                        }
                                    }
                                    
                                    
                                    
                                }
                                else if(stringArr[i].contains("mutualfunds"))
                                {

                                    if(inv_num == 1)
                                    {
                                       
                                        int new_q = Integer.parseInt(Attributes[i + 3]);
                                    

                                        double new_p = Double.parseDouble(Attributes[ i + 4]);
                                        

                                        double new_bv = Double.parseDouble(Attributes[i + 5]);
                                        double gain = 0.00;
                                        investment_Array.add(new mutualfunds(Attributes[i + 1],Attributes[ i + 2],new_q,new_p,new_bv, gain));


                                        //adding to hashmap
                                        StringTokenizer defaultTokenizer = new StringTokenizer(Attributes[ i + 2]);
                                        while (defaultTokenizer.hasMoreTokens())
                                        {
                                            String key = defaultTokenizer.nextToken();

                                            for(investment s : investment_Array)
                                            {
                                                String temp = s.getName().toLowerCase();
                                                if(temp.contains(key.toLowerCase()))
                                                {
                                                    
                                                    index.put(key, new ArrayList<Integer>());
                                                    index.get(key).add(investment_Array.indexOf(s));
        
                                                }
                                            }

                                            index.put(key, value);
                                            // value.clear();
                                        }
                                        // System.out.println("passed1 ");

                                        if(inv_num == counter)
                                        {
                                            break;
                                        }    

                                    }
                                    else
                                    {
            
                                        int new_q = Integer.parseInt(Attributes[i + 2]);
                                    
                                        //System.out.println("p " + Attributes[i + 3]);
                                        double new_p = Double.parseDouble(Attributes[ i + 3]);
                                        //System.out.println("bv " + Attributes[i + 4]);
                                        double new_bv = Double.parseDouble(Attributes[i + 4]);
                                        double gain = 0.00;
                                        //System.out.println("lin1 " + i);

                                        investment_Array.add(new mutualfunds(Attributes[i],Attributes[ i + 1],new_q,new_p,new_bv, gain));

                                        //adding to hashmap
                                        StringTokenizer defaultTokenizer = new StringTokenizer(Attributes[ i + 1]);
                                        while (defaultTokenizer.hasMoreTokens())
                                        {
                                            String key = defaultTokenizer.nextToken();
                                            

                                            for(investment s : investment_Array)
                                            {
                                                String temp = s.getName().toLowerCase();
                                                if(temp.contains(key.toLowerCase()))
                                                {

                                                    
                                                    index.put(key, new ArrayList<Integer>());
                                                    index.get(key).add(investment_Array.indexOf(s));;
        
                                                }
                                            }   
                                            // value.clear();


                                        }

                                        if(inv_num == counter)
                                        {
                                            break;
                                        }
                                    }
                                }

                            }
                        }

                        
                        

                        


                }
        }
        else if(userInput.equals("sell") || userInput.equals("Sell"))
            {
                
                System.out.println("would you like to sell from 'stock' or 'mutualfunds'?\n");
                String sell_from = sc.nextLine();

                if(sell_from.equals("stock") || sell_from.equals("Stock"))
                {


                    System.out.println("Provide the symbol of which u wanto to sell: \n");
                    String symbol_sell = sc.nextLine();

                    if(stock.check_exist_investment(investment_Array, symbol_sell))
                    {
                        System.out.println("Enter the quantity you want to sell: \n");
                        int quantity_sell = sc.nextInt();

                        
                        //updating the quanity after sell
                        //confirming that user has enough to sell
                        if(quantity_sell <= stock.Quantity)
                            {
                                

                                for(investment d : investment_Array)
                                {
                                    if(d.getSybmol() != null && d.getSybmol().contains(symbol_sell))
                                    {
                                        

                                            //System.out.println("Enter the price you want to sell at: \n");
                                            double price_sell = sc.nextDouble();

                                            double payment = stock.compute_payment(stock, quantity_sell, price_sell);
                                            //System.out.println("payment: " + payment);

                                            double last_bv = stock.getBookValue();
                                            //System.out.println("last_bv: " + last_bv);
                                            double new_bookvalue = stock.update_bookValue(stock, quantity_sell);
                                            //System.out.println("new_bookvalue: " + new_bookvalue);
                                            last_bv -= new_bookvalue;
                                            //System.out.println("last_bv: " + last_bv);
                                            stock.setBookValue(last_bv);
                                            d.setBookValue(last_bv);

                                            stock.Quantity -= quantity_sell;
                                            stock.setQuantity(stock.Quantity);
                                            d.setQuantity(stock.Quantity);

                                            double gain = payment - new_bookvalue;

                                            stock.setgain(gain);
                                            d.setgain(gain); 

                                            if (last_bv == 0)
                                            {
                                                investment_Array.remove(d);

                                                
                                                index.remove(d.getName());
                                                break;

                                            }

                                    }
                                }
                            }
                            else
                            {
                                System.out.println("You can't sell, the quanity entered is less than what you have\n");
                            }
                    }
                    else
                    {
                        System.out.println("You cannot sell! stock doesn't exist\n");

                    }
                }
                else if(sell_from.equals("mutualfunds") || sell_from.equals("Mutualfunds"))
                {
                    System.out.println("Provide the symbol of which u wanto to sell: \n");
                    String symbol_sell = sc.nextLine();

                    if(mutualfunds.check_exist_investment(investment_Array , symbol_sell))
                    {
                        System.out.println("Enter the quantity you want to sell: \n");
                        int quantity_sell = sc.nextInt();

                        
                        //updating the quanity after sell
                    
                        if(quantity_sell <= mutualfunds.Quantity)
                            {

                                for(investment d : investment_Array)
                                {
                                    if(d.getSybmol() != null && d.getSybmol().contains(symbol_sell))
                                    {
                                            System.out.println("Enter the price you want to sell at: \n");
                                            double price_sell = sc.nextDouble();

                                            double payment = mutualfunds.compute_payment(mutualfunds, quantity_sell, price_sell);
                                            System.out.println("payment: " + payment);

                                            double last_bv = mutualfunds.getBookValue();
                                            System.out.println("last_bv: " + last_bv);
                                            double new_bookvalue = mutualfunds.update_bookValue(mutualfunds, quantity_sell);
                                            System.out.println("new_bookvalue: " + new_bookvalue);
                                            last_bv -= new_bookvalue;
                                            System.out.println("last_bv: " + last_bv);
                                            mutualfunds.setBookValue(last_bv);
                                            d.setBookValue(last_bv);

                                            mutualfunds.Quantity -= quantity_sell;
                                            mutualfunds.setQuantity(mutualfunds.Quantity);
                                            d.setQuantity(mutualfunds.Quantity);

                                            double gain = payment - new_bookvalue;
                                            System.out.println("gain: " + gain);

                                            mutualfunds.setgain(gain);
                                            d.setgain(gain);

                                            if (last_bv == 0)
                                            {
                                                investment_Array.remove(d);
                                                break;

                                            } 
                                    }
                                }
                            }
                            else
                            {
                                System.out.println("You can't sell, the quanity entered is less than what you have\n");
                            }

                     
                    }
                    else
                    {
                        System.out.println("You cannot sell! mutualfunds doesn't exist\n");

                    }
                }


            }
            else if(userInput.equals("update") || userInput.equals("Update"))
            {
                System.out.println("Would you like to update 'stock' or 'mutualfunds'?\n");
                String update_option ="null";
                
                    update_option = sc.nextLine();
                    if(update_option.equals("stock"))
                    {
                        stock.update(investment_Array);
                    }
                    else if(update_option.equals("mutualfunds"))
                    {
                        mutualfunds.update(investment_Array);
                    }
                
                
            }
            else if(userInput.equals("getGain") || userInput.equals("getgain") || userInput.equals("Getgain"))
            {
                System.out.println("Would you like to get the gain from 'stock' or 'mutualfunds'?\n");
                String getgain_option = sc.nextLine();
                double stock_gain = 0.00;
                double mutualfunds_gain = 0.00;

                if(getgain_option.equals("stock"))
                {
                    stock_gain = stock.get_gain(investment_Array);
                    System.out.println("Total gain is: " + stock_gain);

                }
                else if(getgain_option.equals("mutualfunds"))
                {
                    mutualfunds_gain = mutualfunds.get_gain(investment_Array);
                    System.out.println("Total gain is: " + mutualfunds_gain);
                }
            }

            else if(userInput.equals("quit") || userInput.equals("Quit") || userInput.equals("q") || userInput.equals("Q"))
            {
                
                stock.toFile(investment_Array, filename);
                x = 1;
            }

            else if(userInput.equals("search") || userInput.equals("Search"))
            {
                investment matched = new investment();
                System.out.println("Enter the symbol of investment you wish to search for, if you wish to leave it empty, simply hit ENTER");
                String search_symbol = sc.nextLine();

                System.out.println("Enter the name of investment you wish to search for, if you wish to leave it empty, simply hit ENTER");
                String search_name = sc.nextLine();

                System.out.println("Enter the price range of investment you wish to search for, if you wish to leave it empty, simply hit ENTER");
                String search_price = sc.nextLine();

                stock.search(investment_Array, index, search_symbol, search_name, search_price);
                
                
            }
            else
            {
                System.out.println("Please enter a vlid input");

            }
           
        }
    
    }
}
