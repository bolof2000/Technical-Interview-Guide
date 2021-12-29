package org.example;

//import org.junit.Assert;
//import org.junit.Test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.*;

class SolutionsTest {


    @Test
    public void isAnagram02() {

        boolean expected = Solutions.isAnagram02("abba","bbaa");
        Assertions.assertTrue(expected);
    }

    @Test
    public void reverseOnlyLetters03() {
        // String input = "ab-cd";
        String expected = Solutions.reverseOnlyLetters03("ab-cd");
        Assertions.assertEquals("dc-ba",expected);

    }

    @Test
    public void validPalindrome07() {
        boolean actual = Solutions.validPalindrome07("abba");
        Assertions.assertTrue(actual);
    }




    @Test
   public void validParethesis23() {

       boolean actual = Solutions.validParethesis23("()");
       Assertions.assertTrue(actual);
    }

}