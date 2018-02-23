import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;

public class Application {

	private JFrame frmJvmActiveThreads;
	JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frmJvmActiveThreads.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJvmActiveThreads = new JFrame();
		frmJvmActiveThreads.setTitle("JVM Active Threads");
		frmJvmActiveThreads.setBounds(100, 100, 1211, 730);
		frmJvmActiveThreads.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJvmActiveThreads.getContentPane().setLayout(null);
		ThreadJVM t = new ThreadJVM();
		
		
		/*clear display, print all threads*/
		JButton btnDisplay = new JButton("Display Threads");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
				t.getRoot();
				textArea.setCaretPosition(0);
			}
		});

		btnDisplay.setBounds(1012, 36, 150, 70);
		frmJvmActiveThreads.getContentPane().add(btnDisplay);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		btnExit.setBounds(1012, 579, 150, 70);
		frmJvmActiveThreads.getContentPane().add(btnExit);

		textArea = new JTextArea();
		textArea.setMargin(new Insets(2, 10, 2, 2));
		textArea.setTabSize(10);
		textArea.setEditable(false);
		textArea.setForeground(Color.BLACK);
		textArea.setFont(new Font("Lucida Console", Font.PLAIN, 16));
		textArea.setBounds(22, 30, 934, 593);
		
		JScrollPane scroll = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setLocation(34, 26);
		scroll.setSize(950, 629);
		frmJvmActiveThreads.getContentPane().add(scroll);
	
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
		
	
		JButton btnClear = new JButton("Clear Display");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
			}
		});
		btnClear.setBounds(1012, 205, 150, 70);
		frmJvmActiveThreads.getContentPane().add(btnClear);
		
		
		JButton btnCreateThreads = new JButton("Create Test Threads\r\n");
		btnCreateThreads.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				t.createThreads();
				
			}
		});
		btnCreateThreads.setBounds(1012, 122, 150, 70);
		frmJvmActiveThreads.getContentPane().add(btnCreateThreads);
		System.setOut(printStream);
		System.setErr(printStream);

	}
}
