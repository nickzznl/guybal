package dataBal.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class FormPanel extends JPanel {

    private JLabel nameLabel;
    private JLabel positionLabel;
    private JLabel ageLabel;
    private JLabel backNumberLabel;
    private JLabel knvbValidLabel;
    private JLabel genderLabel;
    private JLabel knvbMemberLabel;
    private JTextField nameField;
    private JTextField positionField;
    private JTextField backNumberField;
    private JButton okBtn;
    private FormListener formListener;
    private JList ageList;
    private JCheckBox knvbCheck;
    private JTextField knvbNumberField;


    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private ButtonGroup genderGroup;


    public FormPanel()
    {
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        nameLabel = new JLabel("Name: ");
        positionLabel = new JLabel("Position: ");
        ageLabel = new JLabel("Age: ");
        backNumberLabel = new JLabel("Back number: ");
        knvbValidLabel = new JLabel("KNVB number: ");
        genderLabel = new JLabel("Gender: ");
        nameField = new JTextField(10);
        positionField = new JTextField(10);
        ageList = new JList();
        backNumberField = new JTextField(10);
        knvbCheck = new JCheckBox();
        knvbNumberField = new JTextField(10);
        knvbMemberLabel = new JLabel("KNVB lid? ");
        maleRadio = new JRadioButton("male");
        femaleRadio = new JRadioButton("female");
        okBtn = new JButton("OK");

        //Set up Mnenomics
        okBtn.setMnemonic(KeyEvent.VK_O);

        nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
        nameLabel.setLabelFor(nameField);

        maleRadio.setActionCommand("male");
        femaleRadio.setActionCommand("female");

        genderGroup = new ButtonGroup();

        maleRadio.setSelected(true);

        // set up gender radios
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        // set up tax ID
        knvbValidLabel.setEnabled(false);
        knvbNumberField.setEnabled(false);

        knvbCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isTicked = knvbCheck.isSelected();
                knvbValidLabel.setEnabled(isTicked);
                knvbNumberField.setEnabled(isTicked);
            }
        });

        // set up list box
        DefaultListModel ageModel = new DefaultListModel();
        ageModel.addElement(new AgeCategory(0, "Under 12"));
        ageModel.addElement(new AgeCategory(1,"12 to 18"));
        ageModel.addElement(new AgeCategory(2, "18 and over"));
        ageList.setModel(ageModel);

        ageList.setPreferredSize(new Dimension(110, 70));
        ageList.setBorder(BorderFactory.createEtchedBorder());
        ageList.setSelectedIndex(1);

        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String position = positionField.getText();
                AgeCategory ageCat = (AgeCategory)ageList.getSelectedValue();
                int backNumber = Integer.parseInt(backNumberField.getText());
                int knvbNumber = Integer.parseInt(knvbNumberField.getText());
                boolean knvbMember = knvbCheck.isSelected();

                String genderCommand = genderGroup.getSelection().getActionCommand();

                FormEvent ev = new FormEvent(this, name, position, ageCat.getId(), backNumber, knvbNumber, knvbMember, genderCommand);

                if(formListener != null)
                {
                    formListener.formEventOccurred(ev);
                }
            }
        });

        Border innerBorder = BorderFactory.createTitledBorder("Add player");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        layoutComponents();
    }

    public void LayoutFunctionOne (int gridy, double weighty, int anchor, JComponent component)
    {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridy = gridy;
        gc.weightx = 1;
        gc.weighty = weighty;
        gc.gridx = 0;
        gc.anchor = anchor;
        gc.insets = new Insets(0, 0, 0, 5);
        add(component, gc);
    }
    public void LayoutFunctionTwo(int gridy, int anchor, JComponent component)
    {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridy = gridy;
        gc.gridx = 1;
        gc.anchor = anchor;
        gc.insets = new Insets(0, 0, 0, 0);
        add(component, gc);
    }
    public void LayoutFunctionSolo(int gridy, double weighty, int anchor, JComponent component)
    {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridy = gridy;

        gc.weightx = 1;
        gc.weighty = weighty;

        gc.gridx = 1;
        gc.anchor = anchor;
        gc.insets = new Insets(0, 0, 0, 0);
        add(component, gc);
    }


    public void layoutComponents()
    {
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        //First row//
        LayoutFunctionOne(0,0.1, GridBagConstraints.LINE_END, nameLabel);
        LayoutFunctionTwo(0, GridBagConstraints.LINE_START, nameField);

        //Next row//
        LayoutFunctionOne(1,0.1, GridBagConstraints.LINE_END, positionLabel);
        LayoutFunctionTwo(1, GridBagConstraints.LINE_START, positionField);

        //Next row//
        LayoutFunctionOne(2,0.1, GridBagConstraints.FIRST_LINE_END, ageLabel);
        LayoutFunctionTwo(2, GridBagConstraints.FIRST_LINE_START, ageList);

        //Next row//
        LayoutFunctionOne(3,0.2, GridBagConstraints.FIRST_LINE_END, backNumberLabel);
        LayoutFunctionTwo(3, GridBagConstraints.FIRST_LINE_START, backNumberField);

        //Next row//
        LayoutFunctionOne(4,0.2, GridBagConstraints.FIRST_LINE_END, knvbMemberLabel);
        LayoutFunctionTwo(4, GridBagConstraints.FIRST_LINE_START, knvbCheck);

        //Next row//
        LayoutFunctionOne(5,0.2, GridBagConstraints.FIRST_LINE_END, knvbValidLabel);
        LayoutFunctionTwo(5, GridBagConstraints.FIRST_LINE_START, knvbNumberField);

        //Next row//
        LayoutFunctionOne(6,0.2, GridBagConstraints.LINE_END, genderLabel);
        LayoutFunctionTwo(6, GridBagConstraints.LINE_START, maleRadio);

        //Next row//
        LayoutFunctionSolo(7, 0.1, GridBagConstraints.FIRST_LINE_START, femaleRadio);

        //Next row//
        LayoutFunctionSolo(8, 2.0, GridBagConstraints.FIRST_LINE_START, okBtn);
    }

    public void setFormListener(FormListener listener)
    {
        this.formListener = listener;
    }
}

class AgeCategory
{
    private int id;
    private String text;

    public AgeCategory(int id, String text)
    {
        this.id = id;
        this.text = text;
    }

    public String toString()
    {
        return text;
    }

    public int getId()
    {
        return id;
    }
}

