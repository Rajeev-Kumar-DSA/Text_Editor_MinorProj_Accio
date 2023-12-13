import javax.swing.*;    // this package call for making a user window
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class TextEditor implements ActionListener{

    JFrame frame;  // declaring the properties of Text Editor(Data Member)
    JMenuBar menuBar;  // help me to make menu option
    JMenu file, edit;   // this is jMenu for menuBar
    JMenuItem newFile, openFile, saveFile;   // this is File Menu Items
    JMenuItem cut, copy, paste, selectAll, close;   // Edit menu Items
    JTextArea textArea;  // creating a Test area -> here I will input the word

    //    create constructor
    TextEditor(){

        // here is Hierchy of step -> JMenu -> JMenuBar -> JFram (this way to making the window)
        // when TextEditor constructor will call -> Frame will call here First
        // here I will initialize the Frame
        frame = new JFrame();

        // now I will initialize the MenuBar
        menuBar = new JMenuBar();

        // initialize Textarea
        textArea = new JTextArea();

        // before adding the menuBar in frame -> we need to add prepare component first
        // Initialize menus
        file = new JMenu("File");  // for menu keep string what you want to show on window
        edit = new JMenu("Edit");

        // here need to initialize first MenuItem
        // File Menu Item
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save");

        // here we need to action Listener to file menu Items
        // here this will refer to textEditor class -> here textEditor behaving like actionListener
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // add menu items for file
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

//        Initialize edit Menu Item/
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // here we need to action Listener to edit menu Items
        // here this will refer to textEditor class -> here textEditor behaing like actionListner
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // add menu item in edit Menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);


        // add Menus to menuBar
        menuBar.add(file);
        menuBar.add(edit);

        // Set MenuBar to frame   -> ya scroll option is not avaiable -> i will use Panel
        frame.setJMenuBar(menuBar);  // after setting then . when I will run will get nothing is change in window. because in menuBar is nothing. here need to pass some menu data

        /// we need to create content Pane -> i will take here instead of frame I will use Panle
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));  // top, left, bottom, right
        panel.setLayout(new BorderLayout(0, 0));  // horizontal gap between border and vertical gap between border
        // add the text area to a panel.
        panel.add(textArea, BorderLayout.CENTER);
        // create scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // add scroll pane to panel
        panel.add(scrollPane);
        // add panel to fream
        frame.add(panel);


        // add text area to frame
//        frame.add(textArea);

        // Set Dimensions of frame  => next three help to make one window
//        frame.setBounds(0, 0, 400, 400);  // x, y, width, height of fram  => x=0, y=0 => it is just location of window when window will open
        frame.setBounds(100, 100, 400, 400);
        // adding the title for text editor
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        // first get source call from
        if(actionEvent.getSource() == cut){
            // perform cut operation  -> here will JTextArea
            textArea.cut();
        }
        if(actionEvent.getSource() == copy){
            // perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource() == paste){
            // perform paste operation
            textArea.paste();
        }
        if(actionEvent.getSource() == selectAll){
            // perform select All operation
            textArea.selectAll();
        }
        if(actionEvent.getSource() == close){
            // perform close editor operation
            // here exit from the code -> my plan I will make one pop-up option their will option to save notSave cancle
            // normally exist from the console
            System.exit(0);
        }

        if(actionEvent.getSource() == openFile){
            // open a File chooser location from this PC  -> pass path directory
            JFileChooser fileChooser = new JFileChooser("C:");
            // here requirement is showOpenDialoag option
            int chooseOption = fileChooser.showOpenDialog(null);   // here dialog will open there will option is open and cancle
            // if we have clicked on open button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                // Getting selected file
                File file = fileChooser.getSelectedFile();
                // Get the Path selected file
                String filePath = file.getPath();

                try{
                    // Here I am reading the file
                    // we need to read the file -> Initiallize the file reader
                    FileReader fileReader = new FileReader(filePath);
                    // intialize the Buffer reader -> it will be help to read the file
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    // Read contents of file line by line
                    while((intermediate=bufferedReader.readLine()) != null){
                        output += intermediate+"\n";
                    }
                    // Set the output string to text area
                    textArea.setText(output);
                }catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource() == saveFile){
            /// Initialize the file picker
            JFileChooser fileChooser = new JFileChooser("C:");  // pass one current directory
            // get choose option from file chooser  -> here requirment is save dialog button
            int chooseOption = fileChooser.showSaveDialog(null);
            // check if we clicked an save button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                // create a new file with chosen directory path and file name
                // filePath = path + fileName + ".txt"
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    // here I will write this file
                    //
                    FileWriter fileWriter = new FileWriter(file);
                    // initialize Buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    // write contains of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource() == newFile){
//             --- i want exist one by one
            TextEditor textEditor = new TextEditor();  // call main function for this button
        }
    }

    public static void main(String[] args) {
        TextEditor newtextEditor = new TextEditor(); // main method trigger -> call the TextEditor class my self

    }
}