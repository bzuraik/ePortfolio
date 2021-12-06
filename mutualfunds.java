package ePortfolio;
import java.util.Scanner;


import java.util.*;
import java.util.ArrayList;

public class mutualfunds extends investment{

    public String Symbol;
    public String Name;
    public int Quantity;
    public double Price;
    public double BookValue;
    public double Gain;

    ArrayList<mutualfunds> mutualfunds_Array = new ArrayList<>();

    


    public mutualfunds()
    {
        
        this.Symbol = "NULL";
        this.Name = "NULL";
        this.Quantity = 0;
        this.Price = 0.0;
        this.BookValue = 0.0;
        this.Gain = 0.00;
    }
    

    public mutualfunds(String S, String n, int q, double p, double bv, double g)
    {
        super(S, n, q, p, bv, g);
        this.Symbol = S;
        this.Name = n;
        this.Quantity = q;
        this.Price = p;
        this.BookValue = bv;
        this.Gain = g; 

    }

    
    

    
    /** 
     * @param investment
     * @param symbol
     */
    

    public String toString()
    {
        
        return ("Symbol:   " + Symbol + "\n" + "Name:   " + Name + "\n" + "Price:   $" + Price + "\n" + "Quantity:   " + Quantity + " units" + "\n" + "BookValue:   $" + BookValue+ "\n");
    }


    
    
    

    
    
    
}
