package Clients;




import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class EnregistrementClients extends JFrame{
	//Les variables
	ConnectionClients con=new ConnectionClients();
	String photoPath=null;
	byte[] userimage=null;
	private GridBagLayout gbl=new GridBagLayout();
	Statement pst;
	ResultSet rs,rs2;
	JLabel lblTitre,lblcode,lblnom,lblclasse,lblsexe,image1;
	JTextField txtcode,txtnom;
	JComboBox combosexe,comboclasse;
	JButton btnenregistrer ,btnsupprimer,btntelecharger;
	JTable table,table1;
	JScrollPane scroll,scroll1;
	
	  public void init() {
		   table1=new JTable();
		   scroll=new JScrollPane();
		   scroll.setBounds(10,280,770,130);
		   scroll.setViewportView(table1);
	   }
	
   public EnregistrementClients() {
	   super.setTitle("Enregistrement Des Clients");
	   super.setSize(800,450);
	   super.setLocationRelativeTo(null);
	   super.setResizable(false);
	   super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   JPanel pn=new JPanel();
	 
	   pn.setLayout(null);
	  pn.setBackground(new Color(200,210,220));
	  add(pn);
	     //Partie Titre
	   lblTitre=new JLabel("PARTIE D'ENREGISTREMENT");
	 
	   lblTitre.setBounds(250,10,800,30);
	   lblTitre.setFont(new Font("Arial",Font.BOLD,24));
	   lblTitre.setForeground(new Color(0,0,205));
	   pn.add(lblTitre);
	   // CodeEleve
	   lblcode = new JLabel("NUMERO ELEVE");
	   lblcode.setBounds(60,60,800,30);
	   lblcode.setFont(new Font("Arial",Font.BOLD,16));
       lblcode.setForeground(new Color(0,0,0));
       pn.add(lblcode);
       
       txtcode=new JTextField();
       txtcode.setBounds(200,60,150,30);
       txtcode.setFont(new Font ("Arial",Font.PLAIN,14));
      // txtcode.setForeground(new Color(0,0,0));
       pn.add(txtcode);
       // NomEleve
       lblnom=new JLabel("NOM ET PRENOM");
       lblnom.setBounds(60,100,800,30);
       lblnom.setFont(new Font("Arial",Font.BOLD,16));
       lblnom.setForeground(new Color(0,0,0));
       pn.add(lblnom); 
       
        txtnom=new JTextField();
        txtnom.setFont(new Font("Arial",Font.PLAIN,14));
        txtnom.setBounds(200,100,310,30);
        pn.add(txtnom);
        //SexeEleve
        lblsexe=new JLabel("SEXE");
        lblsexe.setBounds(60,140,800,30);
        lblsexe.setFont(new Font("Arial",Font.BOLD,16));
        lblsexe.setForeground(new Color(0,0,0));
        pn.add(lblsexe);
         combosexe=new JComboBox();
         combosexe.setBounds(200,140,100,30);
         combosexe.setFont(new Font("Arial",Font.PLAIN,14));
         combosexe.addItem("");
         combosexe.addItem("Masculin");
         combosexe.addItem("Feminin");
         pn.add(combosexe);
         
         
        
        //ClasseEleve
        lblclasse=new JLabel("CLASSE");
        lblclasse.setBounds(60,180,800,30);
        lblclasse.setFont(new Font("Arial",Font.BOLD,16));
        lblclasse.setForeground(new Color(0,0,0));
        pn.add(lblclasse);
        
	   comboclasse=new JComboBox();
	   comboclasse.setBounds(200,180,100,30);
	   comboclasse.setFont(new Font("Arial",Font.BOLD,14));
	   comboclasse.addItem("");
	   comboclasse.addItem("6EME");
	   comboclasse.addItem("5EME");
	   comboclasse.addItem("4EME");
	   comboclasse.addItem("3EME");
	   comboclasse.addItem("2NDE L");
	   comboclasse.addItem("2NDE S");
	   comboclasse.addItem("1ERE L");
	   comboclasse.addItem("1ERE S");
	   pn.add(comboclasse);
	   
	   //Photo 
	   image1=new JLabel();
	   image1.setBounds(530,60,150,150);
	   image1.setFont(new Font ("Arial",Font.BOLD,16));
	   image1.setBackground(new java.awt.Color(225,0,0));
	  
	   image1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	   image1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
	   image1.addMouseListener(new java.awt.event.MouseAdapter() {
		   public void mouseClicked(java.awt.event.MouseEvent evt) {
			   image1MouseClicked(evt);
		   }
		   
		   private void image1MouseClicked(MouseEvent evt) {
			   JFileChooser pic=new JFileChooser();
			   pic.showOpenDialog(null);
			   File picture=pic.getSelectedFile();
			   photoPath =picture.getAbsolutePath();
		
			   try {
				
				   ImageIcon imageIcon =new ImageIcon(photoPath);
				   Image image=imageIcon.getImage().getScaledInstance(150, 150,Image.SCALE_DEFAULT);
				   imageIcon=new ImageIcon(image);
			       image1.setIcon(imageIcon);
			   }
			       catch(Exception e) {
			    	   e.printStackTrace();
			       }
			
			   }
		   }
	   
	   );
	   pn.add(image1); 
	// bouton d'enregistrement
	   btnenregistrer =new JButton ("ENREGISTRER");
	   btnenregistrer.setBounds(200,230,150,30);
	   btnenregistrer.setFont(new Font("Arial",Font.BOLD,15));
	   btnenregistrer.setForeground(Color.BLACK);
	   btnenregistrer.setBackground(new Color(173,216,230));
	   btnenregistrer.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent ev) {
			   String num,nom,sexe,classe;
			   num=txtcode.getText();
			   nom=txtnom.getText();
			   sexe=combosexe.getSelectedItem().toString();
			   classe=comboclasse.getSelectedItem().toString();
			   String rq ="INSERT INTO tp_eleve2 (code,nom,sexe,classe,photo_url) VALUES (?,?,?,?,?)";
			   try {
				   PreparedStatement ps=con.maConnection().prepareStatement(rq);
				   ps.setString(1, num);
				   ps.setString(2, nom);
				   ps.setString(3, sexe);
				   ps.setString(4, classe);
				   ps.setString(5, photoPath);
				   ps.executeUpdate();
				   JOptionPane.showMessageDialog(null, "Eleve Enregistrer",null,JOptionPane.INFORMATION_MESSAGE);
				   con.maConnection().close();
			   }catch(SQLException ex) {
				   JOptionPane.showMessageDialog(null, "Erreur"+ex.getMessage(),null,JOptionPane.ERROR_MESSAGE);
			   }
			   dispose();
			   EnregistrementClients elv=new EnregistrementClients();
			  elv.setVisible(true);
		   }
	   });
	   pn.add(btnenregistrer);
	   //bouton suppression
	   btnsupprimer =new JButton("SUPPRIMER");
	   btnsupprimer.setBounds(370,230,150,30);
	   btnsupprimer.setFont(new Font("Arial",Font.BOLD,15));
	   btnsupprimer.setForeground(Color.BLACK);
	   btnsupprimer.setBackground(new Color(173,216,230));
	   btnsupprimer.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent ev) {
			   String num;
			   num=txtcode.getText();
			   String rq="delete from tp_eleve2 where code ='"+num +"'";
			   try {
				   pst=con.maConnection().createStatement();
				   pst.executeUpdate(rq);
				   JOptionPane.showMessageDialog(null, "Eleve Supprimer",null,JOptionPane.INFORMATION_MESSAGE);
				   con.maConnection().close();
				   
			   }catch(SQLException ex) {
				   JOptionPane.showMessageDialog(null,"Erreur!"+ex.getMessage(),null,JOptionPane.ERROR_MESSAGE);
				   
			   }
			   dispose();
			   EnregistrementClients elv =new EnregistrementClients();
			   elv.setVisible(true);
		   }
	   });
	   pn.add(btnsupprimer);
	   // bouton recherche
	   btntelecharger =new JButton("RECHERCHE");
	   btntelecharger.setBounds(360,60,150,30);
	   btntelecharger.setFont(new Font("Arial",Font.BOLD,14));
	   btntelecharger.setForeground(Color.BLACK);
	   btntelecharger.setBackground(new Color(173,216,230));
	   btntelecharger.addActionListener(new java.awt.event.ActionListener(){
		   public void actionPerformed (java.awt.event.ActionEvent evt) {
			  String num;
			   num=txtcode.getText();
			   int codeEleve=Integer.parseInt(txtcode.getText());
			   try {
				   String rq="select * from tp_eleve2 where code =?";
				   PreparedStatement ps =con.maConnection().prepareStatement(rq);
				   ps.setString(1, num);
				   rs=ps.executeQuery();
				 
				   if(rs.next()==false) {
					   JOptionPane.showMessageDialog(null, "Matricule n'existe pas",null,JOptionPane.ERROR_MESSAGE);
					   txtcode.setText("");
				   }else {
					   txtnom.setText(rs.getString("nom").trim());
					   combosexe.setSelectedItem(rs.getString("sexe").trim());
					   comboclasse.setSelectedItem(rs.getString("classe").trim());
					   
					   String photoUrl =rs.getString("photo_url");
					   if(photoUrl !=null && !photoUrl.isEmpty()) {
						   ImageIcon imageIcon=new ImageIcon(photoUrl);
						   Image image=imageIcon.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT);
						   imageIcon=new ImageIcon(image);
						   image1.setIcon(imageIcon);
						  
						
					    }else {
					    	System.out.println("L'image est null");
					    }
					      }}
					       
					   catch(Exception e) {
						   JOptionPane.showMessageDialog(null, "Erreur "+e.getMessage(),null,JOptionPane.ERROR_MESSAGE);
					   }
				   
				   
			  }
			   
		   
	   });
	   pn.add(btntelecharger);
	  
	   // Liste des eleves 
	   DefaultTableModel mode1=new DefaultTableModel();
	   init();
	   pn.add(scroll);
	   mode1.addColumn("Code");
	   mode1.addColumn("Nom et Prenom");
	   mode1.addColumn("Sexe");
	   mode1.addColumn("Classe");
	   
	   table1.setModel(mode1);
	   String sql="select * from tp_eleve2 order by code asc";
	   try {
		   pst=con.maConnection().createStatement();
		   rs=pst.executeQuery(sql);
		   while(rs.next()) {
			   mode1.addRow(new Object []{
				   rs.getString("code"),
				   rs.getString("nom"),
				   rs.getString("sexe"),
				   rs.getString("classe"),
				   
			   });
		   }
	   }catch(Exception e) {
		   JOptionPane.showMessageDialog(null, "Erreur!",null,JOptionPane.ERROR_MESSAGE);
	   }
	   
	  table1.addMouseListener(new java.awt.event.MouseAdapter() {
		  public void mouseReleased (java.awt.event.MouseEvent evt ) {
			  table1MouseReleased(evt);
		  }
		  private void table1MouseReleased(MouseEvent evt) {
			  int selectionner =table1.getSelectedRow();
			  DefaultTableModel model =(DefaultTableModel)table1.getModel();
			  txtcode.setText(model.getValueAt(selectionner,0).toString());
			  txtnom.setText(model.getValueAt(selectionner,1).toString());
			  combosexe.setSelectedItem(model.getValueAt(selectionner,2).toString());
			  comboclasse.setSelectedItem(model.getValueAt(selectionner,3).toString());
		  }
	  });
   }
   public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
		
			   EnregistrementClients en= new EnregistrementClients();
			   en.setVisible(true);
		   
	} 
  
}

