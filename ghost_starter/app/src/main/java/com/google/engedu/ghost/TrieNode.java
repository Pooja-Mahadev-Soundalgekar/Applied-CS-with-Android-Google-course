package com.google.engedu.ghost;

import java.util.HashMap;
import java.util.Random;


public class TrieNode {
    public Random random;
    private HashMap<Character, TrieNode> children;
    private boolean isWord;

    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }

    public void add(String s) {
        int i;
        TrieNode root = this;
        char str;
        for(i=0;i<s.length();i++)
        {
            str=s.charAt(i);
            if(root.children.get(str)==null)
            {
                TrieNode node = new TrieNode();
                root.children.put(str,node);
                root = root.children.get(str);
            }
            else
            {
                root = root.children.get(str);
            }
            if(i==s.length()-1)
            {
                root.isWord = true;
            }
        }

    }

    public boolean isWord(String s) {
        TrieNode root = this;
        int i;
        char str;
        for(i=0;i<s.length();i++)
        {
            str=s.charAt(i);

            if(root.children.get(str)!=null) {
                root = root.children.get(str);
            }
            else
                return false;
        }
        if(root.isWord==true)
            return true;
        else
            return false;


    }

    public String getAnyWordStartingWith(String s) {

        TrieNode root = this;
        String alpha= "abcdefghijklmnopqrstuvwxyz";
        String retstr="";
        char str;
        int i;
        if(s==null) {

        }
        else if( s!=null && s!="") {
            retstr=s;
            for (i = 0; i < s.length(); i++) {
                str = s.charAt(i);

                if (root.children.get(str) != null) {
                    root = root.children.get(str);
                } else
                    return null;
            }
        }
        while(true) {

            random = new Random();
            int k = random.nextInt(26);
            if (root.children.get(alpha.charAt(k)) != null) {

                root = root.children.get(alpha.charAt(k));
                retstr = retstr + alpha.charAt(k);

                if(root.isWord==true)
                    return retstr;

            }
        }


    }

    public String getGoodWordStartingWith(String s) {
        int j;

        TrieNode root = this;
        String alpha= "abcdefghijklmnopqrstuvwxyz";
        String retstr="";
        char str;
        int i;
        int count1=0,count2=0;
        if( s!=null && s!="") {
            retstr=s;
            for (i = 0; i < s.length(); i++) {
                str = s.charAt(i);

                if (root.children.get(str) != null) {
                    root = root.children.get(str);
                } else
                    return null;
            }

            for(i=0;i<alpha.length();i++) {

                if (root.children.get(alpha.charAt(i)) != null) {
                    count2++;
                    if (root.children.get(alpha.charAt(i)).isWord == true)
                        count1++;
                }

            }
            if(count1==count2)
            {
                for(i=0;i<alpha.length();i++)
                    if(root.children.get(alpha.charAt(i))!=null)
                        if (root.children.get(alpha.charAt(i)).isWord == true) {
                            retstr = retstr + alpha.charAt(i);
                            return retstr;
                        }

            }
            else {
                for(i=0;i<alpha.length();i++)
                    if(root.children.get(alpha.charAt(i))!=null)
                        if (root.children.get(alpha.charAt(i)).isWord != true)
                            root = root.children.get(alpha.charAt(i));
                while (true) {

                    random = new Random();
                    int k = random.nextInt(26);
                    if (root.children.get(alpha.charAt(k)) != null) {

                        root = root.children.get(alpha.charAt(k));
                        retstr = retstr + alpha.charAt(k);

                        if (root.isWord == true)
                            return retstr;

                    }
                }
            }

        }

        while(true) {

            random = new Random();
            int k = random.nextInt(26);
            if (root.children.get(alpha.charAt(k)) != null) {

                root = root.children.get(alpha.charAt(k));
                retstr = retstr + alpha.charAt(k);

                if(root.isWord==true)
                    return retstr;

            }
        }

    }
}

