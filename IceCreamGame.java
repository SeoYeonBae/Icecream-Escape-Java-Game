import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.util.ArrayList;
import java.applet.AudioClip;

public class IceCreamGame extends JFrame {

	//================================== 어레이 리스트 =====================================

	ArrayList<PosImageIcon> iceList = new ArrayList<>();; 			// ICEGUY크림 이미지 
	ArrayList<PosImageIcon> fireList = new ArrayList<>();;			// 불 이미지
	ArrayList<PosImageIcon> heartList = new ArrayList<>();;			// 생명 이미지

	//================================== 이미지 스트링 =====================================

	final String COVER = "res/coverpage.png";						// 이미지 변환하기 쉽게 하기 위하여 스트링으로 저장
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
	final String NUGA = "src/res/누가바.png";
	final String BABAM = "src/res/바밤바.png";
	final String BRAVO = "src/res/부라보콘.png";
	final String SSANG = "src/res/쌍쌍바.png";
	final String ICEGUY = "src/res/아이스.png";
	final String COFFEE = "src/res/커피.png";
	final String CRAZY = "src/res/크레이지바.png";
	final String TANK = "src/res/탱크보이.png";
	final String PASI = "src/res/파시통통.png";
	final String HODU = "src/res/호두마루.png";
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

	//==================================== 음악 스트링 ======================================

	final String START_SOUND = "res/bgm.wav";						// 음악
	final String WIN_SOUND = "res/winSound.wav";
	final String LOSE_SOUND = "res/loseSound.wav";
	final String X_SOUND = "res/Duck.wav";
	final String WOW = "res/Wow.wav";

	//=================================== 프레임 상수 ========================================

	final int WIN_WIDTH = 690; 		// 전체 frame의 폭
	final int WIN_HEIGHT = 577; 	// 전체 frame의 높이

	//=================================== 정수형 변수 ========================================

	int sec = 100;					// 시간초
	int dex;						// 아이스리스트에서 몇 번째 아이스크림을 선택했는지 알기 위한 변수 				
	int panel;						// 패널에 따라 정답인 아이스크림이 달라서 몇 번 패널인지 알기 위한 변수
	int moveCount = 0;				// 캐릭터의 움직임을 알아보기 위한 변수

	//================================= 불리안형 변수===========================================

	boolean isBabam = false;		// 제대로 들어가면 true 아니면 false
	boolean isHodu = false;
	boolean isTank = false;
	boolean isCoffee = false;
	boolean isCrazy = false;
	boolean isIce = false;
	boolean isSSang = false;
	boolean isGirl = true;			// 캐릭터 방향 true = 왼쪽 , false = 오른쪽
	boolean isFire = true;			// true = 불에 맞고, false = 불에 안 맞는다 
	boolean isKeyTimer = false;		// false = 타이머가 돌아가지 X true일 때만 돌아간다
	boolean isFireTimer = false;
	boolean isSecTimer = false;

	//============================ 포스이미지아이콘 형식의 이미지 선언 =================================

	PosImageIcon key;
	PosImageIcon girlL;
	PosImageIcon girlR;
	PosImageIcon timerSection;

	//============================ 이미지 아이콘 =================================================

	ImageIcon girlL1;				// 왼쪽
	ImageIcon girlL2;
	ImageIcon girlL3;
	ImageIcon girlR1;				// 오른쪽
	ImageIcon girlR2;
	ImageIcon girlR3;
	ImageIcon girlStop;				// 멈춤
	ImageIcon clear;				// 투명 아이콘

	//============================ 레이아웃 선언 ===================================================

	CardLayout card;

	//============================ 패널 선언 ======================================================

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

	//============================= 버튼 선언 =====================================================

	JButton startBtn;
	JButton start2Btn;
	JButton start3Btn;
	JButton start4Btn;
	JButton rulesBtn;
	JButton hintBtn;
	JButton nextHint;
	JButton beforeHint;

	//=========================== 음악 ============================================================

	AudioClip backgroundSound;		// 게임 배경
	AudioClip winSound;				// 이겼을 때
	AudioClip loseSound;			// 졌을 때
	AudioClip x;					// 불에 맞았을 때, 아이스크림을 잘못 놓았을 때
	AudioClip wow;					// 아이스크림이 제대로 들어갔을 때

	//========================= 타이머 선언 ========================================================

	Timer iconTimer; 				// 불과 아이스크림 이미지 모두 담당
	Timer secTimer;					// 시간 초 담당 타이머

	//=========================== 메인 ===========================================================

	public static void main(String [] args) {
		try {		
			IceCreamGame game = new IceCreamGame();
		}
		catch(Exception e){}
	}

	//========================== 구성자 ===========================================================

	IceCreamGame(){
		setTitle("더위탈출 대작전");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setSize(WIN_WIDTH, WIN_HEIGHT);
		setLocationRelativeTo(null); 		// 프레임 중앙에 띄우기
		setResizable(false); 				// 프레임 사이즈 고정

		card = new CardLayout();			
		getContentPane().setLayout(card); 	// 레이아웃을 카드로 설정하고 프레임에 씌우기

		girlL1 =new ImageIcon(LEFT1);				// 이미지 아이콘 생성
		girlL2 =new ImageIcon(LEFT2);
		girlL3 =new ImageIcon(LEFT3);
		girlR1 =new ImageIcon(RIGHT1);
		girlR2 =new ImageIcon(RIGHT2);
		girlR3 =new ImageIcon(RIGHT3);
		girlStop = new ImageIcon(STOP);
		clear = new ImageIcon(CLEAR);

		girlL = new PosImageIcon(LEFT2,430,240,true);			// 포스 이미지 아이콘 생성
		girlR = new PosImageIcon(RIGHT2,180,240,true);
		key = new PosImageIcon(KEY,260,70,true);
		timerSection = new PosImageIcon(TIME, 20, 300,true);

		iceList.add(new PosImageIcon(NUGA,465,438,true));			// 아이스 리스트에 이미지 더하기
		iceList.add(new PosImageIcon(BABAM,387,487,true));
		iceList.add(new PosImageIcon(BRAVO,8,437,true));
		iceList.add(new PosImageIcon(SSANG,143,481,true));
		iceList.add(new PosImageIcon(ICEGUY,620,435,true));
		iceList.add(new PosImageIcon(COFFEE,65,418,true));
		iceList.add(new PosImageIcon(CRAZY,268,487,true));
		iceList.add(new PosImageIcon(TANK,506,487,true));
		iceList.add(new PosImageIcon(PASI,160,435,true));
		iceList.add(new PosImageIcon(HODU,316,438,true));

		heartList.add(new PosImageIcon(HEART,460,7,true));			// 하트 리스트에 이미지 더하기
		heartList.add(new PosImageIcon(HEART,530,7,true));
		heartList.add(new PosImageIcon(HEART,600,7,true));

		fireList.add(new PosImageIcon(FIRE,(int)(Math.random()*350+150) ,0,true)); // 불 이미지 리스트에 추가하기

		coverPanel = new CoverPanel();		// 패널 만들기
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

		startBtn = new JButton();								// 버튼 생성
		startBtn.setBorderPainted(false);						// 테두리 없애기
		startBtn.setContentAreaFilled(false); 					// 채우는 색 없애기
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

		iconTimer = new Timer(8, new IconTimer());				// delay를 8로 설정한 아이콘 타이머 생성
		secTimer = new Timer(1000, new SecTimer());				// 1초에 한 번씩 숫자 세는 타이머

		backgroundSound = JApplet.newAudioClip(getClass().getResource(START_SOUND));	//	배경 음악
		winSound = JApplet.newAudioClip(getClass().getResource(WIN_SOUND));				//	이겼을 때
		loseSound = JApplet.newAudioClip(getClass().getResource(LOSE_SOUND));			//	졌을 때
		x = JApplet.newAudioClip(getClass().getResource(X_SOUND));				// 아이스크림을 잘못 놓았거나 불에 맞았거나 아이스크림을 주지 않고 움직일 때
		wow = JApplet.newAudioClip(getClass().getResource(WOW));				// 아이스크림을 제대로 놓았을 때

		startBtn.addActionListener(new BtnListener());						// 버튼에 리스너 달기
		start2Btn.addActionListener(new BtnListener());
		start3Btn.addActionListener(new BtnListener());
		start4Btn.addActionListener(new BtnListener());
		rulesBtn.addActionListener(new BtnListener());
		hintBtn.addActionListener(new BtnListener());
		nextHint.addActionListener(new BtnListener());
		beforeHint.addActionListener(new BtnListener());

		coverPanel.add(startBtn);											// 패널에 버튼 달기 
		coverPanel.add(rulesBtn);
		rulesPanel.add(start2Btn);
		rulesPanel.add(hintBtn);
		hint1Panel.add(start3Btn);
		hint1Panel.add(nextHint);
		hint2Panel.add(start4Btn);
		hint2Panel.add(beforeHint);

		outPanel.addMouseListener(new IceListener());						// 패널에 마우스 리스너 달기
		outPanel.addMouseMotionListener(new IceDraggListener());			// 패널에 마우스 모션 리스너 달기
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
		addKeyListener(new MoveListener());									// 프레임에 키리스너 달기


		getContentPane().add("coverPanel",coverPanel); 						// 카드레이아웃에 "coverPanel"이란 이름의 coverPanel을 추가한다
		card.show(getContentPane(), "coverPanel"); 							// 카드레이아웃에서 "coverPanel"이란 이름을 가진 패널을 꺼내서 보여준다
		backgroundSound.play(); 											// 배경 음악 시작

		requestFocus();
		setFocusable(true);
		setVisible(true); 

	} // IceCreamGame() end

	// ========================================== 패널 클래스 ===================================================

	class CoverPanel extends JPanel {
		public void paintComponent(Graphics g) {
			Image image = new ImageIcon(getClass().getResource(COVER)).getImage();  // 이미지 가져오기
			g.drawImage(image, 0, 0, 684, 548, null);								// 이미지 그리기
			startBtn.setBounds(510, 400, 155, 60);									// 시작 버튼
			rulesBtn.setBounds(510, 470, 155, 60);									// 방법 버튼

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
			Image image = new ImageIcon(getClass().getResource(HINT_1)).getImage();  // 이미지 가져오기
			g.drawImage(image, 0, 0, null); // 이미지 그리기
			start3Btn.setBounds(518, 480, 150, 55);
			nextHint.setBounds(453, 40, 40, 40);
		}
	}// Hint1Panel end

	class Hint2Panel extends JPanel {
		public void paintComponent(Graphics g) {	
			Image image = new ImageIcon(getClass().getResource(HINT_2)).getImage();  // 이미지 가져오기
			g.drawImage(image, 0, 0, null); // 이미지 그리기
			start4Btn.setBounds(518, 480, 150, 55);
			beforeHint.setBounds(205, 43, 40, 40);
		}
	}// Hint2Panel end

	class OutSidePanel extends JPanel {
		public void paintComponent(Graphics g) {	
			ImageIcon image = new ImageIcon(getClass().getResource(OUT_PAGE));
			g.drawImage(image.getImage(), 0, 0, null);
			for (PosImageIcon iceIcon : iceList) {									// 아이스크림 그리기
				iceIcon.draw(g);
			}
			key.draw(g);															// 열쇠 그리기
			panel = 0;																// 패널마다 맞는 아이스크림이 달라서 패널 번호 부여
		}
	}// OutSidePanel class end

	class FirstFloor extends JPanel {
		public void paintComponent(Graphics g) {	
			Image image = new ImageIcon(getClass().getResource(FIRST_FLOOR)).getImage();
			g.drawImage(image, 0, 0, null); 
			for (PosImageIcon iceIcon : iceList) {		
				if(iceIcon.use == true)			//use가 true인 아이스크림만 그리기
					iceIcon.draw(g);
			}
			for (int i = 0; i < fireList.size(); i++) {
				fireList.get(i).draw(g);		// 리스트에 있는 불 이미지 그리기
			}
			for (int i = 0; i < heartList.size(); i++) {
				if(heartList.get(i).use == true)		// use가 true인 하트만 그리기
					heartList.get(i).draw(g);
			}
			timerSection.draw(g);	// 시간초 이미지 그리기
			girlL.draw(g);		// 왼쪽 이미지 그리기

			secTimer.start();		// 시간초 타이머 시작
			isKeyTimer = false;		// 키 타이머 멈추기
			isFireTimer = true;		// 불 그리기 시작

			g.setColor(Color.YELLOW);
			g.setFont(new Font("돋움체", Font.BOLD, 25));
			g.drawString(sec + "초", 60, 365);

			isGirl = true;   	// 왼쪽
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
			girlR.draw(g);		//오른쪽 이미지 그리기
			timerSection.draw(g);

			isGirl = false; 	//오른쪽
			panel = 2;

			g.setColor(Color.YELLOW);
			g.setFont(new Font("돋움체", Font.BOLD, 25));
			g.drawString(sec + "초", 60, 365);
		}
	}//SecondFloor class end

	class ThirdFloor extends JPanel {
		public void paintComponent(Graphics g) {
			iceList.get(5).use = false;
			Image image = new ImageIcon(getClass().getResource(THIRD_FLOOR)).getImage();  // 이미지 가져오기
			g.drawImage(image, 0, 0, null); // 이미지 그리기
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
			g.setFont(new Font("돋움체", Font.BOLD, 25));
			g.drawString(sec + "초", 60, 365);
		}
	}//ThirdFloor class end

	class FourthFloor extends JPanel {
		public void paintComponent(Graphics g) {
			iceList.get(6).use = false;
			Image image = new ImageIcon(getClass().getResource(FOURTH_FLOOR)).getImage();  // 이미지 가져오기
			g.drawImage(image, 0, 0, null); // 이미지 그리기
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
			g.setFont(new Font("돋움체", Font.BOLD, 25));
			g.drawString(sec + "초", 60, 365);
		}
	}//FourthFloor class end

	class FifthFloor extends JPanel {
		public void paintComponent(Graphics g) {
			iceList.get(4).use = false;
			Image image = new ImageIcon(getClass().getResource(FIFTH_FLOOR)).getImage();  // 이미지 가져오기
			g.drawImage(image, 0, 0, null); // 이미지 그리기
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
			g.setFont(new Font("돋움체", Font.BOLD, 25));
			g.drawString(sec + "초", 60, 365);
		}
	}//FifthFloor class end

	class WinPanel extends JPanel{
		public void paintComponent(Graphics g) {
			Image image = new ImageIcon(getClass().getResource(WIN_PAGE)).getImage();  // 이미지 가져오기
			g.drawImage(image, 0, 0, 684, 548, null); // 이미지 그리기
			
			secTimer.stop();		// 시간초 타이머 멈추기 이긴 패널로 넘어왔다면 이 전에 아이콘 타이머는 스탑 되었음
			backgroundSound.stop(); // 배경음악 멈추기
			winSound.play();		// 이겼을 때 음악 시작
		}
	}//winPanel class end

	class GameOverPanel extends JPanel{
		public void paintComponent(Graphics g) {
			Image image = new ImageIcon(getClass().getResource(OVER_PAGE)).getImage();  // 이미지 가져오기
			g.drawImage(image, 0, 0, 684, 548, null); // 이미지 그리기

			iconTimer.stop();
			secTimer.stop();

			backgroundSound.stop();
			loseSound.play();

		}
	}//GamePanel class end

	// ========================================== 버튼 리스너 ===================================================

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

	// ========================================== 마우스 리스너 ===================================================

	class IceListener implements MouseListener{
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}

		public void mousePressed(MouseEvent e) {
			for (int i = 0; i < 10; i++) {		// 아이스크림 리스트에 있는 10개 중 어떤 걸 클릭했는지 확인해서 dex에 선택한 인덱스를 저장
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
					if(dex != 1) {						// 다람쥐 말풍선 좌표, 바밤바 인덱스	
						x.play();
						iceList.get(dex).pX = iceList.get(dex).originPX;
						iceList.get(dex).pY = iceList.get(dex).originPY;
						requestFocus();
						repaint();
					}
					else {
						wow.play();
						isBabam = true;					// 알맞게 가져다 놓았으면 true
						iceList.get(1).use = false;		// false로 바꾸면 다음 패널부터 그림을 그리지 않는다
					}
				}
				if(iceList.get(dex).pX > 500 && iceList.get(dex).pX < 620 && iceList.get(dex).pY > 100 && iceList.get(dex).pY < 200) {
					if(dex != 9) {						// 호두까기 인형 말풍선 좌표, 호두마루 인덱스
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
				if(!isBabam && !isHodu) {		// 말풍선안에 안 들어갔을 때
					x.play();
					if (iceList.get(dex).pX != iceList.get(dex).originPX || iceList.get(dex).pY != iceList.get(dex).originPY) {
						iceList.get(dex).pX = iceList.get(dex).originPX; 		// 원래 위치로 옮긴다
						iceList.get(dex).pY = iceList.get(dex).originPY;
						requestFocus();
						repaint();
					}
				}				
				if(isBabam && !isHodu) {		// 바밤바는 들어갔는데 호두마루가 안 들어갔을 때
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
				if(!isBabam && isHodu) {		//  호두마루는 들어갔는데 바밤바는 안 들어갔을 때
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
				if(isHodu && isBabam) {			// 호두마루도 들어가고 바밤바도 들어갔을 때
					isKeyTimer = true;
					iconTimer.start();
				}	
			}//if() panel == 0 end

			else if(panel == 1) {													// 말풍선 안에 들어갔을 때
				if(iceList.get(dex).pX > 170 && iceList.get(dex).pX < 320 && iceList.get(dex).pY > 100 && iceList.get(dex).pY < 200) {	
					if(dex != 7) {													// 잘못된 아이스크림이 들어간다면
						x.play();	
						iceList.get(dex).pX = iceList.get(dex).originPX;			// 원래 좌표로 옮겨라
						iceList.get(dex).pY = iceList.get(dex).originPY;
						requestFocus();
						repaint();
					}
					else {
						wow.play();
						isTank = true;
					}
				}
				else if(!isTank) {							// 말풍선안에 안 들어갔을 때
					x.play();
					if (iceList.get(dex).pX != iceList.get(dex).originPX || iceList.get(dex).pY != iceList.get(dex).originPY) {
						iceList.get(dex).pX = iceList.get(dex).originPX;		
						iceList.get(dex).pY = iceList.get(dex).originPY;
						requestFocus();
						repaint();
					}
				}
				else {										// 말풍선 안에 들어갔는데 다른 아이스크림을 움직일 때
					x.play();
					if (iceList.get(dex).pX != iceList.get(dex).originPX || iceList.get(dex).pY != iceList.get(dex).originPY) {
						iceList.get(dex).pX = iceList.get(dex).originPX;		// 원래 좌표로 옮겨라
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
	}// 아이스 리스너 end

	// ========================================== 마우스 모션 리스너 ===================================================

	class IceDraggListener implements MouseMotionListener { 
		public void mouseDragged(MouseEvent e) { 	// 드래그하는대로 
			iceList.get(dex).pX = e.getX() - 30;	// 아이스크림을 움직인다
			iceList.get(dex).pY = e.getY() - 30;
			requestFocus();
			repaint();
		}
		public void mouseMoved(MouseEvent e) {}
	}

	// ========================================== 키 리스너 ===================================================

	class MoveListener implements KeyListener{
		public void keyTyped(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_LEFT) {				// 왼쪽을 눌렀을 때	
				if(isTank || isCrazy) {							// 아이스크림을 제대로 놓았다면
					girlL.pX -= 10;								// 좌표를 옮겨라

					if (moveCount > 9)							// moveCount가 9보다 크면
						moveCount = 0;							// 초기화
					if (moveCount<3 )							// moveCount가 0~2일 때는
						girlL.setImage(girlL1.getImage());		//  왼쪽 첫 번째 이미지
					else if (moveCount>=3 && moveCount<6 )		// moveCount가 3~5일 때는
						girlL.setImage(girlL2.getImage());		//  왼쪽 두 번째 이미지
					else										// moveCount가 6~8일 때는
						girlL.setImage(girlL3.getImage());		//  왼쪽 세 번째 이미지

					if(girlL.pX == 200) {											// 200 좌표에 도달했을 때
						if(isTank) {												// 탱크보이를 놓았다면 첫 번째 층이였으므로
							getContentPane().add("secondFloor",secondFloor); 		// 두 번째 층으로 패널을 바꾼다
							card.show(getContentPane(), "secondFloor"); 
							isTank = false;								// if(isTank)에 있는 내용은 이미 실행 하였으므로 다시 안 하기 위해서 false로 바꾼다
							girlL.pX = 440;								// 세 번째 층에서의 좌표를 지정해 준다
						}
						else {
							getContentPane().add("fourthFloor",fourthFloor); 
							card.show(getContentPane(), "fourthFloor"); 
						}
					}

					moveCount++; 		// moveCount에 따라서 이미지를 순차적으로 다르게 그려준다
					requestFocus();
					repaint();
				}
				else				// 아이스크림을 놓지 않았는 데 움직이려 하면
					x.play();		// x 소리 시작	
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
			
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {			// 아래 방향키를 눌렀을 때
				isFire = false;  							// isFire를 false로 바꿔서 불에 안 맞게 한다
				if(isGirl)									// 왼쪽이면
					girlL.setImage(girlStop.getImage());	// 왼쪽 이미지를 정지한 이미지로 바꾸고
				else										// 오른쪽이면
					girlR.setImage(girlStop.getImage());	// 오른쪽 이미지를 정지한 이미지로 바꾼다
			}
		} // keyPressed() end

		public void keyReleased(KeyEvent e) {		//눌렀던 방향키를 떼면 두번째 이미지로 다시 세팅한다
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

	} // 키 리스너 end

	// ========================================== 타이머 ===================================================

	// 시간을 담당하는 타이머
	class SecTimer implements ActionListener {							
		public void actionPerformed (ActionEvent event) {
			if(sec > 1)													// sec가 100 ~ 2 사이라면  
				sec--;													// 1씩 줄이고 
			else if (sec == 30)
				backgroundSound.play();
			else {														// 2가 들어가서 1이 된 그 다음 초에는
				getContentPane().add("gameOverPanel", gameOverPanel);	// gameOverPanel을 띄운다
				card.show(getContentPane(), "gameOverPanel"); 
			}
		}
	} // SecTimer end

	//키 아이콘과 불 아이콘의 움직임을 담당하는 타이머
	class IconTimer implements ActionListener {							
		public void actionPerformed (ActionEvent event) {
			// 키 움직임 제어
			if(isKeyTimer) {
				if(key.pY<300) {					// 키의 Y좌표가 300 이전이라면 좌표를 5씩 변경한다
					key.pY  += 4;
				}
				else { 						// 키의 Y좌표가 300이 넘어 가면 다음 패널로 넘긴다
					getContentPane().add("firstFloor", firstFloor); 
					card.show(getContentPane(), "firstFloor"); 
				}
				requestFocus();
				repaint();
			}// if 키 움직임 end

			// 불 움직임 제어
			if(isFireTimer) {			
				for(int i=0; i< fireList.size();i++) {		
					fireList.get(i).pY++;					// 불 이미지들의 위치를내린다
					
					if(fireList.get(i).pY == 180) {			// 불 이미지의 y좌표가 180에 도달하면 새로운 불을 리스트에 추가해준다
						fireList.add(new PosImageIcon(FIRE,(int)(Math.random()*350 + 150),0,true));
					}
					
					if(fireList.get(i).pY == 400) {			// 불 이미지의 y좌표가 400에 도달하면 불을 리스트에서 지운다
						fireList.remove(0);
					}
					
					// 왼쪽
					if(isGirl) {
						if(girlL.collide(fireList.get(i), (girlL.height + fireList.get(i).height) / 2 - 25)){	// 	불과 캐릭터가 부딪혔을 때
							if(isTank || isCrazy) {																//	아이스크림을 제대로 올려놓은 상태이고
								if(fireList.get(i).isHit && isFire) {											//	아래 방향키를 누르지 않았다면 처음 부딪혔을 때 
									x.play();																
									fireList.get(i).isHit = false;												//  isHit을 부딪혔다는 걸로 바꿔주고
									fireList.get(i).setImage(clear.getImage());									//  불 이미지를 투명한 이미지로 바꿔준다
									if(heartList.size() > 1) {													//  그리고 목숨(하트)이 1개 이상이라면
										heartList.remove(0);													// 	목숨을 하나 지워주고
									}
									else {
										getContentPane().add("gameOverPanel", gameOverPanel);					// 	 1개 밖에 남지 않았을 때는
										card.show(getContentPane(), "gameOverPanel");							//   gameOverPanel을 띄운다
									}
								}
							}
						}
						else {												// 이미지의 거리가 충돌할 만큼이 아니라면
							fireList.get(i).isHit = true;					// true로 설정해서 충돌을 판단할 수 있게 해준다
						}
					}
					
					// 오른쪽
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
										getContentPane().add("gameOverPanel", gameOverPanel); // 카드레이아웃에 coverPanel이란 이름의 coverPanel을 씌운다
										card.show(getContentPane(), "gameOverPanel"); // 카드레이아웃에서 coverPanel이란 이름을 가진 패널을 꺼내서 보여준다
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
			} // if() 불 움직임 end
		}
	} // IconTimer end
} // IceCreamGame Class end