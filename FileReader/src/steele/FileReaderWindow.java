package steele;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

/**
 * File Reader Window class
 * @author KevinSteele
 * @version 1.0
 * @since 10-2-2025
 */
public class FileReaderWindow {

	private JFrame frame; // main frame
	private JButton selectFile; // button to show the file chooser
	private JLabel lblNewLabel; // label for mean
	private JTextArea meanTextArea; // text area for mean
	private JTextArea stdDevTextArea; // text area for standard deviation
	private int sum; // sum of the numbers
	private int count; // count of the numbers

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileReaderWindow window = new FileReaderWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FileReaderWindow() {
		initialize();
		createEvents();
	}

	/**
	 * Create the events for the button
	 */
	public void createEvents() {
		selectFile.addActionListener(new ActionListener() {
			/**
			 * Method to handle showing the file chooser and reading the file
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				Node head = null;
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(frame);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File selectedFile = chooser.getSelectedFile();
					try {
						Scanner fileReader = new Scanner(selectedFile);
						sum = 0;
						head = new Node(Integer.parseInt(fileReader.nextLine()));
						Node trav = head;
						while(fileReader.hasNextLine()) {
							int num = Integer.parseInt(fileReader.nextLine());
							sum += num;
							count++;
							trav.next = new Node(Integer.parseInt(fileReader.nextLine()));
							trav = trav.next;
						}
						int mean = findMean(head);
						double stdDev = findStandardDeviation(head, mean);
						meanTextArea.setText(Integer.toString(mean));
						stdDevTextArea.setText(String.format("%.02f", stdDev));
						
					} catch(FileNotFoundException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
	}
	
	/**
	 * Method to find the mean of the linked list
	 * @param head head of the linked list
	 * @return mean of the linked list
	 */
	private int findMean(Node head) {
		if(head == null) return 0;
		return this.sum / this.count;
	}
	
	/**
	 * Method to find the standard deviation of the linked list
	 * @param head head of the linked list
	 * @param mean mean of the linked list
	 * @return standard deviation of the linked list
	 */
	private double findStandardDeviation(Node head, int mean) {
		if(head == null) return 0;
		Node trav = head;
		while(trav != null) {
			sum += Math.pow(trav.data - mean, 2);
			trav = trav.next;
		}
		return Math.sqrt(sum / this.count);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(800, 650);
		frame.setTitle("File Reader");
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.gray);
		
		JLabel fileBtnLabel = new JLabel("<html>Select a file to read: </html>");
		fileBtnLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fileBtnLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		fileBtnLabel.setSize(231, 61);
		fileBtnLabel.setLocation(153, 240);
		frame.getContentPane().add(fileBtnLabel);
		
		selectFile = new JButton("Select File");
		selectFile.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		selectFile.setSize(182, 38);
		selectFile.setLocation(423, 256);
		frame.getContentPane().add(selectFile);
		
		lblNewLabel = new JLabel("Mean: ");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblNewLabel.setBounds(187, 328, 121, 30);
		frame.getContentPane().add(lblNewLabel);
		
		meanTextArea = new JTextArea();
		meanTextArea.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		meanTextArea.setBounds(282, 328, 231, 30);
		frame.getContentPane().add(meanTextArea);
		
		JLabel stdDevLabel = new JLabel("<html>Standard Deviation:</html>");
		stdDevLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		stdDevLabel.setBounds(153, 391, 121, 47);
		frame.getContentPane().add(stdDevLabel);
		
		stdDevTextArea = new JTextArea();
		stdDevTextArea.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		stdDevTextArea.setBounds(282, 391, 238, 30);
		frame.getContentPane().add(stdDevTextArea);
		
		Toolkit tookit = Toolkit.getDefaultToolkit();
		Dimension screenSize = tookit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		
		frame.setLocation((screenWidth - frame.getWidth()) / 2, (screenHeight - frame.getHeight()) / 2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.setVisible(true);
	}
}
