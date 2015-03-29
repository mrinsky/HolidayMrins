package languages;

/**
 * Created by Михаил on 20.03.2015.
 */
public class Strings_EN implements Language {

    private final String GUEST = "Guest mode";
    private final String NO_ACCOUNT = "No account? ";
    private final String CREATE_ACCOUNT = "Create it!";
    private final String LANGUAGE = "Language:";
    private final String PASSWORD = "Password:";
    private final String LOGIN = "Login:";
    private final String CONSOLE_CHOICE = "How to start?\n1 - console\n2 - new form\n3 - exit";
    public final String START_CHOICE = "Choose your language:\n1 - RU\n2 - EN\n3 - exit";
    private final String MAIN_MENU = "Welcome to holiday library! Press 4 to read a HELP file\n1 - add\n2 - search\n3 - show\n4 - help\n5 - change language\n6 - log out\n7 - exit";
    private final String GUEST_MAIN_MENU = "Welcome to holiday library! Press 4 to read a HELP file\n1 - search\n2 - show\n3 - help\n4 - change language\n5 - exit";
    private final String WRONG_CHOICE = "Wrong choice!";
    private final String ADD_MENU = "What's to add?\n1 - new tradition\n2 - back to main menu";
    private final String IO_ERROR = "Input-output error occurred";
    private final String HELP_FILE_ERROR = "Can't read a help file";
    private final String HELP_MENU = "1 - back to main menu\n2 - exit";
    private final String SHOW_MENU = "What to show?\n1 - holidays today\n2 - holidays by date\n3 - holidays by type\n4 - holidays by country\n5 - show all holidays\n6 - show all countries\n7 - back to main menu";
    private final String SEARCH_MENU = "How to search?\n1 - by substring\n2 - by mask\n3 - regular search\n4 - back to main menu";
    private final String TABLE_ID = "ID";
    private final String TABLE_HOLIDAY = "HOLIDAY";
    private final String TABLE_DATE = "DATE";
    private final String TABLE_TYPE = "TYPE";
    private final String PRINT_MENU = "1 - look its traditions\n2 - change\n3 - remove\n4 - back";
    private final String TABLE_COUNTRY = "COUNTRY";
    private final String ID_REQUEST = "Enter ID:";
    private final String TYPE_CHOICE = "Choose the type number:";
    private final String PARSE_ERROR = "Can't parse the date";
    private final String DAY = "Enter a day:";
    private final String MONTH = "Enter a month:";
    private final String COUNTRY_CHOICE = "Choose the country:";
    private final String TRADITION_MENU = "1 - read the description\n2 - change tradition\n3 - remove tradition\n4 - back";
    private final String COUNTRY_REQUEST = "Enter the country name:";
    private final String HOLIDAY_REQUEST = "Enter the holiday name:";
    private final String WRONG_MASK = "Wrong mask request!";
    private final String NOT_FOUND = "No holidays found";
    private final String READY = "Completed!";
    private final String TYPE_REQUEST = "Choose the type:";
    private final String DATE_REQUEST = "Choose the date: \n1 - today\n2 - enter";
    private final String END_DATE_REQUEST = "Is this holiday duration one day? \n1 - yes\n 2 - no";
    private final String NO_TRADITION = "No added traditions, add?\n 1 - yes\n 2 - no";
    private final String NO_CLASS = "Holiday loading has been failed";
    private final String DESCRIPT_REQUEST = "Add description? \n1 - yes\n2 - no";
    private final String TRADITION_COUNTRY = "Choose the country:\n1 - from existed\n2 - create new";
    private final String TRADITION_HOLIDAY = "Choose the holiday:\n1 - from existed\n2 - create new";
    private final String NO_DESCRIPT = "Description is empty, change it?\n1 - yes\n2 - no";
    private final String CHANGE_TRADITION = "What is to change?\n1 - change description\n2 - change country\n3 - change holiday";
    private final String ENTER_DESCRIPTION = "Enter its description:";
    private final String LOG_IN_CHOICE = "1 - log in\n2 - registration\n3 - guest mode\n4 - exit\n";
    private final String ENTER_LOGIN = "Enter your login:";
    private final String ENTER_PASSWORD = "Enter your password:";
    private final String REPEAT_PASSWORD = "Repeat your password:";
    private final String NO_USER = "Wrong login!";
    private final String WRONG_PASSWORD = "Wrong password!";
    private final String NO_COUNTRIES = "No countries found";
    private final String ADMIN_MAIN_MENU = "Welcome to your library, sir!\n1 - add\n2 - show\n3 - exit";
    private final String STYLE_MENU = "Styles";
    private final String EDIT_MENU = "Edit";
    private final String ADD = "Add";
    private final String COUNTRY_ITEM = "Country";
    private final String HOLIDAY_ITEM = "Holiday";
    private final String TRADITION_ITEM = "Tradition";
    private final String SEARCH = "Search...";
    private final String REMOVE = "Remove";
    private final String REMOVE_MARKED = "Remove all marked";
    private final String SUBSTRING = "by substring";
    private final String REGULAR = "by regular query";
    private final String MASK = "by mask";
    private final String HELP = "Help";
    private final String READ = "Read a help file";
    private final String CHANGE = "Change";
    private final String ENTER_PASS = "Enter pass:";
    private final String PASS_EXCEPTION = "Passwords not match";
    private final String LOGIN_EXCEPTION = "User with same name already exists";
    private final String LOGIN_OR_PASS_EXCEPTION = "Wrong username or password!";
    private final String HELLO_USER = "Welcome, ";
    private final String ENTER_MESSAGE = "Enter:\n1 - Authorization\n2 - Registration\n3 - Guest session\n4 - Exit";
    private final String FILES_BUILD_ERROR = "User files not build";
    private final String XML_ERROR = "Xml file working error";
    private final String CLASS_NOT_FOUND_ERROR = "Error of load class";
    private final String NOT_UNIQUE = "Object already exists";
    private final String PRINT_SHORT_MENU = "1 - look its traditions\n2 - back";
    private final String SHOW_USER_MENU = "What to show?\n1 - holidays today\n2 - holidays by date\n3 - holidays by type\n4 - holidays by country\n5 - show all holidays\n6 - show all countries\n7 - show own holidays\n8 - back to main menu";
    private final String GUEST_TRADITION_MENU = "1 - read the description\n2 - back";
    private final String DATE = "Date";
    private final String CREATE_TITLE = "Create event";
    private final String NOT_FILLED = "Not all fields are filled!";
    private final String ADVANCED_SEARCH = "advanced search";
    private final String SEARCH_MENU_BAR = "Search";
    private final String SHOW_ALL = "Show all";
    private final String ALL_EVENT = "all event";
    private final String ALL_HOLIDAY = "all holiday";
    private final String ALL_COUNTRY = "all country";
    private final String SEARCH_BY_DATE = "Search by date";
    private final String SEARCH_BY_DATE_INTERVAL = "Search by date interval";
    private final String CHOOSE_SEARCH_PARAM = "Choose search params";
    private final String AND_LABEL = "And";


    public String getALL_EVENT() {
        return ALL_EVENT;
    }

    public String getSHOW_ALL() {
        return SHOW_ALL;
    }

    public String getALL_HOLIDAY() {
        return ALL_HOLIDAY;
    }

    public String getALL_COUNTRY() {
        return ALL_COUNTRY;
    }

    public String getSEARCH_MENU_BAR() {
        return SEARCH_MENU_BAR;
    }


    public String getADVANCED_SEARCH() {
        return ADVANCED_SEARCH;
    }

    public String getCHANGE() {
            return CHANGE;
        }

        public String getHELP() {
            return HELP;
        }

        public String getREAD() {
            return READ;
        }

        public String getSUBSTRING() {
            return SUBSTRING;
        }

        public String getREGULAR() {
            return REGULAR;
        }

        public String getMASK() {
            return MASK;
        }

        public String getREMOVE() {
            return REMOVE;
        }

        public String getREMOVE_MARKED() {
            return REMOVE_MARKED;
        }

        public String getSEARCH() {
            return SEARCH;
        }

        public String getADD() {
            return ADD;
        }

        public String getCOUNTRY_ITEM() {
            return COUNTRY_ITEM;
        }

        public String getHOLIDAY_ITEM() {
            return HOLIDAY_ITEM;
        }

        public String getTRADITION_ITEM() {
            return TRADITION_ITEM;
        }

        public String getEDIT_MENU() {
            return EDIT_MENU;
        }

        public String getSTYLE_MENU() {
            return STYLE_MENU;
        }

        public String getADMIN_MAIN_MENU() {
            return ADMIN_MAIN_MENU;
        }

        public String getNO_COUNTRIES() {
            return NO_COUNTRIES;
        }

        public String getWRONG_PASSWORD() {
            return WRONG_PASSWORD;
        }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getLOGIN() {
        return LOGIN;
    }

    public String getNO_USER() {
            return NO_USER;
        }

        public String getENTER_LOGIN() {
            return ENTER_LOGIN;
        }

        public String getENTER_PASSWORD() {
            return ENTER_PASSWORD;
        }

        public String getREPEAT_PASSWORD() {
            return REPEAT_PASSWORD;
        }

        @Override
        public String getLOG_IN_CHOICE() {
            return LOG_IN_CHOICE;
        }

        public String getENTER_DESCRIPTION() {
            return ENTER_DESCRIPTION;
        }

        public String getCHANGE_TRADITION() {
            return CHANGE_TRADITION;
        }

        public String getNO_DESCRIPT() {
            return NO_DESCRIPT;
        }

        public String getTRADITION_HOLIDAY() {
            return TRADITION_HOLIDAY;
        }

        public String getTRADITION_COUNTRY() {
            return TRADITION_COUNTRY;
        }

        public String getDESCRIPT_REQUEST() {
            return DESCRIPT_REQUEST;
        }

        public String getNO_CLASS() {
            return NO_CLASS;
        }

        public String getNO_TRADITION() {
            return NO_TRADITION;
        }

    @Override
    public String getGUEST() {
        return GUEST;
    }

    @Override
    public String getNO_ACCOUNT() {
        return NO_ACCOUNT;
    }

    @Override
    public String getCREATE_ACCOUNT() {
        return CREATE_ACCOUNT;
    }

    @Override
    public String getLANGUAGE() {
        return LANGUAGE;
    }

    @Override
    public String getENTER_PASS() {
        return ENTER_PASS;
    }

    public String getEND_DATE_REQUEST() {
            return END_DATE_REQUEST;
        }

        public String getDATE_REQUEST() {
            return DATE_REQUEST;
        }

        public String getTYPE_REQUEST() {
            return TYPE_REQUEST;
        }

        public String getCONSOLE_CHOICE() {
            return CONSOLE_CHOICE;
        }

        public String getREADY() {
            return READY;
        }

        public String getNOT_FOUND() {
            return NOT_FOUND;
        }

        public String getWRONG_MASK() {
            return WRONG_MASK;
        }

        public String getSEARCH_REQUEST() {
            return SEARCH_REQUEST;
        }

        public String getHOLIDAY_REQUEST() {
            return HOLIDAY_REQUEST;
        }

        public String getCOUNTRY_REQUEST() {
            return COUNTRY_REQUEST;
        }

        private final String SEARCH_REQUEST = "Enter the searching substring:";

        public Strings_EN() {
        }

        public String getSTART_CHOICE() {
            return START_CHOICE;
        }

        public String getMAIN_MENU() {
            return MAIN_MENU;
        }

        public String getWRONG_CHOICE() {
            return WRONG_CHOICE;
        }

        public String getADD_MENU() {
            return ADD_MENU;
        }

        public String getIO_ERROR() {
            return IO_ERROR;
        }

        public String getHELP_FILE_ERROR() {
            return HELP_FILE_ERROR;
        }

        public String getHELP_MENU() {
            return HELP_MENU;
        }

        public String getSHOW_MENU() {
            return SHOW_MENU;
        }

        public String getSEARCH_MENU() {
            return SEARCH_MENU;
        }

        public String getTABLE_ID() {
            return TABLE_ID;
        }

        public String getTABLE_HOLIDAY() {
            return TABLE_HOLIDAY;
        }

        public String getTABLE_DATE() {
            return TABLE_DATE;
        }

        public String getTABLE_TYPE() {
            return TABLE_TYPE;
        }

        public String getPRINT_MENU() {
            return PRINT_MENU;
        }

        public String getTABLE_COUNTRY() {
            return TABLE_COUNTRY;
        }

        public String getID_REQUEST() {
            return ID_REQUEST;
        }

        public String getTYPE_CHOICE() {
            return TYPE_CHOICE;
        }

        public String getPARSE_ERROR() {
            return PARSE_ERROR;
        }

        public String getDAY() {
            return DAY;
        }

        public String getMONTH() {
            return MONTH;
        }

        public String getCOUNTRY_CHOICE() {
            return COUNTRY_CHOICE;
        }

        public String getTRADITION_MENU() {
            return TRADITION_MENU;
        }
        public String getPASS(){
        return ENTER_PASS;
    }
        public String getPASS_EXCEPTION(){
        return PASS_EXCEPTION;
    }
    public String getLOGIN_EXCEPTION(){
        return LOGIN_EXCEPTION;
    }

    public String getLOGIN_OR_PASS_EXCEPTION(){
        return LOGIN_OR_PASS_EXCEPTION;
    }

    public String getHELLO_USER(){
        return HELLO_USER;
    }

    public String getENTER_MESSAGE(){
        return ENTER_MESSAGE;
    }
    public String getFILES_BUILD_ERROR(){
        return FILES_BUILD_ERROR;
    }

    public String getXML_ERROR(){
        return XML_ERROR;
    }

    public String getCLASS_NOT_FOUND_ERROR(){
        return CLASS_NOT_FOUND_ERROR;
    }

    public String getNOT_UNIQUE(){
        return NOT_UNIQUE;
    }
    public String getPRINT_SHORT_MENU(){
        return PRINT_SHORT_MENU;
    }
    public String getSHOW_USER_MENU(){
        return SHOW_USER_MENU;
    }
    public String getGUEST_TRADITION_MENU(){
        return GUEST_TRADITION_MENU;
    }


    public String getNOT_FILLED() {
        return NOT_FILLED;
    }

    public String getCREATE_TITLE() {
        return CREATE_TITLE;
    }

    public String getDATE() {
        return DATE;
    }

    public String getGUEST_MAIN_MENU(){
        return GUEST_MAIN_MENU;
    }

    public String getSEARCH_BY_DATE(){
        return SEARCH_BY_DATE;
    }
    public String getSEARCH_BY_DATE_INTERVAL(){
        return SEARCH_BY_DATE_INTERVAL;
    }
    public String getCHOOSE_SEARCH_PARAM(){
        return CHOOSE_SEARCH_PARAM;
    }
    public String getAND_LABEL(){
        return AND_LABEL;
    }



}
