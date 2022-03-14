import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.util.ArrayList;
import java.applet.AudioClip;

public class IceCreamGame extends JFrame {

	//================================== ��� ����Ʈ =====================================

	ArrayList<PosImageIcon> iceList = new ArrayList<>();; 			// ICEGUYũ�� �̹��� 
	ArrayList<PosImageIcon> fireList = new ArrayList<>();;			// �� �̹���
	ArrayList<PosImageIcon> heartList = new ArrayList<>();;			// ���� �̹���

	//================================== �̹��� ��Ʈ�� =====================================

	final String COVER = "res/coverpage.png";						// �̹��� ��ȯ�ϱ� ���� �ϱ� ���Ͽ� ��Ʈ������ ����
	final String OUT_PAGE = "res/outside.png";
	final String RULES_PAGE = "res/rulespage.png";
	final String HINT_1 = "res/hintpage1.png";
	final String HINT_2 = "res/hintpage2.png";
	final String FIRST_FLOOR = "res/1.png";
	final String SECOND_FLOOR = "res/2.png";
	final String THIRD_FLOOR = "res/3.png";
	final String FOURTH_FLOOR = "res/4.png";
	final String FIFTH_FLOOR = "res/5.png";
	final String WIN_PAGE = "res/win.png";
	final String OVER_PAGE = "res/gameOver.png";
	final String NUGA = "src/res/������.png";
	final String BABAM = "src/res/�ٹ��.png";
	final String BRAVO = "src/res/�ζ���.png";
	final String SSANG = "src/res/�ֹֽ�.png";
	final String ICEGUY = "src/res/���̽�.png";
	final String COFFEE = "src/res/Ŀ��.png";
	final String CRAZY = "src/res/ũ��������.png";
	final String TANK = "src/res/��ũ����.png";
	final String PASI = "src/res/�Ľ�����.png";
	final String HODU = "src/res/ȣ�θ���.png";
	final String KEY = "src/res/key.png";
	final String FIRE = "src/res/fire.png";
	final String LEFT1 = "src/res/left1.png";
	final String LEFT2 = "src/res/left2.png";
	final String LEFT3 = "src/res/left3.png";
	final String RIGHT1 = "src/res/right1.png";
	final String RIGHT2 = "src/res/right2.png";
	final String RIGHT3 = "src/res/right3.png";
	final String STOP = "src/res/stop.png";
	final String HEART = "src/res/heart.png";
	final String CLEAR = "src/res/clear.png";
	final String TIME = "src/res/time.png";

	//==================================== ���� ��Ʈ�� ======================================

	final String START_SOUND = "res/bgm.wav";						// ����
	final String WIN_SOUND = "res/winSound.wav";
	final String LOSE_SOUND = "res/loseSound.wav";
	final String X_SOUND = "res/Duck.wav";
	final String WOW = "res/Wow.wav";

	//=================================== ������ ��� ========================================

	final int WIN_WIDTH = 690; 		// ��ü frame�� ��
	final int WIN_HEIGHT = 577; 	// ��ü frame�� ����

	//=================================== ������ ���� ========================================

	int sec = 100;					// �ð���
	int dex;						// ���̽�����Ʈ���� �� ��° ���̽�ũ���� �����ߴ��� �˱� ���� ���� 				
	int panel;						// �гο� ���� ������ ���̽�ũ���� �޶� �� �� �г����� �˱� ���� ����
	int moveCount = 0;				// ĳ������ �������� �˾ƺ��� ���� ����

	//================================= �Ҹ����� ����===========================================

	boolean isBabam = false;		// ����� ���� true �ƴϸ� false
	boolean isHodu = false;
	boolean isTank = false;
	boolean isCoffee = false;
	boolean isCrazy = false;
	boolean isIce = false;
	boolean isSSang = false;
	boolean isGirl = true;			// ĳ���� ���� true = ���� , false = ������
	boolean isFire = true;			// true = �ҿ� �°�, false = �ҿ� �� �´´� 
	boolean isKeyTimer = false;		// false = Ÿ�̸Ӱ� ���ư��� X true�� ���� ���ư���
	boolean isFireTimer = false;
	boolean isSecTimer = false;

	//============================ �����̹��������� ������ �̹��� ���� =================================

	PosImageIcon key;
	PosImageIcon girlL;
	PosImageIcon girlR;
	PosImageIcon timerSection;

	//============================ �̹��� ������ =================================================

	ImageIcon girlL1;				// ����
	ImageIcon girlL2;
	ImageIcon girlL3;
	ImageIcon girlR1;				// ������
	ImageIcon girlR2;
	ImageIcon girlR3;
	ImageIcon girlStop;				// ����
	ImageIcon clear;				// ���� ������

	//============================ ���̾ƿ� ���� ===================================================

	CardLayout card;

	//============================ �г� ���� ======================================================

	JPanel coverPanel;
	JPanel outPanel;
	JPanel rulesPanel;
	JPanel hint1Panel;
	JPanel hint2Panel;
	JPanel firstFloor;
	JPanel secondFloor;
	JPanel thirdFloor;
	JPanel fourthFloor;
	JPanel fifthFloor;
	JPanel winPanel;
	JPanel gameOverPanel;

	//============================= ��ư ���� =====================================================

	JButton startBtn;
	JButton start2Btn;
	JButton start3Btn;
	JButton start4Btn;
	JButton rulesBtn;
	JButton hintBtn;
	JButton nextHint;
	JButton beforeHint;

	//=========================== ���� ============================================================

	AudioClip backgroundSound;		// ���� ���
	AudioClip winSound;				// �̰��� ��
	AudioClip loseSound;			// ���� ��
	AudioClip x;					// �ҿ� �¾��� ��, ���̽�ũ���� �߸� ������ ��
	AudioClip wow;					// ���̽�ũ���� ����� ���� ��

	//========================= Ÿ�̸� ���� ========================================================

	Timer iconTimer; 				// �Ұ� ���̽�ũ�� �̹��� ��� ���
	Timer secTimer;					// �ð� �� ��� Ÿ�̸�

	//=========================== ���� ===========================================================

	public static void main(String [] args) {
		try {		
			IceCreamGame game = new IceCreamGame();
		}
		catch(Exception e){}
	}

	//========================== ������ ===========================================================

	IceCreamGame(){
		setTitle("����Ż�� ������");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setSize(WIN_WIDTH, WIN_HEIGHT);
		setLocationRelativeTo(null); 		// ������ �߾ӿ� ����
		setResizable(false); 				// ������ ������ ����

		card = new CardLayout();			
		getContentPane().setLayout(card); 	// ���̾ƿ��� ī��� �����ϰ� �����ӿ� �����

		girlL1 =new ImageIcon(LEFT1);				// �̹��� ������ ����
		girlL2 =new ImageIcon(LEFT2);
		girlL3 =new ImageIcon(LEFT3);
		girlR1 =new ImageIcon(RIGHT1);
		girlR2 =new ImageIcon(RIGHT2);
		girlR3 =new ImageIcon(RIGHT3);
		girlStop = new ImageIcon(STOP);
		clear = new ImageIcon(CLEAR);

		girlL = new PosImageIcon(LEFT2,430,240,true);			// ���� �̹��� ������ ����
		girlR = new PosImageIcon(RIGHT2,180,240,true);
		key = new PosImageIcon(KEY,260,70,true);
		timerSection = new PosImageIcon(TIME, 20, 300,true);

		iceList.add(new PosImageIcon(NUGA,465,438,true));			// ���̽� ����Ʈ�� �̹��� ���ϱ�
		iceList.add(new PosImageIcon(BABAM,387,487,true));
		iceList.add(new PosImageIcon(BRAVO,8,437,true));
		iceList.add(new PosImageIcon(SSANG,143,481,true));
		iceList.add(new PosImageIcon(ICEGUY,620,435,true));
		iceList.add(new PosImageIcon(COFFEE,65,418,true));
		iceList.add(new PosImageIcon(CRAZY,268,487,true));
		iceList.add(new PosImageIcon(TANK,506,487,true));
		iceList.add(new PosImageIcon(PASI,160,435,true));
		iceList.add(new PosImageIcon(HODU,316,438,true));

		heartList.add(new PosImageIcon(HEART,460,7,true));			// ��Ʈ ����Ʈ�� �̹��� ���ϱ�
		heartList.add(new PosImageIcon(HEART,530,7,true));
		heartList.add(new PosImageIcon(HEART,600,7,true));

		fireList.add(new PosImageIcon(FIRE,(int)(Math.random()*350+150) ,0,true)); // �� �̹��� ����Ʈ�� �߰��ϱ�

		coverPanel = new CoverPanel();		// �г� �����
		outPanel = new OutSidePanel();
		rulesPanel = new RulesPanel();
		hint1Panel = new Hint1Panel();
		hint2Panel = new Hint2Panel();
		firstFloor = new FirstFloor();
		secondFloor = new SecondFloor();
		thirdFloor = new ThirdFloor();
		fourthFloor = new FourthFloor();
		fifthFloor = new FifthFloor();
		winPanel = new WinPanel();
		gameOverPanel = new GameOverPanel();

		startBtn = new JButton();								// ��ư ����
		startBtn.setBorderPainted(false);						// �׵θ� ���ֱ�
		startBtn.setContentAreaFilled(false); 					// ä��� �� ���ֱ�
		start2Btn = new JButton();
		start2Btn.setBorderPainted(false);
		start2Btn.setContentAreaFilled(false);
		start3Btn = new JButton();
		start3Btn.setBorderPainted(false);
		start3Btn.setContentAreaFilled(false);
		start4Btn = new JButton();
		start4Btn.setBorderPainted(false);
		start4Btn.setContentAreaFilled(false);
		rulesBtn = new JButton();
		rulesBtn.setBorderPainted(false);
		rulesBtn.setContentAreaFilled(false);
		hintBtn = new JButton();
		hintBtn.setBorderPainted(false);
		hintBtn.setContentAreaFilled(false);
		nextHint = new JButton();
		hintBtn.setBorderPainted(false);
		nextHint.setContentAreaFilled(false);
		beforeHint = new JButton();
		beforeHint.setBorderPainted(false);
		beforeHint.setContentAreaFilled(false);

		iconTimer = new Timer(8, new IconTimer());				// delay�� 8�� ������ ������ Ÿ�̸� ����
		secTimer = new Timer(1000, new SecTimer());				// 1�ʿ� �� ���� ���� ���� Ÿ�̸�

		backgroundSound = JApplet.newAudioClip(getClass().getResource(START_SOUND));	//	��� ����
		winSound = JApplet.newAudioClip(getClass().getResource(WIN_SOUND));				//	�̰��� ��
		loseSound = JApplet.newAudioClip(getClass().getResource(LOSE_SOUND));			//	���� ��
		x = JApplet.newAudioClip(getClass().getResource(X_SOUND));				// ���̽�ũ���� �߸� ���Ұų� �ҿ� �¾Ұų� ���̽�ũ���� ���� �ʰ� ������ ��
		wow = JApplet.newAudioClip(getClass().getResource(WOW));				// ���̽�ũ���� ����� ������ ��

		startBtn.addActionListener(new BtnListener());						// ��ư�� ������ �ޱ�
		start2Btn.addActionListener(new BtnListener());
		start3Btn.addActionListener(new BtnListener());
		start4Btn.addActionListener(new BtnListener());
		rulesBtn.addActionListener(new BtnListener());
		hintBtn.addActionListener(new BtnListener());
		nextHint.addActionListener(new BtnListener());
		beforeHint.addActionListener(new BtnListener());

		coverPanel.add(startBtn);											// �гο� ��ư �ޱ� 
		coverPanel.add(rulesBtn);
		rulesPanel.add(start2Btn);
		rulesPanel.add(hintBtn);
		hint1Panel.add(start3Btn);
		hint1Panel.add(nextHint);
		hint2Panel.add(start4Btn);
		hint2Panel.add(beforeHint);

		outPanel.addMouseListener(new IceListener());						// �гο� ���콺 ������ �ޱ�
		outPanel.addMouseMotionListener(new IceDraggListener());			// �гο� ���콺 ��� ������ �ޱ�
		firstFloor.addMouseListener(new IceListener());
		firstFloor.addMouseMotionListener(new IceDraggListener());
		secondFloor.addMouseListener(new IceListener());
		secondFloor.addMouseMotionListener(new IceDraggListener());
		thirdFloor.addMouseListener(new IceListener());
		thirdFloor.addMouseMotionListener(new IceDraggListener());
		fourthFloor.addMouseListener(new IceListener());
		fourthFloor.addMouseMotionListener(new IceDraggListener());
		fifthFloor.addMouseListener(new IceListener());
		fifthFloor.addMouseMotionListener(new IceDraggListener());
		winPanel.addMouseListener(new IceListener());
		addKeyListener(new MoveListener());									// �����ӿ� Ű������ �ޱ�


		getContentPane().add("coverPanel",coverPanel); 						// ī�巹�̾ƿ��� "coverPanel"�̶� �̸��� coverPanel�� �߰��Ѵ�
		card.show(getContentPane(), "coverPanel"); 							// ī�巹�̾ƿ����� "coverPanel"�̶� �̸��� ���� �г��� ������ �����ش�
		backgroundSound.play(); 											// ��� ���� ����

		requestFocus();
		setFocusable(true);
		setVisible(true); 

	} // IceCreamGame() end

	// ========================================== �г� Ŭ���� ===================================================

	class CoverPanel extends JPanel {
		public void paintComponent(Graphics g) {
			Image image = new ImageIcon(getClass().getResource(COVER)).getImage();  // �̹��� ��������
			g.drawImage(image, 0, 0, 684, 548, null);								// �̹��� �׸���
			startBtn.setBounds(510, 400, 155, 60);									// ���� ��ư
			rulesBtn.setBounds(510, 470, 155, 60);									// ��� ��ư

		}
	}// CoverPanel class end

	class RulesPanel extends JPanel {
		public void paintComponent(Graphics g) {	
			Image image = new ImageIcon(getClass().getResource(RULES_PAGE)).getImage();
			g.drawImage(image, 0, 0, null);
			start2Btn.setBounds(518, 480, 150, 55);
			hintBtn.setBounds(305, 445, 140, 45);
		}
	}//RulesPanel end

	class Hint1Panel extends JPanel {
		public void paintComponent(Graphics g) {	
			Image image = new ImageIcon(getClass().getResource(HINT_1)).getImage();  // �̹��� ��������
			g.drawImage(image, 0, 0, null); // �̹��� �׸���
			start3Btn.setBounds(518, 480, 150, 55);
			nextHint.setBounds(453, 40, 40, 40);
		}
	}// Hint1Panel end

	class Hint2Panel extends JPanel {
		public void paintComponent(Graphics g) {	
			Image image = new ImageIcon(getClass().getResource(HINT_2)).getImage();  // �̹��� ��������
			g.drawImage(image, 0, 0, null); // �̹��� �׸���
			start4Btn.setBounds(518, 480, 150, 55);
			beforeHint.setBounds(205, 43, 40, 40);
		}
	}// Hint2Panel end

	class OutSidePanel extends JPanel {
		public void paintComponent(Graphics g) {	
			ImageIcon image = new ImageIcon(getClass().getResource(OUT_PAGE));
			g.drawImage(image.getImage(), 0, 0, null);
			for (PosImageIcon iceIcon : iceList) {									// ���̽�ũ�� �׸���
				iceIcon.draw(g);
			}
			key.draw(g);															// ���� �׸���
			panel = 0;																// �гθ��� �´� ���̽�ũ���� �޶� �г� ��ȣ �ο�
		}
	}// OutSidePanel class end

	class FirstFloor extends JPanel {
		public void paintComponent(Graphics g) {	
			Image image = new ImageIcon(getClass().getResource(FIRST_FLOOR)).getImage();
			g.drawImage(image, 0, 0, null); 
			for (PosImageIcon iceIcon : iceList) {		
				if(iceIcon.use == true)			//use�� true�� ���̽�ũ���� �׸���
					iceIcon.draw(g);
			}
			for (int i = 0; i < fireList.size(); i++) {
				fireList.get(i).draw(g);		// ����Ʈ�� �ִ� �� �̹��� �׸���
			}
			for (int i = 0; i < heartList.size(); i++) {
				if(heartList.get(i).use == true)		// use�� true�� ��Ʈ�� �׸���
					heartList.get(i).draw(g);
			}
			timerSection.draw(g);	// �ð��� �̹��� �׸���
			girlL.draw(g);		// ���� �̹��� �׸���

			secTimer.start();		// �ð��� Ÿ�̸� ����
			isKeyTimer = false;		// Ű Ÿ�̸� ���߱�
			isFireTimer = true;		// �� �׸��� ����

			g.setColor(Color.YELLOW);
			g.setFont(new Font("����ü", Font.BOLD, 25));
			g.drawString(sec + "��", 60, 365);

			isGirl = true;   	// ����
			panel = 1;
		}
	}//FirstFloor class end

	class SecondFloor extends JPanel {
		public void paintComponent(Graphics g) {
			iceList.get(7).use = false;
			Image image = new ImageIcon(getClass().getResource(SECOND_FLOOR)).getImage();
			g.drawImage(image, 0, 0, null);
			for (PosImageIcon icon : iceList) {
				if(icon.use == true)
					icon.draw(g);
			}
			for (int i = 0; i < fireList.size(); i++) {
				fireList.get(i).draw(g);
			}
			for (int i = 0; i < heartList.size(); i++) {
				if(heartList.get(i).use == true)
					heartList.get(i).draw(g);
			}
			girlR.draw(g);		//������ �̹��� �׸���
			timerSection.draw(g);

			isGirl = false; 	//������
			panel = 2;

			g.setColor(Color.YELLOW);
			g.setFont(new Font("����ü", Font.BOLD, 25));
			g.drawString(sec + "��", 60, 365);
		}
	}//SecondFloor class end

	class ThirdFloor extends JPanel {
		public void paintComponent(Graphics g) {
			iceList.get(5).use = false;
			Image image = new ImageIcon(getClass().getResource(THIRD_FLOOR)).getImage();  // �̹��� ��������
			g.drawImage(image, 0, 0, null); // �̹��� �׸���
			for (PosImageIcon icon : iceList) {
				if(icon.use == true)
					icon.draw(g);
			}
			for (int i = 0; i < fireList.size(); i++) {
				fireList.get(i).draw(g);
			}
			for (int i = 0; i < heartList.size(); i++) {
				if(heartList.get(i).use == true)
					heartList.get(i).draw(g);
			}
			girlL.draw(g);
			timerSection.draw(g);

			isGirl = true;	
			panel = 3;

			g.setColor(Color.YELLOW);
			g.setFont(new Font("����ü", Font.BOLD, 25));
			g.drawString(sec + "��", 60, 365);
		}
	}//ThirdFloor class end

	class FourthFloor extends JPanel {
		public void paintComponent(Graphics g) {
			iceList.get(6).use = false;
			Image image = new ImageIcon(getClass().getResource(FOURTH_FLOOR)).getImage();  // �̹��� ��������
			g.drawImage(image, 0, 0, null); // �̹��� �׸���
			for (PosImageIcon icon : iceList) {
				if(icon.use == true)
					icon.draw(g);
			}
			for (int i = 0; i < fireList.size(); i++) {
				fireList.get(i).draw(g);
			}
			for (int i = 0; i < heartList.size(); i++) {
				if(heartList.get(i).use == true)
					heartList.get(i).draw(g);
			}
			girlR.draw(g);
			timerSection.draw(g);

			isGirl = false;
			panel = 4;

			g.setColor(Color.YELLOW);
			g.setFont(new Font("����ü", Font.BOLD, 25));
			g.drawString(sec + "��", 60, 365);
		}
	}//FourthFloor class end

	class FifthFloor extends JPanel {
		public void paintComponent(Graphics g) {
			iceList.get(4).use = false;
			Image image = new ImageIcon(getClass().getResource(FIFTH_FLOOR)).getImage();  // �̹��� ��������
			g.drawImage(image, 0, 0, null); // �̹��� �׸���
			for (PosImageIcon icon : iceList) {
				if(icon.use == true)
					icon.draw(g);
			}
			for (int i = 0; i < heartList.size(); i++) {
				if(heartList.get(i).use == true)
					heartList.get(i).draw(g);
			}
			girlL.pX = 545;
			girlL.pY = 202;
			iconTimer.stop();
			panel = 5;

			girlL.draw(g);
			timerSection.draw(g);

			g.setColor(Color.YELLOW);
			g.setFont(new Font("����ü", Font.BOLD, 25));
			g.drawString(sec + "��", 60, 365);
		}
	}//FifthFloor class end

	class WinPanel extends JPanel{
		public void paintComponent(Graphics g) {
			Image image = new ImageIcon(getClass().getResource(WIN_PAGE)).getImage();  // �̹��� ��������
			g.drawImage(image, 0, 0, 684, 548, null); // �̹��� �׸���
			
			secTimer.stop();		// �ð��� Ÿ�̸� ���߱� �̱� �гη� �Ѿ�Դٸ� �� ���� ������ Ÿ�̸Ӵ� ��ž �Ǿ���
			backgroundSound.stop(); // ������� ���߱�
			winSound.play();		// �̰��� �� ���� ����
		}
	}//winPanel class end

	class GameOverPanel extends JPanel{
		public void paintComponent(Graphics g) {
			Image image = new ImageIcon(getClass().getResource(OVER_PAGE)).getImage();  // �̹��� ��������
			g.drawImage(image, 0, 0, 684, 548, null); // �̹��� �׸���

			iconTimer.stop();
			secTimer.stop();

			backgroundSound.stop();
			loseSound.play();

		}
	}//GamePanel class end

	// ========================================== ��ư ������ ===================================================

	class BtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == startBtn || e.getSource() == start2Btn || e.getSource() == start3Btn || e.getSource() == start4Btn ){
				getContentPane().add("outPanel",outPanel); 
				card.show(getContentPane(), "outPanel"); 
			}
			else if(e.getSource() == rulesBtn){
				getContentPane().add("rulesPanel",rulesPanel); 
				card.show(getContentPane(), "rulesPanel"); 
			}
			else if(e.getSource() == hintBtn ){
				getContentPane().add("hint1Panel",hint1Panel); 
				card.show(getContentPane(), "hint1Panel"); 
			}
			else if(e.getSource() == nextHint){
				getContentPane().add("hint2Panel",hint2Panel); 
				card.show(getContentPane(), "hint2Panel");
			}
			else if(e.getSource() == beforeHint){
				getContentPane().add("hint1Panel",hint1Panel); 
				card.show(getContentPane(), "hint1Panel");
			}
		}
	}

	// ========================================== ���콺 ������ ===================================================

	class IceListener implements MouseListener{
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}

		public void mousePressed(MouseEvent e) {
			for (int i = 0; i < 10; i++) {		// ���̽�ũ�� ����Ʈ�� �ִ� 10�� �� � �� Ŭ���ߴ��� Ȯ���ؼ� dex�� ������ �ε����� ����
				if(e.getX() < iceList.get(i).pX + iceList.get(i).width && e.getX() > iceList.get(i).pX 
						&& e.getY() < iceList.get(i).pY + iceList.get(i).height && e.getY() > iceList.get(i).pY) {
					dex = i;
					repaint();
				}
			}
		} // mousePressdd() end

		public void mouseReleased(MouseEvent e) {
			if(panel == 0) {					
				if(iceList.get(dex).pX > 50 && iceList.get(dex).pX < 200 && iceList.get(dex).pY > 120 && iceList.get(dex).pY < 220) {
					if(dex != 1) {						// �ٶ��� ��ǳ�� ��ǥ, �ٹ�� �ε���	
						x.play();
						iceList.get(dex).pX = iceList.get(dex).originPX;
						iceList.get(dex).pY = iceList.get(dex).originPY;
						requestFocus();
						repaint();
					}
					else {
						wow.play();
						isBabam = true;					// �˸°� ������ �������� true
						iceList.get(1).use = false;		// false�� �ٲٸ� ���� �гκ��� �׸��� �׸��� �ʴ´�
					}
				}
				if(iceList.get(dex).pX > 500 && iceList.get(dex).pX < 620 && iceList.get(dex).pY > 100 && iceList.get(dex).pY < 200) {
					if(dex != 9) {						// ȣ�α�� ���� ��ǳ�� ��ǥ, ȣ�θ��� �ε���
						x.play();
						iceList.get(dex).pX = iceList.get(dex).originPX;
						iceList.get(dex).pY = iceList.get(dex).originPY;
						requestFocus();
						repaint();
					}
					else {
						wow.play();
						isHodu = true;
						iceList.get(9).use = false;
					}
				}			
				if(!isBabam && !isHodu) {		// ��ǳ���ȿ� �� ���� ��
					x.play();
					if (iceList.get(dex).pX != iceList.get(dex).originPX || iceList.get(dex).pY != iceList.get(dex).originPY) {
						iceList.get(dex).pX = iceList.get(dex).originPX; 		// ���� ��ġ�� �ű��
						iceList.get(dex).pY = iceList.get(dex).originPY;
						requestFocus();
						repaint();
					}
				}				
				if(isBabam && !isHodu) {		// �ٹ�ٴ� ���µ� ȣ�θ��簡 �� ���� ��
					if(dex != 1) {
						x.play();
						if (iceList.get(dex).pX != iceList.get(dex).originPX || iceList.get(dex).pY != iceList.get(dex).originPY) {
							iceList.get(dex).pX = iceList.get(dex).originPX;
							iceList.get(dex).pY = iceList.get(dex).originPY;
							requestFocus();
							repaint();
						}
					}
				}		
				if(!isBabam && isHodu) {		//  ȣ�θ���� ���µ� �ٹ�ٴ� �� ���� ��
					if(dex != 9) {
						x.play();
						if (iceList.get(dex).pX != iceList.get(dex).originPX || iceList.get(dex).pY != iceList.get(dex).originPY) {
							iceList.get(dex).pX = iceList.get(dex).originPX;
							iceList.get(dex).pY = iceList.get(dex).originPY;
							requestFocus();
							repaint();
						}
					}
				}
				if(isHodu && isBabam) {			// ȣ�θ��絵 ���� �ٹ�ٵ� ���� ��
					isKeyTimer = true;
					iconTimer.start();
				}	
			}//if() panel == 0 end

			else if(panel == 1) {													// ��ǳ�� �ȿ� ���� ��
				if(iceList.get(dex).pX > 170 && iceList.get(dex).pX < 320 && iceList.get(dex).pY > 100 && iceList.get(dex).pY < 200) {	
					if(dex != 7) {													// �߸��� ���̽�ũ���� ���ٸ�
						x.play();	
						iceList.get(dex).pX = iceList.get(dex).originPX;			// ���� ��ǥ�� �Űܶ�
						iceList.get(dex).pY = iceList.get(dex).originPY;
						requestFocus();
						repaint();
					}
					else {
						wow.play();
						isTank = true;
					}
				}
				else if(!isTank) {							// ��ǳ���ȿ� �� ���� ��
					x.play();
					if (iceList.get(dex).pX != iceList.get(dex).originPX || iceList.get(dex).pY != iceList.get(dex).originPY) {
						iceList.get(dex).pX = iceList.get(dex).originPX;		
						iceList.get(dex).pY = iceList.get(dex).originPY;
						requestFocus();
						repaint();
					}
				}
				else {										// ��ǳ�� �ȿ� ���µ� �ٸ� ���̽�ũ���� ������ ��
					x.play();
					if (iceList.get(dex).pX != iceList.get(dex).originPX || iceList.get(dex).pY != iceList.get(dex).originPY) {
						iceList.get(dex).pX = iceList.get(dex).originPX;		// ���� ��ǥ�� �Űܶ�
						iceList.get(dex).pY = iceList.get(dex).originPY;
						requestFocus();
						repaint();
					}			
				}
			}//if() panel == 1 end

			else if(panel == 2) {
				if(iceList.get(dex).pX > 330 && iceList.get(dex).pX < 480 && iceList.get(dex).pY > 100 && iceList.get(dex).pY < 210) {
					if(dex != 5) {
						x.play();
						iceList.get(dex).pX = iceList.get(dex).originPX;
						iceList.get(dex).pY = iceList.get(dex).originPY;
						requestFocus();
						repaint();
					}
					else {
						wow.play();
						isCoffee = true;	
					}		
				}
				else if(!isCoffee) {
					x.play();
					if (iceList.get(dex).pX != iceList.get(dex).originPX || iceList.get(dex).pY != iceList.get(dex).originPY) {
						iceList.get(dex).pX = iceList.get(dex).originPX;
						iceList.get(dex).pY = iceList.get(dex).originPY;
						requestFocus();
						repaint();
					}
				}
				else {
					x.play();
					if (iceList.get(dex).pX != iceList.get(dex).originPX || iceList.get(dex).pY != iceList.get(dex).originPY) {
						iceList.get(dex).pX = iceList.get(dex).originPX;
						iceList.get(dex).pY = iceList.get(dex).originPY;
						requestFocus();
						repaint();
					}			
				}
			}//if() panel == 2 end

			else if(panel == 3) {
				if(iceList.get(dex).pX > 160 && iceList.get(dex).pX < 325 && iceList.get(dex).pY > 60 && iceList.get(dex).pY < 185) {
					if(dex != 6) {
						x.play();
						iceList.get(dex).pX = iceList.get(dex).originPX;
						iceList.get(dex).pY = iceList.get(dex).originPY;
						requestFocus();
						repaint();
					}
					else {
						wow.play();
						isCrazy = true;
					}
				}
				else if(!isCrazy) {
					x.play();
					if (iceList.get(dex).pX != iceList.get(dex).originPX || iceList.get(dex).pY != iceList.get(dex).originPY) {
						iceList.get(dex).pX = iceList.get(dex).originPX;
						iceList.get(dex).pY = iceList.get(dex).originPY;
						requestFocus();
						repaint();
					}
				}
				else {
					x.play();
					if (iceList.get(dex).pX != iceList.get(dex).originPX || iceList.get(dex).pY != iceList.get(dex).originPY) {
						iceList.get(dex).pX = iceList.get(dex).originPX;
						iceList.get(dex).pY = iceList.get(dex).originPY;
						requestFocus();
						repaint();
					}			
				}
			}//if() panel == 3 end

			else if(panel == 4) {
				if(iceList.get(dex).pX > 340 && iceList.get(dex).pX < 500 && iceList.get(dex).pY > 65 && iceList.get(dex).pY < 190) {
					if(dex != 4) {
						x.play();
						iceList.get(dex).pX = iceList.get(dex).originPX;
						iceList.get(dex).pY = iceList.get(dex).originPY;
						requestFocus();
						repaint();
					}
					else {
						wow.play();
						isIce = true;
					}
				}
				else if(!isIce) {
					x.play();
					if (iceList.get(dex).pX != iceList.get(dex).originPX || iceList.get(dex).pY != iceList.get(dex).originPY) {
						iceList.get(dex).pX = iceList.get(dex).originPX;
						iceList.get(dex).pY = iceList.get(dex).originPY;
						requestFocus();
						repaint();
					}
				}
				else {
					x.play();
					if (iceList.get(dex).pX != iceList.get(dex).originPX || iceList.get(dex).pY != iceList.get(dex).originPY) {
						iceList.get(dex).pX = iceList.get(dex).originPX;
						iceList.get(dex).pY = iceList.get(dex).originPY;
						requestFocus();
						repaint();
					}			
				}
			}//if() panel == 4 end

			else if(panel == 5) {
				if(iceList.get(dex).pX > 75 && iceList.get(dex).pX < 235 && iceList.get(dex).pY > 53 && iceList.get(dex).pY < 178) {
					if(dex != 3) {
						x.play();
						iceList.get(dex).pX = iceList.get(dex).originPX;
						iceList.get(dex).pY = iceList.get(dex).originPY;
						requestFocus();
						repaint();
					}
					else {
						isSSang = true;
						getContentPane().add("winPanel",winPanel);
						card.show(getContentPane(), "winPanel"); 
					}
				}
				else if(!isSSang) {
					if (iceList.get(dex).pX != iceList.get(dex).originPX || iceList.get(dex).pY != iceList.get(dex).originPY) {
						iceList.get(dex).pX = iceList.get(dex).originPX;
						iceList.get(dex).pY = iceList.get(dex).originPY;
						requestFocus();
						repaint();
					}
				}
				else {
					x.play();
					if (iceList.get(dex).pX != iceList.get(dex).originPX || iceList.get(dex).pY != iceList.get(dex).originPY) {
						iceList.get(dex).pX = iceList.get(dex).originPX;
						iceList.get(dex).pY = iceList.get(dex).originPY;
						requestFocus();
						repaint();
					}			
				}
			}//if() panel == 5 end
		}// mouseReleased() end
	}// ���̽� ������ end

	// ========================================== ���콺 ��� ������ ===================================================

	class IceDraggListener implements MouseMotionListener { 
		public void mouseDragged(MouseEvent e) { 	// �巡���ϴ´�� 
			iceList.get(dex).pX = e.getX() - 30;	// ���̽�ũ���� �����δ�
			iceList.get(dex).pY = e.getY() - 30;
			requestFocus();
			repaint();
		}
		public void mouseMoved(MouseEvent e) {}
	}

	// ========================================== Ű ������ ===================================================

	class MoveListener implements KeyListener{
		public void keyTyped(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_LEFT) {				// ������ ������ ��	
				if(isTank || isCrazy) {							// ���̽�ũ���� ����� ���Ҵٸ�
					girlL.pX -= 10;								// ��ǥ�� �Űܶ�

					if (moveCount > 9)							// moveCount�� 9���� ũ��
						moveCount = 0;							// �ʱ�ȭ
					if (moveCount<3 )							// moveCount�� 0~2�� ����
						girlL.setImage(girlL1.getImage());		//  ���� ù ��° �̹���
					else if (moveCount>=3 && moveCount<6 )		// moveCount�� 3~5�� ����
						girlL.setImage(girlL2.getImage());		//  ���� �� ��° �̹���
					else										// moveCount�� 6~8�� ����
						girlL.setImage(girlL3.getImage());		//  ���� �� ��° �̹���

					if(girlL.pX == 200) {											// 200 ��ǥ�� �������� ��
						if(isTank) {												// ��ũ���̸� ���Ҵٸ� ù ��° ���̿����Ƿ�
							getContentPane().add("secondFloor",secondFloor); 		// �� ��° ������ �г��� �ٲ۴�
							card.show(getContentPane(), "secondFloor"); 
							isTank = false;								// if(isTank)�� �ִ� ������ �̹� ���� �Ͽ����Ƿ� �ٽ� �� �ϱ� ���ؼ� false�� �ٲ۴�
							girlL.pX = 440;								// �� ��° �������� ��ǥ�� ������ �ش�
						}
						else {
							getContentPane().add("fourthFloor",fourthFloor); 
							card.show(getContentPane(), "fourthFloor"); 
						}
					}

					moveCount++; 		// moveCount�� ���� �̹����� ���������� �ٸ��� �׷��ش�
					requestFocus();
					repaint();
				}
				else				// ���̽�ũ���� ���� �ʾҴ� �� �����̷� �ϸ�
					x.play();		// x �Ҹ� ����	
			} //if(left) end
			
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				if(isCoffee || isIce) {
					girlR.pX += 10;

					if (moveCount > 9)
						moveCount = 0;
					if (moveCount<3 )
						girlR.setImage(girlR1.getImage());
					else if (moveCount>=3 && moveCount<6 )
						girlR.setImage(girlR2.getImage());
					else
						girlR.setImage(girlR3.getImage());

					if(girlR.pX == 440) {
						if(isCoffee) {
							getContentPane().add("thirdFloor",thirdFloor);
							card.show(getContentPane(), "thirdFloor");
							isCoffee = false;
							girlR.pX = 180;
						}
						else {
							getContentPane().add("fifthFloor",fifthFloor);
							card.show(getContentPane(), "fifthFloor");
						}
					}

					moveCount++;
					requestFocus();
					repaint();
				}
				else
					x.play();

			} // if(right) end
			
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {			// �Ʒ� ����Ű�� ������ ��
				isFire = false;  							// isFire�� false�� �ٲ㼭 �ҿ� �� �°� �Ѵ�
				if(isGirl)									// �����̸�
					girlL.setImage(girlStop.getImage());	// ���� �̹����� ������ �̹����� �ٲٰ�
				else										// �������̸�
					girlR.setImage(girlStop.getImage());	// ������ �̹����� ������ �̹����� �ٲ۴�
			}
		} // keyPressed() end

		public void keyReleased(KeyEvent e) {		//������ ����Ű�� ���� �ι�° �̹����� �ٽ� �����Ѵ�
			if(e.getKeyCode()==KeyEvent.VK_LEFT){
				moveCount = 0;
				girlL.setImage(girlL2.getImage());
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				moveCount = 0;
				girlR.setImage(girlR2.getImage());
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				isFire = true;
				if(isGirl)
					girlL.setImage(girlL2.getImage());
				else
					girlR.setImage(girlR2.getImage());
			}
		} //  keyReleased() end

	} // Ű ������ end

	// ========================================== Ÿ�̸� ===================================================

	// �ð��� ����ϴ� Ÿ�̸�
	class SecTimer implements ActionListener {							
		public void actionPerformed (ActionEvent event) {
			if(sec > 1)													// sec�� 100 ~ 2 ���̶��  
				sec--;													// 1�� ���̰� 
			else if (sec == 30)
				backgroundSound.play();
			else {														// 2�� ���� 1�� �� �� ���� �ʿ���
				getContentPane().add("gameOverPanel", gameOverPanel);	// gameOverPanel�� ����
				card.show(getContentPane(), "gameOverPanel"); 
			}
		}
	} // SecTimer end

	//Ű �����ܰ� �� �������� �������� ����ϴ� Ÿ�̸�
	class IconTimer implements ActionListener {							
		public void actionPerformed (ActionEvent event) {
			// Ű ������ ����
			if(isKeyTimer) {
				if(key.pY<300) {					// Ű�� Y��ǥ�� 300 �����̶�� ��ǥ�� 5�� �����Ѵ�
					key.pY  += 4;
				}
				else { 						// Ű�� Y��ǥ�� 300�� �Ѿ� ���� ���� �гη� �ѱ��
					getContentPane().add("firstFloor", firstFloor); 
					card.show(getContentPane(), "firstFloor"); 
				}
				requestFocus();
				repaint();
			}// if Ű ������ end

			// �� ������ ����
			if(isFireTimer) {			
				for(int i=0; i< fireList.size();i++) {		
					fireList.get(i).pY++;					// �� �̹������� ��ġ��������
					
					if(fireList.get(i).pY == 180) {			// �� �̹����� y��ǥ�� 180�� �����ϸ� ���ο� ���� ����Ʈ�� �߰����ش�
						fireList.add(new PosImageIcon(FIRE,(int)(Math.random()*350 + 150),0,true));
					}
					
					if(fireList.get(i).pY == 400) {			// �� �̹����� y��ǥ�� 400�� �����ϸ� ���� ����Ʈ���� �����
						fireList.remove(0);
					}
					
					// ����
					if(isGirl) {
						if(girlL.collide(fireList.get(i), (girlL.height + fireList.get(i).height) / 2 - 25)){	// 	�Ұ� ĳ���Ͱ� �ε����� ��
							if(isTank || isCrazy) {																//	���̽�ũ���� ����� �÷����� �����̰�
								if(fireList.get(i).isHit && isFire) {											//	�Ʒ� ����Ű�� ������ �ʾҴٸ� ó�� �ε����� �� 
									x.play();																
									fireList.get(i).isHit = false;												//  isHit�� �ε����ٴ� �ɷ� �ٲ��ְ�
									fireList.get(i).setImage(clear.getImage());									//  �� �̹����� ������ �̹����� �ٲ��ش�
									if(heartList.size() > 1) {													//  �׸��� ���(��Ʈ)�� 1�� �̻��̶��
										heartList.remove(0);													// 	����� �ϳ� �����ְ�
									}
									else {
										getContentPane().add("gameOverPanel", gameOverPanel);					// 	 1�� �ۿ� ���� �ʾ��� ����
										card.show(getContentPane(), "gameOverPanel");							//   gameOverPanel�� ����
									}
								}
							}
						}
						else {												// �̹����� �Ÿ��� �浹�� ��ŭ�� �ƴ϶��
							fireList.get(i).isHit = true;					// true�� �����ؼ� �浹�� �Ǵ��� �� �ְ� ���ش�
						}
					}
					
					// ������
					else{
						if(girlR.collide(fireList.get(i), (girlR.height + fireList.get(i).height) / 2 - 25)){
							if( isCoffee || isIce) {
								if(fireList.get(i).isHit && isFire) {
									x.play();
									fireList.get(i).isHit = false;
									fireList.get(i).setImage(clear.getImage());
									if(heartList.size() > 1) {
										heartList.remove(0);	
									}
									else {
										getContentPane().add("gameOverPanel", gameOverPanel); // ī�巹�̾ƿ��� coverPanel�̶� �̸��� coverPanel�� �����
										card.show(getContentPane(), "gameOverPanel"); // ī�巹�̾ƿ����� coverPanel�̶� �̸��� ���� �г��� ������ �����ش�
									}
								}
							}
						}
						else {
							fireList.get(i).isHit = true;
						}
					}
					requestFocus();
					repaint();
				} // for() end
			} // if() �� ������ end
		}
	} // IconTimer end
} // IceCreamGame Class end