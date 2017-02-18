import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

/**
 * Created by moran on 17/02/17.
 */
public class MainWindow extends JFrame {




    // settings area components
    JPanel settingsPanel = new JPanel(); // hosts the user imput fields (ip, port, etc)
    // password settings
    JPanel passwordPanel = new JPanel();
    JLabel passwordLabel = new JLabel(GuiStrings.password_label);
    FocusTextField passwordTextField = new FocusTextField();
    JButton setPasswordButton = new JButton(GuiStrings.password_button);

    // port settings
    JLabel portLabel = new JLabel(GuiStrings.port_label);
    JPanel portPanel = new JPanel();
    FocusTextField portTextField = new FocusTextField();
    JButton setPortButton = new JButton(GuiStrings.port_button);
    // ip display area
    JLabel ipLabel = new JLabel(GuiStrings.ip_label);
    JPanel ipPanel = new JPanel();
    JTextField ipTextField = new JTextField();
    JButton getIpButton = new JButton(GuiStrings.ip_button);
    // server start stop
    public static JButton startServerButton = new JButton(GuiStrings.start_server_button);

    // server monitor area components
    private static JTextArea monitorOutput = new JTextArea(GuiStrings.server_monitor_first_line, 10,30);
    JScrollPane serverMonitorPane = new JScrollPane(monitorOutput);

    // grandma2 server stuff
    public static DroidServer droidServer = new DroidServer();
    public static boolean runServer = false;

    // taken from stack exchange http://stackoverflow.com/questions/7361291/select-all-on-focus-in-lots-of-jtextfield
    static class FocusTextField extends JTextField {
        {
            addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent e) {
                    FocusTextField.this.select(0, getText().length());
                }

                @Override
                public void focusLost(FocusEvent e) {
                    FocusTextField.this.select(0, 0);
                }
            });
        }
    }


    public MainWindow() {
        // settings panel setup
        settingsPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // password area
        constraints.gridx = 0;
        constraints.gridy = 0;
        settingsPanel.add(passwordLabel, constraints);
        updatePasswordLabel();

        constraints.gridx = 0;
        constraints.gridy = 1;
        settingsPanel.add(passwordTextField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        settingsPanel.add(setPasswordButton, constraints);
        setPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // TODO: 18/02/17
                droidServer.setPassword(passwordTextField.getText());
                updatePasswordLabel();
            }
        });

        // port area
        constraints.gridx = 0;
        constraints.gridy = 2;
        settingsPanel.add(portLabel, constraints);
        portLabel.setText(GuiStrings.port_label+Integer.toString(droidServer.getServerPort()));

        constraints.gridx = 0;
        constraints.gridy = 3;
        settingsPanel.add(portTextField, constraints);
        updatePortLabel();

        constraints.gridx = 1;
        constraints.gridy = 3;
        settingsPanel.add(setPortButton, constraints);
        setPortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // TODO: 18/02/17
                String newPort = portTextField.getText();
                if (isInteger(newPort, 10)) {
                    int port = Integer.valueOf(newPort);
                    if((port > 1000) && (port <= 9999)) {
                        droidServer.setServerPort(port);
                        portTextField.setText(GuiStrings.port_successfully_updated);
                    }
                    else {
                        portTextField.setText(GuiStrings.port_failed_to_update);
                    }
                }
                else {
                    portTextField.setText(GuiStrings.port_failed_to_update);
                }
                updatePortLabel();
            }
        });

        // ip area
        constraints.gridx = 0;
        constraints.gridy = 4;
        settingsPanel.add(ipLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        settingsPanel.add(ipTextField, constraints);
        ipTextField.setEditable(false);
        getIp();

        constraints.gridx = 1;
        constraints.gridy = 5;
        settingsPanel.add(getIpButton, constraints);
        getIpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getIp();
            }
        });

        // start/stop server
        constraints.gridx = 0;
        constraints.gridy = 7;
        settingsPanel.add(startServerButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        settingsPanel.add(serverMonitorPane, constraints);


        // server monitor pane setup
        monitorOutput.setEditable(false);

        add(settingsPanel);
        setTitle(GuiStrings.window_title);


    }


    private void updatePasswordLabel(){
        passwordLabel.setText(GuiStrings.password_label+droidServer.getPassword());
    }

    private void updatePortLabel() {
        portLabel.setText(GuiStrings.port_label+Integer.toString(droidServer.getServerPort()));
    }

    private void getIp(){
        ArrayList lst = droidServer.getPossibleIp();
        int lim = lst.size();
        String ips = "";
        for(int i = 0; i < lim; i++){
            if(ips != ""){
                ips += ", ";
            }
            ips += lst.get(i).toString();
        }
        ipTextField.setText(ips);
    }

    public static void main(String[] args) {
        System.out.print(droidServer.getPossibleIp().toString());
        MainWindow mainWindow = new MainWindow();
        mainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainWindow.setSize(800,600);
        mainWindow.setVisible(true);
        while (true){
            startServerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if (droidServer.getConnected()){
                        // TODO: 18/02/17 - stuff to close the server
                        droidServer.stopServer();
                        startServerButton.setText(GuiStrings.start_server_button);
                    }
                    else {
                        droidServer.startServer();
                        if (droidServer.getConnected()){
                            startServerButton.setText(GuiStrings.stop_server_button);
                            runServer = true;
                        }
                    }
                }
            });
        }

    }

    // taken from stack exchange http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }

    public static void addMonitorLine(String line){
        monitorOutput.setText(monitorOutput.getText()+"\n"+line);
    }
}
