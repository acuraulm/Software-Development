package classes;
/* Utilities */
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/* Class variables */
public class Blackjack {
	Dealer dealer = new Dealer();
	Player player = new Player();
	Deck deck = new Deck();
	int bet = 0;
	int counterPlayer = 3;
	int counterDealer = 3;
	private String sBet;
	private String winner;
	private JFrame frame;
	private JPanel startingPanel;
	private JLabel lblStartBackground;
	private JLabel lblNewGame;
	private JTextField txtPlayer;
	private JPanel gamePanel;
	private JLabel lblPlayerAccount;
	private JLabel lblPlayerName;
	private JLabel lblCurrentBet;
	private JLabel lblCardPlayer;
	private JLabel lblCardPlayer2;
	private JLabel lblCardPlayer3;
	private JLabel lblCardPlayer4;
	private JLabel lblCardPlayer5;
	private JLabel lblCardPlayer6;
	private JLabel lblCardDealer;
	private JLabel lblCardDealer2;
	private JLabel lblCardDealer3;
	private JLabel lblCardDealer4;
	private JLabel lblCardDealer5;
	private JLabel lblCardDealer6;
	private JLabel lblPlayerPoints;
	private JLabel lblDealerPoints;
	private JLabel lblBet100;
	private JLabel lblBet50;
	private JLabel lblBet10;
	private JLabel lblBet5;
	private JLabel lblRecharge;
	private JLabel lblReset;
	private JLabel lblStand;
	private JLabel lblSurrender;
	private JLabel lblDouble;
	private JLabel lblHit;
	private JLabel lblPlaceBet;
	private JLabel lblGameBackground;
	private JLabel lblContentBackground;
	private JLabel lblPlayerStatus;
	private JLabel lblDealerStatus;
/*Launch the application.*/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Blackjack window = new Blackjack();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
/* Create the application. */
	public Blackjack() {
		initialize();
	}
/*Initialize the contents of the frame.*/
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);	
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 1614, 937);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(false);
	    frame.getContentPane().setLayout(null);    	    
	    
/*Panels */																																								/*Panels */	    
	    
//Starting panel    
			    startingPanel = new JPanel();
			    startingPanel.setBounds(555, 0, 498, 910);
			    frame.getContentPane().add(startingPanel);
			    startingPanel.setBackground(SystemColor.menu);
			    startingPanel.setVisible(true);
			    startingPanel.setLayout(null);
			    startingPanel.setOpaque(false);    
			    gamePanel = new JPanel(); // Actual game panel
			    gamePanel.setBounds(0, 0, 1610, 910);
			    frame.getContentPane().add(gamePanel);
			    gamePanel.setLayout(null);
			    gamePanel.setOpaque(false);
			    gamePanel.setVisible(false);
			    
			    txtPlayer = new JTextField();
			    txtPlayer.addKeyListener(new KeyAdapter() {
			    	@Override
			    	public void keyPressed(KeyEvent arg0) {
			    		if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
			    	player.setPlayerName(txtPlayer.getText());
		    		lblPlayerName.setText(player.getPlayerName());
		    		gamePanel.setVisible(true);
		    		startingPanel.setVisible(false);
			    	
			    }
			    	}
			    });
			    txtPlayer.setForeground(Color.WHITE);
			    txtPlayer.setBounds(133, 703, 235, 35);
			    txtPlayer.setBackground(Color.BLACK);
			    txtPlayer.setHorizontalAlignment(SwingConstants.CENTER);
			    txtPlayer.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 36));
			    startingPanel.add(txtPlayer);
			    txtPlayer.setColumns(10);
			    
			    lblStartBackground = new JLabel("New label");
			    lblStartBackground.setVerticalAlignment(SwingConstants.TOP);
			    lblStartBackground.setIcon(new ImageIcon(Blackjack.class.getResource("/images/enterName.png")));
			    lblStartBackground.setBounds(107, 128, 287, 84);
			    startingPanel.add(lblStartBackground);
			    
			    lblNewGame = new JLabel();
			    lblNewGame.setIcon(new ImageIcon(Blackjack.class.getResource("/images/blackspade.png")));
			    lblNewGame.setBounds(107, 282, 275, 275);
			    lblNewGame.setVisible(true);
			    startingPanel.add(lblNewGame);
	    

/* Game Panel */	    
	    	    
	    	    lblPlayerStatus = new JLabel();
	    	    lblPlayerStatus.setBounds(54, 287, 382, 208);
	    	    gamePanel.add(lblPlayerStatus);
	    	    
	    	    lblDealerStatus = new JLabel();
	    	    lblDealerStatus.setBounds(1172, 287, 382, 208);
	    	    gamePanel.add(lblDealerStatus);
	    	    
	    	    lblCardDealer6 = new JLabel();
	    	    lblCardDealer6.setBounds(1420, 317, 110, 150);
	    	    gamePanel.add(lblCardDealer6);
	       
	    	    lblCardDealer5 = new JLabel();
	    	    lblCardDealer5.setBounds(1380, 317, 110, 150);
	    	    gamePanel.add(lblCardDealer5);
	    	    
	    	    lblCardDealer4 = new JLabel();
	    	    lblCardDealer4.setBounds(1350, 317, 110, 150);
	    	    gamePanel.add(lblCardDealer4);
	    	    
	    	    lblCardDealer3 = new JLabel();
	    	    lblCardDealer3.setBounds(1310, 317, 110, 150);
	    	    gamePanel.add(lblCardDealer3);
	    	    
	    	    lblCardDealer2 = new JLabel();
	    	    lblCardDealer2.setBounds(1270, 317, 110, 150);
	    	    gamePanel.add(lblCardDealer2);
	    	    lblCardDealer2.setIcon(new ImageIcon(Blackjack.class.getResource("/images/back.png")));
	    	    lblCardDealer2.setVisible(false);
	    	    
	    	    lblCardDealer = new JLabel();
	    	    lblCardDealer.setBounds(1230, 317, 110, 150);
	    	    gamePanel.add(lblCardDealer);
	    	    
	    	    lblCardPlayer6 = new JLabel();
	    	    lblCardPlayer6.setBounds(304, 317, 110, 150);
	    	    gamePanel.add(lblCardPlayer6);
	    	    
	    	    lblCardPlayer5 = new JLabel();
	    	    lblCardPlayer5.setBounds(264, 317, 110, 150);
	    	    gamePanel.add(lblCardPlayer5);
	    	    
	    	    lblCardPlayer4 = new JLabel();
	    	    lblCardPlayer4.setBounds(224, 317, 110, 150);
	    	    gamePanel.add(lblCardPlayer4);
	    	    
	    	    lblCardPlayer3 = new JLabel();
	    	    lblCardPlayer3.setBounds(184, 317, 110, 150);
	    	    gamePanel.add(lblCardPlayer3);
	    	    
	    	    lblCardPlayer2 = new JLabel();
	    	    lblCardPlayer2.setBounds(144, 317, 110, 150);
	    	    gamePanel.add(lblCardPlayer2);
	    	    
	    	    lblCardPlayer = new JLabel();
	    	    lblCardPlayer.setVisible(true);
	    	    lblCardPlayer.setBounds(104, 317, 110, 150);
	    	    gamePanel.add(lblCardPlayer);
	    	    
	    	    lblDealerPoints = new JLabel();
	    	    lblDealerPoints.setHorizontalAlignment(SwingConstants.CENTER);
	    	    lblDealerPoints.setForeground(Color.GREEN);
	    	    lblDealerPoints.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 41));
	    	    lblDealerPoints.setBounds(1028, 341, 120, 87);
	    	    gamePanel.add(lblDealerPoints);
	    	    
	    	    lblPlayerPoints = new JLabel();
	    	    lblPlayerPoints.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 41));
	    	    lblPlayerPoints.setHorizontalAlignment(SwingConstants.CENTER);
	    	    lblPlayerPoints.setForeground(Color.GREEN);
	    	    lblPlayerPoints.setBounds(454, 341, 120, 87);
	    	    gamePanel.add(lblPlayerPoints);
	    	    
	    	    lblPlayerName = new JLabel();
	    	    lblPlayerName.setBounds(553, 773, 250, 54);
	    	    gamePanel.add(lblPlayerName);
	    	    lblPlayerName.setForeground(Color.WHITE);
	    	    lblPlayerName.setBackground(Color.RED);
	    	    lblPlayerName.setFont(new Font("Lucida Sans Typewriter", Font.BOLD | Font.ITALIC, 45));
	    	    lblPlayerName.setHorizontalAlignment(SwingConstants.CENTER);
	    	 
	    	    lblPlayerAccount = new JLabel(""+player.getPlayerCash());
	    	    lblPlayerAccount.setBounds(702, 773, 407, 54);
	    	    gamePanel.add(lblPlayerAccount);
	    	    lblPlayerAccount.setForeground(Color.WHITE);
	    	    lblPlayerAccount.setHorizontalAlignment(SwingConstants.CENTER);
	    	    lblPlayerAccount.setFont(new Font("Lucida Sans Typewriter", Font.BOLD | Font.ITALIC, 45));
	    
			    lblCurrentBet = new JLabel("0");
			    lblCurrentBet.setBounds(607, 661, 136, 45);
			    gamePanel.add(lblCurrentBet);
			    lblCurrentBet.setForeground(Color.WHITE);
			    lblCurrentBet.setBackground(Color.RED);
			    lblCurrentBet.setHorizontalAlignment(SwingConstants.CENTER);
			    lblCurrentBet.setFont(new Font("Lucida Sans Typewriter", Font.BOLD | Font.ITALIC, 35));
			    
/* Playing labels */
			    
			    lblPlaceBet = new JLabel();
			    lblPlaceBet.setIcon(new ImageIcon(Blackjack.class.getResource("/images/InactiveBet.png")));
			    lblPlaceBet.setBounds(770, 645, 231, 61);
			    gamePanel.add(lblPlaceBet);
			    
			    lblHit = new JLabel();
			    lblHit.setIcon(new ImageIcon(Blackjack.class.getResource("/images/InactiveHit.png")));
			    lblHit.setBounds(1035, 768, 113, 101);
			    lblHit.setVisible(false);
			    gamePanel.add(lblHit);	
			    
			    lblDouble = new JLabel();
			    lblDouble.setIcon(new ImageIcon(Blackjack.class.getResource("/images/InactiveDouble.png")));
			    lblDouble.setBounds(1178, 768, 113, 101);
			    lblDouble.setVisible(false);
			    gamePanel.add(lblDouble);
			    
			    lblSurrender = new JLabel();
			    lblSurrender.setIcon(new ImageIcon(Blackjack.class.getResource("/images/InactiveSurrender.png")));
			    lblSurrender.setBounds(1325, 768, 113, 101);
			    lblSurrender.setVisible(false);
			    gamePanel.add(lblSurrender);
			    
			    lblStand = new JLabel();
			    lblStand.setIcon(new ImageIcon(Blackjack.class.getResource("/images/InactiveStand.png")));
			    lblStand.setBounds(1471, 768, 113, 101);
			    lblStand.setVisible(false);
			    gamePanel.add(lblStand);
			    
			    lblReset = new JLabel();
			    lblReset.setIcon(new ImageIcon(Blackjack.class.getResource("/images/InactiveReset.png")));
			    lblReset.setBounds(531, 738, 39, 142);
			    lblReset.setVisible(false);
			    gamePanel.add(lblReset);	
			    
			    lblRecharge = new JLabel();
			    lblRecharge.setIcon(new ImageIcon(Blackjack.class.getResource("/images/InactiveRecharge.png")));
			    lblRecharge.setBounds(613, 845, 390, 35);
			    lblRecharge.setVisible(false);
			    gamePanel.add(lblRecharge);	    
			    lblBet5 = new JLabel();
			    
			    lblBet5.setIcon(new ImageIcon(Blackjack.class.getResource("/images/bet5.png")));
			    lblBet5.setBounds(10, 768,105, 104);
			    gamePanel.add(lblBet5);   
			    
			    lblBet10 = new JLabel();
			    lblBet10.setIcon(new ImageIcon(Blackjack.class.getResource("/images/bet10.png")));
			    lblBet10.setBounds(144, 765,105, 104);
			    gamePanel.add(lblBet10);
			    
			    lblBet50 = new JLabel();
			    lblBet50.setIcon(new ImageIcon(Blackjack.class.getResource("/images/bet50.png")));
			    lblBet50.setBounds(264, 765,105, 104);
			    gamePanel.add(lblBet50);
			    
			    lblBet100 = new JLabel();
			    lblBet100.setIcon(new ImageIcon(Blackjack.class.getResource("/images/bet100.png")));
			    lblBet100.setBounds(389, 765,105, 104);
			    gamePanel.add(lblBet100);
/* Background labels */
			    
			    lblGameBackground = new JLabel();
			    lblGameBackground.setIcon(new ImageIcon(Blackjack.class.getResource("/images/GameBackground.png")));
			    lblGameBackground.setBounds(0, 0, 1610, 910);
			    gamePanel.add(lblGameBackground);
			    
			    lblContentBackground = new JLabel();
			    lblContentBackground.setIcon(new ImageIcon(Blackjack.class.getResource("/images/Background.png")));
			    lblContentBackground.setBounds(0, 0, 1610, 910);
			    frame.getContentPane().add(lblContentBackground);
	    
/* Mouse listeners */
	    
			    lblNewGame.addMouseListener(new MouseAdapter() {
			    	@Override
			    	public void mouseClicked(MouseEvent e) {
			    		player.setPlayerName(txtPlayer.getText());
			    		lblPlayerName.setText(player.getPlayerName());
			    		gamePanel.setVisible(true);
			    		startingPanel.setVisible(false);
			    	}
			    	@Override
			    	public void mouseEntered(MouseEvent e) {
			    		lblNewGame.setIcon(new ImageIcon(Blackjack.class.getResource("/images/blackspadeInactive.png")));
			    	}
			    	@Override
			    	public void mouseExited(MouseEvent e) {
			    		lblNewGame.setIcon(new ImageIcon(Blackjack.class.getResource("/images/blackspade.png")));
			    	}
			    });	  
			    lblBet5.addMouseListener(new MouseAdapter() {
			    	@Override
			    	public void mouseClicked(MouseEvent arg0){
			    		increaseBet(5);
			    		lblCurrentBet.setText(sBet);
			    		lblReset.setVisible(true);
			    		lblPlaceBet.setVisible(true);
			    	}
			    	@Override
			    	public void mouseEntered(MouseEvent arg0) {
			    		 lblBet5.setIcon(new ImageIcon(Blackjack.class.getResource("/images/bet5A.png")));
			    	}
			    	@Override
			    	public void mouseExited(MouseEvent arg0) {
			    		 lblBet5.setIcon(new ImageIcon(Blackjack.class.getResource("/images/bet5.png")));
			    	}
			    });
			    lblBet10.addMouseListener(new MouseAdapter() {
			    	@Override
			    	public void mouseClicked(MouseEvent arg0){
			    		increaseBet(10);
			    		lblCurrentBet.setText(sBet);
			    		lblReset.setVisible(true);
			    		lblPlaceBet.setVisible(true);
			    	}
			    	@Override
			    	public void mouseEntered(MouseEvent arg0) {
			    		lblBet10.setIcon(new ImageIcon(Blackjack.class.getResource("/images/bet10A.png")));
			    	}
			    	@Override
			    	public void mouseExited(MouseEvent arg0) {
			    		lblBet10.setIcon(new ImageIcon(Blackjack.class.getResource("/images/bet10.png")));
			    	}
			    });
			    lblBet50.addMouseListener(new MouseAdapter() {
			    	@Override
			    	public void mouseClicked(MouseEvent arg0){
			    		increaseBet(50);
			    		lblCurrentBet.setText(sBet);
			    		lblReset.setVisible(true);
			    		lblPlaceBet.setVisible(true);
			    	}
			    	@Override
			    	public void mouseEntered(MouseEvent arg0) {
			    		 lblBet50.setIcon(new ImageIcon(Blackjack.class.getResource("/images/bet50A.png")));
			    	}
			    	@Override
			    	public void mouseExited(MouseEvent arg0) {
			    		 lblBet50.setIcon(new ImageIcon(Blackjack.class.getResource("/images/bet50.png")));
			    	}
			    });
			    lblBet100.addMouseListener(new MouseAdapter() {
			    	@Override
			    	public void mouseClicked(MouseEvent arg0){
			    		increaseBet(100);
			    		lblCurrentBet.setText(sBet);
			    		lblReset.setVisible(true);
			    		lblPlaceBet.setVisible(true);
			    	}
			    	@Override
			    	public void mouseEntered(MouseEvent arg0) {
			    		lblBet100.setIcon(new ImageIcon(Blackjack.class.getResource("/images/bet100A.png")));
			    	}
			    	@Override
			    	public void mouseExited(MouseEvent arg0) {
			    		lblBet100.setIcon(new ImageIcon(Blackjack.class.getResource("/images/bet100.png")));
			    	}
			    });		    
			    lblHit.addMouseListener(new MouseAdapter() {
			    	@Override
			    	public void mouseEntered(MouseEvent arg0) {
			    		lblHit.setIcon(new ImageIcon(Blackjack.class.getResource("/images/ActiveHit.png")));
			    	}
			    	@Override
			    	public void mouseExited(MouseEvent arg0) {
			    		lblHit.setIcon(new ImageIcon(Blackjack.class.getResource("/images/InactiveHit.png")));
			    	}
			    	@Override
			    	public void mouseClicked(MouseEvent arg0){
			    		 playerHit();
			    	}
			    	
			    });		    
			    lblDouble.addMouseListener(new MouseAdapter() {
			    	@Override
			    	public void mouseClicked(MouseEvent arg0){
			    		playerDouble();
			    	}
			    	@Override
			    	public void mouseEntered(MouseEvent arg0) {
			    		lblDouble.setIcon(new ImageIcon(Blackjack.class.getResource("/images/ActiveDouble.png")));
			    	}
			    	@Override
			    	public void mouseExited(MouseEvent arg0) {
			    		lblDouble.setIcon(new ImageIcon(Blackjack.class.getResource("/images/InactiveDouble.png")));
			    	}
			    });
			    lblSurrender.addMouseListener(new MouseAdapter() {
			    	@Override
			    	public void mouseClicked(MouseEvent arg0){
			    		playerSurrender();
			    	}
			    	@Override
			    	public void mouseEntered(MouseEvent arg0) {
			    		lblSurrender.setIcon(new ImageIcon(Blackjack.class.getResource("/images/ActiveSurrender.png")));
			    	}
			    	@Override
			    	public void mouseExited(MouseEvent arg0) {
			    		lblSurrender.setIcon(new ImageIcon(Blackjack.class.getResource("/images/InactiveSurrender.png")));
			    	}
			    });
			    lblStand.addMouseListener(new MouseAdapter() {
			    	public void mouseClicked(MouseEvent arg0){
			    		playerStand();
			    	}
			    	@Override
			    	public void mouseEntered(MouseEvent arg0) {
			    		lblStand.setIcon(new ImageIcon(Blackjack.class.getResource("/images/ActiveStand.png")));
			    	}
			    	@Override
			    	public void mouseExited(MouseEvent arg0) {
			    		lblStand.setIcon(new ImageIcon(Blackjack.class.getResource("/images/InactiveStand.png")));
			    	}
			    });
			    lblReset.addMouseListener(new MouseAdapter() {
			    	@Override
			    	public void mouseClicked(MouseEvent arg0){
			    		bet = 0;
			    		sBet =""+bet;
			    		lblCurrentBet.setText(sBet);
			    		lblReset.setVisible(false);
			    	}
			    	@Override
			    	public void mouseEntered(MouseEvent arg0) {
			    		lblReset.setIcon(new ImageIcon(Blackjack.class.getResource("/images/ActiveReset.png")));
			    	}
			    	@Override
			    	public void mouseExited(MouseEvent arg0) {
			    		lblReset.setIcon(new ImageIcon(Blackjack.class.getResource("/images/InactiveReset.png")));
			    	}
			    });
			    lblRecharge.addMouseListener(new MouseAdapter() {
			    	@Override
			    	public void mouseClicked(MouseEvent arg0){
			    		rechargeAccount();
			    	}
			    	@Override
			    	public void mouseEntered(MouseEvent arg0) {
			    		lblRecharge.setIcon(new ImageIcon(Blackjack.class.getResource("/images/ActiveRecharge.png")));
			    	}
			    	@Override
			    	public void mouseExited(MouseEvent arg0) {
			    		lblRecharge.setIcon(new ImageIcon(Blackjack.class.getResource("/images/InactiveRecharge.png")));
			    	}
			    });			    
			    lblPlaceBet.addMouseListener(new MouseAdapter() {
			    	@Override
			    	public void mouseClicked(MouseEvent e) {
			    		placeBet();	    		
			    	}
			    	@Override
			    	public void mouseExited(MouseEvent arg0) {
			    		lblPlaceBet.setIcon(new ImageIcon(Blackjack.class.getResource("/images/InactiveBet.png")));
			    	}
			    	@Override
			    	public void mouseEntered(MouseEvent arg0) {
			    		lblPlaceBet.setIcon(new ImageIcon(Blackjack.class.getResource("/images/ActiveBet.png")));
			    	}
			    });  
	    
	    
/* Methods */																																						/* Methods */
			}
			private void comparePoints(){
				if (player.getPlayerPoints() > dealer.getDealerPoints() ){
						lblPlayerStatus.setIcon(new ImageIcon(Blackjack.class.getResource("/images/winner.png")));
				}
				else if (dealer.getDealerPoints() > player.getPlayerPoints() ){
					if(dealer.getDealerPoints() < 22)
						lblDealerStatus.setIcon(new ImageIcon(Blackjack.class.getResource("/images/winner.png")));
					else if(player.getPlayerPoints() < 22 && dealer.getDealerPoints() > 21)
			 			lblPlayerStatus.setIcon(new ImageIcon(Blackjack.class.getResource("/images/winner.png")));
			 }
				else if (player.getPlayerPoints() > 21){
					lblPlayerStatus.setIcon(new ImageIcon(Blackjack.class.getResource("/images/busted.png")));
					lblPlayerPoints.setForeground(Color.RED);
				}
				else if (dealer.getDealerPoints() > 21){
					lblDealerStatus.setIcon(new ImageIcon(Blackjack.class.getResource("/images/busted.png")));
					lblDealerPoints.setForeground(Color.RED);
				}
				else if ((dealer.getDealerPoints() == player.getPlayerPoints()) && (player.getPlayerPoints() < 21)){
					lblPlayerStatus.setIcon(new ImageIcon(Blackjack.class.getResource("/images/push.png")));
				    lblDealerStatus.setIcon(new ImageIcon(Blackjack.class.getResource("/images/push.png")));
				}
				lblPlayerPoints.setText("" + player.getPlayerPoints() );
				lblDealerPoints.setText("" + dealer.getDealerPoints() );
			}
			private void addWinnerDouble(){
				if(winner == "player")
					player.addPlayerCash(bet*4);
			}
			private boolean isBroke(){
				if(player.getPlayerCash() == 0)
					return true;
				else
					return false;
			}
			private void pushPlayer(){
				player.addPlayerCash(bet);			
			}
			private void checkWinner(){
				if ((player.getPlayerPoints() > dealer.getDealerPoints() && player.getPlayerPoints() < 22) || (player.getPlayerPoints() < 22 && dealer.getDealerPoints() > 21) || (dealer.getDealerPoints() < 22 && player.getPlayerPoints() > dealer.getDealerPoints()))
					winner = "player";
				if ((dealer.getDealerPoints() > player.getPlayerPoints() && dealer.getDealerPoints() < 22))
					winner = "dealer";
				if ( player.getPlayerPoints() == dealer.getDealerPoints() && player.getPlayerPoints() < 22){
					winner = null;
					pushPlayer();
				}
			}
			private void addWinner(){
				if(winner == "player")
					player.addPlayerCash(bet*2);				
			}
			private void playerSurrender(){
	    		player.addPlayerCash(bet/2);
	    		lblPlaceBet.setVisible(true);
	    		lblHit.setVisible(false);
	    		lblDouble.setVisible(false);
	    		lblStand.setVisible(false);
	    		lblSurrender.setVisible(false);
	    		lblPlayerAccount.setText(""+player.getPlayerCash());
	    		
	    		checkBet();
			}
			private void playerHit(){
				 Card drawPlayer3 = deck.card();
	    		 Card drawPlayer4 = deck.card();
	    		 Card drawPlayer5 = deck.card();
	    		 Card drawPlayer6 = deck.card();
		    		lblStand.setVisible(true);
		    		switch(counterPlayer){
			    		case 3:lblCardPlayer3.setIcon(printCard(drawPlayer3));
			    				if(drawPlayer3.cardValue == 11 && (drawPlayer3.cardValue + player.getPlayerPoints()) > 21)
			    					drawPlayer3.cardValue = 1;
			    			   player.addPlayerPoints(drawPlayer3.cardValue);
			    			   lblPlayerPoints.setText("" + player.getPlayerPoints());
			    			   counterPlayer++;
			    			   lblDouble.setVisible(false);
			    			   lblSurrender.setVisible(false);
			    			   if(isBusted())
					    			betTableON();
			    			   break;
			    		case 4:lblCardPlayer4.setIcon(printCard(drawPlayer4));
					    		if(drawPlayer4.cardValue == 11 && (drawPlayer4.cardValue + player.getPlayerPoints()) > 21)
			    					drawPlayer4.cardValue = 1;
			    		player.addPlayerPoints(drawPlayer4.cardValue);
			    			    lblPlayerPoints.setText("" + player.getPlayerPoints());
			    			    counterPlayer++;
			    			    if(isBusted())
					    			betTableON();
			    			    break;
			    		case 5:lblCardPlayer5.setIcon(printCard(drawPlayer5));
					    		if(drawPlayer5.cardValue == 11 && (drawPlayer5.cardValue + player.getPlayerPoints()) > 21)
			    					drawPlayer5.cardValue = 1;
			    		player.addPlayerPoints(drawPlayer5.cardValue);
			    				lblPlayerPoints.setText("" + player.getPlayerPoints());
			    				if(isBusted())
					    			betTableON();
			    				counterPlayer++;
			    				break;
			    		case 6:lblCardPlayer6.setIcon(printCard(drawPlayer6));
			    		player.addPlayerPoints(drawPlayer6.cardValue);
			    				lblPlayerPoints.setText("" + player.getPlayerPoints());
			    				lblHit.setVisible(false);
			    				counterPlayer++;
			    				if(isBusted())
					    			betTableON();
			    				break;
			    		default: break;
		    		}
		    		if (player.getPlayerPoints() < 15)
		    			lblStand.setVisible(false);
		    		else
		    			lblStand.setVisible(true);
		    		checkBust();
		    		if(isBusted()){
		    			if(isBroke()){
						playTableOFF();
						betTableOFF();
						bet = 0;
						lblCurrentBet.setText("" + bet);
						lblPlaceBet.setVisible(false);
						lblRecharge.setVisible(true);
		    			}
					}
			}
			private void playerStand(){
				playDealer();
				comparePoints();
				betTableON();
				lblPlaceBet.setVisible(true);
				checkBust();
	    		if(isBusted()){
	    			if(isBroke()){
					playTableOFF();
					betTableOFF();
					bet = 0;
					lblCurrentBet.setText("" + bet);
					lblPlaceBet.setVisible(false);
					lblRecharge.setVisible(true);
	    			}
				}							
				playTableOFF();
				checkWinner();
				addWinner();
				lblPlayerAccount.setText("" + player.getPlayerCash());			
			}
			private void playTableOFF(){
				lblDouble.setVisible(false);
				lblSurrender.setVisible(false);
				lblHit.setVisible(false);
				lblStand.setVisible(false);
			}
			private void playTableON(){
				lblDouble.setVisible(true);
				lblSurrender.setVisible(true);
				lblHit.setVisible(true);
				lblStand.setVisible(true);
			}
			private void betTableOFF(){
				lblBet5.setVisible(false);
				lblBet10.setVisible(false);
				lblBet50.setVisible(false);
				lblBet100.setVisible(false);
				lblReset.setVisible(false);
			}
			private void betTableON(){
				lblBet5.setVisible(true);
				lblBet10.setVisible(true);
				lblBet50.setVisible(true);
				lblBet100.setVisible(true);
				lblReset.setVisible(true);
			}
			private void playerDouble(){
				player.removePlayerCash(bet);
				Card drawPlayer3 = deck.card();
				if(drawPlayer3.cardValue == 11 && (drawPlayer3.cardValue + player.getPlayerPoints()) > 21)
					drawPlayer3.cardValue = 1;
				player.addPlayerPoints(drawPlayer3.cardValue);
	    		lblCardPlayer3.setIcon(printCard(drawPlayer3));
 			    lblPlayerPoints.setText("" + player.getPlayerPoints());
 			    if (player.getPlayerPoints() < 22){
 			    	playDealer();
 			    	lblDealerPoints.setText("" + dealer.getDealerPoints());
 		    		checkWinner();
 		    		addWinnerDouble();
 			    }
 			    comparePoints();
 			    checkBust();    		
	    		lblPlayerAccount.setText("" + player.getPlayerCash());
	    		betTableON();
	    		if(isBroke()){
					playTableOFF();
					betTableOFF();
					bet = 0;
					lblCurrentBet.setText("" + bet);
					lblPlaceBet.setVisible(false);
					lblRecharge.setVisible(true);
				}
	    		playTableOFF();
	    		lblPlaceBet.setVisible(true);
	    		
			}
			private boolean isBusted(){
				if(player.getPlayerPoints()>22)
					return true;
				else
					return false;
			}
			private void checkBust(){
				if(player.playerPoints > 21){
	    			lblPlayerPoints.setForeground(Color.RED);
	    			lblPlayerStatus.setIcon(new ImageIcon(Blackjack.class.getResource("/images/busted.png")));
	    			lblStand.setVisible(false);
	    			lblHit.setVisible(false);
	    			lblPlaceBet.setVisible(true);
					}else{
		    			lblPlayerPoints.setForeground(Color.GREEN);}
				if(dealer.dealerPoints > 21){
					    lblDealerStatus.setIcon(new ImageIcon(Blackjack.class.getResource("/images/busted.png")));
		    			lblDealerPoints.setForeground(Color.RED);
					}else{
			    		lblDealerPoints.setForeground(Color.GREEN);}	
			}	
			private void playDealer(){
				while( dealer.getDealerPoints() < 16) {
	    		Card drawDealer2 = deck.card();
	    		Card drawDealer3 = deck.card();
	    		Card drawDealer4 = deck.card();
		    	Card drawDealer5 = deck.card();
		    	Card drawDealer6 = deck.card();
			    	if(dealer.dealerPoints < 17){
			    		if(drawDealer2.cardValue == 11 && (drawDealer2.cardValue + dealer.getDealerPoints()) > 21)
	    					drawDealer2.cardValue = 1;
			    		dealer.addDealerPoints(drawDealer2.cardValue);
					    lblCardDealer2.setIcon(printCard(drawDealer2));
					    lblDealerPoints.setText("" + dealer.getDealerPoints());
							if(dealer.dealerPoints < 17 && dealer.dealerPoints < player.playerPoints){
								if(drawDealer3.cardValue == 11 && (drawDealer3.cardValue + dealer.getDealerPoints()) > 21)
			    					drawDealer3.cardValue = 1;
								dealer.addDealerPoints(drawDealer3.cardValue);
								lblCardDealer3.setIcon(printCard(drawDealer3));
						    	lblDealerPoints.setText("" + dealer.getDealerPoints());
						    		if(dealer.dealerPoints < 17 && dealer.dealerPoints < player.playerPoints){
						    			if(drawDealer4.cardValue == 11 && (drawDealer4.cardValue + dealer.getDealerPoints()) > 21)
					    					drawDealer4.cardValue = 1;
						    			dealer.addDealerPoints(drawDealer4.cardValue);
						    			lblCardDealer4.setIcon(printCard(drawDealer4));
							    		lblDealerPoints.setText("" + dealer.getDealerPoints());
								    		if(dealer.dealerPoints < 17 && dealer.dealerPoints < player.playerPoints){
								    			if(drawDealer5.cardValue == 11 && (drawDealer5.cardValue + dealer.getDealerPoints()) > 21)
							    					drawDealer5.cardValue = 1;
								    			dealer.addDealerPoints(drawDealer5.cardValue);
								    			lblCardDealer5.setIcon(printCard(drawDealer5));
									    		lblDealerPoints.setText("" + dealer.getDealerPoints());
								    		}
									    		if(dealer.dealerPoints < 17 && dealer.dealerPoints < player.playerPoints){
									    			if(drawDealer6.cardValue == 11 && (drawDealer6.cardValue + dealer.getDealerPoints()) > 21)
								    					drawDealer6.cardValue = 1;
									    			dealer.addDealerPoints(drawDealer6.cardValue);
									    			lblCardDealer6.setIcon(printCard(drawDealer6));
										    		lblDealerPoints.setText("" + dealer.getDealerPoints());
									    		}
						    		}
							}
			    	}
				}
			}
			private void increaseBet(int value){
				if (bet+value <= player.playerCash){
					bet = bet + value;
				}else{
					bet = player.playerCash;
				}
				sBet =""  + bet;
			}
			private boolean checkBet(){
				if(player.getPlayerCash()>= bet && bet>0)
					return true;
				else
					return false;
			}
			private void placeBet(){
				if(checkBet()){
				resetGame();
	    		playTableON();
	    		betTableOFF();	
				Card drawPlayer = deck.card();
				Card drawDealer = deck.card();
				Card drawPlayer2 = deck.card();
			    	player.removePlayerCash(bet);
		    		player.addPlayerPoints(drawPlayer.cardValue);
		    		dealer.addDealerPoints(drawDealer.cardValue);
		    		player.addPlayerPoints(drawPlayer2.cardValue);
		    		lblCardPlayer.setIcon(printCard(drawPlayer) );
		    		lblCardDealer.setIcon(printCard(drawDealer) );
		    		lblCardPlayer2.setIcon(printCard(drawPlayer2) ); 
		    		lblCardDealer2.setVisible(true);
		    		lblPlaceBet.setVisible(false);
		    		lblPlayerPoints.setText("" + player.getPlayerPoints() );
		    		lblDealerPoints.setText("" + dealer.getDealerPoints() );
		    		lblPlayerAccount.setText("" + player.getPlayerCash() );
		    		counterPlayer = 3;
		    		counterDealer = 2;
		    		if(player.getPlayerPoints() < 15)
		    			lblStand.setVisible(false);
			    	if(bet > player.getPlayerCash())
						lblDouble.setVisible(false);
				}
					if(isBlackjack()){
						lblPlayerStatus.setIcon(new ImageIcon(Blackjack.class.getResource("/images/winner.png")));
						player.addPlayerCash(bet/4);
						player.addPlayerCash(2*bet);
						playTableOFF();
						betTableON();
						lblPlaceBet.setVisible(true);
						lblPlayerAccount.setText("" + player.getPlayerCash());
					}
			}	
			private void rechargeAccount(){
					player.playerCash = 1000;
		    		lblPlayerAccount.setText(""  + player.playerCash);
		    		lblPlaceBet.setVisible(true);
		    		betTableON();
		    		lblReset.setVisible(false);
		    		lblRecharge.setVisible(false);
			}
			private void resetTable(){
				lblPlayerStatus.setIcon(null);
				lblDealerStatus.setIcon(null);
				lblCardPlayer.setIcon(null);
				lblCardPlayer2.setIcon(null);
				lblCardPlayer3.setIcon(null);
				lblCardPlayer4.setIcon(null);
				lblCardPlayer5.setIcon(null);
				lblCardPlayer6.setIcon(null);
				lblCardDealer.setIcon(null);
				lblCardDealer3.setIcon(null);
				lblCardDealer4.setIcon(null);
				lblCardDealer5.setIcon(null);
				lblCardDealer6.setIcon(null);
				lblCardDealer2.setIcon(new ImageIcon(Blackjack.class.getResource("/images/back.png")));
				lblCardDealer2.setVisible(false);
			}
			private void resetPoints(){
				player.setPlayerPoints(0);
				dealer.setDealerPoints(0);
				lblPlayerPoints.setText(null);
				lblDealerPoints.setText(null);
				lblPlayerPoints.setForeground(Color.GREEN);
				lblDealerPoints.setForeground(Color.GREEN);
			}
			private void resetGame(){
				resetTable();
				resetPoints();
			}			
			private boolean isBlackjack(){
				if(player.getPlayerPoints() == 21)
					return true;
				else
					return false;
			}
			private ImageIcon printCard(Card card){
				ImageIcon image=new ImageIcon();
				switch(card.getCardName()){
				case "2 of Hearts": image=new ImageIcon(Blackjack.class.getResource("/images/2H.png"));
				break;
				case "3 of Hearts": image=new ImageIcon(Blackjack.class.getResource("/images/3H.png"));
				break;
				case "4 of Hearts": image=new ImageIcon(Blackjack.class.getResource("/images/4H.png"));
				break;
				case "5 of Hearts": image=new ImageIcon(Blackjack.class.getResource("/images/5H.png"));
				break;
				case "6 of Hearts": image=new ImageIcon(Blackjack.class.getResource("/images/6H.png"));
				break;
				case "7 of Hearts": image=new ImageIcon(Blackjack.class.getResource("/images/7H.png"));
				break;
				case "8 of Hearts": image=new ImageIcon(Blackjack.class.getResource("/images/8H.png"));
				break;
				case "9 of Hearts": image=new ImageIcon(Blackjack.class.getResource("/images/9H.png"));
				break;
				case "10 of Hearts": image=new ImageIcon(Blackjack.class.getResource("/images/10H.png"));
				break;
				case "Jack of Hearts": image=new ImageIcon(Blackjack.class.getResource("/images/JackH.png"));
				break;
				case "Queen of Hearts": image=new ImageIcon(Blackjack.class.getResource("/images/QueenH.png"));
				break;
				case "King of Hearts": image=new ImageIcon(Blackjack.class.getResource("/images/KingH.png"));
				break;
				case "Ace of Hearts": image=new ImageIcon(Blackjack.class.getResource("/images/AceH.png"));
				break;
				case "2 of Clubs": image=new ImageIcon(Blackjack.class.getResource("/images/2C.png"));
				break;
				case "3 of Clubs": image=new ImageIcon(Blackjack.class.getResource("/images/3C.png"));
				break;
				case "4 of Clubs": image=new ImageIcon(Blackjack.class.getResource("/images/4C.png"));
				break;
				case "5 of Clubs": image=new ImageIcon(Blackjack.class.getResource("/images/5C.png"));
				break;
				case "6 of Clubs": image=new ImageIcon(Blackjack.class.getResource("/images/6C.png"));
				break;
				case "7 of Clubs": image=new ImageIcon(Blackjack.class.getResource("/images/7C.png"));
				break;
				case "8 of Clubs": image=new ImageIcon(Blackjack.class.getResource("/images/8C.png"));
				break;
				case "9 of Clubs": image=new ImageIcon(Blackjack.class.getResource("/images/9C.png"));
				break;
				case "10 of Clubs": image=new ImageIcon(Blackjack.class.getResource("/images/10C.png"));
				break;
				case "Jack of Clubs": image=new ImageIcon(Blackjack.class.getResource("/images/JackC.png"));
				break;
				case "Queen of Clubs": image=new ImageIcon(Blackjack.class.getResource("/images/QueenC.png"));
				break;
				case "King of Clubs": image=new ImageIcon(Blackjack.class.getResource("/images/KingC.png"));
				break;
				case "Ace of Clubs": image=new ImageIcon(Blackjack.class.getResource("/images/AceC.png"));
				break;
				case "2 of Spades": image=new ImageIcon(Blackjack.class.getResource("/images/2S.png"));
				break;
				case "3 of Spades": image=new ImageIcon(Blackjack.class.getResource("/images/3S.png"));
				break;
				case "4 of Spades": image=new ImageIcon(Blackjack.class.getResource("/images/4S.png"));
				break;
				case "5 of Spades": image=new ImageIcon(Blackjack.class.getResource("/images/5S.png"));
				break;
				case "6 of Spades": image=new ImageIcon(Blackjack.class.getResource("/images/6S.png"));
				break;
				case "7 of Spades": image=new ImageIcon(Blackjack.class.getResource("/images/7S.png"));
				break;
				case "8 of Spades": image=new ImageIcon(Blackjack.class.getResource("/images/8S.png"));
				break;
				case "9 of Spades": image=new ImageIcon(Blackjack.class.getResource("/images/9S.png"));
				break;
				case "10 of Spades": image=new ImageIcon(Blackjack.class.getResource("/images/10S.png"));
				break;
				case "Jack of Spades": image=new ImageIcon(Blackjack.class.getResource("/images/JackS.png"));
				break;
				case "Queen of Spades": image=new ImageIcon(Blackjack.class.getResource("/images/QueenS.png"));
				break;
				case "King of Spades": image=new ImageIcon(Blackjack.class.getResource("/images/KingS.png"));
				break;
				case "Ace of Spades": image=new ImageIcon(Blackjack.class.getResource("/images/AceS.png"));
				break;
				case "2 of Diamonds": image=new ImageIcon(Blackjack.class.getResource("/images/2D.png"));
				break;
				case "3 of Diamonds": image=new ImageIcon(Blackjack.class.getResource("/images/3D.png"));
				break;
				case "4 of Diamonds": image=new ImageIcon(Blackjack.class.getResource("/images/4D.png"));
				break;
				case "5 of Diamonds": image=new ImageIcon(Blackjack.class.getResource("/images/5D.png"));
				break;
				case "6 of Diamonds": image=new ImageIcon(Blackjack.class.getResource("/images/6D.png"));
				break;
				case "7 of Diamonds": image=new ImageIcon(Blackjack.class.getResource("/images/7D.png"));
				break;
				case "8 of Diamonds": image=new ImageIcon(Blackjack.class.getResource("/images/8D.png"));
				break;
				case "9 of Diamonds": image=new ImageIcon(Blackjack.class.getResource("/images/9D.png"));
				break;
				case "10 of Diamonds": image=new ImageIcon(Blackjack.class.getResource("/images/10D.png"));
				break;
				case "Jack of Diamonds": image=new ImageIcon(Blackjack.class.getResource("/images/JackD.png"));
				break;
				case "Queen of Diamonds": image=new ImageIcon(Blackjack.class.getResource("/images/QueenD.png"));
				break;
				case "King of Diamonds": image=new ImageIcon(Blackjack.class.getResource("/images/KingD.png"));
				break;
				case "Ace of Diamonds": image=new ImageIcon(Blackjack.class.getResource("/images/AceD.png"));
				break;
				default : break;
				}	
				return image;
			}
}
/* End of program */