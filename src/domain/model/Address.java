package domain.model;

import java.io.Serializable;

public class Address implements Serializable
{
   private String street;
   private String number;
   private String city;
   /**
    * Constructor initializing the local variables
    * @param str Street name.
    * @param no number.
    * @param ci City name.
    */
   public Address(String str, String no, String ci)
   {
      street = str;
      number = no;
      city = ci;
   }

   public String getStreet()
   {
      return street;
   }

   public void setStreet(String street)
   {
      this.street = street;
   }

   public String getNumber()
   {
      return number;
   }

   public void setNumber(String number)
   {
      this.number = number;
   }

   public String getCity()
   {
      return city;
   }

   public void setCity(String city)
   {
      this.city = city;
   }

   public String toString()
   {
      return street + ", " + number + ", " + city;
   }

}

