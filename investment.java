package ePortfolio;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.io.File;

import java.io.FileInputStream;

import java.io.IOException;

public class investment {

    
    public String Symbol;
    public String Name;
    public int Quantity;
    public double Price;
    public double BookValue;
    public double Gain;

    ArrayList<investment> mathcedA = new ArrayList<investment>();


    public investment()
    {
        
        
    }

    public investment(String S, String n, int q, double p, double bv, double g)
    {


        this.Symbol = S;
        this.Name = n;
        this.Quantity = q;
        this.Price = p;
        this.BookValue = bv;
        this.Gain = g;
        
    }
    public String getSybmol()
    {
        return this.Symbol;
    }
    
    /** 
     * @param S
     */
    public void setSymbol(String S)
    {
        this.Symbol = S;
    }

    
    /** 
     * @return String
     */
    public String getName()
    {
        return this.Name;
    }

    
    /** 
     * @param n
     */
    public void setName(String n)
    {
        this.Name = n;
    }   

    
    /** 
     * @return int
     */
    public int getQuantity()
    {
        return this.Quantity;
    }

    
    /** 
     * @param d
     */
    public void setQuantity(int d)
    {
        this.Quantity = d;
    }
    
    /** 
     * @return double
     */
    public double getPrice()
    {
        return this.Price;
    }

    
    /** 
     * @param p
     */
    public void setPrice(double p)
    {
        this.Price = p;
    }

    
    /** 
     * @return double
     */
    public double getBookValue()
    {
        return this.BookValue;
    }
    
    
    /** 
     * @param bv
     */
    public void setBookValue(double bv)
    {
        this.BookValue = bv;
    }
    
    /** 
     * @param g
     */
    public void setgain(double g)
    {

        this.Gain += g;
    }

    
    /** 
     * @return double
     */
    public double getGain()
    {
        return this.Gain;
    }
    public double totalGain(ArrayList<investment> investment)
    {
        double total = 0.00;
        for (investment s: investment)
        {
            total += s.getGain();
        }

        return total;
    }
    

    
    
    /** 
     * @param obj1
     * @return double
     */
    public double compute_bookValue(investment obj1)
    {
        double book_value = 0.5;
        
                
        book_value = obj1.Price * (double) obj1.Quantity + 9.99;
                
          
        return book_value;
        
    }


    
    /** 
     * @param obj1
     * @param sell_quanity
     * @param sell_price
     * @return double
     */
    public double compute_payment(investment obj1, int sell_quanity, double sell_price)
    {
        double payment =  (sell_quanity *sell_price ) - 9.99;
        return payment;
    }

    
    /** 
     * @param obj1
     * @param quantity_sell
     * @return double
     */
    public double update_bookValue(investment obj1, int quantity_sell)
    {
        
 
        double bv_sell = obj1.BookValue *  ( (double) quantity_sell / obj1.Quantity);
                
          

        return bv_sell;
    }

    
    /** 
     * @param obj1
     */
    public void sell_stock(stock obj1)
    {
        
    }

    
    /** 
     * @param investment
     * @param symbol
     * @param obj1
     */
    

    
    /** 
     * @param investment
     * @param symbol
     * @return boolean
     */
    public boolean check_exist_investment(ArrayList<investment> investment, String symbol)
    {
        for(investment d : investment)
        {
            if(d.getSybmol() != null && d.getSybmol().contains(symbol))
            {
                return true;
            }
               
        }

        return false;
        
    }

    
    /** 
     * @param investment
     * @return double
     */
    public double get_gain(ArrayList<investment> investment)
    {
        
        return getGain();
    }

    
    /** 
     * @param investment
     */
    public void update(ArrayList<investment> investment)
    {
        Scanner sc = new Scanner(System.in);

        for(investment d : investment)
        {
            if(d.getSybmol() != null)
            {
                System.out.println("Symbols: " + d.getSybmol());
                System.out.println("Enter new price for it: ");
                double new_price = sc.nextDouble();
                d.setPrice(new_price);
            }
            else
            {
                break;
            }
            
        }

    }

    public ArrayList<investment> search(ArrayList<investment> stocks ,HashMap<String, ArrayList<Integer>> index,String search_symbol, String search_name, String price_range)
    {
        investment matched = new investment();
                //All empty
                if(search_symbol.isEmpty() && search_name.isEmpty() && price_range.isEmpty())
                {
                    
                    for(investment s : stocks)
                    {  
                        System.out.println(s);
                        matched = s;
                        mathcedA.add(s);
                        System.out.println("ALL Empty");

                    }
  
                }

                //Symbol exist, rest are empty
                else if(!(search_symbol.isEmpty()) && search_name.isEmpty() && price_range.isEmpty())
                {
                    
                    for(investment s : stocks)
                    {
                        String temp = s.getSybmol().toLowerCase();
                        if(s.getSybmol() != null && temp.contains(search_symbol))
                        {
                            System.out.println(s);
                            matched = s;
                            mathcedA.add(s);
                            
                        } 
                    }

                    


                }

                //search name exist, rest empty
                else if(search_symbol.isEmpty() && !(search_name.isEmpty()) && price_range.isEmpty())
                {
                    ArrayList<Integer> indexes = new ArrayList<Integer>();

                    for(investment s: stocks)
                    {
                        String temp = s.getName().toLowerCase();
                        if(temp != null && temp.contains(search_name.toLowerCase()))
                        {
                            if(index.get(search_name) != null)
                            {
                                System.out.println(index.get(search_name));
                                indexes.addAll(index.get(search_name));
                                for(int i = 0; i< indexes.size(); i++)
                                {
                                    System.out.println(stocks.get(indexes.get(i)));
                                    mathcedA.add((stocks.get(indexes.get(i))));
                                }
                                 	
                            }
                            
                        }
                    }
                     
                    

                    

                    

                    
                }
                //price exist, rest empty
                else if(search_symbol.isEmpty() && search_name.isEmpty() && !(price_range.isEmpty()))
                {
                    

                        //Match extact price
                        if(price_range.indexOf("-") == -1)
                        {
                            double price = Double.parseDouble(price_range);

                            for(investment s : stocks)
                            {

                                if(s.getPrice() == price)
                                {
                                    if(s.getSybmol() != null && s.getSybmol().contains(search_symbol))
                                    {
                                        System.out.println(s);
                                        mathcedA.add(s);
                                    }
                                }
                            }
                            

                        }
                        //Less than the price
                        else if(price_range.indexOf("-") == 0)
                        {
                            price_range = price_range.replace("-", "");
                            double price = Double.parseDouble(price_range);

                            for(investment s : stocks)
                            {

                                if(s.getPrice() <= price)
                                {
                                    if(s.getSybmol() != null && s.getSybmol().contains(search_symbol))
                                    {
                                         System.out.println(s);
                                         matched = s;
                                         mathcedA.add(s);
                                    } 
                                }
                            }

                            
                        
                        }
                        else if(price_range.indexOf("-") == price_range.length() - 1)
                        {
                            
                            price_range = price_range.replace("-", "");
                            double price = Double.parseDouble(price_range);

                            for(investment s : stocks)
                            {

                                if(s.getPrice() >= price)
                                {
                                    if(s.getSybmol() != null && s.getSybmol().contains(search_symbol))
                                    {
                                        System.out.println(s);
                                        mathcedA.add(s);
                                    } 
                                }
                            }

                            

                        }
                        else
                        {
                            String []array = price_range.split("-");
                            double min = Double.parseDouble(array[0]);
                            double max = Double.parseDouble(array[1]);
                            
                            for(investment s : stocks)
                            {

                                if(s.getPrice() >= min && s.getPrice() <= max)
                                {
                                    if(s.getSybmol() != null && s.getSybmol().contains(search_symbol))
                                    {
                                        System.out.println(s);
                                        mathcedA.add(s);
                                    } 
                                }
                            }

                            

                        }
   
                }

                //Symbol and name exit, price empty
                else if(!(search_symbol.isEmpty()) && !(search_name.isEmpty()) && price_range.isEmpty())
                {
                    for(investment s : stocks)
                    {
                        if(s.getSybmol() != null && s.getSybmol().contains(search_symbol))
                        {
                            if(s.getName().contains(search_name))
                            {
                                System.out.println(s);
                                mathcedA.add(s);
                            }
                            
                        }
                    }

                    
                    
                }
                //if symbol and price exist, rest empty
                else if(!(search_symbol.isEmpty()) && search_name.isEmpty() && !(price_range.isEmpty()))
                {

                    for(investment s : stocks)
                    {
                        //checking for symbol if exist
                        if(s.getSybmol() != null && s.getSybmol().contains(search_symbol))
                        {

                            //getting exact price
                            if(price_range.indexOf("-") == -1)
                            {
                                double price = Double.parseDouble(price_range);

                                    if(s.getPrice() == price)
                                    {
                                        System.out.println(s);
                                        mathcedA.add(s);
                                    }
                                

                            }
                            //- in the begging, ex -100
                            else if(price_range.indexOf("-") == 0)
                            {
                                price_range = price_range.replace("-", "");
                                double price = Double.parseDouble(price_range);

                                    if(s.getPrice() <= price)
                                    {
                                        System.out.println(s);
                                        mathcedA.add(s);
                                    }
                                
                            }
                            //- in the end, ex 100-
                            else if(price_range.indexOf("-") == price_range.length() - 1)
                            {
                                
                                price_range = price_range.replace("-", "");
                                double price = Double.parseDouble(price_range);
    
                                    if(s.getPrice() >= price)
                                    {
                                        System.out.println(s);
                                        mathcedA.add(s);
                                    }
                            }
                            else
                            {
                                String []array = price_range.split("-");
                                double min = Double.parseDouble(array[0]);
                                double max = Double.parseDouble(array[1]);

                                    if(s.getPrice() >= min && s.getPrice() <= max)
                                    {
                                        System.out.println(s);
                                        mathcedA.add(s);
                                    }

                            }



                        }


                    }

                }
                //Name and price exist, rest empty
                else if(search_symbol.isEmpty() && !(search_name.isEmpty()) && !(price_range.isEmpty()))
                {

                    for(investment s : stocks)
                    {
                        
                        String temp = s.getName().toLowerCase();
                        if(temp.contains(search_name.toLowerCase()))
                        {

                            if(price_range.indexOf("-") == -1)
                            {
                                double price = Double.parseDouble(price_range);

                                    if(s.getPrice() == price)
                                    {
                                        System.out.println(s);
                                        mathcedA.add(s);
                                    }
                                

                            }
                            else if(price_range.indexOf("-") == 0)
                            {
                                price_range = price_range.replace("-", "");
                                double price = Double.parseDouble(price_range);

                                    if(s.getPrice() <= price)
                                    {
                                        System.out.println(s);
                                        mathcedA.add(s);
                                    }
                                
                            }
                            else if(price_range.indexOf("-") == price_range.length() - 1)
                            {
                                
                                price_range = price_range.replace("-", "");
                                double price = Double.parseDouble(price_range);
    
                                    if(s.getPrice() >= price)
                                    {
                                        System.out.println(s);
                                        mathcedA.add(s);
                                    }
                            }
                            else
                            {
                                String []array = price_range.split("-");
                                double min = Double.parseDouble(array[0]);
                                double max = Double.parseDouble(array[1]);

                                    if(s.getPrice() >= min && s.getPrice() <= max)
                                    {
                                        System.out.println(s);
                                        mathcedA.add(s);
                                    }

                            }



                        }


                    }

                    
                }
                else
                {
                    System.out.println("NONE");
                }

                
       return mathcedA;

    }

    public String toString()
    {
        return ("Symbol:   " + Symbol + "\n" + "Name:   " + Name + "\n" + "Price:   $" + Price + "\n" + "Quantity:   " + Quantity + " units" + "\n" + "BookValue:   $" + BookValue+ "\n");
    }

    public String toFile(ArrayList<investment> stocks, String filename) throws IOException
    {
                BufferedWriter out = new BufferedWriter(new FileWriter(filename));;
                String yourString = "";

                for(investment s : stocks)
                    {  
                        yourString = String.valueOf(s);
                        
                        out.write(yourString);
                        out.newLine();

                        
                    }

                        
                        
                        out.close();

                
                
                return null;
    }
    
}
    
    