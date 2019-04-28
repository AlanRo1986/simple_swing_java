package view;

import system.component.*;
import system.component.Button;
import system.component.render.TreeCellRender;
import system.lib.CompactController;
import system.lib.IController;
import system.model.CheckBoxCellEditor;
import system.model.TableDataFilesModel;
import system.model.TreeNodeFileModel;
import system.model.TreeNodeModel;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.Timer;

/**
 * Created by alan on 2019/4/22.
 */
public class TestViewController extends CompactController implements IController {

    private ListBox<String> listBox;
    private Table table;
    private Button button;
    private Tree tree;

    private List<File> list;
    private TreeNodeModel root;


    public TestViewController() {
        super(1300, 768);
    }

    @Override
    protected void createInit() {
        this.setLayout(null);

        File file = new File("d:/");
        File[] files = file.listFiles((a, n) -> {
            return a.isDirectory();
        });
        String[] names = new String[files.length];
        int i = 0;
        for (File f : files) {
            names[i] = f.getName();
            i++;
        }
        listBox = new ListBox<>();
        listBox.setListData(names);
        listBox.setVisibleRowCount(10);
//        listBox.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        listBox.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//        listBox.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        listBox.setLayoutOrientation(JList.VERTICAL);
//        listBox.setLayoutOrientation(JList.VERTICAL_WRAP);
        listBox.setFont(16);
        listBox.addListSelectionListener(event -> {
            if (event.getValueIsAdjusting()) {
                out(event.getLastIndex() + ":" + listBox.getSelectedValue());
            }
        });
        listBox.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    out(listBox.getSelectedIndex() + ":" + listBox.getSelectedValue());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        JScrollPane jScrollPane = new JScrollPane(listBox);
        jScrollPane.setBounds(10, 10, 200, 400);
        add(jScrollPane);

//        Object[][] data = {
//                {"张三","三年级",156},
//                {"小四","六年级",175},
//                {"小五","二年级",165},
//                {"小六","四年级",160},
//        };
//        Object[] titles = {
//                "姓名","班级","身高（mm）"
//        };
//        table = new Table(data,titles);

//        files = file.listFiles();
//        list = new ArrayList<>();
//        for (File f : files) {
//            list.add(f);
//        }
//        TableDataFilesModel tableDataFilesModel = new TableDataFilesModel(list);
//        table = new Table(tableDataFilesModel);
//        table.setAutoCreateRowSorter(true);
////        table.setCellEditor(new CheckBoxCellEditor());
////        table.setDefaultEditor(Boolean.class, new CheckBoxCellEditor());
//
//        add(new JScrollPane(table));
//
//
//        root = new TreeNodeModel("D盘文件");
//        for (File f : files) {
//            root.add(new TreeNodeFileModel(f.getName(), f.getAbsolutePath(), f.isDirectory()));
//        }
//        DefaultTreeModel treeModel = new DefaultTreeModel(root);
//        tree = new Tree(treeModel);
//        tree.addTreeSelectionListener((e) -> {
//            if (e.getPath().getLastPathComponent() instanceof TreeNodeFileModel) {
//                TreeNodeFileModel node = (TreeNodeFileModel) e.getPath().getLastPathComponent();
//                if (node.isDirectory() && node.isLeaf()) {
//                    File d = new File(node.getFilepath());
//                    for (File f : d.listFiles()) {
//                        node.add(new TreeNodeFileModel(f.getName(), f.getAbsolutePath(), f.isDirectory()));
//                    }
//                }
//            }
//        });
//        tree.setCellRenderer(new TreeCellRender());
//        add(new JScrollPane(tree));
//
//        TextEditFormat textEditFormat = TextEditFormat.newInstanceForInteger();
//        add(textEditFormat);
//
//        button = new Button("Test");
//        button.addActionListener((e) -> {
//            tableDataFilesModel.addData(new File("e:/"));
////            DefaultMutableTreeNode parant = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
////            if (parant == null) {
////                return;
////            }
////
////            while (!parant.isRoot()) {
////                parant = (DefaultMutableTreeNode) parant.getParent();
////            }
//            TreeNodeModel parent = (TreeNodeModel) treeModel.getRoot();
//            treeModel.insertNodeInto(new TreeNodeFileModel("E盘文件", "e:/", true), parent, parent.getChildCount());
//            out(textEditFormat.getIntegerValue());
//        });
//        add(button);
//
////        try {
////            JEditorPane editorPane = new JEditorPane("http://www.lanxinbase.com");
////            editorPane.setSize(600,400);
////            add(new JScrollPane(editorPane));
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//        TextEdit textEdit = new TextEdit(20,16);
//        textEdit.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                setTableColor(textEdit.getText());
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                setTableColor(textEdit.getText());
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//
//            }
//        });
//        add(textEdit);


//        SpinnerDateModel dateModel = new SpinnerDateModel();
//        JSpinner spinner = new JSpinner(dateModel);
//        String patten = ((SimpleDateFormat) DateFormat.getTimeInstance()).toPattern();
//        spinner.setEditor(new JSpinner.DateEditor(spinner, patten));
//
//        JSpinner spinner1 = new JSpinner(new SpinnerNumberModel(5, 1, 20, 5));
//        add(spinner);
//        add(spinner1);
//        spinner.setFont(Fontx.newInstance(20));
//        spinner1.setFont(Fontx.newInstance(20));

//        JEditorPane editorPane = new JEditorPane();
//        try {
//            editorPane.setPage("http://www.lanxinbase.com");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        editorPane.setFont(Fontx.newInstance(16));
//        editorPane.setEditable(false);
//        editorPane.addHyperlinkListener((e)->{
//            out(e.getDescription());
//        });
//        add(editorPane);

//        JProgressBar progressBar = new JProgressBar(0, 100);
//        progressBar.setOpaque(true);
//        progressBar.setBackground(Color.lightGray);
//        progressBar.setForeground(Color.GREEN);
//
////        ProgressMonitor progressMonitor = new ProgressMonitor(this,"请稍后，加载中....","null__",0,100);
//
//        new Thread(() -> {
//            try {
//                while (progressBar.getValue() < 100) {
//                    progressBar.setValue(progressBar.getValue() + 1);
////                    if (progressMonitor.isCanceled()&&progressMonitor.getMinimum()!=-1){
////                        progressMonitor.setMaximum(-1);
////                        out("取消了...");
////                    }else {
////                        progressMonitor.setProgress(progressBar.getValue());
////                    }
//                    Thread.sleep(1000);
//
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }finally {
//                Thread.yield();
////                progressMonitor.close();
//                out("finnish.");
//            }
//        }).start();
//
//
//        add(progressBar);

//        JTabbedPane tabbedPane = new JTabbedPane();
//        tabbedPane.add("A",new JPanel().add(new Button("A")));
//        tabbedPane.add("B",new JPanel().add(new Button("B")));
//        tabbedPane.add("C",new JPanel().add(new Button("C")));
//        tabbedPane.add("D",new JPanel().add(new Button("D")));
//        add(tabbedPane);

        ComponentX componentX = new ComponentX(800, 600, (c, g) -> {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.BLACK);
            g2.drawRect(0, 0, c.getWidth() - 2, c.getHeight() - 2);
            g2.fillRect(0, 0, c.getWidth() - 2, c.getHeight() - 2);
            g2.setColor(Color.lightGray);

//            Rectangle2D r = new Rectangle2D.Float(5f,10f,107.5f,105f);
//            g2.fill(r);
            int n = (c.getWidth() - 10) / 30;
            int lx = 0, ly = 0;
            Random random = new Random();
            for (int j = 0; j < n; j++) {
                int y = random.nextInt(500);
                g2.setColor(Color.green);
                g2.fillOval(10 + j * 30, y, 8, 8);

                g2.setStroke(new BasicStroke(1.5f));
                if (j > 0) {
                    g2.drawLine(lx, ly, 10 + j * 30 + 4, y + 4);
                }
                lx = 10 + j * 30 + 4;
                ly = y + 4;
            }

            int j = 0;
            for (; ; ) {
                g2.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                j++;
                if (j > 1000) {
                    break;
                }
                int str = random.nextInt(122);
                if (str < 32) {
                    str = 32;
                }
                g2.rotate(random.nextInt(360));
                g2.setFont(Fontx.newInstance(20));
                g2.drawString(String.valueOf((char) str), random.nextInt(c.getWidth() - 20), random.nextInt(c.getHeight() - 20));
            }

        });
        componentX.setBounds(220, 10, 1024, 600);
        add(componentX);

        componentX.addMouseListener(new MouseAdapter() {
            private int x, y;
            private boolean isDraw;

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 1) {
                    isDraw = true;
                } else {
                    isDraw = false;
                }
                x = e.getX();
                y = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (isDraw) {
                    Graphics2D g2 = (Graphics2D) componentX.getGraphics();
                    g2.drawLine(x, y, e.getX(), e.getY());
                }
            }

        });

        Button button = new Button("处理");
        button.setBounds(10, 440, 100, 32);
        add(button);

        button.addActionListener((e) -> {
            try {
                BufferedImage bufferedImage = ImageIO.read(new File("d:/DSC_0021.JPG"));
                BufferedImage bufferedImage1 = bufferedImage.getSubimage(500, 1500, 510, 600);

                WritableRaster raster = bufferedImage1.getRaster();
                int[] ints = {0, 0, 0, 255};
                ColorModel colorModel = bufferedImage1.getColorModel();
                Object object = colorModel.getDataElements(Color.RED.getRGB(),null);
//                for (int a = 0; a < 510; a++) {
//                    for (int j = 0; j < 600; j++) {
////                        raster.setPixel(a, j, ints);
////                        raster.setDataElements(a,j,object);
//                        bufferedImage1.setRGB(a,j,250);
//                    }
//                }


                Graphics2D g2 = (Graphics2D) componentX.getGraphics();
                g2.setColor(Color.white);
                g2.fillRect(0, 0, 1024, 800);
                g2.drawImage(bufferedImage1, 0, 0, null);
                g2.drawImage(bufferedImage.getSubimage(500, 1500, 510, 600), 524, 0, null);
                componentX.updateUI();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    private void setTableColor(String text) {
        if (text == null || text.length() == 0) {
            return;
        }
        int r = Double.valueOf(text).intValue();
        if (r < 0) {
            r = 1;
        }
        if (r > 255) {
            r = 255;
        }
        Random random = new Random();
        table.setOpaque(true);
        table.setBackground(new Color(r, random.nextInt(255), random.nextInt(255)));
    }

    @Override
    public void init() {
        setTitle("高级Swing");
    }

    @Override
    public void showX() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
//        this.setResizable(false);
        this.init();
    }

    @Override
    public void close() {

    }

    @Override
    public void hideX() {

    }


}
