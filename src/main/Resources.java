package main;

import languages.Language;
import languages.Strings_EN;
import model.Country;
import model.Holiday;
import model.Tradition;
import model.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;

public class Resources {

    public static LinkedList<Holiday> holidays = new LinkedList<Holiday>();
    public static LinkedList<Country> countries = new LinkedList<Country>();
    public static ArrayList<Tradition> traditions = new ArrayList<Tradition>();
    public static LinkedList<User> users = new LinkedList<User>();
    public static PrintWriter out = new PrintWriter(System.out, true);
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static Language language = new Strings_EN();

}
