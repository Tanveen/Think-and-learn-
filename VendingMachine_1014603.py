
def main_menu():
  while(inserted_amount > 0):
   print ("---------")
   print ("Main Menu")
   print ("---------")
   print ("[1] Drinks")
   print ("[2] Snacks")
   print ("[3] Exit")
   choice = str (input("Enter your choice (3 to exit)--"))
   if(choice == "1"):
    print(drinks_Menu())
   elif(choice == "2"):
    print(snacks_Menu()) 
   elif(choice == "3"):
    print("------------------------------")
    print("You Inserted", inserted_amount)
    print("------------------------------")
    print("Total Purchase", total_amount)
    print("------------------------------")
    print("Change:", (inserted_amount-total_amount))
    print("------------------------------")
    print("GoodBye!!")  
    print("------------------------------")
    print("FUCK THE HELL OFF")
    print(exit())
    
   
  
def drinks_Menu():
 while(inserted_amount > 0):
  print("-----------")
  print("Drinks Menu")
  print("-----------")
  print("Water $0.79")
  print("Juice $0.99")
  print("Soda  $1.39")
  y= str (input ("Select the drink by Entering the full Name or X to go back to Main Menu--"))
  if(y == "water"):
     print("it costs = 0.79")
     cost = 0.79
     global total_amount
     if(inserted_amount >= cost):  
      total_amount += cost
      print("--------------------------------------------------------------------------")
      print("Vending water, You have", inserted_amount - total_amount, "in your account")  
      print("--------------------------------------------------------------------------")
     else:
        print("--------------------------------------------")
        print("You don't have enough money to buy the drink")
        print("--------------------------------------------")
        print(exit())
  elif(y == "juice"):
     print("it costs = 0.99")
     cost = 0.99
     if(inserted_amount >= cost):
      total_amount += cost
      print("------------------------------------------------------------------------")
      print("Vending Juice, You have", inserted_amount-total_amount, "in your account")
      print("------------------------------------------------------------------------")
     else:
      print("--------------------------------------------")
      print("You don't have enough money to buy the drink")
      print("--------------------------------------------")
      print(exit())
  elif(y == "soda"):
     print("it costs = 1.39")
     cost = 1.39
     if(inserted_amount >= 1.39):
      total_amount += cost
      print("-----------------------------------------------------------------------")
      print("Vending Soda, You have", inserted_amount-total_amount, "in your account")
      print("-----------------------------------------------------------------------")
     else:
      print("--------------------------------------------")
      print("You don't have enough money to buy the drink") 
      print("--------------------------------------------")
      print(exit())
  elif(y == "x"):
      print(main_menu())
  

def snacks_Menu():
 while(inserted_amount > 0):
  print("-----------")
  print("Snacks Menu")
  print("-----------")
  print("Chips $0.99")
  print("Peanuts $0.5")
  print("Gums $0.35")
  z= str( input ( " Select the Snack by Entering the Full Name or X to go back to Main Menu--"))
  if(z == "chips"):
     print("it costs = 0.99")
     cost = 0.99
     global total_amount
     if(inserted_amount >= cost):  
      total_amount += cost
      print("--------------------------------------------------------------------------")
      print("Vending Chips, You have", inserted_amount - total_amount, "in your account")  
      print("--------------------------------------------------------------------------")
     else:
        print("--------------------------------------------")
        print("You don't have enough money to buy the drink")
        print("--------------------------------------------")
        print(exit())
  elif(z == "peanuts"):
     print("it costs = 0.5")
     cost = 0.50     
     if(inserted_amount >=cost):
      total_amount+=cost
      print("--------------------------------------------------------------------------")
      print("Vending Peanuts, You have", inserted_amount-total_amount, "in your account")
      print("--------------------------------------------------------------------------")
     else:
        print("--------------------------------------------")
        print("You don't have enough money to buy the Snack")
        print("--------------------------------------------")
        print(exit())
  elif(z == "gums"):
     print("it costs = 0.35")
     cost=0.35
     if(inserted_amount >= cost):
      total_amount += cost
      print("-----------------------------------------------------------------------")
      print("Vending Gums, You have", inserted_amount-total_amount, "in your account")
      print("-----------------------------------------------------------------------")
     else:
        print("--------------------------------------------")
        print("You don't have enough money to buy the drink")
        print("--------------------------------------------")
        print(exit())
  elif(z=="x"):
     print(main_menu())
  

print("Welcome to the UB Vending Machine")
x = int (input ("Enter the number of nickles you wish to insert? -- "))
nikle = 0.05
inserted_amount = (x * nikle)
total_amount = 0
print ("You inserted", inserted_amount, "dollars")
main_menu()

