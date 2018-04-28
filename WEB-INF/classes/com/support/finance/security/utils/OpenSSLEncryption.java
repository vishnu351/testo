package com.support.finance.security.utils;

import org.apache.commons.lang.StringUtils;

public class OpenSSLEncryption {
	/**
     * Parameter input will be in format of string that will be prepared by
     * concatenating parameters - cipherText, iv, salt, passPhrase, keySize,
     * iterationCount in a random order. And at last indices will also be part
     * of this string where 3 random indices will be converted to corresponding
     * alphabet. e.g. 0-represents a , 1-represents b and so on..
     *
     * At 0th position there will always be CipherText At 1st position -> IV At
     * 2nd position -> SALT At 3rd position -> PassPhrase At 4th position ->
     * IterationCount At 5th position -> KeySize At 6th position -> Indices [ in
     * which above six are randomized]
     *
     * @param input ciphered
     * @return String
     */
    public static String decrypt( String input ) {

        String DL = "__bcdef567kop48__";

        String[] values = input.split( DL );
        // last element indicates indices
        String indices = values[values.length - 1];
        int[] indexes = convert( indices );
        SecurityInfo securityInfo = new SecurityInfo( values, indexes );
        SecurityUtil aesUtil = new SecurityUtil( securityInfo.getKeySize(), securityInfo.getIterationCount() );

        return aesUtil.decrypt( securityInfo.getSalt(), securityInfo.getIv(), securityInfo.getPassPhrase(), securityInfo.getCipherText() );

    }

    public static boolean hasValue( String value ) {
        return !StringUtils.isEmpty( value );
    }

    public static int getInt( String s ) {

        if ( StringUtils.isNumeric(s) && hasValue( s ) ) {
            return Integer.parseInt( s );
        }

        return 0;
    }

    private static int[] convert( String indices ) {
        String[] indexes = indices.split( "," );

        int[] ints = new int[indexes.length];

        for ( int i = 0; i < indexes.length; i++ ) {
            String s = getNum( indexes[i] );
            ints[i] = getInt( s );
        }
        return ints;
    }

    private static String getNum( String s ) {
        switch ( s ) {
            case "a":
                return "0";
            case "b":
                return "1";
            case "c":
                return "2";
            case "d":
                return "3";
            case "e":
                return "4";
            case "f":
                return "5";
        }
        return s;
    }

   /* public static void main(String[] args) {
        String dl = "__bcdef567kop48__";
        // for string "test"
         *          89ada0e10a164c96b4b05e55c9ea06c0__bcdef567kop48__eabWTLJCgje9bVvATpjLxA==__bcdef567kop48__4b5119df5855e656b6544943d19290de__bcdef567kop48__%63%35%61%38%38%62%66%30%33%62%2E%36%63%33__bcdef567kop48__1000__bcdef567kop48__128__bcdef567kop48__c,0,b,d,4,5
        String s = "%63%38%61%38%33%37%62%33%31%64%2E%32%61%63%38__bcdef567kop48__1000__bcdef567kop48__4b30f6838362407954cb0cb0155f588f__bcdef567kop48__iCQ05Rkus2o/1i/2IaCooQ==__bcdef567kop48__4026c9cffa3567db126b3aaa70d0ea50__bcdef567kop48__128__bcdef567kop48__d,e,1,0,c,5";
        System.out.println("----dl = " + dl+"----s = " + s);
        System.out.println("s = " + decrypt(s));
    }*/
}
