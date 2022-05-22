package chatappication;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class loginframe extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String url="jdbc:mysql://localhost:3306/student";
	JTextField t=new JTextField();
	JPasswordField p=new JPasswordField();
	JLabel l=new JLabel("LOGIN");
	JLabel l1=new JLabel("USERNAME");
	JLabel l2=new JLabel("PASSWORD");
	JLabel l3=new JLabel();
	JButton b1=new JButton("SUBMIT");
	JButton b2=new JButton("RESET");
	
	public void add()
	{
		add(t);
		add(p);
		add(l);
		add(l1);
		add(l2);
		add(l3);
		add(b1);
		add(b2);
	}
	public void setsize()
	{
		l3.setBounds(120,250,250,30);
		l.setBounds(200,50,50,30);
		t.setBounds(150,150,150,30);
		p.setBounds(150,220,120,30);
		l2.setBounds(70,220,150,30);
		l1.setBounds(70,150,100,30);
		b1.setBounds(120,300,100,30);
		b2.setBounds(250,300,100,30);
	}
	loginframe()
	{
		add();
		setsize();
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("CLIENT");
		addActionEvent();
	}
	public void addActionEvent()
	{
		b1.addActionListener(this);
		b2.addActionListener(this);
	}
	public void login(String username,String password) throws Exception
	{
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,"root","root");
		Statement st=con.createStatement();
		String query="select * from login where uname='"+username+"'and pass='"+password+"'";
		ResultSet rs =st.executeQuery(query);
		if(rs.next())
		{
			Client cl=new Client();
			dispose();
		}
		else
		{
			l3.setText("You have entered wrong password");
		}
		}
		catch(Exception e)
		{
			System.out.print("EXCEPTION="+e);
		}
	}
	
	public void actionPerformed(ActionEvent ae) 
	{
		try {
		if(ae.getSource()==b1)
		{
			String username=t.getText();
			String password=String.valueOf(p.getPassword());
			login(username,password);
		}
		}
		catch(Exception e)
		{
			System.out.println("Exception is"+e);
		}
		if(ae.getSource()==b2)
		{
			t.setText("");
			p.setText("");
		}
	}
	public static void main(String args[]) 
	{
		loginframe frame=new loginframe();
		frame.setSize(500,500);		
		
	}
}