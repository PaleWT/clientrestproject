package com.pale.clientrestproject.validation;

import org.springframework.stereotype.Service;

@Service
public class IDValidator {

    public boolean isDigits(String value)
    {
        boolean result = true;
        for(int i = 0; i < value.length(); i++)
        {
            if(!Character.isDigit(value.charAt(i)))
            {
                result = false;
                break;
            }
        }

        return result;
    }

    public boolean isValidID(String idNumber)
    {
        int numLength = idNumber.length();
        boolean isValid = false;

        //check if the idNumber is 13 digits long and if it only contains integers.
        if(this.isDigits(idNumber) &&  numLength == 13)
        {

            int oddSum = 0;
            int evenSum = 0;
            int product = 0;

            int tempEven = 0;
            int numCheckTotal = 0;

            int Multiplier = 2;

            //Loop through each digit of the idNumber
            for(int i = 0; i < numLength; i++)
            {
                //multiply every second digit by 2
                if((i+1) % 2 == 0)
                {
                    product = Integer.parseInt(String.valueOf(idNumber.charAt(i))) * 2;

                    //if the product is a double digit we will split it and add the 2 digits together.
                    if(product > 9)
                    {
                        String tempProduct = String.valueOf(product);
                        tempEven = Integer.parseInt(String.valueOf(tempProduct.charAt(0))) + Integer.parseInt(String.valueOf(tempProduct.charAt(1)));
                        evenSum += tempEven;
                    }
                    else
                    {
                        evenSum +=  product;
                    }
                }
                else
                {
                    oddSum += Integer.parseInt(String.valueOf(idNumber.charAt(i)));
                }
            }

            //adding the two sum together into a variable
            numCheckTotal = oddSum + evenSum;

            //Now we modulus divide by 10 if we get 0 as the remainder then the ID number is correct.
            if(numCheckTotal % 10 == 0)
            {
                isValid = true;
            }
        }

        return isValid;
    }
}
