Bahaa Aldeen Zuraik
1096463



This program is simply a portfolio of different investments so that the user can keep track of the actions for buying or selling investments,
searching for relevant investments, updating prices, and calculating the total gain of the portfolio.
the general problem im trying to solve is to combined all the investemnt in one place and make it easier for the user
to acceses their investments.

Note:

1. testing it on school server may cause an error (X11), the way i tested it on school server was
        1. installed Xming
        2. installed Putty
        3. ran this in the command line
            $Export DISPLAY:10.0
            
2. I was unable to implement a scroll bar, so if you want to check if i have added more than one investment, go to search and maxmize the frame. 

        

Test plan

Menue/main

    - Asking for user to enter the type of serives looking for: buy,sell etc..
        test case - user enters none of the serives
        expected result- invalid input please try again
        pass/fail - pass

    - Menue is not case sensitive
        test case - ex. user enters buy or BUY or buy
        expected result - loop conitunues
        pass/fail - pass

seeling: 

    - selling investemnt doesn't exist
        test case - user enter non-existnt stock or mutual funds
        expected result - you cannot sell- investemnt doesn't exist
        pass/fail - pass

    - selling quanity is more than what exist
        test case - user sells 10 shares and avaivalbe shares are 8
        expected results - you cannot sell- not enough shares
        pass/fail - pass

Buying:

    - stocks and mutualfundsso can't have the same symbol.
        test case - user buy stock with symbol "APPLE" and symbol already exist in mutualfunds
        expected result - investment already exist with that symbol
        pass/fail - pass

    - Trying to buy exisiting symbol
        test case - user buy stock with symbol "APPLE" and investment already exist in stock
        expected result - you have already invested in this mutualfunds, would you like to invest more?(Yes/No)
        pass/fail - pass

    - user enters other than yes or no or y or n when trying to buy more of same stock
        test case - you have already invested in this mutualfunds, would you like to invest more?(Yes/No). user enter "yup"
        expected result - invalid option try again
        pass/fail - pass

update

    - user enters non-integer value
        test case - kool
        expected result - invalid price
        pass/fail - pass

Quit
    - user enters anything other than  "q", "Q", "Quit", "quit", and "QU
        test case - bye
        expected result - allow user to pick another option from menue 
        pass/fail - pass


---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
To compile, you must be in the directory where "ePortfolio.java" "stock.java" "mutualfunds.java" etc.. exist

$ javac ePortfolio.java investment.java stock.java mutualfunds.java ePortfolioPanel.java -Xlint:-serial

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

To run, you need to go to the parent folder that contains "ePortfolio" as a sub-folder

$ java ePortfolio.ePortfolio 