package com.notjuststudio.fpnt;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

/**
 * @author KLLdon
 */
public class GUI {

    private static final String TITLE = "FPNTViewer";
    private static final String TITLE_SEPARATOR = " - ";

    private static final GUI gui;

    private final JFrame window;

    static {
        gui = new GUI();
    }

    private void openFile() {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogTitle("Open file");
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || FPNTDecoder.checkFile(f);
            }

            @Override
            public String getDescription() {
                return "FPNT file (.fpnt)";
            }
        });
        final int ret = fileChooser.showDialog(gui.window, "Open");
        if (ret == JFileChooser.APPROVE_OPTION)
            openFile(fileChooser.getSelectedFile());
    }

    private void openFile(final String filePath) {
        openFile(new File(filePath));
    }

    private void openFile(final File file) {

    }

    private GUI() {
        initLookAndFeel();

        window = new JFrame(TITLE) {{
            setSize(new Dimension(800, 600));
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    close();
                }
            });

            setJMenuBar(new JMenuBar() {{

                add(new JMenu("File") {{

                    setMnemonic('f');
                    add(new JMenuItem("Open") {{
                        setMnemonic('o');
                        addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                gui.openFile();
                            }
                        });
                    }});


                }});

            }});


        }};
        window.setVisible(true);
    }

    private void close() {
        gui.window.dispose();
        System.exit(0);
    }

    private static void createAndShowGUI() {

    }

    private void initLookAndFeel() {
        final String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        try {

            UIManager.setLookAndFeel(lookAndFeel);
        } catch (ClassNotFoundException e) {
            System.err.println("Couldn't find class for specified look and feel:"
                    + lookAndFeel);
            System.err.println("Did you include the L&F library in the class path?");
            System.err.println("Using the default look and feel.");
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Can't use the specified look and feel ("
                    + lookAndFeel
                    + ") on this platform.");
            System.err.println("Using the default look and feel.");
        } catch (Exception e) {
            System.err.println("Couldn't get specified look and feel ("
                    + lookAndFeel
                    + "), for some reason.");
            System.err.println("Using the default look and feel.");
            e.printStackTrace();
        }

        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
            if (args.length >= 1) {
                gui.openFile(args[0]);
            }
        });
    }
}
