import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Key;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by moran on 18/02/17.
 */
public class DroidServer {

    private static ServerSocket server = null;
    private static Socket client = null;
    private static BufferedReader in = null;
    private static String line;
    private static boolean isConnected=false;
    private static Robot robot;
    private static int SERVER_PORT = 2102;
    private static String password = "key";

    public ArrayList getPossibleIp(){
        ArrayList possibles = new ArrayList();
        try {
            Enumeration e = NetworkInterface.getNetworkInterfaces();
            while(e.hasMoreElements())
            {
                NetworkInterface n = (NetworkInterface) e.nextElement();
                Enumeration ee = n.getInetAddresses();
                while (ee.hasMoreElements())
                {
                    InetAddress i = (InetAddress) ee.nextElement();
                    String ip = i.getHostAddress();
                    if(ip.contains("10.0.") || ip.contains("192.168.") || ip.contains("172.")) {
                        possibles.add(ip);
                    }
                }
            }
        }catch (IOException z){

        }
        return possibles;
    }

    public static int getServerPort() {
        return SERVER_PORT;
    }

    public static void setServerPort(int serverPort) {
        SERVER_PORT = serverPort;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {

        DroidServer.password = password;
    }

    public static boolean getConnected() {
        return isConnected;
    }

    public static void startServer() {
        try {
            robot = new Robot();
            server = new ServerSocket(SERVER_PORT); //Create a server socket on port 8998
            System.out.println("Waiting for client to connect...");
            client = server.accept(); //Listens for a connection to be made to this socket and accepts it
            in = new BufferedReader(new InputStreamReader(client.getInputStream())); //the input stream where data will come from client
            isConnected = true;
        } catch (IOException e) {
            System.out.println("Error in opening Socket");
            System.exit(-1);
            isConnected = false;
        } catch (AWTException e) {
            System.out.println("Error in creating robot instance");
            System.exit(-1);
            isConnected = false;
        }
        if (isConnected) {
            System.out.println("Server is running.");
            listen();
        }
    }

    public static void listen(){

        //read input from client while it is connected
        while(isConnected){
            try{
                line = in.readLine(); //read input from client
                System.out.println(line); //print whatever we get from client
                MainWindow.addMonitorLine(line);

                // if user clicks Fix
                if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_FIX_BUTTON)){
                    //Simulate press and release of key (Calt F)
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_ALT);
                    robot.keyPress(KeyEvent.VK_F);
                    robot.keyRelease(KeyEvent.VK_F);
                    robot.keyRelease(KeyEvent.VK_ALT);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
                // if user clicks Select
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_SELECT_BUTTON)){
                    //Simulate press and release of key (Ctrl S)
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_S);
                    robot.keyRelease(KeyEvent.VK_S);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
                // if user clicks Off
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_OFF_BUTTON)){
                    //Simulate press and release of key (O)
                    robot.keyPress(KeyEvent.VK_O);
                    robot.keyRelease(KeyEvent.VK_O);
                }
                // if user clicks Temp
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_TEMP_BUTTON)){
                    //Simulate press and release of key (Calt T)
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_ALT);
                    robot.keyPress(KeyEvent.VK_T);
                    robot.keyRelease(KeyEvent.VK_T);
                    robot.keyRelease(KeyEvent.VK_ALT);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
                // if user clicks Top
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_TOP_BUTTON)){
                    //Simulate press and release of key (Alt T)
                    robot.keyPress(KeyEvent.VK_ALT);
                    robot.keyPress(KeyEvent.VK_T);
                    robot.keyRelease(KeyEvent.VK_T);
                    robot.keyRelease(KeyEvent.VK_ALT);
                }
                // if user clicks On
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_ON_BUTTON)){
                    //Simulate press and release of key (Calt O)
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_ALT);
                    robot.keyPress(KeyEvent.VK_O);
                    robot.keyRelease(KeyEvent.VK_O);
                    robot.keyRelease(KeyEvent.VK_ALT);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
                // if user clicks <<<
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_STEPBACK_BUTTON)){
                    //Simulate press and release of key (Alt <)
                    robot.keyPress(KeyEvent.VK_ALT);
                    robot.keyPress(KeyEvent.VK_COMMA);
                    robot.keyRelease(KeyEvent.VK_COMMA);
                    robot.keyRelease(KeyEvent.VK_ALT);
                }
                // if user clicks Learn
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_LEARN_BUTTON)){
                    //Simulate press and release of key (Ctrl L)
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_L);
                    robot.keyRelease(KeyEvent.VK_L);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
                // if user clicks >>>
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_STEPFORWARD_BUTTON)){
                    //Simulate press and release of key (Calt <)
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_ALT);
                    robot.keyPress(KeyEvent.VK_COMMA);
                    robot.keyRelease(KeyEvent.VK_COMMA);
                    robot.keyRelease(KeyEvent.VK_ALT);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
                // if user clicks Go -
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_GOMINUS_BUTTON)){
                    //Simulate press and release of key (<)
                    robot.keyPress(KeyEvent.VK_COMMA);
                    robot.keyRelease(KeyEvent.VK_COMMA);
                }
                // if user clicks Pause
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_PAUSE_BUTTON)){
                    //Simulate press and release of key (Ctrl P)
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_P);
                    robot.keyRelease(KeyEvent.VK_P);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
                // if user clicks Go +
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_GOPLUS_BUTTON)){
                    //Simulate press and release of key (Ctrl <)
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_COMMA);
                    robot.keyRelease(KeyEvent.VK_COMMA);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
                // if user clicks View
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_VIEW_BUTTON)){
                    //Simulate press and release of key (V)
                    robot.keyPress(KeyEvent.VK_V);
                    robot.keyRelease(KeyEvent.VK_V);
                }
                // if user clicks Effect
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_EFFECT_BUTTON)){
                    //Simulate press and release of key (Alt E)
                    robot.keyPress(KeyEvent.VK_ALT);
                    robot.keyPress(KeyEvent.VK_E);
                    robot.keyRelease(KeyEvent.VK_E);
                    robot.keyRelease(KeyEvent.VK_ALT);
                }
                // if user clicks Goto
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_GOTO_BUTTON)){
                    //Simulate press and release of key (Alt G)
                    robot.keyPress(KeyEvent.VK_ALT);
                    robot.keyPress(KeyEvent.VK_G);
                    robot.keyRelease(KeyEvent.VK_G);
                    robot.keyRelease(KeyEvent.VK_ALT);
                }
                // if user clicks Page
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_PAGE_BUTTON)){
                    //Simulate press and release of key (Alt P)
                    robot.keyPress(KeyEvent.VK_ALT);
                    robot.keyPress(KeyEvent.VK_P);
                    robot.keyRelease(KeyEvent.VK_P);
                    robot.keyRelease(KeyEvent.VK_ALT);
                }
                // if user clicks Macro
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_MACRO_BUTTON)){
                    //Simulate press and release of key (M)
                    robot.keyPress(KeyEvent.VK_M);
                    robot.keyRelease(KeyEvent.VK_M);
                }
                // if user clicks Preset
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_PRESET_BUTTON)){
                    //Simulate press and release of key (P)
                    robot.keyPress(KeyEvent.VK_P);
                    robot.keyRelease(KeyEvent.VK_P);
                }
                // if user clicks Sequ
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_SEQU_BUTTON)){
                    //Simulate press and release of key (Alt S)
                    robot.keyPress(KeyEvent.VK_ALT);
                    robot.keyPress(KeyEvent.VK_S);
                    robot.keyRelease(KeyEvent.VK_S);
                    robot.keyRelease(KeyEvent.VK_ALT);
                }
                // if user clicks Cue
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_CUE_BUTTON)){
                    //Simulate press and release of key (Alt C)
                    robot.keyPress(KeyEvent.VK_ALT);
                    robot.keyPress(KeyEvent.VK_C);
                    robot.keyRelease(KeyEvent.VK_C);
                    robot.keyRelease(KeyEvent.VK_ALT);
                }
                // if user clicks Exec
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_EXEC_BUTTON)){
                    //Simulate press and release of key (X)
                    robot.keyPress(KeyEvent.VK_X);
                    robot.keyRelease(KeyEvent.VK_X);
                }
                // if user clicks Channel
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_CHANNEL_BUTTON)){
                    //Simulate press and release of key (C)
                    robot.keyPress(KeyEvent.VK_C);
                    robot.keyRelease(KeyEvent.VK_C);
                }
                // if user clicks Fixture
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_FIXTURE_BUTTON)){
                    //Simulate press and release of key (F)
                    robot.keyPress(KeyEvent.VK_F);
                    robot.keyRelease(KeyEvent.VK_F);
                }
                // if user clicks Group
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_GROUP_BUTTON)){
                    //Simulate press and release of key (G)
                    robot.keyPress(KeyEvent.VK_G);
                    robot.keyRelease(KeyEvent.VK_G);
                }
                // if user clicks Del
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_DEL_BUTTON)){
                    //Simulate press and release of key (Ctrl D)
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_D);
                    robot.keyRelease(KeyEvent.VK_D);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
                // if user clicks Blind
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_BLIND_BUTTON)){
                    //Simulate press and release of key (B)
                    robot.keyPress(KeyEvent.VK_B);
                    robot.keyRelease(KeyEvent.VK_B);
                }
                // if user clicks Copy
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_COPY_BUTTON)){
                    //Simulate press and release of key (Ctrl C)
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_C);
                    robot.keyRelease(KeyEvent.VK_C);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
                // if user clicks Freeze
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_FREEZE_BUTTON)){
                    //Simulate press and release of key (Alt F)
                    robot.keyPress(KeyEvent.VK_ALT);
                    robot.keyPress(KeyEvent.VK_F);
                    robot.keyRelease(KeyEvent.VK_F);
                    robot.keyRelease(KeyEvent.VK_ALT);
                }
                // if user clicks Prvw
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_PRVW_BUTTON)){
                    //Simulate press and release of key (Calt P)
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_ALT);
                    robot.keyPress(KeyEvent.VK_P);
                    robot.keyRelease(KeyEvent.VK_P);
                    robot.keyRelease(KeyEvent.VK_ALT);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
                // if user clicks Move
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_MOVE_BUTTON)){
                    //Simulate press and release of key (Ctrl M)
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_M);
                    robot.keyRelease(KeyEvent.VK_M);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
                // if user clicks Assign
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_ASSIGN_BUTTON)){
                    //Simulate press and release of key (Alt A)
                    robot.keyPress(KeyEvent.VK_ALT);
                    robot.keyPress(KeyEvent.VK_A);
                    robot.keyRelease(KeyEvent.VK_A);
                    robot.keyRelease(KeyEvent.VK_ALT);
                }
                // if user clicks Align
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_ALIGN_BUTTON)){
                    //Simulate press and release of key (Ctrl A)
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_A);
                    robot.keyRelease(KeyEvent.VK_A);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
                // if user clicks Time
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_TIME_BUTTON)){
                    //Simulate press and release of key (Ctrl T)
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_T);
                    robot.keyRelease(KeyEvent.VK_T);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
                // if user clicks Esc
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_ESC_BUTTON)){
                    //Simulate press and release of key (Esc)
                    robot.keyPress(KeyEvent.VK_ESCAPE);
                    robot.keyRelease(KeyEvent.VK_ESCAPE);
                }
                // if user clicks Edit
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_EDIT_BUTTON)){
                    //Simulate press and release of key (E)
                    robot.keyPress(KeyEvent.VK_E);
                    robot.keyRelease(KeyEvent.VK_E);
                }
                // if user clicks Oops
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_OOPS_BUTTON)){
                    //Simulate press and release of key (Bspce)
                    robot.keyPress(KeyEvent.VK_BACK_SPACE);
                    robot.keyRelease(KeyEvent.VK_BACK_SPACE);
                }
                // if user clicks Update
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_UPDATE_BUTTON)){
                    //Simulate press and release of key (U)
                    robot.keyPress(KeyEvent.VK_U);
                    robot.keyRelease(KeyEvent.VK_U);
                }
                // if user clicks Clear
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_CLEAR_BUTTON)){
                    //Simulate press and release of key (Del)
                    robot.keyPress(KeyEvent.VK_DELETE);
                    robot.keyRelease(KeyEvent.VK_DELETE);
                }
                // if user clicks Store
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_STORE_BUTTON)){
                    //Simulate press and release of key (S)
                    robot.keyPress(KeyEvent.VK_S);
                    robot.keyRelease(KeyEvent.VK_S);
                }
                // if user clicks numpad 7
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_7_BUTTON)){
                    //Simulate press and release of key (7)
                    robot.keyPress(KeyEvent.VK_7);
                    robot.keyRelease(KeyEvent.VK_7);
                }
                // if user clicks numpad 8
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_8_BUTTON)){
                    //Simulate press and release of key (8)
                    robot.keyPress(KeyEvent.VK_8);
                    robot.keyRelease(KeyEvent.VK_8);
                }
                // if user clicks numpad 9
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_9_BUTTON)){
                    //Simulate press and release of key (9)
                    robot.keyPress(KeyEvent.VK_9);
                    robot.keyRelease(KeyEvent.VK_9);
                }
                // if user clicks numpad +
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_PLUS_BUTTON)){
                    //Simulate press and release of key (+)
                    robot.keyPress(KeyEvent.VK_PLUS);
                    robot.keyRelease(KeyEvent.VK_PLUS);
                }
                // if user clicks numpad 4
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_4_BUTTON)){
                    //Simulate press and release of key (4)
                    robot.keyPress(KeyEvent.VK_4);
                    robot.keyRelease(KeyEvent.VK_4);
                }
                // if user clicks numpad 5
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_5_BUTTON)){
                    //Simulate press and release of key (5)
                    robot.keyPress(KeyEvent.VK_5);
                    robot.keyRelease(KeyEvent.VK_5);
                }
                // if user clicks numpad 6
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_6_BUTTON)){
                    //Simulate press and release of key (6)
                    robot.keyPress(KeyEvent.VK_6);
                    robot.keyRelease(KeyEvent.VK_6);
                }
                // if user clicks numpad Thru
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_THRU_BUTTON)){
                    //Simulate press and release of key (num pad /)
                    robot.keyPress(KeyEvent.VK_SLASH);
                    robot.keyRelease(KeyEvent.VK_SLASH);
                }
                // if user clicks numpad 1
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_1_BUTTON)){
                    //Simulate press and release of key (1)
                    robot.keyPress(KeyEvent.VK_1);
                    robot.keyRelease(KeyEvent.VK_1);
                }
                // if user clicks numpad 2
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_2_BUTTON)){
                    //Simulate press and release of key (2)
                    robot.keyPress(KeyEvent.VK_2);
                    robot.keyRelease(KeyEvent.VK_2);
                }
                // if user clicks numpad 3
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_3_BUTTON)){
                    //Simulate press and release of key (3)
                    robot.keyPress(KeyEvent.VK_3);
                    robot.keyRelease(KeyEvent.VK_3);
                }
                // if user clicks numpad -
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_MINUS_BUTTON)){
                    //Simulate press and release of key (-)
                    robot.keyPress(KeyEvent.VK_MINUS);
                    robot.keyRelease(KeyEvent.VK_MINUS);
                }
                // if user clicks numpad 0
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_0_BUTTON)){
                    //Simulate press and release of key (0)
                    robot.keyPress(KeyEvent.VK_0);
                    robot.keyRelease(KeyEvent.VK_0);
                }
                // if user clicks numpad Dot
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_DOT_BUTTON)){
                    //Simulate press and release of key (num pad .)
                    robot.keyPress(KeyEvent.VK_SEPARATOR);
                    robot.keyRelease(KeyEvent.VK_SEPARATOR);
                }
                // if user clicks If
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_IF_BUTTON)){
                    //Simulate press and release of key (I)
                    robot.keyPress(KeyEvent.VK_I);
                    robot.keyRelease(KeyEvent.VK_I);
                }
                // if user clicks At
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_AT_BUTTON)){
                    //Simulate press and release of key (A)
                    robot.keyPress(KeyEvent.VK_A);
                    robot.keyRelease(KeyEvent.VK_A);
                }
                // if user clicks MA
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_MA_BUTTON)){
                    //Simulate press and release of key (Shift)
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                }
                // if user clicks Please
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_PLEASE_BUTTON)){
                    //Simulate press and release of key (Enter)
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);
                }
                // if user clicks Full
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_FULL_BUTTON)){
                    //Simulate press and release of key (Ctrl F)
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_F);
                    robot.keyRelease(KeyEvent.VK_F);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
                // if user clicks Highlight
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_HIGHLIGHT_BUTTON)){
                    //Simulate press and release of key (H)
                    robot.keyPress(KeyEvent.VK_H);
                    robot.keyRelease(KeyEvent.VK_H);
                }
                // if user clicks Solo
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_SOLO_BUTTON)){
                    //Simulate press and release of key (Calt S)
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_S);
                    robot.keyRelease(KeyEvent.VK_S);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
                // if user clicks Up
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_UP_BUTTON)){
                    //Simulate press and release of key (ArUp)
                    robot.keyPress(KeyEvent.VK_UP);
                    robot.keyRelease(KeyEvent.VK_UP);
                }
                // if user clicks Prev
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_PREV_BUTTON)){
                    //Simulate press and release of key (ArLeft)
                    robot.keyPress(KeyEvent.VK_LEFT);
                    robot.keyRelease(KeyEvent.VK_LEFT);
                }
                // if user clicks Set
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_SET_BUTTON)){
                    //Simulate press and release of key (End)
                    robot.keyPress(KeyEvent.VK_END);
                    robot.keyRelease(KeyEvent.VK_END);
                }
                // if user clicks Next
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_NEXT_BUTTON)){
                    //Simulate press and release of key (ArRght)
                    robot.keyPress(KeyEvent.VK_RIGHT);
                    robot.keyRelease(KeyEvent.VK_RIGHT);
                }
                // if user clicks Down
                else if(line.equalsIgnoreCase(KeyStrings.GRANDMA2_DOWN_BUTTON)){
                    //Simulate press and release of key (ArDown)
                    robot.keyPress(KeyEvent.VK_DOWN);
                    robot.keyRelease(KeyEvent.VK_DOWN);
                }


                //input will come in x,y format if user moves mouse on mouse pad
                else if(line.contains(",")){
                    float movex=Float.parseFloat(line.split(",")[0]);//extract movement in x direction
                    float movey=Float.parseFloat(line.split(",")[1]);//extract movement in y direction
                    Point point = MouseInfo.getPointerInfo().getLocation(); //Get current mouse position
                    float nowx=point.x;
                    float nowy=point.y;
                    robot.mouseMove((int)(nowx+movex),(int)(nowy+movey));//Move mouse pointer to new location
                }
                //if user taps on mousepad to simulate a left click
                else if(line.contains("left_click")){
                    //Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                }
                //Exit if user ends the connection
                else if(line.equalsIgnoreCase("exit")){
                    isConnected=false;
                    //Close server and client socket
                    server.close();
                    client.close();
                    stopServer();
                    System.exit(0);
                }
            }catch (IOException e) {
                System.out.println("Read failed");
                System.exit(-1);
            }
        }
    }

    public static void stopServer() {
        try {
            server.close();
            client.close();
            isConnected = false;
            System.out.println("Server closed");
        }catch (IOException c){
            System.out.println("Failed to close server");
        }
    }
}
