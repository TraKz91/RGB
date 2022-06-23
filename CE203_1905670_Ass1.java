import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

class ID implements Comparable<ID> {
    // id attribute
    int id = 000000;

    // constructor should input an ID as a String or int and set it to the attribute id - to be modified
    public ID(int id) {
        this.id = id;
    }


    // gets a stored ID
    public int getID() {
        return id;
    }


    // sets the input parameter to an ID this can be modified to input a string in which case you will need to convert
    // the parameter to an int
    public void setID(int inputID) {
        id = inputID;
    }


    @Override
    // method used for comparing ID objects based on stored ids, you need to complete the method
    public int compareTo(ID o) {
        if (this.id > o.id) {
            return 1;

        } else if (this.id < o.id) {
            return -1;
        } else {
            return 0;
        }
    }

    // outputs a string representation of the object
    public String toString() {
        return ("ID = " + id);
    }
}

class Couleur { //creation of the class couleur to be used for RGB (color = couleur in french)
    int color1;
    int color2;
    int color3;

    public Couleur(int color1, int color2, int color3) { //creation of the constructor to get all the parameters
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
    }

    public int getColor1() {
        return color1;
    }

    public int getColor2() {
        return color2;
    }

    public int getColor3() {
        return color3;
    }

    public void setColor1(int color1) {
        this.color1 = color1;
    }

    public void setColor2(int color2) {
        this.color2 = color2;
    }

    public void setColor3(int color3) {
        this.color3 = color3;
    }
}

class Filledframe extends JFrame {
    ArrayList<ID> list = new ArrayList<ID>();         //creation of a list with no duplicates entries
    ArrayList<ID> list2 = new ArrayList<ID>();        //creation of a list with duplicates entries
    private JTextField input, col1, col2, col3;
    private JTextArea display, answer;
    private JButton button, button1;
    public int utile;
    public Couleur color;
    JPanel panel;
    JLabel prompt, prompt2;

    public Filledframe() {
        createComponents(); //we call the method createComponent

    }

    public int compareTo(ArrayList<ID> nlist, ID id) //method used for comparing ID from the user's input with the list of id
    {
        for (int i = 0; i < nlist.size(); i++) {             //loop through the list and comparing it with
            if (nlist.get(i).compareTo(id) == 0) {
                return 1;
            }
        }
        return 0;
    }


    public void createComponents() {//creation of all the components and setting the position and the size of JLabel, JPanel, JTextArea

        prompt = new JLabel("Type number, Press add");
        prompt.setBounds(130, 25, 200, 25);


        prompt2 = new JLabel("Insert RGB");
        prompt2.setBounds(170, 75, 200, 25);

        panel = new JPanel();
        panel.setLayout(null);

        input = new JTextField(10);
        input.setBounds(50, 50, 300, 20);
        panel.add(input);

        display = new JTextArea(10, 10);
        display.setBounds(50, 150, 300, 100);

        col1 = new JTextField(5);
        col2 = new JTextField(5);
        col3 = new JTextField(5);

        answer = new JTextArea(1, 20);
        answer.setBounds(50, 265, 300, 20);


        col1.setBounds(50, 100, 75, 20);
        panel.add(col1);

        col2.setBounds(165, 100, 75, 20);
        panel.add(col2);

        col3.setBounds(275, 100, 75, 20);
        panel.add(col3);

        button = new JButton("Add");
        button.setBounds(50, 300, 100, 25);
        panel.add(button);

        button1 = new JButton("Clear");
        button1.setBounds(250, 300, 100, 25);
        panel.add(button1);


        display.setEditable(false);
        answer.setEditable(false);

        panel.add(prompt2);
        panel.add(prompt);
        panel.add(display);
        panel.add(answer);
        add(panel);
        button.addActionListener(new ActionListener() {         //action listener use for button which is 'Add'
            public void actionPerformed(ActionEvent e) {        //The 'try' will check the user input for the ID and RGB
                ID id;                                          //If it's a good ID and RGB, it will add it
                try {                                           //If not, it will be catch with the NumberException and not add to the list and display a message to the user
                    utile = Integer.parseInt(input.getText());
                    id = new ID(utile);
                    color = new Couleur(Integer.parseInt(col1.getText()), Integer.parseInt(col2.getText()), Integer.parseInt(col3.getText()));
                    if (utile >= 100000 && utile < 1000000) {                               //The user's inputs will be checked through these conditions
                        if (0 <= color.getColor1() && color.getColor1() <= 255) {       //It will check if the id is a six digits number and not already in the list
                            if (0 <= color.getColor2() && color.getColor2() <= 255) {   //and if the the parameters of RGB are between 0-255
                                if (0 <= color.getColor3() && color.getColor3() <= 255) {
                                    if (compareTo(list, id) == 1) {
                                        list2.add(id);  //add the id to the ArrayList list2
                                        display.setForeground(new Color(color.getColor1(), color.getColor2(), color.getColor3()));  //we set the color of the text in the JTextArea display
                                        col1.setText("");           //we clear the every JTextField and JTextArea
                                        col2.setText("");
                                        col3.setText("");
                                        display.setText("");
                                        answer.setText("");
                                        input.setText("");
                                        answer.setText("ID " + utile + "   has been added to the list");//we set a new text for the JTextArea answer
                                    } else {
                                        list.add(id);   //add the id to both Arraylist
                                        list2.add(id);
                                        display.setForeground(new Color(color.getColor1(), color.getColor2(), color.getColor3()));  //we set the color of the text in the JTextArea display
                                        col1.setText("");       //we clear the every JTextField and JTextArea
                                        col2.setText("");
                                        col3.setText("");
                                        input.setText("");
                                        display.setText("");
                                        answer.setText("");
                                        answer.setText("ID " + utile + " has been added to the list"); //we set a new text for the JTextArea answer
                                    }
                                } else {
                                    list.add(id);   //add the id to both Arraylist
                                    list2.add(id);
                                    display.setForeground(new Color(0, 0, 0));  //we set the color of the text in the JTextArea display
                                    col1.setText("");       //we clear the every JTextField and JTextArea
                                    col2.setText("");
                                    col3.setText("");
                                    answer.setText("The number you entered for RGB is not a valid"); //we set a new text for the JTextArea answer
                                    input.setText("");
                                    display.setText("");

                                }
                            } else {
                                list.add(id);   //add the id to both Arraylist
                                list2.add(id);
                                display.setForeground(new Color(0, 0, 0));  //we set the color of the text in the JTextArea display
                                col1.setText("");       //we clear the every JTextField and JTextArea
                                col2.setText("");
                                col3.setText("");
                                answer.setText("The number you entered for RGB is not a valid");  //we set a new text for the JTextArea answer
                                input.setText("");
                                display.setText("");

                            }
                        } else {
                            list.add(id);   //add the id to both Arraylist
                            list2.add(id);
                            display.setForeground(new Color(0, 0, 0));  //we set the color of the text in the JTextArea display
                            col1.setText("");       //we clear the every JTextField and JTextArea
                            col2.setText("");
                            col3.setText("");
                            answer.setText("The number you entered for RGB is not a valid"); //we set a new text for the JTextArea answer
                            input.setText("");
                            display.setText("");

                        }
                    } else {
                        col1.setText("");       //we clear the every JTextField and JTextArea
                        col2.setText("");
                        col3.setText("");
                        answer.setText("The Number you entered is not a valid");  //we set a new text for the JTextArea answer
                        input.setText("");
                        display.setText("");

                    }
                    Collections.sort(list);     //sort both ArrayList in ascending order
                    Collections.sort(list2);
                    for (int h = 0; h < list.size(); h++) {     //this will loop through the list and add it to the JTextArea
                        display.append(list.get(h) + "\n");
                    }
                } catch (NumberFormatException ex) {

                    try {

                        utile = Integer.parseInt(input.getText()); //Convert the text to an integer
                        id = new ID(utile);
                        if (utile >= 100000 && utile < 1000000) {
                            if (compareTo(list, id) == 1) {
                                list2.add(id);  //add the id to the ArrayList list2
                                display.setForeground(new Color(color.getColor1(), color.getColor2(), color.getColor3()));  //we set the color of the text in the JTextArea display
                                col1.setText("");           //we clear the every JTextField and JTextArea
                                col2.setText("");
                                col3.setText("");
                                display.setText("");
                                answer.setText("");
                                input.setText("");
                                answer.setText("ID " + utile + "   has been added to the list");//we set a new text for the JTextArea answer
                            } else {
                                list.add(id);   //add the id to both Arraylist
                                list2.add(id);
                                display.setForeground(new Color(0, 0, 0));  //we set the color of the text in the JTextArea display
                                col1.setText("");       //we clear the every JTextField and JTextArea
                                col2.setText("");
                                col3.setText("");
                                input.setText("");
                                display.setText("");
                                answer.setText("");
                                answer.setText("ID " + utile + " has been added to the list"); //we set a new text for the JTextArea answer

                            }
                        } else {
                            col1.setText("");       //we clear the every JTextField and JTextArea
                            col2.setText("");
                            col3.setText("");
                            answer.setText("The Number you entered is not a valid");  //we set a new text for the JTextArea answer
                            input.setText("");
                            display.setText("");

                        }
                        Collections.sort(list);     //sort all ArrayList list in ascending order
                        Collections.sort(list2);
                        for (int h = 0; h < list.size(); h++) {     //this will loop through the list and add it to the JTextArea
                            display.append(list.get(h) + "\n");
                        }

                    } catch (NumberFormatException ntm) {
                        col1.setText("");       //we clear the every JTextField and JTextArea
                        col2.setText("");
                        col3.setText("");
                        input.setText("");
                        display.setText("");
                        answer.setText("The ID you entered is not a valid");  //we set a new text for the JTextArea answer
                        Collections.sort(list);     //sort all ArrayList list in ascending order
                        Collections.sort(list2);
                        for (int h = 0; h < list.size(); h++) {         //this will loop through the list and add it to the JTextArea
                            display.append((list.get(h)).getID() + "\n");
                        }
                    }
                }
            }

        })
        ;
        button1.addActionListener(new ActionListener() {        //action listener use for button1 which is 'clear'
            public void actionPerformed(ActionEvent e) {
                input.setText("");      //we clear the every JTextField, JTextArea and all ArrayList
                display.setText("");
                answer.setText("The list has been cleared");        //we set a new text for the JTextArea answer
                list.clear();
                list2.clear();
                col1.setText("");
                col2.setText("");
                col3.setText("");

            }
        });
    }


    public static void main(String[] args) {        //this will run the program and create a GUI and set all the parameters like the size, the title etc...
        Filledframe all = new Filledframe();
        all.setSize(400, 400);
        all.setTitle("ID GUI");
        all.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        all.setVisible(true);
        all.setResizable(false);


    }

}


