package mFrame;

import Cs_Db.DB_Connection;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;


public class base extends JFrame {
    private JPanel header = new JPanel();
    Container c;
    private Line content = new Line();
    private JPanel footer = new JPanel();
    private JTextField pw = new JTextField("pw 입력");
    private JButton log = new JButton("로그인");
    private JLabel pi;
    private ImageIcon parking = new ImageIcon("images/parkingicon.png");

    Announce announce;
    private JLabel Password_t = new JLabel("Password");
    JComboBox <String> lc_box = new JComboBox<String>();
    String location[] = {"장영실관","하연관","늘빛관"};
    String db_name[]={"car_info","car_info_b","car_info_c"};
    DB_Connection db = new DB_Connection();

    CircleAnimation circle;

    JLabel hello_msg;

    public String get_table_name(){
        return "";
    }

    boolean check_info = false;
    int arc; // 각도

    int opaque_text = 250;
    JButton enter_bt = new JButton("접속");

    Font title_font = new Font("배민도현체",Font.BOLD,22);
    public base() {

        db.connect();
        setTitle("관리자 로그인");
        for(int i =0;  i<3;i++){
            lc_box.addItem(location[i]);
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c =getContentPane();
        c.setLayout(new GridBagLayout());

        c.setBackground(new Color(128,128,128));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;



//

        pi = new JLabel(parking);
        log.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                pw.setVisible(false);
                lc_box.setVisible(false);
                log.setVisible(false);
                Password_t.setVisible(false);
                circle = new CircleAnimation();
                circle.setBounds(10,166,544,333);
                circle.setBackground(new Color(192,192,192));
                content.add(circle);
                circle.setVisible(true);
            }
        });


        gbc.ipady =0;
        gbc.ipadx =0;
        gbc.weighty = 0.25;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth =1;
        gbc.gridheight =2;
        gbc.insets= new Insets(3,3,0,3);
        c.add(header,gbc);

        header.setBackground(new Color(128,128,128));
        header.setLayout(null);
        header.setBorder(new MatteBorder(10,10,0,10,Color.white));
        JLabel title = new JLabel("  Welcome The Parking System.");
        title.setBounds(130,c.getHeight()/10,600,100);
        //title.setForeground(new Color(215, 241, 250));

        title.setFont(title_font);

        header.add(title);

        gbc.weighty = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth =1;
        gbc.gridheight =3;

        gbc.insets= new Insets(0,3,0,3);
        c.add(content,gbc);
        content.setBackground(new Color(128,128,128));
        content.setBorder(new MatteBorder(0,10,0,10,Color.white));
        content.setLayout(null);
        JLabel content_text = new JLabel("카놀라유");
        content_text.setFont(title_font);
        //content_text.setForeground(new Color(215, 241, 250));
        content_text.setBounds(250,c.getHeight()/8,600,100);
        content.add(content_text);

        Password_t.setBounds(190,c.getHeight()/8+90,200,50);
        //반투명 설정?Color test = new Color(0,0,0,122);
        Password_t.setFont(new Font("고딕",Font.PLAIN,10));
        Password_t.setForeground(new Color(216,216,216));
        pw.setBounds(190,c.getHeight()/8+125,200,50);
        content.add(Password_t);
        content.add(pw);
        lc_box.setBounds(190,c.getHeight()/8+175,200,30);
        lc_box.setBackground(Color.white);
        content.add(lc_box);

        gbc.weighty = 0.25;
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth =1;
        gbc.gridheight =2;
        c.add(footer,gbc);
        footer.setBackground(new Color(128,128,128));
        footer.setBorder(new MatteBorder(0,10,0,10,Color.white));
        footer.setLayout(null);
        log.setBounds(190,c.getHeight()/6,200,50);
        log.setForeground(Color.white);
        log.setBackground(Color.black);
        footer.add(log);

        enter_bt.setBounds(190,c.getHeight()/6,200,50);
        enter_bt.setForeground(Color.white);
        enter_bt.setBackground(Color.black);
        footer.add(enter_bt);
        enter_bt.setVisible(false);
        enter_bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Parking(lc_box.getSelectedItem().toString(),db_name[lc_box.getSelectedIndex()]);
                setVisible(false);
            }
        });

        pw.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (pw.getText().equals("pw 입력"))
                    pw.setText("");
            }
        });

        setSize(600, 600);
        setVisible(true);
    }
    class Line extends JPanel{

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setColor(new Color(208,208,208));
            g.drawLine(70,0,530,0);
            g.drawLine(70, 250,530,250);

        }
    }

    public class CircleAnimation extends JPanel {
        public CircleAnimation() {
            hello_msg = new JLabel("Hello SuperVisor");
            // hello_msg.setBorder(new TitledBorder(new LineBorder(Color.BLUE,5)));
            hello_msg.setFont(title_font);
            check_info = db.login(lc_box.getSelectedItem().toString(), pw.getText());
            new Thread(() -> {
                arc=0;
                while (arc <= 360) {
                    arc++;
                    repaint();
                    revalidate();
                    if(arc==360 && check_info==true){
                        // setVisible(false);
                        announce = new Announce(check_info);
                        announce.setBounds(150,166,550,111);
                        content.add(announce);
                        announce.setBackground(new Color(128,128,128));
                        hello_msg.setBounds(240,212,200,50);

                        announce.add(hello_msg);

                        hello_msg.setVisible(false);
                    }
                    else if(arc == 360 && check_info == false){
                        announce = new Announce(check_info);
                        announce.setBackground(new Color(128,128,128));
                        announce.setBounds(130,166,600,111);
                        content.add(announce);
                    }
                    try {
                        Thread.sleep(2);

                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(new Color(128,128,128));
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(new Color(229, 229, 229));
            g2d.fillOval(230, 0, 100, 100);
            g2d.setColor(new Color(71, 70, 67));
            g2d.fill(new Arc2D.Float(230, 0,100, 100, 90, arc, Arc2D.PIE));
            g2d.setColor(new Color(128,128,128));

            g2d.fillOval(250, 20, 60, 60);

        }
    }
    class Announce extends JPanel {
        boolean check_info;

        public Announce(boolean check_info){
            this.check_info = check_info;
            circle.setVisible(false);
            setSize(500,100);

            //setUndecorated(false);

            if(check_info == true) {

                new Thread(() -> {

                    while (opaque_text >= 0) {
                        if (opaque_text == 0) {

                            enter_bt.setVisible(true);
                            hello_msg.setVisible(true);
                            return;
                        }

                        opaque_text -= 10;

                        repaint();
                        revalidate();

                        try {
                            Thread.sleep(50);
                            if (opaque_text == 0) {
                                Thread.sleep(80);
                            }
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            return;
                        }
                    }
                }).start();
            }
            else{
                Password_t.setText("Please, Retry");
                Password_t.setFont( new Font("제주고딕",Font.PLAIN,15));
                Password_t.setForeground(new Color(242,34,34));
                Password_t.setVisible(true);
                log.setVisible(true);
                lc_box.setVisible(true);
                pw.setVisible(true);
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setSize(300,50);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            setBackground(new Color(128,128,128));
            // opaque_text = 100;



            if(check_info == true){


                g2d.setColor(new Color(0,0,0,opaque_text));

                g2d.drawString("success",115,20 );

            }

        }
    }
    public static void main(String[] args) {
        new base();
    }
}
