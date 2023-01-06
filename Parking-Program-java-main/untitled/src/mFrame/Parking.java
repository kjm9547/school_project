package mFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import Cs_Db.Surprise_Db;

public class Parking extends JFrame {
    private JButton enter, exit;

    public JLabel[] parking = new JLabel[63];
    public JLabel charge2;

    BevelBorder border;

    JComboBox<String> field_c = new JComboBox<String>();

    private DigitalClock digitalClock;

    Surprise_Db db;

    //Index_info info[] = new Index_info[12];
    //전역 변수
    public LocalDateTime entertime;

    LocalDateTime[] d2 = new LocalDateTime[63];
    JButton bt_floor[] = new JButton[3];
    String button_image_url[] = {
            "1", "2", "3" // 추가 예정
    };
    String text_floor[] = {"1층", "2층", "3층"};
    long exitTime;

    String c_num;

    private ImageIcon car = new ImageIcon("C:\\Users\\고동민\\Desktop\\자바팀플 20192601 고동민\\Parking-Program-java\\untitled\\images\\enter.png");

    private ImageIcon logo1 = new ImageIcon("C:\\Users\\고동민\\Desktop\\자바팀플 20192601 고동민\\Parking-Program-java\\untitled\\images\\img_3.png");

    private ImageIcon logo2 = new ImageIcon("C:\\Users\\고동민\\Desktop\\자바팀플 20192601 고동민\\Parking-Program-java\\untitled\\images\\입차.png");

    private ImageIcon logo3 = new ImageIcon("C:\\Users\\고동민\\Desktop\\자바팀플 20192601 고동민\\Parking-Program-java\\untitled\\images\\출차.png");

    private ImageIcon place = new ImageIcon("C:\\Users\\고동민\\Desktop\\자바팀플 20192601 고동민\\Parking-Program-java\\untitled\\images\\images.png");

    private ImageIcon conBack = new ImageIcon("C:\\Users\\고동민\\Desktop\\자바팀플 20192601 고동민\\Parking-Program-java\\untitled\\images\\img.png");

    //private showThread st;

    private ImageIcon bt_floor_1 = new ImageIcon("C:\\Users\\고동민\\Desktop\\자바팀플 20192601 고동민\\Parking-Program-java\\untitled\\images\\bt_floor_1.png");
    private ImageIcon bt_floor_2 = new ImageIcon("C:\\Users\\고동민\\Desktop\\자바팀플 20192601 고동민\\Parking-Program-java\\untitled\\images\\bt_floor_2.png");
    private ImageIcon bt_floor_3 = new ImageIcon("C:\\Users\\고동민\\Desktop\\자바팀플 20192601 고동민\\Parking-Program-java\\untitled\\images\\bt_floor_3.png");
    private ImageIcon bt_floor_1_click = new ImageIcon("C:\\Users\\고동민\\Desktop\\자바팀플 20192601 고동민\\Parking-Program-java\\untitled\\images\\bt_floor_1_click.png");
    private ImageIcon bt_floor_2_click = new ImageIcon("C:\\Users\\고동민\\Desktop\\자바팀플 20192601 고동민\\Parking-Program-java\\untitled\\images\\bt_floor_2_click.png");
    private ImageIcon bt_floor_3_click = new ImageIcon("C:\\Users\\고동민\\Desktop\\자바팀플 20192601 고동민\\Parking-Program-java\\untitled\\images\\bt_floor_3_click.png");

    private JLabel enterNotice = new JLabel();

    private JLabel exitNotice = new JLabel();
    JPanel logo = new JPanel() {
        Image img = logo1.getImage();

        public void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    };


    JPanel header = new JPanel();

    Chart_Circle side = new Chart_Circle();

    JPanel content = new JPanel();

    JPanel charge_section = new JPanel();

    Color pinky = new Color(236, 64, 122);

    Color shinysky = new Color(0, 188, 212);

    Color teal = new Color(153, 204, 153);

    Color purplebubble = new Color(103, 58, 183);

    Color[] colors = {pinky, shinysky, purplebubble, Color.gray};

    String[] floorsgraph = {"1st", "2nd", "3rd", "rest"};

    public int cnt_whole = 63;

    public int cnt_graph[] = new int[4];

    JLabel time = new JLabel("");

    int[] angle = new int[4];

    int[] n = new int[4];
    private JLabel logoLabel = new JLabel();

    private Container c;

    private JLabel space;

    int firstNum = 0;

    int secondNum = 0;

    int thirdNum = 0;

    int j;
    int[] count = new int[63];
    JPanel timePanel = new JPanel();

    public Parking(String table_name, String lc_text) {
        db = new Surprise_Db(lc_text);
        db.connect();
        //db.default_create();// 1번만 쓰고 지워요 테이블 디폴트값 설정 용도
        setTitle("주차 관리 시스템");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c = getContentPane();
        c.setLayout(new GridBagLayout());
        /*깔끔한 코드를 위해 레이아웃을 그리드백레이아웃이라는 녀석으로 제가 했는데
          레이아웃 만지고 싶은데 어케 해야 할지 모르겠으면 검색 혹은 저에게 물어봐주세용
          간단히 설명 하자면 155줄 부터 레이아웃 상세 설정 해놓은 건데
          비율 설정해서 화면 분할 한 거에여
          분할된 화면은 내용물 크기에 따라 x축이 자동으로 설정되고 높이는 고정
        */


        //content.setBorder(new TitledBorder(new LineBorder(Color.red, 5)));
        //side.setBorder(new TitledBorder(new LineBorder(Color.red, 5)));
        logo.setBorder(new MatteBorder(1, 0, 0, 1, new Color(0, 0, 0)));
        //header.setBorder(new TitledBorder(new LineBorder(Color.ORANGE, 5)));


        logo.setBackground(Color.WHITE);

        for (int i = 0; i < 63; i++) {
            count[i] = 0;
        }
        time.setFont(new Font("Serif", Font.PLAIN, 15));
        time.setForeground(Color.WHITE);
        time.setHorizontalAlignment(SwingConstants.CENTER);

        Image img = bt_floor_1_click.getImage();
        Image changeImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon changeIcon = new ImageIcon(changeImg);

        Image img1 = bt_floor_2_click.getImage();
        Image changeImg1 = img1.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon changeIcon1 = new ImageIcon(changeImg1);

        Image img3 = bt_floor_3_click.getImage();
        Image changeImg3 = img3.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon changeIcon3 = new ImageIcon(changeImg3);

        Image img2 = bt_floor_1.getImage();
        Image changeImg2 = img2.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon changeIcon2 = new ImageIcon(changeImg2);

        Image img4 = bt_floor_2.getImage();
        Image changeImg4 = img4.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon changeIcon4 = new ImageIcon(changeImg4);


        Image img5 = bt_floor_3.getImage();
        Image changeImg5 = img5.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon changeIcon5 = new ImageIcon(changeImg5);


        timePanel.add(time);
        bt_floor[0] = new JButton(changeIcon);

        bt_floor[1] = new JButton(changeIcon4);

        bt_floor[2] = new JButton(changeIcon5);
        JPanel page_f[] = new JPanel[3];
        for (int i = 0; i < 3; i++) {
            cnt_graph[i] = 0;
            page_f[i] = new JPanel();
            page_f[i].setBackground(new Color(105, 105, 105));
            bt_floor[i].setName(i + "");
            bt_floor[i].setBackground(Color.gray);
            bt_floor[i].setBorder(null);
            bt_floor[i].setContentAreaFilled(false);
            bt_floor[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton field = (JButton) e.getSource();
                    int index = Integer.parseInt(field.getName());
                    for (int i = 0; i < 3; i++) {

                        if (i == index) {
                            page_f[i].setVisible(true);

                        } else {
                            page_f[i].setVisible(false);
                        }
                    }


                }
            });
            bt_floor[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    JButton field = (JButton) e.getSource();
                    int index = Integer.parseInt(field.getName());
                    if (index == 0) {
                        bt_floor[0].setIcon(changeIcon);
                        bt_floor[1].setIcon(changeIcon4);
                        bt_floor[2].setIcon(changeIcon5);
                    }
                    if (index == 1) {
                        bt_floor[1].setIcon(changeIcon1);
                        bt_floor[0].setIcon(changeIcon2);
                        bt_floor[2].setIcon(changeIcon5);
                    }
                    if (index == 2) {
                        bt_floor[2].setIcon(changeIcon3);
                        bt_floor[1].setIcon(changeIcon4);
                        bt_floor[0].setIcon(changeIcon2);
                    }
                }
            });
        }
        cnt_graph[3] = 0;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;


        charge_section.setPreferredSize(new Dimension(200, 200));
        charge_section.setLayout(new GridLayout(4, 1));
        enter = new JButton("입차");
        exit = new JButton("출차");
        enter.setBackground(Color.white);
        exit.setBackground(Color.white);
        charge2 = new JLabel("정산");
        charge2.setHorizontalAlignment(SwingConstants.CENTER);
        timePanel.setBackground(Color.BLACK);
        charge_section.add(enter);
        charge_section.add(exit);
        charge_section.add(charge2);
        charge_section.add(timePanel);
        digitalClock = new DigitalClock(time);
        digitalClock.start();

        Color color = new Color(204, 204, 255);
        content.setBorder(new MatteBorder(1, 0, 0, 0, new Color(0, 0, 0)));
        side.setBorder(new MatteBorder(1, 0, 0, 1, new Color(0, 0, 0)));
        side.setLayout(null);
        charge_section.setBorder(new MatteBorder(1, 0, 0, 1, new Color(0, 0, 0)));

        int a = 0; // 주차공간 id에 접근하기위해서 임의로 값 수정 하기위해 사용한 변수
        int stack = 0;// 주차장의 층 확인용 변수

        for (int i = 0; i < 22; i++) {
            if (i == 21 && stack == 2) break;

            if (i > 20 && stack == 0) {
                stack = 1;
                i -= 21;
                a = 21;
            }

            if (i > 20 && stack == 1) {
                stack = 2;
                i -= 21;
                a = 42;
            }


            if (db.check_parking(i + a + 1) && stack + 1 == db.get_car_floor(i + a + 1)) {
                entertime = db.get_Init_Time(i + a + 1);


                //+ "시 " + entertime.getMinute() + "분"
                c_num = db.get_Car_Num(i + a + 1);
                parking[i + a] = new JLabel();
                parking[i + a].setName(i + a + 1 + "");
                parking[i + a].setIcon(car);
                parking[i + a].setPreferredSize(new Dimension(150, 170));
                page_f[stack].add(parking[i + a]);
                parking[i + a].setBackground(Color.pink);
                parking[i + a].setFont(new Font("Gothic", Font.PLAIN, 20));
                parking[i + a].setHorizontalAlignment(parking[i + a].CENTER);
                parking[i + a].setBorder(new LineBorder(Color.WHITE, 5));
                parking[i + a].setOpaque(true);
                if (stack == 0) {
                    cnt_graph[0]++;
                } else if (stack == 1) {
                    cnt_graph[1]++;
                } else if (stack == 2) {
                    cnt_graph[2]++;
                }

            } else {
                parking[i + a] = new JLabel(Integer.toString(i + 1));
                parking[i + a].setForeground(Color.WHITE);
                parking[i + a].setPreferredSize(new Dimension(150, 170));
                parking[i + a].setName(i + a + 1 + "");
                page_f[stack].add(parking[i + a]);
                parking[i + a].setBackground(new Color(105, 105, 105));
                parking[i + a].setBorder(new LineBorder(Color.WHITE, 5));
                parking[i + a].setFont(new Font("Gothic", Font.PLAIN, 20));
                parking[i + a].setHorizontalAlignment(parking[i + a].CENTER);

                parking[i + a].setOpaque(true);
                cnt_graph[3]++;
            }
        }

        for (int i = 0; i < 3; i++) {
            page_f[i].setLayout(new GridLayout(3, 7, 0, 50));
            //page_f[i].setBorder(new TitledBorder(new LineBorder(Color.red, 5)));
            content.add(page_f[i]);
            page_f[0].setVisible(true);
            page_f[1].setVisible(false);
            page_f[2].setVisible(false);
        }
        gbc.ipady = 0;
        gbc.ipadx = 0;


        gbc.weightx = 0.2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        c.add(logo, gbc);
        gbc.weightx = 0.7;

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        c.add(header, gbc);


        header.setLayout(null);
        for (int i = 0; i < 3; i++) {
            bt_floor[i].setBounds(1100 + 50 * i, 80, 50, 100);
            header.add(bt_floor[i]);
        }
        Font title_font = new Font("배민도현체", Font.BOLD, 22);
        JLabel location_name = new JLabel(table_name);
        location_name.setFont(title_font);
        location_name.setBounds(180, 50, 100, 100);
        header.add(location_name);

        //c.add(field_c);
        gbc.weightx = 0.2;
        gbc.weighty = 0.7;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 5;
        c.add(side, gbc);

        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.gridheight = 3;
        c.add(charge_section, gbc);


        gbc.weightx = 0.7;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 9;
        gbc.gridwidth = 3;
        c.add(content, gbc);


        logo.add(logoLabel);
        for (j = 0; j < 62; j++) {
            parking[j].addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    JLabel label = (JLabel) e.getSource();
                    int ed = Integer.parseInt(label.getName());
                    int d = 0;
                    if (e.getClickCount() == 2) {
                        if (count[ed - 1] == 0) {
                            if (db.check_parking(ed) == true) {
                                c_num = db.get_Car_Num(ed);
                                entertime = db.get_Init_Time(ed);
                                label.setIcon(null);
                                label.setText("<html><body><center>"
                                        + c_num + "<br><br>" + entertime.getMonthValue() + "월 " + entertime.getDayOfMonth() + "일 <br>"  + entertime.getHour()
                                        + "시 " + entertime.getMinute() + "분" + "</center></body></html>");
                                label.setBackground(new Color(38, 35, 36));
                                label.setForeground(Color.WHITE);
                                count[ed - 1] = 1;
                            }
                        } else if (count[ed - 1] == 1) {
                            label.setIcon(car);
                            label.setText("");
                            count[ed - 1] = 0;
                        }
                    }
                }


                /*@Override
                public void mouseEntered(MouseEvent e) {
                    JLabel label = (JLabel) e.getSource();
                    int ed = Integer.parseInt(label.getName());
                    if(db.check_parking(ed) == true) {
                        c_num = db.get_Car_Num(ed);
                        entertime = db.get_Init_Time(ed);
                        label.setIcon(null);
                        label.setText("<html><body><center>"
                                + c_num + "<br><br>" + entertime.getHour()
                                + "시 " + entertime.getMinute() + "분" + "</center></body></html>");
                        label.setBackground(Color.pink);
                    }
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    JLabel label = (JLabel) e.getSource();
                    if (label.getText().length() > 4) {
                        label.setIcon(car);
                        label.setText("");
                    }
                }*/
            });
        }
        content.setBackground(Color.lightGray);
        side.setBackground(Color.GRAY);
        header.setBackground(Color.lightGray);


        enter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                new Enter();
            }
        });

        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                new Exit();
            }
        });
        setSize(1732, 922);
        setVisible(true);
    }


    public class Enter extends JFrame {
        private JButton en = new JButton("입차");

        private JTextField carnum = new JTextField("car_num");

        private JTextField num = new JTextField("0");

        private JTextField floor = new JTextField("0");

        public Enter() {
            setTitle("입차");
            Container c = getContentPane();
            c.setLayout(null);
            c.setBackground(Color.white);
            en.setBackground(Color.gray);
            carnum.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (carnum.getText().equals("car_num"))
                        carnum.setText("");
                }
            });
            num.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (num.getText().equals("0"))
                        num.setText("");
                }
            });

            floor.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (floor.getText().equals("0"))
                        floor.setText("");
                }
            });
            en.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    boolean can_enter = false;
                    entertime = LocalDateTime.now();
                    int select_index = Integer.parseInt(num.getText());
                    c_num = carnum.getText();
                    int fl = Integer.parseInt(floor.getText());
                    if (select_index < 21 && 0 < select_index && fl < 4 && fl > 0) {
                        can_enter = true;
                    }
                    if (can_enter) {
                        if (fl == 2) {
                            select_index += 21;
                            cnt_graph[1]++;
                            cnt_graph[3]--;
                            secondNum++;
                        } else if (fl == 3) {
                            select_index += 42;
                            thirdNum++;
                            cnt_graph[2]++;
                            cnt_graph[3]--;
                        } else if (fl == 1) {
                            firstNum++;
                            cnt_graph[0]++;
                            cnt_graph[3]--;
                        }
                        parking[select_index - 1].setText("");
                        parking[select_index - 1].setIcon(car);
                        parking[select_index - 1].setVerticalTextPosition(SwingConstants.BOTTOM);

                        db.init_car(select_index - 1, c_num, entertime, fl);
                        side.repaint();
                        JOptionPane.showMessageDialog(null, "입차 완료", "message", JOptionPane.INFORMATION_MESSAGE);
                        setVisible(false);//입력 후 창 닫기
                    } else {
                        JOptionPane.showMessageDialog(null, "범위 확인 해주세요", "message", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });

            en.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    entertime = LocalDateTime.now();
                    int select_index = Integer.parseInt(num.getText());
                    c_num = carnum.getText();
                    int fl = Integer.parseInt(floor.getText());
                    if (fl == 2) {
                        select_index += 20;
                    } else if (fl == 3) {
                        select_index += 41;
                    }
                    enterNotice.setFont(new Font("Serif", Font.BOLD, 30));
                    enterNotice.setForeground(Color.BLUE);
                    enterNotice.setText("<html><body><center>" + +entertime.getHour() + "시" + entertime.getMinute() + "분<br>"
                            + Integer.toString(fl) + "층" + Integer.toString(select_index) + "번 자리에 " + c_num + "차량 입차되었습니다</center></body></html>");
                    /*st = new showThread(enterNotice, header, 0, e.getY());
                    st.start();*/
                    revalidate();
                }
            });
            JLabel text1 = new JLabel("차량 번호:");
            text1.setBounds(10, 20, 200, 50);
            carnum.setBounds(75, 20, 200, 50);
            c.add(text1);
            c.add(carnum);
            JLabel text2 = new JLabel("주차 번호:");
            text2.setBounds(10, 70, 200, 50);
            num.setBounds(75, 70, 200, 50);
            c.add(text2);
            c.add(num);


            JLabel text3 = new JLabel("주차 층    :");
            text3.setBounds(10, 120, 200, 50);
            floor.setBounds(75, 120, 200, 50);
            c.add(text3);
            c.add(floor);
            en.setBounds(75, 170, 200, 50);
            c.add(en);
            setSize(300, 300);
            setVisible(true);
        }
    }

    public class Exit extends JFrame {

        private JButton ex = new JButton("출차");

        private JButton back = new JButton("돌아가기");

        private JTextField num = new JTextField("0");

        private JTextField select_floor = new JTextField("0");

        public LocalDateTime exittime;
        String s;

        public Exit() {

            setTitle("출차");

            Container d = getContentPane();

            d.setLayout(null);
            d.setBackground(Color.white);
            ex.setBackground(Color.gray);
            back.setBackground(Color.gray);
            num.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (num.getText().equals("0"))
                        num.setText("");
                }
            });

            select_floor.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (select_floor.getText().equals("0"))
                        select_floor.setText("");
                }
            });

            ex.addActionListener(new ActionListener() {

                @Override

                public void actionPerformed(ActionEvent e) {

                    // TODO Auto-generated method stub
                    boolean can_enter = false;
                    exittime = LocalDateTime.now();

                    /*if (exitTime > 60) {
                        charge2.setText(Long.toString(exitTime / 60) + "분" + Long.toString(exitTime % 60) + "초");
                    } else {
                        charge2.setText(s + "초");
                    }*/
                    int fl = Integer.parseInt(select_floor.getText());
                    int value = Integer.parseInt(num.getText());
                    if (cnt_graph[3] < 63) {
                        can_enter = true;
                    } else if (value < 21 && 0 < value && fl < 4 && fl > 0) {
                        can_enter = true;
                    }

                    if (can_enter) {


                        if (fl == 2) {
                            value += 21;
                            cnt_graph[1]--;
                            cnt_graph[3]++;
                        } else if (fl == 3) {
                            value += 42;
                            cnt_graph[2]--;
                            cnt_graph[3]++;
                        } else {
                            cnt_graph[0]--;
                            cnt_graph[3]++;
                        }

                        d2[value-1] = LocalDateTime.of(exittime.getYear(), exittime.getMonth(), exittime.getDayOfMonth(),
                                exittime.getHour(), exittime.getMinute(), exittime.getSecond());
                        setVisible(false);

                        exitTime = ChronoUnit.SECONDS.between(db.get_Init_Time(value), d2[value-1]);
                        s = Long.toString(exitTime);
                        System.out.println(exitTime+"````````````````````````````````");
                        if (exitTime > 1800) {
                            exitTime -= 1800;
                            exitTime = exitTime / 600 * 500 + 1500;
                        } else if (exitTime <= 1800 && exitTime > 0) {
                            exitTime = 1500;
                        }
                        s = Long.toString(exitTime);
                        charge2.setFont(new Font("Gothic", Font.PLAIN, 15));
                        charge2.setText(db.get_Car_Num(value) + "차량의 요금은 " + s + "원 입니다");

                        db.Out_car(value);
                        parking[value - 1].setIcon(null);
                        parking[value - 1].setForeground(Color.WHITE);
                        parking[value - 1].setBackground(new Color(105, 105, 105));
                        parking[value - 1].setBorder(new LineBorder(Color.WHITE, 5));
                        parking[value - 1].setText(num.getText());
                        side.repaint();
                        JOptionPane.showMessageDialog(null, "출차 완료", "message", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if (cnt_graph[3] == 63) {
                            JOptionPane.showMessageDialog(null, "출차 가능한 차량이 없습니다.", "message", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "범위 확인 해주세요", "message", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }
                }

            });

            ex.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    exittime = LocalDateTime.now();
                    int select_index = Integer.parseInt(num.getText());
                    int fl = Integer.parseInt(select_floor.getText());

                    if (select_index > 21 || 0 > select_index) {
                        num.setText("범위 초과 ");
                    } else if (fl > 4 || fl < 0) {
                        select_floor.setText("범위 초과");
                    } else {
                        if (fl == 2) {
                            select_index += 20;
                        } else if (fl == 3) {
                            select_index += 41;
                        }
                        exitNotice.setFont(new Font("Serif", Font.BOLD, 30));
                        exitNotice.setForeground(Color.BLUE);
                        exitNotice.setText("<html><body><center>" + Integer.toString(fl) + "층 " + Integer.toString(select_index) + "번 자리에 " + exittime.getHour() + "시 " + exittime.getMinute() + "분 출차되었습니다</center></body></html>");
                    /*st = new showThread(exitNotice, header, 0, e.getY());
                    st.start();*/

                        revalidate();
                    }
                }
            });
            JLabel text1 = new JLabel("");
            text1.setText("주차 번호:");
            text1.setBounds(10, 20, 70, 50);
            d.add(text1);
            num.setBounds(75, 20, 200, 50);
            d.add(num);
            JLabel text2 = new JLabel("");
            text2.setText("주차 층    :");
            text2.setBounds(10, 70, 70, 50);
            d.add(text2);
            select_floor.setBounds(75, 70, 200, 50);

            d.add(select_floor);
            ex.setBounds(20, 150, 100, 25);
            d.add(ex);


            setSize(300, 300);

            setVisible(true);

        }

    }

    class Chart_Circle extends JPanel {
        public void paintComponent(Graphics g) {
            int sum = 0;
            super.paintComponent(g);
            //setSize(c.getWidth()/5,c.getHeight()/9*5);
            setBackground(Color.white);

            int startAngle = 0;
            for (int i = 0; i < floorsgraph.length; i++) {
                g.setColor(colors[i]);
                g.setFont(new Font("배민도현체", Font.BOLD, 14));
                g.drawString(floorsgraph[i] + " :" + cnt_graph[i] + "", 30, 300 + i * 30);
            }
            for (int i = 0; i < floorsgraph.length; i++) {
                sum += cnt_graph[i];
            }
            for (int i = 0; i < floorsgraph.length; i++) {
                angle[i] = (int) Math.round((double) cnt_graph[i] / (double) sum * 360);
                /*
                 * repaint();s g.setColor(colors[i]);
                 * g.fillArc(150,50,200,200,startAngle,angle[i]); startAngle += angle[i];
                 */
            }
            g.setColor(pinky);
            g.fillArc(50, 50, 200, 200, 0, angle[0]);
            g.setColor(shinysky);
            g.fillArc(50, 50, 200, 200, angle[0], angle[1]);
            g.setColor(purplebubble);
            g.fillArc(50, 50, 200, 200, angle[0] + angle[1], angle[2]);
            g.setColor(Color.lightGray);
            g.fillArc(50, 50, 200, 200, angle[0] + angle[1] + angle[2], angle[3]);

        }
    }

    public static void main(String[] args) {
        new base();
    }
}