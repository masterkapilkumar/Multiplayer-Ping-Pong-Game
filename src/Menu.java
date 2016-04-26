import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class Menu extends JFrame{
	
	final int screenWidth=900;
	final int screenHeight=400;
	
	int size1=100;
	int size2=40;
	JButton Play,Quit;
	//JCheckBox twoplayers,limitFrameRate;
	JRadioButton option1;
	JRadioButton option2;
	JLabel message;
	
	public Menu()
	{
		addingButtons();
		addingFunctions();
		getContentPane().setLayout(null);
		Play.setBounds((screenWidth-size1)/2,5,size1,size2);
		Quit.setBounds((screenWidth-size1)/2,50,size1,size2);
	
		option1.setBounds(0,5,size1,size2);
		option2.setBounds(0,50,size1,size2);
		message=new JLabel("");
		message.setBounds(screenWidth/2,100,size1,size2);

		
		getContentPane().add(Play);
		getContentPane().add(Quit);
		getContentPane().add(option1);
		getContentPane().add(option2);
		getContentPane().add(message);
		
		pack();
		setVisible(true);
		setSize(screenWidth,screenHeight);
		setTitle("Welcome to the Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		
		
	}
	private void addingButtons()
	{
		Play=new JButton("Play");
		Quit=new JButton("Quit");
		option1=new JRadioButton("two players");
		option2=new JRadioButton("Compu v/s Player");
		option2.setSelected(true);
		ButtonGroup grp=new ButtonGroup();
		grp.add(option1);
		grp.add(option2);
	}
	private void addingFunctions()
	{
		Play.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				dispose();
				PingPong game=new PingPong();
				if(option1.isSelected())
				{
					game.player2.com=false;
				}
				else if (option2.isSelected())
				{
					game.player2.com=true;
				}
				else
				{
					message.setText("Select one of the two options");
					
				}
				game.start();
				
			}
			
		});
		
		Quit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
			
		});
		
		
	}

}
