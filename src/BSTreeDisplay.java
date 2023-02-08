import javax.swing.*;
import java.awt.*;

 class BSTreeDisplay extends JPanel{
    BinarySearchTree tree;
    BinaryNode root;

    public BSTreeDisplay() {

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(1440, 1024));
        tree= new BinarySearchTree();
        JTextField inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(100, 30));
        inputField.addActionListener(e -> {
            int value = Integer.parseInt(inputField.getText());

            tree.add(new BinaryNode(value));
            inputField.setText("");
            repaint();
        });

        JTextField deleteField = new JTextField();
        deleteField.setPreferredSize(new Dimension(100, 50));
        deleteField.setLocation(400,500);
        deleteField.addActionListener(e -> {
            int value = Integer.parseInt(deleteField.getText());
            tree.remove(value);
            deleteField.setText("");
            repaint();
        });
        JLabel deleteLabel = new JLabel("Delete:");

        deleteLabel.setSize(100,500);
        deleteLabel.setVisible(true);

        JPanel deletePanel=new JPanel();
        deletePanel.setLayout(new GridLayout(1, 2));
        deletePanel.add(deleteLabel);
        deletePanel.add(deleteField);
        add(deletePanel);
        JButton preOrderButton = new JButton("Pre-Order");
        preOrderButton.addActionListener(e -> preOrderTraversal());

        JButton postOrderButton = new JButton("Post-Order");
        postOrderButton.addActionListener(e -> postOrderTraversal());

        JButton levelOrderButton = new JButton("Level-Order");
        levelOrderButton.addActionListener(e -> levelOrderTraversal());

        JButton inOrderButton = new JButton("In-Order");
        inOrderButton.addActionListener(e -> inOrderTraversal());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));

        preOrderButton.setFocusable(false);
        levelOrderButton.setFocusable(false);
        inOrderButton.setFocusable(false);
        postOrderButton.setFocusable(false);


        buttonPanel.add(preOrderButton);
        buttonPanel.add(postOrderButton);
        buttonPanel.add(levelOrderButton);
        buttonPanel.add(inOrderButton);
        add(deleteField);
        add(inputField);
        add(buttonPanel, BorderLayout.SOUTH);
    }
     public void preOrderTraversal() {
         String output = tree.preOrder();
         JOptionPane.showMessageDialog(this, output,"Pre Order Traversal",JOptionPane.INFORMATION_MESSAGE);
     }

     public void postOrderTraversal() {
         String output = tree.postOrder();

         JOptionPane.showMessageDialog(this, output,"Post Order Traversal",JOptionPane.INFORMATION_MESSAGE);
     }

     public void levelOrderTraversal() {
         String output = tree.levelOrder();
         JOptionPane.showMessageDialog(this, output,"Level Order Traversal",JOptionPane.INFORMATION_MESSAGE);
     }
     public void inOrderTraversal() {
         String output = tree.inOrder();
         JOptionPane.showMessageDialog(this, output,"In Order Traversal",JOptionPane.INFORMATION_MESSAGE);
     }
     public void paintComponent(Graphics g) {
         super.paintComponent(g);
         drawTree(g, tree.getNode(), getWidth() / 2, 100, getWidth()/4);
     }

     private void drawTree(Graphics g, BinaryNode node, int x, int y, int spacing) {
         if (node == null) {
             return;
         }
         g.setColor(new Color(0xACACAC));
         g.fillOval(x - 10, y - 10, 30, 30);
         g.setColor(Color.black);
         g.drawString(Integer.toString((Integer) node.getValue()), x - 4, y + 10);
         if (node.left()!= null) {
             g.drawLine(x, y+15, x - spacing, y + 50);
             drawTree(g, node.left(), x - spacing, y + 50, spacing / 2);
         }
         if (node.right()!= null) {
             g.drawLine(x, y+15, x + spacing, y + 50);
             drawTree(g, node.right(), x + spacing, y + 50, spacing / 2);
         }
     }



}
