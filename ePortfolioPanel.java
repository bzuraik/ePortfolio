package ePortfolio;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.border.Border;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ePortfolioPanel extends JFrame implements ActionListener {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;
    int count = 0;
    int arrayS = 0;

    public static ArrayList<investment> investment_Array = new ArrayList<>();
    HashMap<String, ArrayList<Integer>> index = new HashMap<String, ArrayList<Integer>>();
    ArrayList<investment> matchedA = new ArrayList<>();
    
    JFrame frame = new JFrame("ePortfolioo");
    JFrame frameBuy = new JFrame("ePortfolioo");
    JFrame frameSell = new JFrame("ePortfolioo");
    JFrame frameSearch = new JFrame("ePortfolioo");
    JFrame frameUpdate = new JFrame("ePortfolioo");
    JFrame frameGain = new JFrame("ePortfolioo");

    investment obj = new investment();
    stock obj2 = new stock();
    investment matched = new investment();

    public ePortfolioPanel()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             
        frame.setLocationRelativeTo(null);
        
        
        
        frame.setSize(520, 300);
        
        JPanel p = new JPanel();
        p.setLayout(null);
        JLabel welcomeMessageJLabel = new JLabel("Welcome to ePortfolio.");
        Dimension size = welcomeMessageJLabel.getPreferredSize();
        welcomeMessageJLabel.setBounds(25, 50, size.width, size.height);
        

        JLabel welcomeMessageJLabel2 = new JLabel();
        welcomeMessageJLabel2.setText("Choose a command from the Commands menu to buy or sell an investment,");
        Dimension size2 = welcomeMessageJLabel2.getPreferredSize();
        welcomeMessageJLabel2.setBounds(25, 70, size2.width, size2.height);

        JLabel welcomeMessageJLabel3 = new JLabel();
        welcomeMessageJLabel3.setText("update prices for all investments, get gain for the portfolio, search ");
        Dimension size3 = welcomeMessageJLabel3.getPreferredSize();
        welcomeMessageJLabel3.setBounds(25, 85, size3.width, size3.height);

        JLabel welcomeMessageJLabel4 = new JLabel();
        welcomeMessageJLabel4.setText("for relevant investments, or quit the program.");
        Dimension size4 = welcomeMessageJLabel4.getPreferredSize();
        welcomeMessageJLabel4.setBounds(25, 100, size4.width, size4.height);

        p.add(welcomeMessageJLabel);
        p.add(welcomeMessageJLabel2);
        p.add(welcomeMessageJLabel3);
        p.add(welcomeMessageJLabel4);
        frame.add(p);
        
        JMenuBar bar = commandMenue();

        
        frame.setJMenuBar(bar);
         
        frame.setVisible(true);

        // buy panel

        

        buyPanel();
        sellPanel();
        searchPanel();
        updatePanel();
        gainPanel();

    }

    public void frame__windowStateChanged(WindowEvent e, JTextArea messages){
        // minimized
        if ((e.getNewState() & Frame.ICONIFIED) == Frame.ICONIFIED){
            messages.setBounds(15, 170, 800, 180);
        }
        // maximized
        else if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH){
            messages.setBounds(15, 170, 1000, 400);
        }
        else
        {
            messages.setBounds(15, 140, 475, 90);
        }
        
     }

    public void buyPanel()
    {
        JPanel buyPanel = new JPanel();

        buyPanel.setLayout(null);
        

        frameBuy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             
        frameBuy.setLocationRelativeTo(null);
        
        //buyPanel.setLayout(new BorderLayout());
        frameBuy.setSize(520, 300);
        
        frameBuy.setResizable(true);

        JLabel buy = new JLabel("Buying an investment");
        Dimension buy_size = buy.getPreferredSize();
        buy.setBounds(5, 10, buy_size.width, buy_size.height);

        buyPanel.add(buy);


        String[] TypeList = { "stock", "mutualfunds"};
        JLabel TypeL = new JLabel("     Type");
        JComboBox<String> Type = new JComboBox<>(TypeList);
        Type.setSelectedIndex(0);
        Type.addActionListener(this);

        Dimension size = TypeL.getPreferredSize();
        TypeL.setBounds(15, 33, size.width, size.height);

        Dimension sizee = Type.getPreferredSize();
        Type.setBounds(85, 33, sizee.width, sizee.height);
        //---------------------------------------------------

        JLabel SymbolL = new JLabel("     Symbol");
        JTextField Symbol = new JTextField(10);

        Dimension SymbolL_size = SymbolL.getPreferredSize();
        SymbolL.setBounds(15, 60, SymbolL_size.width, SymbolL_size.height);

        Dimension Symbol_size = Symbol.getPreferredSize();
        Symbol.setBounds(85, 60, Symbol_size.width, Symbol_size.height);

        //-----------------------------------------------------
        JLabel NameL = new JLabel("     Name");
        JTextField Name = new JTextField(10);

        Dimension NameL_size = NameL.getPreferredSize();
        NameL.setBounds(15, 85,NameL_size.width, NameL_size.height);

        Dimension Name_size = Symbol.getPreferredSize();
        Name.setBounds(85, 85, Name_size.width, Name_size.height);

        //-----------------------------------------------------
        JLabel QuantityL = new JLabel("     Quantity");
        JTextField Quantity = new JTextField(10);

        Dimension QuantityL_size = QuantityL.getPreferredSize();
        QuantityL.setBounds(15, 110,QuantityL_size.width, QuantityL_size.height);

        Dimension Quantity_size = Symbol.getPreferredSize();
        Quantity.setBounds(85, 110, Quantity_size.width, Quantity_size.height);

        //-----------------------------------------------------
        JLabel PriceL = new JLabel("     Price");
        JTextField Price = new JTextField(10);

        Dimension PriceL_size = PriceL.getPreferredSize();
        PriceL.setBounds(15, 135,PriceL_size.width, PriceL_size.height);

        Dimension Price_size = Symbol.getPreferredSize();
        Price.setBounds(85, 135, Price_size.width, Price_size.height);

        //-------------------------------------------------------

        JButton Reset = new JButton("Reset");  
        Reset.setBounds(380,40,75,20);
        Reset.addActionListener(this);

        JTextArea messages = new JTextArea(475, 80);
        messages.setBounds(15, 160, 475, 75);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        messages.setBorder(blackline);
        messages.setEditable(false);

        Reset.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){

                Symbol.setText("");
                Name.setText("");
                Price.setText("");
                Quantity.setText("");

                messages.setText("");
                

            }
         });

        JButton Buy = new JButton("Buy");  
        Buy.setBounds(380,85,75,20);

        

        Buy.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){

                
                if(Type.getSelectedIndex() == 0)
                {
                    
                    if(!Symbol.getText().equals("") && !(Name.getText().equals("")) && !(Quantity.getText().equals("")) && (!Price.getText().equals("")))
                    {
                        messages.setText("");
                        String Symbol_f = Symbol.getText();
                        obj.setSymbol(Symbol_f);

                        String Name_f = Name.getText();
                        obj.setName(Name_f);

                        String Quantity_f = Quantity.getText();
                        int quanity = Integer.parseInt(Quantity_f);
                        obj.setQuantity(quanity);

                        String Price_f = Price.getText();
                        double price = Double.parseDouble(Price_f);
                        obj.setPrice(price);

                        double stock_bv = obj.compute_bookValue(obj);
                        obj.setBookValue(stock_bv);

                        double tg = 0.00;
                        obj.setgain(tg);

                        investment_Array.add(new investment(Symbol_f, Name_f, quanity, price, stock_bv, tg));
                        arrayS++;

                        messages.append("Bought succefully");
                    }
                    else
                    {
                        messages.append("Missing input");
                    }
                    
                }
                else
                {
                    messages.setText("");
                    String Symbol_f = Symbol.getText();

                    String Name_f = Name.getText();

                    String Quantity_f = Quantity.getText();
                    int quanity = Integer.parseInt(Quantity_f);

                    String Price_f = Price.getText();
                    double price = Double.parseDouble(Price_f);

                    double stock_bv = obj.compute_bookValue(obj);
                    obj.setBookValue(stock_bv);

                    double tg = 0.00;
                    obj.setgain(tg);

                    investment_Array.add(new investment(Symbol_f, Name_f, quanity, price, stock_bv, tg));
                    messages.append("Bought succefully");

                }
            }
         });
        //--------------------------------------------------------

        buyPanel.add(Type);
        buyPanel.add(TypeL);
        buyPanel.add(SymbolL);
        buyPanel.add(Symbol);
        buyPanel.add(NameL);
        buyPanel.add(Name);
        buyPanel.add(QuantityL);
        buyPanel.add(Quantity);
        buyPanel.add(PriceL);
        buyPanel.add(Price);
        buyPanel.add(Reset);
        buyPanel.add(Buy);
        buyPanel.add(messages);
        
        JMenuBar bar = commandMenue();

        
        frameBuy.setJMenuBar(bar);
        frameBuy.add(buyPanel);
        frameBuy.setVisible(false);
    }

    public void sellPanel()
    {
        JPanel sellPanel = new JPanel();

        sellPanel.setLayout(null);

        JMenuBar bar = commandMenue();
        frameSell.setJMenuBar(bar);

        

        frameSell.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             
        frameSell.setLocationRelativeTo(null);
        
        //buyPanel.setLayout(new BorderLayout());
        frameSell.setSize(520, 300);
        
        frameSell.setResizable(true);

        JLabel buy = new JLabel("selling an investment");
        Dimension buy_size = buy.getPreferredSize();
        buy.setBounds(5, 10, buy_size.width, buy_size.height);
        sellPanel.add(buy);

        JLabel SymbolL = new JLabel("     Symbol");
        JTextField Symbol = new JTextField(10);

        Dimension SymbolL_size = SymbolL.getPreferredSize();
        SymbolL.setBounds(15, 33, SymbolL_size.width, SymbolL_size.height);

        Dimension Symbol_size = Symbol.getPreferredSize();
        Symbol.setBounds(85, 33, Symbol_size.width, Symbol_size.height);

        //-----------------------------------------------------
        JLabel QuantityL = new JLabel("     Quantity");
        JTextField Quantity = new JTextField(10);

        Dimension QuantityL_size = QuantityL.getPreferredSize();
        QuantityL.setBounds(15, 66,QuantityL_size.width, QuantityL_size.height);

        Dimension Quantity_size = Symbol.getPreferredSize();
        Quantity.setBounds(85, 66, Quantity_size.width, Quantity_size.height);

        //-----------------------------------------------------
        JLabel PriceL = new JLabel("     Price");
        JTextField Price = new JTextField(10);

        Dimension PriceL_size = PriceL.getPreferredSize();
        PriceL.setBounds(15, 99,PriceL_size.width, PriceL_size.height);

        Dimension Price_size = Symbol.getPreferredSize();
        Price.setBounds(85, 99, Price_size.width, Price_size.height);

        //-----------------------------------------------------
        JTextArea messages = new JTextArea(475, 90);
        messages.setBounds(15, 140, 475, 90);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        messages.setBorder(blackline);
        messages.setEditable(false);

        JButton Reset = new JButton("Reset");  
        Reset.setBounds(380,40,75,20);
        Reset.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){

                Symbol.setText("");
                Price.setText("");
                Quantity.setText("");

                messages.setText("");

            }
         });

        JButton Sell = new JButton("Sell");  
        Sell.setBounds(380,85,75,20);
        Sell.addActionListener(this);

        Sell.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){

                
                if(!Symbol.getText().equals("")  && !(Quantity.getText().equals("")) && (!Price.getText().equals("")))
                {

                
                String Symbol_f = Symbol.getText();
                

                String Quantity_f = Quantity.getText();
                int quanity = Integer.parseInt(Quantity_f);
                

                String Price_f = Price.getText();
                double price = Double.parseDouble(Price_f);

                            if(obj.check_exist_investment(investment_Array, Symbol_f))
                            {
                                if(quanity <= obj.Quantity)
                                {
                                    
                                    for(investment d : investment_Array)
                                    {
                                        if(d.getSybmol() != null && d.getSybmol().contains(Symbol_f))
                                        {

                                            
                                            double payment = obj.compute_payment(obj, quanity, price);
                                            double last_bv = obj.getBookValue();
                                            double new_bookvalue = obj.update_bookValue(obj, quanity);
                                            last_bv -= new_bookvalue;
                                            messages.setText("price "+ last_bv);
                                            obj.setBookValue(last_bv);
                                            d.setBookValue(last_bv);
                                            

                                            obj.Quantity -= quanity;
                                            obj.setQuantity(obj.Quantity);
                                            d.setQuantity(obj.Quantity);

                                            double gain = payment - new_bookvalue;
                                            obj.setgain(gain);
                                            d.setgain(gain);

                                            messages.append("Sold succefully!");

                                        }
                                    }
                                    

                                }
                                else
                                {
                                    messages.append("you dont have enough to sell");
                                }

                            }
                            else
                            {
                                messages.append("Symbol does not exit!");
                            }
                        
                    
                    }
                    else
                    {
                        messages.append("Missing input");
                    }
            }
         });

        

        sellPanel.add(QuantityL);
        sellPanel.add(Quantity);
        sellPanel.add(SymbolL);
        sellPanel.add(Symbol);
        sellPanel.add(PriceL);
        sellPanel.add(Price);
        sellPanel.add(Reset);
        sellPanel.add(Sell);
        sellPanel.add(messages);


        frameSell.add(sellPanel);
        frameSell.setVisible(false);

    }

    public void searchPanel()
    {
        JPanel searchPanel = new JPanel();

        searchPanel.setLayout(null);

        JMenuBar bar = commandMenue();
        frameSearch.setJMenuBar(bar);

        

        frameSearch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             
        frameSearch.setLocationRelativeTo(null);
        
        
        frameSearch.setSize(520, 300);
        
        frameSearch.setResizable(true);

        JLabel buy = new JLabel("searching an investment");
        Dimension buy_size = buy.getPreferredSize();
        buy.setBounds(5, 10, buy_size.width, buy_size.height);
        searchPanel.add(buy);

        JLabel SymbolL = new JLabel("     Symbol");
        JTextField Symbol = new JTextField(10);

        Dimension SymbolL_size = SymbolL.getPreferredSize();
        SymbolL.setBounds(15, 33, SymbolL_size.width, SymbolL_size.height);

        Dimension Symbol_size = Symbol.getPreferredSize();
        Symbol.setBounds(130, 33, Symbol_size.width, Symbol_size.height);

        //-----------------------------------------------------
        JLabel NameL = new JLabel("     Name/KeyWords");
        JTextField Name = new JTextField(10);

        Dimension NameL_size = NameL.getPreferredSize();
        NameL.setBounds(15, 55,NameL_size.width, NameL_size.height);

        Dimension Name_size = Symbol.getPreferredSize();
        Name.setBounds(130, 55, Name_size.width, Name_size.height);

        //-----------------------------------------------------
        JLabel LPriceL = new JLabel("     Low Price");
        JTextField LPrice = new JTextField(10);

        Dimension LPriceL_size = LPriceL.getPreferredSize();
        LPriceL.setBounds(15, 77,LPriceL_size.width, LPriceL_size.height);

        Dimension LPrice_size = Symbol.getPreferredSize();
        LPrice.setBounds(130, 77, LPrice_size.width, LPrice_size.height);

        //-----------------------------------------------------

        JLabel HPriceL = new JLabel("     High Price");
        JTextField HPrice = new JTextField(10);

        Dimension HPriceL_size = HPriceL.getPreferredSize();
        HPriceL.setBounds(15, 99,HPriceL_size.width, HPriceL_size.height);

        Dimension Price_size = Symbol.getPreferredSize();
        HPrice.setBounds(130, 99, Price_size.width, Price_size.height);

        JTextArea messages = new JTextArea(475, 90);
        messages.setBounds(15, 140, 475, 90);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        messages.setBorder(blackline);
        messages.setEditable(false);

        JButton Reset = new JButton("Reset");  
        Reset.setBounds(380,40,75,20);
        Reset.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){

                Symbol.setText("");
                LPrice.setText("");
                HPrice.setText("");
                Name.setText("");

                messages.setText("");

            }
         });

        JButton Search = new JButton("Search");  
        Search.setBounds(380,85,75,20);
        Search.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                
                
                messages.append("");
                String Symbol_f = Symbol.getText();

                String Name_s = Name.getText();

                String HPrice_s = HPrice.getText();
                

                String LPrice_s = LPrice.getText();
                
                String price_range = "";

                if(HPrice_s.isEmpty() && !LPrice_s.isEmpty())
                {
                    price_range = LPrice_s + "-";
                }
                else if(!HPrice_s.isEmpty() && LPrice_s.isEmpty())
                {
                    price_range =  "-" + HPrice_s;
                }
                else if(!HPrice_s.isEmpty() && !LPrice_s.isEmpty())
                {
                    price_range = LPrice_s + "-" + HPrice_s;
                }
                matchedA = obj.search(investment_Array, index, Symbol_f.toLowerCase(), Name_s.toLowerCase(), price_range);

                for(investment s: matchedA)
                {
                    messages.append(s.toString());
                    messages.append("\n");
                }
                
                
                matchedA.clear();



            }
         });;

         frameSearch.addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent arg0) {
                frame__windowStateChanged(arg0, messages);
            }
            });


        searchPanel.add(NameL);
        searchPanel.add(Name);
        searchPanel.add(SymbolL);
        searchPanel.add(Symbol);
        searchPanel.add(HPriceL);
        searchPanel.add(HPrice);
        searchPanel.add(LPrice);
        searchPanel.add(LPriceL);
        searchPanel.add(Reset);
        searchPanel.add(Search);
        searchPanel.add(messages);
        


        frameSearch.add(searchPanel);
        frameSearch.setVisible(false);
    }

    public void updatePanel()
    {
        JPanel updatePanel = new JPanel();

        updatePanel.setLayout(null);

        JMenuBar bar = commandMenue();
        frameUpdate.setJMenuBar(bar);

        

        frameUpdate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             
        frameUpdate.setLocationRelativeTo(null);
        
        //buyPanel.setLayout(new BorderLayout());
        frameUpdate.setSize(520, 300);
        
        frameUpdate.setResizable(true);

        JLabel buy = new JLabel("Updating an investment");
        Dimension buy_size = buy.getPreferredSize();
        buy.setBounds(5, 10, buy_size.width, buy_size.height);
        updatePanel.add(buy);

        JLabel SymbolL = new JLabel("     Symbol");
        JTextField Symbol = new JTextField(10);
        Symbol.setText(obj.getSybmol());
        Symbol.setEditable(false);

        Dimension SymbolL_size = SymbolL.getPreferredSize();
        SymbolL.setBounds(15, 33, SymbolL_size.width, SymbolL_size.height);

        Dimension Symbol_size = Symbol.getPreferredSize();
        Symbol.setBounds(85, 33, Symbol_size.width, Symbol_size.height);


        //-----------------------------------------------------
        JLabel NameL = new JLabel("     Name");
        JTextField Name = new JTextField(10);
        Name.setText(obj.getName());
        Name.setEditable(false);

        Dimension NameL_size = NameL.getPreferredSize();
        NameL.setBounds(15, 66,NameL_size.width, NameL_size.height);

        Dimension Name_size = Symbol.getPreferredSize();
        Name.setBounds(85, 66, Name_size.width, Name_size.height);

        //-----------------------------------------------------
        JLabel PriceL = new JLabel("     Price");
        JTextField Price = new JTextField(10);
        
        

        Dimension PriceL_size = PriceL.getPreferredSize();
        PriceL.setBounds(15, 99,PriceL_size.width, PriceL_size.height);

        Dimension Price_size = Symbol.getPreferredSize();
        Price.setBounds(85, 99, Price_size.width, Price_size.height);

        //-----------------------------------------------------
        JTextArea messages = new JTextArea(475, 90);
        messages.setBounds(15, 140, 475, 90);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        messages.setBorder(blackline);
        messages.setEditable(false);

        JButton Prev = new JButton("Prev");  
        Prev.setBounds(380,40,75,20);

        JButton Next = new JButton("Next");  
            Next.setBounds(380,70,75,20);

        JButton Save = new JButton("Save");  
        Save.setBounds(380,100,75,20);

        
       
        Prev.setEnabled(false);
        
            Prev.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){

                    Next.setEnabled(true);
                    count--;
                    Symbol.setText(investment_Array.get(count).getSybmol());
                    Name.setText(investment_Array.get(count).getName());
                    if(count == 0)
                    {
                        Prev.setEnabled(false);
                    }
                    

                }
            });
  
            Next.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                        
                        Prev.setEnabled(true);
                        Symbol.setText(investment_Array.get(count).getSybmol());
                        Name.setText(investment_Array.get(count).getName());
                        String priceU = String.valueOf(investment_Array.get(count).getPrice());
                        Price.setText(priceU);
                        count++;

                        if(count == arrayS)
                        {
                            Next.setEnabled(false);
                        }
                        
                    

                }
            });

            
            Save.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){

                    String new_p = Price.getText();
                    double new_pric = Double.parseDouble(new_p);
                    
                    
                    
                    for(investment d: investment_Array)
                    {
                        
                        if(d.getSybmol() != null && d.getSybmol().contains(Symbol.getText()))
                        {
                            d.setPrice(new_pric);
                            
                            messages.append("Saved!");
                            
                        }
                    }
                    
                    

                }
            });
        
        updatePanel.add(NameL);
        updatePanel.add(Name);
        updatePanel.add(SymbolL);
        updatePanel.add(Symbol);
        updatePanel.add(PriceL);
        updatePanel.add(Price);
        updatePanel.add(Prev);
        updatePanel.add(Next);
        updatePanel.add(Save);
        updatePanel.add(messages);

        frameUpdate.add(updatePanel);
        frameUpdate.setVisible(false);
    }

    public void update_gain(JTextArea messages, JTextField totalGain)
    {
        double totalG = 0.00;
        for(investment s: investment_Array)
        {
            String gains = String.valueOf(s.getGain());
            messages.append("Symbol: " + s.getSybmol() + " Gain: "+ gains);
            messages.append("\n");
            totalG += s.getGain();
        }
        String finalgain = String.valueOf(totalG);
        totalGain.setText(finalgain);

    }

    public void gainPanel()
    {
        JPanel gainPanel = new JPanel();

        gainPanel.setLayout(null);

        JMenuBar bar = commandMenue();
        frameGain.setJMenuBar(bar);

        

        frameGain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             
        frameGain.setLocationRelativeTo(null);
        
        //buyPanel.setLayout(new BorderLayout());
        frameGain.setSize(520, 300);
        
        frameGain.setResizable(true);

        JLabel gain = new JLabel("Getting total gain");
        Dimension buy_size = gain.getPreferredSize();
        gain.setBounds(5, 10, buy_size.width, buy_size.height);
        gainPanel.add(gain);

        JLabel TgL = new JLabel("     total gain");
        JTextField Tg = new JTextField(10);
        String total_gain = String.valueOf(obj.totalGain(investment_Array));
        Tg.setText(total_gain);
        Tg.setEditable(false);
    

        Dimension NameL_size = TgL.getPreferredSize();
        TgL.setBounds(15, 50,NameL_size.width, NameL_size.height);

        Dimension Name_size = Tg.getPreferredSize();
        Tg.setBounds(85, 50, Name_size.width, Name_size.height);

        JTextArea messages = new JTextArea(600, 90);
        messages.setBounds(15, 77, 475, 150);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        messages.setBorder(blackline);
        messages.setEditable(false);

        JButton Getgain = new JButton("Getgain");  
        Getgain.setBounds(380,55,80,20);

        Getgain.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){


                update_gain(messages, Tg);
                

            }
        });

        
       

        gainPanel.add(Getgain);
        gainPanel.add(Tg);
        gainPanel.add(TgL);
        gainPanel.add(messages);

        frameGain.add(gainPanel);
        frameGain.setVisible(false);



    }
    public JMenuBar commandMenue()
    {
        JMenu commandMenue = new JMenu("Commands");

        JMenuItem buyChoice = new JMenuItem("Buy");
        buyChoice.addActionListener(this);
        commandMenue.add(buyChoice);

        JMenuItem sellChoice = new JMenuItem("Sell");
        sellChoice.addActionListener(this);
        commandMenue.add(sellChoice);

        JMenuItem updateChoice = new JMenuItem("Update");
        updateChoice.addActionListener(this);
        commandMenue.add(updateChoice);

        JMenuItem getGainChoice = new JMenuItem("Getgain");
        getGainChoice.addActionListener(this);
        commandMenue.add(getGainChoice);

        JMenuItem searchChoice = new JMenuItem("Search");
        searchChoice.addActionListener(this);
        commandMenue.add(searchChoice);

        JMenuItem quitChoice = new JMenuItem("Quit");
        quitChoice.addActionListener(this);
        commandMenue.add(quitChoice);
 
        JMenuBar bar = new JMenuBar();
        bar.add(commandMenue);
    
        return bar;
    }

    


    @Override
    public void actionPerformed(ActionEvent e)
    {
        String buttonString = e.getActionCommand();
        if(buttonString.equals("Buy"))
        {
            frameBuy.setVisible(true);
            frame.setVisible(false);
            frameSell.setVisible(false);
            frameSearch.setVisible(false);
            frameUpdate.setVisible(false);

        }
        else if(buttonString.equals("Sell"))
        {
            frameSell.setVisible(true);
            frameBuy.setVisible(false);
            frame.setVisible(false);
            frameSearch.setVisible(false);
            frameUpdate.setVisible(false);
            frameGain.setVisible(false);
        }
        else if(buttonString.equals("Search"))
        {
            frameSearch.setVisible(true);
            frameSell.setVisible(false);
            frameBuy.setVisible(false);
            frame.setVisible(false);
            frameUpdate.setVisible(false);
            frameGain.setVisible(false);
        }
        else if(buttonString.equals("Update"))
        {
            frameUpdate.setVisible(true);
            frameSearch.setVisible(false);
            frameSell.setVisible(false);
            frameBuy.setVisible(false);
            frame.setVisible(false);
            frameGain.setVisible(false);
        }
        else if(buttonString.equals("Getgain"))
        {
            frameGain.setVisible(true);
            frameUpdate.setVisible(false);
            frameSearch.setVisible(false);
            frameSell.setVisible(false);
            frameBuy.setVisible(false);
            frame.setVisible(false);
            
        }
        else if(buttonString.equals("Quit"))
        {
            frameGain.setVisible(false);
            frameUpdate.setVisible(false);
            frameSearch.setVisible(false);
            frameSell.setVisible(false);
            frameBuy.setVisible(false);
            frame.setVisible(false);
            
        }
    }
    


    public static void main(String[] args) {
    ePortfolioPanel gui = new ePortfolioPanel();
    gui.frameBuy.getContentPane();

    
    }
}