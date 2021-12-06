package ePortfolio;

import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

import java.util.*;
import java.util.ArrayList;



public class stock extends investment{

    public String Symbol;
    public String Name;
    public int Quantity;
    public double Price;
    public double BookValue;
    public double Gain;

        ArrayList<investment> investment_Array = new ArrayList<>();
        
    
    public stock()
    {
        this.Symbol = "NULL";
        this.Name = "NULL";
        this.Quantity = 0;
        this.Price = 0.0;
        this.BookValue = 0.0;
        this.Gain = 0.00;
    }

    

    public stock(String S, String n, int q, double p, double bv, double g)
    {
        super(S, n, q, p, bv, g);
        this.Symbol = S;
        this.Name = n;
        this.Quantity = q;
        this.Price = p;
        this.BookValue = bv;
        this.Gain = g;
        
    }

    

    public String toString()
    
    {
        return ( "Symbol:   " + Symbol + "\n" + "Name:   " + Name + "\n" + "Price:   $" + Price + "\n" + "Quantity:   " + Quantity + " shares" + "\n" + "BookValue:   $" + BookValue+ "\n");
    }

    
    /** 
     * @param stocks
     * @param mutualfundss
     * @param search_symbol
     * @param search_name
     * @param price_range
     */
    


}
