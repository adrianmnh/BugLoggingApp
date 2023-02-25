package classes;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CellRenderer {

    public static void main(String[] args) {
        new CellRenderer();
    }

    public CellRenderer() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                File file = new File("src/assets/gal.png");
                File[] files = {file, file, file,file, file};
                ImageTableModel model = new ImageTableModel();
                for (File f : files) {
                    try {
                        model.add(ImageIO.read(file));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

                JTable table = new JTable(model);
                table.setRowHeight(100);
                table.setDefaultRenderer(BufferedImage.class, new BufferedImageCellRenderer());

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new JScrollPane(table));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class BufferedImageCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (value instanceof BufferedImage) {
                setIcon(new ImageIcon((BufferedImage)value));
                setText(null);
            } else {
                setText("Bad image");
            }
            return this;
        }
    }

    public class ImageTableModel extends AbstractTableModel {

        private ArrayList<BufferedImage> images = new ArrayList<>(25);
        private ArrayList<Icon> icons = new ArrayList<Icon>(25);

        @Override
        public int getRowCount() {
            return images.size();
        }

        public void add(BufferedImage image) {
            images.add(image);
            icons.add(new ImageIcon(image));
            fireTableRowsInserted(images.size() - 1, images.size() - 1);
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Object value = null;
            switch (columnIndex) {
                case 0:
                    value = images.get(rowIndex);
                    break;
                case 1:
                    value = icons.get(rowIndex);
                    break;
            }
            return value;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            Class clazz = String.class;
            switch (columnIndex) {
                case 0:
                    clazz = BufferedImage.class;
                    break;
                case 1:
                    clazz = Icon.class;
                    break;
            }
            return clazz;
        }

        @Override
        public String getColumnName(int column) {
            String name = null;
            switch (column) {
                case 0:
                    name = "BufferedImage";
                    break;
                case 1:
                    name = "Icon";
                    break;
            }
            return name;
        }
    }
}